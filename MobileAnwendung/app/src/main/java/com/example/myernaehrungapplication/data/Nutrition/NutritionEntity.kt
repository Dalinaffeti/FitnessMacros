package com.example.myernaehrungapplication.data.Nutrition

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nutrition_table")
data class NutritionEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val proteinPercentage: Float,
    val carbsPercentage: Float,
    val fatPercentage: Float

)