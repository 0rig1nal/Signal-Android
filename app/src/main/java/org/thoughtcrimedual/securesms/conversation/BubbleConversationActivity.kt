package org.thoughtcrimedual.securesms.conversation

import org.thoughtcrimedual.securesms.R
import org.thoughtcrimedual.securesms.conversation.v2.ConversationActivity
import org.thoughtcrimedual.securesms.util.ViewUtil

/**
 * Activity which encapsulates a conversation for a Bubble window.
 *
 * This activity exists so that we can override some of its manifest parameters
 * without clashing with [ConversationActivity] and provide an API-level
 * independent "is in bubble?" check.
 */
class BubbleConversationActivity : ConversationActivity() {
  override fun onPause() {
    super.onPause()
    ViewUtil.hideKeyboard(this, findViewById(R.id.fragment_container))
  }
}
