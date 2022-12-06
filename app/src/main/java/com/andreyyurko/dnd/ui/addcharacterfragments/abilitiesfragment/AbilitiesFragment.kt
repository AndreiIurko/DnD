package com.andreyyurko.dnd.ui.addcharacterfragments.abilitiesfragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.databinding.FragmentAbilitiesBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AbilitiesFragment : Fragment(R.layout.fragment_abilities) {

    private val viewBinding by viewBinding(FragmentAbilitiesBinding::bind)

    private lateinit var viewModel: AbilitiesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[AbilitiesViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.cancelButton.setOnClickListener {
            viewModel.deleteCharacter()
            findNavController().popBackStack()
        }

        viewBinding.submitButton.setOnClickListener {
            /*viewModel.characterInfo.ac = viewBinding.acEditText.text.toString().toInt()
            viewModel.characterInfo.hp = viewBinding.hitPointsEditText.text.toString().toInt()
            viewModel.characterInfo.speed = viewBinding.speedEditText.text.toString().toInt()
            viewModel.characterInfo.initiativeBonus = viewBinding.initiativeEditText.text.toString().toInt()
            viewModel.characterInfo.strengthBonus = viewBinding.strEditText.text.toString().toInt()
            viewModel.characterInfo.dexterityBonus = viewBinding.dexEditText.text.toString().toInt()
            viewModel.characterInfo.constitutionBonus = viewBinding.conEditText.text.toString().toInt()
            viewModel.characterInfo.intelligenceBonus = viewBinding.intEditText.text.toString().toInt()
            viewModel.characterInfo.wisdomBonus = viewBinding.wisEditText.text.toString().toInt()
            viewModel.characterInfo.charismaBonus = viewBinding.chaEditText.text.toString().toInt()
            viewModel.characterInfo.race = viewBinding.raceEditText.text.toString()*/
            viewModel.setAbility(viewBinding.nameEditText.text.toString())
            findNavController().navigate(R.id.classFragment)
        }
    }
}