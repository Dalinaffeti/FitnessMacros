package com.example.myernaehrungapplication.data.Personal

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "personal_table")
data class PersonalEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val goal: String,
    val weight: Int,
    val height: Int,
    val gender: String,
    val age: Int,
    val createdAt: String,
)

