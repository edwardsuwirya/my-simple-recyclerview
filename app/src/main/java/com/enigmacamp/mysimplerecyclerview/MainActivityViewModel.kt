package com.enigmacamp.mysimplerecyclerview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel(), ItemClickListener {
    private var _itemLiveData = MutableLiveData<List<Item>>()
    val itemLiveData: LiveData<List<Item>>
        get() {
            return _itemLiveData
        }

    var itemList = arrayListOf<Item>(Item("ABC", "123"), Item("XYZ", "000"), Item("DEF", "111"))

    init {
        loadItemData()
    }

    fun loadItemData() {
        _itemLiveData.value = itemList
    }

    override fun onDelete(item: Item) {
        val itemPos = itemList.indexOf(item)
        itemList.removeAt(itemPos)
        Log.d("ViewModel", itemList.toString())
        _itemLiveData.value = itemList
    }
}