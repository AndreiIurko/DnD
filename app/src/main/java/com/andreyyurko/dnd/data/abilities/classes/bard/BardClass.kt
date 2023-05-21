package com.andreyyurko.dnd.data.abilities.classes.bard

import com.andreyyurko.dnd.data.abilities.classes.AbilityNodeLevel
import com.andreyyurko.dnd.data.abilities.other.abilityScoreImprovement
import com.andreyyurko.dnd.data.abilities.other.acrobatics
import com.andreyyurko.dnd.data.abilities.other.acrobaticsExpertise
import com.andreyyurko.dnd.data.abilities.other.animalHandling
import com.andreyyurko.dnd.data.abilities.other.animalHandlingExpertise
import com.andreyyurko.dnd.data.abilities.other.arcana
import com.andreyyurko.dnd.data.abilities.other.arcanaExpertise
import com.andreyyurko.dnd.data.abilities.other.athletics
import com.andreyyurko.dnd.data.abilities.other.athleticsExpertise
import com.andreyyurko.dnd.data.abilities.other.bagpipes
import com.andreyyurko.dnd.data.abilities.other.deception
import com.andreyyurko.dnd.data.abilities.other.deceptionExpertise
import com.andreyyurko.dnd.data.abilities.other.drum
import com.andreyyurko.dnd.data.abilities.other.dulcimer
import com.andreyyurko.dnd.data.abilities.other.flute
import com.andreyyurko.dnd.data.abilities.other.history
import com.andreyyurko.dnd.data.abilities.other.historyExpertise
import com.andreyyurko.dnd.data.abilities.other.horn
import com.andreyyurko.dnd.data.abilities.other.insight
import com.andreyyurko.dnd.data.abilities.other.insightExpertise
import com.andreyyurko.dnd.data.abilities.other.intimidation
import com.andreyyurko.dnd.data.abilities.other.intimidationExpertise
import com.andreyyurko.dnd.data.abilities.other.investigation
import com.andreyyurko.dnd.data.abilities.other.investigationExpertise
import com.andreyyurko.dnd.data.abilities.other.lute
import com.andreyyurko.dnd.data.abilities.other.lyre
import com.andreyyurko.dnd.data.abilities.other.medicine
import com.andreyyurko.dnd.data.abilities.other.medicineExpertise
import com.andreyyurko.dnd.data.abilities.other.nature
import com.andreyyurko.dnd.data.abilities.other.natureExpertise
import com.andreyyurko.dnd.data.abilities.other.panFlute
import com.andreyyurko.dnd.data.abilities.other.perception
import com.andreyyurko.dnd.data.abilities.other.perceptionExpertise
import com.andreyyurko.dnd.data.abilities.other.performance
import com.andreyyurko.dnd.data.abilities.other.performanceExpertise
import com.andreyyurko.dnd.data.abilities.other.persuasion
import com.andreyyurko.dnd.data.abilities.other.persuasionExpertise
import com.andreyyurko.dnd.data.abilities.other.religion
import com.andreyyurko.dnd.data.abilities.other.religionExpertise
import com.andreyyurko.dnd.data.abilities.other.shawm
import com.andreyyurko.dnd.data.abilities.other.sleightOfHand
import com.andreyyurko.dnd.data.abilities.other.sleightOfHandExpertise
import com.andreyyurko.dnd.data.abilities.other.stealth
import com.andreyyurko.dnd.data.abilities.other.stealthExpertise
import com.andreyyurko.dnd.data.abilities.other.survival
import com.andreyyurko.dnd.data.abilities.other.survivalExpertise
import com.andreyyurko.dnd.data.abilities.other.viol
import com.andreyyurko.dnd.data.characterData.Ability
import com.andreyyurko.dnd.data.characterData.Action
import com.andreyyurko.dnd.data.characterData.ActionType
import com.andreyyurko.dnd.data.characterData.ArmorProf
import com.andreyyurko.dnd.data.characterData.character.CharacterInfo
import com.andreyyurko.dnd.data.characterData.ChargesCounter
import com.andreyyurko.dnd.data.characterData.Classes
import com.andreyyurko.dnd.data.characterData.Priority
import com.andreyyurko.dnd.data.characterData.Weapon
import com.andreyyurko.dnd.data.characterData.addAllSimpleWeapons
import com.andreyyurko.dnd.data.characterData.character.AbilityNode
import com.andreyyurko.dnd.data.characterData.character.abilityToModifier
import com.andreyyurko.dnd.data.spells.CharacterSpells
import com.andreyyurko.dnd.data.spells.SpellLists
import com.andreyyurko.dnd.data.spells.spellist
import kotlin.math.max

