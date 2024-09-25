package org.thoughtcrimedual.securesms.stories.settings.select

import org.thoughtcrimedual.securesms.database.model.DistributionListId
import org.thoughtcrimedual.securesms.database.model.DistributionListRecord
import org.thoughtcrimedual.securesms.recipients.RecipientId

data class BaseStoryRecipientSelectionState(
  val distributionListId: DistributionListId?,
  val privateStory: DistributionListRecord? = null,
  val selection: Set<RecipientId> = emptySet()
)
