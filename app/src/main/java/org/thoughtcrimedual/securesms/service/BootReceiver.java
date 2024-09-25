package org.thoughtcrimedual.securesms.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import org.thoughtcrimedual.securesms.dependencies.AppDependencies;
import org.thoughtcrimedual.securesms.jobs.MessageFetchJob;

public class BootReceiver extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {
    AppDependencies.getJobManager().add(new MessageFetchJob());
  }
}
