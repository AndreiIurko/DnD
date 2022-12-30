package com.andreyyurko.dnd.data.abilities.other

import android.util.Log
import com.andreyyurko.dnd.data.characters.Ability
import com.andreyyurko.dnd.data.characters.AbilityNode
import com.andreyyurko.dnd.data.characters.CharacterInfo

var strengthAbilityImprovement: AbilityNode = AbilityNode(
    name = Ability.Strength.abilityName,
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.strengthBonus += 1
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {true},
    add_requirements = emptyList(),
    description = ""
)

var dexterityAbilityImprovement: AbilityNode = AbilityNode(
    name = Ability.Dexterity.abilityName,
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.dexterityBonus += 1
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {true},
    add_requirements = emptyList(),
    description = ""
)

var constitutionAbilityImprovement: AbilityNode = AbilityNode(
    name = Ability.Constitution.abilityName,
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.constitutionBonus += 1
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {true},
    add_requirements = emptyList(),
    description = ""
)

var intelligenceAbilityImprovement: AbilityNode = AbilityNode(
    name = Ability.Intelligence.abilityName,
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.intelligenceBonus += 1
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {true},
    add_requirements = emptyList(),
    description = ""
)

var wisdomAbilityImprovement: AbilityNode = AbilityNode(
    name = Ability.Wisdom.abilityName,
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.wisdomBonus += 1
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {true},
    add_requirements = emptyList(),
    description = ""
)

var charismaAbilityImprovement: AbilityNode = AbilityNode(
    name = Ability.Charisma.abilityName,
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.charismaBonus += 1
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {true},
    add_requirements = emptyList(),
    description = ""
)

var abilityScoreImprovement: AbilityNode = AbilityNode(
    name = "Увеличение характеристик",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        Log.d("test", "increase ability")
        abilities
    },
    alternatives = mutableMapOf(
        Pair("firstAbility", listOf(
            strengthAbilityImprovement.name,
            dexterityAbilityImprovement.name,
            constitutionAbilityImprovement.name,
            intelligenceAbilityImprovement.name,
            wisdomAbilityImprovement.name,
            charismaAbilityImprovement.name
        )),
        Pair("secondAbility", listOf(
            strengthAbilityImprovement.name,
            dexterityAbilityImprovement.name,
            constitutionAbilityImprovement.name,
            intelligenceAbilityImprovement.name,
            wisdomAbilityImprovement.name,
            charismaAbilityImprovement.name
        ))
    ),
    requirements = {true},
    add_requirements = emptyList(),
    description = ""
)

var mapOfAbilityScoreImprovement = mutableMapOf(
    Pair(strengthAbilityImprovement.name, strengthAbilityImprovement),
    Pair(dexterityAbilityImprovement.name, dexterityAbilityImprovement),
    Pair(constitutionAbilityImprovement.name, constitutionAbilityImprovement),
    Pair(intelligenceAbilityImprovement.name, intelligenceAbilityImprovement),
    Pair(wisdomAbilityImprovement.name, wisdomAbilityImprovement),
    Pair(charismaAbilityImprovement.name, charismaAbilityImprovement),
    Pair(abilityScoreImprovement.name, abilityScoreImprovement)
)