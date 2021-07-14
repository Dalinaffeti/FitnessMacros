package com.example.myernaehrungapplication.data.Nutrition

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NutritionDao {

    @Insert
    suspend fun addNutritionInfo(goal: NutritionEntity): Long

    @Query("SELECT * FROM nutrition_table ORDER BY id ASC")
    fun fetchAllNutritionInfo(): List<NutritionEntity>
}