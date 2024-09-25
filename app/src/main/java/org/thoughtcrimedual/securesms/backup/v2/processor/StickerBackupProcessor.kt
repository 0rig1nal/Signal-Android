/*
 * Copyright 2024 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.thoughtcrimedual.securesms.backup.v2.processor

import okio.ByteString.Companion.toByteString
import org.signal.core.util.Hex
import org.signal.core.util.insertInto
import org.thoughtcrimedual.securesms.backup.v2.proto.Frame
import org.thoughtcrimedual.securesms.backup.v2.proto.StickerPack
import org.thoughtcrimedual.securesms.backup.v2.stream.BackupFrameEmitter
import org.thoughtcrimedual.securesms.database.SQLiteDatabase
import org.thoughtcrimedual.securesms.database.SignalDatabase
import org.thoughtcrimedual.securesms.database.StickerTable
import org.thoughtcrimedual.securesms.database.StickerTable.StickerPackRecordReader
import org.thoughtcrimedual.securesms.database.model.StickerPackRecord
import org.thoughtcrimedual.securesms.dependencies.AppDependencies
import org.thoughtcrimedual.securesms.jobs.StickerPackDownloadJob

object StickerBackupProcessor {
  fun export(db: SignalDatabase, emitter: BackupFrameEmitter) {
    StickerPackRecordReader(db.stickerTable.allStickerPacks).use { reader ->
      var record: StickerPackRecord? = reader.next
      while (record != null) {
        if (record.isInstalled) {
          val frame = record.toBackupFrame()
          emitter.emit(frame)
        }
        record = reader.next
      }
    }
  }

  fun import(stickerPack: StickerPack) {
    SignalDatabase.rawDatabase
      .insertInto(StickerTable.TABLE_NAME)
      .values(
        StickerTable.PACK_ID to Hex.toStringCondensed(stickerPack.packId.toByteArray()),
        StickerTable.PACK_KEY to Hex.toStringCondensed(stickerPack.packKey.toByteArray()),
        StickerTable.PACK_TITLE to "",
        StickerTable.PACK_AUTHOR to "",
        StickerTable.INSTALLED to 1,
        StickerTable.COVER to 1,
        StickerTable.EMOJI to "",
        StickerTable.CONTENT_TYPE to "",
        StickerTable.FILE_PATH to ""
      )
      .run(SQLiteDatabase.CONFLICT_IGNORE)

    AppDependencies.jobManager.add(
      StickerPackDownloadJob.forInstall(
        Hex.toStringCondensed(stickerPack.packId.toByteArray()),
        Hex.toStringCondensed(stickerPack.packKey.toByteArray()),
        false
      )
    )
  }
}

private fun StickerPackRecord.toBackupFrame(): Frame {
  val packIdBytes = Hex.fromStringCondensed(packId)
  val packKey = Hex.fromStringCondensed(packKey)
  val pack = StickerPack(
    packId = packIdBytes.toByteString(),
    packKey = packKey.toByteString()
  )
  return Frame(stickerPack = pack)
}
