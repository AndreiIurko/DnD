package com.andreyyurko.dnd.ui.showcharacterfragments.spells

import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.data.SpellSpecificLanguage
import com.andreyyurko.dnd.data.characterData.character.Character
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
        return spellsHandler.isAllKnownIsPrepared(characterViewModel.shownCharacter)
    }

    fun getFilters(): SpellsHandler.Filters {
        return if (isPreparedListShown) preparedFilters
        else knownFilters
    }

    fun showPreparedSpells(): List<Spell> {
        return spellsHandler.getPreparedSpellsWithDescription(characterViewModel.shownCharacter, preparedFilters)
    }

    fun showKnownSpells(): List<Spell> {
        return spellsHandler.getKnownSpellsWithDescription(characterViewModel.shownCharacter, knownFilters)
    }

    fun addPreparedSpell(spell: Spell) {
        spellsHandler.addPreparedSpell(spell.data.name, spell.listName, characterViewModel.shownCharacter)
        characterViewModel.updateCharacterInfo()
    }

    fun removePreparedSpell(spell: Spell) {
        spellsHandler.removePreparedSpell(spell.data.name, spell.listName, characterViewModel.shownCharacter)
        characterViewModel.updateCharacterInfo()
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