package com.example.mealapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mealapp.R
import com.example.mealapp.data.MealObject
import kotlinx.android.synthetic.main.fragment_breakfast.view.*

class BreakfastFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_breakfast, container, false)
        view.tv_breakfast.text = MealObject.breakfast
        return view
    }
}