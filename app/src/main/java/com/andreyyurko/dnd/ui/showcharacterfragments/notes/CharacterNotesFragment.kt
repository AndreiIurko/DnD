package com.andreyyurko.dnd.ui.showcharacterfragments.notes

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.databinding.FragmentCharacterNotesBinding
import com.andreyyurko.dnd.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharacterNotesFragment : BaseFragment(R.layout.fragment_character_notes) {

    private val viewBinding by viewBinding(FragmentCharacterNotesBinding::bind)

    @Inject
    lateinit var notesViewModel: NotesViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.addNoteButton.setOnClickListener {
            notesViewModel.createNewNote()
            findNavController().navigate(R.id.characterCreateNoteFragment)
        }

        viewBinding.searchButton.setOnClickListener {
            setupRecyclerView()
        }

        viewBinding.cancelSearchButton.setOnClickListener {
            viewBinding.searchEditText.setText("")
            setupRecyclerView()
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val searchString = viewBinding.searchEditText.text.toString()
        val recyclerView = viewBinding.notesRecyclerView
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val adapter = NotesAdapter(notesViewModel, findNavController())
        adapter.searchString = searchString
        recyclerView.adapter = adapter

        adapter.apply {
            notes = notesViewModel.getNotes(searchString)
            notifyDataSetChanged()
        }
    }
}