package com.andreyyurko.dnd.data.abilities.classes.sorcerer

import com.andreyyurko.dnd.data.characterData.*
import com.andreyyurko.dnd.data.characterData.character.AbilityNode

var wildMagicSurge: AbilityNode = AbilityNode(
    name = "Волна дикой магии",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Волна дикой магии"] =
            "Ваше колдовство может вызвать волны дикой магии. Сразу после накладывания вами заклинания чародея как минимум 1-го уровня Мастер может попросить вас бросить к20. Если выпадает «1», бросьте кость по таблице «Волна дикой магии» для создания случайного магического эффекта. Волна может возникать только один раз за ход. Если эффект волны является заклинанием, он слишком непредсказуем, чтобы его можно было модифицировать метамагией. Если заклинание-эффект в обычных обстоятельствах требует концентрацию, то в данном случае оно не требует концентрацию и длится свою максимальную длительность."
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 1 && abilities.characterClass == Classes.Sorcerer
    },
    description = "Ваше колдовство может вызвать волны дикой магии. Сразу после накладывания вами заклинания чародея как минимум 1-го уровня Мастер может попросить вас бросить к20. Если выпадает «1», бросьте кость по таблице «Волна дикой магии» для создания случайного магического эффекта. Волна может возникать только один раз за ход. Если эффект волны является заклинанием, он слишком непредсказуем, чтобы его можно было модифицировать метамагией. Если заклинание-эффект в обычных обстоятельствах требует концентрацию, то в данном случае оно не требует концентрацию и длится свою максимальную длительность."
)

var tidesOfChaos: AbilityNode = AbilityNode(
    name = "Поток Хаоса",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        if (!abilities.currentState.charges.contains("Поток Хаоса")) {
            abilities.currentState.charges["Поток Хаоса"] = ChargesCounter(
                current = 1,
                maximum = 1
            )
        }

        abilities.actionsList.add(
            Action(
                name = "Поток Хаоса",
                description = "Вы можете манипулировать силами случая и хаоса, чтобы получить преимущество для одного броска атаки, проверки характеристики или спасброска. Сделав это, вы должны совершить продолжительный отдых, прежде чем вы сможете использовать это умение ещё раз.\n" +
                        "\n" +
                        "В любой момент до того как вы восстановите это умение, сразу после использования вами заклинания чародея как минимум 1-го уровня, Мастер может попросить вас бросить кость на эффект от «Волны дикой магии». После этого вы восстанавливаете использование этого умения.",
                type = ActionType.PartOfAction,
                relatedCharges = "Поток Хаоса"
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 1 && abilities.characterClass == Classes.Sorcerer
    },
    description = "Вы можете манипулировать силами случая и хаоса, чтобы получить преимущество для одного броска атаки, проверки характеристики или спасброска. Сделав это, вы должны совершить продолжительный отдых, прежде чем вы сможете использовать это умение ещё раз.\n" +
            "\n" +
            "В любой момент до того как вы восстановите это умение, сразу после использования вами заклинания чародея как минимум 1-го уровня, Мастер может попросить вас бросить кость на эффект от «Волны дикой магии». После этого вы восстанавливаете использование этого умения."
)

var bendLuck: AbilityNode = AbilityNode(
    name = "Подчинение удачи",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Подчинение удачи",
                description = "Вы получаете возможность менять судьбу с помощью дикой магии. Когда другое существо, видимое вам, совершает бросок атаки, проверку характеристики или спасбросок, вы можете реакцией потратить 2 единицы чародейства и бросить 1к4. Результат броска станет бонусом или штрафом (на ваш выбор) к броску существа. Вы можете сделать это после броска существа, но до того, как начнут действовать эффекты от броска.",
                type = ActionType.Reaction,
                relatedCharges = "Единицы чародейства"
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 6 && abilities.characterClass == Classes.Sorcerer
    },
    description = "Вы получаете возможность менять судьбу с помощью дикой магии. Когда другое существо, видимое вам, совершает бросок атаки, проверку характеристики или спасбросок, вы можете реакцией потратить 2 единицы чародейства и бросить 1к4. Результат броска станет бонусом или штрафом (на ваш выбор) к броску существа. Вы можете сделать это после броска существа, но до того, как начнут действовать эффекты от броска."
)

var controlledChaos: AbilityNode = AbilityNode(
    name = "Контролируемый хаос",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        if (abilities.additionalAbilities.contains("Волна дикой магии")) {
            abilities.additionalAbilities["Волна дикой магии"] =
                abilities.additionalAbilities["Волна дикой магии"] + "\n" + "Каждый раз, когда вы совершаете бросок по таблице дикой магии, вы можете совершить два броска и использовать любой из двух."
        }
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 14 && abilities.characterClass == Classes.Sorcerer
    },
    description = "Вы получаете толику контроля над волнами дикой магии. Каждый раз, когда вы совершаете бросок по таблице дикой магии, вы можете совершить два броска и использовать любой из двух."
)

var spellBombardment: AbilityNode = AbilityNode(
    name = "Разрушительные заклинания",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Разрушительные заклинания",
                description = "Когда вы совершаете бросок урона от заклинания, и на одной из костей выпадает максимально возможное значение, выберите одну из этих костей и бросьте её ещё раз. Добавьте выпавшее значение к урону. Вы можете использовать это умение только один раз в ход.",
                type = ActionType.PartOfAction
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 18 && abilities.characterClass == Classes.Sorcerer
    },
    description = "Разрушительная энергия ваших заклинаний увеличивается. Когда вы совершаете бросок урона от заклинания, и на одной из костей выпадает максимально возможное значение, выберите одну из этих костей и бросьте её ещё раз. Добавьте выпавшее значение к урону. Вы можете использовать это умение только один раз в ход."
)

var wildMagic: AbilityNode = AbilityNode(
    name = "Дикая магия",
    changesInCharacterInfo = { abilities: CharacterInfo -> abilities },
    alternatives = mutableMapOf(
        Pair("first", listOf(wildMagicSurge.name)),
        Pair("second", listOf(tidesOfChaos.name)),
        Pair("third", listOf(bendLuck.name)),
        Pair("fourth", listOf(controlledChaos.name)),
        Pair("fifth", listOf(spellBombardment.name))
    ),
    requirements = { abilities: CharacterInfo ->
        abilities.characterClass == Classes.Sorcerer
    },
    description = "",
    isNeedsToBeShown = false
)

var wildMagicMap = mutableMapOf(
    Pair(wildMagicSurge.name, wildMagicSurge),
    Pair(tidesOfChaos.name, tidesOfChaos),
    Pair(bendLuck.name, bendLuck),
    Pair(controlledChaos.name, controlledChaos),
    Pair(spellBombardment.name, spellBombardment),
    Pair(wildMagic.name, wildMagic)
)