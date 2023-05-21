package com.andreyyurko.dnd.data.characterData.character

import android.graphics.Bitmap
import com.andreyyurko.dnd.data.abilities.baseAN
import com.andreyyurko.dnd.data.characterData.Note
import com.andreyyurko.dnd.data.characterData.Priority

data class Character(
    var id: Int,
    var image: Bitmap? = null,
    var name: String = "",
    var characterInfo: CharacterInfo = CharacterInfo(),
    var customAbilities: CharacterInfo = CharacterInfo(),
    var baseCAN: CharacterAbilityNode = CharacterAbilityNode(baseAN, null),
    var notes: MutableList<Note> = mutableListOf()
) {
    init {
        this.baseCAN.character = this
    }
}

fun mergeAllAbilities(character: Character) {
    val abilities: List<CharacterAbilityNode> = listOf(character.baseCAN)
    var characterInfo = CharacterInfo()

    characterInfo.currentState = character.characterInfo.currentState
    characterInfo.inventory = character.characterInfo.inventory
    characterInfo.spellsInfo = character.characterInfo.spellsInfo

    characterInfo = mergeCharacterInfo(characterInfo, character.customAbilities)
    for (priority in Priority.values()) {
        for (ability in abilities) {
            characterInfo = ability.merge(characterInfo, priority)
        }
    }
    character.characterInfo = characterInfo
}

fun abilityToModifier(ability: Int): Int {
    return Math.floorDiv(ability - 10, 2)
}