var classFeaturesBard: AbilityNode = AbilityNode(
    name = "бард: классовые умения",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.savingThrowProf.add(Ability.Dexterity)
        abilities.savingThrowProf.add(Ability.Charisma)
        addAllSimpleWeapons(abilities)
        abilities.weaponProficiency.add(Weapon.Longsword)
        abilities.weaponProficiency.add(Weapon.ShortSword)
        abilities.weaponProficiency.add(Weapon.Rapier)
        abilities.weaponProficiency.add(Weapon.CrossbowHand)
        abilities.armorProficiency.add(ArmorProf.LightArmor)
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair(
            "musicalInstrument1",
            {
                listOf(
                    bagpipes.name,
                    drum.name,
                    dulcimer.name,
                    flute.name,
                    lute.name,
                    lyre.name,
                    horn.name,
                    panFlute.name,
                    shawm.name,
                    viol.name,
                )
            }
        ),
        Pair(
            "musicalInstrument2",
            {
                listOf(
                    bagpipes.name,
                    drum.name,
                    dulcimer.name,
                    flute.name,
                    lute.name,
                    lyre.name,
                    horn.name,
                    panFlute.name,
                    shawm.name,
                    viol.name,
                )
            }
        ),
        Pair(
            "musicalInstrument3",
            {
                listOf(
                    bagpipes.name,
                    drum.name,
                    dulcimer.name,
                    flute.name,
                    lute.name,
                    lyre.name,
                    horn.name,
                    panFlute.name,
                    shawm.name,
                    viol.name,
                )
            }
        ),
        Pair(
            "skill1",
            {
                listOf(
                    acrobatics.name,
                    animalHandling.name,
                    arcana.name,
                    athletics.name,
                    deception.name,
                    history.name,
                    insight.name,
                    intimidation.name,
                    investigation.name,
                    medicine.name,
                    nature.name,
                    perception.name,
                    performance.name,
                    persuasion.name,
                    religion.name,
                    sleightOfHand.name,
                    stealth.name,
                    survival.name
                )
            }
        ),
        Pair(
            "skill2",
            {
                listOf(
                    acrobatics.name,
                    animalHandling.name,
                    arcana.name,
                    athletics.name,
                    deception.name,
                    history.name,
                    insight.name,
                    intimidation.name,
                    investigation.name,
                    medicine.name,
                    nature.name,
                    perception.name,
                    performance.name,
                    persuasion.name,
                    religion.name,
                    sleightOfHand.name,
                    stealth.name,
                    survival.name
                )
            }
        ),
        Pair(
            "skill3",
            {
                listOf(
                    acrobatics.name,
                    animalHandling.name,
                    arcana.name,
                    athletics.name,
                    deception.name,
                    history.name,
                    insight.name,
                    intimidation.name,
                    investigation.name,
                    medicine.name,
                    nature.name,
                    perception.name,
                    performance.name,
                    persuasion.name,
                    religion.name,
                    sleightOfHand.name,
                    stealth.name,
                    survival.name
                )
            }
        ),
    ),
    requirements = { abilities: CharacterInfo ->
        abilities.characterClass == Classes.Bard
    },
    description = "ХИТЫ\n" +
            "\n" +
            "Кость Хитов: 1к8 за каждый уровень барда\n" +
            "\n" +
            "Хиты на 1 уровне: 8 + модификатор Телосложения\n" +
            "\n" +
            "Хиты на следующих уровнях: 1к8 (или 5) + модификатор Телосложения (суммарно минимум 1) за каждый уровень барда после первого\n" +
            "\n" +
            "ВЛАДЕНИЕ\n" +
            "\n" +
            "Доспехи: Лёгкие доспехи\n" +
            "\n" +
            "Оружие: Простое оружие, длинные мечи, короткие мечи, рапиры, ручные арбалеты\n" +
            "\n" +
            "Инструменты: Три музыкальных инструмента на ваш выбор\n" +
            "\n" +
            "Спасброски: Ловкость, Харизма\n" +
            "\n" +
            "Навыки: Выберите три любых",
    priority = Priority.DoFirst
)

