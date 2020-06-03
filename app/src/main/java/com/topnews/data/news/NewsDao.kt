package com.topnews.data.news

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.topnews.data.news.News

@Dao
interface NewsDao {

    @Query("select * from news")
    fun loadNews(): LiveData<News?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(news: News)
}