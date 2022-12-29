package com.andreyyurko.dnd.ui.addcharacterfragments.classfragment

import android.util.Log
import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.data.abilities.baseAN
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

    fun makeChoice(choice: String) {
        // TODO: think about how to do it better
        mapOfAn[choice]?.let {
            baseCAN.chosen_alternatives["class"] = CharacterAbilityNodeLevel((it as AbilityNodeLevel))
        }
        //baseCAN.makeChoice("class", choice)

        val firstLevelCAN = baseCAN.chosen_alternatives["class"] as CharacterAbilityNodeLevel
        firstLevelCAN.makeChoice()
        showAllClassAbilities(choice)

        createCharacterViewModel.character = mergeAllAbilities(createCharacterViewModel.character)
        updateCharacter()
    }

    fun updateCharacter() {
        createCharacterViewModel.updateCharacter()
    }

    fun deleteCharacter() {
        createCharacterViewModel.deleteCharacter()
    }

    private fun showAllClassAbilities(classChoice: String) {
        val newListOfAbilities = mutableListOf<ClassAbility>()
        for (key in mapOfAn.keys) {
            // check if this is not class AN
            if (key.split("_").first() != classChoice.split("_").first()) continue

            val classLevelAN = mapOfAn[key]!! as AbilityNodeLevel
            for (entry in classLevelAN.alternatives.entries) {
                // check if level up ability
                if (entry.key == classLevelAN.next_level) continue
                val ability = mapOfAn[entry.value[0]]!!
                newListOfAbilities.add(ClassAbility(
                    name = ability.name,
                    classDescription = classLevelAN.description,
                    description = ability.description,
                    parentName = entry.key
                ))
            }
        }
        listOfClassAbilities = newListOfAbilities
        adapter.apply {
            abilitiesList = listOfClassAbilities
            classCAN = character.baseCAN.chosen_alternatives["class"] as CharacterAbilityNodeLevel
            notifyDataSetChanged()
        }
    }

    companion object {
        const val LOG_TAG = "ClassViewModel"
    }
}