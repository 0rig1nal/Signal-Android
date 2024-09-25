package org.thoughtcrimedual.securesms.components.settings.app.wrapped

import androidx.fragment.app.Fragment
import org.thoughtcrimedual.securesms.R
import org.thoughtcrimedual.securesms.preferences.AdvancedPinPreferenceFragment

class WrappedAdvancedPinPreferenceFragment : SettingsWrapperFragment() {
  override fun getFragment(): Fragment {
    toolbar.setTitle(R.string.preferences__advanced_pin_settings)
    return AdvancedPinPreferenceFragment()
  }
}
