/*
 * Copyright 2023 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.thoughtcrimedual.securesms.backup.v2.database

import org.signal.core.util.deleteAll
import org.thoughtcrimedual.securesms.database.StickerTable

fun StickerTable.clearAllDataForBackupRestore() {
  writableDatabase.deleteAll(StickerTable.TABLE_NAME)
}
