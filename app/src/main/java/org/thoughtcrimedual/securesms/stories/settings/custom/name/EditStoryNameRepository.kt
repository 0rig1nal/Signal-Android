package org.thoughtcrimedual.securesms.stories.settings.custom.name

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.thoughtcrimedual.securesms.database.SignalDatabase
import org.thoughtcrimedual.securesms.database.model.DistributionListId
import org.thoughtcrimedual.securesms.stories.Stories

class EditStoryNameRepository {
  fun save(privateStoryId: DistributionListId, name: CharSequence): Completable {
    return Completable.create {
      if (privateStoryId == DistributionListId.MY_STORY) {
        error("Cannot set name for My Story")
      }

      if (SignalDatabase.distributionLists.setName(privateStoryId, name.toString())) {
        Stories.onStorySettingsChanged(privateStoryId)

        it.onComplete()
      } else {
        it.onError(Exception("Could not update story name."))
      }
    }.subscribeOn(Schedulers.io())
  }
}
