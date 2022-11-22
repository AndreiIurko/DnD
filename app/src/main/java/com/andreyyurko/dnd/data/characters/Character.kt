package com.andreyyurko.dnd.data.characters

import android.util.Log
import com.andreyyurko.dnd.data.abilities.custom.CustomAbilities


data class Character(
    var id: Int,
    var name: String = "",
    var characterInfo: CharacterInfo = CharacterInfo(),
    var customAbilities: CustomAbilities = CustomAbilities(),
    var abilities: List<CharacterAbility> = listOf()
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