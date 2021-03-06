package com.topnews.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.topnews.MainApplication
import com.topnews.R
import com.topnews.data.db.AppDatabase
import com.topnews.utils.SP_NAME_TAG
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import kotlin.random.Random

@Module
class AppModule {


    @Provides
    fun provideRequestOptions(): RequestOptions {
        return RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
    }

    @Provides
    fun provideGlide(
        application: MainApplication,
        requestOptions: RequestOptions
    ): RequestManager {
        return Glide.with(application.applicationContext).applyDefaultRequestOptions(requestOptions)
    }

    @Provides
    fun provideContext(application: MainApplication): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideSP(application: MainApplication): SharedPreferences {
        return application.getSharedPreferences(SP_NAME_TAG, Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideRandom(): Random {
        return Random(123)
    }

    @Singleton
    @Provides
    fun providesAppDatabase(context: Context): AppDatabase = AppDatabase.buildDatabase(context)
}