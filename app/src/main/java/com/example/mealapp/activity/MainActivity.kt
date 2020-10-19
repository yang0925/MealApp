package com.example.mealapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.mealapp.R
import com.example.mealapp.adapter.ViewPagerAdapter
import com.example.mealapp.data.MealData
import com.example.mealapp.fragment.BreakfastFragment
import com.example.mealapp.fragment.DinnerFragment
import com.example.mealapp.fragment.LunchFragment
import com.example.mealapp.network.RetrofitClient
import com.example.mealapp.network.Service
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_breakfast.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class MainActivity : AppCompatActivity(){

    val pref = SearchActivity().getPreferences(0)
    val editor = pref.edit()

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

        setUpTabs()
        setMealsRetrofit()

    }

    private fun setUpTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(BreakfastFragment(), "아침")
        adapter.addFragment(LunchFragment(), "점심")
        adapter.addFragment(DinnerFragment(), "저녁")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        //tabs.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_brightness_medium_24)
        //tabs.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_brightness_high_24)
        //tabs.getTabAt(2)!!.setIcon(R.drawable.ic_baseline_brightness_3_24)
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
                Log.d("TAG", response.body()!!.data.meals[0].replace("<br/>", "\n"))
                tv_breakfast.text = response.body()!!.data.meals[0].replace("<br/>", "\n")
                //tv_date.text = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일").format(current);
            }

        })
    }
}