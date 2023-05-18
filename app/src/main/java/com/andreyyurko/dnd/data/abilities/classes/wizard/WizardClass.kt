package com.andreyyurko.dnd.data.abilities.classes.wizard

import com.andreyyurko.dnd.data.abilities.classes.AbilityNodeLevel
import com.andreyyurko.dnd.data.abilities.other.abilityScoreImprovement
import com.andreyyurko.dnd.data.abilities.other.arcana
import com.andreyyurko.dnd.data.abilities.other.history
import com.andreyyurko.dnd.data.abilities.other.insight
import com.andreyyurko.dnd.data.abilities.other.investigation
import com.andreyyurko.dnd.data.abilities.other.medicine
import com.andreyyurko.dnd.data.abilities.other.religion
import com.andreyyurko.dnd.data.characterData.Ability
import com.andreyyurko.dnd.data.characterData.Action
import com.andreyyurko.dnd.data.characterData.ActionType
import com.andreyyurko.dnd.data.characterData.CharacterInfo
import com.andreyyurko.dnd.data.characterData.ChargesCounter
import com.andreyyurko.dnd.data.characterData.Classes
import com.andreyyurko.dnd.data.characterData.Priority
import com.andreyyurko.dnd.data.characterData.Weapon
import com.andreyyurko.dnd.data.characterData.character.AbilityNode
import com.andreyyurko.dnd.data.characterData.character.abilityToModifier
import com.andreyyurko.dnd.data.spells.CharacterSpells
import kotlin.math.max

var classFeaturesWizard: AbilityNode = AbilityNode(
    name = "Волшебник: классовые умения",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.savingThrowProf.add(Ability.Wisdom)
        abilities.savingThrowProf.add(Ability.Intelligence)
        abilities.weaponProficiency.add(Weapon.Dagger)
        abilities.weaponProficiency.add(Weapon.Dart)
        abilities.weaponProficiency.add(Weapon.Sling)
        abilities.weaponProficiency.add(Weapon.Quarterstaff)
        abilities.weaponProficiency.add(Weapon.CrossbowLight)
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair(
            "skill1",
            {
                listOf(
                    history.name,
                    arcana.name,
                    medicine.name,
                    insight.name,
                    investigation.name,
                    religion.name
                )
            }
        ),
        Pair(
            "skill2",
            {
                listOf(
                    history.name,
                    arcana.name,
                    medicine.name,
                    insight.name,
                    investigation.name,
                    religion.name
                )
            }
        ),
    ),
    requirements = { abilities: CharacterInfo ->
        abilities.characterClass == Classes.Wizard
    },
    description = "ХИТЫ\n" +
            "\n" +
            "Кость Хитов: 1к6 за каждый уровень волшебника\n" +
            "\n" +
            "Хиты на 1 уровне: 6 + ваш модификатор Телосложения\n" +
            "\n" +
            "Хиты на следующих уровнях: 1к6 (или 4) + модификатор Телосложения (суммарно минимум 1) за каждый уровень волшебника после первого\n" +
            "\n" +
            "ВЛАДЕНИЕ\n" +
            "\n" +
            "Доспехи: Нет\n" +
            "\n" +
            "Оружие: Кинжалы, дротики, пращи, боевые посохи, лёгкие арбалеты\n" +
            "\n" +
            "Инструменты: Нет\n" +
            "\n" +
            "Спасброски: Интеллект, Мудрость\n" +
            "\n" +
            "Навыки: Выберите два навыка из следующих: История, Магия, Медицина, Проницательность, Расследование, Религия",
    priority = Priority.DoFirst
)

