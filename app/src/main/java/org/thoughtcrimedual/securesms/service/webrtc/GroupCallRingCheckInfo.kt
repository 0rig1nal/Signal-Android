package org.thoughtcrimedual.securesms.service.webrtc

import org.signal.ringrtc.CallManager
import org.thoughtcrimedual.securesms.groups.GroupId
import org.thoughtcrimedual.securesms.recipients.RecipientId
import org.whispersystems.signalservice.api.push.ServiceId.ACI

data class GroupCallRingCheckInfo(
  val recipientId: RecipientId,
  val groupId: GroupId.V2,
  val ringId: Long,
  val ringerAci: ACI,
  val ringUpdate: CallManager.RingUpdate
)
