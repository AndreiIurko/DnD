package com.andreyyurko.dnd.data.abilities.characterclass

import com.andreyyurko.dnd.data.characters.CharacterInfo
import com.andreyyurko.dnd.data.characters.Classes
import com.andreyyurko.dnd.data.characters.character.AbilityNode

var barbarian1: AbilityNode = AbilityNodeLevel(
    "Варвар_1",
    {abilities: CharacterInfo ->
        abilities.characterClass = Classes.Barbarian
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
    {abilities: CharacterInfo -> true},
    listOf(listOf()),
    "1-й уровень, способности варвара",
    "barbarian2"
)

var barbarian2: AbilityNode = AbilityNodeLevel(
    "Варвар_2",
    {abilities: CharacterInfo -> abilities},
    mutableMapOf(),
    {abilities: CharacterInfo -> true},
    listOf(listOf()),
    "2-й уровень, способности варвара",
    null
)

var mapOfBarbarianAbilities: MutableMap<String, AbilityNode> = mutableMapOf(
    Pair(barbarian1.name, barbarian1),
    Pair(barbarian2.name, barbarian2),
)