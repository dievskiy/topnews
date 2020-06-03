package com.topnews.ui.views

import android.content.Context
import android.util.AttributeSet
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

/**
 * WebView to display news' content
 */
class BaseWebView(context: Context, attrs: AttributeSet) : WebView(context, attrs) {
    init {
        webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return false
            }
        }
        settings.javaScriptEnabled = true
    }

}