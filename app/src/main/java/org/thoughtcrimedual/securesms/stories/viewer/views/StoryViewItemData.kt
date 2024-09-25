package org.thoughtcrimedual.securesms.stories.viewer.views

import org.thoughtcrimedual.securesms.recipients.Recipient

data class StoryViewItemData(
  val recipient: Recipient,
  val timeViewedInMillis: Long
)
