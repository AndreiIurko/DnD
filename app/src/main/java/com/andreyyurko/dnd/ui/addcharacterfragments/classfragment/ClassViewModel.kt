package com.andreyyurko.dnd.ui.addcharacterfragments.classfragment

import android.util.Log
import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.data.abilities.characterclass.AbilityNodeLevel
import com.andreyyurko.dnd.data.abilities.characterclass.CharacterAbilityNodeLevel
import com.andreyyurko.dnd.data.abilities.mapOfAn
import com.andreyyurko.dnd.data.characters.mergeAllAbilities
import com.andreyyurko.dnd.utils.CreateCharacterViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ClassViewModel @Inject constructor(
    private val createCharacterViewModel: CreateCharacterViewModel
) : ViewModel() {
    val character = createCharacterViewModel.character
    val baseCAN = character.baseCAN

    lateinit var adapter: ClassAdapter
    var listOfClassAbilities = emptyList<ClassAbility>()

    var chosenLevel = 1

    fun makeChoice(choice: String) {
        // TODO: think about how to do it better
        mapOfAn[choice]?.let {
            baseCAN.chosen_alternatives["class"] = CharacterAbilityNodeLevel((it as AbilityNodeLevel))
        }
        //baseCAN.makeChoice("class", choice)

        val firstLevelCAN = baseCAN.chosen_alternatives["class"] as CharacterAbilityNodeLevel
        firstLevelCAN.makeChoice()
        showAllClassAbilities()

        createCharacterViewModel.character = mergeAllAbilities(createCharacterViewModel.character)
        updateCharacter()
    }

    fun updateCharacter() {
        createCharacterViewModel.updateCharacter()
    }

    fun deleteCharacter() {
        createCharacterViewModel.deleteCharacter()
    }

    private fun showAllClassAbilities() {
        val newListOfAbilities = mutableListOf<ClassAbility>()

        val firstLevelCAN = character.baseCAN.chosen_alternatives["class"] as CharacterAbilityNodeLevel
        showNextLevel(firstLevelCAN, newListOfAbilities, 1)

        listOfClassAbilities = newListOfAbilities
        adapter.apply {
            abilitiesList = listOfClassAbilities
            notifyDataSetChanged()
        }
    }

    private fun showNextLevel(can: CharacterAbilityNodeLevel?, listOfAbilities: MutableList<ClassAbility>, level: Int) {
        Log.d("test", level.toString())
        Log.d("test", can?.data?.name.toString())
        can?.let {
            //it.levelUp()
            // TODO: пройти через showOptions
            // it - CAN текущего уровня - для уровня размер альтернативы = 1
            it.makeChoice()
            // TODO: showOptions
            for (entry in it.data.alternatives.entries) {
                // check if level up ability
                val ability = mapOfAn[entry.value[0]]!!
                listOfAbilities.add(ClassAbility(
                    name = ability.name,
                    classDescription = it.data.description, // 1-й уровень монаха
                    description = ability.description,
                    parentName = entry.key, // имя в мапе
                    classCAN = it // чтобы иметь доступ для makeChoice
                ))
            }
            var nextLevelCAN : CharacterAbilityNodeLevel? = null
            if (level < chosenLevel) {
                it.levelUp()
                nextLevelCAN = it.next_level
            }
            showNextLevel(nextLevelCAN, listOfAbilities, level + 1)
        }
    }

    companion object {
        const val LOG_TAG = "ClassViewModel"
    }
}