var spellCastingBard: AbilityNode = AbilityNode(
    name = "бард: использование заклинаний",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.spellsInfo.apply {
            if (!this.contains("Заклинания класса")) {
                this["Заклинания класса"] = CharacterSpells()
            }
            this["Заклинания класса"]?.className = Classes.Bard.className
            this["Заклинания класса"]?.maxKnownSpellsCount = abilities.level + 3
            if ((abilities.level > 9) and (abilities.level < 12))
                this["Заклинания класса"]?.maxKnownSpellsCount = abilities.level + 2
            if ((abilities.level > 11) and (abilities.level < 17))
                this["Заклинания класса"]?.maxKnownSpellsCount = abilities.level + 3
            if (abilities.level > 16)
                this["Заклинания класса"]?.maxKnownSpellsCount = 20
            this["Заклинания класса"]?.maxKnownCantripsCount = 2
            if ((abilities.level > 3) and (abilities.level < 10))
                this["Заклинания класса"]?.maxKnownCantripsCount = 3
            if (abilities.level > 9)
                this["Заклинания класса"]?.maxKnownCantripsCount = 4
        }
        abilities.additionalAbilities["Ритуальное колдовство"] =
            "Вы можете сотворить любое известное вам заклинание барда в качестве ритуала, если заклинание позволяет это.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.characterClass == Classes.Bard
    },
    description = "Вы научились изменять ткань реальности в соответствии с вашими волей и музыкой. Ваши заклинания являются частью вашего обширного репертуара; это магия, которой вы найдёте применение в любой ситуации. Вы найдёте список заклинаний, доступных барду в этом разделе: заклинания барда.\n" +
            "Заговоры (заклинания 0-го уровня)\n" +
            "\n" +
            "Вы знаете два заговора из списка доступных для барда на ваш выбор. При достижении более высоких уровней вы выучите новые, в соответствии с колонкой «известные заговоры».\n" +
            "Ячейки заклинаний\n" +
            "\n" +
            "Таблица «Бард» показывает, сколько ячеек заклинаний у вас есть для заклинаний барда 1-го и других уровней. Для накладывания заклинания вы должны потратить ячейку соответствующего либо превышающего уровня. Вы восстанавливаете все потраченные ячейки в конце продолжительного отдыха. Например, если вы знаете заклинание 1-го уровня лечение ран [cure wounds], и у вас есть ячейки 1-го и 2-го уровней, вы можете наложить его с помощью любой из этих ячеек.\n" +
            "Известные заклинания первого и более высоких уровней\n" +
            "\n" +
            "Вы знаете четыре заклинания 1-го уровня на свой выбор из списка доступных барду.\n" +
            "\n" +
            "Колонка «известные заклинания» показывает, когда вы сможете выучить новые заклинания. Уровень заклинаний не должен превышать уровень самой высокой имеющейся у вас ячейки заклинаний. Например, когда вы достигнете 3-го уровня в этом классе, вы можете выучить одно новое заклинание 1-го или 2-го уровня.\n" +
            "\n" +
            "Кроме того, когда вы получаете новый уровень в этом классе, вы можете одно из известных вам заклинаний барда заменить на другое из списка барда, уровень которого тоже должен соответствовать имеющимся ячейкам заклинаний.\n" +
            "Базовая характеристика заклинаний\n" +
            "\n" +
            "При накладывании заклинаний бард использует свою Харизму. Ваша магия проистекает из сердечности и душевности, которую вы вкладываете в исполнение музыки и произнесение речей. Вы используете Харизму в случаях, когда заклинание ссылается на базовую характеристику. Кроме того, вы используете Харизму при определении Сл спасбросков от ваших заклинаний, и при броске атаки заклинаниями.\n" +
            "\n" +
            "Сл спасброска = 8 + ваш бонус мастерства + ваш модификатор Харизмы\n" +
            "\n" +
            "Модификатор броска атаки = ваш бонус мастерства + ваш модификатор Харизмы\n" +
            "Ритуальное колдовство\n" +
            "\n" +
            "Вы можете сотворить любое известное вам заклинание барда в качестве ритуала, если заклинание позволяет это.\n" +
            "Фокусировка заклинания\n" +
            "\n" +
            "Вы можете использовать ваш музыкальный инструмент в качестве фокусировки для ваших заклинаний барда.\n"
)

