package org.thoughtcrimedual.securesms.mediasend.v2.text.send

import org.thoughtcrimedual.securesms.database.model.IdentityRecord

sealed class TextStoryPostSendResult {
  object Success : TextStoryPostSendResult()
  object Failure : TextStoryPostSendResult()
  data class UntrustedRecordsError(val untrustedRecords: List<IdentityRecord>) : TextStoryPostSendResult()
}
