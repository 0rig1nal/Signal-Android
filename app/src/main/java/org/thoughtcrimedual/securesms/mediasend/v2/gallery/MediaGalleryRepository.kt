package org.thoughtcrimedual.securesms.mediasend.v2.gallery

import android.content.Context
import org.thoughtcrimedual.securesms.mediasend.Media
import org.thoughtcrimedual.securesms.mediasend.MediaFolder
import org.thoughtcrimedual.securesms.mediasend.MediaRepository

class MediaGalleryRepository(context: Context, private val mediaRepository: MediaRepository) {
  private val context: Context = context.applicationContext

  fun getFolders(onFoldersRetrieved: (List<MediaFolder>) -> Unit) {
    mediaRepository.getFolders(context) { onFoldersRetrieved(it) }
  }

  fun getMedia(bucketId: String, onMediaRetrieved: (List<Media>) -> Unit) {
    mediaRepository.getMediaInBucket(context, bucketId) { onMediaRetrieved(it) }
  }
}
