package com.example.myernaehrungapplication.data.Personal

import androidx.lifecycle.LiveData

class PersonalRepository (private val personalDao: PersonalDao){

    suspend fun addPersonalInfo(personalInfo: PersonalEntity) : Long{
        return personalDao.addPersonalInfo(personalInfo)
    }
    suspend fun fetchAllPersonalData(): List<PersonalEntity> {
        return personalDao.fetchAllPersonalInfo()
    }
}