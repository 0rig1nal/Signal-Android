/**
 * Copyright 2023 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.thoughtcrimedual.securesms.calls.links.details

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import org.signal.core.util.concurrent.MaybeCompat
import org.signal.core.util.orNull
import org.thoughtcrimedual.securesms.database.SignalDatabase
import org.thoughtcrimedual.securesms.dependencies.AppDependencies
import org.thoughtcrimedual.securesms.recipients.Recipient
import org.thoughtcrimedual.securesms.service.webrtc.links.CallLinkRoomId
import org.thoughtcrimedual.securesms.service.webrtc.links.ReadCallLinkResult
import org.thoughtcrimedual.securesms.service.webrtc.links.SignalCallLinkManager

class CallLinkDetailsRepository(
  private val callLinkManager: SignalCallLinkManager = AppDependencies.signalCallManager.callLinkManager
) {
  fun refreshCallLinkState(callLinkRoomId: CallLinkRoomId): Disposable {
    return MaybeCompat.fromCallable { SignalDatabase.callLinks.getCallLinkByRoomId(callLinkRoomId) }
      .flatMapSingle { callLinkManager.readCallLink(it.credentials!!) }
      .subscribeOn(Schedulers.io())
      .subscribeBy { result ->
        when (result) {
          is ReadCallLinkResult.Success -> SignalDatabase.callLinks.updateCallLinkState(callLinkRoomId, result.callLinkState)
          is ReadCallLinkResult.Failure -> Unit
        }
      }
  }

  fun watchCallLinkRecipient(callLinkRoomId: CallLinkRoomId): Observable<Recipient> {
    return MaybeCompat.fromCallable { SignalDatabase.recipients.getByCallLinkRoomId(callLinkRoomId).orNull() }
      .flatMapObservable { Recipient.observable(it) }
      .distinctUntilChanged { a, b -> a.hasSameContent(b) }
      .subscribeOn(Schedulers.io())
  }
}
