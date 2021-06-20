package com.example.myernaehrungapplication.fragments.goal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class GoalViewModelFactory (private val weight: Int, private val height: Int, private val gender: String, private val age: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(GoalViewModel::class.java)) {
            return GoalViewModel(weight, height, gender, age) as T
        }
        throw  IllegalArgumentException("Unknown ViewModel Class")
    }
}