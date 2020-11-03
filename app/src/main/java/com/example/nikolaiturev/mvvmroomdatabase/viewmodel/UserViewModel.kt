package com.example.nikolaiturev.mvvmroomdatabase.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.nikolaiturev.mvvmroomdatabase.data.UserDatabase
import com.example.nikolaiturev.mvvmroomdatabase.redository.UserRepository
import com.example.nikolaiturev.mvvmroomdatabase.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<User>>
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

     fun addUser(user: User) {
        // Dispatchers.IO используется для фоновых задач, не блокирующих основной поток
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }


}