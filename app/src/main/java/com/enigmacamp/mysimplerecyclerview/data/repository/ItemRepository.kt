package com.enigmacamp.mysimplerecyclerview.data.repository

import android.util.Log
import com.enigmacamp.mysimplerecyclerview.data.model.Item
import java.util.*

class ItemRepository : SimpleRepository {
    companion object {
        var itemList = arrayListOf(Item("111", "Mas Egi", "Tak uuk"))
    }

    override fun add(item: Item): Item {
        if (item.id.isNullOrEmpty()) {
            val uuid = UUID.randomUUID().toString()
            val itemWithId = item.copy(id = uuid)
            itemList.add(itemWithId)
            return itemWithId
        } else {
            itemList.add(item)
            return item
        }
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