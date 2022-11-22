package com.andreyyurko.dnd.data.characters

import android.util.Log

open class AbilityNode(
    val name: String,
    var characterInfo: CharacterInfo,
    val alternatives: MutableMap<String, List<String>>,
    open val requirements: List<List<Triple<String, String, Int>>>,
    open val add_requirements: List<List<Triple<String, String, Int>>>
) {
    constructor(name: String) : this(
        name = name,
        characterInfo = CharacterInfo(),
        alternatives = mutableMapOf<String, List<String>>(),
        requirements = listOf<List<Triple<String, String, Int>>>(),
        add_requirements = listOf<List<Triple<String, String, Int>>>(),
    )
    fun merge (abilities: CharacterInfo): CharacterInfo {
        return merge(abilities, characterInfo)
    }
    fun isCorrect(): Boolean {
        return true
    }
    fun isAddable(): Boolean {
        return isCorrect() and true
    }
    fun showOptions(abilities: CharacterInfo, option_name: String): List<String> {
        val result: MutableList<String> = mutableListOf()
        for (option in alternatives[option_name]!!){
            if (mapOfAn[option]!!.isAddable()) result.add(option)
        }
        return result
    }
}

class AbilityNodeLevel (
    name: String,
    data: CharacterInfo,
    alternatives: MutableMap<String, List<String>>,
    requirements: List<List<Triple<String, String, Int>>>,
    add_requirements: List<List<Triple<String, String, Int>>>,
    var next_level: String?
) : AbilityNode(name, data, alternatives, requirements, add_requirements)

var baseAN: AbilityNode = AbilityNode(
    "base_an",
    CharacterInfo(),
    mutableMapOf(
        Pair("class", listOf("monk1", "barbarian1")),
    ),
    listOf(listOf()),
    listOf(listOf())
)

var barbarian1: AbilityNode = AbilityNodeLevel(
    "barbarian1",
    CharacterInfo(),
    mutableMapOf(),
    listOf(listOf()),
    listOf(listOf()),
    "barbarian2"
)

var barbarian2: AbilityNode = AbilityNodeLevel(
    "barbarian2",
    CharacterInfo(),
    mutableMapOf(),
    listOf(listOf()),
    listOf(listOf()),
    null
)

var monk1: AbilityNode = AbilityNodeLevel(
    "monk1",
    CharacterInfo(),
    mutableMapOf(),
    listOf(listOf()),
    listOf(listOf()),
    "monk2"
)

var monk2: AbilityNode = AbilityNodeLevel(
    "monk2",
    CharacterInfo(),
    mutableMapOf(),
    listOf(listOf()),
    listOf(listOf()),
    null
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

class CharacterAbilityNodeLevel(
    override val data: AbilityNodeLevel,
    chosen_alternatives: MutableMap<String, CharacterAbilityNode>,
    var next_level: CharacterAbilityNodeLevel?
) : CharacterAbilityNode(data, chosen_alternatives) {
    constructor(_data: AbilityNodeLevel) : this(
        data = _data,
        chosen_alternatives = mutableMapOf(),
        next_level = null
    )
    fun levelUp() {
        next_level = CharacterAbilityNodeLevel(mapOfAn[data.next_level] as AbilityNodeLevel)
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