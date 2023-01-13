package com.andreyyurko.dnd.data.characters.character

import com.andreyyurko.dnd.data.abilities.baseAN
import com.andreyyurko.dnd.data.characters.CharacterInfo
import com.andreyyurko.dnd.data.characters.Priority
import com.andreyyurko.dnd.data.characters.mergeCharacterInfo

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

fun abilityToModifier(ability: Int): Int {
    return (ability - 10) / 2
}