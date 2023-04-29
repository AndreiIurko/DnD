package com.andreyyurko.dnd.data.abilities.classes.cleric

//import android.util.Log
//import com.andreyyurko.dnd.data.abilities.other.*
//import com.andreyyurko.dnd.data.characterData.Priority
import com.andreyyurko.dnd.data.abilities.classes.AbilityNodeLevel
import com.andreyyurko.dnd.data.abilities.other.*
import com.andreyyurko.dnd.data.characterData.*
import com.andreyyurko.dnd.data.characterData.character.AbilityNode
import com.andreyyurko.dnd.data.characterData.character.abilityToModifier
import com.andreyyurko.dnd.data.spells.CharacterSpells
import kotlin.math.max

var classFeaturesCleric: AbilityNode = AbilityNode(
    name = "Жрец: классовые умения",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.savingThrowProf.add(Ability.Wisdom)
        abilities.savingThrowProf.add(Ability.Charisma)
        abilities.armorProficiency.add(ArmorProf.Shield)
        abilities.armorProficiency.add(ArmorProf.LightArmor)
        abilities.armorProficiency.add(ArmorProf.MediumArmor)
        addAllSimpleWeapons(abilities)
        abilities
    },
    alternatives = mutableMapOf(
        Pair(
            "skill1",
            listOf(history.name, medicine.name, insight.name, religion.name, persuasion.name)
        ),
        Pair(
            "skill2",
            listOf(history.name, medicine.name, insight.name, religion.name, persuasion.name)
        ),
    ),
    requirements = { abilities: CharacterInfo ->
        abilities.characterClass == Classes.Cleric
    },
    description = "ХИТЫ\n" +
            "\n" +
            "Кость Хитов: 1к8 за каждый уровень жреца\n" +
            "\n" +
            "Хиты на 1 уровне: 8 + модификатор Телосложения\n" +
            "\n" +
            "Хиты на следующих уровнях: 1к8 (или 6) + модификатор Телосложения (суммарно минимум 1) за каждый уровень жреца после первого\n" +
            "\n" +
            "ВЛАДЕНИЕ\n" +
            "\n" +
            "Доспехи: лёгкие доспехи, средние доспехи, щиты\n" +
            "\n" +
            "Оружие: простое оружие\n" +
            "\n" +
            "Инструменты: нет\n" +
            "\n" +
            "Спасброски: Мудрость, Харизма\n" +
            "\n" +
            "Навыки: Выберите два навыка из следующих: История, Медицина, Проницательность, Религия, Убеждение.",
    priority = Priority.DoFirst
)

