package com.andreyyurko.dnd.ui.addcharacterfragments.classfragment

import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.data.abilities.characterclass.AbilityNodeLevel
import com.andreyyurko.dnd.data.abilities.characterclass.CharacterAbilityNodeLevel
import com.andreyyurko.dnd.data.abilities.mapOfAn
import com.andreyyurko.dnd.data.characterData.character.mergeAllAbilities
import com.andreyyurko.dnd.utils.CreateCharacterViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ClassViewModel @Inject constructor(
    private val createCharacterViewModel: CreateCharacterViewModel
) : ViewModel() {
    val character = createCharacterViewModel.character
    val baseCAN = character.baseCAN

    lateinit var adapter: ClassAdapter

    var chosenLevel = 1
    var chosenClass: String? = null

    fun makeChoice(choice: String) {
        // TODO: think about how to do it better
        mapOfAn[choice]?.let {
            baseCAN.chosen_alternatives["class"] = CharacterAbilityNodeLevel((it as AbilityNodeLevel), character)
        }
        //baseCAN.makeChoice("class", choice)

        val firstLevelCAN = baseCAN.chosen_alternatives["class"] as CharacterAbilityNodeLevel
        firstLevelCAN.makeChoice()
        showAllClassAbilities()

        mergeAllAbilities(createCharacterViewModel.character)
        updateCharacter()
    }

    fun updateCharacter() {
        createCharacterViewModel.updateCharacter()
    }

    fun deleteCharacter() {
        createCharacterViewModel.deleteCharacter()
    }

    private fun showAllClassAbilities() {

        val firstLevelCAN = character.baseCAN.chosen_alternatives["class"] as CharacterAbilityNodeLevel
        levelUp(firstLevelCAN, 1)

        adapter.apply {
            firstLevelClassCAN = firstLevelCAN
            notifyDataSetChanged()
        }
    }

    private fun levelUp(can: CharacterAbilityNodeLevel?, level: Int) {
        can?.let {
            it.makeChoice()
            var nextLevelCAN : CharacterAbilityNodeLevel? = null
            if (level < chosenLevel) {
                it.levelUp()
                nextLevelCAN = it.next_level
            }
            levelUp(nextLevelCAN, level + 1)
        }
    }

    companion object {
        const val LOG_TAG = "ClassViewModel"
    }
}