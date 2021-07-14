package com.example.myernaehrungapplication.data.Goal

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GoalDao {

    @Insert
    suspend fun addGoalInfo(goal: GoalEntity): Long

    @Query("SELECT * FROM goal_table ORDER BY id ASC")
    fun fetchAllGoalInfo(): List<GoalEntity>
}