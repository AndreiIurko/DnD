package com.andreyyurko.dnd.ui.characterskills

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.databinding.FragmentCharacterSkillsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterSkillsFragment : Fragment(R.layout.fragment_character_skills) {

    private val viewBinding by viewBinding(FragmentCharacterSkillsBinding::bind)
    private lateinit var viewModel: CharacterSkillsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[CharacterSkillsViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val recyclerView = viewBinding.skillsRecyclerView
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = CharacterSkillsAdapter(viewModel)
        recyclerView.adapter = adapter
        adapter.apply {
            notifyDataSetChanged()
        }
    }


}