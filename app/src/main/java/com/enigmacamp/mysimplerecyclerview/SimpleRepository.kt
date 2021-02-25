package com.enigmacamp.mysimplerecyclerview

interface SimpleRepository {
    fun add(item: Item)
    fun delete(item: Item)
    fun list(): List<Item>
}