package org.thoughtcrimedual.securesms.dependencies

import io.mockk.mockk
import org.mockito.Mockito
import org.signal.core.util.billing.BillingApi
import org.signal.core.util.concurrent.DeadlockDetector
import org.signal.libsignal.net.Network
import org.signal.libsignal.zkgroup.profiles.ClientZkProfileOperations
import org.signal.libsignal.zkgroup.receipts.ClientZkReceiptOperations
import org.thoughtcrimedual.securesms.components.TypingStatusRepository
import org.thoughtcrimedual.securesms.components.TypingStatusSender
import org.thoughtcrimedual.securesms.crypto.storage.SignalServiceDataStoreImpl
import org.thoughtcrimedual.securesms.database.DatabaseObserver
import org.thoughtcrimedual.securesms.database.PendingRetryReceiptCache
import org.thoughtcrimedual.securesms.jobmanager.JobManager
import org.thoughtcrimedual.securesms.megaphone.MegaphoneRepository
import org.thoughtcrimedual.securesms.messages.IncomingMessageObserver
import org.thoughtcrimedual.securesms.notifications.MessageNotifier
import org.thoughtcrimedual.securesms.payments.Payments
import org.thoughtcrimedual.securesms.push.SignalServiceNetworkAccess
import org.thoughtcrimedual.securesms.recipients.LiveRecipientCache
import org.thoughtcrimedual.securesms.revealable.ViewOnceMessageManager
import org.thoughtcrimedual.securesms.service.DeletedCallEventManager
import org.thoughtcrimedual.securesms.service.ExpiringMessageManager
import org.thoughtcrimedual.securesms.service.ExpiringStoriesManager
import org.thoughtcrimedual.securesms.service.PendingRetryReceiptManager
import org.thoughtcrimedual.securesms.service.ScheduledMessageManager
import org.thoughtcrimedual.securesms.service.TrimThreadsByDateManager
import org.thoughtcrimedual.securesms.service.webrtc.SignalCallManager
import org.thoughtcrimedual.securesms.shakereport.ShakeToReport
import org.thoughtcrimedual.securesms.util.EarlyMessageCache
import org.thoughtcrimedual.securesms.util.FrameRateTracker
import org.thoughtcrimedual.securesms.video.exo.GiphyMp4Cache
import org.thoughtcrimedual.securesms.video.exo.SimpleExoPlayerPool
import org.thoughtcrimedual.securesms.webrtc.audio.AudioManagerCompat
import org.whispersystems.signalservice.api.SignalServiceAccountManager
import org.whispersystems.signalservice.api.SignalServiceDataStore
import org.whispersystems.signalservice.api.SignalServiceMessageReceiver
import org.whispersystems.signalservice.api.SignalServiceMessageSender
import org.whispersystems.signalservice.api.SignalWebSocket
import org.whispersystems.signalservice.api.archive.ArchiveApi
import org.whispersystems.signalservice.api.attachment.AttachmentApi
import org.whispersystems.signalservice.api.groupsv2.GroupsV2Operations
import org.whispersystems.signalservice.api.keys.KeysApi
import org.whispersystems.signalservice.api.services.CallLinksService
import org.whispersystems.signalservice.api.services.DonationsService
import org.whispersystems.signalservice.api.services.ProfileService
import org.whispersystems.signalservice.internal.configuration.SignalServiceConfiguration
import org.whispersystems.signalservice.internal.push.PushServiceSocket
import java.util.function.Supplier

class MockApplicationDependencyProvider : AppDependencies.Provider {
  override fun providePushServiceSocket(signalServiceConfiguration: SignalServiceConfiguration, groupsV2Operations: GroupsV2Operations): PushServiceSocket {
    return mockk()
  }

  override fun provideGroupsV2Operations(signalServiceConfiguration: SignalServiceConfiguration): GroupsV2Operations {
    return mockk()
  }

  override fun provideSignalServiceAccountManager(pushServiceSocket: PushServiceSocket, groupsV2Operations: GroupsV2Operations): SignalServiceAccountManager {
    return mockk()
  }

  override fun provideSignalServiceMessageSender(signalWebSocket: SignalWebSocket, protocolStore: SignalServiceDataStore, pushServiceSocket: PushServiceSocket): SignalServiceMessageSender {
    return mockk()
  }

