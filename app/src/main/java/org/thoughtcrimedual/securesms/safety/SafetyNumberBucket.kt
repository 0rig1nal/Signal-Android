package org.thoughtcrimedual.securesms.safety

import org.thoughtcrimedual.securesms.database.model.DistributionListId
import org.thoughtcrimedual.securesms.recipients.Recipient

sealed class SafetyNumberBucket {
  data class DistributionListBucket(val distributionListId: DistributionListId, val name: String) : SafetyNumberBucket()
  data class GroupBucket(val recipient: Recipient) : SafetyNumberBucket()
  object ContactsBucket : SafetyNumberBucket()
}
