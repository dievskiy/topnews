package com.topnews.ui.webview

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.topnews.R
import com.topnews.data.news.News
import com.topnews.ui.views.BaseWebView
import com.topnews.utils.viewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_web.*
import javax.inject.Inject


class WebActivity : DaggerAppCompatActivity() {

    companion object {
        const val INTENT_EXTRAS_ARTICLE = "article"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: WebActivityViewModel

    private var article: News.Article? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        viewModel = viewModelProvider(viewModelFactory)

        val intent = intent
        article = intent.getSerializableExtra(INTENT_EXTRAS_ARTICLE) as News.Article?

        // set toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        loadPage()
        setArticle()
    }

    private fun setToolbarIconObserver() {
        viewModel.isSaved.observe(this, Observer {
            if (it) {
                toolbar.menu.findItem(R.id.web_menu_like)?.setIcon(R.drawable.ic_like_filled)
            } else {
                toolbar.menu.findItem(R.id.web_menu_like)?.setIcon(R.drawable.ic_like)
            }
        })
    }

    private fun setArticle() {
        article?.let { viewModel.checkIfArticleSaved(it) }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.web_menu, menu)
        setToolbarIconObserver()
        return true
    }

    private fun loadPage() {
        article?.run {
            if (url.isNotEmpty()) {
                val webView = findViewById<BaseWebView>(R.id.webView)
                webView.loadUrl(url)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.web_menu_like -> likeClicked()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun likeClicked() {
        viewModel.saveClicked()
    }


}