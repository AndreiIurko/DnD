package com.andreyyurko.dnd.utils

import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.data.characters.Character
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterViewModel @Inject constructor(
    private val charactersHolder: CharactersHolder
) : ViewModel() {
    var charactersBriefInfo = charactersHolder.getBriefInfo()

    lateinit var shownCharacter: Character
    fun updateBriefInfo() {
        charactersBriefInfo = charactersHolder.getBriefInfo()
    }
    fun setShownCharacter(id: Int) {
        shownCharacter = charactersHolder.getCharacterById(id)
    }

    fun deleteCharacter(id: Int) {
        charactersHolder.removeCharacterById(id)
    }
}