package com.andreyyurko.dnd.ui.showcharacterfragments.spells

import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.data.SpellSpecificLanguage
import com.andreyyurko.dnd.utils.CharacterViewModel
import com.andreyyurko.dnd.utils.SpellsHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterSpellsViewModel @Inject constructor(
    val characterViewModel: CharacterViewModel,
    val spellsHandler: SpellsHandler
) : ViewModel() {

    val filters = SpellsHandler.Filters()

    fun showPreparedSpells(): List<SpellSpecificLanguage> {
        return spellsHandler.getPreparedSpells(characterViewModel.shownCharacter, filters)
    }

    fun showKnownSpells(): List<SpellSpecificLanguage> {
        return spellsHandler.getKnownSpells(characterViewModel.shownCharacter, filters)
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