package com.andreyyurko.dnd.data.characters

import android.util.Log
import com.andreyyurko.dnd.data.abilities.custom.CustomAbilities
import javax.inject.Inject

open class AbilityNode @Inject constructor(
    private var name: String,
    private var data: CharacterInfo,
    private var alternatives: Map<String, List<String>>,
    private var requirements: List<List<Triple<String, String, Int>>>,
    private var add_requirements: List<List<Triple<String, String, Int>>>
) {
    fun merge (abilities: CharacterInfo): CharacterInfo {
        return merge(abilities, data)
    }
    fun is_correct(): Boolean {
        return true
    }
    fun is_addable(): Boolean {
        return is_correct() and true
    }
    fun show_options(abilities: CharacterInfo, option_name: String): List<String>? {
        var result: MutableList<String> = mutableListOf()
        for (option in alternatives[option_name]!!){
            if (list_of_an[option]!!.is_addable()) result.add(option)
        }
        return result
    }
}

class AbilityNodeLevel @Inject constructor(
    private var name: String,
    private var data: CharacterInfo,
    private var alternatives: Map<String, List<String>>,
    private var requirements: List<List<Triple<String, String, Int>>>,
    private var add_requirements: List<List<Triple<String, String, Int>>>,
    var next_level: String?
) : AbilityNode(name, data, alternatives, requirements, add_requirements)

var base_an: AbilityNode = AbilityNode(
    "base_an",
    CharacterInfo(),
    mapOf(
        Pair("class", listOf("monk1", "barbarian1"))
    ),
    listOf(listOf()),
    listOf(listOf())
)

var barbarian1: AbilityNode = AbilityNodeLevel(
    "barbarian1",
    CharacterInfo(),
    mapOf(),
    listOf(listOf()),
    listOf(listOf()),
    "barbarian2"
)

var barbarian2: AbilityNode = AbilityNodeLevel(
    "barbarian2",
    CharacterInfo(),
    mapOf(),
    listOf(listOf()),
    listOf(listOf()),
    null
)

var monk1: AbilityNode = AbilityNodeLevel(
    "monk1",
    CharacterInfo(),
    mapOf(),
    listOf(listOf()),
    listOf(listOf()),
    "monk2"
)

var monk2: AbilityNode = AbilityNodeLevel(
    "monk2",
    CharacterInfo(),
    mapOf(),
    listOf(listOf()),
    listOf(listOf()),
    null
)

var list_of_an: Map<String, AbilityNode> = mapOf(
    Pair("base_an", base_an),
    Pair("barbarian1", barbarian1),
    Pair("barbarian2", barbarian2),
    Pair("monk1", monk1),
    Pair("monk2", monk2)
)

open class CharacterAbilityNode @Inject constructor(
    private var data: AbilityNode,
    private var chosen_alternatives: MutableMap<String, CharacterAbilityNode>
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
    fun show_options(abilities: CharacterInfo, option_name: String): List<String>? {
        return data.show_options(abilities, option_name)
    }
    fun make_choice(option_name: String, choice: String){
        //may be we need to add some check.
        //And we also need to delete old children if clever kotlin didn't do it
        list_of_an[choice]?.let { CharacterAbilityNode(it) }
            ?.let { chosen_alternatives.put(option_name, it) }
    }
}

class CharacterAbilityNodeLevel @Inject constructor(
    private var data: AbilityNodeLevel,
    private var chosen_alternatives: MutableMap<String, CharacterAbilityNode>,
    private var next_level: CharacterAbilityNodeLevel?
) : CharacterAbilityNode(data, chosen_alternatives) {
    constructor(_data: AbilityNodeLevel) : this(
        data = _data,
        chosen_alternatives = mutableMapOf(),
        next_level = null
    )
    fun levelup() {
        next_level = CharacterAbilityNodeLevel(list_of_an[data.next_level] as AbilityNodeLevel)
    }
}

data class Character(
    var id: Int,
    var name: String = "",
    var characterInfo: CharacterInfo = CharacterInfo(),
    var customAbilities: CustomAbilities = CustomAbilities(),
    var abilities: List<CharacterAbility> = listOf(),
    var class_abilities: CharacterAbilityNode = CharacterAbilityNode(base_an)
)

fun mergeAllAbilities(character: Character): Character {
    character.abilities = listOf(character.customAbilities)
    Log.d("mergeAllAb", character.abilities.toString())
    var characterInfo = CharacterInfo()
    for (ability in character.abilities) {
        Log.d("mergeAllAbItem", ability.toString())
        characterInfo = mergeAllCharacterInfo(characterInfo, ability)
    }
    character.characterInfo = characterInfo
    Log.d("mergeAllAbRes", character.characterInfo.toString())
    return character
}

fun mergeAllCharacterInfo(characterInfo: CharacterInfo, ability: CharacterAbility): CharacterInfo {
    Log.d("mergeAllChar", characterInfo.toString())
    var newCharacterInfo = characterInfo
    for (nextAbility in ability.nextAbilities) {
        newCharacterInfo = mergeAllCharacterInfo(newCharacterInfo, nextAbility)
    }
    newCharacterInfo = merge(newCharacterInfo, ability.characterInfo)
    Log.d("mergeAllCharRes", newCharacterInfo.toString())
    return newCharacterInfo
}