package com.example.nikolaiturev.mvvmroomdatabase.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

// Для передачи объектов следует использовать другой интерфейс Parcelable.
// http://developer.alexanderklimov.ru/android/theory/parcelable.php
@Parcelize
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int
) : Parcelable