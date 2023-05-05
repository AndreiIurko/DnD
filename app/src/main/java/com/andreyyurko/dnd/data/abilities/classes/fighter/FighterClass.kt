package com.andreyyurko.dnd.data.abilities.classes.fighter

import com.andreyyurko.dnd.data.abilities.classes.AbilityNodeLevel
import com.andreyyurko.dnd.data.abilities.other.*
import com.andreyyurko.dnd.data.characterData.*
import com.andreyyurko.dnd.data.characterData.character.AbilityNode
import com.andreyyurko.dnd.data.characterData.character.abilityToModifier


var classFeaturesFighter: AbilityNode = AbilityNode(
    name = "Воин: классовые умения",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.savingThrowProf.add(Ability.Strength)
        abilities.savingThrowProf.add(Ability.Constitution)
        addAllSimpleWeapons(abilities)
        addAllMartialWeapons(abilities)
        abilities.armorProficiency.add(ArmorProf.LightArmor)
        abilities.armorProficiency.add(ArmorProf.MediumArmor)
        abilities.armorProficiency.add(ArmorProf.HeavyArmor)
        abilities.armorProficiency.add(ArmorProf.Shield)
        abilities
    },
    alternatives = mutableMapOf(
        Pair(
            "skill1",
            listOf(
                acrobatics.name,
                athletics.name,
                perception.name,
                survival.name,
                intimidation.name,
                history.name,
                insight.name,
                animalHandling.name
            )
        ),
        Pair(
            "skill2",
            listOf(
                acrobatics.name,
                athletics.name,
                perception.name,
                survival.name,
                intimidation.name,
                history.name,
                insight.name,
                animalHandling.name
            )
        )
    ),
    requirements = { abilities: CharacterInfo ->
        abilities.characterClass == Classes.Fighter
    },
    description = "ХИТЫ\n" +
            "\n" +
            "Кость Хитов: 1к10 за каждый уровень воина\n" +
            "Хиты на 1 уровне: 10 + модификатор Телосложения\n" +
            "Хиты на следующих уровнях: 1к10 (или 6) + модификатор Телосложения (суммарно минимум 1) за каждый уровень воина после первого\n" +
            "\n" +
            "ВЛАДЕНИЕ\n" +
            "\n" +
            "Доспехи: Все доспехи, щиты\n" +
            "Оружие: Простое оружие, воинское оружие\n" +
            "Инструменты: Нет\n" +
            "Спасброски: Сила, Телосложение\n" +
            "Навыки: Выберите два навыка из следующих: Акробатика, Атлетика, Восприятие, Выживание, Запугивание, История, Проницательность, Уход за животными",
    priority = Priority.DoFirst
)

var fightingStyle: AbilityNode = AbilityNode(
    name = "Боевой стиль",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    alternatives = mutableMapOf(
        Pair("style", mapOfFightingStyles.keys.toList())
    ),
    requirements = { abilities: CharacterInfo ->
        abilities.characterClass == Classes.Fighter
    },
    description = "Вы выбираете боевой стиль, соответствующий вашей специализации. Выберите один из следующих вариантов. Вы не можете выбирать один и тот же вариант боевого стиля, даже если позже у вас будет возможность выбрать еще один стиль."
)

var secondWind: AbilityNode = AbilityNode(
    name = "Второе дыхание",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        if (!abilities.currentState.charges.contains("Второе дыхание")) {
            abilities.currentState.charges["Второе дыхание"] = ChargesCounter(current = 1, maximum = 1)
        }
        abilities.actionsList.add(
            Action(
                name = "Второе дыхание",
                description = "Вы обладаете ограниченным источником выносливости, которым можете воспользоваться, чтобы уберечь себя. В свой ход вы можете бонусным действием восстановить хиты в размере 1к10 + ваш уровень воина.\n" +
                        "\n" +
                        "Использовав это умение, вы должны завершить короткий либо продолжительный отдых, чтобы получить возможность использовать его снова.",
                type = ActionType.Bonus,
                relatedCharges = "Второе дыхание"
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.characterClass == Classes.Fighter
    },
    description = "Вы обладаете ограниченным источником выносливости, которым можете воспользоваться, чтобы уберечь себя. В свой ход вы можете бонусным действием восстановить хиты в размере 1к10 + ваш уровень воина.\n" +
            "\n" +
            "Использовав это умение, вы должны завершить короткий либо продолжительный отдых, чтобы получить возможность использовать его снова."
)

var fighter1: AbilityNodeLevel = AbilityNodeLevel(
    name = "Воин_1",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.characterClass = Classes.Fighter
        abilities.level += 1
        abilities.proficiencyBonus += 2
        abilities.hp += abilityToModifier(abilities.constitution) + 10
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(classFeaturesFighter.name)),
        Pair("second", listOf(fightingStyle.name)),
        Pair("third", listOf(secondWind.name))
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "1-й уровень, способности воина",
    next_level = "Воин_2",
)

