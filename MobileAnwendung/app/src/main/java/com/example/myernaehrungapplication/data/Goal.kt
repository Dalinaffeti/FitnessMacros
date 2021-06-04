package com.example.myernaehrungapplication.data

import androidx.room.Entity

@Entity(tableName = "goal_table")
data class Goal (

    val goalType: String,
    val kgProWoche: Float,
    val kalorien: Int

)