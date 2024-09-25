package org.thoughtcrimedual.securesms.components.settings.app.subscription.models

import org.thoughtcrimedual.securesms.databinding.PaypalButtonBinding
import org.thoughtcrimedual.securesms.util.adapter.mapping.BindingFactory
import org.thoughtcrimedual.securesms.util.adapter.mapping.BindingViewHolder
import org.thoughtcrimedual.securesms.util.adapter.mapping.MappingAdapter
import org.thoughtcrimedual.securesms.util.adapter.mapping.MappingModel

object PayPalButton {
  fun register(mappingAdapter: MappingAdapter) {
    mappingAdapter.registerFactory(Model::class.java, BindingFactory(::ViewHolder, PaypalButtonBinding::inflate))
  }

  class Model(val onClick: () -> Unit, val isEnabled: Boolean) : MappingModel<Model> {
    override fun areItemsTheSame(newItem: Model): Boolean = true
    override fun areContentsTheSame(newItem: Model): Boolean = isEnabled == newItem.isEnabled
  }

  class ViewHolder(binding: PaypalButtonBinding) : BindingViewHolder<Model, PaypalButtonBinding>(binding) {
    override fun bind(model: Model) {
      binding.paypalButton.isEnabled = model.isEnabled
      binding.paypalButton.setOnClickListener {
        binding.paypalButton.isEnabled = false
        model.onClick()
      }
    }
  }
}
