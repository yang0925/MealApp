package com.example.mealapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.mealapp.R
import com.example.mealapp.adapter.ViewPagerAdapter
import com.example.mealapp.data.MealData
import com.example.mealapp.data.MealObject
import com.example.mealapp.fragment.BreakfastFragment
import com.example.mealapp.fragment.DinnerFragment
import com.example.mealapp.fragment.LunchFragment
import com.example.mealapp.network.RetrofitClient
import com.example.mealapp.network.Service
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class MainActivity : AppCompatActivity(){

    var schoolId : String = ""
    var opCode : String = ""

    lateinit var myAPI : Service
    lateinit var retrofit: Retrofit

    lateinit var adapter : ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Log.d("TAG", "들어옴")

        retrofit = RetrofitClient.getInstance()

        schoolId = intent.getStringExtra("schoolId").toString()
        opCode = intent.getStringExtra("opCode").toString()

        Log.d("TAG", schoolId)
        Log.d("TAG", opCode)

        setUpTabs()
        setMealsRetrofit()

    }

    private fun setUpTabs() {
        adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(BreakfastFragment(), "아침")
        adapter.addFragment(LunchFragment(), "점심")
        adapter.addFragment(DinnerFragment(), "저녁")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }

    private fun setMealsRetrofit() {

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
                val list = response.body()!!.data.meals

                MealObject.breakfast = list[0]?.replace("<br/>", "\n") ?: "급식이 없습니다"
                MealObject.lunch = list[1]?.replace("<br/>", "\n") ?: "급식이 없습니다"
                MealObject.dinner = list[2]?.replace("<br/>", "\n") ?: "급식이 없습니다"
                print(MealObject.lunch)
                adapter.notifyDataSetChanged()
            }

        })
    }
}