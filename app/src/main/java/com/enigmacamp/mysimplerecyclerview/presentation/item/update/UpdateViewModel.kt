package com.enigmacamp.mysimplerecyclerview.presentation.item.update

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enigmacamp.mysimplerecyclerview.data.model.Item
import com.enigmacamp.mysimplerecyclerview.data.repository.SimpleRepository

class UpdateViewModel(private val repository: SimpleRepository) : ViewModel() {
    private var _updateStatus = MutableLiveData<Item>()
    val updateStatus: LiveData<Item>
        get() {
            return _updateStatus
        }

    fun onUpdate(item: Item) {
        val itemUpdated = repository.update(item)
        _updateStatus.value = itemUpdated
    }

    fun onAddItem(title: String, description: String) {
        val itemUpdated = repository.add(Item(title = title, description = description))
        _updateStatus.value = itemUpdated
    }
}