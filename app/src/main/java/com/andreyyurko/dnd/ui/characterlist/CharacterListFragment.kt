package com.andreyyurko.dnd.ui.characterlist

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.databinding.FragmentCharacterListBinding
import com.andreyyurko.dnd.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.insetter.applyInsetter

@AndroidEntryPoint
class CharacterListFragment : BaseFragment(R.layout.fragment_character_list) {

    private val viewBinding by viewBinding(FragmentCharacterListBinding::bind)
    private lateinit var viewModel: CharacterListViewModel

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

        viewBinding.addImageButton.applyInsetter {
            type(statusBars = true) { margin() }
        }

        viewBinding.addImageButton.setOnClickListener {
            viewModel.createNewCharacter()
            val controller = findNavController()
            controller.navigate(R.id.action_charactersListFragment_to_character_creation_nav_grapg)
        }

        viewBinding.arrowBackImageButton.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun setupRecyclerView() {
        val recyclerView = viewBinding.charactersRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = CharacterListAdapter(viewModel, ::showDialog)
        recyclerView.adapter = adapter
        adapter.apply {
            viewModel.updateBriefInfo()
            charactersList = viewModel.charactersBriefInfo
            notifyDataSetChanged()
        }
        //recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
    }
}