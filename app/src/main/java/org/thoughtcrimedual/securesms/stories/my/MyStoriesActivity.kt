package org.thoughtcrimedual.securesms.stories.my

import androidx.fragment.app.Fragment
import org.thoughtcrimedual.securesms.components.FragmentWrapperActivity

class MyStoriesActivity : FragmentWrapperActivity() {
  override fun getFragment(): Fragment {
    return MyStoriesFragment()
  }
}
