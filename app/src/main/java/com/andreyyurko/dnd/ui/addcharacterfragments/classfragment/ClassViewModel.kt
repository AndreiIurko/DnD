package com.andreyyurko.dnd.ui.addcharacterfragments.classfragment

import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.data.abilities.classes.AbilityNodeLevel
import com.andreyyurko.dnd.data.abilities.classes.CharacterAbilityNodeLevel
import com.andreyyurko.dnd.data.abilities.mapOfAn
import com.andreyyurko.dnd.data.characterData.Classes
import com.andreyyurko.dnd.data.characterData.character.checkIfSomeRequirementsSatisfied
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
            chosenClass = baseCAN.chosenAlternatives["class"]?.data?.name
    }

    fun makeChoice(choice: String) {
        character.characterInfo.currentState.charges = mutableMapOf()
        // TODO: понять как лучше всего обработать смену класса
        character.characterInfo.spellsInfo.remove("Заклинания класса")
        // TODO: think about how to do it better
        mapOfAn[choice]?.let {
            baseCAN.chosenAlternatives["class"] =
                CharacterAbilityNodeLevel((it as AbilityNodeLevel), character)
        }
        //baseCAN.makeChoice("class", choice)

        val firstLevelCAN = baseCAN.chosenAlternatives["class"] as CharacterAbilityNodeLevel
        firstLevelCAN.makeChoice()
        levelUp(firstLevelCAN, 1)
        showAllClassAbilities()

        mergeAllAbilities(createCharacterViewModel.character)
    }

    fun changeLevel(level: Int) {
        character.characterInfo.currentState.charges = mutableMapOf()
        var can = baseCAN.chosenAlternatives["class"]
        for (i in 1 until level) {
            can?.let {
                val nextLevelName = it.data.name.split('_')[0] + '_' +
                        (it.data.name.split('_')[1].toInt() + 1).toString()
                if (!it.chosenAlternatives.containsKey("nextLevel")) {
                    it.makeChoice("nextLevel", nextLevelName)
                }
                can = it.chosenAlternatives["nextLevel"]
            }
        }

        can?.chosenAlternatives?.remove("nextLevel")
        chosenLevel = level

        checkIfSomeRequirementsSatisfied(baseCAN.chosenAlternatives["class"])
        mergeAllAbilities(createCharacterViewModel.character)
        showAllClassAbilities()
    }

    fun levelUp() {
        var can = baseCAN.chosenAlternatives["class"]!!
        var nextLevel = can.chosenAlternatives["nextLevel"]

        while (nextLevel != null) {
            can = nextLevel
            nextLevel = can.chosenAlternatives["nextLevel"]
        }

        val nextLevelName =
            can.data.name.split('_')[0] + '_' + (can.data.name.split('_')[1].toInt() + 1).toString()

        can.makeChoice("nextLevel", nextLevelName)
        chosenLevel++
        checkIfSomeRequirementsSatisfied(baseCAN.chosenAlternatives["class"])
        mergeAllAbilities(createCharacterViewModel.character)
        showAllClassAbilities()
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
        val firstLevelCAN = character.baseCAN.chosenAlternatives["class"]

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