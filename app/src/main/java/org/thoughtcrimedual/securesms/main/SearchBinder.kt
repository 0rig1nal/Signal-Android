package org.thoughtcrimedual.securesms.main

import android.widget.ImageView
import org.thoughtcrimedual.securesms.components.Material3SearchToolbar
import org.thoughtcrimedual.securesms.util.views.Stub

interface SearchBinder {
  fun getSearchAction(): ImageView

  fun getSearchToolbar(): Stub<Material3SearchToolbar>

  fun onSearchOpened()

  fun onSearchClosed()
}
