package com.andreyyurko.dnd.data.abilities.classes.bard

import com.andreyyurko.dnd.data.characterData.Action
import com.andreyyurko.dnd.data.characterData.ActionType
import com.andreyyurko.dnd.data.characterData.CharacterInfo
import com.andreyyurko.dnd.data.characterData.Classes
import com.andreyyurko.dnd.data.characterData.character.AbilityNode
import com.andreyyurko.dnd.data.spells.CharacterSpells
import com.andreyyurko.dnd.data.spells.SpellLists


var collegeOfWhispers = AbilityNode(
    name = "Коллегия шёпотов",
    changesInCharacterInfo = { abilities: CharacterInfo -> abilities },
    alternatives = mutableMapOf(
        //Pair("first", listOf(blessingsOfKnowledge.name)),
        //Pair("second", listOf(KnowledgeDomainSpells.name)),
        //Pair("third", listOf(channelDivinityKnowledgeOfTheAges.name)),
        //Pair("fourth", listOf(channelDivinityReadThoughts.name)),
        //Pair("fifth", listOf(potentSpellcasting.name)),
        //Pair("sizth", listOf(visionsOfThePast.name))
    ),
    requirements = { true },
    description = "",
    isNeedsToBeShown = false
)

var mapOfCollegeOfWhispersAbilities: MutableMap<String, AbilityNode> = mutableMapOf(
    Pair(collegeOfWhispers.name, collegeOfWhispers),
)