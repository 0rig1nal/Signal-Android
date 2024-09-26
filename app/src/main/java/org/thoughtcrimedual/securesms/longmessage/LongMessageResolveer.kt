package org.thoughtcrimedual.securesms.longmessage

import android.content.Context
import android.net.Uri
import org.signal.core.util.StreamUtil
import org.signal.core.util.logging.Log
import org.thoughtcrimedual.securesms.conversation.ConversationMessage
import org.thoughtcrimedual.securesms.database.SignalDatabase
import org.thoughtcrimedual.securesms.database.model.MmsMessageRecord
import org.thoughtcrimedual.securesms.mms.PartAuthority
import org.thoughtcrimedual.securesms.recipients.Recipient
import java.io.IOException

const val TAG = "LongMessageResolver"

fun readFullBody(context: Context, uri: Uri): String {
  try {
    PartAuthority.getAttachmentStream(context, uri).use { stream -> return StreamUtil.readFullyAsString(stream) }
  } catch (e: IOException) {
    Log.w(TAG, "Failed to read full text body.", e)
    return ""
  }
}

fun MmsMessageRecord.resolveBody(context: Context): ConversationMessage {
  val threadRecipient: Recipient = requireNotNull(SignalDatabase.threads.getRecipientForThreadId(threadId))
  val textSlide = slideDeck.textSlide
  val textSlideUri = textSlide?.uri
  return if (textSlide != null && textSlideUri != null) {
    ConversationMessage.ConversationMessageFactory.createWithUnresolvedData(context, this, readFullBody(context, textSlideUri), threadRecipient)
  } else {
    ConversationMessage.ConversationMessageFactory.createWithUnresolvedData(context, this, threadRecipient)
  }
}
