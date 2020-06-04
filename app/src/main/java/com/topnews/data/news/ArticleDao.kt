package com.topnews.data.news

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface ArticleDao {

    @Query("select * from articles")
    fun loadNews(): LiveData<List<News.Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(article: News.Article)

    @Query("select * from articles where url = :url")
    fun searchArticle(url: String): News.Article?

    @Query("delete from articles where url = :url")
    fun delete(url: String)

}