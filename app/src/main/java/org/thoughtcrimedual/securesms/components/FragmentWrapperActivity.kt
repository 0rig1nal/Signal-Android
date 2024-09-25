package org.thoughtcrimedual.securesms.components

import android.os.Bundle
import androidx.fragment.app.Fragment
import org.thoughtcrimedual.securesms.PassphraseRequiredActivity
import org.thoughtcrimedual.securesms.R
import org.thoughtcrimedual.securesms.util.DynamicNoActionBarTheme
import org.thoughtcrimedual.securesms.util.DynamicTheme

/**
 * Activity that wraps a given fragment
 */
abstract class FragmentWrapperActivity : PassphraseRequiredActivity() {

  protected open val dynamicTheme: DynamicTheme = DynamicNoActionBarTheme()
  protected open val contentViewId: Int = R.layout.fragment_container

  override fun onPreCreate() {
    dynamicTheme.onCreate(this)
  }

  override fun onCreate(savedInstanceState: Bundle?, ready: Boolean) {
    setContentView(contentViewId)

    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction()
        .replace(R.id.fragment_container, getFragment())
        .commit()
    }
  }

  abstract fun getFragment(): Fragment

  override fun onResume() {
    super.onResume()
    dynamicTheme.onResume(this)
  }
}
