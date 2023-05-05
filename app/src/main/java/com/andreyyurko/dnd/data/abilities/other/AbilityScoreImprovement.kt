package com.andreyyurko.dnd.data.abilities.other

import com.andreyyurko.dnd.data.characterData.Ability
import com.andreyyurko.dnd.data.characterData.CharacterInfo
import com.andreyyurko.dnd.data.characterData.Priority
import com.andreyyurko.dnd.data.characterData.character.AbilityNode

var strengthAbilityImprovement: AbilityNode = AbilityNode(
    name = Ability.Strength.abilityName,
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.strength += 1
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = emptyList(),
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var dexterityAbilityImprovement: AbilityNode = AbilityNode(
    name = Ability.Dexterity.abilityName,
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.dexterity += 1
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = emptyList(),
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var constitutionAbilityImprovement: AbilityNode = AbilityNode(
    name = Ability.Constitution.abilityName,
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.constitution += 1
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = emptyList(),
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var intelligenceAbilityImprovement: AbilityNode = AbilityNode(
    name = Ability.Intelligence.abilityName,
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.intelligence += 1
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = emptyList(),
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var wisdomAbilityImprovement: AbilityNode = AbilityNode(
    name = Ability.Wisdom.abilityName,
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.wisdom += 1
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    isNeedsToBeShown = false,
    addRequirements = emptyList(),
    description = "",
    priority = Priority.DoFirst
)

var charismaAbilityImprovement: AbilityNode = AbilityNode(
    name = Ability.Charisma.abilityName,
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.charisma += 1
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = emptyList(),
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var abilityScoreImprovement: AbilityNode = AbilityNode(
    name = "Увеличение характеристик",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair(
            "firstAbility", {
                listOf(
                    strengthAbilityImprovement.name,
                    dexterityAbilityImprovement.name,
                    constitutionAbilityImprovement.name,
                    intelligenceAbilityImprovement.name,
                    wisdomAbilityImprovement.name,
                    charismaAbilityImprovement.name
                )
            }
        ),
        Pair(
            "secondAbility", {
                listOf(
                    strengthAbilityImprovement.name,
                    dexterityAbilityImprovement.name,
                    constitutionAbilityImprovement.name,
                    intelligenceAbilityImprovement.name,
                    wisdomAbilityImprovement.name,
                    charismaAbilityImprovement.name
                )
            }
        )
    ),
    requirements = { true },
    addRequirements = emptyList(),
    description = "",
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