var bardicInspiration: AbilityNode = AbilityNode(
    name = "Вдохновение барда",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        if (!abilities.currentState.charges.contains("Бардовское вдохновение")) {
            abilities.currentState.charges["Бардовское вдохновение"] = ChargesCounter(
                current = max(1, abilityToModifier(abilities.charisma)),
                maximum = max(1, abilityToModifier(abilities.charisma))
            )
        }
        abilities.currentState.charges["Бардовское вдохновение"]?.let {
            if (it.maximum < max(1, abilityToModifier(abilities.charisma))) {
                abilities.currentState.charges["Бардовское вдохновение"] = ChargesCounter(
                    current = max(1, abilityToModifier(abilities.charisma)),
                    maximum = max(1, abilityToModifier(abilities.charisma))
                )
            }
        }
        abilities.actionsMap["Вдохновение барда"] =
            Action(
                name = "Бардовское вдохновение",
                description = "Своими словами или музыкой вы можете вдохновлять других. Для этого вы должны бонусным действием выбрать одно существо, отличное от вас, в пределах 60 футов, которое может вас слышать. Это существо получает кость бардовского вдохновения — к6.\n" +
                        "\n" +
                        "В течение следующих 10 минут это существо может один раз бросить эту кость и добавить результат к проверке характеристики, броску атаки или спасброску, который оно совершает. Существо может принять решение о броске кости вдохновения уже после броска к20, но должно сделать это прежде, чем Мастер объявит результат броска. Как только кость бардовского вдохновения брошена, она исчезает. Существо может иметь только одну такую кость одновременно.\n" +
                        "\n" +
                        "Вы можете использовать это умение количество раз, равное модификатору вашей Харизмы, но как минимум один раз. Потраченные использования этого умения восстанавливаются после продолжительного отдыха.\n" +
                        "\n" +
                        "Ваша кость бардовского вдохновения изменяется с ростом вашего уровня в этом классе. Она становится к8 на 5-м уровне, к10 на 10-м уровне и к12 на 15-м уровне.\n",
                type = ActionType.Bonus,
                relatedCharges = "Бардовское вдохновение"

            )
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "Своими словами или музыкой вы можете вдохновлять других. Для этого вы должны бонусным действием выбрать одно существо, отличное от вас, в пределах 60 футов, которое может вас слышать. Это существо получает кость бардовского вдохновения — к6.\n" +
            "\n" +
            "В течение следующих 10 минут это существо может один раз бросить эту кость и добавить результат к проверке характеристики, броску атаки или спасброску, который оно совершает. Существо может принять решение о броске кости вдохновения уже после броска к20, но должно сделать это прежде, чем Мастер объявит результат броска. Как только кость бардовского вдохновения брошена, она исчезает. Существо может иметь только одну такую кость одновременно.\n" +
            "\n" +
            "Вы можете использовать это умение количество раз, равное модификатору вашей Харизмы, но как минимум один раз. Потраченные использования этого умения восстанавливаются после продолжительного отдыха.\n" +
            "\n" +
            "Ваша кость бардовского вдохновения изменяется с ростом вашего уровня в этом классе. Она становится к8 на 5-м уровне, к10 на 10-м уровне и к12 на 15-м уровне.\n"
)

