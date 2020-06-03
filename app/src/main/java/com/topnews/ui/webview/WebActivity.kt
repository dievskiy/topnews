package com.topnews.ui.webview

import android.os.Bundle
import android.view.MenuItem
import com.topnews.R
import com.topnews.ui.views.BaseWebView
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : DaggerAppCompatActivity() {

    companion object {
        const val INTENT_EXTRAS_STRING_URL = "url"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        // set toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)


        loadPage()
    }

    private fun loadPage() {
        val intent = intent
        val url = intent.getStringExtra(INTENT_EXTRAS_STRING_URL)
        val webView = findViewById<BaseWebView>(R.id.webView)
        webView.loadUrl(url)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.itemId == android.R.id.home) {
            finish() // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item)
    }


}