package com.example.myernaehrungapplication.data.Goal

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "goal_table")
data class GoalEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val goalType: String,
    val kgProWoche: Float,
    val kalorien: Double

)