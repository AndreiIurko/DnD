package com.andreyyurko.dnd.ui.characterlist

import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.data.characterData.CharacterBriefInfo
import com.andreyyurko.dnd.utils.CharacterViewModel
import com.andreyyurko.dnd.utils.CreateCharacterViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val characterViewModel: CharacterViewModel,
    private val createCharacterViewModel: CreateCharacterViewModel
) : ViewModel() {
    var charactersBriefInfo = characterViewModel.charactersBriefInfo

    fun setShownCharacter(id: Int) {
        characterViewModel.setShownCharacter(id)
    }

    fun createNewCharacter() {
        createCharacterViewModel.createEmptyCharacter()
    }

    fun updateBriefInfo() {
        characterViewModel.updateBriefInfo()
        charactersBriefInfo = characterViewModel.charactersBriefInfo
    }

    fun deleteCharacter(id: Int): MutableList<CharacterBriefInfo> {
        characterViewModel.deleteCharacter(id)
        updateBriefInfo()
        return charactersBriefInfo
    }
}