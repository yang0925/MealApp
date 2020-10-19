package com.example.mealapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MealsDao {
    @Query("SELECT * From meals")
    fun getAll(): List<MealsEntity>

    @Insert(onConflict = 0)
    fun insert(meals : MealsEntity)

    @Query("DELETE from meals")
    fun deleteAll()
}