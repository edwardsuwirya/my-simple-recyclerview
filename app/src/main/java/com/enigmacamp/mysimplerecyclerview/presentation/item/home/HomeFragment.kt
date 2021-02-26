package com.enigmacamp.mysimplerecyclerview.presentation.item.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.enigmacamp.mysimplerecyclerview.R
import com.enigmacamp.mysimplerecyclerview.data.model.Item
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
        arguments?.let {
            val itemUpdate = it.getParcelable<Item>("result")
            itemUpdate?.let {
                Toast.makeText(requireContext(), "Success update ${it.title}", Toast.LENGTH_LONG)
                    .show()
            }
        }
        binding = FragmentHomeBinding.inflate(inflater)
        binding.apply {
            viewModel.loadItemData()
            rvAdapter = ItemViewAdapter(viewModel)
            itemRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = rvAdapter
            }

            addButton.setOnClickListener {
                Navigation.findNavController(requireView()).navigate(
                    R.id.action_homeFragment_to_updateFragment,
                    bundleOf("update_action" to "ADD")
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
                .navigate(
                    R.id.action_homeFragment_to_updateFragment,
                    bundleOf("item_update" to it, "update_action" to "UPDATE")
                )
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        arguments?.clear()
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}