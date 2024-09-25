package org.thoughtcrimedual.securesms.service.webrtc.state

import org.thoughtcrimedual.securesms.events.CallParticipant
import org.thoughtcrimedual.securesms.events.CallParticipantId
import org.thoughtcrimedual.securesms.events.GroupCallReactionEvent

/**
 * The state of the call system which contains data which changes frequently.
 */
data class WebRtcEphemeralState(
  val localAudioLevel: CallParticipant.AudioLevel = CallParticipant.AudioLevel.LOWEST,
  val remoteAudioLevels: Map<CallParticipantId, CallParticipant.AudioLevel> = emptyMap(),
  private val reactions: List<GroupCallReactionEvent> = emptyList()
) {

  fun getUnexpiredReactions(): List<GroupCallReactionEvent> {
    return reactions.filter { System.currentTimeMillis() < it.getExpirationTimestamp() }
  }
}
