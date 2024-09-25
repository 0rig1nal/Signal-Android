package org.thoughtcrimedual.securesms.wallpaper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;

import org.thoughtcrimedual.securesms.PassphraseRequiredActivity;
import org.thoughtcrimedual.securesms.R;
import org.thoughtcrimedual.securesms.recipients.RecipientId;
import org.thoughtcrimedual.securesms.util.DynamicNoActionBarTheme;
import org.thoughtcrimedual.securesms.util.DynamicTheme;

public final class ChatWallpaperActivity extends PassphraseRequiredActivity {

  private static final String EXTRA_RECIPIENT_ID = "extra.recipient.id";

  private final DynamicTheme dynamicTheme = new DynamicNoActionBarTheme();

  public static @NonNull Intent createIntent(@NonNull Context context) {
    return createIntent(context, null);
  }

  public static @NonNull Intent createIntent(@NonNull Context context, @Nullable RecipientId recipientId) {
    Intent intent = new Intent(context, ChatWallpaperActivity.class);
    intent.putExtra(EXTRA_RECIPIENT_ID, recipientId);
    return intent;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState, boolean ready) {
    ChatWallpaperViewModel.Factory factory = new ChatWallpaperViewModel.Factory(getIntent().getParcelableExtra(EXTRA_RECIPIENT_ID));
    new ViewModelProvider(this, factory).get(ChatWallpaperViewModel.class);

    dynamicTheme.onCreate(this);
    setContentView(R.layout.chat_wallpaper_activity);

    if (savedInstanceState == null) {
      Bundle   extras = getIntent().getExtras();
      NavGraph graph  = Navigation.findNavController(this, R.id.nav_host_fragment).getGraph();

      Navigation.findNavController(this, R.id.nav_host_fragment).setGraph(graph, extras != null ? extras : new Bundle());
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
    dynamicTheme.onResume(this);
  }
}
