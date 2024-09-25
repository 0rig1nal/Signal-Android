package org.thoughtcrimedual.securesms.keyboard.emoji

import org.thoughtcrimedual.securesms.components.emoji.EmojiPageModel
import org.thoughtcrimedual.securesms.components.emoji.EmojiPageViewGridAdapter
import org.thoughtcrimedual.securesms.components.emoji.RecentEmojiPageModel
import org.thoughtcrimedual.securesms.components.emoji.parsing.EmojiTree
import org.thoughtcrimedual.securesms.emoji.EmojiCategory
import org.thoughtcrimedual.securesms.emoji.EmojiSource
import org.thoughtcrimedual.securesms.util.adapter.mapping.MappingModel

fun EmojiPageModel.toMappingModels(): List<MappingModel<*>> {
  val emojiTree: EmojiTree = EmojiSource.latest.emojiTree

  return displayEmoji.map {
    val isTextEmoji = EmojiCategory.EMOTICONS.key == key || (RecentEmojiPageModel.KEY == key && emojiTree.getEmoji(it.value, 0, it.value.length) == null)

    if (isTextEmoji) {
      EmojiPageViewGridAdapter.EmojiTextModel(key, it)
    } else {
      EmojiPageViewGridAdapter.EmojiModel(key, it)
    }
  }
}