var bard1: AbilityNode = AbilityNodeLevel(
    name = "Бард_1",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.characterClass = Classes.Bard
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.spellsLevel += 1
        abilities.proficiencyBonus += 2
        abilities.hp += abilityToModifier(abilities.constitution) + 8
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(classFeaturesBard.name) }),
        Pair("second", { listOf(spellCastingBard.name) }),
        Pair("third", { listOf(bardicInspiration.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "1-й уровень, способности барда",
    next_level = "Бард_2"
)

var jackOfAllTrades: AbilityNode = AbilityNode(
    name = "Мастер на все руки",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        for (ability in Ability.values()) {
            abilities.halfProfSet.add(ability)
        }
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "Вы можете добавлять половину бонуса мастерства, округлённую в меньшую сторону, ко всем проверкам характеристики, куда этот бонус еще не включён.\n"
)

var songOfRest: AbilityNode = AbilityNode(
    name = "Песнь отдыха",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsMap["Песнь отдыха"] =
            Action(
                name = "Песнь отдыха",
                description = "Вы с помощью успокаивающей музыки или речей можете помочь своим раненым союзникам восстановить их силы во время короткого отдыха. Если вы или любые союзные существа, способные слышать ваше исполнение, восстанавливаете хиты в конце короткого отдыха, используя Кости Хитов, каждое потратившее Кость Хитов существо восстанавливает дополнительно 1к6 хитов.\n" +
                        "\n" +
                        "Количество дополнительно восстанавливаемых хитов растёт с вашим уровнем в этом классе: 1к8 на 9-м уровне, 1к10 на 13 уровне и 1к12 на 17 уровне.",
                type = ActionType.Long
            )
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "Вы с помощью успокаивающей музыки или речей можете помочь своим раненым союзникам восстановить их силы во время короткого отдыха. Если вы или любые союзные существа, способные слышать ваше исполнение, восстанавливаете хиты в конце короткого отдыха, используя Кости Хитов, каждое потратившее Кость Хитов существо восстанавливает дополнительно 1к6 хитов.\n" +
            "\n" +
            "Количество дополнительно восстанавливаемых хитов растёт с вашим уровнем в этом классе: 1к8 на 9-м уровне, 1к10 на 13 уровне и 1к12 на 17 уровне.\n"
)

var bard2: AbilityNode = AbilityNodeLevel(
    name = "Бард_2",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first") { listOf(jackOfAllTrades.name) },
        Pair("second") { listOf(songOfRest.name) }
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "2-й уровень, способности барда",
    next_level = "Бард_3"
)

var bardCollege: AbilityNode = AbilityNode(
    name = "Коллегия бардов",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("college") { listOf(collegeOfLore.name, collegeOfWhispers.name) }
    ),
    requirements = { abilities: CharacterInfo ->
        abilities.characterClass == Classes.Bard
    },
    description = "Вы углубляетесь в традиции выбранной вами коллегии бардов. Этот выбор предоставляет вам умения на 3-м, 6-м и 14-м уровнях.\n"
)

var expertise: AbilityNode = AbilityNode(
    name = "Экспертиза",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair(
            "expertise1",
            {
                listOf(
                    acrobaticsExpertise.name,
                    animalHandlingExpertise.name,
                    arcanaExpertise.name,
                    athleticsExpertise.name,
                    deceptionExpertise.name,
                    historyExpertise.name,
                    insightExpertise.name,
                    intimidationExpertise.name,
                    investigationExpertise.name,
                    medicineExpertise.name,
                    natureExpertise.name,
                    perceptionExpertise.name,
                    performanceExpertise.name,
                    persuasionExpertise.name,
                    religionExpertise.name,
                    sleightOfHandExpertise.name,
                    stealthExpertise.name,
                    survivalExpertise.name
                )
            }
        ),
        Pair(
            "expertise2",
            {
                listOf(
                    acrobaticsExpertise.name,
                    animalHandlingExpertise.name,
                    arcanaExpertise.name,
                    athleticsExpertise.name,
                    deceptionExpertise.name,
                    historyExpertise.name,
                    insightExpertise.name,
                    intimidationExpertise.name,
                    investigationExpertise.name,
                    medicineExpertise.name,
                    natureExpertise.name,
                    perceptionExpertise.name,
                    performanceExpertise.name,
                    persuasionExpertise.name,
                    religionExpertise.name,
                    sleightOfHandExpertise.name,
                    stealthExpertise.name,
                    survivalExpertise.name
                )
            }
        )
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "Выберите 2 навыка из тех, которыми вы владеете. Ваш бонус мастерства для этих навыков удваивается.\n"
)

var bard3: AbilityNode = AbilityNodeLevel(
    name = "Бард_3",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.spellsLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first") { listOf(bardCollege.name) },
        Pair("second") { listOf(expertise.name) }
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "3-й уровень, способности барда",
    next_level = "Бард_4"
)

var bard4: AbilityNode = AbilityNodeLevel(
    name = "Бард_4",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first") { listOf(abilityScoreImprovement.name) },
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "4-й уровень, способности барда",
    next_level = "Бард_5"
)

var fontOfInspiration: AbilityNode = AbilityNode(
    name = "Источник вдохновения",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Источник вдохновения"] =
            "Вы восстанавливаете истраченные вдохновения барда и после короткого и после продолжительного отдыха.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "Вы восстанавливаете истраченные вдохновения барда и после короткого и после продолжительного отдыха.\n"
)

var bard5: AbilityNode = AbilityNodeLevel(
    name = "Бард_5",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.spellsLevel += 1
        abilities.proficiencyBonus += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first") { listOf(fontOfInspiration.name) }
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "5-й уровень, способности барда",
    next_level = "Бард_6"
)

var contercharm: AbilityNode = AbilityNode(
    name = "Контрочарование",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsMap["Контрочарование"] =
            Action(
                name = "Контрочарование",
                description = "Вы получаете возможность использовать звуки или слова силы для разрушения воздействующих на разум эффектов. Вы можете действием начать исполнение, которое продлится до конца вашего следующего хода. В течение этого времени вы и все дружественные существа в пределах 30 футов от вас совершают спасброски от запугивания и очарования с преимуществом. Чтобы получить это преимущество, существа должны слышать вас. Исполнение заканчивается преждевременно, если вы оказываетесь недееспособны, теряете способность говорить, или прекращаете исполнение добровольно (на это не требуется действие).\n",
                type = ActionType.Action
            )

        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "Вы получаете возможность использовать звуки или слова силы для разрушения воздействующих на разум эффектов. Вы можете действием начать исполнение, которое продлится до конца вашего следующего хода. В течение этого времени вы и все дружественные существа в пределах 30 футов от вас совершают спасброски от запугивания и очарования с преимуществом. Чтобы получить это преимущество, существа должны слышать вас. Исполнение заканчивается преждевременно, если вы оказываетесь недееспособны, теряете способность говорить, или прекращаете исполнение добровольно (на это не требуется действие).\n"
)

var bard6: AbilityNode = AbilityNodeLevel(
    name = "Бард_6",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "6-й уровень, способности барда",
    next_level = "Бард_7"
)

var bard7: AbilityNode = AbilityNodeLevel(
    name = "Бард_7",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.spellsLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "7-й уровень, способности барда",
    next_level = "Бард_8"
)

var bard8: AbilityNode = AbilityNodeLevel(
    name = "Бард_8",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first") { listOf(abilityScoreImprovement.name) },
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "8-й уровень, способности барда",
    next_level = "Бард_9"
)

var bard9: AbilityNode = AbilityNodeLevel(
    name = "Бард_9",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.spellsLevel += 1
        abilities.proficiencyBonus += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "9-й уровень, способности барда",
    next_level = "Бард_10"
)

var magicalSecrets_10 = AbilityNode(
    name = "Тайны магии 10 уровня",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { spellist.filter { it.level <= 5 }.map { it.name } }),
        Pair("second", { spellist.filter { it.level <= 5 }.map { it.name } })
    ),
    requirements = { true },
    description = "Вы можете выучить 2 заклинания из доступных любому классу на свой выбор. Их уровень не должен превышать уровня заклинаний, которые вы можете использовать на этом уровне, как показано в таблице Барда. Они также могут быть заговорами. Выбранные заклинания теперь считаются для вас заклинаниями барда, но они не учитываются в общем количестве известных вам заклинаний барда.\n",
    actionForChoice = mutableMapOf(
        Pair("first") { choice: String, abilities: CharacterInfo ->
            abilities.spellsInfo["Тайны магии 10 уровня 1 заклинание"] = CharacterSpells(
                className = abilities.characterClass.className,
                maxKnownSpellsCount = 0,
                maxKnownCantripsCount = 0,
                spellLists = SpellLists(
                    knownSpells = mutableSetOf(choice)
                )
            )
            abilities
        },
        Pair("second") { choice: String, abilities: CharacterInfo ->
            abilities.spellsInfo["Тайны магии 10 уровня 2 заклинание"] = CharacterSpells(
                className = abilities.characterClass.className,
                maxKnownSpellsCount = 0,
                maxKnownCantripsCount = 0,
                spellLists = SpellLists(
                    knownSpells = mutableSetOf(choice)
                )
            )
            abilities
        }
    )
)

