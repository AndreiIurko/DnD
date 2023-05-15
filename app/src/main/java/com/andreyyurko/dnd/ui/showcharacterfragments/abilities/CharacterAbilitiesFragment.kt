package com.andreyyurko.dnd.ui.showcharacterfragments.abilities

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.databinding.FragmentCharacterAbilitiesBinding
import com.andreyyurko.dnd.ui.base.BaseFragment
import com.andreyyurko.dnd.utils.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharacterAbilitiesFragment : BaseFragment(R.layout.fragment_character_abilities) {

    @Inject
    lateinit var characterViewModel: CharacterViewModel

    private val viewBinding by viewBinding(FragmentCharacterAbilitiesBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAll()
    }

    private fun setupAll() {
        viewBinding.strTextView.text = characterViewModel.getCharacter().characterInfo.strength.toString()
        viewBinding.dexTextView.text = characterViewModel.getCharacter().characterInfo.dexterity.toString()
        viewBinding.conTextView.text = characterViewModel.getCharacter().characterInfo.constitution.toString()
        viewBinding.intTextView.text = characterViewModel.getCharacter().characterInfo.intelligence.toString()
        viewBinding.wisTextView.text = characterViewModel.getCharacter().characterInfo.wisdom.toString()
        viewBinding.chaTextView.text = characterViewModel.getCharacter().characterInfo.charisma.toString()
        viewBinding.passiveInsight.text = characterViewModel.getCharacter().characterInfo.passiveInsightBonus.toString()
        viewBinding.passivePerception.text =
            characterViewModel.getCharacter().characterInfo.passivePerceptionBonus.toString()
    }
}