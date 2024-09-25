package org.thoughtcrimedual.securesms.database.model

import org.thoughtcrimedual.securesms.recipients.RecipientId

/** A model for [org.thoughtcrimedual.securesms.database.PendingRetryReceiptTable] */
data class PendingRetryReceiptModel(
  val id: Long,
  val author: RecipientId,
  val authorDevice: Int,
  val sentTimestamp: Long,
  val receivedTimestamp: Long,
  val threadId: Long
)
