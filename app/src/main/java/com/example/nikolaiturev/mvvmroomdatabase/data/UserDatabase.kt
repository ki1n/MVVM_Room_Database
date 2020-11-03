package com.example.nikolaiturev.mvvmroomdatabase.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nikolaiturev.mvvmroomdatabase.model.User

// entities - сущности, exportSchema - схема экспорта
@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object{
        // @Volatile Помечает поле поддержки JVM аннотированного свойства как изменчивое, что означает,
        // что записи в это поле немедленно становятся видимыми для других потоков.
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            // синхронизация потоков
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}