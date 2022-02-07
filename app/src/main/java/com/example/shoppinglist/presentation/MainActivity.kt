package com.example.shoppinglist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglist.R
import com.example.shoppinglist.domain.ShopItem

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var llShopList: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        llShopList = findViewById(R.id.ll_shop_list)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            showList(it)
        }
    }

    private fun showList(list: List<ShopItem>) {
        llShopList.removeAllViews()
        for (shopItem in list) {
            val itemView =
                LayoutInflater.from(this).inflate(getLayoutId(shopItem), llShopList, false)
            val tvName = itemView.findViewById<TextView>(R.id.tv_name)
            val tvCount = itemView.findViewById<TextView>(R.id.tv_count)
            tvName.text = shopItem.name
            tvCount.text = shopItem.count.toString()
            itemView.setOnLongClickListener() {
                viewModel.editShopItem(shopItem)
                true
            }
            llShopList.addView(itemView)
        }
    }

    private fun getLayoutId(shopItem: ShopItem) = if (shopItem.enable) {
        R.layout.item_shop_enabled
    } else {
        R.layout.item_shop_disabled
    }
}