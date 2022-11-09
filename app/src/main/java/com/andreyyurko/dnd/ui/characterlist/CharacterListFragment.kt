package com.andreyyurko.dnd.ui.characterlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.databinding.FragmentCharacterListBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.insetter.applyInsetter

@AndroidEntryPoint
class CharacterListFragment : Fragment(R.layout.fragment_character_list) {

    private val viewBinding by viewBinding(FragmentCharacterListBinding::bind)
    private lateinit var viewModel : CharacterListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[CharacterListViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        viewBinding.charactersTextView.applyInsetter {
            type(statusBars = true) { margin() }
        }
        viewBinding.arrowBackImageButton.applyInsetter {
            type(statusBars = true) { margin() }
        }

    }

    private fun setupRecyclerView() {
        val recyclerView = viewBinding.charactersRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = CharacterListAdapter()
        recyclerView.adapter = adapter
        adapter.apply {
            charactersList = viewModel.charactersBriefInfo
            notifyDataSetChanged()
        }
        //recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
    }
}