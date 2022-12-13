package com.andreyyurko.dnd.ui.addcharacterfragments.abilitiesfragment

import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.data.characters.mergeCharacterInfo
import com.andreyyurko.dnd.data.characters.mergeAllAbilities
import com.andreyyurko.dnd.utils.CreateCharacterViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class AbilitiesViewModel @Inject constructor(
    private val createCharacterViewModel: CreateCharacterViewModel
) : ViewModel() {

    var characterInfo = createCharacterViewModel.character.customAbilities
    fun setAbility(name: String) {
        createCharacterViewModel.character.customAbilities = characterInfo
        createCharacterViewModel.character.name = name
        createCharacterViewModel.character = mergeAllAbilities(createCharacterViewModel.character)
        createCharacterViewModel.updateCharacter()
    }

    fun deleteCharacter() {
        createCharacterViewModel.deleteCharacter()
    }

    companion object {
        const val LOG_TAG = "AbilitiesViewModel"
    }

}