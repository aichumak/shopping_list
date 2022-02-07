package com.example.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.domain.ShopItem

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    companion object {
        const val VIEW_TYPE_ENABLED = 1
        const val VIEW_TYPE_DISABLED = 0
        const val MAX_POOL_SIZE = 15
    }

    var shopList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layoutType = when (viewType) {
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown ViewType $viewType")
        }
        val itemView = LayoutInflater.from(parent.context).inflate(layoutType, parent, false)

        return ShopItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = shopList[position]
        with(holder) {
            rvCount.text = shopItem.count.toString()
            rvName.text = shopItem.name
            itemView.setOnLongClickListener() {
                true
            }
        }
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (shopList[position].enable) VIEW_TYPE_ENABLED else VIEW_TYPE_DISABLED
    }

    class ShopItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val rvName = view.findViewById<TextView>(R.id.tv_name)
        val rvCount = view.findViewById<TextView>(R.id.tv_count)
    }
}