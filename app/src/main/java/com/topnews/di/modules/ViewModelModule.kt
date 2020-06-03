package com.topnews.di.modules

import androidx.lifecycle.ViewModelProvider
import com.topnews.di.other.CommonViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: CommonViewModelFactory):
            ViewModelProvider.Factory
}