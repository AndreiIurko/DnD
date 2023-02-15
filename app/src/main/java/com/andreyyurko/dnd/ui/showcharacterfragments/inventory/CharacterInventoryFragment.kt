package com.andreyyurko.dnd.ui.showcharacterfragments.inventory

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.data.characterData.character.Filter
import com.andreyyurko.dnd.databinding.FragmentCharacterInventoryBinding
import com.andreyyurko.dnd.ui.base.BaseFragment
import com.andreyyurko.dnd.ui.showcharacterfragments.fragmentwithfilters.FragmentWithFilters
import com.andreyyurko.dnd.utils.CharacterViewModel
import com.andreyyurko.dnd.utils.setupBasicPopUpMenu
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class CharacterInventoryFragment : FragmentWithFilters(R.layout.fragment_character_inventory) {

    @Inject
    lateinit var characterViewModel: CharacterViewModel

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

        viewBinding.filtersButton.setOnClickListener {
            if (viewBinding.filtersView.visibility == View.GONE) {
                showFilters(viewBinding.filtersView, viewBinding.filtersButton, viewBinding.searchEditText)
            }
            else {
                closeFilters(viewBinding.filtersView, viewBinding.filtersButton, viewBinding.searchEditText)
                showItems()
            }
        }

        viewBinding.rarityButton.setOnClickListener {
            setupFilter(viewBinding.rarityButton, viewModel.filters.rarity)
        }

        viewBinding.typeButton.setOnClickListener {
            setupFilter(viewBinding.typeButton, viewModel.filters.type)
        }

        viewBinding.sourceButton.setOnClickListener {
            setupFilter(viewBinding.sourceButton, viewModel.filters.source)
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = viewBinding.inventoryRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val adapter = InventoryAdapter(viewModel.inventoryHandler, viewModel.characterViewModel, viewBinding.popUpBackground)
        recyclerView.adapter = adapter

        val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(context?.let { ContextCompat.getDrawable(it, R.drawable.divider) }!!)
        recyclerView.addItemDecoration(itemDecorator)

        adapter.apply {
            itemsList = viewModel.showItems()
            notifyDataSetChanged()
        }
    }

    private fun showItems() {
        viewModel.filters.substring = viewBinding.searchEditText.text.toString()
        (viewBinding.inventoryRecyclerView.adapter as InventoryAdapter).apply {
            itemsList = viewModel.showItems()
            notifyDataSetChanged()
        }
    }
}