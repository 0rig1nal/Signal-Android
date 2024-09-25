package org.thoughtcrimedual.securesms.components.settings.app.subscription.receipts.list

import org.thoughtcrimedual.securesms.database.model.InAppPaymentReceiptRecord

data class DonationReceiptListPageState(
  val records: List<InAppPaymentReceiptRecord> = emptyList(),
  val isLoaded: Boolean = false
)
