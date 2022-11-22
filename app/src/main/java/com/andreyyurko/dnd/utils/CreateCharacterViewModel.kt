package com.andreyyurko.dnd.utils

import android.util.Log
import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.data.characters.Character
import com.andreyyurko.dnd.data.characters.CharacterInfo
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
            abilities = emptyList(),
            characterInfo = CharacterInfo()
        )
        character = charactersHolder.addCharacter(character)
    }

    fun updateCharacter() {
        Log.d(LOG_TAG, character.toString())
        charactersHolder.updateCharacter(character)
    }

    companion object {
        const val LOG_TAG = "CreateCharacterViewModel"
    }
}