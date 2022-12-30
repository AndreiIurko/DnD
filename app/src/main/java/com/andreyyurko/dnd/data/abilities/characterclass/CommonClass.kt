package com.andreyyurko.dnd.data.abilities.characterclass

import android.util.Log
import com.andreyyurko.dnd.data.abilities.mapOfAn
import com.andreyyurko.dnd.data.characters.AbilityNode
import com.andreyyurko.dnd.data.characters.CharacterAbilityNode
import com.andreyyurko.dnd.data.characters.CharacterInfo
import com.andreyyurko.dnd.data.characters.Priority

// For all levels alternatives: it is important that
class AbilityNodeLevel (
    name: String,
    changesInCharacterInfo: (abilities: CharacterInfo) -> CharacterInfo,
    alternatives: MutableMap<String, List<String>>,
    requirements: (abilities: CharacterInfo) -> Boolean,
    add_requirements: List<List<Triple<String, String, Int>>>,
    description: String,
    var next_level: String?,
) : AbilityNode(name, changesInCharacterInfo, alternatives, requirements, add_requirements, description, Priority.DoAsSoonAsPossible)

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

    fun makeChoice() {
        for (entries in data.alternatives.entries) {
            // check if ability is next level
            if (entries.value[0] == data.next_level) continue

            // choose all abilities
            mapOfAn[entries.value[0]]?.let {
                chosen_alternatives[entries.key] = CharacterAbilityNode(
                    it
                )
            }
        }
    }

    fun levelUp() {
        val nextAN = mapOfAn[data.next_level]
        nextAN?.let {
            next_level = CharacterAbilityNodeLevel(nextAN as AbilityNodeLevel)
            chosen_alternatives["nextLevel"] = next_level as CharacterAbilityNode
        }

    }
}