var spellCastingWizard: AbilityNode = AbilityNode(
    name = "Волшебник: использование заклинаний",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.spellsInfo.apply {
            if (!this.contains("Заклинания класса")) {
                this["Заклинания класса"] = CharacterSpells()
            }
            this["Заклинания класса"]?.className = Classes.Wizard.className
            this["Заклинания класса"]?.maxKnownSpellsCount = 4 + 2 * (abilities.level)
            this["Заклинания класса"]?.maxPreparedSpellsCount =
                max((abilityToModifier(abilities.intelligence) + abilities.level), 1)
            this["Заклинания класса"]?.maxKnownCantripsCount =
                kotlin.math.min((abilities.level + 20) / 6, 5)
        }
        abilities.additionalAbilities["Ритуальное колдовство"] =
            "Вы можете сотворить любое известное вам заклинание волшебника в качестве ритуала, если заклинание позволяет это.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.characterClass == Classes.Wizard
    },
    description = "Являясь учеником тайной магии, вы обладаете книгой, содержащей заклинания, показывающие первые проблески вашей истинной силы. Вы найдёте список заклинаний, доступных волшебнику в этом разделе: заклинания волшебника.\n" +
            "Заговоры (заклинания 0-го уровня)\n" +
            "\n" +
            "На 1-м уровне вы знаете три заговора на ваш выбор из списка заклинаний волшебника. Вы узнаёте дополнительные заговоры волшебника на более высоких уровнях, как показано в колонке «Известные заговоры».\n" +
            "Книга заклинаний\n" +
            "\n" +
            "На 1-м уровне у вас есть книга заклинаний, содержащая шесть заклинаний волшебника 1-го уровня по вашему выбору. Ваша книга заклинаний является хранилищем известных вам заклинаний волшебника, за исключением заговоров, которые вы всегда помните.\nПодготовка и сотворение заклинаний\n" +
            "\n" +
            "Таблица «Волшебник» показывает, какое количество ячеек для накладывания заклинаний волшебника у вас есть на первом и более высоких уровнях. Для накладывания одного из этих заклинаний вы должны потратить ячейку заклинаний того же уровня или выше, что и само заклинание. Вы восстановите все потраченные ячейки, когда завершите продолжительный отдых.\n" +
            "\n" +
            "Вы подготавливаете список заклинаний волшебника, доступных для накладывания. При этом вы выбираете число заклинаний волшебника из своей книги заклинаний, равное модификатору Интеллекта + уровень волшебника (минимум одно заклинание). Уровень заклинаний не должен превышать уровень самой высокой имеющейся у вас ячейки заклинаний. Например, если вы волшебник 3-го уровня, то у вас есть четыре ячейки заклинаний 1-го уровня и две ячейки 2-го уровня. При Интеллекте 16 ваш список подготовленных заклинаний может включать в себя шесть заклинаний 1-го или 2-го уровня в любой комбинации, выбранных из вашей книги заклинаний. Если вы подготовили заклинание 1-го уровня волшебная стрела [magic missile], вы можете наложить его, используя ячейку 1-го уровня или ячейку 2-го уровня. Накладывание заклинания не удаляет его из списка подготовленных заклинаний.\n" +
            "\n" +
            "Вы можете изменить список подготовленных заклинаний, когда заканчиваете продолжительный отдых. Подготовка нового списка заклинаний волшебника требует времени, проведённого в изучении книги заклинаний и запоминания слов и жестов, которые вы должны совершить, чтобы наложить заклинание: не менее 1 минуты за уровень заклинания для каждого заклинания в вашем списке.\n" +
            "Базовая характеристика заклинаний\n" +
            "\n" +
            "При создании заклинаний волшебник использует Интеллект, так как вы узнаёте свои заклинания специальными исследованиями и запоминанием. Вы используете Интеллект в случаях, когда заклинание ссылается на базовую характеристику. Кроме того, вы используете модификатор Интеллекта при определении Сл спасбросков от ваших заклинаний волшебника, и при броске атаки заклинаниями.\n" +
            "\n" +
            "Сл спасброска = 8 + ваш бонус мастерства + ваш модификатор Интеллекта\n" +
            "\n" +
            "Модификатор броска атаки = ваш бонус мастерства + ваш модификатор Интеллекта\n" +
            "Ритуальное колдовство\n" +
            "\n" +
            "Вы можете сотворить заклинание волшебника как ритуал, если у этого заклинания есть ключевое слово «ритуал», и оно есть в вашей книге заклинаний. Вам не нужно иметь это заклинание подготовленным.\n" +
            "Фокусировка заклинания\n" +
            "\n" +
            "Вы можете использовать магическую фокусировку в качестве заклинательной фокусировки для заклинаний волшебника.\n" +
            "Известные заклинания первого и более высоких уровней\n" +
            "\n" +
            "Каждый раз, когда вы получаете уровень волшебника, вы можете добавить два заклинания волшебника по вашему выбору в книгу заклинаний. Уровень этих заклинаний не должен превышать уровень самой высокой имеющейся у вас ячейки заклинаний. Во время приключений вы можете найти другие заклинания, которые сможете добавить в книгу заклинаний.\n"
)

