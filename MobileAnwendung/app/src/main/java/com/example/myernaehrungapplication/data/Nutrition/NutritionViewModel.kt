package com.example.myernaehrungapplication.data.Nutrition

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myernaehrungapplication.data.appDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NutritionViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NutritionRepository

    init {
        val nutritionDao = appDatabase.getDatabase(application).nutritionDao()
        repository = NutritionRepository(nutritionDao)
    }

    //coroutines to run the code in a background thread
    fun addNutritionInfo(nutrition: NutritionEntity): LiveData<Long> {
        val result = MutableLiveData<Long>()
        viewModelScope.launch(Dispatchers.IO) {
            val insertedValue = repository.addNutritionInfo(nutrition)
            result.postValue(insertedValue)
        }
        return result
    }

    fun fetchAllNutritionInfo(): LiveData<List<NutritionEntity>> {
        val result = MutableLiveData<List<NutritionEntity>>()
        viewModelScope.launch(Dispatchers.IO) {
            val userValue = repository.fetchAllNutritionInfo()
            result.postValue(userValue)
        }
        return result
    }

}