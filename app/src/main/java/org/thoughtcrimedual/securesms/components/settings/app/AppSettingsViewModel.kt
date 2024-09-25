package org.thoughtcrimedual.securesms.components.settings.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import org.thoughtcrimedual.securesms.components.settings.app.subscription.InAppDonations
import org.thoughtcrimedual.securesms.components.settings.app.subscription.RecurringInAppPaymentRepository
import org.thoughtcrimedual.securesms.conversationlist.model.UnreadPaymentsLiveData
import org.thoughtcrimedual.securesms.database.model.InAppPaymentSubscriberRecord
import org.thoughtcrimedual.securesms.dependencies.AppDependencies
import org.thoughtcrimedual.securesms.keyvalue.SignalStore
import org.thoughtcrimedual.securesms.recipients.Recipient
import org.thoughtcrimedual.securesms.util.TextSecurePreferences
import org.thoughtcrimedual.securesms.util.livedata.Store

class AppSettingsViewModel : ViewModel() {

  private val store = Store(
    AppSettingsState(
      Recipient.self(),
      0,
      SignalStore.inAppPayments.getExpiredGiftBadge() != null,
      SignalStore.inAppPayments.isLikelyASustainer() || InAppDonations.hasAtLeastOnePaymentMethodAvailable(),
      TextSecurePreferences.isUnauthorizedReceived(AppDependencies.application) || !SignalStore.account.isRegistered,
      SignalStore.misc.isClientDeprecated
    )
  )

  private val unreadPaymentsLiveData = UnreadPaymentsLiveData()
  private val selfLiveData: LiveData<Recipient> = Recipient.self().live().liveData
  private val disposables = CompositeDisposable()

  val state: LiveData<AppSettingsState> = store.stateLiveData

  init {
    store.update(unreadPaymentsLiveData) { payments, state -> state.copy(unreadPaymentsCount = payments.map { it.unreadCount }.orElse(0)) }
    store.update(selfLiveData) { self, state -> state.copy(self = self) }

    disposables += RecurringInAppPaymentRepository.getActiveSubscription(InAppPaymentSubscriberRecord.Type.DONATION).subscribeBy(
      onSuccess = { activeSubscription ->
        store.update { state ->
          state.copy(allowUserToGoToDonationManagementScreen = activeSubscription.isActive || InAppDonations.hasAtLeastOnePaymentMethodAvailable())
        }
      },
      onError = {}
    )
  }

  override fun onCleared() {
    disposables.clear()
  }

  fun refreshDeprecatedOrUnregistered() {
    store.update {
      it.copy(
        clientDeprecated = SignalStore.misc.isClientDeprecated,
        userUnregistered = TextSecurePreferences.isUnauthorizedReceived(AppDependencies.application) || !SignalStore.account.isRegistered
      )
    }
  }

  fun refreshExpiredGiftBadge() {
    store.update { it.copy(hasExpiredGiftBadge = SignalStore.inAppPayments.getExpiredGiftBadge() != null) }
  }
}
