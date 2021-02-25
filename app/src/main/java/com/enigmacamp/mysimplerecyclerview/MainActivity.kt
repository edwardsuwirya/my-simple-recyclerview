package com.enigmacamp.mysimplerecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
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
                viewModel.onAddItem(titleEditText.text.toString(), descriptionEditText.text.toString())
            }
        }

        subscribe()
    }

    fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
    }

    fun subscribe() {
        viewModel.itemLiveData.observe(this, {
            rvAdapter.setData(it)
        })
    }
}