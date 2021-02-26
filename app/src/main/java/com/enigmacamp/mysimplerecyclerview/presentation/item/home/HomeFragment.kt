package com.enigmacamp.mysimplerecyclerview.presentation.item.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.enigmacamp.mysimplerecyclerview.R
import com.enigmacamp.mysimplerecyclerview.data.repository.ItemRepository
import com.enigmacamp.mysimplerecyclerview.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var rvAdapter: ItemViewAdapter
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        subscribe()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(inflater)
        binding.apply {
            viewModel.loadItemData()
            rvAdapter = ItemViewAdapter(viewModel)
            itemRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = rvAdapter
            }
            addButton.setOnClickListener {
                viewModel.onAddItem(
                    titleEditText.text.toString(),
                    descriptionEditText.text.toString()
                )
            }
        }

        return binding.root
    }

    fun initViewModel() {
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val repo = ItemRepository()
                return HomeViewModel(repo) as T
            }

        }).get(HomeViewModel::class.java)
    }

    fun subscribe() {
        viewModel.itemLiveData.observe(this, {
            rvAdapter.setData(it)
        })
        viewModel.itemUpdateLiveData.observe(this, {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_homeFragment_to_updateFragment, bundleOf("item_update" to it))
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}