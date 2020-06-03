package com.topnews.api

import androidx.lifecycle.LiveData
import com.topnews.api.ApiResponse
import com.topnews.data.news.News
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Service for fetching news from REST API
 */
interface NewsService {

    /**
     * Fetch "Popular" news
     */
    @GET("v2/top-headlines/")
    fun fetchTopNews(
        @Query("country") country: String,
        @Query("pageSize") pageSize: Int = 100,
        @Query("apiKey") apiKey: String = "05bd29e4cdba42cfa3fb117b03b9be87"
    ): LiveData<ApiResponse<News>>

    /**
     * Fetch favourites news based on categories
     */
    @GET("v2/top-headlines/")
    fun fetchFavouritesNews(
        @Query("country") country: String,
        @Query("pageSize") pageSize: Int = 100,
        @Query("apiKey") apiKey: String = "05bd29e4cdba42cfa3fb117b03b9be87",
        @Query("category") category: String
    ): LiveData<ApiResponse<News>>


}