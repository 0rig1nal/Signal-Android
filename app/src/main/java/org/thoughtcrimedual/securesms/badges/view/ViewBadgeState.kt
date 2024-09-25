package org.thoughtcrimedual.securesms.badges.view

import org.thoughtcrimedual.securesms.badges.models.Badge
import org.thoughtcrimedual.securesms.recipients.Recipient

data class ViewBadgeState(
  val allBadgesVisibleOnProfile: List<Badge> = listOf(),
  val badgeLoadState: LoadState = LoadState.INIT,
  val selectedBadge: Badge? = null,
  val recipient: Recipient? = null
) {
  enum class LoadState {
    INIT,
    LOADED
  }
}
