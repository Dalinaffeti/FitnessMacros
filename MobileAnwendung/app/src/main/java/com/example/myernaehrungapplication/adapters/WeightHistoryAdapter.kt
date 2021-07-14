package com.example.myernaehrungapplication.adapters

import android.content.Context
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myernaehrungapplication.R
import com.example.myernaehrungapplication.data.Personal.PersonalEntity
import kotlinx.android.synthetic.main.item_weight_history.view.*
import java.text.SimpleDateFormat
import java.util.*


class WeightHistoryAdapter(private val weightList: List<PersonalEntity>):
                RecyclerView.Adapter<WeightHistoryAdapter.CustomViewHolder>() {

    lateinit var context: Context
    val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvHeight : TextView = itemView.weight
        val tvCreatedAt : TextView = itemView.createdAt
        val tvMonth : TextView = itemView.month
        val tvDate : TextView = itemView.date
        val tvDay : TextView = itemView.day
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        context = parent.context
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_weight_history, parent, false)
        return CustomViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val currentItem = weightList[position]
        holder.tvHeight.text = "Weight : ${currentItem.weight}"
        holder.tvCreatedAt.text = currentItem.createdAt

        val date: Date = format.parse(currentItem.createdAt)
        holder.tvDate.text = DateFormat.format("dd", date)
        holder.tvMonth.text = DateFormat.format("MMM", date)
        holder.tvDay.text = DateFormat.format("EEE", date)
    }

    override fun getItemCount(): Int {
        return weightList.size
    }
}
