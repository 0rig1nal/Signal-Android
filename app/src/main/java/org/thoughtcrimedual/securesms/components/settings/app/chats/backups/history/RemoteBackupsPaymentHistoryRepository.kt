/*
 * Copyright 2024 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.thoughtcrimedual.securesms.components.settings.app.chats.backups.history

import org.thoughtcrimedual.securesms.database.SignalDatabase
import org.thoughtcrimedual.securesms.database.model.InAppPaymentReceiptRecord

object RemoteBackupsPaymentHistoryRepository {

  fun getReceipts(): List<InAppPaymentReceiptRecord> {
    return SignalDatabase.donationReceipts.getReceipts(InAppPaymentReceiptRecord.Type.RECURRING_BACKUP)
  }
}
