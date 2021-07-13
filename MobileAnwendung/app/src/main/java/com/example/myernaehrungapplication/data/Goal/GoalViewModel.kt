package com.example.myernaehrungapplication.data.Goal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myernaehrungapplication.data.appDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GoalViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: GoalRepository

    init {
        val goalDao = appDatabase.getDatabase(application).goalDao()
        repository = GoalRepository(goalDao)
    }

    //coroutines to run the code in a background thread
    fun addGoalInfo(goal: GoalEntity): LiveData<Long> {
        val result = MutableLiveData<Long>()
        viewModelScope.launch(Dispatchers.IO) {
            val insertedValue = repository.addGoalInfo(goal)
            result.postValue(insertedValue)
        }
        return result
    }

    fun fetchAllGoalInfo(): LiveData<List<GoalEntity>> {
        val result = MutableLiveData<List<GoalEntity>>()
        viewModelScope.launch(Dispatchers.IO) {
            val userValue = repository.fetchAllGoalInfo()
            result.postValue(userValue)
        }
        return result
    }

}