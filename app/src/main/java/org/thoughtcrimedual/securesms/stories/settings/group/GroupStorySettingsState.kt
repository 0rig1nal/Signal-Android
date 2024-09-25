package org.thoughtcrimedual.securesms.stories.settings.group

import org.thoughtcrimedual.securesms.recipients.Recipient

data class GroupStorySettingsState(
  val name: String = "",
  val members: List<Recipient> = emptyList(),
  val removed: Boolean = false
)
