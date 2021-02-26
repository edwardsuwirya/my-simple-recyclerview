package com.enigmacamp.mysimplerecyclerview.presentation.main

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.enigmacamp.mysimplerecyclerview.data.model.Item
import com.enigmacamp.mysimplerecyclerview.data.repository.ItemRepository
import com.enigmacamp.mysimplerecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var rvAdapter: ItemViewAdapter
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        initViewModel()
        binding.apply {
            setContentView(root)
            rvAdapter = ItemViewAdapter(viewModel)

            itemRecyclerView.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = rvAdapter
            }

            addButton.setOnClickListener {
                viewModel.onAddItem(
                    titleEditText.text.toString(),
                    descriptionEditText.text.toString()
                )
            }
        }

        subscribe()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == UPDATE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                viewModel.loadItemData()
                val itemUpdated = data?.getParcelableExtra<Item>("result")
                Toast.makeText(this, "Success update ${itemUpdated!!.title}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun initViewModel() {
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val repo = ItemRepository()
                return MainActivityViewModel(repo) as T
            }

        }).get(MainActivityViewModel::class.java)
    }

    fun subscribe() {
        viewModel.itemLiveData.observe(this, {
            rvAdapter.setData(it)
        })
        viewModel.itemUpdateLiveData.observe(this, {
            val intent = Intent(this, ItemUpdateActivity::class.java)
            intent.putExtra(ITEM_PARCEL, it)
            startActivityForResult(intent, UPDATE_REQUEST_CODE)
        })
    }

    companion object {
        val UPDATE_REQUEST_CODE = 1111
        val ITEM_PARCEL = "ITEM_PARCEL"
    }
}