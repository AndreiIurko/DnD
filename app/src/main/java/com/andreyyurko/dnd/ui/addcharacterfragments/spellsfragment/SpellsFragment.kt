package com.andreyyurko.dnd.ui.addcharacterfragments.spellsfragment

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.ui.showcharacterfragments.fragmentwithfilters.FragmentWithFilters
import by.kirich1409.viewbindingdelegate.viewBinding
import com.andreyyurko.dnd.databinding.FragmentSpellsBinding
import com.andreyyurko.dnd.ui.showcharacterfragments.spells.SpellsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpellsFragment: FragmentWithFilters(R.layout.fragment_spells) {
    private val viewBinding by viewBinding(FragmentSpellsBinding::bind)
    private lateinit var viewModel: SpellsViewModel
    private var isPopupBackgroundShown = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[SpellsViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewBinding.allButton.setOnClickListener {
            viewBinding.knownButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.on_primary))
            viewBinding.allButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.primary))
            viewModel.isKnownListShown = false
            showItems()
        }

        viewBinding.knownButton.setOnClickListener {
            viewBinding.allButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.on_primary))
            viewBinding.knownButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.primary))
            viewModel.isKnownListShown = true
            showItems()
        }

        viewBinding.knownSpellsCount.text = viewModel.getKnownSpellsCount().toString()
        viewBinding.maxSpellsCount.text = viewModel.getMaxKnownSpellsCount().toString()

        viewBinding.knownCantripsCount.text = viewModel.getKnownCantripsCount().toString()
        viewBinding.maxCantripsCount.text = viewModel.getMaxKnownCantripsCount().toString()

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

        viewBinding.submitButton.setOnClickListener {
            viewModel.updateCharacter()
            findNavController().popBackStack(R.id.charactersListFragment, false)
        }
        viewBinding.cancelButton.setOnClickListener {
            viewModel.deleteCharacter()
            findNavController().popBackStack(R.id.charactersListFragment, false)
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = viewBinding.spellsRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val adapter = SpellsAdapter(
            viewBinding.knownSpellsCount, viewBinding.knownCantripsCount,
            viewModel.getKnownSpells(), viewModel.getKnownCantrips(),
            viewBinding.root,
            { showPopupBackground() },
            { showPopupBackground() },
            { viewModel.updateCharacter() }
        )

        recyclerView.adapter = adapter

        val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(context?.let { ContextCompat.getDrawable(it, R.drawable.divider) }!!)
        recyclerView.addItemDecoration(itemDecorator)

        adapter.apply {
            shownSpellsList = viewModel.showAllSpells()
            notifyDataSetChanged()
        }
    }

    private fun showItems() {
        viewModel.getFilters().substring = viewBinding.searchEditText.text.toString()
        (viewBinding.spellsRecyclerView.adapter as SpellsAdapter).apply {
            shownSpellsList =
                if (viewModel.isKnownListShown) viewModel.showKnownSpells()
                else viewModel.showAllSpells()
            notifyDataSetChanged()
        }
    }

    fun showPopupBackground() {
        isPopupBackgroundShown = !isPopupBackgroundShown
        if (isPopupBackgroundShown) {
            val fadeOut = ObjectAnimator.ofFloat(viewBinding.popUpBackground, "alpha", 0f, .5f)
            fadeOut.duration = 300
            fadeOut.start()
        } else {
            val fadeIn = ObjectAnimator.ofFloat(viewBinding.popUpBackground, "alpha", 0.5f, 0f)
            fadeIn.duration = 300
            fadeIn.start()
        }
    }
}