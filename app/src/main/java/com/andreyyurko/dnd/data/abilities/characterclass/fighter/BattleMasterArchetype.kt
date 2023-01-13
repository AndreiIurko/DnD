package com.andreyyurko.dnd.data.abilities.characterclass.fighter

import com.andreyyurko.dnd.data.characters.CharacterInfo
import com.andreyyurko.dnd.data.characters.Classes
import com.andreyyurko.dnd.data.characters.character.AbilityNode

var combatSuperiority = AbilityNode(
    name = "Боевое превосходство",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        abilities.level >= 3 && abilities.characterClass == Classes.Fighter
    },
    description = "Если вы выбираете этот архетип, вы изучаете приёмы, использующие специальные кости, называемые костями превосходства.\n" +
            "\n" +
            "Приёмы. Вы изучаете три приёма на ваш выбор. Большинство приёмов тем или иным образом усиливают атаку. Во время одной атаки вы можете использовать только один приём. Вы изучаете два дополнительных приёма при достижении 7-го, 10-го и 15-го уровней. Каждый раз, при изучении новых приёмов, вы можете также заменить один из известных вам приёмов на другой.\n" +
            "\n" +
            "Кости превосходства. У вас есть четыре кости превосходства. Это кости к8. Кости превосходства тратятся при использовании. Вы восполняете все потраченные кости в конце короткого или продолжительного отдыха.\n" +
            "\n" +
            "Вы получаете ещё по одной кости превосходства на 7-м и 15-м уровнях.\n" +
            "\n" +
            "Спасброски. Некоторые из ваших приёмов требуют от цели спасброска, чтобы избежать эффекта приёма. Сложность такого спасброска рассчитывается следующим образом:\n" +
            "\n" +
            "Сложность спасброска приёма = 8 + ваш бонус мастерства + ваш модификатор Силы или Ловкости (на ваш выбор)"
)

var studentOfWar = AbilityNode(
    name = "Ученик войны",
    changesInCharacterInfo = {abilities: CharacterInfo -> abilities },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        abilities.level >= 3 && abilities.characterClass == Classes.Fighter
    },
    description = "Вы осваиваете владением одним из ремесленных инструментов на ваш выбор."
)

var knowYourEnemy = AbilityNode(
    name = "Познай своего врага",
    changesInCharacterInfo = {abilities: CharacterInfo -> abilities },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        abilities.level >= 7 && abilities.characterClass == Classes.Fighter
    },
    description = "Если вы потратите как минимум 1 минуту, рассматривая, или по другому взаимодействуя с существом вне боя, вы можете узнать некоторую информацию о его способностях в сравнении с вашими. Мастер сообщит вам, равняется ли существо, превосходит или уступает вам в двух характеристиках на ваш выбор из перечисленных:\n" +
            "\n" +
            "    Значение Силы\n" +
            "    Значение Ловкости\n" +
            "    Значение Телосложения\n" +
            "    Класс Доспеха\n" +
            "    Текущие хиты\n" +
            "    Общее количество уровней (если есть)\n" +
            "    Количество уровней в классе Воин (если есть)\n"
)

var improvedCombatSuperiority = AbilityNode(
    name = "Улучшенное боевое превосходство",
    changesInCharacterInfo = {abilities: CharacterInfo -> abilities },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        abilities.level >= 10 && abilities.characterClass == Classes.Fighter
    },
    description = "Ваша кость превосходства увеличивается до к10. На 18-м уровне — до к12."
)

var relentless = AbilityNode(
    name = "Неослабевающий",
    changesInCharacterInfo = {abilities: CharacterInfo -> abilities },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        abilities.level >= 15 && abilities.characterClass == Classes.Fighter
    },
    description = "Если вы совершаете бросок инициативы, не имея костей превосходства, вы получаете одну."
)

var battleMaster = AbilityNode(
    name = "Мастер боевых искусств",
    changesInCharacterInfo = {abilities: CharacterInfo -> abilities },
    alternatives = mutableMapOf(
        Pair("first", listOf(combatSuperiority.name)),
        Pair("second", listOf(studentOfWar.name)),
        Pair("third", listOf(knowYourEnemy.name)),
        Pair("fourth", listOf(improvedCombatSuperiority.name)),
        Pair("fifth", listOf(relentless.name))
    ),
    requirements = {true},
    description = "",
    isNeedsToBeShown = false
)

var mapOfBattleMasterAbilities: MutableMap<String, AbilityNode> = mutableMapOf(
    Pair(combatSuperiority.name, combatSuperiority),
    Pair(studentOfWar.name, studentOfWar),
    Pair(knowYourEnemy.name, knowYourEnemy),
    Pair(improvedCombatSuperiority.name, improvedCombatSuperiority),
    Pair(relentless.name, relentless),
    Pair(battleMaster.name, battleMaster)
)