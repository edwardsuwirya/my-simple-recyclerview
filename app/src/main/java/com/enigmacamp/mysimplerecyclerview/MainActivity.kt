package com.enigmacamp.mysimplerecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.enigmacamp.mysimplerecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var rvAdapter: ItemViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.apply {
            setContentView(root)
            rvAdapter = ItemViewAdapter()
            rvAdapter.setData(listOf(Item("ABC", "123")))
            itemRecyclerView.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = rvAdapter
            }
        }
    }
}