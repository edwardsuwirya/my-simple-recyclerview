package com.enigmacamp.mysimplerecyclerview

class ItemRepository : SimpleRepository {
    companion object {
        var itemList = arrayListOf(Item("ABC", "123"), Item("XYZ", "000"), Item("DEF", "111"))
    }

    override fun add(item: Item) {
        itemList.add(item)
    }

    override fun delete(item: Item) {
        val itemPos = itemList.indexOf(item)
        itemList.removeAt(itemPos)
    }

    override fun list(): List<Item> = itemList

}