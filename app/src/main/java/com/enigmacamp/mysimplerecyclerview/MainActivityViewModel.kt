package com.enigmacamp.mysimplerecyclerview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel(val repository: SimpleRepository) : ViewModel(), ItemClickListener {
    private var _itemLiveData = MutableLiveData<List<Item>>()
    val itemLiveData: LiveData<List<Item>>
        get() {
            return _itemLiveData
        }


    init {
        loadItemData()
    }

    fun loadItemData() {
        _itemLiveData.value = repository.list()
    }

    override fun onDelete(item: Item) {
        repository.delete(item)
        loadItemData()
    }

    fun onAddItem(title: String, description: String) {
        repository.add(Item(title, description))
        loadItemData()
    }
}