package com.andreyyurko.dnd.data.abilities.characterclass

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
    requirements = {_ -> true},
    add_requirements = listOf(),
    description = "1-й уровень, способности монаха",
    next_level = "Воин_2"
)

var mapOfFighterAbilities: MutableMap<String, AbilityNode> = mutableMapOf(
    Pair(fightingStyle.name, fightingStyle),
    Pair(secondWind.name, secondWind),
    Pair(fighter1.name, fighter1)
)