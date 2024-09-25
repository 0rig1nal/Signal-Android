package org.thoughtcrimedual.securesms.groups;

public final class GroupDoesNotExistException extends GroupChangeException {

  public GroupDoesNotExistException(Throwable throwable) {
    super(throwable);
  }
}
