/*
 * Copyright 2024 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.thoughtcrimedual.securesms.components.settings.app.chats.backups.history

import androidx.compose.runtime.Stable
import kotlinx.collections.immutable.PersistentMap
import kotlinx.collections.immutable.persistentMapOf
import org.thoughtcrimedual.securesms.database.model.InAppPaymentReceiptRecord

@Stable
data class RemoteBackupsPaymentHistoryState(
  val records: PersistentMap<Long, InAppPaymentReceiptRecord> = persistentMapOf(),
  val displayProgressDialog: Boolean = false
)
