package com.andreyyurko.dnd.ui.addcharacterfragments.spellsfragment

import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.data.SpellSpecificLanguage
import com.andreyyurko.dnd.utils.CharacterViewModel
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

    private val knownFilters = SpellsHandler.Filters()
    private val allFilters = SpellsHandler.Filters()
    var isKnownListShown = false

    fun getFilters(): SpellsHandler.Filters {
        return if (isKnownListShown) knownFilters
        else allFilters
    }

    fun getKnownSpellsCount(): Int {
        return spellsHandler.getKnownSpellsCount(createCharacterViewModel.character)
    }

    fun getMaxKnownSpellsCount(): Int {
        return spellsHandler.getMaxKnownSpellsCount(createCharacterViewModel.character)
    }

    fun getKnownCantripsCount(): Int {
        return spellsHandler.getKnownCantripsCount(createCharacterViewModel.character)
    }

    fun getMaxKnownCantripsCount(): Int {
        return spellsHandler.getMaxKnownCantripsCount(createCharacterViewModel.character)
    }

    fun showAllSpells(): List<SpellSpecificLanguage> {
        return spellsHandler.getClassSpellsWithDescription(createCharacterViewModel.character, allFilters).toList()
    }

    fun showKnownSpells(): List<SpellSpecificLanguage> {
        return spellsHandler.getKnownSpellsWithDescription(createCharacterViewModel.character, knownFilters).toList()
    }

    fun getKnownSpells(): MutableSet<String> {
        return spellsHandler.getKnownSpells(createCharacterViewModel.character)
    }

    fun getKnownCantrips(): MutableSet<String> {
        return spellsHandler.getKnownCantrips(createCharacterViewModel.character)
    }

    fun updateCharacter() {
        createCharacterViewModel.updateCharacter()
    }

    fun deleteCharacter() {
        createCharacterViewModel.deleteCharacter()
    }
}