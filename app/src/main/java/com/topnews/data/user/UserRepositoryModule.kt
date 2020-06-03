package com.topnews.data.user

import com.topnews.data.db.AppDatabase
import dagger.Module
import dagger.Provides


@Module
class UserRepositoryModule {
    @Provides
    fun bindUserRepository(appDatabase: AppDatabase): UserRepository {
        return UserRepository(appDatabase.userDao())
    }
}