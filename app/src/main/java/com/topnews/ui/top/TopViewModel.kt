package com.topnews.ui.top

import android.util.Log
import androidx.lifecycle.*
import com.topnews.data.network.Resource
import com.topnews.data.news.News
import com.topnews.data.news.NewsRepository
import com.topnews.data.user.CountryType
import com.topnews.data.user.NewsCategory
import com.topnews.data.user.User
import com.topnews.data.user.UserRepository
import javax.inject.Inject

class TopViewModel @Inject constructor(
    newsRepository: NewsRepository,
    userRepository: UserRepository
) : ViewModel() {

    val scrollPosition = MutableLiveData(0)

    private val _user: LiveData<User?> = userRepository.getUser()

    val news: LiveData<Resource<News>> = _user.switchMap { user ->
        Log.d("123", "$user: ")
        // todo start intro
        newsRepository.fetchNews(
            countryType = user?.countryType ?: CountryType.US,
            category = user?.categories?.getOrNull(0) ?: NewsCategory.BUSINESS
        )
    }


}