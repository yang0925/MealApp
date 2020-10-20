package com.example.mealapp.data

data class MealData (
    val status : Int,
    val message : String,
    val data : MealsSubData
)
data class MealsSubData (
    val meals : ArrayList<String?>
)