package com.example.myernaehrungapplication.data.User

import androidx.lifecycle.LiveData

class userRepository (private val userDao: UserDao){

    val readAllData: LiveData<List<User>> = userDao.readAllData()
    suspend fun addUser(user: User) : Long{
        return userDao.addUser(user)
    }
    suspend fun isValidAccount(username: String, password: String): User? {
        val userAccount = userDao.getUser(username)
        if(userAccount != null && userAccount.password == password){
            return userAccount
        }
        return null
    }
}