var spellCastingCleric: AbilityNode = AbilityNode(
    name = "Жрец: использование заклинаний",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.spellsInfo.apply {
            if (!this.contains("Заклинания класса")) {
                this["Заклинания класса"] = CharacterSpells()
            }
            this["Заклинания класса"]?.className = Classes.Cleric.className
            this["Заклинания класса"]?.maxPreparedSpellsCount =
                max((abilityToModifier(abilities.wisdom) + abilities.level), 1)
            this["Заклинания класса"]?.maxKnownCantripsCount = 3
            this["Заклинания класса"]?.maxKnownCantripsCount = 3
            if ((abilities.level > 3) and (abilities.level < 10))
                this["Заклинания класса"]?.maxKnownCantripsCount = 4
            if (abilities.level > 9)
                this["Заклинания класса"]?.maxKnownCantripsCount = 5
        }
        abilities.additionalAbilities["Ритуальное колдовство"] = "Вы можете сотворить любое известное вам заклинание жреца в качестве ритуала, если заклинание позволяет это.\n"
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.characterClass == Classes.Cleric
    },
    description = "Будучи проводником божественной силы, вы можете накладывать заклинания жреца\n" +
            "\n" +
            "Заговоры (заклинания 0-го уровня)" +
            "\n" +
            "На 1-м уровне вы знаете три заговора на свой выбор из списка заклинаний жреца. Вы узнаёте дополнительные заговоры жреца на более высоких уровнях, как показано в колонке «Известные заговоры».\n" +
            "\n" +
            "Подготовка и сотворение заклинаний\n" +
            "\n" +
            "Таблица «Жрец» показывает, какое количество ячеек для накладывания заклинаний жреца у вас есть на первом и более высоких уровнях. Для накладывания одного из этих заклинаний вы должны потратить ячейку заклинаний того же уровня или выше, что и само заклинание. Вы восстановите все потраченные ячейки, когда закончите продолжительный отдых.\n" +
            "\n" +
            "Вы подготавливаете список заклинаний жреца, доступных для накладывания. При этом вы выбираете количество заклинаний жреца, равное модификатору Мудрости + уровень жреца (минимум одно заклинание). Уровень заклинаний не должен превышать уровень самой высокой имеющейся у вас ячейки заклинаний.\n" +
            "\n" +
            "Например, если вы жрец 3-го уровня, то у вас есть четыре ячейки заклинаний 1-го уровня и две ячейки 2-го уровня. При Мудрости 16 ваш список подготовленных заклинаний может включать в себя шесть заклинаний 1-го или 2-го уровня в любой комбинации. Если вы подготовили заклинание 1-го уровня лечение ран [cure wounds], вы можете наложить его, используя ячейку 1-го уровня или ячейку 2-го уровня. Накладывание заклинания не удаляет его из списка подготовленных заклинаний.\n" +
            "\n" +
            "Вы можете изменить список подготовленных заклинаний, когда заканчиваете продолжительный отдых. Подготовка нового списка заклинаний жреца требует времени, проведённого в молитвах и медитации: не менее 1 минуты за уровень заклинания для каждого заклинания в вашем списке.\n" +
            "\n" +
            "Базовая характеристика заклинаний\n" +
            "\n" +
            "При накладывании заклинаний жрец использует Мудрость. Сила ваших заклинаний исходит от вашей преданности своему божеству. Вы используете Мудрость в случаях, когда заклинание ссылается на базовую характеристику. Кроме того, вы используете модификатор Мудрости при определении Сл спасбросков от ваших заклинаний жреца, и при броске атаки заклинаниями.\n" +
            "\n" +
            "Сл спасброска = 8 + ваш бонус мастерства + ваш модификатор Мудрости\n" +
            "\n" +
            "Модификатор броска атаки = ваш бонус мастерства + ваш модификатор Мудрости\n" +
            "\n" +
            "Ритуальное колдовство\n" +
            "\n" +
            "Вы можете сотворить заклинание жреца как ритуал, если у этого заклинания есть ключевое слово «ритуал», и оно подготовлено вами.\n" +
            "\n" +
            "Фокусировка заклинания\n" +
            "\n" +
            "Вы можете использовать священный символ в качестве заклинательной фокусировки для заклинаний жреца.\n"
)

var divineDomain: AbilityNode = AbilityNode(
    name = "Божественный домен",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    alternatives = mutableMapOf(
        Pair("domain", listOf(tempestDomain.name, KnowledgeDomain.name))
    ),
    requirements = { abilities: CharacterInfo ->
        abilities.characterClass == Classes.Cleric
    },
    description = "Выберите один домен, связанный с вашим божеством. Все домены детально рассмотрены в конце описания класса, и каждый содержит примеры божеств, связанных с ним. Ваш выбор даёт вам заклинания домена и другие умения. Он также даёт вам дополнительные способы использования «Божественного канала», когда вы получаете это умение на 2-м уровне, и дополнительные умения на 6-м, 8-м и 17-м уровнях.\n" +
            "\n" +
            "ЗАКЛИНАНИЯ ДОМЕНА\n" +
            "\n" +
            "У каждого домена есть список заклинаний, которые вы получаете на новых уровнях жреца. Как только вы получаете заклинание домена, оно всегда считается подготовленным и не учитывается при подсчёте количества заклинаний, которые можно подготовить.\n" +
            "\n" +
            "Если вы получаете доступ к заклинанию, отсутствующему в списке заклинаний жреца, оно всё равно будет считаться для вас заклинанием жреца.\n"
)

