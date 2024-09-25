package org.thoughtcrimedual.securesms.service;


import android.content.Context;

import org.thoughtcrimedual.securesms.jobs.PreKeysSyncJob;
import org.thoughtcrimedual.securesms.keyvalue.SignalStore;
import org.thoughtcrimedual.securesms.util.TextSecurePreferences;

public class RotateSignedPreKeyListener extends PersistentAlarmManagerListener {

  @Override
  protected long getNextScheduledExecutionTime(Context context) {
    return TextSecurePreferences.getSignedPreKeyRotationTime(context);
  }

  @Override
  protected long onAlarm(Context context, long scheduledTime) {
    if (scheduledTime != 0 && SignalStore.account().isRegistered()) {
      PreKeysSyncJob.enqueue();
    }

    long nextTime = System.currentTimeMillis() + PreKeysSyncJob.REFRESH_INTERVAL;
    TextSecurePreferences.setSignedPreKeyRotationTime(context, nextTime);

    return nextTime;
  }

  public static void schedule(Context context) {
    new RotateSignedPreKeyListener().onReceive(context, getScheduleIntent());
  }
}
