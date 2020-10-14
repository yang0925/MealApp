package com.example.mealapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.mealapp.data.MealData
import com.example.mealapp.network.RetrofitClient
import com.example.mealapp.network.Service
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class MainActivity : AppCompatActivity() {

    var schoolId : String = ""
    var opCode : String = ""

    lateinit var myAPI : Service
    lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("TAG", "들어옴")

        retrofit = RetrofitClient.getInstance()

        schoolId = intent.getStringExtra("schoolId").toString()
        opCode = intent.getStringExtra("opCode").toString()

        Log.d("TAG", schoolId)
        Log.d("TAG", opCode)

        setMealRetrofit()
    }

    private fun setMealRetrofit() {

        myAPI = retrofit.create(Service::class.java)
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
        myAPI.getMeal(school_id = schoolId, office_code = opCode, date = formatter.format(current)).enqueue(object : Callback<MealData> {
            override fun onFailure(call: Call<MealData>, t: Throwable) {
                Log.d("TAG", t.message.toString())
                Toast.makeText(applicationContext, "실패", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<MealData>, response: Response<MealData>) {
                Toast.makeText(applicationContext, "성공", Toast.LENGTH_SHORT).show()
                Log.d("TAG", response.body()!!.data.meals[2].replace("<br/>", "\n"))
                tv_success.text = response.body()!!.message
                tv_meals.text = response.body()!!.data.meals[2].replace("<br/>", "\n")
                tv_date.text = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일").format(current);
            }

        })
    }

}