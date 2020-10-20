package com.example.mealapp.data

import com.google.gson.annotations.SerializedName

data class MealData (
    val status : Int,
    val message : String,
    val data : MealsSubData
)
data class MealsSubData (
    val meals : ArrayList<String?>
)