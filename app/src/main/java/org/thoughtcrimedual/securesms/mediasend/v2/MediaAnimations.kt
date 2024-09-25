package org.thoughtcrimedual.securesms.mediasend.v2

import android.view.animation.Interpolator
import org.thoughtcrimedual.securesms.util.createDefaultCubicBezierInterpolator

object MediaAnimations {
  /**
   * Fast-In-Extra-Slow-Out Interpolator
   */
  @JvmStatic
  val interpolator: Interpolator = createDefaultCubicBezierInterpolator()
}
