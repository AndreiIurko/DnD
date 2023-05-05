package com.andreyyurko.dnd.data.abilities.other

import com.andreyyurko.dnd.data.characterData.CharacterInfo
import com.andreyyurko.dnd.data.characterData.character.AbilityNode


var customBackstory: AbilityNode = AbilityNode(
    name = "Предыстория",
    changesInCharacterInfo = { abilities: CharacterInfo -> abilities },
    getAlternatives = mutableMapOf(
        Pair("first", { mapOfSkills.keys.toList() }),
        Pair("second", { mapOfSkills.keys.toList() }),
        Pair("third", { mapOfLanguages.keys.toList() + mapOfTools.keys.toList() }),
        Pair("fourth", { mapOfLanguages.keys.toList() + mapOfTools.keys.toList() })
    ),
    requirements = { true },
    description = "Выберите два навыка для вашей предыстории."
)