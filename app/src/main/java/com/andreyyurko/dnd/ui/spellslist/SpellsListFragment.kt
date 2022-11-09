package com.andreyyurko.dnd.ui.spellslist

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.databinding.FragmentSpellsListBinding
import com.andreyyurko.dnd.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.insetter.applyInsetter
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SpellsListFragment : BaseFragment(R.layout.fragment_spells_list) {

    companion object {
        const val LOG_TAG = "SpellsListFragment"
    }

    private val viewBinding by viewBinding(FragmentSpellsListBinding::bind)

    private lateinit var viewModel: SpellsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[SpellsListViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.searchEditText.applyInsetter {
            type(statusBars = true) { margin() }
        }

        setupRecyclerView()

        viewModel.parseSpells(context)
        viewModel.getFavoriteSpells()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loadSpellsActionState.collect {viewState ->
                    renderViewState(viewState)
                }
            }
        }

        viewBinding.searchButton.setOnClickListener {
            (viewBinding.spellsRecyclerView.adapter as SpellsListAdapter).apply {
                viewModel.setShownSpellList(viewBinding.searchEditText.text.toString(), this)
            }
        }

        viewBinding.menuButton.setOnClickListener {
            setupPopupMenu()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.saveFavoriteSpells()
    }

    private fun setupRecyclerView() {
        val recyclerView = viewBinding.spellsRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = SpellsListAdapter(viewModel.spellsFavoritesHolder)
        adapter.viewModel = viewModel
        recyclerView.adapter = adapter
        //recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
    }

    private fun renderViewState(viewState: SpellsListViewModel.LoadSpellsActionState) {
        when(viewState) {
            is SpellsListViewModel.LoadSpellsActionState.Loading -> {
                // TODO: реализовать отображение загрузки
            }
            is SpellsListViewModel.LoadSpellsActionState.Ok -> {
                (viewBinding.spellsRecyclerView.adapter as SpellsListAdapter).apply {
                    //spellsList = viewModel.allSpells
                    //notifyDataSetChanged()
                    viewModel.setShownSpellList("", this)
                }
            }
            is SpellsListViewModel.LoadSpellsActionState.Error -> {
                // TODO: реализовать показание ошибки
            }
        }
    }

    private fun setupPopupMenu() {
        val filtersMenuView = layoutInflater.inflate(R.layout.view_filters_for_spell_list, null)
        val wid = LinearLayout.LayoutParams.WRAP_CONTENT
        val high = LinearLayout.LayoutParams.WRAP_CONTENT
        val focus = true
        val filtersMenu = PopupWindow(filtersMenuView, wid, high, focus)
        val showFavoritesButton = filtersMenuView.findViewById<TextView>(R.id.show_favorites_button)
        when (viewModel.shownList) {
            SpellsListViewModel.SpellListState.All -> {
                showFavoritesButton.text = "Показать избранные"
            }
            SpellsListViewModel.SpellListState.Favorites -> {
                showFavoritesButton.text = "Показать все"
            }
        }
        filtersMenu.showAtLocation(view, Gravity.NO_GRAVITY, viewBinding.menuButton.x.toInt() - 350, viewBinding.menuButton.y.toInt() + 100)
        showFavoritesButton.setOnClickListener {
            when (viewModel.shownList) {
                SpellsListViewModel.SpellListState.All -> {
                    viewModel.shownList = SpellsListViewModel.SpellListState.Favorites
                }
                SpellsListViewModel.SpellListState.Favorites -> {
                    viewModel.shownList = SpellsListViewModel.SpellListState.All
                }
            }
            viewModel.setShownSpellList("", viewBinding.spellsRecyclerView.adapter as SpellsListAdapter)
            filtersMenu.dismiss()
        }
    }
}