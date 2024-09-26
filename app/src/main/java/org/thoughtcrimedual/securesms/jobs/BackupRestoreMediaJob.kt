/*
 * Copyright 2024 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.thoughtcrimedual.securesms.jobs

import org.signal.core.util.logging.Log
import org.signal.core.util.withinTransaction
import org.thoughtcrimedual.securesms.attachments.AttachmentId
import org.thoughtcrimedual.securesms.database.AttachmentTable
import org.thoughtcrimedual.securesms.database.SignalDatabase
import org.thoughtcrimedual.securesms.database.model.MmsMessageRecord
import org.thoughtcrimedual.securesms.dependencies.AppDependencies
import org.thoughtcrimedual.securesms.jobmanager.Job
import org.thoughtcrimedual.securesms.jobmanager.impl.NetworkConstraint
import org.thoughtcrimedual.securesms.keyvalue.SignalStore
import org.thoughtcrimedual.securesms.net.NotPushRegisteredException
import kotlin.time.Duration.Companion.days

/**
 * Job that is responsible for enqueueing attachment download
 * jobs upon restore.
 */
class BackupRestoreMediaJob private constructor(parameters: Parameters) : BaseJob(parameters) {

  companion object {
    private val TAG = Log.tag(BackupRestoreMediaJob::class.java)

    const val KEY = "BackupRestoreMediaJob"
  }

  constructor() : this(
    Parameters.Builder()
      .addConstraint(NetworkConstraint.KEY)
      .setMaxAttempts(Parameters.UNLIMITED)
      .setMaxInstancesForFactory(2)
      .build()
  )

  override fun serialize(): ByteArray? = null

  override fun getFactoryKey(): String = KEY

  override fun onFailure() = Unit

  override fun onRun() {
    if (!SignalStore.account.isRegistered) {
      Log.e(TAG, "Not registered, cannot restore!")
      throw NotPushRegisteredException()
    }

    val jobManager = AppDependencies.jobManager
    val batchSize = 500
    val restoreTime = System.currentTimeMillis()

    do {
      val restoreThumbnailJobs: MutableList<RestoreAttachmentThumbnailJob> = mutableListOf()
      val restoreFullAttachmentJobs: MutableList<RestoreAttachmentJob> = mutableListOf()

      val restoreThumbnailOnlyAttachmentsIds: MutableList<AttachmentId> = mutableListOf()
      val notRestorable: MutableList<AttachmentId> = mutableListOf()

      val attachmentBatch = SignalDatabase.attachments.getRestorableAttachments(batchSize)
      val messageIds = attachmentBatch.map { it.mmsId }.toSet()
      val messageMap = SignalDatabase.messages.getMessages(messageIds).associate { it.id to (it as MmsMessageRecord) }

      for (attachment in attachmentBatch) {
        val isWallpaper = attachment.mmsId == AttachmentTable.WALLPAPER_MESSAGE_ID

        val message = messageMap[attachment.mmsId]
        if (message == null && !isWallpaper) {
          Log.w(TAG, "Unable to find message for ${attachment.attachmentId}, mmsId: ${attachment.mmsId}")
          notRestorable += attachment.attachmentId
          continue
        }

        restoreThumbnailJobs += RestoreAttachmentThumbnailJob(
          messageId = attachment.mmsId,
          attachmentId = attachment.attachmentId,
          highPriority = false
        )

        if (isWallpaper || shouldRestoreFullSize(message!!, restoreTime, SignalStore.backup.optimizeStorage)) {
          restoreFullAttachmentJobs += RestoreAttachmentJob.forInitialRestore(
            messageId = attachment.mmsId,
            attachmentId = attachment.attachmentId
          )
        } else {
          restoreThumbnailOnlyAttachmentsIds += attachment.attachmentId
        }
      }

      SignalDatabase.rawDatabase.withinTransaction {
        // Mark not restorable thumbnails and attachments as failed
        SignalDatabase.attachments.setThumbnailRestoreState(notRestorable, AttachmentTable.ThumbnailRestoreState.PERMANENT_FAILURE)
        SignalDatabase.attachments.setRestoreTransferState(notRestorable, AttachmentTable.TRANSFER_PROGRESS_FAILED)

        // Set thumbnail only attachments as offloaded
        SignalDatabase.attachments.setRestoreTransferState(restoreThumbnailOnlyAttachmentsIds, AttachmentTable.TRANSFER_RESTORE_OFFLOADED)
      }

      // Intentionally enqueues one at a time for safer attachment transfer state management
      restoreThumbnailJobs.forEach { jobManager.add(it) }
      restoreFullAttachmentJobs.forEach { jobManager.add(it) }
    } while (restoreThumbnailJobs.isNotEmpty() && restoreFullAttachmentJobs.isNotEmpty() && notRestorable.isNotEmpty())

    SignalStore.backup.totalRestorableAttachmentSize = SignalDatabase.attachments.getRemainingRestorableAttachmentSize()

    jobManager.add(CheckRestoreMediaLeftJob(RestoreAttachmentJob.constructQueueString(RestoreAttachmentJob.RestoreOperation.INITIAL_RESTORE)))
  }

  private fun shouldRestoreFullSize(message: MmsMessageRecord, restoreTime: Long, optimizeStorage: Boolean): Boolean {
    return !optimizeStorage || ((restoreTime - message.dateReceived) < 30.days.inWholeMilliseconds)
  }

  override fun onShouldRetry(e: Exception): Boolean = false

  class Factory : Job.Factory<BackupRestoreMediaJob> {
    override fun create(parameters: Parameters, serializedData: ByteArray?): BackupRestoreMediaJob {
      return BackupRestoreMediaJob(parameters)
    }
  }
}
