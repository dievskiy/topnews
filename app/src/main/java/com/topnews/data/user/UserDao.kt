package com.topnews.data.user

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Query("select * from user where id = 0")
    fun searchUser(): LiveData<User?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)
}