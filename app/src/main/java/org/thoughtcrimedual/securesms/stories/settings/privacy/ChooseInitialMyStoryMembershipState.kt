package org.thoughtcrimedual.securesms.stories.settings.privacy

import org.thoughtcrimedual.securesms.recipients.RecipientId
import org.thoughtcrimedual.securesms.stories.settings.my.MyStoryPrivacyState

data class ChooseInitialMyStoryMembershipState(
  val recipientId: RecipientId? = null,
  val privacyState: MyStoryPrivacyState = MyStoryPrivacyState(),
  val allSignalConnectionsCount: Int = 0,
  val hasUserPerformedManualSelection: Boolean = false
)
