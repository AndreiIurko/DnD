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
        var className = ""
        if (character.characterInfo.spellsInfo.contains("Заклинания класса")) {
            character.characterInfo.spellsInfo["Заклинания класса"]?.let{
                className = it.className
            }
        }
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
        for((_, value) in character.characterInfo.spellsInfo.entries) {
            if (value.maxKnownSpellsCount == -1) {
                result = getClassSpellsWithDescription(character, filters)
            } else {
                for (spellName in value.spellLists.knownSpells + value.spellLists.knownCantrips) {
                    allSpells[spellName]?.apply {
                        if (checkFilters(this, filters)) result.add(this)
                    }
                }
            }
        }
        return result
    }

    fun getPreparedSpellsWithDescription(character: Character, filters: Filters = Filters()): MutableList<SpellSpecificLanguage> {
        var result: MutableList<SpellSpecificLanguage> = mutableListOf()
        for((_, value) in character.characterInfo.spellsInfo.entries) {
            if (value.maxPreparedSpellsCount == -1) {
                result += getKnownSpellsWithDescription(character, filters)
            } else {
                for (spellName in value.spellLists.preparedCantrips + value.spellLists.preparedSpells) {
                    allSpells[spellName]?.apply {
                        if (checkFilters(this, filters)) result.add(this)
                    }
                }
            }
        }
        sortByLevel(result)
        return result
    }

    fun isAllKnownIsPrepared(character: Character): Boolean {
        if (character.characterInfo.spellsInfo.contains("Заклинания класса")) {
            character.characterInfo.spellsInfo["Заклинания класса"]?.let{
                return it.maxPreparedSpellsCount == -1
            }
        }
        return true
    }

    fun isAllKnown(character: Character): Boolean {
        if (character.characterInfo.spellsInfo.contains("Заклинания класса")) {
            character.characterInfo.spellsInfo["Заклинания класса"]?.let{
                return it.maxKnownSpellsCount == -1
            }
        }
        return true
    }

    // this is essential to return reference
    fun getPreparedSpells(character: Character): MutableSet<String> {
        var result: MutableSet<String> = mutableSetOf()
        for((_, value) in character.characterInfo.spellsInfo.entries) {
            if (value.maxPreparedSpellsCount == -1) {
                result += value.spellLists.knownSpells
            } else {
                result += value.spellLists.preparedSpells
            }
        }
        return result
        //return character.characterInfo.spellsInfo.spellLists.preparedSpells
    }
    fun getPreparedCantrips(character: Character): MutableSet<String> {
        var result: MutableSet<String> = mutableSetOf()
        for((_, value) in character.characterInfo.spellsInfo.entries) {
            if (value.maxPreparedCantripsCount == -1) {
                result += value.spellLists.knownCantrips
            } else {
                result += value.spellLists.preparedCantrips
            }
        }
        return result
        //return character.characterInfo.spellsInfo.spellLists.preparedCantrips
    }
    fun getKnownSpells(character: Character): MutableSet<String> {
        var result: MutableSet<String> = mutableSetOf()
        if (character.characterInfo.spellsInfo.contains("Заклинания класса")) {
            character.characterInfo.spellsInfo["Заклинания класса"]?.let{
                result = it.spellLists.knownSpells
            }
        }
        return result
    }
    fun getKnownCantrips(character: Character): MutableSet<String> {
        var result: MutableSet<String> = mutableSetOf()
        if (character.characterInfo.spellsInfo.contains("Заклинания класса")) {
            character.characterInfo.spellsInfo["Заклинания класса"]?.let{
                result = it.spellLists.knownCantrips
            }
        }
        return result
    }

    fun getPreparedSpellsCount(character: Character): Int {
        var result = 0
        if (character.characterInfo.spellsInfo.contains("Заклинания класса")) {
            character.characterInfo.spellsInfo["Заклинания класса"]?.let{
                result = it.spellLists.preparedSpells.size
            }
        }
        return result
    }

    fun getMaxPreparedSpellsCount(character: Character): Int {
        var result = 0
        if (character.characterInfo.spellsInfo.contains("Заклинания класса")) {
            character.characterInfo.spellsInfo["Заклинания класса"]?.let{
                result = it.maxPreparedSpellsCount
            }
        }
        return result
    }

    fun getPreparedCantripsCount(character: Character): Int {
        var result = 0
        if (character.characterInfo.spellsInfo.contains("Заклинания класса")) {
            character.characterInfo.spellsInfo["Заклинания класса"]?.let{
                result = it.spellLists.preparedCantrips.size
            }
        }
        return result
    }

    fun getMaxPreparedCantripsCount(character: Character): Int {
        var result = 0
        if (character.characterInfo.spellsInfo.contains("Заклинания класса")) {
            character.characterInfo.spellsInfo["Заклинания класса"]?.let{
                result = it.maxPreparedCantripsCount
            }
        }
        return result
    }

    fun getKnownSpellsCount(character: Character): Int {
        var result = 0
        if (character.characterInfo.spellsInfo.contains("Заклинания класса")) {
            character.characterInfo.spellsInfo["Заклинания класса"]?.let{
                result = it.spellLists.knownSpells.size
            }
        }
        return result
    }

    fun getMaxKnownSpellsCount(character: Character): Int {
        var result = 0
        if (character.characterInfo.spellsInfo.contains("Заклинания класса")) {
            character.characterInfo.spellsInfo["Заклинания класса"]?.let{
                result = it.maxKnownSpellsCount
            }
        }
        return result
    }

    fun getKnownCantripsCount(character: Character): Int {
        var result = 0
        if (character.characterInfo.spellsInfo.contains("Заклинания класса")) {
            character.characterInfo.spellsInfo["Заклинания класса"]?.let{
                result = it.spellLists.knownCantrips.size
            }
        }
        return result
    }

    fun getMaxKnownCantripsCount(character: Character): Int {
        var result = 0
        if (character.characterInfo.spellsInfo.contains("Заклинания класса")) {
            character.characterInfo.spellsInfo["Заклинания класса"]?.let{
                result = it.maxKnownCantripsCount
            }
        }
        return result
    }

    /*private fun addPreparedSpell(character: Character, spellName: String) {
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
    }*/

    /*fun addSpell(character: Character, spell: SpellSpecificLanguage) {
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
    }*/

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