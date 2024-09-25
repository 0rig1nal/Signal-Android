package org.thoughtcrimedual.securesms.components.settings.conversation.permissions

import org.thoughtcrimedual.securesms.groups.ui.GroupChangeFailureReason

sealed class PermissionsSettingsEvents {
  class GroupChangeError(val reason: GroupChangeFailureReason) : PermissionsSettingsEvents()
}
