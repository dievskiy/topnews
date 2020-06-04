package com.topnews.ui.bookmarks

import androidx.lifecycle.ViewModel
import com.topnews.di.other.ChildFragmentScoped
import com.topnews.di.other.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


@Module
@Suppress("UNUSED")
internal abstract class BookmarksModule {

    @ChildFragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeTopFragment(): BookmarksFragment

    @Binds
    @IntoMap
    @ViewModelKey(BookmarkViewModel::class)
    abstract fun bindTopViewModel(viewModel: BookmarkViewModel): ViewModel

}

