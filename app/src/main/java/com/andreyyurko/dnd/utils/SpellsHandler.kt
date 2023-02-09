package com.andreyyurko.dnd.utils

import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.data.SpellSpecificLanguage
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

    fun getKnownSpells(character: Character, filters: Filters): MutableSet<SpellSpecificLanguage> {
        return if (character.characterInfo.spellsInfo.maxKnownSpellsCount == -1) {
            val result : MutableSet<SpellSpecificLanguage> = mutableSetOf()
            for (spell in getClassSpells(character)) {
                if (!spell.level.contains("заговор")) {
                    if (spell.level.toInt() <= character.characterInfo.level / 2) {
                        result.add(spell)
                    }
                }
                else {
                    result.add(spell)
                }
            }
            return result
        } else {
            val result : MutableSet<SpellSpecificLanguage> = mutableSetOf()
            for (spellName in character.characterInfo.spellsInfo.spellLists.knownSpells) {
                result.add(allSpells[spellName]!!)
            }
            result
        }
    }

    fun getPreparedSpells(character: Character): MutableSet<SpellSpecificLanguage> {
        val result : MutableSet<SpellSpecificLanguage> = mutableSetOf()
        for (spellName in character.characterInfo.spellsInfo.spellLists.preparedSpells) {
            result.add(allSpells[spellName]!!)
        }
        for (spellName in character.characterInfo.spellsInfo.spellLists.preparedCantrips) {
            result.add(allSpells[spellName]!!)
        }
        return result
    }

    fun getPreparedSpellsCount(character: Character): Int {
        return character.characterInfo.spellsInfo.spellLists.preparedSpells.size
    }

    fun getMaxPreparedSpellsCount(character: Character): Int {
        return  character.characterInfo.spellsInfo.maxPreparedSpellsCount
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
        }
        else {
            addPreparedSpell(character, spell.name)
        }
    }

    fun removeSpell(character: Character, spell: SpellSpecificLanguage) {
        if (spell.level.toInt() == 0) {
            removePreparedCantrip(character, spell.name)
        }
        else {
            removePreparedSpell(character, spell.name)
        }
    }

    data class Filters(
        var levels: MutableSet<String> = mutableSetOf()
    )
}