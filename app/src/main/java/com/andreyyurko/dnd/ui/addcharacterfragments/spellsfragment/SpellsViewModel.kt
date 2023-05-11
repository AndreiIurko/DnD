package com.andreyyurko.dnd.ui.addcharacterfragments.spellsfragment

import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.data.spells.Spell
import com.andreyyurko.dnd.utils.CreateCharacterViewModel
import com.andreyyurko.dnd.utils.SpellsHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SpellsViewModel @Inject constructor(
    private val createCharacterViewModel: CreateCharacterViewModel,
    val spellsHandler: SpellsHandler
) : ViewModel() {

    var character = createCharacterViewModel.character
    var classesForSpells: MutableList<String> = mutableListOf()
    var currentClassList = 0

    private val knownFilters = SpellsHandler.Filters()
    private val allFilters = SpellsHandler.Filters()
    var isKnownListShown = false

    init {
        for (spellList in character.characterInfo.spellsInfo.values) {
            if (!classesForSpells.contains(spellList.className)) {
                classesForSpells.add(spellList.className)
            }
        }
    }

    fun getFilters(): SpellsHandler.Filters {
        return if (isKnownListShown) knownFilters
        else allFilters
    }

    fun getKnownSpellsCount(): Int {
        return spellsHandler.getKnownSpellsCount(createCharacterViewModel.character, classesForSpells[currentClassList])
    }

    fun getMaxKnownSpellsCount(): Int {
        return spellsHandler.getMaxKnownSpellsCount(
            createCharacterViewModel.character,
            classesForSpells[currentClassList]
        )
    }

    fun getKnownCantripsCount(): Int {
        return spellsHandler.getKnownCantripsCount(
            createCharacterViewModel.character,
            classesForSpells[currentClassList]
        )
    }

    fun getMaxKnownCantripsCount(): Int {
        return spellsHandler.getMaxKnownCantripsCount(
            createCharacterViewModel.character,
            classesForSpells[currentClassList]
        )
    }

    fun showAllSpells(): List<Spell> {
        return spellsHandler.getAllSpellsWhatNeedsToBeChosen(
            createCharacterViewModel.character,
            classesForSpells[currentClassList],
            allFilters
        )
    }

    fun showKnownSpells(): List<Spell> {
        return spellsHandler.getKnownSpellsWithDescription(
            createCharacterViewModel.character,
            knownFilters,
            classesForSpells[currentClassList]
        )
    }

    fun addKnownSpell(spell: Spell) {
        spellsHandler.addKnownSpell(spell.data.name, spell.listName, createCharacterViewModel.character)
        updateCharacter()
    }

    fun removeKnownSpell(spell: Spell) {
        spellsHandler.removeKnownSpell(spell.data.name, spell.listName, createCharacterViewModel.character)
        updateCharacter()
    }

    fun updateCharacter() {
        createCharacterViewModel.updateCharacter()
    }

    fun deleteCharacter() {
        createCharacterViewModel.deleteCharacter()
    }

    fun saveChangesInCharacter() {
        createCharacterViewModel.saveChangesInCharacter()
    }

    fun nextListOrExit(): Boolean {
        currentClassList++
        return currentClassList != classesForSpells.size
    }

    fun previousOrBack(): Boolean {
        currentClassList--
        return currentClassList >= 0
    }
}