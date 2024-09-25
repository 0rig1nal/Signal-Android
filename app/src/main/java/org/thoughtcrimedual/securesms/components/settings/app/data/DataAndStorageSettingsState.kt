package org.thoughtcrimedual.securesms.components.settings.app.data

import org.thoughtcrimedual.securesms.mms.SentMediaQuality
import org.thoughtcrimedual.securesms.webrtc.CallDataMode

data class DataAndStorageSettingsState(
  val totalStorageUse: Long,
  val mobileAutoDownloadValues: Set<String>,
  val wifiAutoDownloadValues: Set<String>,
  val roamingAutoDownloadValues: Set<String>,
  val callDataMode: CallDataMode,
  val isProxyEnabled: Boolean,
  val sentMediaQuality: SentMediaQuality
)
