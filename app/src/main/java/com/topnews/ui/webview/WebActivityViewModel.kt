package com.topnews.ui.webview

import androidx.lifecycle.*
import com.topnews.data.bookmark.BookmarkRepository
import com.topnews.data.news.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class WebActivityViewModel @Inject constructor(private val repository: BookmarkRepository) :
    ViewModel() {


    val isSaved: MutableLiveData<Boolean> = MutableLiveData()

    private val article: MutableLiveData<News.Article> = MutableLiveData()

    fun checkIfArticleSaved(articleToSet: News.Article) {
        viewModelScope.launch(Dispatchers.IO) {
            article.postValue(articleToSet)
            isSaved.postValue(repository.isSaved(articleToSet.url))
        }
    }

    fun saveClicked() {
        if (isSaved.value != null && article.value != null) {
            val newValue = isSaved.value!!
            val article = article.value!!
            isSaved.value = !newValue
            viewModelScope.launch(Dispatchers.IO) {
                if (newValue) repository.delete(article.url)
                else repository.save(article)
            }
        }
    }


}