var arcaneRecovery: AbilityNode = AbilityNode(
    name = "Магическое восстановление",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        if (!abilities.currentState.charges.contains("Магическое восстановление")) {
            abilities.currentState.charges["Магическое восстановление"] = ChargesCounter(
                current = 1,
                maximum = 1
            )
        }
        abilities.actionsMap["Магическое восстановление"] =
            Action(
                name = "Магическое восстановление",
                description = "Вы знаете как восстанавливать часть магической энергии, изучая книгу заклинаний. Один раз в день, когда вы заканчиваете короткий отдых, вы можете восстановить часть использованных ячеек заклинаний. Ячейки заклинаний могут иметь суммарный уровень, который не превышает половину уровня вашего волшебника (округляя в большую сторону), и ни одна из ячеек не может быть шестого уровня или выше.\n" +
                        "\n" +
                        "Например, если вы волшебник 4-го уровня, вы можете восстановить ячейки заклинаний с суммой уровней не больше двух. Вы можете восстановить одну ячейку заклинаний 2-го уровня, или две ячейки заклинаний 1-го уровня.\n",
                type = ActionType.Long,
                relatedCharges = "Магическое восстановление"
            )

        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "Вы знаете как восстанавливать часть магической энергии, изучая книгу заклинаний. Один раз в день, когда вы заканчиваете короткий отдых, вы можете восстановить часть использованных ячеек заклинаний. Ячейки заклинаний могут иметь суммарный уровень, который не превышает половину уровня вашего волшебника (округляя в большую сторону), и ни одна из ячеек не может быть шестого уровня или выше.\n" +
            "\n" +
            "Например, если вы волшебник 4-го уровня, вы можете восстановить ячейки заклинаний с суммой уровней не больше двух. Вы можете восстановить одну ячейку заклинаний 2-го уровня, или две ячейки заклинаний 1-го уровня.\n"
)

var wizard1: AbilityNode = AbilityNodeLevel(
    name = "Волшебник_1",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.characterClass = Classes.Wizard
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.spellsLevel += 1
        abilities.proficiencyBonus += 2
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(classFeaturesWizard.name) }),
        Pair("second", { listOf(spellCastingWizard.name) }),
        Pair("third", { listOf(arcaneRecovery.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "1-й уровень, способности волшебника",
    next_level = "Волшебник_2"
)

var arcaneTradition: AbilityNode = AbilityNode(
    name = "Магические традиции",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(schoolOfDivination.name, schoolOfConjuration.name) })
    ),
    requirements = { true },
    description = "Вы выбираете магическую традицию, определяющую вашу магическую практику. Подробнее обо всех традициях можно прочесть в конце описания класса.\n" +
            "\n" +
            "Ваш выбор даёт вам умения на 2-м, 6-м, 10-м и 14-м уровнях.\n"
)

var wizard2: AbilityNode = AbilityNodeLevel(
    name = "Волшебник_2",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(arcaneTradition.name) }),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "2-й уровень, способности волшебника",
    next_level = "Волшебник_3"
)

var wizard3: AbilityNode = AbilityNodeLevel(
    name = "Волшебник_3",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.spellsLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "3-й уровень, способности волшебника",
    next_level = "Волшебник_4"
)

var wizard4: AbilityNode = AbilityNodeLevel(
    name = "Волшебник_4",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(abilityScoreImprovement.name) }),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "4-й уровень, способности волшебника",
    next_level = "Волшебник_5"
)

var wizard5: AbilityNode = AbilityNodeLevel(
    name = "Волшебник_5",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.spellsLevel += 1
        abilities.proficiencyBonus += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "5-й уровень, способности волшебника",
    next_level = "Волшебник_6"
)

var wizard6: AbilityNode = AbilityNodeLevel(
    name = "Волшебник_6",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "6-й уровень, способности волшебника",
    next_level = "Волшебник_7"
)

