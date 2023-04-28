package com.andreyyurko.dnd.data.abilities.other

import com.andreyyurko.dnd.data.characterData.CharacterInfo
import com.andreyyurko.dnd.data.characterData.character.AbilityNode


var customBackstory: AbilityNode = AbilityNode(
    name = "Предыстория",
    changesInCharacterInfo = {abilities: CharacterInfo -> abilities },
    alternatives = mutableMapOf(
        Pair("first", listOf(acrobatics.name, animalHandling.name, arcana.name, athletics.name, deception.name,
            history.name, insight.name, intimidation.name, investigation.name, medicine.name, nature.name,
            perception.name, performance.name, persuasion.name, religion.name, sleightOfHand.name, stealth.name,
            survival.name)),
        Pair("second", listOf(acrobatics.name, animalHandling.name, arcana.name, athletics.name, deception.name,
            history.name, insight.name, intimidation.name, investigation.name, medicine.name, nature.name,
            perception.name, performance.name, persuasion.name, religion.name, sleightOfHand.name, stealth.name,
            survival.name))
    ),
    requirements = {true},
    description = "Выберите два навыка для вашей предыстории."
)