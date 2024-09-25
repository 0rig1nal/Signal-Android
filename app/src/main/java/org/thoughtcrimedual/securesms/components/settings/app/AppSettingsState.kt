package org.thoughtcrimedual.securesms.components.settings.app

import org.thoughtcrimedual.securesms.recipients.Recipient

data class AppSettingsState(
  val self: Recipient,
  val unreadPaymentsCount: Int,
  val hasExpiredGiftBadge: Boolean,
  val allowUserToGoToDonationManagementScreen: Boolean,
  val userUnregistered: Boolean,
  val clientDeprecated: Boolean
) {
  fun isRegisteredAndUpToDate(): Boolean {
    return !userUnregistered && !clientDeprecated
  }
}
