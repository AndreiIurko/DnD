package com.andreyyurko.dnd.ui.addcharacterfragments.abilitiesfragment

import android.util.Log
import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.data.abilities.custom.CustomChangeAbilities
import com.andreyyurko.dnd.data.characters.mergeAllAbilities
import com.andreyyurko.dnd.utils.CreateCharacterViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class AbilitiesViewModel @Inject constructor(
    private val createCharacterViewModel: CreateCharacterViewModel
) : ViewModel() {

    var characterAbility = CustomChangeAbilities()

    fun setAbility(name: String) {
        createCharacterViewModel.character.customAbilities.customChangeAbilities = characterAbility
        createCharacterViewModel.character = mergeAllAbilities(createCharacterViewModel.character)
        createCharacterViewModel.character.name = name
        Log.d(LOG_TAG, characterAbility.characterInfo.toString())
        createCharacterViewModel.updateCharacter()
    }

    companion object {
        const val LOG_TAG = "AbilitiesViewModel"
    }

}