package org.thoughtcrimedual.securesms.payments.deactivate;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import org.thoughtcrimedual.securesms.keyvalue.SignalStore;
import org.thoughtcrimedual.securesms.payments.Balance;
import org.thoughtcrimedual.securesms.payments.preferences.PaymentsHomeRepository;
import org.thoughtcrimedual.securesms.util.SingleLiveEvent;
import org.whispersystems.signalservice.api.payments.Money;

public class DeactivateWalletViewModel extends ViewModel {

  private final LiveData<Money>         balance;
  private final PaymentsHomeRepository  paymentsHomeRepository   = new PaymentsHomeRepository();
  private final SingleLiveEvent<Result> deactivatePaymentResults = new SingleLiveEvent<>();

  public DeactivateWalletViewModel() {
    balance = Transformations.map(SignalStore.payments().liveMobileCoinBalance(), Balance::getFullAmount);
  }

  void deactivateWallet() {
    paymentsHomeRepository.deactivatePayments(isDisabled -> deactivatePaymentResults.postValue(isDisabled ? Result.SUCCESS : Result.FAILED));
  }

  LiveData<Result> getDeactivationResults() {
    return deactivatePaymentResults;
  }

  LiveData<Money> getBalance() {
    return balance;
  }

  enum Result {
    SUCCESS,
    FAILED
  }
}
