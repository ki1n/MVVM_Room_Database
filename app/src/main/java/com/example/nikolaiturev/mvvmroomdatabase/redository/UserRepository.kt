package com.example.nikolaiturev.mvvmroomdatabase.redository

import androidx.lifecycle.LiveData
import com.example.nikolaiturev.mvvmroomdatabase.data.UserDao
import com.example.nikolaiturev.mvvmroomdatabase.model.User

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAddData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }
}