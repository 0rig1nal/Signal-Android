package org.thoughtcrimedual.securesms.components.settings.app.data

import android.content.Context
import org.signal.core.util.concurrent.SignalExecutors
import org.thoughtcrimedual.securesms.database.SignalDatabase
import org.thoughtcrimedual.securesms.dependencies.AppDependencies

class DataAndStorageSettingsRepository {

  private val context: Context = AppDependencies.application

  fun getTotalStorageUse(consumer: (Long) -> Unit) {
    SignalExecutors.BOUNDED.execute {
      val breakdown = SignalDatabase.media.getStorageBreakdown()

      consumer(listOf(breakdown.audioSize, breakdown.documentSize, breakdown.photoSize, breakdown.videoSize).sum())
    }
  }
}
