package com.andreyyurko.dnd.data.abilities.characterclass

import com.andreyyurko.dnd.data.characters.AbilityNode
import com.andreyyurko.dnd.data.characters.CharacterInfo
import com.andreyyurko.dnd.data.characters.mapOfAn

var barbarian1: AbilityNode = AbilityNodeLevel(
    "barbarian1",
    {abilities: CharacterInfo ->
        abilities.characterClass = abilities.characterClass + "Варвар"
        abilities.level = abilities.level + 1
        abilities.proficiencyBonus = abilities.proficiencyBonus + 1
        abilities
    },
    /*CharacterInfo(
        characterClass = "Варвар",
        level = 1,
        proficiencyBonus = 2
    ),*/
    mutableMapOf(),
    {abilities: CharacterInfo -> false},
    listOf(listOf()),
    "1-й уровень, способности варвара",
    "barbarian2"
)

var barbarian2: AbilityNode = AbilityNodeLevel(
    "barbarian2",
    {abilities: CharacterInfo -> abilities},
    mutableMapOf(),
    {abilities: CharacterInfo -> true},
    listOf(listOf()),
    "2-й уровень, способности варвара",
    null
)