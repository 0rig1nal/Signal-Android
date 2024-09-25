package org.thoughtcrimedual.securesms.components.settings.conversation

import org.thoughtcrimedual.securesms.util.DynamicNoActionBarTheme
import org.thoughtcrimedual.securesms.util.DynamicTheme

class CallInfoActivity : ConversationSettingsActivity(), ConversationSettingsFragment.Callback {

  override val dynamicTheme: DynamicTheme = DynamicNoActionBarTheme()
}
