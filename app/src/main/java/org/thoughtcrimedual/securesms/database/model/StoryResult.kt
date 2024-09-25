package org.thoughtcrimedual.securesms.database.model

import org.thoughtcrimedual.securesms.recipients.RecipientId

class StoryResult(
  val recipientId: RecipientId,
  val messageId: Long,
  val messageSentTimestamp: Long,
  val isOutgoing: Boolean
)
