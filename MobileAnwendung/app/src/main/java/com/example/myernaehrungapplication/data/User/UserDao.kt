package com.example.myernaehrungapplication.data.User

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User): Long

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData <List<User>>

    @Query("SELECT * FROM user_table WHERE user_table.email LIKE :username")
    fun getUser(username: String): User
}