package org.thoughtcrimedual.securesms.payments.backup;

import androidx.annotation.NonNull;

import org.thoughtcrimedual.securesms.keyvalue.SignalStore;
import org.thoughtcrimedual.securesms.payments.Mnemonic;

public final class PaymentsRecoveryRepository {
  public @NonNull Mnemonic getMnemonic() {
    return SignalStore.payments().getPaymentsMnemonic();
  }
}
