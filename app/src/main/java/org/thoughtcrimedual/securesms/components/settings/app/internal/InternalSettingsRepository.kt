package org.thoughtcrimedual.securesms.components.settings.app.internal

import android.content.Context
import org.json.JSONObject
import org.signal.core.util.concurrent.SignalExecutors
import org.signal.donations.InAppPaymentType
import org.thoughtcrimedual.securesms.database.MessageTable
import org.thoughtcrimedual.securesms.database.SignalDatabase
import org.thoughtcrimedual.securesms.database.model.RemoteMegaphoneRecord
import org.thoughtcrimedual.securesms.database.model.addStyle
import org.thoughtcrimedual.securesms.database.model.databaseprotos.BodyRangeList
import org.thoughtcrimedual.securesms.dependencies.AppDependencies
import org.thoughtcrimedual.securesms.emoji.EmojiFiles
import org.thoughtcrimedual.securesms.jobs.AttachmentDownloadJob
import org.thoughtcrimedual.securesms.jobs.CreateReleaseChannelJob
import org.thoughtcrimedual.securesms.jobs.FetchRemoteMegaphoneImageJob
import org.thoughtcrimedual.securesms.jobs.InAppPaymentRecurringContextJob
import org.thoughtcrimedual.securesms.keyvalue.SignalStore
import org.thoughtcrimedual.securesms.notifications.v2.ConversationId
import org.thoughtcrimedual.securesms.recipients.Recipient
import org.thoughtcrimedual.securesms.releasechannel.ReleaseChannel
import java.util.UUID
import kotlin.time.Duration.Companion.days

class InternalSettingsRepository(context: Context) {

  private val context = context.applicationContext

  fun getEmojiVersionInfo(consumer: (EmojiFiles.Version?) -> Unit) {
    SignalExecutors.BOUNDED.execute {
      consumer(EmojiFiles.Version.readVersion(context))
    }
  }

  fun enqueueSubscriptionRedemption() {
    SignalExecutors.BOUNDED.execute {
      val latest = SignalDatabase.inAppPayments.getByLatestEndOfPeriod(InAppPaymentType.RECURRING_DONATION)
      if (latest != null) {
        InAppPaymentRecurringContextJob.createJobChain(latest).enqueue()
      }
    }
  }

  fun addSampleReleaseNote() {
    SignalExecutors.UNBOUNDED.execute {
      AppDependencies.jobManager.runSynchronously(CreateReleaseChannelJob.create(), 5000)

      val title = "Release Note Title"
      val bodyText = "Release note body. Aren't I awesome?"
      val body = "$title\n\n$bodyText"
      val bodyRangeList = BodyRangeList.Builder()
        .addStyle(BodyRangeList.BodyRange.Style.BOLD, 0, title.length)

      val recipientId = SignalStore.releaseChannel.releaseChannelRecipientId!!
      val threadId = SignalDatabase.threads.getOrCreateThreadIdFor(Recipient.resolved(recipientId))

      val insertResult: MessageTable.InsertResult? = ReleaseChannel.insertReleaseChannelMessage(
        recipientId = recipientId,
        body = body,
        threadId = threadId,
        messageRanges = bodyRangeList.build(),
        media = "/static/release-notes/signal.png",
        mediaWidth = 1800,
        mediaHeight = 720
      )

      SignalDatabase.messages.insertBoostRequestMessage(recipientId, threadId)

      if (insertResult != null) {
        SignalDatabase.attachments.getAttachmentsForMessage(insertResult.messageId)
          .forEach { AppDependencies.jobManager.add(AttachmentDownloadJob(insertResult.messageId, it.attachmentId, false)) }

        AppDependencies.messageNotifier.updateNotification(context, ConversationId.forConversation(insertResult.threadId))
      }
    }
  }

  fun addRemoteMegaphone(actionId: RemoteMegaphoneRecord.ActionId) {
    SignalExecutors.UNBOUNDED.execute {
      val record = RemoteMegaphoneRecord(
        uuid = UUID.randomUUID().toString(),
        priority = 100,
        countries = "*:1000000",
        minimumVersion = 1,
        doNotShowBefore = System.currentTimeMillis() - 2.days.inWholeMilliseconds,
        doNotShowAfter = System.currentTimeMillis() + 28.days.inWholeMilliseconds,
        showForNumberOfDays = 30,
        conditionalId = null,
        primaryActionId = actionId,
        secondaryActionId = RemoteMegaphoneRecord.ActionId.SNOOZE,
        imageUrl = "/static/release-notes/donate-heart.png",
        title = "Donate Test",
        body = "Donate body test.",
        primaryActionText = "Donate",
        secondaryActionText = "Snooze",
        primaryActionData = null,
        secondaryActionData = JSONObject("{ \"snoozeDurationDays\": [5, 7, 100] }")
      )

      SignalDatabase.remoteMegaphones.insert(record)

      if (record.imageUrl != null) {
        AppDependencies.jobManager.add(FetchRemoteMegaphoneImageJob(record.uuid, record.imageUrl))
      }
    }
  }
}
