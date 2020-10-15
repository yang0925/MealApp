package com.example.mealapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mealapp.R
import com.example.mealapp.data.MealData
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

class BreakfastFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_breakfast, container, false)
    }
}