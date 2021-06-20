package com.example.myernaehrungapplication.fragments.goal

import androidx.lifecycle.ViewModel

class GoalViewModel(weight: Int, height: Int, gender: String, age: Int) : ViewModel() {
    var WeightData = weight
    var HeightData = height
    var GenderData = gender
    var AgeData = age

}