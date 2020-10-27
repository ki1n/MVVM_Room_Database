package com.example.nikolaiturev.mvvmroomdatabase.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    // Аннотация Insert - это простой способ вставить объект в базу данных.
    // Режимы вставки
    //Рассмотрим ситуацию, когда мы вставляем в таблицу запись, но обнаруживается,
    // что запись с таким ключом там уже есть. По умолчанию мы получим ошибку:
    // SQLiteConstraintException: UNIQUE constraint failed. И ничего в базу не запишется.
    //Но это можно поменять с помощью параметра onConflict.
    // suspend - https://kotlinlang.ru/docs/reference/coroutines.html

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    // ORDER BY сортировка на базе выборки SELECT * FROM user_table, ASC в порядке возрастания
    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAddData(): LiveData<List<User>>



}