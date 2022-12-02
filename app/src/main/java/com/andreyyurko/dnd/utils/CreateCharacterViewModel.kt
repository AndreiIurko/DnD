package com.andreyyurko.dnd.utils

import android.util.Log
import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.data.characters.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreateCharacterViewModel @Inject constructor(
    private val charactersHolder: CharactersHolder
) : ViewModel() {
    lateinit var character: Character

    fun createEmptyCharacter() {
        character = Character(
            id = -1,
        )
        character = charactersHolder.addCharacter(character)
        createAbilityNode("customChangeAbility", "abilityChange_${character.id}")
        charactersHolder.updateCharacter(character)
    }

    private fun createAbilityNode(optionName: String, nodeName: String) {
        character.customAbilities.data.alternatives[optionName] = listOf(nodeName)
        mapOfAn[nodeName] = AbilityNode(nodeName)
        character.customAbilities.makeChoice(optionName, nodeName)
    }

    fun updateCharacter() {
        charactersHolder.updateCharacter(character)
    }

    fun deleteCharacter() {
        charactersHolder.removeCharacterById(character.id)
    }

    companion object {
        const val LOG_TAG = "CreateCharacterViewModel"
    }
}