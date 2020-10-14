package com.example.mealapp.network


import com.example.mealapp.data.MealData
import com.example.mealapp.data.SearchData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface Service {
    @GET("/search")
    fun getSchool(
        @Query("school_name") school_name : String,
        @Query("page") page : Int
    ) : Call<SearchData>

    @GET("/meals")
    fun getMeal(
        @Query("school_id") school_id : String,
        @Query("office_code") office_code : String,
        @Query("date") date : String
    ) : Call<MealData>
}