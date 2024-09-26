package org.thoughtcrimedual.securesms.mms;


import androidx.annotation.NonNull;

import org.thoughtcrimedual.securesms.attachments.Attachment;
import org.thoughtcrimedual.securesms.util.MediaUtil;

/**
 * Slide used for attachments with contentType {@link MediaUtil#VIEW_ONCE}.
 * Attachments will only get this type *after* they've been viewed, or if they were synced from a
 * linked device. Incoming unviewed messages will have the appropriate image/video contentType.
 */
public class ViewOnceSlide extends Slide {

  public ViewOnceSlide(@NonNull Attachment attachment) {
    super(attachment);
  }

  @Override
  public boolean hasViewOnce() {
    return true;
  }
}
