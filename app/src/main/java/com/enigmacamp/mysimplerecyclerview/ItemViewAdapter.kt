package com.enigmacamp.mysimplerecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ItemViewAdapter(val itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<ItemViewHolder>() {

    var items = ArrayList<Item>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(itemView, itemClickListener)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount() = items.size

    fun setData(newItemList: List<Item>) {
        items.clear()
        items.addAll(newItemList)
        notifyDataSetChanged()
    }
}