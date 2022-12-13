package com.andreyyurko.dnd.utils

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