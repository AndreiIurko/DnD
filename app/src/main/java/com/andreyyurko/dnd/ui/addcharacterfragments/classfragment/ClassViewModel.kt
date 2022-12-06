package com.andreyyurko.dnd.ui.addcharacterfragments.classfragment

import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.data.characters.mergeAllAbilities
import com.andreyyurko.dnd.utils.CreateCharacterViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ClassViewModel @Inject constructor(
    private val createCharacterViewModel: CreateCharacterViewModel
) : ViewModel() {
    val character = createCharacterViewModel.character
    val baseCAN = character.classAbilities

    fun makeChoice(choice: String) {
        baseCAN.makeChoice("class", choice)
        createCharacterViewModel.character = mergeAllAbilities(createCharacterViewModel.character)
        createCharacterViewModel.updateCharacter()
    }

    fun deleteCharacter() {
        createCharacterViewModel.deleteCharacter()
    }
}