var magicalSecrets_14 = AbilityNode(
    name = "Тайны магии 14 уровня",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { spellist.filter { it.level <= 7 }.map { it.name } }),
        Pair("second", { spellist.filter { it.level <= 7 }.map { it.name } })
    ),
    requirements = { true },
    description = "Вы можете выучить 2 заклинания из доступных любому классу на свой выбор. Их уровень не должен превышать уровня заклинаний, которые вы можете использовать на этом уровне, как показано в таблице Барда. Они также могут быть заговорами. Выбранные заклинания теперь считаются для вас заклинаниями барда, но они не учитываются в общем количестве известных вам заклинаний барда.\n",
    actionForChoice = mutableMapOf(
        Pair("first") { choice: String, abilities: CharacterInfo ->
            abilities.spellsInfo["Тайны магии 14 уровня 1 заклинание"] = CharacterSpells(
                className = abilities.characterClass.className,
                maxKnownSpellsCount = 0,
                maxKnownCantripsCount = 0,
                spellLists = SpellLists(
                    knownSpells = mutableSetOf(choice)
                )
            )
            abilities
        },
        Pair("second") { choice: String, abilities: CharacterInfo ->
            abilities.spellsInfo["Тайны магии 14 уровня 2 заклинание"] = CharacterSpells(
                className = abilities.characterClass.className,
                maxKnownSpellsCount = 0,
                maxKnownCantripsCount = 0,
                spellLists = SpellLists(
                    knownSpells = mutableSetOf(choice)
                )
            )
            abilities
        }
    )
)

