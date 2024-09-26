package org.thoughtcrimedual.securesms.components.settings.conversation.sounds

import org.thoughtcrimedual.securesms.database.RecipientTable
import org.thoughtcrimedual.securesms.recipients.Recipient
import org.thoughtcrimedual.securesms.recipients.RecipientId

data class SoundsAndNotificationsSettingsState(
  val recipientId: RecipientId = Recipient.UNKNOWN.id,
  val muteUntil: Long = 0L,
  val mentionSetting: RecipientTable.MentionSetting = RecipientTable.MentionSetting.DO_NOT_NOTIFY,
  val hasCustomNotificationSettings: Boolean = false,
  val hasMentionsSupport: Boolean = false,
  val channelConsistencyCheckComplete: Boolean = false
)
