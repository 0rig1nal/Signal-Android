/*
 * Copyright 2024 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.thoughtcrimedual.securesms.registration.util;

import org.signal.core.util.logging.Log;
import org.thoughtcrimedual.securesms.dependencies.AppDependencies;
import org.thoughtcrimedual.securesms.jobs.DirectoryRefreshJob;
import org.thoughtcrimedual.securesms.jobs.RefreshAttributesJob;
import org.thoughtcrimedual.securesms.jobs.StorageSyncJob;
import org.thoughtcrimedual.securesms.keyvalue.PhoneNumberPrivacyValues.PhoneNumberDiscoverabilityMode;
import org.thoughtcrimedual.securesms.keyvalue.SignalStore;
import org.thoughtcrimedual.securesms.recipients.Recipient;

public final class RegistrationUtil {

  private static final String TAG = Log.tag(RegistrationUtil.class);

  private RegistrationUtil() {}

  /**
   * There's several events where a registration may or may not be considered complete based on what
   * path a user has taken. This will only truly mark registration as complete if all of the
   * requirements are met.
   */
  public static void maybeMarkRegistrationComplete() {
    if (!SignalStore.registration().isRegistrationComplete() &&
        SignalStore.account().isRegistered() &&
        !Recipient.self().getProfileName().isEmpty() &&
        (SignalStore.svr().hasPin() || SignalStore.svr().hasOptedOut()))
    {
      Log.i(TAG, "Marking registration completed.", new Throwable());
      SignalStore.registration().setRegistrationComplete();
      SignalStore.registration().clearLocalRegistrationMetadata();

      if (SignalStore.phoneNumberPrivacy().getPhoneNumberDiscoverabilityMode() == PhoneNumberDiscoverabilityMode.UNDECIDED) {
        Log.w(TAG, "Phone number discoverability mode is still UNDECIDED. Setting to DISCOVERABLE.");
        SignalStore.phoneNumberPrivacy().setPhoneNumberDiscoverabilityMode(PhoneNumberDiscoverabilityMode.DISCOVERABLE);
      }

      AppDependencies.getJobManager().startChain(new RefreshAttributesJob())
                     .then(new StorageSyncJob())
                     .then(new DirectoryRefreshJob(false))
                     .enqueue();

    } else if (!SignalStore.registration().isRegistrationComplete()) {
      Log.i(TAG, "Registration is not yet complete.", new Throwable());
    }
  }
}
