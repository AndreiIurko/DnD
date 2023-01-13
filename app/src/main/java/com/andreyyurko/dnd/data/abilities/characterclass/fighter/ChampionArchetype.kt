package com.andreyyurko.dnd.data.abilities.characterclass.fighter

import com.andreyyurko.dnd.data.characters.CharacterInfo
import com.andreyyurko.dnd.data.characters.character.AbilityNode


var champion = AbilityNode(
    name = "Чемпион",
    changesInCharacterInfo = {abilities: CharacterInfo -> abilities },
    alternatives = mutableMapOf(),
    requirements = {true},
    description = ""
)

var mapOfChampionAbilities: MutableMap<String, AbilityNode> = mutableMapOf(
    Pair(champion.name, champion)
)