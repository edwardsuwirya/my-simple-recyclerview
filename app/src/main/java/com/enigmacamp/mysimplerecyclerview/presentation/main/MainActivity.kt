package com.enigmacamp.mysimplerecyclerview.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.enigmacamp.mysimplerecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.apply {
            setContentView(root)
        }
    }
}