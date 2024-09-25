package org.thoughtcrimedual.securesms.safety

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.thoughtcrimedual.securesms.contacts.paged.ContactSearchKey
import org.thoughtcrimedual.securesms.database.model.MessageId
import org.thoughtcrimedual.securesms.recipients.RecipientId

/**
 * Fragment argument for `SafetyNumberBottomSheetFragment`
 */
@Parcelize
data class SafetyNumberBottomSheetArgs(
  val untrustedRecipients: List<RecipientId>,
  val destinations: List<ContactSearchKey.RecipientSearchKey>,
  val messageId: MessageId? = null
) : Parcelable
