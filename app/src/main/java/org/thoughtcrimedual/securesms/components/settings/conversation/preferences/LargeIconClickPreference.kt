package org.thoughtcrimedual.securesms.components.settings.conversation.preferences

import android.view.View
import org.thoughtcrimedual.securesms.R
import org.thoughtcrimedual.securesms.components.settings.DSLSettingsIcon
import org.thoughtcrimedual.securesms.components.settings.DSLSettingsText
import org.thoughtcrimedual.securesms.components.settings.PreferenceModel
import org.thoughtcrimedual.securesms.components.settings.PreferenceViewHolder
import org.thoughtcrimedual.securesms.util.adapter.mapping.LayoutFactory
import org.thoughtcrimedual.securesms.util.adapter.mapping.MappingAdapter

/**
 * Renders a preference line item with a larger (40dp) icon
 */
object LargeIconClickPreference {

  fun register(adapter: MappingAdapter) {
    adapter.registerFactory(Model::class.java, LayoutFactory(::ViewHolder, R.layout.large_icon_preference_item))
  }

  class Model(
    override val title: DSLSettingsText?,
    override val icon: DSLSettingsIcon,
    override val summary: DSLSettingsText? = null,
    override val isEnabled: Boolean = true,
    val onClick: () -> Unit
  ) : PreferenceModel<Model>()

  private class ViewHolder(itemView: View) : PreferenceViewHolder<Model>(itemView) {
    override fun bind(model: Model) {
      super.bind(model)
      itemView.setOnClickListener { model.onClick() }
    }
  }
}
