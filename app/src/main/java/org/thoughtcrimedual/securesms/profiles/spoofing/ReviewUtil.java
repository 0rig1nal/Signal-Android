package org.thoughtcrimedual.securesms.profiles.spoofing;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;

import com.annimon.stream.Stream;

import org.thoughtcrimedual.securesms.database.SignalDatabase;
import org.thoughtcrimedual.securesms.database.model.GroupRecord;
import org.thoughtcrimedual.securesms.recipients.Recipient;
import org.thoughtcrimedual.securesms.recipients.RecipientId;

public final class ReviewUtil {

  private ReviewUtil() { }

  @WorkerThread
  public static int getGroupsInCommonCount(@NonNull Context context, @NonNull RecipientId recipientId) {
    return Stream.of(SignalDatabase.groups()
                 .getPushGroupsContainingMember(recipientId))
                 .filter(g -> g.getMembers().contains(Recipient.self().getId()))
                 .map(GroupRecord::getRecipientId)
                 .toList()
                 .size();
  }
}