var cleric1: AbilityNode = AbilityNodeLevel(
    name = "Жрец_1",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.characterClass = Classes.Cleric
        abilities.level += 1
        abilities.proficiencyBonus += 2
        abilities.hp += abilityToModifier(abilities.constitution) + 8
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(classFeaturesCleric.name)),
        Pair("second", listOf(spellCastingCleric.name)),
        Pair("third", listOf(divineDomain.name)),
    ),
    requirements = { true },
    add_requirements = listOf(),
    description = "1-й уровень, способности жреца",
    next_level = "Жрец_2"
)

var channelDivinity: AbilityNode = AbilityNode(
    name = "Божественный канал",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        if (!abilities.currentState.charges.contains("Божественный канал")) {
            abilities.currentState.charges["Божественный канал"] = ChargesCounter(
                current = 1,
                maximum = 1
            )
        }
        abilities.currentState.charges["Божественный канал"]?.let {
            if (it.maximum < (abilities.level + 18) / 12) {
                abilities.currentState.charges["Божественный канал"] = ChargesCounter(
                    current = (abilities.level + 18) / 12,
                    maximum = (abilities.level + 18) / 12
                )
            }
        }
        abilities.actionsList.add(
            Action(
                name = "Изгнание нежити",
                description = "Вы действием демонстрируете свой священный символ и читаете молитву, изгоняющую Нежить. " +
                        "Вся Нежить, которая может видеть или слышать вас в пределах 30 футов, должна совершить спасбросок Мудрости." +
                        "Если существо провалило спасбросок, оно изгоняется на 1 минуту, или пока не получит урон. " +
                        "Изгнанное существо должно тратить свои ходы, пытаясь уйти от вас как можно дальше, и не может добровольно переместиться в пространство, находящееся в пределах 30 футов от вас. " +
                        "Оно также не может совершать реакции. Действием существо может использовать только Рывок или пытаться освободиться от эффекта, препятствующего его передвижению. " +
                        "Если двигаться некуда, существо может использовать действие Уклонение.",
                type = ActionType.Action,
                relatedCharges = "Божественный канал"
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    add_requirements = listOf(),
    description = "Вы получаете возможность направлять божественную энергию непосредственно от своего божества, используя её для подпитки магических эффектов. Вы начинаете с двумя такими эффектами: «Изгнание Нежити» и эффектом, определяемым вашим доменом. Некоторые домены дают вам дополнительные эффекты, как только вы получите новые уровни.\n" +
            "\n" +
            "Когда вы используете «Божественный канал», вы выбираете, какой эффект создать. Затем вы должны окончить короткий или продолжительный отдых, чтобы использовать «Божественный канал» снова. Некоторые эффекты «Божественного канала» требуют совершить спасбросок. Когда вы используете эффекты такого рода, Сл равна Сл спасброска от ваших заклинаний жреца.\n" +
            "\n" +
            "Начиная с 6-го уровня вы можете использовать «Божественный канал» дважды перед отдыхом, а начиная с 18-го уровня вы можете использовать его три раза перед отдыхом: по окончании короткого или продолжительного отдыха вы восстановите все потраченные использования.",
)

var cleric2: AbilityNodeLevel = AbilityNodeLevel(
    name = "Жрец_2",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(channelDivinity.name)),
        //Pair("second", listOf(domain.name))
    ),
    requirements = { true },
    add_requirements = listOf(),
    description = "2-й уровень, способности жреца",
    next_level = "Жрец_3",
)

var cleric3: AbilityNodeLevel = AbilityNodeLevel(
    name = "Жрец_3",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    add_requirements = listOf(),
    description = "3-й уровень, способности жреца",
    next_level = "Жрец_4",
)

var cleric4: AbilityNodeLevel = AbilityNodeLevel(
    name = "Жрец_4",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities.spellsInfo["Заклинания класса"]?.let {
            it.maxKnownCantripsCount += 1
        }
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(abilityScoreImprovement.name)),
    ),
    requirements = { true },
    add_requirements = listOf(),
    description = "4-й уровень, способности жреца",
    next_level = "Жрец_5",
)

