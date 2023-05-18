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
    var isPointBy: Boolean = false

    val totalPoints: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>(27)
    }

    var characterInfo = createCharacterViewModel.character.customAbilities

    init {
        increaseAbility(Ability.Strength.abilityName, characterInfo.strength)
        increaseAbility(Ability.Dexterity.abilityName, characterInfo.dexterity)
        increaseAbility(Ability.Constitution.abilityName, characterInfo.constitution)
        increaseAbility(Ability.Intelligence.abilityName, characterInfo.intelligence)
        increaseAbility(Ability.Wisdom.abilityName, characterInfo.wisdom)
        increaseAbility(Ability.Charisma.abilityName, characterInfo.charisma)
    }

    fun changeSystem() {
        if (isPointBy) {
            isPointBy = false
        } else {
            isPointBy = true
            val currentValues = abilities.values.toList()
            abilities.keys.forEach {
                abilities[it] = 8
            }
            totalPoints.value = 27
            for ((i, ability) in Ability.values().withIndex()) {
                increaseAbility(ability.abilityName, currentValues[i])
            }
        }
    }

    private fun increaseAbility(abilityName: String, end: Int) {
        for (i in abilities[abilityName]!! + 1..end) {
            increaseAbility(i)
        }
        if (abilities[abilityName]!! < end) abilities[abilityName] = end
    }

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
        } else if (newValue > 8) {
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
        } else {
            totalPoints.value?.let {
                totalPoints.value = it + 1
            }
        }
    }

    fun getName(): String {
        return createCharacterViewModel.character.name
    }

}