  override fun provideSignalServiceMessageReceiver(pushServiceSocket: PushServiceSocket): SignalServiceMessageReceiver {
    return mockk()
  }

  override fun provideSignalServiceNetworkAccess(): SignalServiceNetworkAccess {
    return mockk()
  }

  override fun provideRecipientCache(): LiveRecipientCache {
    return mockk()
  }

  override fun provideJobManager(): JobManager {
    return mockk()
  }

  override fun provideFrameRateTracker(): FrameRateTracker {
    return mockk()
  }

  override fun provideMegaphoneRepository(): MegaphoneRepository {
    return mockk()
  }

  override fun provideEarlyMessageCache(): EarlyMessageCache {
    return mockk()
  }

  override fun provideMessageNotifier(): MessageNotifier {
    return mockk()
  }

  override fun provideIncomingMessageObserver(): IncomingMessageObserver {
    return mockk()
  }

  override fun provideTrimThreadsByDateManager(): TrimThreadsByDateManager {
    return mockk()
  }

  override fun provideViewOnceMessageManager(): ViewOnceMessageManager {
    return mockk()
  }

  override fun provideExpiringStoriesManager(): ExpiringStoriesManager {
    return mockk()
  }

  override fun provideExpiringMessageManager(): ExpiringMessageManager {
    return mockk()
  }

  override fun provideDeletedCallEventManager(): DeletedCallEventManager {
    return mockk()
  }

  override fun provideTypingStatusRepository(): TypingStatusRepository {
    return mockk()
  }

  override fun provideTypingStatusSender(): TypingStatusSender {
    return mockk()
  }

  override fun provideDatabaseObserver(): DatabaseObserver {
    return Mockito.mock(DatabaseObserver::class.java)
  }

  override fun providePayments(signalServiceAccountManager: SignalServiceAccountManager): Payments {
    return mockk()
  }

  override fun provideShakeToReport(): ShakeToReport {
    return mockk()
  }

  override fun provideSignalCallManager(): SignalCallManager {
    return mockk()
  }

  override fun providePendingRetryReceiptManager(): PendingRetryReceiptManager {
    return mockk()
  }

  override fun providePendingRetryReceiptCache(): PendingRetryReceiptCache {
    return mockk()
  }

  override fun provideSignalWebSocket(signalServiceConfigurationSupplier: Supplier<SignalServiceConfiguration>, libSignalNetworkSupplier: Supplier<Network>): SignalWebSocket {
    return mockk()
  }

  override fun provideProtocolStore(): SignalServiceDataStoreImpl {
    return mockk()
  }

  override fun provideGiphyMp4Cache(): GiphyMp4Cache {
    return mockk()
  }

  override fun provideExoPlayerPool(): SimpleExoPlayerPool {
    return mockk()
  }

  override fun provideAndroidCallAudioManager(): AudioManagerCompat {
    return mockk()
  }

  override fun provideDonationsService(pushServiceSocket: PushServiceSocket): DonationsService {
    return mockk()
  }

  override fun provideCallLinksService(pushServiceSocket: PushServiceSocket): CallLinksService {
    return mockk()
  }

  override fun provideProfileService(profileOperations: ClientZkProfileOperations, signalServiceMessageReceiver: SignalServiceMessageReceiver, signalWebSocket: SignalWebSocket): ProfileService {
    return mockk()
  }

  override fun provideDeadlockDetector(): DeadlockDetector {
    return mockk()
  }

  override fun provideClientZkReceiptOperations(signalServiceConfiguration: SignalServiceConfiguration): ClientZkReceiptOperations {
    return mockk()
  }

  override fun provideScheduledMessageManager(): ScheduledMessageManager {
    return mockk()
  }

  override fun provideLibsignalNetwork(config: SignalServiceConfiguration): Network {
    return mockk()
  }

  override fun provideBillingApi(): BillingApi {
    return mockk()
  }

  override fun provideArchiveApi(pushServiceSocket: PushServiceSocket): ArchiveApi {
    return mockk()
  }

  override fun provideKeysApi(pushServiceSocket: PushServiceSocket): KeysApi {
    return mockk()
  }

  override fun provideAttachmentApi(signalWebSocket: SignalWebSocket, pushServiceSocket: PushServiceSocket): AttachmentApi {
    return mockk()
  }
}
