/*
 * Copyright 2024 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.thoughtcrimedual.securesms.net

import org.thoughtcrimedual.securesms.dependencies.AppDependencies
import org.whispersystems.signalservice.api.archive.ArchiveApi
import org.whispersystems.signalservice.api.attachment.AttachmentApi
import org.whispersystems.signalservice.api.keys.KeysApi

/**
 * A convenient way to access network operations, similar to [org.thoughtcrimedual.securesms.database.SignalDatabase] and [org.thoughtcrimedual.securesms.keyvalue.SignalStore].
 */
object SignalNetwork {
  val archive: ArchiveApi
    get() = AppDependencies.archiveApi

  val attachments: AttachmentApi
    get() = AppDependencies.attachmentApi

  val keys: KeysApi
    get() = AppDependencies.keysApi
}
