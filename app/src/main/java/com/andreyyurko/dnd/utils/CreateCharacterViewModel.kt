package com.andreyyurko.dnd.utils

import android.util.Log
import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.data.characterData.character.Character
import com.andreyyurko.dnd.data.characterData.character.mergeAllAbilities
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreateCharacterViewModel @Inject constructor(
    private val charactersHolder: CharactersHolder
) : ViewModel() {
    lateinit var character: Character
    var currentlyChangingCharacterId: Int? = null

    fun createEmptyCharacter() {
        character = Character(
            id = -1,
        )
        currentlyChangingCharacterId = null
        character.baseCAN.character = character
        character = charactersHolder.addCharacter(character)
    }

    fun updateCharacter() {
        mergeAllAbilities(character)

        if (currentlyChangingCharacterId == null)
            charactersHolder.updateCharacter(character)
    }

    fun saveChangesInCharacter() {
        if (currentlyChangingCharacterId != null) {
            character.id = currentlyChangingCharacterId!!
            mergeAllAbilities(character)
            charactersHolder.updateCharacter(character)
        }
    }

    fun deleteCharacter() {
        if (currentlyChangingCharacterId == null)
            charactersHolder.removeCharacterById(character.id)
    }

    companion object {
        const val LOG_TAG = "CreateCharacterViewModel"
    }
}