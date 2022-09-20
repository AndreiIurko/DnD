package com.andreyyurko.dnd.ui.spellslist

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.ui.base.BaseFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.andreyyurko.dnd.databinding.FragmentSpellsListBinding
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.insetter.applyInsetter

@AndroidEntryPoint
class SpellsListFragment : BaseFragment(R.layout.fragment_spells_list) {
    private val viewBinding by viewBinding(FragmentSpellsListBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.spellsRecyclerView.applyInsetter {
            type(statusBars = true) { margin() }
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView(): SpellsListAdapter {
        val recyclerView = viewBinding.spellsRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = SpellsListAdapter()
        recyclerView.adapter = adapter
        //recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        return adapter
    }
}