package com.andreyyurko.dnd.utils

import android.util.Log
import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.data.SpellSpecificLanguage
import com.andreyyurko.dnd.data.characterData.School
import com.andreyyurko.dnd.data.characterData.Source
import com.andreyyurko.dnd.data.characterData.character.Character
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SpellsHandler @Inject constructor(
    private val charactersHolder: CharactersHolder,
    private val spellsParser: SpellsParser
) : ViewModel() {

    var allSpells: MutableMap<String, SpellSpecificLanguage> = mutableMapOf()

    init {
        for (spell in spellsParser.spells!!) {
            allSpells[spell.name] = spell
        }
    }

    fun getClassSpellsWithDescription(character: Character, filters: Filters = Filters()): MutableList<SpellSpecificLanguage> {
        val className = character.characterInfo.spellsInfo.className
        val result: MutableList<SpellSpecificLanguage> = mutableListOf()
        for (spell in allSpells.values) {
            if (spell.classes.contains(className.lowercase()) &&
                checkFilters(spell, filters) &&
                spell.level.toInt() <= (character.characterInfo.level + 1) / 2)
                result.add(spell)
        }
        return result
    }

    fun getKnownSpellsWithDescription(character: Character, filters: Filters = Filters()): MutableList<SpellSpecificLanguage> {
        var result: MutableList<SpellSpecificLanguage> = mutableListOf()
        if (character.characterInfo.spellsInfo.maxKnownSpellsCount == -1) {
            result = getClassSpellsWithDescription(character, filters)
        } else {
            for (spellName in character.characterInfo.spellsInfo.spellLists.knownSpells + character.characterInfo.spellsInfo.spellLists.knownCantrips) {
                allSpells[spellName]?.apply {
                    if (checkFilters(this, filters)) result.add(this)
                }
            }
        }
        return result
    }

    fun getPreparedSpellsWithDescription(character: Character, filters: Filters = Filters()): MutableList<SpellSpecificLanguage> {
        var result: MutableList<SpellSpecificLanguage> = mutableListOf()
        if (character.characterInfo.spellsInfo.maxPreparedSpellsCount == -1) {
            result = getKnownSpellsWithDescription(character, filters)
        }
        else {
            for (spellName in character.characterInfo.spellsInfo.spellLists.preparedCantrips + character.characterInfo.spellsInfo.spellLists.preparedSpells) {
                allSpells[spellName]?.apply {
                    if (checkFilters(this, filters)) result.add(this)
                }

            }
        }
        sortByLevel(result)
        return result
    }

    fun isAllKnownIsPrepared(character: Character): Boolean {
        return character.characterInfo.spellsInfo.maxPreparedSpellsCount == -1
    }

    fun isAllKnown(character: Character): Boolean {
        return character.characterInfo.spellsInfo.maxKnownSpellsCount == -1
    }

    // this is essential to return reference
    fun getPreparedSpells(character: Character): MutableSet<String> {
        return character.characterInfo.spellsInfo.spellLists.preparedSpells
    }
    fun getPreparedCantrips(character: Character): MutableSet<String> {
        return character.characterInfo.spellsInfo.spellLists.preparedCantrips
    }
    fun getKnownSpells(character: Character): MutableSet<String> {
        return character.characterInfo.spellsInfo.spellLists.knownSpells
    }
    fun getKnownCantrips(character: Character): MutableSet<String> {
        return character.characterInfo.spellsInfo.spellLists.knownCantrips
    }

    fun getPreparedSpellsCount(character: Character): Int {
        return character.characterInfo.spellsInfo.spellLists.preparedSpells.size
    }

    fun getMaxPreparedSpellsCount(character: Character): Int {
        return character.characterInfo.spellsInfo.maxPreparedSpellsCount
    }

    fun getPreparedCantripsCount(character: Character): Int {
        return character.characterInfo.spellsInfo.spellLists.preparedCantrips.size
    }

    fun getMaxPreparedCantripsCount(character: Character): Int {
        return character.characterInfo.spellsInfo.maxPreparedCantripsCount
    }

    fun getKnownSpellsCount(character: Character): Int {
        return character.characterInfo.spellsInfo.spellLists.knownSpells.size
    }

    fun getMaxKnownSpellsCount(character: Character): Int {
        return character.characterInfo.spellsInfo.maxKnownSpellsCount
    }

    fun getKnownCantripsCount(character: Character): Int {
        return character.characterInfo.spellsInfo.spellLists.knownCantrips.size
    }

    fun getMaxKnownCantripsCount(character: Character): Int {
        return character.characterInfo.spellsInfo.maxKnownCantripsCount
    }

    private fun addPreparedSpell(character: Character, spellName: String) {
        character.characterInfo.spellsInfo.spellLists.preparedSpells.add(spellName)
        charactersHolder.updateCharacter(character)
    }

    private fun removePreparedSpell(character: Character, spellName: String) {
        character.characterInfo.spellsInfo.spellLists.preparedSpells.remove(spellName)
        charactersHolder.updateCharacter(character)
    }

    private fun addPreparedCantrip(character: Character, spellName: String) {
        character.characterInfo.spellsInfo.spellLists.preparedCantrips.add(spellName)
        charactersHolder.updateCharacter(character)
    }

    private fun removePreparedCantrip(character: Character, spellName: String) {
        character.characterInfo.spellsInfo.spellLists.preparedCantrips.remove(spellName)
        charactersHolder.updateCharacter(character)
    }

    fun addSpell(character: Character, spell: SpellSpecificLanguage) {
        if (spell.level.toInt() == 0) {
            addPreparedCantrip(character, spell.name)
        } else {
            addPreparedSpell(character, spell.name)
        }
    }

    fun removeSpell(character: Character, spell: SpellSpecificLanguage) {
        if (spell.level.toInt() == 0) {
            removePreparedCantrip(character, spell.name)
        } else {
            removePreparedSpell(character, spell.name)
        }
    }

    data class Filters(
        var levels: MutableSet<String> = mutableSetOf(),
        var source: MutableSet<Source> = mutableSetOf(),
        var school: MutableSet<School> = mutableSetOf(),
        var substring: String = ""
    )

    private fun sortByLevel(spells: MutableList<SpellSpecificLanguage>) {
        spells.sortWith { first: SpellSpecificLanguage, second: SpellSpecificLanguage ->
            second.level.toInt().compareTo(first.level.toInt())
        }
    }

    private fun checkFilters(spell: SpellSpecificLanguage, filters: Filters): Boolean {
        return checkSubstring(spell, filters.substring) &&
                checkFilter(spell.level, filters.levels.toList()) &&
                checkFilter(spell.sources, filters.source.map { it.sourceName }) &&
                checkFilter(spell.school, filters.school.map { it.shownName })

    }

    private fun checkSubstring(spell: SpellSpecificLanguage, substring: String): Boolean {
        for (property in spell.properties) {
            if (property.lowercase().contains(substring.lowercase())) return true
        }
        return false
    }

    private fun checkFilter(substring: String, filters: List<String>): Boolean {
        if (filters.isEmpty()) return true
        for (filter in filters) {
            if (filter.lowercase().contains(substring.lowercase())) return true
        }
        return false
    }
}