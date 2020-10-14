package com.example.mealapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mealapp.adapter.SearchAdapter
import com.example.mealapp.data.Schools
import com.example.mealapp.data.SearchData
import com.example.mealapp.network.RetrofitClient
import com.example.mealapp.network.Service
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class SearchActivity : AppCompatActivity() {

    lateinit var myAPI : Service
    lateinit var retrofit: Retrofit
    lateinit var searchList : ArrayList<Schools>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        retrofit = RetrofitClient.getInstance()

        searchRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
        btn_search.setOnClickListener {
            getSchool()
        }
    }

    fun getSchool() {
        myAPI = retrofit.create(Service::class.java)
        myAPI.getSchool(school_name = et_search.text.toString(), page = 1).enqueue(object : Callback<SearchData> {
            override fun onResponse(call: Call<SearchData>, response: Response<SearchData>) {
                if(response.code() == 200)
                {
                    searchList = response.body()?.data?.schools as ArrayList<Schools>
                    val mAdapter = SearchAdapter(searchList)
                    searchRecyclerView.setHasFixedSize(true)
                    searchRecyclerView.adapter = mAdapter
                }
            }

            override fun onFailure(call: Call<SearchData>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}

