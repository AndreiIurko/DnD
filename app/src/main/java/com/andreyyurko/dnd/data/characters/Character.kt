package com.andreyyurko.dnd.data.characters

import android.util.Log
import com.andreyyurko.dnd.data.abilities.characterclass.*

open class AbilityNode(
    val name: String,
    var characterInfo: CharacterInfo,
    val alternatives: MutableMap<String, List<String>>,
    val requirements: (abilities: CharacterInfo) -> Boolean,
    open val add_requirements: List<List<Triple<String, String, Int>>>,
    var description: String
) {
    constructor(name: String) : this(
        name = name,
        characterInfo = CharacterInfo(),
        alternatives = mutableMapOf<String, List<String>>(),
        requirements = { true },
        add_requirements = listOf<List<Triple<String, String, Int>>>(),
        description = ""
    )
    fun merge (abilities: CharacterInfo): CharacterInfo {
        return merge(abilities, characterInfo)
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
}

var baseAN: AbilityNode = AbilityNode(
    "base_an",
    CharacterInfo(),
    mutableMapOf(
        Pair("class", listOf("monk1", "barbarian1")),
    ),
    {true},
    listOf(listOf()),
    description = "Base Ability Node, root of all AN"
)

var mapOfAn: MutableMap<String, AbilityNode> = mutableMapOf(
    Pair(baseAN.name, baseAN),
    Pair(barbarian1.name, barbarian1),
    Pair(barbarian2.name, barbarian2),
    Pair(monk1.name, monk1),
    Pair(monk2.name, monk2)
)

open class CharacterAbilityNode(
    open val data: AbilityNode,
    var chosen_alternatives: MutableMap<String, CharacterAbilityNode>
) {
    constructor(_data: AbilityNode) : this(
        data = _data,
        chosen_alternatives = mutableMapOf()
    )
    fun merge(abilities: CharacterInfo): CharacterInfo {
        var result: CharacterInfo = abilities
        result = data.merge(result)
        for ((_, value) in chosen_alternatives.entries){
            result = value.merge(result)
        }
        return result
    }
    fun showOptions(abilities: CharacterInfo, option_name: String): List<String>? {
        return data.showOptions(abilities, option_name)
    }
    fun makeChoice(option_name: String, choice: String){
        //maybe we need to add some check.
        //And we also need to delete old children if clever kotlin didn't do it
        mapOfAn[choice]?.let {
            chosen_alternatives[option_name] = CharacterAbilityNode(it)
        }
    }
}

data class Character(
    var id: Int,
    var name: String = "",
    var characterInfo: CharacterInfo = CharacterInfo(),
    var customAbilities: CharacterAbilityNode = CharacterAbilityNode(AbilityNode("customRoot")),
    var classAbilities: CharacterAbilityNode = CharacterAbilityNode(baseAN),
    var abilities: List<CharacterAbilityNode> = listOf(customAbilities, classAbilities),
)

fun mergeAllAbilities(character: Character): Character {
    var characterInfo = CharacterInfo()
    for (ability in character.abilities) {
        characterInfo = ability.merge(characterInfo)
    }
    character.characterInfo = characterInfo
    return character
}
