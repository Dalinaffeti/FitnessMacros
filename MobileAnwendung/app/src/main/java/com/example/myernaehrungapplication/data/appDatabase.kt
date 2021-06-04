package com.example.myernaehrungapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class appDatabase: RoomDatabase() {
    abstract  fun userDao(): UserDao

    companion object{


        //Singelton
        @Volatile
        private var INSTANCE: appDatabase? = null

        fun getDatabase(context: Context): appDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    appDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }

}