package org.thoughtcrimedual.securesms.components.settings.models

import android.view.View
import android.widget.TextView
import org.thoughtcrimedual.securesms.R
import org.thoughtcrimedual.securesms.components.settings.DSLSettingsText
import org.thoughtcrimedual.securesms.components.settings.PreferenceModel
import org.thoughtcrimedual.securesms.util.adapter.mapping.LayoutFactory
import org.thoughtcrimedual.securesms.util.adapter.mapping.MappingAdapter
import org.thoughtcrimedual.securesms.util.adapter.mapping.MappingViewHolder
import org.thoughtcrimedual.securesms.util.visible

object Progress {

  fun register(mappingAdapter: MappingAdapter) {
    mappingAdapter.registerFactory(Model::class.java, LayoutFactory(::ViewHolder, R.layout.dsl_progress_pref))
  }

  data class Model(
    override val title: DSLSettingsText?
  ) : PreferenceModel<Model>()

  private class ViewHolder(itemView: View) : MappingViewHolder<Model>(itemView) {

    private val title: TextView = itemView.findViewById(R.id.dsl_progress_pref_title)

    override fun bind(model: Model) {
      title.text = model.title?.resolve(context)
      title.visible = model.title != null
    }
  }
}
