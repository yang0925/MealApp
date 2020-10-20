package com.example.mealapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mealapp.data.MealData

@Entity(tableName = "meals")
class MealsEntity (
    @PrimaryKey(autoGenerate = true) var id: Long,
    @ColumnInfo(name = "meal") var meal: ArrayList<MealData>? = ArrayList()
)