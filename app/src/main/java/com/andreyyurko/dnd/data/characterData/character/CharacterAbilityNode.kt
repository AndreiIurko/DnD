package com.andreyyurko.dnd.data.characterData.character

import android.util.Log
import com.andreyyurko.dnd.data.abilities.mapOfAn
import com.andreyyurko.dnd.data.characterData.CharacterInfo
import com.andreyyurko.dnd.data.characterData.Priority

open class CharacterAbilityNode (
    open val data: AbilityNode,
    var chosen_alternatives: MutableMap<String, CharacterAbilityNode>,
    var character: Character? = null
) {
    constructor(_data: AbilityNode, character: Character?) : this(
        data = _data,
        chosen_alternatives = mutableMapOf(),
        character = character
    )
    fun merge(abilities: CharacterInfo, priority: Priority): CharacterInfo {
        var result: CharacterInfo = abilities
        if (data.priority == priority) {
            result = data.merge(result)
        }
        for ((_, value) in chosen_alternatives.entries) {
            result = value.merge(result, priority)
        }
        return result
    }
    fun showOptions(option_name: String): List<String> {
        return data.showOptions(character!!.characterInfo, option_name)
    }
    open fun makeChoice(option_name: String, choice: String, isFirst: Boolean = true) {
        mapOfAn[choice]?.let {
            chosen_alternatives[option_name] = CharacterAbilityNode(it, character)
            makeAllSimpleChoice(chosen_alternatives[option_name])
        }
        if (isFirst && character != null) {
            mergeAllAbilities(character!!)
        }
    }

    private fun makeAllSimpleChoice(can: CharacterAbilityNode?) {
        if (can == null) return

        for (optionName in can.data.alternatives.keys) {
            can.character?.let {
                val optionList = can.showOptions(optionName)
                if (optionList.size == 1 && can.chosen_alternatives[optionName] == null) {
                    can.makeChoice(optionName, optionList[0], false)
                }
            }
        }
    }
}