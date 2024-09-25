package org.thoughtcrimedual.securesms.stories.settings.group

import org.thoughtcrimedual.securesms.recipients.RecipientId

/**
 * Minimum data needed to launch ConversationActivity for a given grou
 */
data class GroupConversationData(
  val groupRecipientId: RecipientId,
  val groupThreadId: Long
)
