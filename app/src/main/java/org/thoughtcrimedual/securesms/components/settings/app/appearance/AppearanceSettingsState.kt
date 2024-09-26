package org.thoughtcrimedual.securesms.components.settings.app.appearance

import org.thoughtcrimedual.securesms.keyvalue.SettingsValues

data class AppearanceSettingsState(
  val theme: SettingsValues.Theme,
  val messageFontSize: Int,
  val language: String,
  val isCompactNavigationBar: Boolean
)
