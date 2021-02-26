package com.enigmacamp.mysimplerecyclerview.presentation.item.update

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
import com.enigmacamp.mysimplerecyclerview.R
import com.enigmacamp.mysimplerecyclerview.data.model.Item
import com.enigmacamp.mysimplerecyclerview.data.repository.ItemRepository
import com.enigmacamp.mysimplerecyclerview.databinding.FragmentUpdateBinding


/**
 * A simple [Fragment] subclass.
 * Use the [UpdateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UpdateFragment : Fragment() {
    private var itemUpdate: Item? = null
    private var updateAction: String? = null

    private lateinit var binding: FragmentUpdateBinding
    private lateinit var viewModel: UpdateViewModel
    private var updatedItem: Item? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            itemUpdate = it.getParcelable<Item>("item_update")
            updateAction = it.getString("update_action", "")
        }
        initViewModel()
        subscribe()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(inflater)
        binding.apply {
            cancelButton.setOnClickListener {
                Navigation.findNavController(requireView()).popBackStack()
            }

            itemUpdate?.apply {
                titleUpdateEditText.editText?.setText(title)
                descriptionUpdateEditText.editText?.setText(description)
            }
            updateButton.setOnClickListener {
                when (updateAction) {
                    "ADD" -> {
                        viewModel.onAddItem(
                            titleUpdateEditText.editText?.text.toString(),
                            descriptionUpdateEditText.editText?.text.toString()
                        )
                    }
                    "UPDATE" -> {
                        itemUpdate?.apply {
                            val updatedItem = copy(
                                title = titleUpdateEditText.editText?.text.toString(),
                                description = descriptionUpdateEditText.editText?.text.toString()
                            )
                            viewModel.onUpdate(updatedItem)
                        }
                    }
                }

            }
        }
        return binding.root
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val repository = ItemRepository()
                return UpdateViewModel(repository) as T
            }
        }).get(UpdateViewModel::class.java)
    }

    private fun subscribe() {
        viewModel.updateStatus.observe(this, {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_updateFragment_to_homeFragment, bundleOf("result" to it))
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = UpdateFragment()
    }
}