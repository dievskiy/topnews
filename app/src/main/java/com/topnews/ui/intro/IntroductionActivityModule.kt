package com.topnews.ui.intro

import androidx.lifecycle.ViewModel
import com.topnews.di.other.ActivityScoped
import com.topnews.di.other.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton


@Module
abstract class IntroductionActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(IntroViewModel::class)
    @ActivityScoped
    internal abstract fun bindViewModel(viewModel: IntroViewModel): ViewModel
}