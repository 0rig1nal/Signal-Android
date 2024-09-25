/*
 * Copyright 2023 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.thoughtcrimedual.securesms.components.settings.app.updates

import android.os.Build
import org.thoughtcrimedual.securesms.R
import org.thoughtcrimedual.securesms.components.settings.DSLConfiguration
import org.thoughtcrimedual.securesms.components.settings.DSLSettingsFragment
import org.thoughtcrimedual.securesms.components.settings.DSLSettingsText
import org.thoughtcrimedual.securesms.components.settings.configure
import org.thoughtcrimedual.securesms.dependencies.AppDependencies
import org.thoughtcrimedual.securesms.jobs.ApkUpdateJob
import org.thoughtcrimedual.securesms.keyvalue.SignalStore
import org.thoughtcrimedual.securesms.util.adapter.mapping.MappingAdapter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Settings around app updates. Only shown for builds that manage their own app updates.
 */
class AppUpdatesSettingsFragment : DSLSettingsFragment(R.string.preferences_app_updates__title) {

  override fun bindAdapter(adapter: MappingAdapter) {
    adapter.submitList(getConfiguration().toMappingModelList())
  }

  private fun getConfiguration(): DSLConfiguration {
    return configure {
      if (Build.VERSION.SDK_INT >= 31) {
        switchPref(
          title = DSLSettingsText.from("Automatic updates"),
          summary = DSLSettingsText.from("Automatically download and install app updates"),
          isChecked = SignalStore.apkUpdate.autoUpdate,
          onClick = {
            SignalStore.apkUpdate.autoUpdate = !SignalStore.apkUpdate.autoUpdate
          }
        )
      }

      clickPref(
        title = DSLSettingsText.from("Check for updates"),
        summary = DSLSettingsText.from("Last checked on: $lastSuccessfulUpdateString"),
        onClick = {
          AppDependencies.jobManager.add(ApkUpdateJob())
        }
      )
    }
  }

  private val lastSuccessfulUpdateString: String
    get() {
      val lastUpdateTime = SignalStore.apkUpdate.lastSuccessfulCheck

      return if (lastUpdateTime > 0) {
        val dateFormat = SimpleDateFormat("MMMM dd, yyyy 'at' h:mma", Locale.US)
        dateFormat.format(Date(lastUpdateTime))
      } else {
        "Never"
      }
    }
}
