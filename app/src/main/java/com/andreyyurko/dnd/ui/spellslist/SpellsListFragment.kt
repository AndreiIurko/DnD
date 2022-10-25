package com.andreyyurko.dnd.ui.spellslist

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.ui.base.BaseFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.andreyyurko.dnd.databinding.FragmentSpellsListBinding
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

        viewBinding.spellsRecyclerView.applyInsetter {
            type(statusBars = true) { margin() }
        }

        setupRecyclerView()

        viewModel.parseSpells(context)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loadSpellsActionState.collect {viewState ->
                    renderViewState(viewState)
                }
            }
        }

        viewBinding.searchButton.setOnClickListener {
            (viewBinding.spellsRecyclerView.adapter as SpellsListAdapter).apply {
                setShownSpellList(viewBinding.searchEditText.text.toString())
                notifyDataSetChanged()
            }
        }
    }

    private fun setupRecyclerView(): SpellsListAdapter {
        val recyclerView = viewBinding.spellsRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = SpellsListAdapter()
        recyclerView.adapter = adapter
        //recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        return adapter
    }

    private fun renderViewState(viewState: SpellsListViewModel.LoadSpellsActionState) {
        when(viewState) {
            is SpellsListViewModel.LoadSpellsActionState.Loading -> {
                // TODO: реализовать отображение загрузки
            }
            is SpellsListViewModel.LoadSpellsActionState.Data -> {
                (viewBinding.spellsRecyclerView.adapter as SpellsListAdapter).apply {
                    spellsList = viewState.spells
                    setShownSpellList()
                    notifyDataSetChanged()
                }
            }
            is SpellsListViewModel.LoadSpellsActionState.Error -> {
                // TODO: реализовать показание ошибки
            }
        }
    }
}