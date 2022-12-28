package com.andreyyurko.dnd.ui.addcharacterfragments.abilitiesfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.databinding.FragmentAbilitiesBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.andreyyurko.dnd.data.characters.Ability
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

        for (ability in Ability.values()) {
            setupAbilityView(ability.abilityName)
        }

        val onChangePoints = Observer<Int> {state ->
            viewBinding.pointsCapacity.text = "Всего очков: $state"
        }
        viewModel.totalPoints.observe(viewLifecycleOwner, onChangePoints)

        viewBinding.cancelButton.setOnClickListener {
            viewModel.deleteCharacter()
            findNavController().popBackStack()
        }

        viewBinding.submitButton.setOnClickListener {
            viewModel.characterInfo.hp = viewBinding.hitPointsEditText.text.toString().toInt()
            viewModel.characterInfo.speed = viewBinding.speedEditText.text.toString().toInt()
            viewModel.characterInfo.race = viewBinding.raceEditText.text.toString()

            viewModel.characterInfo.strengthBonus = viewModel.abilities[Ability.Strength.abilityName]!!
            viewModel.characterInfo.dexterityBonus = viewModel.abilities[Ability.Dexterity.abilityName]!!
            viewModel.characterInfo.constitutionBonus = viewModel.abilities[Ability.Constitution.abilityName]!!
            viewModel.characterInfo.intelligenceBonus = viewModel.abilities[Ability.Intelligence.abilityName]!!
            viewModel.characterInfo.wisdomBonus = viewModel.abilities[Ability.Wisdom.abilityName]!!
            viewModel.characterInfo.charismaBonus = viewModel.abilities[Ability.Charisma.abilityName]!!

            viewModel.setAbility(viewBinding.nameEditText.text.toString())
            findNavController().navigate(R.id.classFragment)
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
            Log.d("Ability test", "here")
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

        val changeValueObserver = Observer<Int> {state ->
            val currentValue = score.text.toString().toInt()
            if (currentValue == 8) {
                decreaseButton.isEnabled = false
                decreaseButton.alpha = 0.6f
            }
            else if (currentValue == 15 || currentValue >= 13 && state < 2 || state == 0) {
                increaseButton.isEnabled = false
                increaseButton.alpha = 0.6f
            }
            else {
                decreaseButton.isEnabled = true
                decreaseButton.alpha = 1f
                increaseButton.isEnabled = true
                increaseButton.alpha = 1f
            }
        }
        viewModel.totalPoints.observe(viewLifecycleOwner, changeValueObserver)

        viewBinding.abilitiesLinearLayout.addView(abilityView)
    }
}