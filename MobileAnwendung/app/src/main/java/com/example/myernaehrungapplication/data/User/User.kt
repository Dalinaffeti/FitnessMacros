package com.example.myernaehrungapplication.data.User

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "user_table", indices = [Index(value = ["email"], unique = true)])
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val email: String,
    val password: String,
    val firstname: String,
    val lastnamename: String,



)

