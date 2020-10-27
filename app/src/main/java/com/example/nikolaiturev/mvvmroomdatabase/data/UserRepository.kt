package com.example.nikolaiturev.mvvmroomdatabase.data

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAddData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }


}