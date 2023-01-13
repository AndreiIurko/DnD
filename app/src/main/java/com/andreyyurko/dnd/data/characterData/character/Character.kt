package com.andreyyurko.dnd.data.characterData.character

import android.util.Log
import com.andreyyurko.dnd.data.abilities.baseAN
import com.andreyyurko.dnd.data.characterData.CharacterInfo
import com.andreyyurko.dnd.data.characterData.Priority
import com.andreyyurko.dnd.data.characterData.mergeCharacterInfo

class Character(
    var id: Int,
    var name: String = "",
    var characterInfo: CharacterInfo = CharacterInfo(),
    var customAbilities: CharacterInfo = CharacterInfo(),
    var baseCAN: CharacterAbilityNode = CharacterAbilityNode(baseAN, null),
) {
    init {
        this.baseCAN.character = this
    }
}

fun mergeAllAbilities(character: Character) {
    val abilities: List<CharacterAbilityNode> = listOf(character.baseCAN)
    var characterInfo = CharacterInfo()
    characterInfo.currentState = character.characterInfo.currentState
    characterInfo = mergeCharacterInfo(characterInfo, character.customAbilities)
    for (priority in Priority.values()) {
        for (ability in abilities) {
            characterInfo = ability.merge(characterInfo, priority)
        }
    }
    character.characterInfo = characterInfo
}

fun abilityToModifier(ability: Int): Int {
    return (ability - 10) / 2
}