package com.andreyyurko.dnd.ui.characterabilities

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.databinding.FragmentCharacterAbilitiesBinding
import com.andreyyurko.dnd.utils.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharacterAbilitiesFragment : Fragment(R.layout.fragment_character_abilities) {

    @Inject
    lateinit var characterViewModel: CharacterViewModel

    private val viewBinding by viewBinding(FragmentCharacterAbilitiesBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAll()
    }

    private fun setupAll() {
        viewBinding.strTextView.text = characterViewModel.shownCharacter.characterInfo.strength.toString()
        viewBinding.dexTextView.text = characterViewModel.shownCharacter.characterInfo.dexterity.toString()
        viewBinding.conTextView.text = characterViewModel.shownCharacter.characterInfo.constitution.toString()
        viewBinding.intTextView.text = characterViewModel.shownCharacter.characterInfo.intelligence.toString()
        viewBinding.wisTextView.text = characterViewModel.shownCharacter.characterInfo.wisdom.toString()
        viewBinding.chaTextView.text = characterViewModel.shownCharacter.characterInfo.charisma.toString()
    }
}