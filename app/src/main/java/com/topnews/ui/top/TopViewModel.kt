package com.topnews.ui.top

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.topnews.data.network.Resource
import com.topnews.data.news.News
import com.topnews.data.news.NewsRepository
import com.topnews.data.user.NewsCategory
import com.topnews.data.user.User
import com.topnews.data.user.UserRepository
import com.topnews.utils.randomOrNullNotExperimental
import javax.inject.Inject
import kotlin.random.Random

class TopViewModel @Inject constructor(
    newsRepository: NewsRepository,
    userRepository: UserRepository,
    private val random: Random
) : ViewModel() {

    val scrollPosition = MutableLiveData(0)

    private val _user: LiveData<User?> = userRepository.getUser()

    private val _showIntro: MutableLiveData<Boolean> = MutableLiveData(false)
    val showIntro: LiveData<Boolean>
        get() = _showIntro

    val news: LiveData<Resource<News>> = _user.switchMap { user ->
        if (user == null) {
            _showIntro.value = true
            return@switchMap com.topnews.utils.AbsentLiveData.create<Resource<News>>()
        }
        newsRepository.fetchNews(
            countryType = user.countryType,
            category = user.categories.randomOrNullNotExperimental(random)
                ?: NewsCategory.GENERAL
        )
    }


}