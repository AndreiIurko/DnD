package com.andreyyurko.dnd.data.abilities.classes.cleric

import com.andreyyurko.dnd.data.abilities.classes.fighter.*
import com.andreyyurko.dnd.data.characterData.CharacterInfo
import com.andreyyurko.dnd.data.characterData.Classes
import com.andreyyurko.dnd.data.characterData.character.AbilityNode

var blessingsOfKnowledge = AbilityNode(
    name = "Благословение знаний",
    changesInCharacterInfo = { abilities: CharacterInfo -> abilities },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 1 && abilities.characterClass == Classes.Cleric
    },
    description = "Вы можете выучить два языка на свой выбор. Вы также получаете владение двумя навыками из следующего списка: История, Магия, Природа, Религия.\n" +
            "\n" +
            "Ваш бонус мастерства удваивается для всех проверок характеристик при использовании этих навыков.\n"
)

var KnowledgeDomain = AbilityNode(
    name = "Домен знаний",
    changesInCharacterInfo = { abilities: CharacterInfo -> abilities },
    alternatives = mutableMapOf(
        Pair("first", listOf(blessingsOfKnowledge.name)),
        //Pair("second", listOf(studentOfWar.name)),
        //Pair("third", listOf(knowYourEnemy.name)),
        //Pair("fourth", listOf(improvedCombatSuperiority.name)),
        //Pair("fifth", listOf(relentless.name))
    ),
    requirements = { true },
    description = "",
    isNeedsToBeShown = false
)

var mapOfKnowledgeDomainAbilities: MutableMap<String, AbilityNode> = mutableMapOf(
    Pair(blessingsOfKnowledge.name, blessingsOfKnowledge),
    Pair(KnowledgeDomain.name, KnowledgeDomain),
)