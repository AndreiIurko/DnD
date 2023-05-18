package com.andreyyurko.dnd.data.abilities.races

import com.andreyyurko.dnd.data.abilities.other.mapOfLanguages
import com.andreyyurko.dnd.data.abilities.other.perception
import com.andreyyurko.dnd.data.abilities.other.stealth
import com.andreyyurko.dnd.data.characterData.Action
import com.andreyyurko.dnd.data.characterData.ActionType
import com.andreyyurko.dnd.data.characterData.CharacterInfo
import com.andreyyurko.dnd.data.characterData.ChargesCounter
import com.andreyyurko.dnd.data.characterData.DamageType
import com.andreyyurko.dnd.data.characterData.Languages
import com.andreyyurko.dnd.data.characterData.Priority
import com.andreyyurko.dnd.data.characterData.character.AbilityNode

val tabaxiChooseLanguage = AbilityNode(
    name = "Выбор языка: табакси",
    changesInCharacterInfo = { abilities: CharacterInfo -> abilities },
    getAlternatives = mutableMapOf(
        Pair("language", { mapOfLanguages.keys.toList() })
    ),
    requirements = { true },
    description = "Выберите любой язык:"
)

val tabaxiClaws = AbilityNode(
    name = "Когти табакси",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Скорость лазанья табакси"] = "Из-за ваших когтей у вас есть скорость лазания 20 футов.\n"
        checkProfs@ for (prof in abilities.weaponProficiency) {
            if (prof.weaponName == "Безоружный удар") {
                prof.damage = "1к4"
                prof.damageType = DamageType.Slashing
            }
        }
        abilities
    },
    getAlternatives = mutableMapOf(
    ),
    requirements = { true },
    description = "",
    isNeedsToBeShown = false
)

val tabaxiAbilities = AbilityNode(
    name = "Способности табакси",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.dexterity += 2
        abilities.charisma += 1
        abilities.languageProficiency.add(Languages.Common)
        abilities.speed += 30
        if (abilities.additionalAbilities.contains("Тёмное зрение")) {
            var regex = Regex(" [^ ]* футов")
            abilities.additionalAbilities["Тёмное зрение"]?.let {
                regex.find(it)?.let { parsed ->
                    if (60 > parsed.value.split(" ")[1].toInt()) {
                        abilities.additionalAbilities["Тёмное зрение"] =
                            "У Вас есть острые кошачьи чувства, особенно в темноте. На расстоянии в 60 футов вы при тусклом освещении можете видеть так, как будто это яркое освещение, и в темноте так, как будто это тусклое освещение. В темноте вы не можете различать цвета, только оттенки серого.\n"
                    }
                }
            }
        } else {
            abilities.additionalAbilities["Тёмное зрение"] =
                "У Вас есть острые кошачьи чувства, особенно в темноте. На расстоянии в 60 футов вы при тусклом освещении можете видеть так, как будто это яркое освещение, и в темноте так, как будто это тусклое освещение. В темноте вы не можете различать цвета, только оттенки серого.\n"
        }
        if (!abilities.currentState.charges.contains("Кошачье проворство")) {
            abilities.currentState.charges["Кошачье проворство"] = ChargesCounter(
                current = 1,
                maximum = 1
            )
        }
        abilities.actionsList.add(
            Action(
                name = "Кошачье проворство",
                description = "Ваши рефлексы и проворство позволяют вам двигаться с увеличением скорости. Когда вы двигаетесь в бою в свой ход, вы можете удвоить свою скорость до конца хода.\n" +
                        "\n" +
                        "Использовав эту особенность, вы не можете использовать её снова, до тех пор пока вы не переместитесь на 0 футов в один из ваших ходов.\n",
                type = ActionType.PartOfAction,
                relatedCharges = "Кошачье проворство"
            )
        )
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("languageFirst", { listOf(tabaxiChooseLanguage.name) }),
        Pair("skill1", { listOf(perception.name) }),
        Pair("skill1", { listOf(stealth.name) }),
        Pair("first", { listOf(tabaxiClaws.name) })
    ),
    requirements = { true },
    description = "Ваш персонаж-табакси обладает следующими особенностями.\n" +
            "\n" +
            "Увеличение характеристик. Значение вашей Ловкости увеличивается на 2, а значение Харизмы увеличивается на 1.\n" +
            "\n" +
            "Возраст. Табакси имеют такую же продолжительность жизни, что и люди.\n" +
            "\n" +
            "Мировоззрение. Любое.\n" +
            "\n" +
            "Размер. Табакси в среднем более высокие чем люди и относительно худощавые. Ваш размер – Средний.\n" +
            "\n" +
            "Скорость. Ваша базовая скорость ходьбы составляет 30 футов.\n" +
            "\n" +
            "Тёмное зрение. У Вас есть острые кошачьи чувства, особенно в темноте. На расстоянии в 60 футов вы при тусклом освещении можете видеть так, как будто это яркое освещение, и в темноте так, как будто это тусклое освещение. В темноте вы не можете различать цвета, только оттенки серого.\n" +
            "\n" +
            "Кошачье проворство. Ваши рефлексы и проворство позволяют вам двигаться с увеличением скорости. Когда вы двигаетесь в бою в свой ход, вы можете удвоить свою скорость до конца хода.\n" +
            "\n" +
            "Использовав эту особенность, вы не можете использовать её снова, до тех пор пока вы не переместитесь на 0 футов в один из ваших ходов.\n" +
            "\n" +
            "Кошачьи когти. Из-за ваших когтей у вас есть скорость лазания 20 футов. Кроме того, ваши когти — естественное оружие, которое вы можете использовать, нанося безоружные удары.\n" +
            "\n" +
            "Если вы совершаете атаку ими, вы наносите рубящий урон, равный 1к4 + ваш модификатор Силы, вместо дробящего урона, обычного для безоружного удара.\n" +
            "\n" +
            "Кошачьи способности. Вы владеете навыками Восприятие и Скрытность.\n" +
            "\n" +
            "Языки. Вы можете говорить, читать и писать на Общем и ещё одном языке на ваш выбор.\n",
    priority = Priority.DoFirst
)

val tabaxi = AbilityNode(
    name = "Табакси",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.race = "Табакси"
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("abilities", { listOf(tabaxiAbilities.name) })
    ),
    requirements = { true },
    description = "Табакси",
)

val mapOfTabaxiAbilities = mutableMapOf(
    Pair(tabaxiChooseLanguage.name, tabaxiChooseLanguage),
    Pair(tabaxiClaws.name, tabaxiClaws),
    Pair(tabaxiAbilities.name, tabaxiAbilities),
    Pair(tabaxi.name, tabaxi)
)