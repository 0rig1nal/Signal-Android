/*
 * Copyright 2024 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.thoughtcrimedual.securesms.backup.v2

import org.signal.core.util.concurrent.SignalExecutors
import org.thoughtcrimedual.securesms.attachments.AttachmentId
import org.thoughtcrimedual.securesms.attachments.DatabaseAttachment
import org.thoughtcrimedual.securesms.database.AttachmentTable
import org.thoughtcrimedual.securesms.database.model.MessageRecord
import org.thoughtcrimedual.securesms.database.model.MmsMessageRecord
import org.thoughtcrimedual.securesms.dependencies.AppDependencies
import org.thoughtcrimedual.securesms.jobs.RestoreAttachmentThumbnailJob

/**
 * Responsible for managing logic around restore prioritization
 */
object BackupRestoreManager {

  private val reprioritizedAttachments: HashSet<AttachmentId> = HashSet()

  /**
   * Raise priority of all attachments for the included message records.
   *
   * This is so we can make certain attachments get downloaded more quickly
   */
  fun prioritizeAttachmentsIfNeeded(messageRecords: List<MessageRecord>) {
    SignalExecutors.BOUNDED.execute {
      synchronized(this) {
        val restoringAttachments = messageRecords
          .asSequence()
          .mapNotNull { (it as? MmsMessageRecord?)?.slideDeck?.slides }
          .flatten()
          .mapNotNull { it.asAttachment() as? DatabaseAttachment }
          .filter {
            val needThumbnail = it.thumbnailRestoreState == AttachmentTable.ThumbnailRestoreState.NEEDS_RESTORE && it.transferState == AttachmentTable.TRANSFER_RESTORE_IN_PROGRESS
            (needThumbnail || it.thumbnailRestoreState == AttachmentTable.ThumbnailRestoreState.IN_PROGRESS) && !reprioritizedAttachments.contains(it.attachmentId)
          }
          .map { it.attachmentId to it.mmsId }
          .toSet()

        reprioritizedAttachments += restoringAttachments.map { it.first }

        val thumbnailJobs = restoringAttachments.map { (attachmentId, mmsId) ->
          RestoreAttachmentThumbnailJob(attachmentId = attachmentId, messageId = mmsId, highPriority = true)
        }

        if (thumbnailJobs.isNotEmpty()) {
          AppDependencies.jobManager.addAll(thumbnailJobs)
        }
      }
    }
  }
}
