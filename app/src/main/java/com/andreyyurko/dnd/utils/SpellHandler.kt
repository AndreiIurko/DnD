package com.andreyyurko.dnd.utils

import android.util.Log
import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.data.SpellSpecificLanguage
import com.andreyyurko.dnd.data.characterData.School
import com.andreyyurko.dnd.data.characterData.Source
import com.andreyyurko.dnd.data.characterData.character.Character
import com.andreyyurko.dnd.data.spells.CharacterSpells
import com.andreyyurko.dnd.data.spells.Spell
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

    fun getClassSpellsWithDescription(character: Character, filters: Filters = Filters(), listName: String): MutableList<Spell> {
        val className = character.characterInfo.characterClass.className
        // in the future we must decide what to do with multiclass
        /*if (character.characterInfo.spellsInfo.contains("Заклинания класса")) {
            character.characterInfo.spellsInfo["Заклинания класса"]?.let{
                className = it.className
            }
        }*/
        val result: MutableList<Spell> = mutableListOf()
        for (spellDescription in allSpells.values) {
            val spell = Spell(spellDescription, listName, false)
            if (spell.data.classes.contains(className.lowercase()) &&
                checkFilters(spell, filters) &&
                spell.data.level.toInt() <= (character.characterInfo.level + 1) / 2)
                result.add(spell)
        }
        return result
    }

    fun getAllSpellsWhatNeedsToBePrepared(character: Character, filters: Filters = Filters()): MutableList<Spell> {
        val result: MutableList<Spell> = mutableListOf()
        Log.d("spell", character.characterInfo.characterClass.className)
        for ((listName, value) in character.characterInfo.spellsInfo.entries) {
            if ((value.maxKnownSpellsCount != -1 || value.maxKnownCantripsCount != -1 ) && character.characterInfo.characterClass.className == value.className) {
                Log.d("spell", value.className)
                for (spell in getClassSpellsWithDescription(character, filters, listName)) {
                    if (value.maxKnownSpellsCount != -1 && spell.data.level != "0")
                        result.add(spell)
                    if (value.maxKnownCantripsCount != -1 && spell.data.level == "0")
                        result.add(spell)
                }
            }
        }
        return result
    }

    private fun getKnownSpellsWithDescriptionFromList(
        character: Character, filters: Filters = Filters(),
        listName: String, value: CharacterSpells
    ): MutableList<Spell> {
        val result: MutableList<Spell> = mutableListOf()
        if (value.maxKnownCantripsCount == -1) {
            for (spell in getClassSpellsWithDescription(character, filters, listName)) {
                if (spell.data.level == "0") {
                    spell.isAlwaysIncluded = true
                    result.add(spell)
                }
            }
        }
        if (value.maxKnownSpellsCount == -1) {
            for (spell in getClassSpellsWithDescription(character, filters, listName)) {
                if (spell.data.level != "0") {
                    spell.isAlwaysIncluded = true
                    result.add(spell)
                }
            }
        }
        for (spellName in value.spellLists.knownCantrips + value.spellLists.knownSpells) {
            allSpells[spellName]?.let {
                val spell = Spell(it, listName, false)
                if (checkFilters(spell, filters)) result.add(spell)
            }
        }
        return result
    }

    fun getKnownSpellsWithDescription(character: Character, filters: Filters = Filters()): MutableList<Spell> {
        val result: MutableList<Spell> = mutableListOf()
        for((listName, value) in character.characterInfo.spellsInfo.entries) {
            result += getKnownSpellsWithDescriptionFromList(character, filters, listName, value)
        }
        return result
    }

    fun getPreparedSpellsWithDescription(character: Character, filters: Filters = Filters()): MutableList<Spell> {
        var result: MutableList<Spell> = mutableListOf()
        for((listName, value) in character.characterInfo.spellsInfo.entries) {
            if (value.maxPreparedCantripsCount == -1) {
                for (spell in getKnownSpellsWithDescriptionFromList(character, filters, listName, value)) {
                    if (spell.data.level == "0") {
                        spell.isAlwaysIncluded = true
                        result.add(spell)
                    }
                }
            }
            if (value.maxPreparedSpellsCount == -1) {
                for (spell in getKnownSpellsWithDescriptionFromList(character, filters, listName, value)) {
                    if (spell.data.level != "0") {
                        spell.isAlwaysIncluded = true
                        result.add(spell)
                    }
                }
            }
            for (spellName in value.spellLists.preparedCantrips + value.spellLists.preparedSpells) {
                allSpells[spellName]?.let {
                    val spell = Spell(it, listName, false)
                    if (checkFilters(spell, filters)) result.add(spell)
                }
            }
        }
        sortByLevel(result)
        return result
    }

    fun addKnownSpell(spellName: String, listName: String, character: Character) {
        character.characterInfo.spellsInfo[listName]?.let {
            allSpells[spellName]?.apply {
                if (this.level.toInt() == 0)
                    it.spellLists.knownCantrips.add(spellName)
                else
                    it.spellLists.knownSpells.add(spellName)
            }
        }
    }

    fun removeKnownSpell(spellName: String, listName: String, character: Character) {
        character.characterInfo.spellsInfo[listName]?.let {
            allSpells[spellName]?.apply {
                if (this.level.toInt() == 0)
                    it.spellLists.knownCantrips.remove(spellName)
                else
                    it.spellLists.knownSpells.remove(spellName)
            }
        }
    }

    fun addPreparedSpell(spellName: String, listName: String, character: Character) {
        character.characterInfo.spellsInfo[listName]?.let {
            allSpells[spellName]?.apply {
                if (this.level.toInt() == 0)
                    it.spellLists.preparedCantrips.add(spellName)
                else
                    it.spellLists.preparedSpells.add(spellName)
            }
        }
    }

    fun removePreparedSpell(spellName: String, listName: String, character: Character) {
        character.characterInfo.spellsInfo[listName]?.let {
            allSpells[spellName]?.apply {
                if (this.level.toInt() == 0)
                    it.spellLists.preparedCantrips.remove(spellName)
                else
                    it.spellLists.preparedSpells.remove(spellName)
            }
        }
    }

    fun getPreparedSpellsCount(character: Character): Int {
        var result = 0
        for ((_, value) in character.characterInfo.spellsInfo) {
            if (value.maxPreparedSpellsCount != -1)
                result += value.spellLists.preparedSpells.size

        }
        return result
    }

    fun getMaxPreparedSpellsCount(character: Character): Int {
        var result = 0
        for ((_, value) in character.characterInfo.spellsInfo) {
            if (value.maxPreparedSpellsCount != -1)
                result += value.maxPreparedSpellsCount
        }
        return result
    }

    fun getPreparedCantripsCount(character: Character): Int {
        var result = 0
        for ((_, value) in character.characterInfo.spellsInfo) {
            if (value.maxPreparedCantripsCount != -1)
                result += value.spellLists.preparedCantrips.size

        }
        return result
    }

    fun getMaxPreparedCantripsCount(character: Character): Int {
        var result = 0
        Log.d("spell", character.characterInfo.spellsInfo.toString())
        for ((_, value) in character.characterInfo.spellsInfo) {
            if (value.maxPreparedCantripsCount != -1)
                result += value.maxPreparedCantripsCount
        }
        return result
    }

    fun getKnownSpellsCount(character: Character): Int {
        var result = 0
        for ((_, value) in character.characterInfo.spellsInfo) {
            if (value.maxKnownSpellsCount != -1)
                result += value.spellLists.knownSpells.size

        }
        return result
    }

    fun getMaxKnownSpellsCount(character: Character): Int {
        var result = 0
        for ((_, value) in character.characterInfo.spellsInfo) {
            if (value.maxKnownSpellsCount != -1)
                result += value.maxKnownSpellsCount

        }
        return result
    }

    fun getKnownCantripsCount(character: Character): Int {
        var result = 0
        for ((_, value) in character.characterInfo.spellsInfo) {
            if (value.maxKnownCantripsCount != -1)
                result += value.spellLists.knownCantrips.size

        }
        return result
    }

    fun getMaxKnownCantripsCount(character: Character): Int {
        var result = 0
        for ((_, value) in character.characterInfo.spellsInfo) {
            if (value.maxKnownCantripsCount != -1)
                result += value.maxKnownCantripsCount

        }
        return result
    }

    fun isAllKnownIsPrepared(character: Character): Boolean {
        return getMaxPreparedSpellsCount(character) + getMaxPreparedCantripsCount(character) == 0
    }

    fun isAllKnown(character: Character): Boolean {
        return getMaxKnownSpellsCount(character) + getMaxKnownCantripsCount(character) == 0
    }

    data class Filters(
        var levels: MutableSet<String> = mutableSetOf(),
        var source: MutableSet<Source> = mutableSetOf(),
        var school: MutableSet<School> = mutableSetOf(),
        var substring: String = ""
    )

    private fun sortByLevel(spells: MutableList<Spell>) {
        spells.sortWith { first: Spell, second: Spell->
            second.data.level.toInt().compareTo(first.data.level.toInt())
        }
    }

    private fun checkFilters(spell: Spell, filters: Filters): Boolean {
        return checkSubstring(spell, filters.substring) &&
                checkFilter(spell.data.level, filters.levels.toList()) &&
                checkFilter(spell.data.sources, filters.source.map { it.sourceName }) &&
                checkFilter(spell.data.school, filters.school.map { it.shownName })

    }

    private fun checkSubstring(spell: Spell, substring: String): Boolean {
        for (property in spell.data.properties) {
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