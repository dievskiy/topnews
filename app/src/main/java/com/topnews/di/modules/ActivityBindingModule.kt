package com.topnews.di.modules

import com.topnews.data.news.NewsFetchRepositoryModule
import com.topnews.data.user.UserRepository
import com.topnews.data.user.UserRepositoryModule
import com.topnews.di.other.ActivityScoped
import com.topnews.ui.favourites.FavouritesModule
import com.topnews.ui.home.HomeActivity
import com.topnews.ui.home.HomeActivityModule
import com.topnews.ui.webview.WebActivity
import com.topnews.ui.webview.WebModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [WebModule::class])
    internal abstract fun webActivity(): WebActivity

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            // activity
            HomeActivityModule::class,
            // fragments
            FavouritesModule::class,
            // other
            NewsFetchRepositoryModule::class,
            UserRepositoryModule::class
        ]
    )
    internal abstract fun homeActivity(): HomeActivity

}