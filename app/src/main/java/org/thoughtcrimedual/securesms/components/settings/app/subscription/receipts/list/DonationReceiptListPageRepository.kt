package org.thoughtcrimedual.securesms.components.settings.app.subscription.receipts.list

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.thoughtcrimedual.securesms.database.SignalDatabase
import org.thoughtcrimedual.securesms.database.model.InAppPaymentReceiptRecord

class DonationReceiptListPageRepository {
  fun getRecords(type: InAppPaymentReceiptRecord.Type?): Single<List<InAppPaymentReceiptRecord>> {
    return Single.fromCallable {
      SignalDatabase.donationReceipts.getReceipts(type)
    }.subscribeOn(Schedulers.io())
  }
}
