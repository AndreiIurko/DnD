package com.andreyyurko.dnd.ui.showcharacterfragments.spells

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.data.spells.Spell
import com.andreyyurko.dnd.databinding.FragmentCharacterSpellsBinding
import com.andreyyurko.dnd.ui.showcharacterfragments.fragmentwithfilters.FragmentWithFilters
import com.google.android.material.color.MaterialColors
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterSpellsFragment : FragmentWithFilters(R.layout.fragment_character_spells) {

    private val viewBinding by viewBinding(FragmentCharacterSpellsBinding::bind)
    private lateinit var viewModel: CharacterSpellsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[CharacterSpellsViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        if (viewModel.isAllPrepared()) {
            viewBinding.changeListsButton.visibility = View.GONE
            viewBinding.spellsCountContainer.visibility = View.GONE
            viewBinding.cantripsCountContainer.visibility = View.GONE
        } else {
            viewBinding.knownButton.setOnClickListener {
                viewBinding.preparedButton.setTextColor(MaterialColors.getColor(requireContext(), R.attr.colorOnPrimary, Color.BLACK))
                viewBinding.knownButton.setTextColor(MaterialColors.getColor(requireContext(), R.attr.colorPrimary, Color.BLACK))
                viewModel.isPreparedListShown = false
                showItems()
            }

            viewBinding.preparedButton.setOnClickListener {
                viewBinding.knownButton.setTextColor(MaterialColors.getColor(requireContext(), R.attr.colorOnPrimary, Color.BLACK))
                viewBinding.preparedButton.setTextColor(MaterialColors.getColor(requireContext(), R.attr.colorPrimary, Color.BLACK))
                viewModel.isPreparedListShown = true
                showItems()
            }

            viewBinding.preparedSpellsCount.text = viewModel.getPreparedSpellsCount().toString()
            viewBinding.maxSpellsCount.text = viewModel.getMaxPreparedSpellsCount().toString()

            viewBinding.preparedCantripsCount.text = viewModel.getPreparedCantripsCount().toString()
            viewBinding.maxCantripsCount.text = viewModel.getMaxPreparedCantripsCount().toString()
        }

        viewBinding.searchButton.setOnClickListener {
            showItems()
        }

        viewBinding.filtersButton.setOnClickListener {
            if (viewBinding.filtersView.visibility == View.GONE) {
                showFilters(viewBinding.filtersView, viewBinding.filtersButton, viewBinding.searchEditText)
            } else {
                closeFilters(viewBinding.filtersView, viewBinding.filtersButton, viewBinding.searchEditText)
                showItems()
            }
        }

        viewBinding.levelButton.setOnClickListener {
            setupStringFilter(viewBinding.levelButton, viewModel.getFilters().levels, (0..9).map { i -> i.toString() })
        }

        viewBinding.sourceButton.setOnClickListener {
            setupFilter(viewBinding.sourceButton, viewModel.getFilters().source)
        }

        viewBinding.schoolButton.setOnClickListener {
            setupFilter(viewBinding.schoolButton, viewModel.getFilters().school)
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = viewBinding.spellsRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val adapter = SpellsAdapter(
            viewBinding.preparedSpellsCount, viewBinding.preparedCantripsCount,
            viewBinding.root,
            { viewModel.characterViewModel.showPopUpBackground() },
            { viewModel.characterViewModel.closePopUpBackground() },
            { spell: Spell -> viewModel.addPreparedSpell(spell) },
            { spell: Spell -> viewModel.removePreparedSpell(spell) },
            { viewModel.showPreparedSpells() }
        )

        recyclerView.adapter = adapter

        val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        AppCompatResources.getDrawable(requireContext(), R.drawable.divider)?.let {
            itemDecorator.setDrawable(it)
        }
        recyclerView.addItemDecoration(itemDecorator)

        adapter.apply {
            shownSpellsList = viewModel.showPreparedSpells().toMutableList()
            notifyDataSetChanged()
        }
    }

    private fun showItems() {
        viewModel.getFilters().substring = viewBinding.searchEditText.text.toString()
        (viewBinding.spellsRecyclerView.adapter as SpellsAdapter).apply {
            shownSpellsList =
                if (viewModel.isPreparedListShown) viewModel.showPreparedSpells()
                else viewModel.showKnownSpells()
            notifyDataSetChanged()
        }
    }
}