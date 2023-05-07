package com.andreyyurko.dnd.data.abilities.classes.sorcerer

import com.andreyyurko.dnd.data.abilities.classes.AbilityNodeLevel
import com.andreyyurko.dnd.data.abilities.other.*
import com.andreyyurko.dnd.data.characterData.*
import com.andreyyurko.dnd.data.characterData.character.AbilityNode
import com.andreyyurko.dnd.data.characterData.character.abilityToModifier
import com.andreyyurko.dnd.data.spells.CharacterSpells

var classFeaturesSorcerer: AbilityNode = AbilityNode(
    name = "Чародей: классовые умения",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.savingThrowProf.add(Ability.Constitution)
        abilities.savingThrowProf.add(Ability.Charisma)
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair(
            "skill1",
            { listOf(intimidation.name, arcana.name, deception.name, insight.name, religion.name, persuasion.name) }
        ),
        Pair(
            "skill2",
            { listOf(intimidation.name, arcana.name, deception.name, insight.name, religion.name, persuasion.name) }
        ),
    ),
    requirements = { abilities: CharacterInfo ->
        abilities.characterClass == Classes.Sorcerer
    },
    description = "ХИТЫ\n" +
            "\n" +
            "Кость Хитов: 1к6 за каждый уровень чародея\n" +
            "\n" +
            "Хиты на 1 уровне: 6 + модификатор Телосложения\n" +
            "\n" +
            "Хиты на следующих уровнях: 1к6 (или 4) + модификатор Телосложения (суммарно минимум 1) за каждый уровень чародея после первого\n" +
            "\n" +
            "ВЛАДЕНИЕ\n" +
            "\n" +
            "Доспехи: нет\n" +
            "\n" +
            "Оружие: Боевые посохи, дротики, кинжалы, лёгкие арбалеты, пращи\n" +
            "\n" +
            "Инструменты: нет\n" +
            "\n" +
            "Спасброски: Телосложение, Харизма\n" +
            "\n" +
            "Навыки: Выберите два навыка из следующих: Запугивание, Магия, Обман, Проницательность, Религия, Убеждение.",
    priority = Priority.DoFirst
)

var spellCastingSorcerer: AbilityNode = AbilityNode(
    name = "Чародей: использование заклинаний",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.spellsInfo.apply {
            if (!this.contains("Заклинания класса")) {
                this["Заклинания класса"] = CharacterSpells()
            }
            this["Заклинания класса"]?.className = Classes.Sorcerer.className
            this["Заклинания класса"]?.maxKnownSpellsCount = 2
            this["Заклинания класса"]?.maxKnownCantripsCount = 4
        }
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.characterClass == Classes.Sorcerer
    },
    description = "Определённое событие в вашем прошлом или в жизни ваших родителей или предков оставило на вас неизгладимый отпечаток, связав вас с магией. Ваши заклинания питает некий источник магии, какого бы происхождения он не был.\n" +
            "Заговоры (заклинания 0-го уровня)\n" +
            "\n" +
            "На 1-м уровне вам известны четыре заговора, которые вы выбираете из списка заклинаний чародея. Вы обучаетесь дополнительным заговорам чародея на свой выбор по мере роста в уровнях, как это показано в колонке «Известные заговоры».\n" +
            "Ячейки заклинаний\n" +
            "\n" +
            "Таблица «Чародей» показывает, сколько ячеек заклинаний для сотворения заклинаний чародея у вас есть для заклинаний 1-го и других уровней. Для использования заклинания вы должны потратить ячейку соответствующего, либо превышающего уровня. Вы восстанавливаете все потраченные ячейки по окончании продолжительного отдыха.\n" +
            "\n" +
            "Например, если вы знаете заклинание 1-го уровня огненные ладони [burning hands], и у вас есть ячейки 1-го и 2-го уровней, вы можете наложить его с помощью любой из этих ячеек.\n" +
            "Известные заклинания первого и более высоких уровней\n" +
            "\n" +
            "Вы знаете два заклинания 1-го уровня на свой выбор из списка заклинаний чародея.\n" +
            "\n" +
            "Колонка «Известные заклинания» показывает, когда вы сможете выучить новые заклинания. Уровень заклинаний не должен превышать уровень самой высокой имеющейся у вас ячейки заклинаний. Например, когда вы достигнете 3-го уровня в этом классе, вы можете выучить одно новое заклинание 1-го или 2-го уровня.\n" +
            "\n" +
            "Кроме того, когда вы получаете новый уровень в этом классе, вы можете одно из известных вам заклинаний чародея заменить на другое из списка чародея, уровень которого тоже должен соответствовать имеющимся ячейкам заклинаний.\n" +
            "Базовая характеристика заклинаний\n" +
            "\n" +
            "Вы используете Харизму для накладывания заклинаний чародея. Мощь заклинаний зависит от вашей способности проецировать свою волю в реальность. Вы используете Харизму в случаях, когда заклинание ссылается на базовую характеристику. Кроме того, вы используете Харизму при определении Сл спасбросков от ваших заклинаний, и при броске атаки заклинаниями.\n" +
            "\n" +
            "Сл спасброска = 8 + ваш бонус мастерства + ваш модификатор Харизмы\n" +
            "\n" +
            "Модификатор броска атаки = ваш бонус мастерства + ваш модификатор Харизмы\n" +
            "Фокусировка заклинания\n" +
            "\n" +
            "Вы можете использовать магическую фокусировку в качестве фокусировки для заклинаний чародея",
    priority = Priority.DoAsSoonAsPossible
)

