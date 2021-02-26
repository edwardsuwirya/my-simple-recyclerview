package com.enigmacamp.mysimplerecyclerview.presentation.main

import com.enigmacamp.mysimplerecyclerview.data.model.Item

interface ItemClickListener {
    fun onDelete(item: Item)
    fun onUpdate(item: Item)
}