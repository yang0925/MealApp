package com.example.mealapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MealsEntity::class], version = 1)
abstract class MealsDB: RoomDatabase(){
    abstract fun mealsDao() :MealsDao

    companion object{
        private var INSTANCE: MealsDB? = null

        fun getInstance(context: Context): MealsDB? {
            if(INSTANCE == null){
                synchronized(MealsDB::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            MealsDB::class.java, "meals.db")
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return INSTANCE
        }
    }

    fun destroyInstance() {
        INSTANCE = null
    }
}