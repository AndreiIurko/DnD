package com.andreyyurko.dnd.ui.showcharacterfragments.spells

import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.data.SpellSpecificLanguage
import com.andreyyurko.dnd.data.characterData.character.Character
import com.andreyyurko.dnd.utils.CharacterViewModel
import com.andreyyurko.dnd.utils.SpellsHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterSpellsViewModel @Inject constructor(
    val characterViewModel: CharacterViewModel,
    private val spellsHandler: SpellsHandler
) : ViewModel() {

    private val preparedFilters = SpellsHandler.Filters()
    private val knownFilters = SpellsHandler.Filters()
    var isPreparedListShown = true

    fun isAllPrepared(): Boolean {
        return spellsHandler.isAllKnownIsPrepared(characterViewModel.shownCharacter)
    }

    fun getFilters(): SpellsHandler.Filters {
        return if (isPreparedListShown) preparedFilters
        else knownFilters
    }

    fun showPreparedSpells(): List<SpellSpecificLanguage> {
        return spellsHandler.getPreparedSpellsWithDescription(characterViewModel.shownCharacter, preparedFilters)
    }

    fun showKnownSpells(): List<SpellSpecificLanguage> {
        return spellsHandler.getKnownSpellsWithDescription(characterViewModel.shownCharacter, knownFilters)
    }

    fun getPreparedSpells(): MutableSet<String> {
        return spellsHandler.getPreparedSpells(characterViewModel.shownCharacter)
    }
    fun getPreparedCantrips(): MutableSet<String> {
        return spellsHandler.getPreparedCantrips(characterViewModel.shownCharacter)
    }

    fun getPreparedSpellsCount(): Int {
        return spellsHandler.getPreparedSpellsCount(characterViewModel.shownCharacter)
    }

    fun getMaxPreparedSpellsCount(): Int {
        return spellsHandler.getMaxPreparedSpellsCount(characterViewModel.shownCharacter)
    }

    fun getPreparedCantripsCount(): Int {
        return spellsHandler.getPreparedCantripsCount(characterViewModel.shownCharacter)
    }

    fun getMaxPreparedCantripsCount(): Int {
        return spellsHandler.getMaxPreparedCantripsCount(characterViewModel.shownCharacter)
    }
}