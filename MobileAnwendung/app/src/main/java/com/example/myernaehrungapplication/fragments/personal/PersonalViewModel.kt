package com.example.myernaehrungapplication.fragments.personal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PersonalViewModel : ViewModel() {
    private val _weight = MutableLiveData<Int>()
    val weight: LiveData<Int>
        get() = _weight
    private  val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word
    init {
        _weight.value = 80

    }
    fun onIncrease() {
        _weight.value = (_weight.value)?.plus(1)
    }

    fun onDecrease() {
        _weight.value = (_weight.value)?.minus(1)
    }

}