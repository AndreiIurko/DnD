package com.andreyyurko.dnd.ui.addcharacterfragments.abilitiesfragment

import android.util.Log
import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.data.characters.CharacterInfo
import com.andreyyurko.dnd.data.characters.mergeAllAbilities
import com.andreyyurko.dnd.utils.CreateCharacterViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class AbilitiesViewModel @Inject constructor(
    private val createCharacterViewModel: CreateCharacterViewModel
) : ViewModel() {

    var characterInfo = createCharacterViewModel.character.customAbilities.chosen_alternatives["abilityChange"]!!.data.characterInfo

    fun setAbility(name: String) {
        createCharacterViewModel.character.customAbilities.chosen_alternatives["abilityChange"]!!.data.characterInfo = characterInfo
        createCharacterViewModel.character.customAbilities.merge(CharacterInfo())
        createCharacterViewModel.character = mergeAllAbilities(createCharacterViewModel.character)
        createCharacterViewModel.character.name = name
        createCharacterViewModel.updateCharacter()
    }

    companion object {
        const val LOG_TAG = "AbilitiesViewModel"
    }

}