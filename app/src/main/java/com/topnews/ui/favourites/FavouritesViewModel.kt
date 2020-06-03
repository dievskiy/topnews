package com.topnews.ui.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.topnews.data.network.Resource
import com.topnews.data.news.News
import com.topnews.data.news.NewsRepository
import com.topnews.data.user.CountryType
import com.topnews.data.user.NewsCategory
import com.topnews.data.user.UserRepository
import javax.inject.Inject

class FavouritesViewModel @Inject constructor(
    newsRepository: NewsRepository,
    userRepository: UserRepository
) : ViewModel() {

    private val _user = userRepository.getUser()

    val news: LiveData<Resource<News>> = _user.switchMap { user ->
        // todo start intro
        newsRepository.fetchFavouriteNews(
            countryType = user?.countryType ?: CountryType.US,
            category = user?.categories?.getOrNull(0) ?: NewsCategory.BUSINESS
        )
    }

    val scrollPosition = MutableLiveData(0)

}