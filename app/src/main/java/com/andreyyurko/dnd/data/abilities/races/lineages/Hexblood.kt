package com.andreyyurko.dnd.data.abilities.races.lineages

import com.andreyyurko.dnd.data.characterData.CharacterInfo
import com.andreyyurko.dnd.data.characterData.character.AbilityNode

val hexblood = AbilityNode(
    name = "Ведьмовская кровь",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.race = "Ведьмовская кровь"
        abilities.speed += 30
        if (abilities.additionalAbilities.contains("Тёмное зрение")) {
            var regex = Regex(" [^ ]* футов")
            abilities.additionalAbilities["Тёмное зрение"]?.let {
                regex.find(it)?.let { parsed ->
                    if (60 > parsed.value.split(" ")[1].toInt()) {
                        abilities.additionalAbilities["Тёмное зрение"] =
                            "На расстоянии 60 футов вы при тусклом освещении можете видеть так, как будто это яркое освещение, и в темноте так, как будто это тусклое освещение. В темноте вы не можете различать цвета, только оттенки серого.\n"
                    }
                }
            }
        } else {
            abilities.additionalAbilities["Тёмное зрение"] =
                "На расстоянии 60 футов вы при тусклом освещении можете видеть так, как будто это яркое освещение, и в темноте так, как будто это тусклое освещение. В темноте вы не можете различать цвета, только оттенки серого.\n"
        }
        abilities.additionalAbilities["Тип существа"] = "Вы фея"
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(lineageAbilityScoreImprovement.name) }),
        Pair("second", { listOf(ancestralLegacy.name) }),
        Pair("third", { listOf(lineageLanguage.name) }),
    ),
    requirements = { true },
    description = "\n",
)

val mapOfHexbloodAbilities = mutableMapOf(
    Pair(hexblood.name, hexblood)
)