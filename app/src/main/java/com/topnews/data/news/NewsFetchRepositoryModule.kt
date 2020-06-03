package com.topnews.data.news

import com.topnews.AppExecutors
import com.topnews.data.db.AppDatabase
import com.topnews.api.NewsService
import com.topnews.data.user.UserRepository
import com.topnews.utils.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
internal class NewsFetchRepositoryModule {

    @Provides
    fun provideNewsService(): NewsService {
        return Retrofit.Builder()
            .baseUrl("http://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(NewsService::class.java)
    }

    @Provides
    fun provideAppExecutors(): AppExecutors {
        return AppExecutors()
    }

    @Provides
    fun bindFavouritesRepository(
        appDatabase: AppDatabase,
        appExecutors: AppExecutors,
        newsService: NewsService
    ): NewsRepository {
        return NewsRepository(appExecutors, appDatabase.newsDao(), newsService)
    }

}