package com.andreyyurko.dnd.data.abilities.characterclass

import com.andreyyurko.dnd.data.characters.AbilityNode
import com.andreyyurko.dnd.data.characters.CharacterInfo

var monk1: AbilityNode = AbilityNodeLevel(
    "monk1",
    {abilities: CharacterInfo ->
        abilities.characterClass = "monk"
        abilities.ac = abilities.ac + (abilities.wisdomBonus - 10) / 2
        abilities.level += 1
        abilities
    },
    /*CharacterInfo(
        characterClass = "Монах",
        level = 1,
        proficiencyBonus = 2
    ),*/
    mutableMapOf(),
    {abilities: CharacterInfo -> true},
    listOf(listOf()),
    "1-й уровень, способности монаха",
    "monk2"
)

var monk2: AbilityNode = AbilityNodeLevel(
    "monk2",
    {abilities: CharacterInfo -> abilities},
    mutableMapOf(),
    {abilities: CharacterInfo -> true},
    listOf(listOf()),
    "2-й уровень, способности монаха",
    null
)