package com.andreyyurko.dnd.data.abilities.races.lineages

import com.andreyyurko.dnd.data.abilities.other.*
import com.andreyyurko.dnd.data.characterData.CharacterInfo
import com.andreyyurko.dnd.data.characterData.character.AbilityNode

val lineageAbilityScoreImprovement = AbilityNode(
    name = "Увеличение характеристик происхождения",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair(
            "firstAbility", {
                listOf(
                    strengthAbilityImprovement.name,
                    dexterityAbilityImprovement.name,
                    constitutionAbilityImprovement.name,
                    intelligenceAbilityImprovement.name,
                    wisdomAbilityImprovement.name,
                    charismaAbilityImprovement.name
                )
            }
        ),
        Pair(
            "secondAbility", {
                listOf(
                    strengthAbilityImprovement.name,
                    dexterityAbilityImprovement.name,
                    constitutionAbilityImprovement.name,
                    intelligenceAbilityImprovement.name,
                    wisdomAbilityImprovement.name,
                    charismaAbilityImprovement.name
                )
            }
        ),
        Pair(
            "thirdAbility", {
                listOf(
                    strengthAbilityImprovement.name,
                    dexterityAbilityImprovement.name,
                    constitutionAbilityImprovement.name,
                    intelligenceAbilityImprovement.name,
                    wisdomAbilityImprovement.name,
                    charismaAbilityImprovement.name
                )
            }
        ),
    ),
    requirements = { true },
    description = "Значение трёх ваших характеристик по вашему выбору увеличивается на 1. Вы не можете выбрать одну и ту же характеристи\n",
)

val lineageSkills = AbilityNode(
    name = "Навыки происхождения",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("skill1", { mapOfSkills.keys.toList() }),
        Pair("skill2", { mapOfSkills.keys.toList() }),
    ),
    requirements = { true },
    description = "Выберите два любых навыка.\n",
)

val tabaxiLegacy = AbilityNode(
    name = "Наследие табакси",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Скорость лазанья табакси"] = "Из-за ваших когтей у вас есть скорость лазания 20 футов.\n"
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("skill1", { listOf(perception.name) }),
        Pair("skill2", { listOf(stealth.name) }),
    ),
    requirements = { true },
    description = "Вы владеете навыками Восприятие и Скрытность. Кроме того, из-за ваших когтей у вас есть скорость лазания 20 футов.\n",
)

val ancestralLegacy = AbilityNode(
    name = "Наследие предков",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(lineageSkills.name, tabaxiLegacy.name) })
    ),
    requirements = { true },
    description = "Если вы заменяете расу данным происхождением, вы можете сохранить следующие элементы выбранной расы: любые навыки, которые вы от неё получаете, и любую скорость лазания, полёта или плавания, которую вы от неё получаете.\n" +
            "\n" +
            "Если вы не оставляете ни один из этих элементов или выбираете это происхождение при создании персонажа, вы получаете владение двумя навыками по вашему выбору.\n",
)

val lineageLanguage = AbilityNode(
    name = "Язык происхождения",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("language1", { listOf(commonLanguage.name) }),
        Pair("language2", { mapOfLanguages.keys.toList() }),
    ),
    requirements = { true },
    description = "Ваш персонаж может говорить, читать и писать на Общем и ещё одном языке, который по вашему мнению подходит для персонажа\n",
)

val lineageChoice = AbilityNode(
    name = "Выбор происхождения",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("abilities", { listOf(hexblood.name, dhampir.name) })
    ),
    requirements = { true },
    description = "Вы можете выбрать одно из предложенных происхождений или собрать его самостоятельно\n",
)

val commonLineageAbilities = AbilityNode(
    name = "Способности происхождения",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("subrace", { listOf(lineageChoice.name) })
    ),
    requirements = { true },
    description = "В Землях Туманов сила и ужас заключаются в простом вопросе: «Что со мной произошло?». Варианты происхождения — это расы, которые персонажи могут получить благодаря особенным событиям. Они заменяют вашу первоначальную расу, если таковая имеется, становясь новой расой. Персонаж может выбрать происхождение при создании персонажа, если его трансформация произошла до начала игры. Или же события, разворачивающиеся во время приключения, могут привести вашего персонажа к смене вашей расы данным новым происхождением. Пообщайтесь со своим Мастером, чтобы определить, готовы ли вы к подобному развитию событий и тому как разворачиваются подобные истории.\n",
)

val commonLineage = AbilityNode(
    name = "Происхождение",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("abilities", { listOf(commonLineageAbilities.name) })
    ),
    requirements = { true },
    description = "Происхождение",
)

val mapOfLineageAbilities = (
        mutableMapOf(
            Pair(lineageAbilityScoreImprovement.name, lineageAbilityScoreImprovement),
            Pair(lineageSkills.name, lineageSkills),
            Pair(tabaxiLegacy.name, tabaxiLegacy),
            Pair(ancestralLegacy.name, ancestralLegacy),
            Pair(lineageLanguage.name, lineageLanguage),
            Pair(lineageChoice.name, lineageChoice),
            Pair(commonLineageAbilities.name, commonLineageAbilities),
            Pair(commonLineage.name, commonLineage)
        )
        + mapOfHexbloodAbilities
        + mapOfDhampirAbilities
    ).toMutableMap()