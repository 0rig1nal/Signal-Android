package org.thoughtcrimedual.securesms.components.settings.app.notifications.manual

import org.thoughtcrimedual.securesms.notifications.profiles.NotificationProfile
import java.time.LocalDateTime

data class NotificationProfileSelectionState(
  val notificationProfiles: List<NotificationProfile> = listOf(),
  val expandedId: Long = -1L,
  val timeSlotB: LocalDateTime
)
