package com.example.myernaehrungapplication.data.User

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myernaehrungapplication.data.appDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val readAllData: LiveData<List<User>>
    private val repository: userRepository

    init{
        val userDao = appDatabase.getDatabase(application).userDao()
        repository = userRepository(userDao)
        readAllData = repository.readAllData
    }
    //coroutines to run the code in a background thread
    fun addUser(user: User) : LiveData<Long>{
        val result = MutableLiveData<Long>()
        viewModelScope.launch(Dispatchers.IO) {
            val insertedValue = repository.addUser(user)
            result.postValue(insertedValue)
        }
        return result
    }

    fun checkValidLogin(username: String, password: String): LiveData<User> {
        val result = MutableLiveData<User>()
        viewModelScope.launch(Dispatchers.IO) {
            val userValue = repository.isValidAccount(username, password)
            result.postValue(userValue)
        }
        return result
    }

}