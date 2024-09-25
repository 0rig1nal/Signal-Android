package org.thoughtcrimedual.securesms.stories.viewer.reply.direct

import org.thoughtcrimedual.securesms.database.model.MessageRecord
import org.thoughtcrimedual.securesms.recipients.Recipient

data class StoryDirectReplyState(
  val groupDirectReplyRecipient: Recipient? = null,
  val storyRecord: MessageRecord? = null
)
