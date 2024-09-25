package org.thoughtcrimedual.securesms.components.settings.app.wrapped

import androidx.fragment.app.Fragment
import org.thoughtcrimedual.securesms.R
import org.thoughtcrimedual.securesms.preferences.EditProxyFragment

class WrappedEditProxyFragment : SettingsWrapperFragment() {
  override fun getFragment(): Fragment {
    toolbar.setTitle(R.string.preferences_use_proxy)
    return EditProxyFragment()
  }
}
