/*
 * Copyright 2024 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */
package org.thoughtcrimedual.securesms.conversation.clicklisteners

import android.view.View
import org.signal.core.util.logging.Log
import org.thoughtcrimedual.securesms.attachments.DatabaseAttachment
import org.thoughtcrimedual.securesms.dependencies.AppDependencies
import org.thoughtcrimedual.securesms.jobs.AttachmentCompressionJob
import org.thoughtcrimedual.securesms.jobs.AttachmentDownloadJob
import org.thoughtcrimedual.securesms.jobs.AttachmentUploadJob
import org.thoughtcrimedual.securesms.mms.Slide
import org.thoughtcrimedual.securesms.mms.SlidesClickedListener

internal class AttachmentCancelClickListener : SlidesClickedListener {
  override fun onClick(v: View, slides: List<Slide>) {
    Log.i(TAG, "Canceling compression/upload/download jobs for ${slides.size} items")
    val jobManager = AppDependencies.jobManager
    var cancelCount = 0
    for (slide in slides) {
      val attachmentId = (slide.asAttachment() as DatabaseAttachment).attachmentId
      val jobsToCancel = jobManager.find {
        when (it.factoryKey) {
          AttachmentDownloadJob.KEY -> AttachmentDownloadJob.jobSpecMatchesAttachmentId(it, attachmentId)
          AttachmentCompressionJob.KEY -> AttachmentCompressionJob.jobSpecMatchesAttachmentId(it, attachmentId)
          AttachmentUploadJob.KEY -> AttachmentUploadJob.jobSpecMatchesAttachmentId(it, attachmentId)
          else -> false
        }
      }
      jobsToCancel.forEach {
        jobManager.cancel(it.id)
        cancelCount++
      }
    }
    Log.i(TAG, "Canceled $cancelCount jobs.")
  }

  companion object {
    private val TAG = Log.tag(AttachmentCancelClickListener::class.java)
  }
}
