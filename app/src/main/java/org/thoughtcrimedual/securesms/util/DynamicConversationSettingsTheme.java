package org.thoughtcrimedual.securesms.util;

import androidx.annotation.StyleRes;

import org.thoughtcrimedual.securesms.R;

public class DynamicConversationSettingsTheme extends DynamicTheme {

  protected @StyleRes int getTheme() {
    return R.style.Signal_DayNight_ConversationSettings;
  }
}
