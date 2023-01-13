package com.andreyyurko.dnd.data.characterData.character

import com.andreyyurko.dnd.data.abilities.mapOfAn
import com.andreyyurko.dnd.data.characterData.CharacterInfo
import com.andreyyurko.dnd.data.characterData.Priority

open class AbilityNode(
    val name: String,
    val changesInCharacterInfo: (abilities: CharacterInfo) -> CharacterInfo,
    val alternatives: MutableMap<String, List<String>>,
    val requirements: (abilities: CharacterInfo) -> Boolean,
    open val add_requirements: List<List<Triple<String, String, Int>>> = listOf(listOf()),
    var description: String,
    val isNeedsToBeShown: Boolean = true,
    val priority: Priority = Priority.Basic
) {
    constructor(name: String) : this(
        name = name,
        changesInCharacterInfo = {abilities: CharacterInfo -> abilities},
        alternatives = mutableMapOf<String, List<String>>(),
        requirements = { true },
        add_requirements = listOf<List<Triple<String, String, Int>>>(),
        description = ""
    )
    fun merge (abilities: CharacterInfo): CharacterInfo {
        return changesInCharacterInfo(abilities)
    }
    fun isCorrect(abilities: CharacterInfo): Boolean {
        return requirements(abilities)
    }
    fun isAddable(abilities: CharacterInfo): Boolean {
        return isCorrect(abilities) and true
    }
    fun showOptions(abilities: CharacterInfo, option_name: String): List<String> {
        val result: MutableList<String> = mutableListOf()
        for (option in alternatives[option_name]!!) {
            if (mapOfAn[option]!!.isAddable(abilities)) result.add(option)
        }
        return result
    }

    fun showOptions(option_name: String) : List<String> {
        val result: MutableList<String> = mutableListOf()
        for (option in alternatives[option_name]!!) {
            result.add(option)
        }
        return result
    }
}