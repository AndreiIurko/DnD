package com.andreyyurko.dnd.ui.addcharacterfragments.abilitiesfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.data.characterData.Ability
import com.andreyyurko.dnd.databinding.FragmentAbilitiesBinding
import com.andreyyurko.dnd.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AbilitiesFragment : BaseFragment(R.layout.fragment_abilities) {

    private val viewBinding by viewBinding(FragmentAbilitiesBinding::bind)

    private lateinit var viewModel: AbilitiesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[AbilitiesViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        for (ability in Ability.values()) {
            setupAbilityView(ability.abilityName)
        }

        val onChangePoints = Observer<Int> { state ->
            viewBinding.pointsCapacity.text = "Всего очков: $state"
        }
        viewModel.totalPoints.observe(viewLifecycleOwner, onChangePoints)

        viewBinding.nameEditText.setText(viewModel.getName())

        viewBinding.cancelButton.setOnClickListener {
            viewModel.deleteCharacter()
            findNavController().popBackStack()
        }

        viewBinding.submitButton.setOnClickListener {
            viewModel.characterInfo.strength = viewModel.abilities[Ability.Strength.abilityName]!!
            viewModel.characterInfo.dexterity = viewModel.abilities[Ability.Dexterity.abilityName]!!
            viewModel.characterInfo.constitution = viewModel.abilities[Ability.Constitution.abilityName]!!
            viewModel.characterInfo.intelligence = viewModel.abilities[Ability.Intelligence.abilityName]!!
            viewModel.characterInfo.wisdom = viewModel.abilities[Ability.Wisdom.abilityName]!!
            viewModel.characterInfo.charisma = viewModel.abilities[Ability.Charisma.abilityName]!!

            viewModel.setAbility(viewBinding.nameEditText.text.toString())
            findNavController().navigate(R.id.raceFragment)
        }
    }

    private fun setupAbilityView(abilityName: String) {
        val abilityView = LayoutInflater.from(context).inflate(R.layout.choose_ability_view, null)
        val abilityText = abilityView.findViewById<TextView>(R.id.abilityText)
        val score = abilityView.findViewById<TextView>(R.id.scoreText)
        val increaseButton = abilityView.findViewById<ImageButton>(R.id.increaseButton)
        val decreaseButton = abilityView.findViewById<ImageButton>(R.id.decreaseButton)

        abilityText.text = abilityName
        score.text = viewModel.abilities[abilityName].toString()

        increaseButton.setOnClickListener {
            val newValue = score.text.toString().toInt() + 1
            score.text = newValue.toString()
            viewModel.abilities[abilityName] = newValue
            viewModel.increaseAbility(newValue)
        }

        decreaseButton.setOnClickListener {
            val newValue = score.text.toString().toInt() - 1
            score.text = newValue.toString()
            viewModel.abilities[abilityName] = newValue
            viewModel.decreaseAbility(newValue)
        }

        val changeValueObserver = Observer<Int> { state ->
            val currentValue = score.text.toString().toInt()
            if (currentValue == 8) {
                decreaseButton.isEnabled = false
                decreaseButton.alpha = 0.6f
            } else {
                decreaseButton.isEnabled = true
                decreaseButton.alpha = 1f
            }

            if (currentValue == 15 || currentValue >= 13 && state < 2 || state == 0) {
                increaseButton.isEnabled = false
                increaseButton.alpha = 0.6f
            } else {
                increaseButton.isEnabled = true
                increaseButton.alpha = 1f
            }
        }
        viewModel.totalPoints.observe(viewLifecycleOwner, changeValueObserver)

        viewBinding.abilitiesLinearLayout.addView(abilityView)
    }
}