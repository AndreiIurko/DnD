package com.andreyyurko.dnd.data.characters

import android.util.Log

fun merge(characterInfoFirst: CharacterInfo, characterInfoSecond: CharacterInfo): CharacterInfo {
    val resultCharacterInfo = CharacterInfo()

    resultCharacterInfo.characterClass = characterInfoFirst.characterClass + characterInfoSecond.characterClass
    resultCharacterInfo.level = characterInfoFirst.level + characterInfoSecond.level
    resultCharacterInfo.race = characterInfoFirst.race + characterInfoSecond.race
    resultCharacterInfo.strengthBonus = characterInfoFirst.strengthBonus + characterInfoSecond.strengthBonus
    resultCharacterInfo.dexterityBonus = characterInfoFirst.dexterityBonus + characterInfoSecond.dexterityBonus
    resultCharacterInfo.constitutionBonus = characterInfoFirst.constitutionBonus + characterInfoSecond.constitutionBonus
    resultCharacterInfo.intelligenceBonus = characterInfoFirst.intelligenceBonus + characterInfoSecond.intelligenceBonus
    resultCharacterInfo.wisdomBonus = characterInfoFirst.wisdomBonus + characterInfoSecond.wisdomBonus
    resultCharacterInfo.charismaBonus = characterInfoFirst.charismaBonus + characterInfoSecond.charismaBonus
    resultCharacterInfo.proficiencyBonus = characterInfoFirst.proficiencyBonus + characterInfoSecond.proficiencyBonus
    resultCharacterInfo.speed = characterInfoFirst.speed + characterInfoSecond.speed
    resultCharacterInfo.initiativeBonus = characterInfoFirst.initiativeBonus + characterInfoSecond.initiativeBonus
    resultCharacterInfo.ac = characterInfoFirst.ac + characterInfoSecond.ac
    resultCharacterInfo.hp = characterInfoFirst.hp + characterInfoSecond.hp

    return resultCharacterInfo
}