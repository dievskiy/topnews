package com.topnews.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.topnews.data.user.CountryType
import com.topnews.data.user.NewsCategory

object UserTypeConverter {
    @TypeConverter
    @JvmStatic
    fun fromCountryLangList(value: List<NewsCategory>): String {
        val gson = Gson()
        val type = object : TypeToken<List<NewsCategory>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    @JvmStatic
    fun toCountryLangList(value: String): List<NewsCategory> {
        val gson = Gson()
        val type = object : TypeToken<List<NewsCategory>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    @JvmStatic
    fun toCountryTag(value: String) = enumValueOf<CountryType>(value)

    @TypeConverter
    @JvmStatic
    fun fromCountryTag(value: CountryType) = value.name
}