var destroy_undead: AbilityNode = AbilityNode(
    name = "Уничтожение нежити",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        for (i in 0..abilities.actionsList.size - 1) {
            if (abilities.actionsList[i].name == "Изгнание нежити") {
                abilities.actionsList[i].description = abilities.actionsList[i].description +
                        "\n" +
                        "Когда Нежить проваливает спасбросок от вашего умения «Изгнание Нежити», существо мгновенно уничтожается, если его показатель опасности не превышает значения, указанного в таблице:\n" +
                        "\n" +
                        "Уровень жреца | Уничтожается нежить с ПО\n" +
                        "       5      | 1/2 и ниже\n" +
                        "       8      | 1 и ниже\n" +
                        "      11      | 2 и ниже\n" +
                        "      14      | 3 и ниже\n" +
                        "      17      | 4 и ниже\n"
            }
        }
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    add_requirements = listOf(),
    description = "Когда Нежить проваливает спасбросок от вашего умения «Изгнание Нежити», существо мгновенно уничтожается, если его показатель опасности не превышает значения, указанного в таблице:\n" +
            "\n" +
            "Уровень жреца | Уничтожается нежить с ПО\n" +
            "       5      | 1/2 и ниже\n" +
            "       8      | 1 и ниже\n" +
            "      11      | 2 и ниже\n" +
            "      14      | 3 и ниже\n" +
            "      17      | 4 и ниже\n",
)


var cleric5: AbilityNodeLevel = AbilityNodeLevel(
    name = "Жрец_5",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(destroy_undead.name)),
    ),
    requirements = { true },
    add_requirements = listOf(),
    description = "5-й уровень, способности жреца",
    next_level = "Жрец_6",
)

var cleric6: AbilityNodeLevel = AbilityNodeLevel(
    name = "Жрец_6",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    add_requirements = listOf(),
    description = "6-й уровень, способности жреца",
    next_level = "Жрец_7",
)

var cleric7: AbilityNodeLevel = AbilityNodeLevel(
    name = "Жрец_7",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    add_requirements = listOf(),
    description = "7-й уровень, способности жреца",
    next_level = "Жрец_8",
)

var cleric8: AbilityNodeLevel = AbilityNodeLevel(
    name = "Жрец_8",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(abilityScoreImprovement.name)),
    ),
    requirements = { true },
    add_requirements = listOf(),
    description = "8-й уровень, способности жреца",
    next_level = "Жрец_9",
)

var cleric9: AbilityNodeLevel = AbilityNodeLevel(
    name = "Жрец_9",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    add_requirements = listOf(),
    description = "9-й уровень, способности жреца",
    next_level = "Жрец_10",
)

var divineIntervention: AbilityNode = AbilityNode(
    name = "Божественное вмешательство",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        if (!abilities.currentState.charges.contains("Божественное вмешательство")) {
            abilities.currentState.charges["Божественное вмешательство"] = ChargesCounter(
                current = 1,
                maximum = 1
            )
        }
        abilities.actionsList.add(
            Action(
                name = "Божественное вмешательство",
                description = "Вы можете воззвать к своему божеству о помощи, когда сильно в этом нуждаетесь. Мольба о помощи совершается действием. Опишите помощь, которую вы ждёте, и киньте процентную кость. Если выпадет число, не превышающее ваш уровень жреца, ваше божество вмешается. Мастер сам выбирает природу вмешательства; подойдёт эффект какого-либо заклинания жреца или заклинания домена.\n" +
                        "\n" +
                        "Если божество вмешивается, вы не можете использовать это умение в течение 7 дней. В противном случае вы можете использовать это умение после продолжительного отдыха. На 20-м уровне ваше воззвание автоматически успешно и не требует проверки.\n",
                type = ActionType.Action,
                relatedCharges = "Божественное вмешательство"
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    add_requirements = listOf(),
    description = "Вы можете воззвать к своему божеству о помощи, когда сильно в этом нуждаетесь. Мольба о помощи совершается действием. Опишите помощь, которую вы ждёте, и киньте процентную кость. Если выпадет число, не превышающее ваш уровень жреца, ваше божество вмешается. Мастер сам выбирает природу вмешательства; подойдёт эффект какого-либо заклинания жреца или заклинания домена.\n" +
            "\n" +
            "Если божество вмешивается, вы не можете использовать это умение в течение 7 дней. В противном случае вы можете использовать это умение после продолжительного отдыха. На 20-м уровне ваше воззвание автоматически успешно и не требует проверки.\n",
)

var cleric10: AbilityNodeLevel = AbilityNodeLevel(
    name = "Жрец_10",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(divineIntervention.name)),
    ),
    requirements = { true },
    add_requirements = listOf(),
    description = "10-й уровень, способности жреца",
    next_level = "Жрец_11",
)

