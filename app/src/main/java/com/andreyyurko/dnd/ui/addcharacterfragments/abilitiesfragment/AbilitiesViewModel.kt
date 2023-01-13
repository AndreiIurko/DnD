package com.andreyyurko.dnd.ui.addcharacterfragments.abilitiesfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.data.characterData.Ability
import com.andreyyurko.dnd.data.characterData.character.mergeAllAbilities
import com.andreyyurko.dnd.utils.CreateCharacterViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class AbilitiesViewModel @Inject constructor(
    private val createCharacterViewModel: CreateCharacterViewModel
) : ViewModel() {

    var abilities = Ability.values().associateBy({ it.abilityName }, { 8 }).toMutableMap()
    val totalPoints: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>(27)
    }

    var characterInfo = createCharacterViewModel.character.customAbilities
    fun setAbility(name: String) {
        createCharacterViewModel.character.customAbilities = characterInfo
        createCharacterViewModel.character.name = name
        mergeAllAbilities(createCharacterViewModel.character)
        createCharacterViewModel.updateCharacter()
    }

    fun deleteCharacter() {
        createCharacterViewModel.deleteCharacter()
    }

    companion object {
        const val LOG_TAG = "AbilitiesViewModel"
    }

    fun increaseAbility(newValue: Int) {
        if (newValue == 14 || newValue == 15) {
            totalPoints.value?.let {
                totalPoints.value = it - 2
            }
        }
        else {
            totalPoints.value?.let {
                totalPoints.value = it - 1
            }
        }
    }

    fun decreaseAbility(newValue: Int) {
        if (newValue == 13 || newValue == 14) {
            totalPoints.value?.let {
                totalPoints.value = it + 2
            }
        }
        else {
            totalPoints.value?.let {
                totalPoints.value = it + 1
            }
        }
    }

}