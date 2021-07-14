package com.example.myernaehrungapplication.data.Personal

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PersonalDao {

    @Insert
    suspend fun addPersonalInfo(personal: PersonalEntity): Long

    @Query("SELECT * FROM personal_table ORDER BY id ASC")
    fun fetchAllPersonalInfo(): List<PersonalEntity>
}