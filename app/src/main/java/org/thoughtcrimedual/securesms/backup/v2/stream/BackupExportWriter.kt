/*
 * Copyright 2023 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.thoughtcrimedual.securesms.backup.v2.stream

import org.thoughtcrimedual.securesms.backup.v2.proto.BackupInfo
import org.thoughtcrimedual.securesms.backup.v2.proto.Frame

interface BackupExportWriter : AutoCloseable {
  fun write(header: BackupInfo)
  fun write(frame: Frame)
}
