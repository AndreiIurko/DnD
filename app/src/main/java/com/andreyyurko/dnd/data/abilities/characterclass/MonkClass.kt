package com.andreyyurko.dnd.data.abilities.characterclass

import com.andreyyurko.dnd.data.characters.AbilityNode
import com.andreyyurko.dnd.data.characters.CharacterInfo

var monk1: AbilityNode = AbilityNodeLevel(
    "monk1",
    {abilities: CharacterInfo ->
        abilities.characterClass = "monk"
        val old_ac = abilities.ac
        abilities.ac = {
            abilities_data: CharacterInfo ->
            old_ac(abilities_data) + (abilities_data.wisdomBonus - 10) / 2
        }
        abilities.level++
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