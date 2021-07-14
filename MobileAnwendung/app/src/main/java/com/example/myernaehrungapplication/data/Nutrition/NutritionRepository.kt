package com.example.myernaehrungapplication.data.Nutrition

class NutritionRepository (private val nutritionDao: NutritionDao){

    suspend fun addNutritionInfo(nutrition: NutritionEntity) : Long{
        return nutritionDao.addNutritionInfo(nutrition)
    }
    suspend fun fetchAllNutritionInfo(): List<NutritionEntity> {
        return nutritionDao.fetchAllNutritionInfo()
    }
}