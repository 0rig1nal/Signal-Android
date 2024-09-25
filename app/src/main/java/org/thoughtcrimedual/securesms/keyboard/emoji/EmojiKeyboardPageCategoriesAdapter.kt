package org.thoughtcrimedual.securesms.keyboard.emoji

import org.thoughtcrimedual.securesms.R
import org.thoughtcrimedual.securesms.keyboard.KeyboardPageCategoryIconViewHolder
import org.thoughtcrimedual.securesms.util.adapter.mapping.LayoutFactory
import org.thoughtcrimedual.securesms.util.adapter.mapping.MappingAdapter
import java.util.function.Consumer

class EmojiKeyboardPageCategoriesAdapter(private val onPageSelected: Consumer<String>) : MappingAdapter() {
  init {
    registerFactory(RecentsMappingModel::class.java, LayoutFactory({ v -> KeyboardPageCategoryIconViewHolder(v, onPageSelected) }, R.layout.keyboard_pager_category_icon))
    registerFactory(EmojiCategoryMappingModel::class.java, LayoutFactory({ v -> KeyboardPageCategoryIconViewHolder(v, onPageSelected) }, R.layout.keyboard_pager_category_icon))
  }
}
