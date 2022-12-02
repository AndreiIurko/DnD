package com.andreyyurko.dnd.data.abilities.characterclass

import com.andreyyurko.dnd.data.characters.AbilityNode
import com.andreyyurko.dnd.data.characters.CharacterInfo

var monk1: AbilityNode = AbilityNodeLevel(
    "monk1",
    CharacterInfo(
        characterClass = "Монах",
        level = 1,
        proficiencyBonus = 2
    ),
    mutableMapOf(),
    listOf(listOf()),
    listOf(listOf()),
    "1-й уровень, способности монаха",
    "monk2"
)

var monk2: AbilityNode = AbilityNodeLevel(
    "monk2",
    CharacterInfo(),
    mutableMapOf(),
    listOf(listOf()),
    listOf(listOf()),
    "2-й уровень, способности монаха",
    null
)