/*
 * Copyright 2023 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.thoughtcrimedual.securesms.backup.v2.database

import org.signal.core.util.deleteAll
import org.thoughtcrimedual.securesms.attachments.Attachment
import org.thoughtcrimedual.securesms.attachments.AttachmentId
import org.thoughtcrimedual.securesms.database.AttachmentTable

fun AttachmentTable.clearAllDataForBackupRestore() {
  writableDatabase.deleteAll(AttachmentTable.TABLE_NAME)
}

fun AttachmentTable.restoreWallpaperAttachment(attachment: Attachment): AttachmentId? {
  return insertAttachmentsForMessage(AttachmentTable.WALLPAPER_MESSAGE_ID, listOf(attachment), emptyList()).values.firstOrNull()
}