var magicalSecrets_18 = AbilityNode(
    name = "Тайны магии 18 уровня",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { spellist.map { it.name } }),
        Pair("second", { spellist.map { it.name } })
    ),
    requirements = { true },
    description = "Вы можете выучить 2 заклинания из доступных любому классу на свой выбор. Их уровень не должен превышать уровня заклинаний, которые вы можете использовать на этом уровне, как показано в таблице Барда. Они также могут быть заговорами. Выбранные заклинания теперь считаются для вас заклинаниями барда, но они не учитываются в общем количестве известных вам заклинаний барда.\n",
    actionForChoice = mutableMapOf(
        Pair("first") { choice: String, abilities: CharacterInfo ->
            abilities.spellsInfo["Тайны магии 18 уровня 1 заклинание"] = CharacterSpells(
                className = abilities.characterClass.className,
                maxKnownSpellsCount = 0,
                maxKnownCantripsCount = 0,
                spellLists = SpellLists(
                    knownSpells = mutableSetOf(choice)
                )
            )
            abilities
        },
        Pair("second") { choice: String, abilities: CharacterInfo ->
            abilities.spellsInfo["Тайны магии 18 уровня 2 заклинание"] = CharacterSpells(
                className = abilities.characterClass.className,
                maxKnownSpellsCount = 0,
                maxKnownCantripsCount = 0,
                spellLists = SpellLists(
                    knownSpells = mutableSetOf(choice)
                )
            )
            abilities
        }
    )
)

var bard10: AbilityNode = AbilityNodeLevel(
    name = "Бард_10",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first") { listOf(expertise.name) },
        Pair("second") { listOf(magicalSecrets_10.name) }
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "10-й уровень, способности барда",
    next_level = "Бард_11"
)

var bard11: AbilityNode = AbilityNodeLevel(
    name = "Бард_11",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.spellsLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "11-й уровень, способности барда",
    next_level = "Бард_12"
)

