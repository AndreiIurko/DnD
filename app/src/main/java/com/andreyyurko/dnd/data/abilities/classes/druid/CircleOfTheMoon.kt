package com.andreyyurko.dnd.data.abilities.classes.druid

import com.andreyyurko.dnd.data.characterData.Action
import com.andreyyurko.dnd.data.characterData.ActionType
import com.andreyyurko.dnd.data.characterData.character.CharacterInfo
import com.andreyyurko.dnd.data.characterData.Classes
import com.andreyyurko.dnd.data.characterData.Priority
import com.andreyyurko.dnd.data.characterData.character.AbilityNode

var combatWildShape: AbilityNode = AbilityNode(
    name = "Боевой дикий облик",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsMap["Дикий облик"]?.let { action ->
            abilities.actionsMap["Дикий облик_2"] =
                Action(
                    name = "Дикий облик",
                    description = "Действием или бонусным д" + action.description.substring(1) + "Обряды круга луны позволяют принимать облик более опасных животных. Вы можете превращаться в Зверя с показателем опасности до 1 (игнорируйте столбец «Макс. ПО» таблицы «Облик зверя»).\n" +
                            "\n" +
                            "Начиная с 6-го уровня, вы можете превращаться в Зверя с показателем опасности вплоть до уровня друида, поделённого на 3 (округляя в меньшую сторону).\n",
                    type = ActionType.Bonus,
                    relatedCharges = action.relatedCharges
                )
            action.description =
                "Действием или бонусным д" + action.description.substring(1) + "Обряды круга луны позволяют принимать облик более опасных животных. Вы можете превращаться в Зверя с показателем опасности до 1 (игнорируйте столбец «Макс. ПО» таблицы «Облик зверя»).\n" +
                        "\n" +
                        "Начиная с 6-го уровня, вы можете превращаться в Зверя с показателем опасности вплоть до уровня друида, поделённого на 3 (округляя в меньшую сторону).\n"
        }

        abilities.actionsMap["Восстановление хитов в диком облике"] =
            Action(
                name = "Восстановление хитов в диком облике",
                description = "Пока вы перевоплощены умением «Дикий облик», вы можете бонусным действием потратить ячейку заклинания, чтобы восстановить 1к8 хитов за каждый уровень потраченной ячейки.\n",
                type = ActionType.Bonus,
            )

        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 2 && abilities.characterClass == Classes.Druid
    },
    addRequirements = listOf(),
    description = "Вы можете использовать умение «Дикий облик» бонусным действием, а не действием.\n" +
            "\n" +
            "Кроме того, пока вы перевоплощены умением «Дикий облик», вы можете бонусным действием потратить ячейку заклинания, чтобы восстановить 1к8 хитов за каждый уровень потраченной ячейки.\n" +
            "\n" +
            "Обряды круга луны позволяют принимать облик более опасных животных. Вы можете превращаться в Зверя с показателем опасности до 1 (игнорируйте столбец «Макс. ПО» таблицы «Облик зверя»).\n" +
            "\n" +
            "Начиная с 6-го уровня, вы можете превращаться в Зверя с показателем опасности вплоть до уровня друида, поделённого на 3 (округляя в меньшую сторону).\n",
    priority = Priority.DoAfterBasic
)

var primalStrike: AbilityNode = AbilityNode(
    name = "Природный удар",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Природный удар"] =
            "Ваши атаки в облике Зверя считаются магическими для преодоления сопротивления и иммунитета к немагическим атакам и урону.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 6 && abilities.characterClass == Classes.Druid
    },
    addRequirements = listOf(),
    description = "Ваши атаки в облике Зверя считаются магическими для преодоления сопротивления и иммунитета к немагическим атакам и урону.\n",
)

var elementalWildShape: AbilityNode = AbilityNode(
    name = "Стихийный дикий облик",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        var charges = "Дикий облик"
        if (abilities.level == 20) charges = ""
        abilities.actionsMap["Стихийный дикий облик"] =
            Action(
                name = "Стихийный дикий облик_Основное",
                description = "Вы можете потратить сразу два использования «Дикого облика», чтобы принять облик водяного, воздушного, земляного или огненного элементаля.\n",
                type = ActionType.Action,
                relatedCharges = charges
            )

        abilities.actionsMap["Стихийный дикий облик_Бонусное"] =
            Action(
                name = "Стихийный дикий облик",
                description = "Вы можете потратить сразу два использования «Дикого облика», чтобы принять облик водяного, воздушного, земляного или огненного элементаля.\n",
                type = ActionType.Bonus,
                relatedCharges = charges
            )

        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 10 && abilities.characterClass == Classes.Druid
    },
    addRequirements = listOf(),
    description = "Вы можете потратить сразу два использования «Дикого облика», чтобы принять облик водяного, воздушного, земляного или огненного элементаля.\n",
)

var thousandForms: AbilityNode = AbilityNode(
    name = "Тысяча лиц",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Тысяча лиц"] =
            "Вы понимаете, как магически изменять детали своего облика. Вы можете неограниченно накладывать заклинание смена обличья [alter self].\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 14 && abilities.characterClass == Classes.Druid
    },
    addRequirements = listOf(),
    description = "Вы понимаете, как магически изменять детали своего облика. Вы можете неограниченно накладывать заклинание смена обличья [alter self].\n",
)

var circleOfTheMoon = AbilityNode(
    name = "Круг луны",
    changesInCharacterInfo = { abilities: CharacterInfo -> abilities },
    getAlternatives = mutableMapOf(
        Pair("first") { listOf(combatWildShape.name) },
        Pair("second") { listOf(primalStrike.name) },
        Pair("third") { listOf(elementalWildShape.name) },
        Pair("fourth") { listOf(thousandForms.name) }
    ),
    requirements = { true },
    description = "",
    isNeedsToBeShown = false
)

var mapOfСircleOfTheMoonAbilities: MutableMap<String, AbilityNode> = mutableMapOf(
    Pair(circleOfTheMoon.name, circleOfTheMoon),
    Pair(combatWildShape.name, combatWildShape),
    Pair(primalStrike.name, primalStrike),
    Pair(elementalWildShape.name, elementalWildShape),
    Pair(thousandForms.name, thousandForms),
)