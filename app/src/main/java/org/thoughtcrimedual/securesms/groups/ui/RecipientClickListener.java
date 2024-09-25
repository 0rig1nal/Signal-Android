package org.thoughtcrimedual.securesms.groups.ui;

import androidx.annotation.NonNull;

import org.thoughtcrimedual.securesms.recipients.Recipient;

public interface RecipientClickListener {
  void onClick(@NonNull Recipient recipient);
}
