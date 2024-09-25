package org.thoughtcrimedual.securesms.search

import org.thoughtcrimedual.securesms.database.model.ThreadRecord

data class ThreadSearchResult(val results: List<ThreadRecord>, val query: String)
