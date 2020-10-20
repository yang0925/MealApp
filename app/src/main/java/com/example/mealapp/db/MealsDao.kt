package com.example.mealapp.db

import androidx.room.Dao
import androidx.room.Query


@Dao
interface MealsDao {
    @Query("SELECT * FROM meals")
    fun getAll(): Array<MealsEntity>
}