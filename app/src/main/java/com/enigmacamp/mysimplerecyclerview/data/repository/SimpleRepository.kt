package com.enigmacamp.mysimplerecyclerview.data.repository

import com.enigmacamp.mysimplerecyclerview.data.model.Item

interface SimpleRepository {
    fun add(item: Item): Item
    fun delete(item: Item): Boolean
    fun list(): List<Item>
    fun update(item: Item): Item
}