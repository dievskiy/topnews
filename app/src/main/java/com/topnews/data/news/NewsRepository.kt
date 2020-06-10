package com.topnews.data.news

import androidx.lifecycle.LiveData
import com.topnews.AppExecutors
import com.topnews.api.ApiResponse
import com.topnews.data.network.NetworkBoundResource
import com.topnews.api.NewsService
import com.topnews.data.network.Resource
import com.topnews.data.user.CountryType
import com.topnews.data.user.NewsCategory
import com.topnews.data.network.RateLimiter
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * repository for fetching news from [NewsService]
 */
@Singleton
class NewsRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val newsDao: NewsDao,
    private val newsService: NewsService,
    private val limitHandler: LimitHandler
) {

    private val repoListRateLimit =
        RateLimiter(60 * 7, TimeUnit.MINUTES, limitHandler.get()) // 7 hours

    fun fetchNews(
        countryType: CountryType,
        category: NewsCategory
    ): LiveData<Resource<News>> {
        return object : NetworkBoundResource<News, News>(appExecutors) {

            override fun saveCallResult(item: News) = newsDao.insert(item)

            override fun shouldFetch(data: News?): Boolean {
                return data == null || repoListRateLimit.shouldFetch()
            }

            override fun loadFromDb() = newsDao.loadNews()

            override fun createCall(): LiveData<ApiResponse<News>> {
                limitHandler.save()
                return newsService.fetchFavouritesNews(
                    country = countryType.name,
                    category = category.name
                )
            }

            override fun onFetchFailed() {
                limitHandler.reset()
            }
        }.asLiveData()
    }


}