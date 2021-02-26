package com.enigmacamp.mysimplerecyclerview.presentation.main

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.enigmacamp.mysimplerecyclerview.data.model.Item
import com.enigmacamp.mysimplerecyclerview.data.repository.ItemRepository
import com.enigmacamp.mysimplerecyclerview.databinding.ActivityItemUpdateBinding

class ItemUpdateActivity : AppCompatActivity() {
    lateinit var binding: ActivityItemUpdateBinding
    lateinit var viewModel: ItemUpdateActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemUpdateBinding.inflate(layoutInflater)
        binding.apply {
            setContentView(root)
            val itemForUpdate = intent.getParcelableExtra<Item>(MainActivity.ITEM_PARCEL)
            itemForUpdate?.apply {
                titleUpdateEditText.setText(itemForUpdate.title)
                descriptionUpdateEditText.setText(itemForUpdate.description)
                binding.updateButton.setOnClickListener {
                    val updatedItem = itemForUpdate.copy(
                        title = titleUpdateEditText.text.toString(),
                        description = descriptionUpdateEditText.text.toString()
                    )
                    itemForUpdate?.apply {
                        viewModel.onUpdate(updatedItem)
                    }
                }
            }

        }
        initViewModel()
        subscribe()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val repository = ItemRepository()
                return ItemUpdateActivityViewModel(repository) as T
            }
        }).get(ItemUpdateActivityViewModel::class.java)
    }

    private fun subscribe() {
        viewModel.updateStatus.observe(this, {
            val returnIntent = Intent()
            returnIntent.putExtra("result", it)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        })
    }
}