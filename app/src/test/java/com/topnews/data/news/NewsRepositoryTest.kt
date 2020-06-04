package com.topnews.data.news

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.topnews.AppExecutors
import com.topnews.api.ApiResponse
import com.topnews.api.NewsService
import com.topnews.data.db.AppDatabase
import com.topnews.data.network.Resource
import com.topnews.data.user.CountryType
import com.topnews.data.user.NewsCategory
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import retrofit2.Response
import java.util.concurrent.Executor

@RunWith(JUnit4::class)
class NewsRepositoryTest {
    private lateinit var repository: NewsRepository
    private val dao = Mockito.mock(NewsDao::class.java)
    private val service = Mockito.mock(NewsService::class.java)

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    class InstantAppExecutors : AppExecutors(instant, instant, instant) {
        companion object {
            private val instant = Executor { it.run() }
        }
    }

    @Before
    fun init() {
        val db = Mockito.mock(AppDatabase::class.java)
        Mockito.`when`(db.newsDao()).thenReturn(dao)
        Mockito.`when`(db.runInTransaction(ArgumentMatchers.any())).thenCallRealMethod()
        repository = NewsRepository(InstantAppExecutors(), dao, service)
    }


    @Test
    fun loadRepoFromNetwork() {
        val dbData = MutableLiveData<News>()
        Mockito.`when`(dao.loadNews()).thenReturn(dbData)

        val news = News(id = 0, status = "ok", articles = emptyList())
        val call = successCall(news)
        Mockito.`when`(service.fetchFavouritesNews("ru", category = "business")).thenReturn(call)

        val data = repository.fetchNews(CountryType.RU, NewsCategory.BUSINESS)
        Mockito.verify(dao).loadNews()
        Mockito.verifyNoMoreInteractions(service)

        val observer: Observer<Resource<News>> = mock()
        data.observeForever(observer)
        Mockito.verifyNoMoreInteractions(service)
        Mockito.verify(observer).onChanged(Resource.loading(null))
        val updatedDbData = MutableLiveData<News>()
        Mockito.`when`(dao.loadNews()).thenReturn(updatedDbData)

    }
}

fun <T : Any> successCall(data: T) = createCall(Response.success(data))

fun <T : Any> createCall(response: Response<T>) = MutableLiveData<ApiResponse<T>>().apply {
    value = ApiResponse.create(response)
} as LiveData<ApiResponse<T>>

inline fun <reified T> mock(): T = Mockito.mock(T::class.java)
