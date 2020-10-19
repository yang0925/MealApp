package com.example.mealapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Meals")
data class MealsEntity (
    @PrimaryKey(autoGenerate = true) val id : Long,

)