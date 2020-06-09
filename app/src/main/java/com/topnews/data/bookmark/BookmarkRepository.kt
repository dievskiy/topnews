package com.topnews.data.bookmark

import com.topnews.data.news.ArticleDao
import com.topnews.data.news.News
import javax.inject.Inject

class BookmarkRepository @Inject constructor(private val articleDao: ArticleDao) {

    fun isSaved(url: String): Boolean = articleDao.searchArticle(url) != null

    fun save(article: News.Article) = articleDao.insert(article)

    fun delete(url: String) = articleDao.delete(url)

    fun loadBookmarks() = articleDao.loadNews()

}