package org.thoughtcrimedual.securesms.sharing;

import org.thoughtcrimedual.securesms.R;
import org.thoughtcrimedual.securesms.util.adapter.mapping.MappingAdapter;

public class ShareSelectionAdapter extends MappingAdapter {
  public ShareSelectionAdapter() {
    registerFactory(ShareSelectionMappingModel.class,
                    ShareSelectionViewHolder.createFactory(R.layout.share_contact_selection_item));
  }
}
