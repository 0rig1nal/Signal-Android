package org.thoughtcrimedual.securesms.badges.gifts.flow

import org.signal.core.util.money.FiatMoney
import org.thoughtcrimedual.securesms.badges.models.Badge
import org.thoughtcrimedual.securesms.database.InAppPaymentTable
import org.thoughtcrimedual.securesms.recipients.Recipient
import java.util.Currency

/**
 * State maintained by the GiftFlowViewModel
 */
data class GiftFlowState(
  val inAppPaymentId: InAppPaymentTable.InAppPaymentId? = null,
  val currency: Currency,
  val giftLevel: Long? = null,
  val giftBadge: Badge? = null,
  val giftPrices: Map<Currency, FiatMoney> = emptyMap(),
  val stage: Stage = Stage.INIT,
  val recipient: Recipient? = null,
  val additionalMessage: CharSequence? = null
) {
  enum class Stage {
    INIT,
    READY,
    RECIPIENT_VERIFICATION,
    TOKEN_REQUEST,
    PAYMENT_PIPELINE,
    FAILURE
  }
}
