package com.example.myernaehrungapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myernaehrungapplication.data.Goal.GoalDao
import com.example.myernaehrungapplication.data.Goal.GoalEntity
import com.example.myernaehrungapplication.data.Nutrition.NutritionDao
import com.example.myernaehrungapplication.data.Nutrition.NutritionEntity
import com.example.myernaehrungapplication.data.Personal.PersonalDao
import com.example.myernaehrungapplication.data.Personal.PersonalEntity
import com.example.myernaehrungapplication.data.User.User
import com.example.myernaehrungapplication.data.User.UserDao

@Database(entities = [User::class, PersonalEntity::class, GoalEntity::class, NutritionEntity::class], version = 1, exportSchema = false)
abstract class appDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun personalDao(): PersonalDao
    abstract fun goalDao(): GoalDao
    abstract fun nutritionDao(): NutritionDao

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
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }

    }

}