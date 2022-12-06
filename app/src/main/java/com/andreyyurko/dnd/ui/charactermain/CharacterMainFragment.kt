package com.andreyyurko.dnd.ui.charactermain

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.databinding.FragmentCharacterMainBinding
import dagger.hilt.android.AndroidEntryPoint
import by.kirich1409.viewbindingdelegate.viewBinding
import dev.chrisbanes.insetter.applyInsetter

@AndroidEntryPoint
class CharacterMainFragment : Fragment(R.layout.fragment_character_main) {

    private val viewBinding by viewBinding(FragmentCharacterMainBinding::bind)
    private lateinit var viewModel: CharacterMainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[CharacterMainViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.arrowBackImageButton.applyInsetter {
            type(statusBars = true) { margin() }
        }

        viewBinding.nameTextView.applyInsetter {
            type(statusBars = true) { margin() }
        }

        viewBinding.menuButton.applyInsetter {
            type(statusBars = true) { margin() }
        }

        viewBinding.settingsButton.applyInsetter {
            type(statusBars = true) { margin() }
        }

        setupAll()

        viewBinding.arrowBackImageButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupAll() {
        viewBinding.nameTextView.text = viewModel.shownCharacter.name
        viewBinding.classTextView.text = viewModel.shownCharacter.characterInfo.characterClass
        viewBinding.levelTextView.text = viewModel.shownCharacter.characterInfo.level.toString()
        viewBinding.hpEditText.setText(viewModel.shownCharacter.characterInfo.hp.toString())
        viewBinding.proficiencyTextView.text = "+ ${viewModel.shownCharacter.characterInfo.proficiencyBonus}"
        viewBinding.speedTextView.text = "${viewModel.shownCharacter.characterInfo.speed}ft"
        viewBinding.initiativeTextView.text = "+ ${viewModel.shownCharacter.characterInfo.initiativeBonus(viewModel.shownCharacter.characterInfo)}"
        viewBinding.acTextView.text = viewModel.shownCharacter.characterInfo.ac(viewModel.shownCharacter.characterInfo).toString()
        viewBinding.strTextView.text = viewModel.shownCharacter.characterInfo.strengthBonus.toString()
        viewBinding.dexTextView.text = viewModel.shownCharacter.characterInfo.dexterityBonus.toString()
        viewBinding.conTextView.text = viewModel.shownCharacter.characterInfo.constitutionBonus.toString()
        viewBinding.intTextView.text = viewModel.shownCharacter.characterInfo.intelligenceBonus.toString()
        viewBinding.wisTextView.text = viewModel.shownCharacter.characterInfo.wisdomBonus.toString()
        viewBinding.chaTextView.text = viewModel.shownCharacter.characterInfo.charismaBonus.toString()
    }
}