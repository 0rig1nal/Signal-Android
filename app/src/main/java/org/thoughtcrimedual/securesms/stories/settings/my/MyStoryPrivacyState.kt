package org.thoughtcrimedual.securesms.stories.settings.my

import org.thoughtcrimedual.securesms.database.model.DistributionListPrivacyMode

data class MyStoryPrivacyState(val privacyMode: DistributionListPrivacyMode? = null, val connectionCount: Int = 0)
