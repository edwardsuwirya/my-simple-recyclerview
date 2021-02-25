package com.enigmacamp.mysimplerecyclerview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    private var _itemLiveData = MutableLiveData<List<Item>>()
    val itemLiveData: LiveData<List<Item>>
        get() {
            return _itemLiveData
        }

    init {
        loadItemData()
    }

    fun loadItemData() {
        _itemLiveData.value = listOf(Item("ABC", "123"))
    }
}