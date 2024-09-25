package org.thoughtcrimedual.securesms.components.settings.app.subscription.receipts.detail

import org.thoughtcrimedual.securesms.database.model.InAppPaymentReceiptRecord

data class DonationReceiptDetailState(
  val inAppPaymentReceiptRecord: InAppPaymentReceiptRecord? = null,
  val subscriptionName: String? = null
)
