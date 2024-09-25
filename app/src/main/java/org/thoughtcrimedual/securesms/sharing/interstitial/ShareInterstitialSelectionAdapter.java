package org.thoughtcrimedual.securesms.sharing.interstitial;

import org.thoughtcrimedual.securesms.R;
import org.thoughtcrimedual.securesms.util.adapter.mapping.MappingAdapter;
import org.thoughtcrimedual.securesms.util.viewholders.RecipientViewHolder;

class ShareInterstitialSelectionAdapter extends MappingAdapter {
  ShareInterstitialSelectionAdapter() {
    registerFactory(ShareInterstitialMappingModel.class, RecipientViewHolder.createFactory(R.layout.share_contact_selection_item, null));
  }
}
