package com.topnews.ui.favourites

import androidx.lifecycle.ViewModel
import com.topnews.di.other.ChildFragmentScoped
import com.topnews.di.other.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
@Suppress("UNUSED")
internal abstract class TopModule {

    @ChildFragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFavFragment(): TopFragment

    @Binds
    @IntoMap
    @ViewModelKey(TopViewModel::class)
    abstract fun bindFavViewModel(viewModel: TopViewModel): ViewModel

}

