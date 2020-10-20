package com.example.mealapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MealsEntity::class], version = 1)
abstract class MealsDatabase: RoomDatabase() {
    abstract fun mealsDao(): MealsDao

    companion object {
        private var Instance: MealsDatabase? = null

        fun getInstance(context: Context): MealsDatabase? {
            if(Instance == null) {
                synchronized(MealsDatabase::class) {
                    Instance = Room.databaseBuilder(context.applicationContext,
                            MealsDatabase::class.java, "meals.db")
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return Instance
        }
        fun destroyInstance() {
            Instance = null
        }
    }
}