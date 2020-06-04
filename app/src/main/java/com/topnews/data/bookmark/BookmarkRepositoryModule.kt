package com.topnews.data.bookmark

import com.topnews.data.db.AppDatabase
import com.topnews.data.user.UserRepository
import dagger.Module
import dagger.Provides

@Module
class BookmarkRepositoryModule {
    @Provides
    fun bindBookmarkRepository(appDatabase: AppDatabase): BookmarkRepository {
        return BookmarkRepository(appDatabase.articleDao())
    }
}