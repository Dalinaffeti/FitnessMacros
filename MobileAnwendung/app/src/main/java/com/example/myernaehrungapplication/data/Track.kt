package com.example.myernaehrungapplication.data

import androidx.room.Entity
import java.util.*

@Entity(tableName = "tracking_table")
data class Track (

    val startDate: Date,
    val currentDate: Date,
    val weeksNumber: Int,
    val achievedWeight: Float,
    val latestCalories: Int
)