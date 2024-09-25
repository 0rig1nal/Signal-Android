package org.thoughtcrimedual.securesms.stickers;

import androidx.annotation.NonNull;

import org.thoughtcrimedual.securesms.database.model.StickerRecord;

public interface StickerEventListener {
  void onStickerSelected(@NonNull StickerRecord sticker);

  void onStickerManagementClicked();
}
