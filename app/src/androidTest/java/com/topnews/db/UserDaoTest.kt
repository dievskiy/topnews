package com.topnews.db

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.topnews.data.user.CountryType
import com.topnews.data.user.NewsCategory
import com.topnews.data.user.User
import com.topnews.db.DbTest
import com.topnews.utils.InstantTaskExecutorRule
import com.topnews.utils.getOrAwaitValue
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserDaoTest : DbTest() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertAndLoad() {
        val categories = listOf(NewsCategory.SPORTS)
        val countryType = CountryType.CA
        val user =
            User(userId = 0, categories = categories, countryType = countryType)
        db.userDao().insert(user)
        val loaded = db.userDao().searchUser().getOrAwaitValue()
        assertThat(loaded?.countryType, `is`(countryType))
        assertThat(loaded?.categories, `is`(categories))


        val categoriesReplacement = listOf(NewsCategory.TECHNOLOGY)
        val countryTypeReplacement = CountryType.RU
        val replacement =
            User(
                userId = 0,
                categories = categoriesReplacement,
                countryType = countryTypeReplacement
            )
        db.userDao().insert(replacement)
        val loadedReplacement = db.userDao().searchUser().getOrAwaitValue()
        assertThat(loadedReplacement?.countryType, `is`(countryTypeReplacement))
        assertThat(loadedReplacement?.categories, `is`(categoriesReplacement))
    }
}