package com.topnews.ui.settings

import androidx.lifecycle.ViewModel
import com.topnews.di.other.ChildFragmentScoped
import com.topnews.di.other.ViewModelKey
import com.topnews.ui.favourites.TopFragment
import com.topnews.ui.favourites.TopViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
@Suppress("UNUSED")
internal abstract class SettingsModule {

    @ChildFragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeSettingsFragment(): SettingsFragment

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    abstract fun bindSettingsViewModel(viewModel: SettingsViewModel): ViewModel

}

