package org.thoughtcrimedual.securesms.components.settings.app.wrapped

import androidx.fragment.app.Fragment
import org.thoughtcrimedual.securesms.R
import org.thoughtcrimedual.securesms.delete.DeleteAccountFragment

class WrappedDeleteAccountFragment : SettingsWrapperFragment() {
  override fun getFragment(): Fragment {
    toolbar.setTitle(R.string.preferences__delete_account)
    return DeleteAccountFragment()
  }
}
