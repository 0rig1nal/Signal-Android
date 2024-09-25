package org.thoughtcrimedual.securesms.components.settings.app.subscription.receipts.list

import org.thoughtcrimedual.securesms.badges.models.Badge
import org.thoughtcrimedual.securesms.database.model.InAppPaymentReceiptRecord

data class DonationReceiptBadge(
  val type: InAppPaymentReceiptRecord.Type,
  val level: Int,
  val badge: Badge
)
