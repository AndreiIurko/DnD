package com.andreyyurko.dnd.data.abilities.characterclass

import com.andreyyurko.dnd.data.characters.AbilityNode
import com.andreyyurko.dnd.data.characters.CharacterInfo
import com.andreyyurko.dnd.data.characters.mapOfAn

var barbarian1: AbilityNode = AbilityNodeLevel(
    "barbarian1",
    CharacterInfo(
        characterClass = "Варвар",
        level = 1,
        proficiencyBonus = 2
    ),
    mutableMapOf(),
    listOf(listOf()),
    listOf(listOf()),
    "1-й уровень, способности варвара",
    "barbarian2"
)

var barbarian2: AbilityNode = AbilityNodeLevel(
    "barbarian2",
    CharacterInfo(),
    mutableMapOf(),
    listOf(listOf()),
    listOf(listOf()),
    "2-й уровень, способности варвара",
    null
)