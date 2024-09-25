package org.thoughtcrimedual.securesms.components.settings.app.wrapped

import androidx.fragment.app.Fragment
import org.thoughtcrimedual.securesms.R
import org.thoughtcrimedual.securesms.preferences.BackupsPreferenceFragment

class WrappedBackupsPreferenceFragment : SettingsWrapperFragment() {
  override fun getFragment(): Fragment {
    toolbar.setTitle(R.string.BackupsPreferenceFragment__chat_backups)
    return BackupsPreferenceFragment()
  }
}
