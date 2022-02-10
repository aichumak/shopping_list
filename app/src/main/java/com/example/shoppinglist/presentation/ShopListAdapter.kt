package com.example.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.shoppinglist.R
import com.example.shoppinglist.domain.ShopItem

//class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {
class ShopListAdapter :
    androidx.recyclerview.widget.ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {

    companion object {
        const val VIEW_TYPE_ENABLED = 1
        const val VIEW_TYPE_DISABLED = 0
        const val MAX_POOL_SIZE = 15
    }

//    var shopList = listOf<ShopItem>()
//        set(value) {
//            val callBack = ShopListDiffUtilCallback(shopList, value)
//            val diffResult = DiffUtil.calculateDiff(callBack)
//            diffResult.dispatchUpdatesTo(this)
//            field = value
//            //notifyDataSetChanged()
//        }

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

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
        val shopItem = getItem(position)
        with(holder) {
            rvCount.text = shopItem.count.toString()
            rvName.text = shopItem.name
            itemView.setOnLongClickListener {
                onShopItemLongClickListener?.invoke(shopItem)
                true
            }
            itemView.setOnClickListener {
                onShopItemClickListener?.invoke(shopItem)
            }
        }
    }

//    override fun getItemCount(): Int {
//        return shopList.size
//    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).enable) VIEW_TYPE_ENABLED else VIEW_TYPE_DISABLED
    }
}