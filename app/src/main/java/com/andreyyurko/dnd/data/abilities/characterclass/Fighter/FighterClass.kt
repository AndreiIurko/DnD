package com.andreyyurko.dnd.data.abilities.characterclass

import com.andreyyurko.dnd.data.abilities.other.abilityScoreImprovement
import com.andreyyurko.dnd.data.abilities.other.mapOfFightingStyles
import com.andreyyurko.dnd.data.characters.*

var fightingStyle: AbilityNode = AbilityNode(
    name = "Боевой стиль",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities
    },
    alternatives = mutableMapOf(
        Pair("style", mapOfFightingStyles.keys.toList())
    ),
    requirements = {abilities: CharacterInfo ->
        abilities.characterClass == Classes.Fighter
    },
    description = "Вы выбираете боевой стиль, соответствующий вашей специализации. Выберите один из следующих вариантов. Вы не можете выбирать один и тот же вариант боевого стиля, даже если позже у вас будет возможность выбрать еще один стиль."
)

var secondWind: AbilityNode = AbilityNode(
    name = "Второе дыхание",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        if ( ! abilities.currentState.charges.contains("Второе дыхание")) {
            abilities.currentState.charges["Второе дыхание"] = ChargesCounter(current = 1, maximum = 1)
            abilities.actionsList.add(Action(
                name = "Второе дыхание",
                description = "Вы обладаете ограниченным источником выносливости, которым можете воспользоваться, чтобы уберечь себя. В свой ход вы можете бонусным действием восстановить хиты в размере 1к10 + ваш уровень воина.\n" +
                        "\n" +
                        "Использовав это умение, вы должны завершить короткий либо продолжительный отдых, чтобы получить возможность использовать его снова.",
                type = ActionType.Bonus
            ))
        }
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        abilities.characterClass == Classes.Fighter
    },
    description = "Вы обладаете ограниченным источником выносливости, которым можете воспользоваться, чтобы уберечь себя. В свой ход вы можете бонусным действием восстановить хиты в размере 1к10 + ваш уровень воина.\n" +
            "\n" +
            "Использовав это умение, вы должны завершить короткий либо продолжительный отдых, чтобы получить возможность использовать его снова."
)

var fighter1 : AbilityNodeLevel = AbilityNodeLevel(
    name = "Воин_1",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.characterClass = Classes.Fighter
        abilities.level += 1
        abilities.proficiencyBonus += 2
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(fightingStyle.name)),
        Pair("Second", listOf(secondWind.name))
    ),
    requirements = {true},
    add_requirements = listOf(),
    description = "1-й уровень, способности воина",
    next_level = "Воин_2",
)

var actionSurge: AbilityNode = AbilityNode(
    name = "Всплеск действий",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.actionsList.add(Action(
            name = "Всплеск действий",
            description = "Вы получаете возможность на мгновение преодолеть обычные возможности. В свой ход вы можете совершить одно дополнительное действие помимо обычного и бонусного действий. Использовав это умение, вы должны завершить короткий или продолжительный отдых, чтобы получить возможность использовать его снова. Начиная с 17-го уровня, вы можете использовать это умение дважды, прежде чем вам понадобится отдых, но в течение одного хода его всё равно можно использовать лишь один раз.",
            type = ActionType.Additional
        ))
        abilities.currentState.charges["Всплеск действий"] = ChargesCounter(
            current = 1,
            maximum = 1
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        abilities.characterClass == Classes.Fighter
    },
    description = "Вы получаете возможность на мгновение преодолеть обычные возможности. В свой ход вы можете совершить одно дополнительное действие помимо обычного и бонусного действий. Использовав это умение, вы должны завершить короткий или продолжительный отдых, чтобы получить возможность использовать его снова. Начиная с 17-го уровня, вы можете использовать это умение дважды, прежде чем вам понадобится отдых, но в течение одного хода его всё равно можно использовать лишь один раз."
)

var fighter2 : AbilityNodeLevel = AbilityNodeLevel(
    name = "Воин_2",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.level += 1
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(actionSurge.name)),
    ),
    requirements = {true},
    add_requirements = listOf(),
    description = "2-й уровень, способности воина",
    next_level = "Воин_3",
)

var martialArchetype: AbilityNode = AbilityNode(
    name = "Воинский архетип",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        abilities.characterClass == Classes.Fighter
    },
    description = "Вы выбираете архетип, отражающий стиль и технику, к которым вы стремитесь. Подробности всех архетипов приведены ниже. Выбранный вами архетип предоставляет вам умения на 3-м, 7-м, 10-м, 15-м и 18-м уровнях."
)

var fighter3 : AbilityNodeLevel = AbilityNodeLevel(
    name = "Воин_3",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.level += 1
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(martialArchetype.name)),
    ),
    requirements = {true},
    add_requirements = listOf(),
    description = "3-й уровень, способности воина",
    next_level = "Воин_4",
)

var fighter4 : AbilityNodeLevel = AbilityNodeLevel(
    name = "Воин_4",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.level += 1
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(abilityScoreImprovement.name)),
    ),
    requirements = {true},
    add_requirements = listOf(),
    description = "4-й уровень, способности воина",
    next_level = null,
)



var mapOfFighterAbilities: MutableMap<String, AbilityNode> = mutableMapOf(
    Pair(fightingStyle.name, fightingStyle),
    Pair(secondWind.name, secondWind),
    Pair(fighter1.name, fighter1),
    Pair(actionSurge.name, actionSurge),
    Pair(fighter2.name, fighter2),
    Pair(martialArchetype.name, martialArchetype),
    Pair(fighter3.name, fighter3),
    Pair(fighter4.name, fighter4)
)