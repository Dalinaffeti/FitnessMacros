package com.example.myernaehrungapplication.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val readAllData: LiveData<List<User>>
    private val repository: appRepository

    init{
        val userDao = appDatabase.getDatabase(application).userDao()
        repository = appRepository(userDao)
        readAllData = repository.readAllData
    }
    //coroutines to run the code in a background thread
    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

}