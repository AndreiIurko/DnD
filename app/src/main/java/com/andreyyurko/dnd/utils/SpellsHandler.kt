package com.andreyyurko.dnd.utils

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

    private fun getClassSpells(character: Character): MutableSet<SpellSpecificLanguage> {
        val className = character.characterInfo.spellsInfo.className
        val result: MutableSet<SpellSpecificLanguage> = mutableSetOf()
        for (spell in allSpells.values) {
            if (spell.classes.contains(className.lowercase())) result.add(spell)
        }
        return result
    }

    fun getKnownSpells(character: Character, filters: Filters = Filters()): MutableList<SpellSpecificLanguage> {
        val result: MutableList<SpellSpecificLanguage> = mutableListOf()
        if (character.characterInfo.spellsInfo.maxKnownSpellsCount == -1) {
            for (spell in getClassSpells(character)) {
                if (
                    spell.level.toInt() <= (character.characterInfo.level + 1) / 2 &&
                    checkFilters(spell, filters)
                ) result.add(spell)
            }
        } else {
            for (spellName in character.characterInfo.spellsInfo.spellLists.knownSpells) {
                result.add(allSpells[spellName]!!)
            }
        }
        return result
    }

    fun getPreparedSpells(character: Character, filters: Filters = Filters()): MutableList<SpellSpecificLanguage> {
        val result: MutableList<SpellSpecificLanguage> = mutableListOf()
        for (spellName in character.characterInfo.spellsInfo.spellLists.preparedSpells + character.characterInfo.spellsInfo.spellLists.preparedCantrips) {
            allSpells[spellName]?.apply {
                if (
                    checkSubstring(this, filters.substring)
                )
                    result.add(this)
            }

        }
        sortByLevel(result)
        return result
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