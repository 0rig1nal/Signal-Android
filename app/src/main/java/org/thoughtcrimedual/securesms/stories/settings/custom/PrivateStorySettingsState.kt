package org.thoughtcrimedual.securesms.stories.settings.custom

import org.thoughtcrimedual.securesms.database.model.DistributionListRecord

data class PrivateStorySettingsState(
  val privateStory: DistributionListRecord? = null,
  val areRepliesAndReactionsEnabled: Boolean = false,
  val isActionInProgress: Boolean = false
)
