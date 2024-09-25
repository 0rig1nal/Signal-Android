package org.thoughtcrimedual.securesms.components.settings.app.chats

import android.content.Context
import org.signal.core.util.concurrent.SignalExecutors
import org.thoughtcrimedual.securesms.database.SignalDatabase
import org.thoughtcrimedual.securesms.dependencies.AppDependencies
import org.thoughtcrimedual.securesms.jobs.MultiDeviceConfigurationUpdateJob
import org.thoughtcrimedual.securesms.jobs.MultiDeviceContactUpdateJob
import org.thoughtcrimedual.securesms.keyvalue.SignalStore
import org.thoughtcrimedual.securesms.recipients.Recipient
import org.thoughtcrimedual.securesms.storage.StorageSyncHelper
import org.thoughtcrimedual.securesms.util.TextSecurePreferences

class ChatsSettingsRepository {

  private val context: Context = AppDependencies.application

  fun syncLinkPreviewsState() {
    SignalExecutors.BOUNDED.execute {
      val isLinkPreviewsEnabled = SignalStore.settings.isLinkPreviewsEnabled

      SignalDatabase.recipients.markNeedsSync(Recipient.self().id)
      StorageSyncHelper.scheduleSyncForDataChange()
      AppDependencies.jobManager.add(
        MultiDeviceConfigurationUpdateJob(
          TextSecurePreferences.isReadReceiptsEnabled(context),
          TextSecurePreferences.isTypingIndicatorsEnabled(context),
          TextSecurePreferences.isShowUnidentifiedDeliveryIndicatorsEnabled(context),
          isLinkPreviewsEnabled
        )
      )
    }
  }

  fun syncPreferSystemContactPhotos() {
    SignalExecutors.BOUNDED.execute {
      SignalDatabase.recipients.markNeedsSync(Recipient.self().id)
      AppDependencies.jobManager.add(MultiDeviceContactUpdateJob(true))
      StorageSyncHelper.scheduleSyncForDataChange()
    }
  }

  fun syncKeepMutedChatsArchivedState() {
    SignalExecutors.BOUNDED.execute {
      SignalDatabase.recipients.markNeedsSync(Recipient.self().id)
      StorageSyncHelper.scheduleSyncForDataChange()
    }
  }
}
