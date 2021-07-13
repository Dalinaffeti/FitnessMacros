package com.example.myernaehrungapplication.data.Personal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myernaehrungapplication.data.appDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonalViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PersonalRepository

    init{
        val personalDao = appDatabase.getDatabase(application).personalDao()
        repository = PersonalRepository(personalDao)
    }
    //coroutines to run the code in a background thread
    fun addPersonalInfo(info: PersonalEntity) : LiveData<Long>{
        val result = MutableLiveData<Long>()
        viewModelScope.launch(Dispatchers.IO) {
            val insertedValue = repository.addPersonalInfo(info)
            result.postValue(insertedValue)
        }
        return result
    }

    fun fetchAllPersonalInfo(): LiveData<List<PersonalEntity>> {
        val result = MutableLiveData<List<PersonalEntity>>()
        viewModelScope.launch(Dispatchers.IO) {
            val userValue = repository.fetchAllPersonalData()
            result.postValue(userValue)
        }
        return result
    }

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