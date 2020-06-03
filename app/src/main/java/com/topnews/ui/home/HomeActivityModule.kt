package com.topnews.ui.home


import androidx.lifecycle.ViewModel
import com.topnews.di.other.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeActivityViewModel::class)
    internal abstract fun bindViewModel(viewModel: HomeActivityViewModel): ViewModel
}