var wizard7: AbilityNode = AbilityNodeLevel(
    name = "Волшебник_7",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.spellsLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "7-й уровень, способности волшебника",
    next_level = "Волшебник_8"
)

var wizard8: AbilityNode = AbilityNodeLevel(
    name = "Волшебник_8",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(abilityScoreImprovement.name) }),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "8-й уровень, способности волшебника",
    next_level = "Волшебник_9"
)

var wizard9: AbilityNode = AbilityNodeLevel(
    name = "Волшебник_9",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.spellsLevel += 1
        abilities.proficiencyBonus += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "9-й уровень, способности волшебника",
    next_level = "Волшебник_10"
)

var wizard10: AbilityNode = AbilityNodeLevel(
    name = "Волшебник_10",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "10-й уровень, способности волшебника",
    next_level = "Волшебник_11"
)

var wizard11: AbilityNode = AbilityNodeLevel(
    name = "Волшебник_11",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.spellsLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "11-й уровень, способности волшебника",
    next_level = "Волшебник_12"
)

var wizard12: AbilityNode = AbilityNodeLevel(
    name = "Волшебник_12",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(abilityScoreImprovement.name) }),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "12-й уровень, способности волшебника",
    next_level = "Волшебник_13"
)

var wizard13: AbilityNode = AbilityNodeLevel(
    name = "Волшебник_13",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.spellsLevel += 1
        abilities.proficiencyBonus += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        if (!abilities.currentState.charges.contains("Ячейки_7")) {
            abilities.currentState.charges["Ячейки_7"] = ChargesCounter(
                current = 1,
                maximum = 1
            )
        }
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "13-й уровень, способности волшебника",
    next_level = "Волшебник_14"
)

var wizard14: AbilityNode = AbilityNodeLevel(
    name = "Волшебник_14",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "14-й уровень, способности волшебника",
    next_level = "Волшебник_15"
)

var wizard15: AbilityNode = AbilityNodeLevel(
    name = "Волшебник_15",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.spellsLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
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
    description = "15-й уровень, способности волшебника",
    next_level = "Волшебник_16"
)

var wizard16: AbilityNode = AbilityNodeLevel(
    name = "Волшебник_16",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(abilityScoreImprovement.name) }),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "16-й уровень, способности волшебника",
    next_level = "Волшебник_17"
)

var wizard17: AbilityNode = AbilityNodeLevel(
    name = "Волшебник_17",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.spellsLevel += 1
        abilities.proficiencyBonus += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        if (!abilities.currentState.charges.contains("Ячейки_9")) {
            abilities.currentState.charges["Ячейки_9"] = ChargesCounter(
                current = 1,
                maximum = 1
            )
        }
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "17-й уровень, способности волшебника",
    next_level = "Волшебник_18"
)

var spellMastery: AbilityNode = AbilityNode(
    name = "Мастерство заклинателя",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Мастерство заклинателя"] =
            "Вы достигаете такого мастерства в отношении некоторых заклинаний, что можете накладывать их неограниченное количество раз. Выберите одно заклинание волшебника 1-го уровня и одно заклинание волшебника 2-го уровня, которые есть в вашей книге заклинаний. Вы можете накладывать эти заклинания без увеличения их уровня, не тратя ячейки заклинаний, при условии, что вы их подготовили. Если вы хотите увеличить уровень этих заклинаний, вы должны потратить ячейку заклинаний как обычно.\n" +
                    "\n" +
                    "Потратив 8 часов на обучение, вы можете изменить одно или оба эти заклинания по своему усмотрению на заклинания тех же уровней.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "Вы достигаете такого мастерства в отношении некоторых заклинаний, что можете накладывать их неограниченное количество раз. Выберите одно заклинание волшебника 1-го уровня и одно заклинание волшебника 2-го уровня, которые есть в вашей книге заклинаний. Вы можете накладывать эти заклинания без увеличения их уровня, не тратя ячейки заклинаний, при условии, что вы их подготовили. Если вы хотите увеличить уровень этих заклинаний, вы должны потратить ячейку заклинаний как обычно.\n" +
            "\n" +
            "Потратив 8 часов на обучение, вы можете изменить одно или оба эти заклинания по своему усмотрению на заклинания тех же уровней.\n"
)

