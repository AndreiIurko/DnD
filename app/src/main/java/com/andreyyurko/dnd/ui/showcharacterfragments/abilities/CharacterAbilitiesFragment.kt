package com.andreyyurko.dnd.ui.showcharacterfragments.abilities

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import by.kirich1409.viewbindingdelegate.viewBinding
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.data.characterData.Classes
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
        val characterInfo = characterViewModel.getCharacter().characterInfo
        viewBinding.strTextView.text = characterInfo.strength.toString()
        viewBinding.dexTextView.text = characterInfo.dexterity.toString()
        viewBinding.conTextView.text = characterInfo.constitution.toString()
        viewBinding.intTextView.text = characterInfo.intelligence.toString()
        viewBinding.wisTextView.text = characterInfo.wisdom.toString()
        viewBinding.chaTextView.text = characterInfo.charisma.toString()
        viewBinding.passiveInsight.text = characterInfo.passiveInsightBonus.toString()
        viewBinding.passivePerception.text = characterInfo.passivePerceptionBonus.toString()

        viewBinding.allHitDiceTextView.text =
            characterInfo.level.toString() + getHitDice(characterInfo.characterClass)
        if (characterInfo.currentState.hitDiceCount == "")
            characterInfo.currentState.hitDiceCount = characterInfo.level.toString()
        viewBinding.currentHitDiceTextView.setText(characterInfo.currentState.hitDiceCount)
        viewBinding.currentHitDiceTextView.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewBinding.currentHitDiceTextView.clearFocus()
                characterInfo.currentState.hitDiceCount =
                    viewBinding.currentHitDiceTextView.text.toString()
                characterViewModel.updateCharacterInfo()
            }
            false
        }

        viewBinding.tempHitPointsEditText.setText(characterInfo.currentState.temporaryHp)
        viewBinding.tempHitPointsEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewBinding.tempHitPointsEditText.clearFocus()
                characterViewModel.getCharacter().characterInfo.currentState.temporaryHp =
                    viewBinding.tempHitPointsEditText.text.toString()
                characterViewModel.updateCharacterInfo()
            }
            false
        }

        viewBinding.platinumCoinsEditText.setText(
            characterViewModel.getCharacter().characterInfo.currentState.coins["пм"].toString()
        )
        setupEditText(viewBinding.platinumCoinsEditText) { value: Int ->
            characterViewModel.getCharacter().characterInfo.currentState.coins["пм"] = value
        }
        viewBinding.goldCoinsEditText.setText(
            characterViewModel.getCharacter().characterInfo.currentState.coins["зм"].toString()
        )
        setupEditText(viewBinding.goldCoinsEditText) { value: Int ->
            characterViewModel.getCharacter().characterInfo.currentState.coins["зм"] = value
        }
        viewBinding.silverCoinsEditText.setText(
            characterViewModel.getCharacter().characterInfo.currentState.coins["см"].toString()
        )
        setupEditText(viewBinding.silverCoinsEditText) { value: Int ->
            characterViewModel.getCharacter().characterInfo.currentState.coins["см"] = value
        }
        viewBinding.copperCoinsEditText.setText(
            characterViewModel.getCharacter().characterInfo.currentState.coins["мм"].toString()
        )
        setupEditText(viewBinding.copperCoinsEditText) { value: Int ->
            characterViewModel.getCharacter().characterInfo.currentState.coins["мм"] = value
        }
    }

    private fun getHitDice(characterClass: Classes): String {
        return when (characterClass) {
            Classes.Wizard, Classes.Sorcerer -> "к6"
            Classes.Fighter, Classes.Ranger, Classes.Paladin -> "к10"
            Classes.Barbarian -> "к12"
            else -> "к8"
        }
    }

    private fun setupEditText(editText: EditText, action: (Int) -> Unit) {
        editText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                editText.clearFocus()
                try {
                    action(editText.text.toString().toInt())
                } catch (e: Exception) {
                    Toast.makeText(
                        requireContext(),
                        "Введите пожалуйста целое число",
                        Toast.LENGTH_LONG
                    ).show()
                }
                characterViewModel.updateCharacterInfo()
            }
            try {
                action(editText.text.toString().toInt())
            } catch (_: Exception) {
            }
            false
        }
    }
}