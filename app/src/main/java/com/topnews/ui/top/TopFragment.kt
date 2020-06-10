package com.topnews.ui.top

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.topnews.R
import com.topnews.data.news.ArticleListAdapter
import com.topnews.databinding.FragmentTopBinding
import com.topnews.ui.intro.IntroductionActivity
import com.topnews.ui.webview.WebActivity
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class TopFragment : DaggerFragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: TopViewModel by navGraphViewModels(R.id.mobile_navigation) {
        viewModelFactory
    }

    private lateinit var binding: FragmentTopBinding

    private lateinit var adapter: ArticleListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTopBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        adapter = ArticleListAdapter(requireContext()) { article ->
            val intent = Intent(requireContext(), WebActivity::class.java)
            intent.putExtra(WebActivity.INTENT_EXTRAS_ARTICLE, article)
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
        binding.viewmodel = viewModel
        binding.articlesRv.adapter = adapter
        binding.configure.setOnClickListener { startIntroduction() }

        setUpObservers()
    }

    private fun startIntroduction() {
        val intent = Intent(requireContext(), IntroductionActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun setUpObservers() {
        viewModel.news.observe(viewLifecycleOwner, Observer {
            it.data ?: return@Observer
            adapter.submitList(it.data.articles)
        })

    }


}

