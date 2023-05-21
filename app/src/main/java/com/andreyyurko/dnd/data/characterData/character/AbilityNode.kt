package com.andreyyurko.dnd.data.characterData.character

import com.andreyyurko.dnd.data.abilities.mapOfAn
import com.andreyyurko.dnd.data.characterData.Priority

open class AbilityNode(
    val name: String,
    val changesInCharacterInfo:
        (abilities: CharacterInfo) -> CharacterInfo,
    val getAlternatives: MutableMap<String, (abilities: CharacterInfo?) -> List<String>>,
    val requirements: (abilities: CharacterInfo) -> Boolean,
    open val addRequirements: List<List<Triple<String, String, Int>>> = listOf(listOf()),
    var description: String,
    val isNeedsToBeShown: Boolean = true,
    val priority: Priority = Priority.Basic,
    val actionForChoice: Map<String, (choice: String, abilities: CharacterInfo) -> CharacterInfo> = mutableMapOf(),
) {
    fun merge(abilities: CharacterInfo): CharacterInfo {
        return changesInCharacterInfo(abilities)
    }

    fun isAddable(abilities: CharacterInfo?): Boolean {
        if (abilities == null) return false
        return requirements(abilities)
    }

    fun showOptions(abilities: CharacterInfo, optionName: String): List<String> {
        val result: MutableList<String> = mutableListOf()
        for (option in getAlternatives[optionName]!!(abilities)) {
            mapOfAn[option]?.let {
                if (it.isAddable(abilities)) result.add(option)
            }
            actionForChoice[optionName]?.let {
                result.add(option)
            }
        }
        return result
    }
}