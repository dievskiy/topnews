package com.topnews.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.topnews.data.news.ArticleDao
import com.topnews.data.news.NewsDao
import com.topnews.data.news.News
import com.topnews.data.user.User
import com.topnews.data.user.UserDao
import com.topnews.utils.DATABASE_NAME

/**
 * The Room database for this app
 */
@Database(
    entities = [News::class, User::class, News.Article::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
    abstract fun userDao(): UserDao
    abstract fun articleDao(): ArticleDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance
                    ?: buildDatabase(
                        context
                    ).also { instance = it }
            }
        }

        fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}