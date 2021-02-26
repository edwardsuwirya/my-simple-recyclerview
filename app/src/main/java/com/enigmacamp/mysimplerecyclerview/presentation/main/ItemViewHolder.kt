package com.enigmacamp.mysimplerecyclerview.presentation.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.enigmacamp.mysimplerecyclerview.data.model.Item
import com.enigmacamp.mysimplerecyclerview.databinding.ItemLayoutBinding

class ItemViewHolder(view: View, val clickListener: ItemClickListener) :
    RecyclerView.ViewHolder(view) {
    val binding = ItemLayoutBinding.bind(view)

    fun bind(item: Item) {
        binding.apply {
            titleTextView.setText(item.title)
            descriptionTextView.setText((item.description))
            deleteButton.setOnClickListener {
                clickListener.onDelete(item)
            }
            titleTextView.setOnClickListener {
                clickListener.onUpdate(item)
            }
        }
    }
}