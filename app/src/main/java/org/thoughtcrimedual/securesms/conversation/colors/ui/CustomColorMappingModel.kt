package org.thoughtcrimedual.securesms.conversation.colors.ui

import org.thoughtcrimedual.securesms.util.adapter.mapping.MappingModel

class CustomColorMappingModel : MappingModel<CustomColorMappingModel> {
  override fun areItemsTheSame(newItem: CustomColorMappingModel): Boolean {
    return true
  }

  override fun areContentsTheSame(newItem: CustomColorMappingModel): Boolean {
    return true
  }
}
