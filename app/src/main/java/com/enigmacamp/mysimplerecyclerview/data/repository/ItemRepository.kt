package com.enigmacamp.mysimplerecyclerview.data.repository

import android.util.Log
import com.enigmacamp.mysimplerecyclerview.data.model.Item
import java.util.*
import kotlin.collections.ArrayList

class ItemRepository : SimpleRepository {
    companion object {
        var itemList = arrayListOf(Item("111", "Mas Egi", "Tak Uuk"))
    }

    override fun add(item: Item): Item {
        val uuid = UUID.randomUUID().toString()
        val itemWithId = item.copy(id = uuid)
        itemList.add(itemWithId)
        return itemWithId
    }

    override fun delete(item: Item): Boolean {
        val itemPos = itemList.indexOf(item)
        itemList.removeAt(itemPos)
        return true
    }

    override fun list(): List<Item> = itemList

    override fun update(item: Item): Item {
        var oldItem = itemList.find {
            it.id == item.id
        }
        oldItem?.let {
            delete(it)
            add(item)
        }
        Log.d("Repo", itemList.toString())
        return item
    }

}