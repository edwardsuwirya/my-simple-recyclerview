package com.enigmacamp.mysimplerecyclerview

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.enigmacamp.mysimplerecyclerview.databinding.ItemLayoutBinding

class ItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val binding = ItemLayoutBinding.bind(view)

    fun bind(item: Item) {
        binding.apply {
            titleTextView.setText(item.title)
            descriptionTextView.setText((item.description))
            deleteButton.setOnClickListener {
                Log.d("Item", "delete")
            }
        }
    }
}