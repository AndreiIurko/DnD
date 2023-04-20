package com.andreyyurko.dnd.ui.addcharacterfragments.classfragment

import android.util.Log
import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.data.abilities.classes.AbilityNodeLevel
import com.andreyyurko.dnd.data.abilities.classes.CharacterAbilityNodeLevel
import com.andreyyurko.dnd.data.abilities.mapOfAn
import com.andreyyurko.dnd.data.characterData.Classes
import com.andreyyurko.dnd.data.characterData.character.mergeAllAbilities
import com.andreyyurko.dnd.ui.addcharacterfragments.AbilityAdapter
import com.andreyyurko.dnd.utils.CreateCharacterViewModel
import com.andreyyurko.dnd.utils.SpellsHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ClassViewModel @Inject constructor(
    private val createCharacterViewModel: CreateCharacterViewModel,
    private val spellsHandler: SpellsHandler
) : ViewModel() {
    val character = createCharacterViewModel.character
    val baseCAN = character.baseCAN

    lateinit var adapter: AbilityAdapter

    var chosenLevel = 1
    var chosenClass: String? = null

    init {
        if (character.characterInfo.level > 0)
            chosenLevel = character.characterInfo.level
        if (character.characterInfo.characterClass != Classes.NotImplemented)
            chosenClass = character.characterInfo.characterClass.className
    }

    fun makeChoice(choice: String) {
        character.characterInfo.spellsInfo.remove("Заклинания класса")
        // TODO: think about how to do it better
        mapOfAn[choice]?.let {
            baseCAN.chosen_alternatives["class"] = CharacterAbilityNodeLevel((it as AbilityNodeLevel), character)
        }
        //baseCAN.makeChoice("class", choice)

        val firstLevelCAN = baseCAN.chosen_alternatives["class"] as CharacterAbilityNodeLevel
        firstLevelCAN.makeChoice()
        levelUp(firstLevelCAN, 1)
        showAllClassAbilities()

        mergeAllAbilities(createCharacterViewModel.character)
    }

    fun updateCharacter() {
        createCharacterViewModel.updateCharacter()
    }

    fun saveChangesInCharacter() {
        createCharacterViewModel.saveChangesInCharacter()
    }

    fun deleteCharacter() {
        createCharacterViewModel.deleteCharacter()
    }

    fun isNeededToChooseKnownSpells(): Boolean {
        return !spellsHandler.isAllKnown(createCharacterViewModel.character)
    }

    fun showAllClassAbilities() {
        val firstLevelCAN = character.baseCAN.chosen_alternatives["class"]

        adapter.apply {
            rootCan = firstLevelCAN
            notifyDataSetChanged()
        }
    }

    private fun levelUp(can: CharacterAbilityNodeLevel?, level: Int) {
        can?.let {
            it.makeChoice()
            var nextLevelCAN: CharacterAbilityNodeLevel? = null
            if (level < chosenLevel) {
                it.levelUp()
                nextLevelCAN = it.next_level
            }
            levelUp(nextLevelCAN, level + 1)
        }
    }

    companion object {
        const val LOG_TAG = "ClassViewModel"
    }
}