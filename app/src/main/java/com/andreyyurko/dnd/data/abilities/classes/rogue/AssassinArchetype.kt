package com.andreyyurko.dnd.data.abilities.classes.rogue

import com.andreyyurko.dnd.data.characterData.Action
import com.andreyyurko.dnd.data.characterData.ActionType
import com.andreyyurko.dnd.data.characterData.CharacterInfo
import com.andreyyurko.dnd.data.characterData.Classes
import com.andreyyurko.dnd.data.characterData.Tools
import com.andreyyurko.dnd.data.characterData.character.AbilityNode

var bonusProficiencyAssassinArchetype = AbilityNode(
    name = "Дополнительные владения убийцы",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.toolProficiency.add(Tools.PoisonerTools)
        abilities.toolProficiency.add(Tools.DisguiseTools)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 3 && abilities.characterClass == Classes.Rogue
    },
    description = "Вы получаете владение набором для грима и инструментами отравителя.\n"
)

var assassinate = AbilityNode(
    name = "Ликвидация",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Ликвидация"] = "Вы совершаете с преимуществом броски атаки по всем существам, которые ещё не совершали ход в этом бою. Кроме того, все попадания по существам, захваченным врасплох, являются критическими попаданиями.\n"
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 3 && abilities.characterClass == Classes.Rogue
    },
    description = "Вы становитесь смертоносными для врагов. Вы совершаете с преимуществом броски атаки по всем существам, которые ещё не совершали ход в этом бою. Кроме того, все попадания по существам, захваченным врасплох, являются критическими попаданиями.\n"
)

var infiltrationExpertise = AbilityNode(
    name = "Мастер проникновения",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Мастер проникновения",
                description = "Вы можете создавать себе ложную биографию. Вы должны потратить семь дней и 25 зм, чтобы сделать её достоверной и установить нужные связи. Вы не можете взять биографию, принадлежащую другому лицу. Например, вы можете приобрести соответствующую одежду, рекомендательные письма и официального вида сертификаты, чтобы обозначить себя в качестве члена торгового дома из далёкого города, или внушить, что вы из группы богатых торговцев.\n" +
            "\n" +
                    "После принятия новой личности в качестве маскировки другие существа верят, что вы являетесь этой личностью, пока не подадите очевидный повод решить обратное.\n",
                type = ActionType.Long
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 9 && abilities.characterClass == Classes.Rogue
    },
    description = "Вы можете создавать себе ложную биографию. Вы должны потратить семь дней и 25 зм, чтобы сделать её достоверной и установить нужные связи. Вы не можете взять биографию, принадлежащую другому лицу. Например, вы можете приобрести соответствующую одежду, рекомендательные письма и официального вида сертификаты, чтобы обозначить себя в качестве члена торгового дома из далёкого города, или внушить, что вы из группы богатых торговцев.\n" +
            "\n" +
            "После принятия новой личности в качестве маскировки другие существа верят, что вы являетесь этой личностью, пока не подадите очевидный повод решить обратное.\n"
)

var impostor = AbilityNode(
    name = "Самозванец",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Самозванец",
                description = "Вы получаете способность безошибочно подражать чужой речи, почерку и привычкам. Вы должны провести не менее трёх часов изучения этих трёх компонентов поведения существа, слушая его речь, рассматривая почерк и наблюдая за привычками.\n" +
                        "\n" +
                        "Ваши уловки не видны для случайного наблюдателя. Если осторожное существо подозревает что-то неладное, вы совершаете с преимуществом все проверки Харизмы (Обман), чтобы избежать обнаружения.\n",
                type = ActionType.Long
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 13 && abilities.characterClass == Classes.Rogue
    },
    description = "Вы получаете способность безошибочно подражать чужой речи, почерку и привычкам. Вы должны провести не менее трёх часов изучения этих трёх компонентов поведения существа, слушая его речь, рассматривая почерк и наблюдая за привычками.\n" +
            "\n" +
            "Ваши уловки не видны для случайного наблюдателя. Если осторожное существо подозревает что-то неладное, вы совершаете с преимуществом все проверки Харизмы (Обман), чтобы избежать обнаружения.\n"
)

var deathStrike = AbilityNode(
    name = "Смертельный удар",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Смертельный удар"] = "Если вы атакуете и попадаете по существу, захваченному врасплох, оно должно совершить спасбросок Телосложения (Сл 8 + ваш модификатор Ловкости + ваш бонус мастерства). Проваленный спасбросок означает удвоение урона от вашей атаки по этому существу.\n"
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 17 && abilities.characterClass == Classes.Rogue
    },
    description = "Вы становитесь мастером мгновенной смерти. Если вы атакуете и попадаете по существу, захваченному врасплох, оно должно совершить спасбросок Телосложения (Сл 8 + ваш модификатор Ловкости + ваш бонус мастерства). Проваленный спасбросок означает удвоение урона от вашей атаки по этому существу.\n"
)

var assassinArchetype = AbilityNode(
    name = "Убийца",
    changesInCharacterInfo = { abilities: CharacterInfo -> abilities },
    alternatives = mutableMapOf(
        Pair("first", listOf(bonusProficiencyAssassinArchetype.name)),
        Pair("second", listOf(assassinate.name)),
        Pair("third", listOf(infiltrationExpertise.name)),
        Pair("fourth", listOf(impostor.name)),
        Pair("fifth", listOf(deathStrike.name))
    ),
    requirements = { true },
    description = "",
    isNeedsToBeShown = false
)

var mapOfAssassinArchetypeAbilities: MutableMap<String, AbilityNode> = mutableMapOf(
    Pair(bonusProficiencyAssassinArchetype.name, bonusProficiencyAssassinArchetype),
    Pair(assassinate.name, assassinate),
    Pair(infiltrationExpertise.name, infiltrationExpertise),
    Pair(impostor.name, impostor),
    Pair(deathStrike.name, deathStrike),
    Pair(assassinArchetype.name, assassinArchetype),
)