var cleric11: AbilityNodeLevel = AbilityNodeLevel(
    name = "Жрец_11",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    add_requirements = listOf(),
    description = "11-й уровень, способности жреца",
    next_level = "Жрец_12",
)

var cleric12: AbilityNodeLevel = AbilityNodeLevel(
    name = "Жрец_12",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(abilityScoreImprovement.name)),
    ),
    requirements = { true },
    add_requirements = listOf(),
    description = "12-й уровень, способности жреца",
    next_level = "Жрец_13",
)

var cleric13: AbilityNodeLevel = AbilityNodeLevel(
    name = "Жрец_13",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    add_requirements = listOf(),
    description = "13-й уровень, способности жреца",
    next_level = "Жрец_14",
)

var cleric14: AbilityNodeLevel = AbilityNodeLevel(
    name = "Жрец_14",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    add_requirements = listOf(),
    description = "14-й уровень, способности жреца",
    next_level = "Жрец_15",
)

var cleric15: AbilityNodeLevel = AbilityNodeLevel(
    name = "Жрец_15",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    add_requirements = listOf(),
    description = "15-й уровень, способности жреца",
    next_level = "Жрец_16",
)

var cleric16: AbilityNodeLevel = AbilityNodeLevel(
    name = "Жрец_16",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(abilityScoreImprovement.name)),
    ),
    requirements = { true },
    add_requirements = listOf(),
    description = "16-й уровень, способности жреца",
    next_level = "Жрец_17",
)

var cleric17: AbilityNodeLevel = AbilityNodeLevel(
    name = "Жрец_17",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    add_requirements = listOf(),
    description = "17-й уровень, способности жреца",
    next_level = "Жрец_18",
)

var cleric18: AbilityNodeLevel = AbilityNodeLevel(
    name = "Жрец_18",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    add_requirements = listOf(),
    description = "18-й уровень, способности жреца",
    next_level = "Жрец_19",
)

var cleric19: AbilityNodeLevel = AbilityNodeLevel(
    name = "Жрец_19",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(abilityScoreImprovement.name)),
    ),
    requirements = { true },
    add_requirements = listOf(),
    description = "19-й уровень, способности жреца",
    next_level = "Жрец_20",
)

var cleric20: AbilityNodeLevel = AbilityNodeLevel(
    name = "Жрец_20",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    add_requirements = listOf(),
    description = "20-й уровень, способности жреца",
    next_level = null,
)

var mapOfClericAbilities: MutableMap<String, AbilityNode> = (mutableMapOf(
    Pair(classFeaturesCleric.name, classFeaturesCleric),
    Pair(spellCastingCleric.name, spellCastingCleric),
    Pair(divineDomain.name, divineDomain),
    Pair(cleric1.name, cleric1),
    Pair(channelDivinity.name, channelDivinity),
    Pair(cleric2.name, cleric2),
    Pair(cleric3.name, cleric3),
    Pair(cleric4.name, cleric4),
    Pair(destroy_undead.name, destroy_undead),
    Pair(cleric5.name, cleric5),
    Pair(cleric6.name, cleric6),
    Pair(cleric7.name, cleric7),
    Pair(cleric8.name, cleric8),
    Pair(cleric9.name, cleric9),
    Pair(divineIntervention.name, divineIntervention),
    Pair(cleric10.name, cleric10),
    Pair(cleric11.name, cleric11),
    Pair(cleric12.name, cleric12),
    Pair(cleric13.name, cleric13),
    Pair(cleric14.name, cleric14),
    Pair(cleric15.name, cleric15),
    Pair(cleric16.name, cleric16),
    Pair(cleric17.name, cleric17),
    Pair(cleric18.name, cleric18),
    Pair(cleric19.name, cleric19),
    Pair(cleric20.name, cleric20),
)
        + mapOfTempestDomainAbilities
        + mapOfKnowledgeDomainAbilities).toMutableMap()






