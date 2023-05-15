package com.andreyyurko.dnd.ui.showcharacterfragments.spells

import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.data.spells.Spell
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
        return spellsHandler.isAllKnownIsPrepared(characterViewModel.getCharacter())
    }

    fun getFilters(): SpellsHandler.Filters {
        return if (isPreparedListShown) preparedFilters
        else knownFilters
    }

    fun showPreparedSpells(): List<Spell> {
        return spellsHandler.getPreparedSpellsWithDescription(characterViewModel.getCharacter(), preparedFilters)
    }

    fun showKnownSpells(): List<Spell> {
        return spellsHandler.getKnownSpellsWithDescription(characterViewModel.getCharacter(), knownFilters)
    }

    fun addPreparedSpell(spell: Spell) {
        spellsHandler.addPreparedSpell(spell.data.name, spell.listName, characterViewModel.getCharacter())
        characterViewModel.updateCharacterInfo()
    }

    fun removePreparedSpell(spell: Spell) {
        spellsHandler.removePreparedSpell(spell.data.name, spell.listName, characterViewModel.getCharacter())
        characterViewModel.updateCharacterInfo()
    }

    fun getPreparedSpellsCount(): Int {
        return spellsHandler.getPreparedSpellsCount(characterViewModel.getCharacter())
    }

    fun getMaxPreparedSpellsCount(): Int {
        return spellsHandler.getMaxPreparedSpellsCount(characterViewModel.getCharacter())
    }

    fun getPreparedCantripsCount(): Int {
        return spellsHandler.getPreparedCantripsCount(characterViewModel.getCharacter())
    }

    fun getMaxPreparedCantripsCount(): Int {
        return spellsHandler.getMaxPreparedCantripsCount(characterViewModel.getCharacter())
    }
}