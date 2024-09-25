package org.thoughtcrimedual.securesms.badges.gifts.viewgift.sent

import org.thoughtcrimedual.securesms.badges.models.Badge
import org.thoughtcrimedual.securesms.recipients.Recipient

data class ViewSentGiftState(
  val recipient: Recipient? = null,
  val badge: Badge? = null
)
