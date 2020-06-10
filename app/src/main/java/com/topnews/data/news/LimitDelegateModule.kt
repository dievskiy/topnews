package com.topnews.data.news

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides

@Module
internal class LimitDelegateModule {

    @Provides
    fun provideLimitHandler(sharedPreferences: SharedPreferences): LimitHandler {
        return LimitDelegate(sharedPreferences)
    }
}