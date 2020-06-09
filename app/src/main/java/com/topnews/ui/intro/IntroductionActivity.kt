package com.topnews.ui.intro

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.topnews.R
import com.topnews.utils.viewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class IntroductionActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: IntroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = viewModelProvider(viewModelFactory)

        setContentView(R.layout.activity_introduction)

    }

    override fun onBackPressed() {
    }
}

interface MovingToNext{
    fun moveToNext()
}