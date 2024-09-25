package org.thoughtcrimedual.securesms.deeplinks;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import org.thoughtcrimedual.securesms.MainActivity;
import org.thoughtcrimedual.securesms.PassphraseRequiredActivity;

public class DeepLinkEntryActivity extends PassphraseRequiredActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState, boolean ready) {
    Intent intent = MainActivity.clearTop(this);
    Uri    data   = getIntent().getData();
    intent.setData(data);
    startActivity(intent);
  }
}
