/*
 * Copyright 2023 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.thoughtcrimedual.securesms.apkupdate;


import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrimedual.securesms.BuildConfig;
import org.thoughtcrimedual.securesms.dependencies.AppDependencies;
import org.thoughtcrimedual.securesms.jobs.ApkUpdateJob;
import org.thoughtcrimedual.securesms.service.PersistentAlarmManagerListener;
import org.thoughtcrimedual.securesms.util.Environment;
import org.thoughtcrimedual.securesms.util.TextSecurePreferences;

import java.util.concurrent.TimeUnit;

public class ApkUpdateRefreshListener extends PersistentAlarmManagerListener {

  private static final String TAG = Log.tag(ApkUpdateRefreshListener.class);

  private static final long INTERVAL = Environment.IS_NIGHTLY ? TimeUnit.HOURS.toMillis(2) : TimeUnit.HOURS.toMillis(6);

  @Override
  protected long getNextScheduledExecutionTime(Context context) {
    return TextSecurePreferences.getUpdateApkRefreshTime(context);
  }

  @Override
  protected long onAlarm(Context context, long scheduledTime) {
    Log.i(TAG, "onAlarm...");

    if (scheduledTime != 0 && BuildConfig.MANAGES_APP_UPDATES) {
      Log.i(TAG, "Queueing APK update job...");
      AppDependencies.getJobManager().add(new ApkUpdateJob());
    }

    long newTime = System.currentTimeMillis() + INTERVAL;
    TextSecurePreferences.setUpdateApkRefreshTime(context, newTime);

    return newTime;
  }

  public static void schedule(Context context) {
    new ApkUpdateRefreshListener().onReceive(context, getScheduleIntent());
  }

}
