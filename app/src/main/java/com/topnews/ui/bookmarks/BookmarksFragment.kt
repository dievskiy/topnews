package com.topnews.ui.bookmarks

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navGraphViewModels
import com.topnews.R
import com.topnews.data.bookmark.BookmarkListAdapter
import com.topnews.databinding.FragmentBookmarksBinding
import com.topnews.ui.webview.WebActivity
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class BookmarksFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: BookmarkViewModel by navGraphViewModels(R.id.mobile_navigation) {
        viewModelFactory
    }

    private lateinit var binding: FragmentBookmarksBinding

    private lateinit var adapter: BookmarkListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookmarksBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }

        adapter = BookmarkListAdapter(requireContext()) { article ->
            val intent = Intent(requireContext(), WebActivity::class.java)
            intent.putExtra(WebActivity.INTENT_EXTRAS_ARTICLE, article)
            startActivity(intent)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = viewModel
        binding.articlesRv.adapter = adapter

        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.articles.observe(viewLifecycleOwner, Observer {
            // show last bookmarks first
            adapter.submitList(it.reversed())
        })
    }

}