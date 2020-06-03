package com.topnews.ui.favourites

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.topnews.R
import com.topnews.data.news.ArticleListAdapter
import com.topnews.databinding.FragmentFavouritesBinding
import com.topnews.ui.webview.WebActivity
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class FavouritesFragment : DaggerFragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
//    private lateinit var viewModel: FavouritesViewModel

    private val viewModel: FavouritesViewModel by navGraphViewModels(R.id.mobile_navigation) {
        viewModelFactory
    }

    private lateinit var binding: FragmentFavouritesBinding

    private lateinit var adapter: ArticleListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouritesBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        adapter = ArticleListAdapter(requireContext()) { article ->
            val intent = Intent(requireContext(), WebActivity::class.java)
            intent.putExtra(WebActivity.INTENT_EXTRAS_STRING_URL, article.url)
            startActivity(intent)
        }
        return binding.root
    }


    override fun onPause() {
        super.onPause()
        // saving scroll position
        viewModel.scrollPosition.postValue(
            (binding.articlesRv.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        )
    }

    override fun onResume() {
        super.onResume()
        // restoring scroll position
        (binding.articlesRv.layoutManager as LinearLayoutManager).scrollToPosition(
            viewModel.scrollPosition.value ?: 0
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel = viewModelProvider(viewModelFactory)

        binding.viewmodel = viewModel
        binding.articlesRv.adapter = adapter

        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.news.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it.data?.articles)
            binding.articlesRv.scrollTo(0, 0)
        })
    }


}