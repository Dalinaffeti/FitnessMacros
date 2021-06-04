package com.example.myernaehrungapplication.data

import androidx.lifecycle.LiveData

class appRepository (private val userDao: UserDao){

    val readAllData: LiveData<List<User>> = userDao.readAllData()
    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
}