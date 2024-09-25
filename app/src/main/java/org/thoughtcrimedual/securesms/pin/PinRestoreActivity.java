package org.thoughtcrimedual.securesms.pin;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.thoughtcrimedual.securesms.MainActivity;
import org.thoughtcrimedual.securesms.PassphraseRequiredActivity;
import org.thoughtcrimedual.securesms.R;
import org.thoughtcrimedual.securesms.lock.v2.CreateSvrPinActivity;
import org.thoughtcrimedual.securesms.util.DynamicNoActionBarTheme;
import org.thoughtcrimedual.securesms.util.DynamicTheme;

public final class PinRestoreActivity extends AppCompatActivity {

  private final DynamicTheme dynamicTheme = new DynamicNoActionBarTheme();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    dynamicTheme.onCreate(this);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.pin_restore_activity);
  }

  @Override
  protected void onResume() {
    super.onResume();
    dynamicTheme.onResume(this);
  }

  void navigateToPinCreation() {
    final Intent main      = MainActivity.clearTop(this);
    final Intent createPin = CreateSvrPinActivity.getIntentForPinCreate(this);
    final Intent chained   = PassphraseRequiredActivity.chainIntent(createPin, main);

    startActivity(chained);
    finish();
  }
}
