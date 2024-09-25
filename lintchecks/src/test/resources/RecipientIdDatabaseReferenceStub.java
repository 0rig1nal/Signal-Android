package org.thoughtcrimedual.securesms.database;

interface RecipientIdDatabaseReference {
  void remapRecipient(RecipientId fromId, RecipientId toId);
}
