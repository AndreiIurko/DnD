package com.andreyyurko.dnd.data.characterData.character

import com.andreyyurko.dnd.data.abilities.mapOfAn
import com.andreyyurko.dnd.data.characterData.Priority

open class CharacterAbilityNode(
    open val data: AbilityNode,
    var chosenAlternatives: MutableMap<String, CharacterAbilityNode> = mutableMapOf(),
    var character: Character? = null,
    var chosenAlternativesForActions: MutableMap<String, String> = mutableMapOf()
) {
    constructor(_data: AbilityNode, character: Character?) : this(
        data = _data,
        chosenAlternatives = mutableMapOf(),
        character = character
    )

    fun merge(abilities: CharacterInfo, priority: Priority): CharacterInfo {
        var result: CharacterInfo = abilities
        if (data.priority == priority) {
            result = data.merge(result)
            for ((option, choice) in chosenAlternativesForActions) {
                data.actionForChoice[option]?.let {
                    result = it(choice, result)
                }
            }
        }

        for ((_, value) in chosenAlternatives.entries) {
            result = value.merge(result, priority)
        }
        return result
    }

    fun showOptions(option_name: String): List<String> {
        character?.characterInfo?.let {
            return data.showOptions(it, option_name)
        }
        return emptyList()
    }

    fun showAllOptions(optionName: String): List<String> {
        return data.getAlternatives[optionName]?.invoke(character?.characterInfo) ?: listOf()
    }

    open fun makeChoice(option_name: String, choice: String, isFirst: Boolean = true) {
        mapOfAn[choice]?.let {
            chosenAlternatives[option_name] = CharacterAbilityNode(it, character)
            makeAllSimpleChoice(chosenAlternatives[option_name])
        }
        data.actionForChoice[option_name]?.let {
            chosenAlternativesForActions[option_name] = choice
        }
        if (isFirst && character != null) {
            mergeAllAbilities(character!!)
        }
    }

    private fun makeAllSimpleChoice(can: CharacterAbilityNode?) {
        if (can == null) return

        for (optionName in can.data.getAlternatives.keys) {
            can.character?.let {
                val optionList = can.showOptions(optionName)
                if (optionList.size == 1 && can.chosenAlternatives[optionName] == null) {
                    can.makeChoice(optionName, optionList[0], false)
                }
            }
        }
    }
}

fun checkIfSomeRequirementsSatisfied(can: CharacterAbilityNode?) {
    if (can == null) return
    for ((optionName, listOfOptions) in can.data.getAlternatives) {
        if (can.chosenAlternatives[optionName] == null && listOfOptions(can.character?.characterInfo).size == 1) {
            if (mapOfAn[listOfOptions(can.character?.characterInfo)[0]]!!.isAddable(can.character?.characterInfo)) {
                can.makeChoice(optionName, listOfOptions(can.character?.characterInfo)[0], false)
            }
        }
    }

    for (next_can in can.chosenAlternatives.values) {
        checkIfSomeRequirementsSatisfied(next_can)
    }
}