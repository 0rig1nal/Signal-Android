package org.thoughtcrimedual.securesms.conversation.ui.mentions;

import androidx.annotation.NonNull;

import org.thoughtcrimedual.securesms.recipients.Recipient;
import org.thoughtcrimedual.securesms.util.viewholders.RecipientMappingModel;

public final class MentionViewState extends RecipientMappingModel<MentionViewState> {

  private final Recipient recipient;

  public MentionViewState(@NonNull Recipient recipient) {
    this.recipient = recipient;
  }

  @Override
  public @NonNull Recipient getRecipient() {
    return recipient;
  }
}
