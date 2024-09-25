/*
 * Copyright 2024 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.thoughtcrimedual.securesms.net

import android.app.Application
import android.app.Notification
import android.app.PendingIntent
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import org.signal.core.util.PendingIntentFlags
import org.thoughtcrimedual.securesms.R
import org.thoughtcrimedual.securesms.keyvalue.InternalValues
import org.thoughtcrimedual.securesms.keyvalue.SignalStore
import org.thoughtcrimedual.securesms.logsubmit.SubmitDebugLogActivity
import org.thoughtcrimedual.securesms.notifications.NotificationChannels
import org.thoughtcrimedual.securesms.notifications.NotificationIds
import org.thoughtcrimedual.securesms.util.RemoteConfig
import org.whispersystems.signalservice.internal.websocket.WebSocketShadowingBridge

/**
 * Implements a [WebSocketShadowingBridge] to provide shadowing-specific functionality to
 * [org.whispersystems.signalservice.internal.websocket.ShadowingWebSocketConnection]
 */
class DefaultWebSocketShadowingBridge(private val context: Application) : WebSocketShadowingBridge {
  private val store: InternalValues = SignalStore.internal

  override fun writeStatsSnapshot(bytes: ByteArray) {
    store.setWebSocketShadowingStats(bytes)
  }

  override fun readStatsSnapshot(): ByteArray? {
    return store.getWebSocketShadowingStats(null)
  }

  override fun triggerFailureNotification(message: String) {
    if (!RemoteConfig.internalUser) {
      return
    }
    val notification: Notification = NotificationCompat.Builder(context, NotificationChannels.getInstance().FAILURES)
      .setSmallIcon(R.drawable.ic_notification)
      .setContentTitle("[Internal-only] $message")
      .setContentText("Tap to send a debug log")
      .setContentIntent(
        PendingIntent.getActivity(
          context,
          0,
          Intent(context, SubmitDebugLogActivity::class.java),
          PendingIntentFlags.mutable()
        )
      )
      .build()

    NotificationManagerCompat.from(context).notify(NotificationIds.INTERNAL_ERROR, notification)
  }
}
