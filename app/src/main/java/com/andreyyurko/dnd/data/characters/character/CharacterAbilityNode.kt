package com.andreyyurko.dnd.data.characters.character

import android.util.Log
import com.andreyyurko.dnd.data.abilities.mapOfAn
import com.andreyyurko.dnd.data.characters.CharacterInfo
import com.andreyyurko.dnd.data.characters.Priority

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
        if (data.priority == priority) {
            result = data.merge(result)
            Log.d("skill", data.name)
        }
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