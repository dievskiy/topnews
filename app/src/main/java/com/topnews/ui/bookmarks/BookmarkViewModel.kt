package com.topnews.ui.bookmarks

import androidx.lifecycle.*
import com.topnews.data.bookmark.BookmarkRepository
import com.topnews.data.news.News
import javax.inject.Inject

class BookmarkViewModel @Inject constructor(
    bookmarkRepository: BookmarkRepository
) : ViewModel() {

    private val _articles = bookmarkRepository.loadBookmarks()

    val articles: LiveData<List<News.Article>>
        get() = _articles

}