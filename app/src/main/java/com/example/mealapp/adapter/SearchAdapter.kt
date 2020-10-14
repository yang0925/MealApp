package com.example.mealapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mealapp.MainActivity
import com.example.mealapp.R
import com.example.mealapp.data.Schools

class SearchAdapter(val searchList: ArrayList<Schools>) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    override fun onBindViewHolder(holder: SearchAdapter.ViewHolder, position: Int) {
        holder.bind(searchList[position])

    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val school_name = itemView.findViewById<TextView>(R.id.tv_school_name)

        fun bind(item: Schools)
        {
            school_name.text = item.school_name


            itemView.setOnClickListener {
                itemView.context.startActivity(Intent(itemView.context, MainActivity::class.java).apply {
                    putExtra("schoolId", item.school_id)
                    putExtra("opCode", item.office_code)
                })
            }
        }
    }
}