package org.thoughtcrimedual.securesms.avatar.vector

import org.thoughtcrimedual.securesms.avatar.Avatar
import org.thoughtcrimedual.securesms.avatar.AvatarColorItem
import org.thoughtcrimedual.securesms.avatar.Avatars

data class VectorAvatarCreationState(
  val currentAvatar: Avatar.Vector
) {
  fun colors(): List<AvatarColorItem> = Avatars.colors.map { AvatarColorItem(it, currentAvatar.color == it) }
}
