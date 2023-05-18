package com.andreyyurko.dnd.data.abilities.classes.wizard

import com.andreyyurko.dnd.data.characterData.Action
import com.andreyyurko.dnd.data.characterData.ActionType
import com.andreyyurko.dnd.data.characterData.CharacterInfo
import com.andreyyurko.dnd.data.characterData.Classes
import com.andreyyurko.dnd.data.characterData.character.AbilityNode

var divineSavant = AbilityNode(
    name = "Мастер прорицания",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Мастер прорицания"] =
            "Золото и время, которое вы тратите на копирование заклинания школы Прорицания в свою книгу заклинаний, уменьшаются вдвое.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 2 && abilities.characterClass == Classes.Wizard
    },
    description = "Золото и время, которое вы тратите на копирование заклинания школы Прорицания в свою книгу заклинаний, уменьшаются вдвое.\n",
)

var portent = AbilityNode(
    name = "Знамение",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        if (abilities.level < 14) {
            abilities.actionsMap["Знамение"] =
                Action(
                    name = "Знамение",
                    description = "Когда вы выбираете эту школу, в вашем сознании возникают проблески будущего. Когда вы заканчиваете продолжительный отдых, сделайте два броска к20 и запишите их результаты. Вы можете заменить любой бросок атаки, спасбросок, или проверку характеристики, сделанную вами или существом, которое вы можете видеть, одним из этих бросков предсказания. Вы должны сделать выбор о том, что так поступаете, до броска, и вы можете заменить значение броска подобным путём только один раз за ход.\n" +
                            "\n" +
                            "Каждый бросок предсказания может быть использован только один раз. Когда вы заканчиваете продолжительный отдых, вы теряете все неиспользованные броски предсказания.\n",
                    type = ActionType.Additional
                )

        } else {
            abilities.actionsMap["Знамение"] =
                Action(
                    name = "Знамение",
                    description = "Когда вы выбираете эту школу, в вашем сознании возникают проблески будущего. Когда вы заканчиваете продолжительный отдых, сделайте три броска к20 и запишите их результаты. Вы можете заменить любой бросок атаки, спасбросок, или проверку характеристики, сделанную вами или существом, которое вы можете видеть, одним из этих бросков предсказания. Вы должны сделать выбор о том, что так поступаете, до броска, и вы можете заменить значение броска подобным путём только один раз за ход.\n" +
                            "\n" +
                            "Каждый бросок предсказания может быть использован только один раз. Когда вы заканчиваете продолжительный отдых, вы теряете все неиспользованные броски предсказания.\n",
                    type = ActionType.Additional
                )

        }
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 2 && abilities.characterClass == Classes.Wizard
    },
    description = "Когда вы выбираете эту школу, в вашем сознании возникают проблески будущего. Когда вы заканчиваете продолжительный отдых, сделайте два броска к20 и запишите их результаты. Вы можете заменить любой бросок атаки, спасбросок, или проверку характеристики, сделанную вами или существом, которое вы можете видеть, одним из этих бросков предсказания. Вы должны сделать выбор о том, что так поступаете, до броска, и вы можете заменить значение броска подобным путём только один раз за ход.\n" +
            "\n" +
            "Каждый бросок предсказания может быть использован только один раз. Когда вы заканчиваете продолжительный отдых, вы теряете все неиспользованные броски предсказания.\n",
)

var expertDivination = AbilityNode(
    name = "Эксперт прорицания",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Эксперт прорицания"] =
            "Вы так легко накладываете заклинания школы Прорицания, что расходуете лишь малую часть своих сил. Когда вы накладываете заклинание школы Прорицания 2-го уровня или выше, используя ячейку заклинаний, вы восстанавливаете одну уже израсходованную ячейку заклинаний. Восстанавливаемая ячейка должна быть ниже уровнем, чем заклинание, которое вы накладываете, и не может быть выше 5-го уровня.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 6 && abilities.characterClass == Classes.Wizard
    },
    description = "Вы так легко накладываете заклинания школы Прорицания, что расходуете лишь малую часть своих сил. Когда вы накладываете заклинание школы Прорицания 2-го уровня или выше, используя ячейку заклинаний, вы восстанавливаете одну уже израсходованную ячейку заклинаний. Восстанавливаемая ячейка должна быть ниже уровнем, чем заклинание, которое вы накладываете, и не может быть выше 5-го уровня.\n"
)

var theThirdEye = AbilityNode(
    name = "Третий глаз",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsMap["Третий глаз"] =
            Action(
                name = "Третий глаз",
                description = "Вы можете действием увеличить силу восприятия. Когда вы так поступаете, выберите одно преимущество, которое будет длиться, пока вы не станете недееспособны или пока вы не совершите короткий или продолжительный отдых. Вы не можете использовать это умение повторно, пока не завершите отдых.\n" +
                        "\n" +
                        "Большое понимание. Вы можете читать на любых языках.\n" +
                        "\n" +
                        "Видеть невидимое. Вы можете видеть находящихся на линии обзора невидимых существ и предметы в пределах 10 футов.\n" +
                        "\n" +
                        "Тёмное зрение. Вы получаете тёмное зрение в пределах 60 футов.\n" +
                        "\n" +
                        "Эфирный взгляд. Ваше зрение простирается на Эфирный План в пределах 60 футов.\n",
                type = ActionType.Action
            )

        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 10 && abilities.characterClass == Classes.Wizard
    },
    description = "Вы можете действием увеличить силу восприятия. Когда вы так поступаете, выберите одно преимущество, которое будет длиться, пока вы не станете недееспособны или пока вы не совершите короткий или продолжительный отдых. Вы не можете использовать это умение повторно, пока не завершите отдых.\n" +
            "\n" +
            "Большое понимание. Вы можете читать на любых языках.\n" +
            "\n" +
            "Видеть невидимое. Вы можете видеть находящихся на линии обзора невидимых существ и предметы в пределах 10 футов.\n" +
            "\n" +
            "Тёмное зрение. Вы получаете тёмное зрение в пределах 60 футов.\n" +
            "\n" +
            "Эфирный взгляд. Ваше зрение простирается на Эфирный План в пределах 60 футов.\n"
)

var greaterPortent = AbilityNode(
    name = "Великое знамение",
    changesInCharacterInfo = { abilities: CharacterInfo -> abilities },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 14 && abilities.characterClass == Classes.Wizard
    },
    description = "Видения в ваших грёзах усиливаются и рисуют более точную картину того, что грядёт. Вы совершаете три броска к20 для вашего умения «Знамение» вместо двух.\n"
)

var schoolOfDivination = AbilityNode(
    name = "Школа прорицания",
    changesInCharacterInfo = { abilities: CharacterInfo -> abilities },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(divineSavant.name) }),
        Pair("second", { listOf(portent.name) }),
        Pair("third", { listOf(expertDivination.name) }),
        Pair("fourth", { listOf(theThirdEye.name) }),
        Pair("fifth", { listOf(greaterPortent.name) })
    ),
    requirements = { true },
    description = "",
    isNeedsToBeShown = false
)

var mapOfSchoolOfDivinationAbilities: MutableMap<String, AbilityNode> = mutableMapOf(
    Pair(divineSavant.name, divineSavant),
    Pair(portent.name, portent),
    Pair(expertDivination.name, expertDivination),
    Pair(theThirdEye.name, theThirdEye),
    Pair(greaterPortent.name, greaterPortent),
    Pair(schoolOfDivination.name, schoolOfDivination),
)

