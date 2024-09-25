/*
 * Copyright 2024 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.thoughtcrimedual.securesms.backup.v2.ui.subscription

import org.thoughtcrimedual.securesms.backup.v2.MessageBackupTier
import org.thoughtcrimedual.securesms.database.InAppPaymentTable
import org.thoughtcrimedual.securesms.keyvalue.SignalStore
import org.whispersystems.signalservice.api.backup.BackupKey

data class MessageBackupsFlowState(
  val hasBackupSubscriberAvailable: Boolean = false,
  val selectedMessageBackupTierLabel: String? = null,
  val selectedMessageBackupTier: MessageBackupTier? = SignalStore.backup.backupTier,
  val currentMessageBackupTier: MessageBackupTier? = SignalStore.backup.backupTier,
  val availableBackupTypes: List<MessageBackupsType> = emptyList(),
  val inAppPayment: InAppPaymentTable.InAppPayment? = null,
  val startScreen: MessageBackupsStage,
  val stage: MessageBackupsStage = startScreen,
  val backupKey: BackupKey = SignalStore.svr.getOrCreateMasterKey().deriveBackupKey(),
  val failure: Throwable? = null
)
