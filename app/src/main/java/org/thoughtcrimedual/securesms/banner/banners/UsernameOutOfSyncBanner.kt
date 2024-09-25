/*
 * Copyright 2024 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.thoughtcrimedual.securesms.banner.banners

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.signal.core.ui.Previews
import org.signal.core.ui.SignalPreview
import org.thoughtcrimedual.securesms.R
import org.thoughtcrimedual.securesms.banner.Banner
import org.thoughtcrimedual.securesms.banner.ui.compose.Action
import org.thoughtcrimedual.securesms.banner.ui.compose.DefaultBanner
import org.thoughtcrimedual.securesms.banner.ui.compose.Importance
import org.thoughtcrimedual.securesms.keyvalue.AccountValues
import org.thoughtcrimedual.securesms.keyvalue.AccountValues.UsernameSyncState
import org.thoughtcrimedual.securesms.keyvalue.SignalStore

class UsernameOutOfSyncBanner(private val onActionClick: (UsernameSyncState) -> Unit) : Banner<UsernameSyncState>() {

  override val enabled: Boolean
    get() {
      return when (SignalStore.account.usernameSyncState) {
        AccountValues.UsernameSyncState.USERNAME_AND_LINK_CORRUPTED -> true
        AccountValues.UsernameSyncState.LINK_CORRUPTED -> true
        AccountValues.UsernameSyncState.IN_SYNC -> false
      }
    }

  override val dataFlow: Flow<UsernameSyncState>
    get() = flowOf(SignalStore.account.usernameSyncState)

  @Composable
  override fun DisplayBanner(model: UsernameSyncState, contentPadding: PaddingValues) {
    Banner(
      contentPadding = contentPadding,
      usernameSyncState = model,
      onFixClicked = onActionClick
    )
  }
}

@Composable
private fun Banner(contentPadding: PaddingValues, usernameSyncState: UsernameSyncState, onFixClicked: (UsernameSyncState) -> Unit = {}) {
  DefaultBanner(
    title = null,
    body = if (usernameSyncState == UsernameSyncState.USERNAME_AND_LINK_CORRUPTED) {
      stringResource(id = R.string.UsernameOutOfSyncReminder__username_and_link_corrupt)
    } else {
      stringResource(id = R.string.UsernameOutOfSyncReminder__link_corrupt)
    },
    importance = Importance.ERROR,
    actions = listOf(
      Action(R.string.UsernameOutOfSyncReminder__fix_now) {
        onFixClicked(usernameSyncState)
      }
    ),
    paddingValues = contentPadding
  )
}

@SignalPreview
@Composable
private fun BannerPreviewUsernameCorrupted() {
  Previews.Preview {
    Banner(contentPadding = PaddingValues(0.dp), usernameSyncState = UsernameSyncState.USERNAME_AND_LINK_CORRUPTED)
  }
}

@SignalPreview
@Composable
private fun BannerPreviewLinkCorrupted() {
  Previews.Preview {
    Banner(contentPadding = PaddingValues(0.dp), usernameSyncState = UsernameSyncState.LINK_CORRUPTED)
  }
}
