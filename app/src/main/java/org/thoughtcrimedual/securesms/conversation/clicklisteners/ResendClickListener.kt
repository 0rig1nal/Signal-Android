/*
 * Copyright 2024 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.thoughtcrimedual.securesms.conversation.clicklisteners

import android.view.View
import org.signal.core.util.logging.Log
import org.thoughtcrimedual.securesms.database.model.MessageRecord
import org.thoughtcrimedual.securesms.mms.Slide
import org.thoughtcrimedual.securesms.mms.SlidesClickedListener
import org.thoughtcrimedual.securesms.sms.MessageSender

class ResendClickListener(private val messageRecord: MessageRecord) : SlidesClickedListener {
  override fun onClick(v: View?, slides: MutableList<Slide>?) {
    if (v == null) {
      Log.w(TAG, "Could not resend message, view was null!")
      return
    }

    MessageSender.resend(v.context, messageRecord)
  }

  companion object {
    private val TAG = Log.tag(ResendClickListener::class.java)
  }
}
