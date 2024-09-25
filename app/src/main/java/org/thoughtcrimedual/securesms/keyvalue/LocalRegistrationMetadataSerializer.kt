/*
 * Copyright 2024 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.thoughtcrimedual.securesms.keyvalue

import org.signal.core.util.ByteSerializer
import org.thoughtcrimedual.securesms.database.model.databaseprotos.LocalRegistrationMetadata

/**
 * Serialize [LocalRegistrationMetadata]
 */
object LocalRegistrationMetadataSerializer : ByteSerializer<LocalRegistrationMetadata> {
  override fun serialize(data: LocalRegistrationMetadata): ByteArray = data.encode()
  override fun deserialize(data: ByteArray): LocalRegistrationMetadata = LocalRegistrationMetadata.ADAPTER.decode(data)
}
