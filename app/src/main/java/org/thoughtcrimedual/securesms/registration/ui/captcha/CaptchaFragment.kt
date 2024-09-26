/*
 * Copyright 2024 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.thoughtcrimedual.securesms.registration.ui.captcha

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import org.thoughtcrimedual.securesms.BuildConfig
import org.thoughtcrimedual.securesms.LoggingFragment
import org.thoughtcrimedual.securesms.R
import org.thoughtcrimedual.securesms.components.ViewBinderDelegate
import org.thoughtcrimedual.securesms.databinding.FragmentRegistrationCaptchaBinding
import org.thoughtcrimedual.securesms.registration.fragments.RegistrationConstants

abstract class CaptchaFragment : LoggingFragment(R.layout.fragment_registration_captcha) {

  private val binding: FragmentRegistrationCaptchaBinding by ViewBinderDelegate(FragmentRegistrationCaptchaBinding::bind)

  private val backListener = object : OnBackPressedCallback(true) {
    override fun handleOnBackPressed() {
      handleUserExit()
    }
  }

  @SuppressLint("SetJavaScriptEnabled")
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.registrationCaptchaWebView.settings.javaScriptEnabled = true
    binding.registrationCaptchaWebView.clearCache(true)

    binding.registrationCaptchaWebView.webViewClient = object : WebViewClient() {
      @Deprecated("Deprecated in Java")
      override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        if (url.startsWith(RegistrationConstants.SIGNAL_CAPTCHA_SCHEME)) {
          val token = url.substring(RegistrationConstants.SIGNAL_CAPTCHA_SCHEME.length)
          handleCaptchaToken(token)
          backListener.isEnabled = false
          findNavController().navigateUp()
          return true
        }
        return false
      }
    }
    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
      handleUserExit()
    }
    binding.registrationCaptchaWebView.loadUrl(BuildConfig.SIGNAL_CAPTCHA_URL)
  }

  abstract fun handleCaptchaToken(token: String)

  abstract fun handleUserExit()
}
