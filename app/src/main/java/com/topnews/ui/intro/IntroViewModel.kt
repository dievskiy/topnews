package com.topnews.ui.intro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.topnews.data.user.CountryType
import com.topnews.data.user.NewsCategory
import com.topnews.data.user.User
import com.topnews.data.user.UserRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class IntroViewModel @Inject constructor(val userRepository: UserRepository) : ViewModel() {

    private val _countryType = MutableLiveData<CountryType>()
    val countryType: LiveData<CountryType>
        get() = _countryType

    val chosenCategories = MutableLiveData<MutableList<NewsCategory>>()

    init {
        chosenCategories.value = mutableListOf()
    }

    fun setCountry(type: CountryType) {
        _countryType.value = type
    }

    suspend fun insertUser() = coroutineScope {
        val countryType = _countryType.value
        val categories = chosenCategories.value
        if (countryType != null && categories != null) {
            userRepository.insertUser(
                User(
                    userId = 0,
                    countryType = countryType,
                    categories = categories
                )
            )
        }
    }

}