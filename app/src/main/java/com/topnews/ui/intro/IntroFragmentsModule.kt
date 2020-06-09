package com.topnews.ui.intro

import androidx.lifecycle.ViewModel
import com.topnews.di.other.ChildFragmentScoped
import com.topnews.di.other.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


@Module
@Suppress("UNUSED")
internal abstract class IntroFragmentsModule {

    @ContributesAndroidInjector
    internal abstract fun contributeCountryFragment(): CountryFragment

    @ContributesAndroidInjector
    internal abstract fun contributeCategoriesFragment(): CategoriesFragment

    @ContributesAndroidInjector
    internal abstract fun contributePrivacyFragment(): PrivacyFragment

}

