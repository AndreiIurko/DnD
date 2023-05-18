package com.andreyyurko.dnd.data.abilities.classes.warlock

import com.andreyyurko.dnd.data.characterData.CharacterInfo
import com.andreyyurko.dnd.data.characterData.character.AbilityNode
import com.andreyyurko.dnd.data.spells.CharacterSpells
import com.andreyyurko.dnd.data.spells.SpellLists

var agonizingBlast = AbilityNode(
    name = "Мучительный взрыв",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Мучительный взрыв"] =
            "Когда вы накладываете мистический заряд, добавьте модификатор Харизмы к урону, причиняемому при попадании.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.additionalAbilities.contains("Мучительный взрыв")
    },
    description = "Когда вы накладываете мистический заряд, добавьте модификатор Харизмы к урону, причиняемому при попадании.\n",
)

var armorOfShadows = AbilityNode(
    name = "Доспех теней",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Доспех теней"] =
            "Вы можете неограниченно накладывать на себя заклинание доспехи мага, не тратя ячейки заклинаний и материальные компоненты.\n"
        abilities.spellsInfo["Заклинание доспеха теней"] = CharacterSpells(
            className = abilities.characterClass.className,
            maxKnownSpellsCount = 0,
            maxKnownCantripsCount = 0,
            spellLists = SpellLists(
                knownSpells = mutableSetOf("Доспехи мага")
            )
        )
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.additionalAbilities.contains("Доспех теней")
    },
    description = "Вы можете неограниченно накладывать на себя заклинание доспехи мага, не тратя ячейки заклинаний и материальные компоненты.\n",
)

var beastSpeech = AbilityNode(
    name = "Животная речь",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Животная речь"] =
            "Вы можете неограниченно накладывать заклинание разговор с животными, не тратя ячейки заклинаний.\n"
        abilities.spellsInfo["Заклинание животной речи"] = CharacterSpells(
            className = abilities.characterClass.className,
            maxKnownSpellsCount = 0,
            maxKnownCantripsCount = 0,
            spellLists = SpellLists(
                knownSpells = mutableSetOf("Разговор с животными")
            )
        )
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.additionalAbilities.contains("Животная речь")
    },
    description = "Вы можете неограниченно накладывать заклинание разговор с животными, не тратя ячейки заклинаний.\n",
)
var mapOfEldrichInvocations = mutableMapOf(
    Pair(agonizingBlast.name, agonizingBlast),
    Pair(armorOfShadows.name, armorOfShadows),
    Pair(beastSpeech.name, beastSpeech)
)