var wizard18: AbilityNode = AbilityNodeLevel(
    name = "Волшебник_18",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities.currentState.charges["Ячейки_5"]?.let {
            if (it.maximum < 3)
                it.maximum = 3
        }
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(spellMastery.name) }),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "18-й уровень, способности волшебника",
    next_level = "Волшебник_19"
)

var wizard19: AbilityNode = AbilityNodeLevel(
    name = "Волшебник_19",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.spellsLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities.currentState.charges["Ячейки_6"]?.let {
            if (it.maximum < 2)
                it.maximum = 2
        }
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(abilityScoreImprovement.name) }),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "19-й уровень, способности волшебника",
    next_level = "Волшебник_20"
)

var signatureSpells: AbilityNode = AbilityNode(
    name = "Фирменное заклинание",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsMap["Фирменное заклинание"] =
            Action(
                name = "Фирменное заклинание",
                description = "Вы получаете власть над двумя мощными заклинаниями и можете накладывать их без усилий. Выберите два заклинания волшебника 3-го уровня из своей книги заклинаний в качестве фирменных заклинаний. Для вас эти заклинания всегда считаются подготовленными, они не учитываются в количестве подготовленных заклинаний, и вы можете наложить каждое из этих заклинаний 3-го уровня, не тратя ячейку заклинаний. Когда вы так поступаете, вы не можете наложить их повторно таким же образом, пока не закончите короткий или продолжительный отдых.\n" +
                        "\n" +
                        "Если вы хотите наложить заклинание более высокого уровня, вы должны потратить ячейку заклинаний как обычно.\n",
                type = ActionType.Long,
            )

        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "Вы получаете власть над двумя мощными заклинаниями и можете накладывать их без усилий. Выберите два заклинания волшебника 3-го уровня из своей книги заклинаний в качестве фирменных заклинаний. Для вас эти заклинания всегда считаются подготовленными, они не учитываются в количестве подготовленных заклинаний, и вы можете наложить каждое из этих заклинаний 3-го уровня, не тратя ячейку заклинаний. Когда вы так поступаете, вы не можете наложить их повторно таким же образом, пока не закончите короткий или продолжительный отдых.\n" +
            "\n" +
            "Если вы хотите наложить заклинание более высокого уровня, вы должны потратить ячейку заклинаний как обычно.\n"
)

var wizard20: AbilityNode = AbilityNodeLevel(
    name = "Волшебник_20",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities.currentState.charges["Ячейки_7"]?.let {
            if (it.maximum < 2)
                it.maximum = 2
        }
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(signatureSpells.name) }),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "20-й уровень, способности волшебника",
    next_level = null
)

/// we need to add multichoice to spellMastery and signatureSpells

var mapOfWizardAbilities: MutableMap<String, AbilityNode> = (mutableMapOf(
    Pair(classFeaturesWizard.name, classFeaturesWizard),
    Pair(spellCastingWizard.name, spellCastingWizard),
    Pair(arcaneRecovery.name, arcaneRecovery),
    Pair(wizard1.name, wizard1),
    Pair(arcaneTradition.name, arcaneTradition),
    Pair(wizard2.name, wizard2),
    Pair(wizard3.name, wizard3),
    Pair(wizard4.name, wizard4),
    Pair(wizard5.name, wizard5),
    Pair(wizard6.name, wizard6),
    Pair(wizard7.name, wizard7),
    Pair(wizard8.name, wizard8),
    Pair(wizard9.name, wizard9),
    Pair(wizard10.name, wizard10),
    Pair(wizard11.name, wizard11),
    Pair(wizard12.name, wizard12),
    Pair(wizard13.name, wizard13),
    Pair(wizard14.name, wizard14),
    Pair(wizard15.name, wizard15),
    Pair(wizard16.name, wizard16),
    Pair(wizard17.name, wizard17),
    Pair(spellMastery.name, spellMastery),
    Pair(wizard18.name, wizard18),
    Pair(wizard19.name, wizard19),
    Pair(signatureSpells.name, signatureSpells),
    Pair(wizard20.name, wizard20),
)
        + mapOfSchoolOfDivinationAbilities
        + mapOfSchoolOfConjurationAbilities
        ).toMutableMap()