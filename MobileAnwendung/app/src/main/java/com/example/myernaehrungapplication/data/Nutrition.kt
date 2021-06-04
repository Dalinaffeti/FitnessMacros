package com.example.myernaehrungapplication.data

import androidx.room.Entity

@Entity(tableName = "nutrition_table")
data class Nutrition (

    val proteinPercentage: Float,
    val carbsPercentage: Float,
    val fatPercentage: Float

)