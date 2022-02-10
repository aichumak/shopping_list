package com.example.shoppinglist.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R

class ShopItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val rvName = view.findViewById<TextView>(R.id.tv_name)
    val rvCount = view.findViewById<TextView>(R.id.tv_count)
}