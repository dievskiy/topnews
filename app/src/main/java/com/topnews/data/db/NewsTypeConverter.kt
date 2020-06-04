package com.topnews.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.topnews.data.news.News

object NewsTypeConverter {

    @TypeConverter
    @JvmStatic
    fun fromNewsArticleList(value: List<News.Article>): String {
        val type = object : TypeToken<List<News.Article>>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    @JvmStatic
    fun toNewsArticleList(value: String): List<News.Article> {
        val type = object : TypeToken<List<News.Article>>() {}.type
        return Gson().fromJson(value, type)
    }
}