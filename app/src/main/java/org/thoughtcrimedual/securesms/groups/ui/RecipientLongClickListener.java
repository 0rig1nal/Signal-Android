package org.thoughtcrimedual.securesms.groups.ui;

import androidx.annotation.NonNull;

import org.thoughtcrimedual.securesms.recipients.Recipient;

public interface RecipientLongClickListener {
  boolean onLongClick(@NonNull Recipient recipient);
}
