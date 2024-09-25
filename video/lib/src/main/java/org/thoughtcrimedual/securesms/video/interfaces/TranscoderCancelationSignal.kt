package org.thoughtcrimedual.securesms.video.interfaces

fun interface TranscoderCancelationSignal {
  fun isCanceled(): Boolean
}
