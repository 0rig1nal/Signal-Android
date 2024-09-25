package org.thoughtcrimedual.securesms.components.settings.app.internal.donor

import org.signal.donations.StripeDeclineCode
import org.thoughtcrimedual.securesms.badges.models.Badge
import org.thoughtcrimedual.securesms.components.settings.app.subscription.errors.UnexpectedSubscriptionCancellation

data class InternalDonorErrorConfigurationState(
  val badges: List<Badge> = emptyList(),
  val selectedBadge: Badge? = null,
  val selectedUnexpectedSubscriptionCancellation: UnexpectedSubscriptionCancellation? = null,
  val selectedStripeDeclineCode: StripeDeclineCode.Code? = null
)
