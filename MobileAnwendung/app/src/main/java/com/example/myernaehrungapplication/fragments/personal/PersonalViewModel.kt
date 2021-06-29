package com.example.myernaehrungapplication.fragments.personal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PersonalViewModel : ViewModel() {



    private val _weight = MutableLiveData<Int>()
    val weight: LiveData<Int>
        get() = _weight


    private  val _height = MutableLiveData<Int>()
    val height: LiveData<Int>
        get() = _height

    private  val _age = MutableLiveData<Int>()
    val age: LiveData<Int>
        get() = _age

    private val _sexe = MutableLiveData<String>()
    val sexe: LiveData<String>
        get() = _sexe
    init {
        _weight.value = 80
        _height.value = 180

    }
    //weight in-, decrementation
    fun onIncreaseWeight() {
        _weight.value = (_weight.value)?.plus(1)
    }

    fun onDecreaseWeight() {
        _weight.value = (_weight.value)?.minus(1)
    }

    //height in-, decrementation
    fun onIncreaseHeight() {
        _height.value = (_height.value)?.plus(1)
    }

    fun onDecreaseHeight() {
        _height.value = (_height.value)?.minus(1)
    }

}