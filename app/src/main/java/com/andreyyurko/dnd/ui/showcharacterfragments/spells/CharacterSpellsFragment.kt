package com.andreyyurko.dnd.ui.showcharacterfragments.spells

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.databinding.FragmentCharacterSpellsBinding
import com.andreyyurko.dnd.ui.base.BaseFragment
import com.andreyyurko.dnd.ui.showcharacterfragments.fragmentwithfilters.FragmentWithFilters
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterSpellsFragment : FragmentWithFilters(R.layout.fragment_character_spells) {

    private val viewBinding by viewBinding(FragmentCharacterSpellsBinding::bind)
    private lateinit var viewModel: CharacterSpellsViewModel

    private var isPreparedListShown = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[CharacterSpellsViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewBinding.knownButton.setOnClickListener {
            viewModel.filters.substring = viewBinding.searchEditText.text.toString()
            viewBinding.preparedButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.on_primary))
            viewBinding.knownButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.primary))
            (viewBinding.spellsRecyclerView.adapter as SpellsAdapter).apply {
                spellsList = viewModel.showKnownSpells()
                notifyDataSetChanged()
            }
            isPreparedListShown = false
        }

        viewBinding.preparedButton.setOnClickListener {
            viewModel.filters.substring = viewBinding.searchEditText.text.toString()
            viewBinding.knownButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.on_primary))
            viewBinding.preparedButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.primary))
            (viewBinding.spellsRecyclerView.adapter as SpellsAdapter).apply {
                spellsList = viewModel.showPreparedSpells()
                notifyDataSetChanged()
            }
            isPreparedListShown = true
        }

        viewBinding.preparedSpellsCount.text = viewModel.getPreparedSpellsCount().toString()
        viewBinding.maxSpellsCount.text = viewModel.getMaxPreparedSpellsCount().toString()

        viewBinding.preparedCantripsCount.text = viewModel.getPreparedCantripsCount().toString()
        viewBinding.maxCantripsCount.text = viewModel.getMaxPreparedCantripsCount().toString()

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

        viewBinding.levelButton.setOnClickListener {
            setupStringFilter(viewBinding.levelButton, viewModel.filters.levels, (0..9).map {i -> i.toString()})
        }

        viewBinding.sourceButton.setOnClickListener {
            setupFilter(viewBinding.sourceButton, viewModel.filters.source)
        }

        viewBinding.schoolButton.setOnClickListener {
            setupFilter(viewBinding.schoolButton, viewModel.filters.school)
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = viewBinding.spellsRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val adapter = SpellsAdapter(viewModel.spellsHandler, viewModel.characterViewModel, viewBinding.preparedSpellsCount, viewBinding.preparedCantripsCount)
        recyclerView.adapter = adapter

        val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(context?.let { ContextCompat.getDrawable(it, R.drawable.divider) }!!)
        recyclerView.addItemDecoration(itemDecorator)

        adapter.apply {
            spellsList = viewModel.showPreparedSpells().toMutableList()
            notifyDataSetChanged()
        }
    }

    private fun showItems() {
        viewModel.filters.substring = viewBinding.searchEditText.text.toString()
        (viewBinding.spellsRecyclerView.adapter as SpellsAdapter).apply {
            spellsList =
                if (isPreparedListShown) viewModel.showPreparedSpells()
                else viewModel.showKnownSpells().toMutableList()
            notifyDataSetChanged()
        }
    }
}