var actionSurge: AbilityNode = AbilityNode(
    name = "Всплеск действий",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Всплеск действий",
                description = "Вы получаете возможность на мгновение преодолеть обычные возможности. В свой ход вы можете совершить одно дополнительное действие помимо обычного и бонусного действий. Использовав это умение, вы должны завершить короткий или продолжительный отдых, чтобы получить возможность использовать его снова. Начиная с 17-го уровня, вы можете использовать это умение дважды, прежде чем вам понадобится отдых, но в течение одного хода его всё равно можно использовать лишь один раз.",
                type = ActionType.Additional,
                relatedCharges = "Всплеск действий"
            )
        )
        if (!abilities.currentState.charges.contains("Всплеск действий")) {
            abilities.currentState.charges["Всплеск действий"] = ChargesCounter(
                current = 1,
                maximum = 1
            )
        }
        if (abilities.currentState.charges["Всплеск действий"]?.maximum == 1 && abilities.level >= 17) {
            abilities.currentState.charges["Всплеск действий"] = ChargesCounter(
                current = 2,
                maximum = 2
            )
        }

        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.characterClass == Classes.Fighter
    },
    description = "Вы получаете возможность на мгновение преодолеть обычные возможности. В свой ход вы можете совершить одно дополнительное действие помимо обычного и бонусного действий. Использовав это умение, вы должны завершить короткий или продолжительный отдых, чтобы получить возможность использовать его снова. Начиная с 17-го уровня, вы можете использовать это умение дважды, прежде чем вам понадобится отдых, но в течение одного хода его всё равно можно использовать лишь один раз."
)

var fighter2: AbilityNodeLevel = AbilityNodeLevel(
    name = "Воин_2",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(actionSurge.name)),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "2-й уровень, способности воина",
    next_level = "Воин_3",
)

var martialArchetype: AbilityNode = AbilityNode(
    name = "Воинский архетип",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    alternatives = mutableMapOf(
        Pair("archetype", listOf(battleMaster.name, champion.name))
    ),
    requirements = { abilities: CharacterInfo ->
        abilities.characterClass == Classes.Fighter
    },
    description = "Вы выбираете архетип, отражающий стиль и технику, к которым вы стремитесь. Подробности архетипа будут приведены ниже после выбора. Выбранный вами архетип предоставляет вам умения на 3-м, 7-м, 10-м, 15-м и 18-м уровнях."
)

var fighter3: AbilityNodeLevel = AbilityNodeLevel(
    name = "Воин_3",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(martialArchetype.name)),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "3-й уровень, способности воина",
    next_level = "Воин_4",
)

var fighter4: AbilityNodeLevel = AbilityNodeLevel(
    name = "Воин_4",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(abilityScoreImprovement.name)),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "4-й уровень, способности воина",
    next_level = "Воин_5",
)

var extraAttackFighter: AbilityNode = AbilityNode(
    name = "Дополнительная атака (воинская)",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        for (action in abilities.actionsList) {
            if (action.name == "Атака") {
                val actionSplit: MutableList<String> = action.description.split("\n") as MutableList<String>
                actionSplit[0] = if (abilities.level >= 20) "Совершить четыре атаки оружием"
                else if (abilities.level >= 11) "Совершить три атаки оружием"
                else "Совершить две атаки оружием"
                action.description = actionSplit.joinToString("\n")
            }
        }
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "Если вы в свой ход совершаете действие Атака, вы можете совершить две атаки вместо одной.\n" +
            "\n" +
            "Количество атак увеличивается до трёх на 11-м уровне этого класса, и до четырёх на 20-м уровне.",
    priority = Priority.DoLast
)

var fighter5: AbilityNodeLevel = AbilityNodeLevel(
    name = "Воин_5",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities.proficiencyBonus += 1
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(extraAttackFighter.name)),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "5-й уровень, способности воина",
    next_level = "Воин_6",
)

var fighter6: AbilityNodeLevel = AbilityNodeLevel(
    name = "Воин_6",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(abilityScoreImprovement.name)),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "6-й уровень, способности воина",
    next_level = "Воин_7",
)

var fighter7: AbilityNodeLevel = AbilityNodeLevel(
    name = "Воин_7",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "7-й уровень, способности воина",
    next_level = "Воин_8",
)

var fighter8: AbilityNodeLevel = AbilityNodeLevel(
    name = "Воин_8",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(abilityScoreImprovement.name)),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "8-й уровень, способности воина",
    next_level = "Воин_9",
)

