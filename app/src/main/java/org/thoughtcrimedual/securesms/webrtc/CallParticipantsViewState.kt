package org.thoughtcrimedual.securesms.webrtc

import org.thoughtcrimedual.securesms.components.webrtc.CallParticipantsState
import org.thoughtcrimedual.securesms.service.webrtc.state.WebRtcEphemeralState

class CallParticipantsViewState(
  callParticipantsState: CallParticipantsState,
  ephemeralState: WebRtcEphemeralState,
  val isPortrait: Boolean,
  val isLandscapeEnabled: Boolean,
  val isStartedFromCallLink: Boolean
) {

  val callParticipantsState = CallParticipantsState.update(callParticipantsState, ephemeralState)
}
