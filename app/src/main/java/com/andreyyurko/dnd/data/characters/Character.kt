package com.andreyyurko.dnd.data.characters

import android.util.Log
import com.andreyyurko.dnd.data.abilities.baseAN
import com.andreyyurko.dnd.data.abilities.mapOfAn

open class AbilityNode(
    val name: String,
    val changesInCharacterInfo: (abilities: CharacterInfo) -> CharacterInfo,
    val alternatives: MutableMap<String, List<String>>,
    val requirements: (abilities: CharacterInfo) -> Boolean,
    open val add_requirements: List<List<Triple<String, String, Int>>> = listOf(listOf()),
    var description: String,
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
        for (option in alternatives[option_name]!!){
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

open class CharacterAbilityNode(
    open val data: AbilityNode,
    var chosen_alternatives: MutableMap<String, CharacterAbilityNode>
) {
    constructor(_data: AbilityNode) : this(
        data = _data,
        chosen_alternatives = mutableMapOf()
    )
    fun merge(abilities: CharacterInfo, priority: Priority): CharacterInfo {
        var result: CharacterInfo = abilities
        if (data.priority == priority) result = data.merge(result)
        for ((_, value) in chosen_alternatives.entries) {
            result = value.merge(result, priority)
        }
        return result
    }
    fun showOptions(abilities: CharacterInfo, option_name: String): List<String> {
        return data.showOptions(abilities, option_name)
    }
    open fun makeChoice(option_name: String, choice: String) {
        //maybe we need to add some check.
        //And we also need to delete old children if clever kotlin didn't do it

        Log.d("test", "option = $option_name")
        Log.d("test", "choice = $choice")
        mapOfAn[choice]?.let {
            chosen_alternatives[option_name] = CharacterAbilityNode(it)
        }
    }
}

data class Character(
    var id: Int,
    var name: String = "",
    var characterInfo: CharacterInfo = CharacterInfo(),
    var customAbilities: CharacterInfo = CharacterInfo(),
    var baseCAN: CharacterAbilityNode = CharacterAbilityNode(baseAN),
    var abilities: List<CharacterAbilityNode> = listOf(baseCAN),
    //var sortedByPriority: SortedSet<CharacterAbilityNode> = sortedSetOf()
)

fun mergeAllAbilities(character: Character): Character {
    var characterInfo = CharacterInfo()
    characterInfo.currentState = character.characterInfo.currentState
    characterInfo = mergeCharacterInfo(characterInfo, character.customAbilities)
    for (priority in Priority.values()) {
        for (ability in character.abilities) {
            characterInfo = ability.merge(characterInfo, priority)
        }
    }

    character.characterInfo = characterInfo
    return character
}