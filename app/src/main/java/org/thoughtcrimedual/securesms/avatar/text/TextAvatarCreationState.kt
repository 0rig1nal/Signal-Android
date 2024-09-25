package org.thoughtcrimedual.securesms.avatar.text

import org.thoughtcrimedual.securesms.avatar.Avatar
import org.thoughtcrimedual.securesms.avatar.AvatarColorItem
import org.thoughtcrimedual.securesms.avatar.Avatars

data class TextAvatarCreationState(
  val currentAvatar: Avatar.Text
) {
  fun colors(): List<AvatarColorItem> = Avatars.colors.map { AvatarColorItem(it, currentAvatar.color == it) }
}
