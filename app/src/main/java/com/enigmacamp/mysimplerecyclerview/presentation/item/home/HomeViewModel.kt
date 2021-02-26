package com.enigmacamp.mysimplerecyclerview.presentation.item.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enigmacamp.mysimplerecyclerview.data.model.Item
import com.enigmacamp.mysimplerecyclerview.data.repository.SimpleRepository
import com.enigmacamp.mysimplerecyclerview.presentation.main.ItemClickListener

class HomeViewModel(private val repository: SimpleRepository) : ViewModel(), ItemClickListener {
    private var _itemLiveData = MutableLiveData<List<Item>>()
    val itemLiveData: LiveData<List<Item>>
        get() {
            return _itemLiveData
        }


    private var _itemUpdateLiveData = MutableLiveData<Item>()
    val itemUpdateLiveData: LiveData<Item>
        get() {
            return _itemUpdateLiveData
        }


    fun loadItemData() {
        _itemLiveData.value = repository.list()
    }

    override fun onDelete(item: Item) {
        repository.delete(item)
        loadItemData()
    }

    override fun onUpdate(item: Item) {
        _itemUpdateLiveData.value = item
    }

}