var sorcerousOrigin: AbilityNode = AbilityNode(
    name = "Происхождение чародея",
    changesInCharacterInfo = { abilities: CharacterInfo -> abilities },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(draconicBloodline.name, wildMagic.name) })
    ),
    requirements = { true },
    description = "Выберите источник, из которого ваш персонаж черпает свою силу.\n" +
            "\n" +
            "Ваш выбор предоставляет вам умения на 1-м, 6-м, 14-м и 18-м уровнях."
)

var sorcerer1: AbilityNodeLevel = AbilityNodeLevel(
    name = "Чародей_1",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.characterClass = Classes.Sorcerer
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.proficiencyBonus += 2
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(classFeaturesSorcerer.name) }),
        Pair("second", { listOf(spellCastingSorcerer.name) }),
        Pair("third", { listOf(sorcerousOrigin.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "1-й уровень, способности чародея",
    next_level = "Чародей_2",
)

var fontOfMagic: AbilityNode = AbilityNode(
    name = "Источник магии",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        if (!abilities.currentState.charges.contains("Единицы чародейства")) {
            abilities.currentState.charges["Единицы чародейства"] = ChargesCounter(
                current = 2,
                maximum = 2
            )
        }
        if (abilities.level > abilities.currentState.charges["Единицы чародейства"]!!.maximum) {
            abilities.currentState.charges["Единицы чародейства"]!!.maximum = abilities.level
            abilities.currentState.charges["Единицы чародейства"]!!.current = abilities.level
        }

        abilities.actionsList.add(
            Action(
                name = "Преобразование ячейки заклинания в единицы чародейства",
                description = "Вы можете в свой ход бонусным действием преобразовать одну ячейку заклинаний в единицы чародейства, количество которых равно уровню ячейки.",
                type = ActionType.Bonus,
                relatedCharges = "Единицы чародейства"
            )
        )

        abilities.actionsList.add(
            Action(
                name = "Создание ячеек заклинаний",
                description = " В свой ход вы можете бонусным действием превратить оставшиеся единицы чародейства в дополнительные ячейки заклинаний. Приведённая таблица отображает стоимость создания ячеек разных уровней. Вы не можете создавать ячейки с уровнем выше 5. Созданные ячейки заклинаний исчезают в конце длительного отдыха.\n" +
                        "СОЗДАНИЕ ЯЧЕЕК ЗАКЛИНАНИЙ\n" +
                        "Единицы -> Уровень\n" +
                        "   2    ->     1\n" +
                        "   3    ->     2\n" +
                        "   5    ->     3\n" +
                        "   6    ->     4\n" +
                        "   7    ->     5\n",
                type = ActionType.Bonus,
                relatedCharges = "Единицы чародейства"
            )
        )

        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.characterClass == Classes.Sorcerer
    },
    description = "Вы получаете доступ к внутреннему источнику магии. Этот источник выражен в единицах чародейства, позволяющих вам создавать разнообразные магические эффекты.\n" +
            "Единицы чародейства\n" +
            "\n" +
            "Вы получаете 2 единицы чародейства, и их становится больше на более высоких уровнях. У вас не может быть этих единиц больше, чем указанно в таблице для вашего уровня. Вы восстанавливаете все использованные единицы чародейства по окончании продолжительного отдыха.\n" +
            "Свободное заклинательство\n" +
            "\n" +
            "Вы можете использовать единицы чародейства, чтобы получить дополнительные ячейки заклинаний, и наоборот, пожертвовать ячейками, чтобы получить единицы. Другие способы использования единиц чародейства вы освоите на более высоких уровнях.\n" +
            "\n" +
            "Преобразование ячейки заклинания в единицы чародейства. Вы можете в свой ход бонусным действием преобразовать одну ячейку заклинаний в единицы чародейства, количество которых равно уровню ячейки.\n" +
            "\n" +
            "Создание ячеек заклинаний. В свой ход вы можете бонусным действием превратить оставшиеся единицы чародейства в дополнительные ячейки заклинаний. Приведённая таблица отображает стоимость создания ячеек разных уровней. Вы не можете создавать ячейки с уровнем выше 5. Созданные ячейки заклинаний исчезают в конце длительного отдыха.\n" +
            "СОЗДАНИЕ ЯЧЕЕК ЗАКЛИНАНИЙ\n" +
            "Единицы -> Уровень\n" +
            "   2    ->     1\n" +
            "   3    ->     2\n" +
            "   5    ->     3\n" +
            "   6    ->     4\n" +
            "   7    ->     5\n",
)

var sorcerer2: AbilityNodeLevel = AbilityNodeLevel(
    name = "Чародей_2",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities.spellsInfo["Заклинания класса"]?.let {
            it.maxKnownSpellsCount += 1
        }
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(fontOfMagic.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "2-й уровень, способности чародея",
    next_level = "Чародей_3",
)

var metamagic: AbilityNode = AbilityNode(
    name = "Метамагия",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair(
            "first", {
                listOf(
                    carefulSpell.name, distantSpell.name, empoweredSpell.name, extendedSpell.name,
                    heightenedSpell.name, quickenedSpell.name, subtleSpell.name, twinnedSpell.name
                )
            }
        ),
        Pair(
            "second", {
                listOf(
                    carefulSpell.name, distantSpell.name, empoweredSpell.name, extendedSpell.name,
                    heightenedSpell.name, quickenedSpell.name, subtleSpell.name, twinnedSpell.name
                )
            }
        ),
    ),
    requirements = { true },
    description = "Вы получаете способность подстраивать заклинания под свои нужды. Вы выбираете два варианта метамагии из перечисленных ниже. На 10-м и 17-м уровне вы получаете ещё по одному варианту.\n" +
            "\n" +
            "При накладывании заклинания может быть использован только один метамагический вариант, если в его описании не указано обратное."
)

var sorcerer3: AbilityNodeLevel = AbilityNodeLevel(
    name = "Чародей_3",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities.spellsInfo["Заклинания класса"]?.let {
            it.maxKnownSpellsCount += 1
        }
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(metamagic.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "3-й уровень, способности чародея",
    next_level = "Чародей_4",
)

var sorcerer4: AbilityNodeLevel = AbilityNodeLevel(
    name = "Чародей_4",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities.spellsInfo["Заклинания класса"]?.let {
            it.maxKnownSpellsCount += 1
        }
        abilities.spellsInfo["Заклинания класса"]?.let {
            it.maxKnownCantripsCount += 1
        }
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "4-й уровень, способности чародея",
    next_level = "Чародей_5",
)

var sorcerer5: AbilityNodeLevel = AbilityNodeLevel(
    name = "Чародей_5",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.proficiencyBonus += 1
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities.spellsInfo["Заклинания класса"]?.let {
            it.maxKnownSpellsCount += 1
        }
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "5-й уровень, способности чародея",
    next_level = "Чародей_6",
)

var sorcerer6: AbilityNodeLevel = AbilityNodeLevel(
    name = "Чародей_6",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities.spellsInfo["Заклинания класса"]?.let {
            it.maxKnownSpellsCount += 1
        }
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "6-й уровень, способности чародея",
    next_level = "Чародей_7",
)

var sorcerer7: AbilityNodeLevel = AbilityNodeLevel(
    name = "Чародей_7",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities.spellsInfo["Заклинания класса"]?.let {
            it.maxKnownSpellsCount += 1
        }
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "7-й уровень, способности чародея",
    next_level = "Чародей_8",
)

var sorcerer8: AbilityNodeLevel = AbilityNodeLevel(
    name = "Чародей_8",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities.spellsInfo["Заклинания класса"]?.let {
            it.maxKnownSpellsCount += 1
        }
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "8-й уровень, способности чародея",
    next_level = "Чародей_9",
)

var sorcerer9: AbilityNodeLevel = AbilityNodeLevel(
    name = "Чародей_9",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities.proficiencyBonus += 1
        abilities.spellsInfo["Заклинания класса"]?.let {
            it.maxKnownSpellsCount += 1
        }
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "9-й уровень, способности чародея",
    next_level = "Чародей_10",
)

var metamagic2: AbilityNode = AbilityNode(
    name = "Метамагия: выбор опции",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair(
            "first", {
                listOf(
                    carefulSpell.name, distantSpell.name, empoweredSpell.name, extendedSpell.name,
                    heightenedSpell.name, quickenedSpell.name, subtleSpell.name, twinnedSpell.name
                )
            }
        ),
    ),
    requirements = { true },
    description = "Выберите еще одну опцию метамагии:"
)

var sorcerer10: AbilityNodeLevel = AbilityNodeLevel(
    name = "Чародей_10",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities.spellsInfo["Заклинания класса"]?.let {
            it.maxKnownSpellsCount += 1
        }
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(metamagic2.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "10-й уровень, способности чародея",
    next_level = "Чародей_11",
)

var sorcerer11: AbilityNodeLevel = AbilityNodeLevel(
    name = "Чародей_11",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities.spellsInfo["Заклинания класса"]?.let {
            it.maxKnownSpellsCount += 1
        }
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "11-й уровень, способности чародея",
    next_level = "Чародей_12",
)

var sorcerer12: AbilityNodeLevel = AbilityNodeLevel(
    name = "Чародей_12",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities.spellsInfo["Заклинания класса"]?.let {
            it.maxKnownSpellsCount += 1
        }
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "12-й уровень, способности чародея",
    next_level = "Чародей_13",
)

var sorcerer13: AbilityNodeLevel = AbilityNodeLevel(
    name = "Чародей_13",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities.proficiencyBonus += 1
        abilities.spellsInfo["Заклинания класса"]?.let {
            it.maxKnownSpellsCount += 1
        }
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "13-й уровень, способности чародея",
    next_level = "Чародей_14",
)

var sorcerer14: AbilityNodeLevel = AbilityNodeLevel(
    name = "Чародей_14",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities.spellsInfo["Заклинания класса"]?.let {
            it.maxKnownSpellsCount += 1
        }
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "14-й уровень, способности чародея",
    next_level = "Чародей_15",
)

var sorcerer15: AbilityNodeLevel = AbilityNodeLevel(
    name = "Чародей_15",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities.spellsInfo["Заклинания класса"]?.let {
            it.maxKnownSpellsCount += 1
        }
        if (!abilities.currentState.charges.contains("Ячейки_8")) {
            abilities.currentState.charges["Ячейки_8"] = ChargesCounter(
                current = 1,
                maximum = 1
            )
        }
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "15-й уровень, способности чародея",
    next_level = "Чародей_16",
)

var sorcerer16: AbilityNodeLevel = AbilityNodeLevel(
    name = "Чародей_16",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities.spellsInfo["Заклинания класса"]?.let {
            it.maxKnownSpellsCount += 1
        }
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "16-й уровень, способности чародея",
    next_level = "Чародей_17",
)

var metamagic3: AbilityNode = AbilityNode(
    name = "Метамагия: выбор опции",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair(
            "first", {
                listOf(
                    carefulSpell.name, distantSpell.name, empoweredSpell.name, extendedSpell.name,
                    heightenedSpell.name, quickenedSpell.name, subtleSpell.name, twinnedSpell.name
                )
            }
        ),
    ),
    requirements = { true },
    description = "Выберите еще одну опцию метамагии:"
)

var sorcerer17: AbilityNodeLevel = AbilityNodeLevel(
    name = "Чародей_17",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities.proficiencyBonus += 1
        abilities.spellsInfo["Заклинания класса"]?.let {
            it.maxKnownSpellsCount += 1
        }
        if (!abilities.currentState.charges.contains("Ячейки_9")) {
            abilities.currentState.charges["Ячейки_9"] = ChargesCounter(
                current = 1,
                maximum = 1
            )
        }
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(metamagic3.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "17-й уровень, способности чародея",
    next_level = "Чародей_18",
)

var sorcerer18: AbilityNodeLevel = AbilityNodeLevel(
    name = "Чародей_18",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities.spellsInfo["Заклинания класса"]?.let {
            it.maxKnownSpellsCount += 1
        }
        abilities.currentState.charges["Ячейки_5"]?.let {
            if (it.maximum < 3)
                it.maximum = 3
        }
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "18-й уровень, способности чародея",
    next_level = "Чародей_19",
)

var sorcerer19: AbilityNodeLevel = AbilityNodeLevel(
    name = "Чародей_19",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities.spellsInfo["Заклинания класса"]?.let {
            it.maxKnownSpellsCount += 1
        }
        abilities.currentState.charges["Ячейки_6"]?.let {
            if (it.maximum < 2)
                it.maximum = 2
        }
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "19-й уровень, способности чародея",
    next_level = "Чародей_20",
)

var sorcerer20: AbilityNodeLevel = AbilityNodeLevel(
    name = "Чародей_20",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities.spellsInfo["Заклинания класса"]?.let {
            it.maxKnownSpellsCount += 1
        }
        abilities.currentState.charges["Ячейки_7"]?.let {
            if (it.maximum < 2)
                it.maximum = 2
        }
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "20-й уровень, способности чародея",
    next_level = null,
)

var mapOfSorcererAbilities = mutableMapOf(
    Pair(classFeaturesSorcerer.name, classFeaturesSorcerer),
    Pair(spellCastingSorcerer.name, spellCastingSorcerer),
    Pair(sorcerousOrigin.name, sorcerousOrigin),
    Pair(sorcerer1.name, sorcerer1),
    Pair(fontOfMagic.name, fontOfMagic),
    Pair(sorcerer2.name, sorcerer2),
    Pair(metamagic.name, metamagic),
    Pair(sorcerer3.name, sorcerer3),
    Pair(sorcerer4.name, sorcerer4),
    Pair(sorcerer5.name, sorcerer5),
    Pair(sorcerer6.name, sorcerer6),
    Pair(sorcerer7.name, sorcerer7),
    Pair(sorcerer8.name, sorcerer8),
    Pair(sorcerer9.name, sorcerer9),
    Pair(metamagic2.name, metamagic2),
    Pair(sorcerer10.name, sorcerer10),
    Pair(sorcerer11.name, sorcerer11),
    Pair(sorcerer12.name, sorcerer12),
    Pair(sorcerer13.name, sorcerer13),
    Pair(sorcerer14.name, sorcerer14),
    Pair(sorcerer15.name, sorcerer15),
    Pair(sorcerer16.name, sorcerer16),
    Pair(metamagic3.name, metamagic3),
    Pair(sorcerer17.name, sorcerer17),
    Pair(sorcerer18.name, sorcerer18),
    Pair(sorcerer19.name, sorcerer19),
    Pair(sorcerer20.name, sorcerer20)
) + mapOfMetamagicOptions + draconicBloodlineMap + wildMagicMap