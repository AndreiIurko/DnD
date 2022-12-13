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
import com.andreyyurko.dnd.utils.CharacterViewModel
import dev.chrisbanes.insetter.applyInsetter
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
        viewBinding.strTextView.text = characterViewModel.shownCharacter.characterInfo.strengthBonus.toString()
        viewBinding.dexTextView.text = characterViewModel.shownCharacter.characterInfo.dexterityBonus.toString()
        viewBinding.conTextView.text = characterViewModel.shownCharacter.characterInfo.constitutionBonus.toString()
        viewBinding.intTextView.text = characterViewModel.shownCharacter.characterInfo.intelligenceBonus.toString()
        viewBinding.wisTextView.text = characterViewModel.shownCharacter.characterInfo.wisdomBonus.toString()
        viewBinding.chaTextView.text = characterViewModel.shownCharacter.characterInfo.charismaBonus.toString()
    }
}