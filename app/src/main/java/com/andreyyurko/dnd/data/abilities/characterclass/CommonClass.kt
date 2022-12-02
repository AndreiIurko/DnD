package com.andreyyurko.dnd.data.abilities.characterclass

import com.andreyyurko.dnd.data.characters.AbilityNode
import com.andreyyurko.dnd.data.characters.CharacterAbilityNode
import com.andreyyurko.dnd.data.characters.CharacterInfo
import com.andreyyurko.dnd.data.characters.mapOfAn

class AbilityNodeLevel (
    name: String,
    data: CharacterInfo,
    alternatives: MutableMap<String, List<String>>,
    requirements: List<List<Triple<String, String, Int>>>,
    add_requirements: List<List<Triple<String, String, Int>>>,
    var next_level: String?
) : AbilityNode(name, data, alternatives, requirements, add_requirements)

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