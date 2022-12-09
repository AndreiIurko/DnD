package com.andreyyurko.dnd.ui.characterabilities

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.andreyyurko.dnd.R
import dagger.hilt.android.AndroidEntryPoint
import by.kirich1409.viewbindingdelegate.viewBinding
import com.andreyyurko.dnd.databinding.FragmentCharacterAbilitiesBinding
import dev.chrisbanes.insetter.applyInsetter

@AndroidEntryPoint
class CharacterAbilitiesFragment : Fragment(R.layout.fragment_character_abilities) {

    private val viewBinding by viewBinding(FragmentCharacterAbilitiesBinding::bind)
    private lateinit var viewModel: CharacterAbilitiesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[CharacterAbilitiesViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAll()
    }

    private fun setupAll() {
        viewBinding.strTextView.text = viewModel.shownCharacter.characterInfo.strengthBonus.toString()
        viewBinding.dexTextView.text = viewModel.shownCharacter.characterInfo.dexterityBonus.toString()
        viewBinding.conTextView.text = viewModel.shownCharacter.characterInfo.constitutionBonus.toString()
        viewBinding.intTextView.text = viewModel.shownCharacter.characterInfo.intelligenceBonus.toString()
        viewBinding.wisTextView.text = viewModel.shownCharacter.characterInfo.wisdomBonus.toString()
        viewBinding.chaTextView.text = viewModel.shownCharacter.characterInfo.charismaBonus.toString()
    }
}