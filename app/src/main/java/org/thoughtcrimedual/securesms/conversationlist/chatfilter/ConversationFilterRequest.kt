package org.thoughtcrimedual.securesms.conversationlist.chatfilter

import org.thoughtcrimedual.securesms.conversationlist.model.ConversationFilter

data class ConversationFilterRequest(
  val filter: ConversationFilter,
  val source: ConversationFilterSource
)
