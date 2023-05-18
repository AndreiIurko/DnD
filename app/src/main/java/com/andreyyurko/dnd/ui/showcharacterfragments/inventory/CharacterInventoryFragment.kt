package com.andreyyurko.dnd.ui.showcharacterfragments.inventory

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.databinding.FragmentCharacterInventoryBinding
import com.andreyyurko.dnd.ui.showcharacterfragments.fragmentwithfilters.FragmentWithFilters
import com.google.android.material.color.MaterialColors
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CharacterInventoryFragment : FragmentWithFilters(R.layout.fragment_character_inventory) {

    lateinit var viewModel: CharacterInventoryViewModel

    private val viewBinding by viewBinding(FragmentCharacterInventoryBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[CharacterInventoryViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewBinding.searchButton.setOnClickListener {
            showItems()
        }

        viewBinding.allButton.setOnClickListener {
            viewBinding.allButton.setTextColor(
                MaterialColors.getColor(
                    requireContext(),
                    R.attr.colorPrimary,
                    Color.BLACK
                )
            )
            viewBinding.chosenButton.setTextColor(
                MaterialColors.getColor(
                    requireContext(),
                    R.attr.colorOnPrimary,
                    Color.BLACK
                )
            )
            viewModel.isChosenListShown = false
            showItems()
        }

        viewBinding.chosenButton.setOnClickListener {
            viewBinding.allButton.setTextColor(
                MaterialColors.getColor(
                    requireContext(),
                    R.attr.colorOnPrimary,
                    Color.BLACK
                )
            )
            viewBinding.chosenButton.setTextColor(
                MaterialColors.getColor(
                    requireContext(),
                    R.attr.colorPrimary,
                    Color.BLACK
                )
            )
            viewModel.isChosenListShown = true
            showItems()
        }

        viewBinding.filtersButton.setOnClickListener {
            if (viewBinding.filtersView.visibility == View.GONE) {
                showFilters(
                    viewBinding.filtersView,
                    viewBinding.filtersButton,
                    viewBinding.searchEditText
                )
            } else {
                closeFilters(
                    viewBinding.filtersView,
                    viewBinding.filtersButton,
                    viewBinding.searchEditText
                )
                showItems()
            }
        }

        viewBinding.rarityButton.setOnClickListener {
            setupFilter(viewBinding.rarityButton, viewModel.getFilters().rarity)
        }

        viewBinding.typeButton.setOnClickListener {
            setupFilter(viewBinding.typeButton, viewModel.getFilters().type)
        }

        viewBinding.sourceButton.setOnClickListener {
            setupFilter(viewBinding.sourceButton, viewModel.getFilters().source)
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = viewBinding.inventoryRecyclerView
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val adapter = InventoryAdapter(viewModel.inventoryHandler, viewModel.characterViewModel)
        recyclerView.adapter = adapter

        val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        AppCompatResources.getDrawable(requireContext(), R.drawable.divider)?.let {
            itemDecorator.setDrawable(it)
        }

        recyclerView.addItemDecoration(itemDecorator)

        adapter.apply {
            itemsList = viewModel.showItems()
            notifyDataSetChanged()
        }
    }

    private fun showItems() {
        viewModel.getFilters().substring = viewBinding.searchEditText.text.toString()
        (viewBinding.inventoryRecyclerView.adapter as InventoryAdapter).apply {
            itemsList = viewModel.showItems()
            notifyDataSetChanged()
        }
    }
}