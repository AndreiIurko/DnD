package com.andreyyurko.dnd.data.abilities.classes.ranger

import com.andreyyurko.dnd.data.characterData.Action
import com.andreyyurko.dnd.data.characterData.ActionType
import com.andreyyurko.dnd.data.characterData.character.CharacterInfo
import com.andreyyurko.dnd.data.characterData.ChargesCounter
import com.andreyyurko.dnd.data.characterData.Classes
import com.andreyyurko.dnd.data.characterData.character.AbilityNode
import com.andreyyurko.dnd.data.characterData.character.abilityToModifier
import com.andreyyurko.dnd.data.spells.CharacterSpells
import com.andreyyurko.dnd.data.spells.SpellLists
import kotlin.math.max

var monsterSlayerSpells = AbilityNode(
    name = "Магия убийцы монстров",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        val additionalSpellList = mutableSetOf<String>()
        if (abilities.level >= 3) {
            additionalSpellList.add("Защита от зла и добра")
        }
        if (abilities.level >= 5) {
            additionalSpellList.add("Область истины")
        }
        if (abilities.level >= 9) {
            additionalSpellList.add("Магический круг")
        }
        if (abilities.level >= 13) {
            additionalSpellList.add("Изгнание")
        }
        if (abilities.level >= 17) {
            additionalSpellList.add("Удержание чудовища")
        }
        abilities.spellsInfo["Заклинания убийцы монстров"] = CharacterSpells(
            className = abilities.characterClass.className,
            maxKnownSpellsCount = 0,
            maxKnownCantripsCount = 0,
            spellLists = SpellLists(
                knownSpells = additionalSpellList
            )
        )
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 3 && abilities.characterClass == Classes.Ranger
    },
    description =
    "Вы узнаёте дополнительные заклинания, когда вы достигнете определенных уровней в этом классе, как показано в таблице «Заклинания убийцы монстров». Заклинания считаются для вас заклинаниями следопыта, и они не учитываются при подсчёте известных вам заклинаний следопыта.\n" +
            "Уровень следопыта | Заклинания\n" +
            "       3      | защита от зла и добра\n" +
            "       5      | область истины\n" +
            "       9      | магический круг\n" +
            "       13      | изгнание\n" +
            "       17      | удержание чудовища\n"
)

var huntersSense = AbilityNode(
    name = "Охотничьи чувства",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        if (!abilities.currentState.charges.contains("Охотничьи чувства")) {
            abilities.currentState.charges["Охотничьи чувства"] = ChargesCounter(
                current = max(abilityToModifier(abilities.wisdom), 1),
                maximum = max(abilityToModifier(abilities.wisdom), 1)
            )
        }
        abilities.currentState.charges["Охотничьи чувства"]?.let {
            if (it.maximum != max(abilityToModifier(abilities.wisdom), 1)) {
                abilities.currentState.charges["Охотничьи чувства"] = ChargesCounter(
                    current = max(abilityToModifier(abilities.wisdom), 1),
                    maximum = max(abilityToModifier(abilities.wisdom), 1)
                )
            }
        }
        abilities.actionsMap["Охотничьи чувства"] =
            Action(
                name = "Охотничьи чувства",
                description = "Вы получаете способность оценить существо и определить, как лучше всего навредить ему. Действием выберите одно существо, которое вы можете видеть в пределах 60 футов. Вы мгновенно узнаёте, имеет ли существо какие-либо иммунитеты, сопротивления или уязвимости, и какие они. Если существо скрыто от магии Прорицания, то вы чувствуете, что у него нет иммунитетов, сопротивлений или уязвимостей.\n" +
                        "\n" +
                        "Вы можете использовать это умение количество раз равное модификатору вашей Мудрости (минимум 1). Вы восстанавливаете все потраченные использования после окончания короткого или продолжительного отдыха.\n",
                type = ActionType.Action,
                relatedCharges = "Охотничьи чувства"
            )

        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo -> abilities.level >= 3 && abilities.characterClass == Classes.Ranger },
    description = "Вы получаете способность оценить существо и определить, как лучше всего навредить ему. Действием выберите одно существо, которое вы можете видеть в пределах 60 футов. Вы мгновенно узнаёте, имеет ли существо какие-либо иммунитеты, сопротивления или уязвимости, и какие они. Если существо скрыто от магии Прорицания, то вы чувствуете, что у него нет иммунитетов, сопротивлений или уязвимостей.\n" +
            "\n" +
            "Вы можете использовать это умение количество раз равное модификатору вашей Мудрости (минимум 1). Вы восстанавливаете все потраченные использования после окончания короткого или продолжительного отдыха.\n"
)

var slayersPrey = AbilityNode(
    name = "Добыча убийцы",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsMap["Добыча убийцы"] =
            Action(
                name = "Добыча убийцы",
                description = "Вы можете сфокусировать свой гнев на одном враге, увеличивая урон, который вы ему причиняете.\n" +
                        "\n" +
                        "Бонусным действием вы выбираете целью этого умения одно существо, которое можете видеть в пределах 60 футов. На каждом ходу, когда вы первый раз попадаете по этой цели атакой оружием, она получает дополнительный урон 1к6 от оружия.\n" +
                        "\n" +
                        "Это преимущество действует до окончания вашего короткого или продолжительного отдыха. Оно заканчивается раньше, если вы выбираете другое существо.\n",
                type = ActionType.Bonus,
            )

        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo -> abilities.level >= 3 && abilities.characterClass == Classes.Ranger },
    description = "Вы можете сфокусировать свой гнев на одном враге, увеличивая урон, который вы ему причиняете.\n" +
            "\n" +
            "Бонусным действием вы выбираете целью этого умения одно существо, которое можете видеть в пределах 60 футов. На каждом ходу, когда вы первый раз попадаете по этой цели атакой оружием, она получает дополнительный урон 1к6 от оружия.\n" +
            "\n" +
            "Это преимущество действует до окончания вашего короткого или продолжительного отдыха. Оно заканчивается раньше, если вы выбираете другое существо.\n"
)

