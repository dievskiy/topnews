package com.topnews.ui.favourites

import androidx.lifecycle.ViewModel
import com.topnews.di.other.ChildFragmentScoped
import com.topnews.di.other.ViewModelKey
//import com.topnews.ui.favourites.FavouritesRepository
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
@Suppress("UNUSED")
internal abstract class FavouritesModule {

    @ChildFragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFavFragment(): FavouritesFragment

    @Binds
    @IntoMap
    @ViewModelKey(FavouritesViewModel::class)
    abstract fun bindFavViewModel(viewModel: FavouritesViewModel): ViewModel


}