package com.example.myernaehrungapplication.data.Goal

class GoalRepository (private val goalDao: GoalDao){

    suspend fun addGoalInfo(goal: GoalEntity) : Long{
        return goalDao.addGoalInfo(goal)
    }
    suspend fun fetchAllGoalInfo(): List<GoalEntity> {
        return goalDao.fetchAllGoalInfo()
    }
}