var bard12: AbilityNode = AbilityNodeLevel(
    name = "Бард_12",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first") { listOf(abilityScoreImprovement.name) },
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "12-й уровень, способности барда",
    next_level = "Бард_13"
)

var bard13: AbilityNode = AbilityNodeLevel(
    name = "Бард_13",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.spellsLevel += 1
        abilities.proficiencyBonus += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "13-й уровень, способности барда",
    next_level = "Бард_14"
)

var bard14: AbilityNode = AbilityNodeLevel(
    name = "Бард_14",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first") { listOf(magicalSecrets_14.name) }
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "14-й уровень, способности барда",
    next_level = "Бард_15"
)

var bard15: AbilityNode = AbilityNodeLevel(
    name = "Бард_15",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.spellsLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "15-й уровень, способности барда",
    next_level = "Бард_16"
)

var bard16: AbilityNode = AbilityNodeLevel(
    name = "Бард_16",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first") { listOf(abilityScoreImprovement.name) },
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "16-й уровень, способности барда",
    next_level = "Бард_17"
)

var bard17: AbilityNode = AbilityNodeLevel(
    name = "Бард_17",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.spellsLevel += 1
        abilities.proficiencyBonus += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "17-й уровень, способности барда",
    next_level = "Бард_18"
)

var bard18: AbilityNode = AbilityNodeLevel(
    name = "Бард_18",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first") { listOf(magicalSecrets_18.name) }
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "18-й уровень, способности барда",
    next_level = "Бард_19"
)

var bard19: AbilityNode = AbilityNodeLevel(
    name = "Бард_19",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.spellsLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first") { listOf(abilityScoreImprovement.name) },
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "19-й уровень, способности барда",
    next_level = "Бард_20"
)

var superiorInspiration: AbilityNode = AbilityNode(
    name = "Превосходное вдохновение",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Превосходное вдохновение"] =
            "Если на момент броска инициативы у вас не осталось неиспользованных вдохновений, вы получаете одно.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "Если на момент броска инициативы у вас не осталось неиспользованных вдохновений, вы получаете одно.\n"
)

var bard20: AbilityNode = AbilityNodeLevel(
    name = "Бард_20",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first") { listOf(superiorInspiration.name) },
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "20-й уровень, способности барда",
    next_level = null
)

var mapOfBardAbilities: MutableMap<String, AbilityNode> = (mutableMapOf(
    Pair(classFeaturesBard.name, classFeaturesBard),
    Pair(spellCastingBard.name, spellCastingBard),
    Pair(bardicInspiration.name, bardicInspiration),
    Pair(bard1.name, bard1),
    Pair(jackOfAllTrades.name, jackOfAllTrades),
    Pair(songOfRest.name, songOfRest),
    Pair(bard2.name, bard2),
    Pair(bardCollege.name, bardCollege),
    Pair(expertise.name, expertise),
    Pair(bard3.name, bard3),
    Pair(bard4.name, bard4),
    Pair(fontOfInspiration.name, fontOfInspiration),
    Pair(bard5.name, bard5),
    Pair(contercharm.name, contercharm),
    Pair(bard6.name, bard6),
    Pair(bard7.name, bard7),
    Pair(bard8.name, bard8),
    Pair(bard9.name, bard9),
    Pair(magicalSecrets_10.name, magicalSecrets_10),
    Pair(magicalSecrets_14.name, magicalSecrets_14),
    Pair(magicalSecrets_18.name, magicalSecrets_18),
    Pair(bard10.name, bard10),
    Pair(bard11.name, bard11),
    Pair(bard12.name, bard12),
    Pair(bard13.name, bard13),
    Pair(bard14.name, bard14),
    Pair(bard15.name, bard15),
    Pair(bard16.name, bard16),
    Pair(bard17.name, bard17),
    Pair(bard18.name, bard18),
    Pair(bard19.name, bard19),
    Pair(superiorInspiration.name, superiorInspiration),
    Pair(bard20.name, bard20),
)
        + mapOfCollegeOfLoreAbilities
        + mapOfCollegeOfWhispersAbilities
        ).toMutableMap()
