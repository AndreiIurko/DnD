package com.andreyyurko.dnd.ui.showcharacterfragments.inventory

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.data.characterData.Armor
import com.andreyyurko.dnd.databinding.FragmentCharacterInventoryBinding
import com.andreyyurko.dnd.ui.showcharacterfragments.skills.CharacterSkillsAdapter
import com.andreyyurko.dnd.utils.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharacterInventoryFragment : Fragment(R.layout.fragment_character_inventory) {

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
}