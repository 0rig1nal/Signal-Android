package org.thoughtcrimedual.securesms.stories.settings.create

import androidx.navigation.fragment.findNavController
import org.thoughtcrimedual.securesms.R
import org.thoughtcrimedual.securesms.database.model.DistributionListId
import org.thoughtcrimedual.securesms.recipients.RecipientId
import org.thoughtcrimedual.securesms.stories.settings.select.BaseStoryRecipientSelectionFragment
import org.thoughtcrimedual.securesms.util.navigation.safeNavigate

/**
 * Allows user to select who will see the story they are creating
 */
class CreateStoryViewerSelectionFragment : BaseStoryRecipientSelectionFragment() {
  override val actionButtonLabel: Int = R.string.CreateStoryViewerSelectionFragment__next
  override val distributionListId: DistributionListId? = null

  override fun goToNextScreen(recipients: Set<RecipientId>) {
    findNavController().safeNavigate(CreateStoryViewerSelectionFragmentDirections.actionCreateStoryViewerSelectionToCreateStoryWithViewers(recipients.toTypedArray()))
  }
}
