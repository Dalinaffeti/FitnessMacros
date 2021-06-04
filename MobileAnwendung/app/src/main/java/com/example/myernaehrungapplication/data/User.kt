package com.example.myernaehrungapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val email: String,
    val password: String,
    val firstname: String,
    val lastnamename: String,
    val goal: String,
    val weight: String,
    val height: String,
    val sex: Char,
    val age: Int,
    val job: String,


)

