package org.thoughtcrimedual.securesms.keyboard.emoji

import org.thoughtcrimedual.securesms.components.emoji.EmojiEventListener
import org.thoughtcrimedual.securesms.keyboard.emoji.search.EmojiSearchFragment

interface EmojiKeyboardCallback :
  EmojiEventListener,
  EmojiKeyboardPageFragment.Callback,
  EmojiSearchFragment.Callback