var indomitable: AbilityNode = AbilityNode(
    name = "Упорный",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Упорный",
                description = "Вы можете перебросить проваленный спасбросок и должны использовать новый результат. После этого вы можете повторно использовать это умение только после завершения продолжительного отдыха.\n" +
                        "\n" +
                        "Вы можете использовать это умение дважды между периодами продолжительного отдыха после достижения 13-го уровня, и трижды после достижения 17-го уровня.",
                type = ActionType.Additional,
                relatedCharges = "Упорный"
            )
        )
        if (!abilities.currentState.charges.contains("Упорный")) {
            abilities.currentState.charges["Упорный"] = ChargesCounter(
                current = 1,
                maximum = 1
            )
        }
        if (abilities.currentState.charges["Упорный"]?.maximum == 1 && abilities.level >= 13) {
            abilities.currentState.charges["Упорный"] = ChargesCounter(
                current = 2,
                maximum = 2
            )
        }
        if (abilities.currentState.charges["Упорный"]?.maximum == 2 && abilities.level >= 17) {
            abilities.currentState.charges["Упорный"] = ChargesCounter(
                current = 3,
                maximum = 3
            )
        }
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "Вы можете перебросить проваленный спасбросок и должны использовать новый результат. После этого вы можете повторно использовать это умение только после завершения продолжительного отдыха.\n" +
            "\n" +
            "Вы можете использовать это умение дважды между периодами продолжительного отдыха после достижения 13-го уровня, и трижды после достижения 17-го уровня."

)

var fighter9: AbilityNodeLevel = AbilityNodeLevel(
    name = "Воин_9",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities.proficiencyBonus += 1
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(indomitable.name))
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "9-й уровень, способности воина",
    next_level = "Воин_10",
)

var fighter10: AbilityNodeLevel = AbilityNodeLevel(
    name = "Воин_10",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "10-й уровень, способности воина",
    next_level = "Воин_11",
)

var fighter11: AbilityNodeLevel = AbilityNodeLevel(
    name = "Воин_11",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "11-й уровень, способности воина",
    next_level = "Воин_12",
)

var fighter12: AbilityNodeLevel = AbilityNodeLevel(
    name = "Воин_12",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(abilityScoreImprovement.name)),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "12-й уровень, способности воина",
    next_level = "Воин_13",
)

var fighter13: AbilityNodeLevel = AbilityNodeLevel(
    name = "Воин_13",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.proficiencyBonus += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "13-й уровень, способности воина",
    next_level = "Воин_14",
)

var fighter14: AbilityNodeLevel = AbilityNodeLevel(
    name = "Воин_14",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(abilityScoreImprovement.name)),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "14-й уровень, способности воина",
    next_level = "Воин_15",
)

var fighter15: AbilityNodeLevel = AbilityNodeLevel(
    name = "Воин_15",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "15-й уровень, способности воина",
    next_level = "Воин_16",
)

var fighter16: AbilityNodeLevel = AbilityNodeLevel(
    name = "Воин_16",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(abilityScoreImprovement.name)),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "16-й уровень, способности воина",
    next_level = "Воин_17",
)

var fighter17: AbilityNodeLevel = AbilityNodeLevel(
    name = "Воин_17",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities.proficiencyBonus += 1
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "17-й уровень, способности воина",
    next_level = "Воин_18",
)

var fighter18: AbilityNodeLevel = AbilityNodeLevel(
    name = "Воин_18",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "18-й уровень, способности воина",
    next_level = "Воин_19",
)

var fighter19: AbilityNodeLevel = AbilityNodeLevel(
    name = "Воин_19",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(abilityScoreImprovement.name)),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "19-й уровень, способности воина",
    next_level = "Воин_20",
)

var fighter20: AbilityNodeLevel = AbilityNodeLevel(
    name = "Воин_20",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "20-й уровень, способности воина",
    next_level = null,
)

var mapOfFighterAbilities: MutableMap<String, AbilityNode> = (mutableMapOf(
    Pair(classFeaturesFighter.name, classFeaturesFighter),
    Pair(fightingStyle.name, fightingStyle),
    Pair(secondWind.name, secondWind),
    Pair(fighter1.name, fighter1),
    Pair(actionSurge.name, actionSurge),
    Pair(fighter2.name, fighter2),
    Pair(martialArchetype.name, martialArchetype),
    Pair(fighter3.name, fighter3),
    Pair(fighter4.name, fighter4),
    Pair(extraAttackFighter.name, extraAttackFighter),
    Pair(fighter5.name, fighter5),
    Pair(fighter6.name, fighter6),
    Pair(fighter7.name, fighter7),
    Pair(fighter8.name, fighter8),
    Pair(indomitable.name, indomitable),
    Pair(fighter9.name, fighter9),
    Pair(fighter10.name, fighter10),
    Pair(fighter11.name, fighter11),
    Pair(fighter12.name, fighter12),
    Pair(fighter13.name, fighter13),
    Pair(fighter14.name, fighter14),
    Pair(fighter15.name, fighter15),
    Pair(fighter16.name, fighter16),
    Pair(fighter17.name, fighter17),
    Pair(fighter18.name, fighter18),
    Pair(fighter19.name, fighter19),
    Pair(fighter20.name, fighter20)
)
        + mapOfBattleMasterAbilities
        + mapOfChampionAbilities).toMutableMap()