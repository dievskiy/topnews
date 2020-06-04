package com.topnews.ui.webview

import androidx.lifecycle.ViewModel
import com.topnews.di.other.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class WebModule {

    @Binds
    @IntoMap
    @ViewModelKey(WebActivityViewModel::class)
    internal abstract fun bindViewModel(viewModel: WebActivityViewModel): ViewModel
}