var supernaturalDefense = AbilityNode(
    name = "Сверхъестественная защита",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Сверхъестественная защита"] =
            "Вы получаете дополнительную устойчивость против нападений вашей добычи на ваш ум и тело. Всякий раз, когда цель умения «Добыча убийцы» заставляет вас совершить спасбросок или когда вы совершаете проверку способности, чтобы перестать быть схваченным этой целью — добавьте 1к6 к броску.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo -> abilities.level >= 7 && abilities.characterClass == Classes.Ranger },
    description = "Вы получаете дополнительную устойчивость против нападений вашей добычи на ваш ум и тело. Всякий раз, когда цель умения «Добыча убийцы» заставляет вас совершить спасбросок или когда вы совершаете проверку способности, чтобы перестать быть схваченным этой целью — добавьте 1к6 к броску.\n"
)

var magicusersNemesis = AbilityNode(
    name = "Враг заклинателя",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        if (!abilities.currentState.charges.contains("Враг заклинателя")) {
            abilities.currentState.charges["Враг заклинателя"] = ChargesCounter(
                current = 1,
                maximum = 1
            )
        }
        abilities.actionsMap["Враг заклинателя"] =
            Action(
                name = "Враг заклинателя",
                description = "Вы получаете способность разрушать чужую магию.\n" +
                        "\n" +
                        "Когда вы видите существо, накладывающее заклинание или телепортирующееся в пределах 60 футов от вас, вы можете реакцией попытаться магически помешать ей. Существо должно преуспеть в спасброске Мудрости против Сл ваших заклинаний, иначе заклинание или телепортация терпит неудачу.\n" +
                        "\n" +
                        "Использовав это умение, вы не можете использовать его повторно до окончания короткого или продолжительного отдыха.\n",
                type = ActionType.Reaction,
                relatedCharges = "Враг заклинателя"
            )

        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo -> abilities.level >= 11 && abilities.characterClass == Classes.Ranger },
    description = "Вы получаете способность разрушать чужую магию.\n" +
            "\n" +
            "Когда вы видите существо, накладывающее заклинание или телепортирующееся в пределах 60 футов от вас, вы можете реакцией попытаться магически помешать ей. Существо должно преуспеть в спасброске Мудрости против Сл ваших заклинаний, иначе заклинание или телепортация терпит неудачу.\n" +
            "\n" +
            "Использовав это умение, вы не можете использовать его повторно до окончания короткого или продолжительного отдыха.\n"
)

var slayersCounter = AbilityNode(
    name = "Контратака убийцы",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsMap["Контратака убийцы"] =
            Action(
                name = "Контратака убийцы",
                description = "Вы получаете возможность контратаковать, когда ваша жертва пытается навредить вам. Если цель умения «Добыча убийцы» заставляет вас совершить спасбросок, то вы можете реакцией совершить одну атаку оружием по Добыче. Вы совершаете эту атаку непосредственно перед выполнением спасброска. Если атака попадает, вы автоматически проходите спасбросок в дополнение к обычным эффектам атаки.\n",
                type = ActionType.Reaction,
            )

        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo -> abilities.level >= 15 && abilities.characterClass == Classes.Ranger },
    description = "Вы получаете возможность контратаковать, когда ваша жертва пытается навредить вам. Если цель умения «Добыча убийцы» заставляет вас совершить спасбросок, то вы можете реакцией совершить одну атаку оружием по Добыче. Вы совершаете эту атаку непосредственно перед выполнением спасброска. Если атака попадает, вы автоматически проходите спасбросок в дополнение к обычным эффектам атаки.\n"
)

var monsterSlayer = AbilityNode(
    name = "Убийца монстров",
    changesInCharacterInfo = { abilities: CharacterInfo -> abilities },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(monsterSlayerSpells.name) }),
        Pair("second", { listOf(huntersSense.name) }),
        Pair("third", { listOf(slayersPrey.name) }),
        Pair("fourth", { listOf(supernaturalDefense.name) }),
        Pair("fifth", { listOf(magicusersNemesis.name) }),
        Pair("sixth", { listOf(slayersCounter.name) })
    ),
    requirements = { true },
    description = "",
    isNeedsToBeShown = false
)

var mapOfMonsterSlayerAbilities: MutableMap<String, AbilityNode> = mutableMapOf(
    Pair(monsterSlayerSpells.name, monsterSlayerSpells),
    Pair(huntersSense.name, huntersSense),
    Pair(slayersPrey.name, slayersPrey),
    Pair(supernaturalDefense.name, supernaturalDefense),
    Pair(monsterSlayer.name, monsterSlayer),
    Pair(magicusersNemesis.name, magicusersNemesis),
    Pair(slayersCounter.name, slayersCounter)
)