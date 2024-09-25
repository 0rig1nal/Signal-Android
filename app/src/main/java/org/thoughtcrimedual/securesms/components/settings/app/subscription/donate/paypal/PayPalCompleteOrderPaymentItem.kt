package org.thoughtcrimedual.securesms.components.settings.app.subscription.donate.paypal

import org.thoughtcrimedual.securesms.R
import org.thoughtcrimedual.securesms.util.adapter.mapping.LayoutFactory
import org.thoughtcrimedual.securesms.util.adapter.mapping.MappingAdapter
import org.thoughtcrimedual.securesms.util.adapter.mapping.MappingModel
import org.thoughtcrimedual.securesms.util.adapter.mapping.MappingViewHolder.SimpleViewHolder

/**
 * Line item on the PayPal order confirmation screen.
 */
object PayPalCompleteOrderPaymentItem {
  fun register(mappingAdapter: MappingAdapter) {
    mappingAdapter.registerFactory(Model::class.java, LayoutFactory(::SimpleViewHolder, R.layout.paypal_complete_order_payment_item))
  }

  class Model : MappingModel<Model> {
    override fun areItemsTheSame(newItem: Model): Boolean = true

    override fun areContentsTheSame(newItem: Model): Boolean = true
  }
}
