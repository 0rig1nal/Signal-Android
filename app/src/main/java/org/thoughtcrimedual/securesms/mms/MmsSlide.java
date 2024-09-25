package org.thoughtcrimedual.securesms.mms;


import android.content.Context;

import androidx.annotation.NonNull;

import org.thoughtcrimedual.securesms.attachments.Attachment;

public class MmsSlide extends ImageSlide {

  public MmsSlide(@NonNull Attachment attachment) {
    super(attachment);
  }

  @NonNull
  @Override
  public String getContentDescription(Context context) {
    return "MMS";
  }

}
