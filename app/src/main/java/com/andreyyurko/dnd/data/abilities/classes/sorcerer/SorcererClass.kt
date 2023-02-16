package com.andreyyurko.dnd.data.abilities.classes.sorcerer

import android.util.Log
import com.andreyyurko.dnd.data.abilities.classes.AbilityNodeLevel
import com.andreyyurko.dnd.data.abilities.other.*
import com.andreyyurko.dnd.data.characterData.Ability
import com.andreyyurko.dnd.data.characterData.CharacterInfo
import com.andreyyurko.dnd.data.characterData.Classes
import com.andreyyurko.dnd.data.characterData.Priority
import com.andreyyurko.dnd.data.characterData.character.AbilityNode
import com.andreyyurko.dnd.data.characterData.character.abilityToModifier

var classFeaturesSorcerer: AbilityNode = AbilityNode(
    name = "Чародей: классовые умения",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.savingThrowProf.add(Ability.Constitution)
        abilities.savingThrowProf.add(Ability.Charisma)
        abilities
    },
    alternatives = mutableMapOf(
        Pair(
            "skill1",
            listOf(intimidation.name, arcana.name, deception.name, insight.name, religion.name, persuasion.name)
        ),
        Pair(
            "skill2",
            listOf(intimidation.name, arcana.name, deception.name, insight.name, religion.name, persuasion.name)
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
            this.className = Classes.Sorcerer.className
            this.maxPreparedSpellsCount += 2
            this.maxPreparedCantripsCount += 4
        }
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.characterClass == Classes.Sorcerer
    },
    description = "Определённое событие в вашем прошлом или в жизни ваших родителей или предков оставило на вас неизгладимый отпечаток, связав вас с магией. Ваши заклинания питает некий источник магии, какого бы происхождения он не был. Вы найдёте список заклинаний, доступных чародею в этом разделе: заклинания чародея.\n" +
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
            "Вы можете использовать магическую фокусировку в качестве фокусировки для заклинаний чародея"
)

var sorcerer1: AbilityNodeLevel = AbilityNodeLevel(
    name = "Чародей_1",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.characterClass = Classes.Sorcerer
        abilities.level += 1
        abilities.proficiencyBonus += 2
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(classFeaturesSorcerer.name)),
        Pair("second", listOf(spellCastingSorcerer.name)),
    ),
    requirements = { true },
    add_requirements = listOf(),
    description = "1-й уровень, способности чародея",
    next_level = "Чародей_2",
)

var sorcerer2: AbilityNodeLevel = AbilityNodeLevel(
    name = "Чародей_2",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities.spellsInfo.maxPreparedSpellsCount += 1
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    add_requirements = listOf(),
    description = "2-й уровень, способности чародея",
    next_level = "Чародей_3",
)

var sorcerer3: AbilityNodeLevel = AbilityNodeLevel(
    name = "Чародей_3",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities.spellsInfo.maxPreparedSpellsCount += 1
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    add_requirements = listOf(),
    description = "3-й уровень, способности чародея",
    next_level = "Чародей_4",
)

var sorcerer4: AbilityNodeLevel = AbilityNodeLevel(
    name = "Чародей_4",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities.spellsInfo.maxPreparedSpellsCount += 1
        Log.d("spells", abilities.spellsInfo.maxPreparedSpellsCount.toString())
        abilities.spellsInfo.maxPreparedCantripsCount += 1
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    add_requirements = listOf(),
    description = "4-й уровень, способности чародея",
    next_level = "Чародей_5",
)

var sorcerer5: AbilityNodeLevel = AbilityNodeLevel(
    name = "Чародей_5",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.proficiencyBonus += 1
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 4
        abilities.spellsInfo.maxPreparedSpellsCount += 1
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    add_requirements = listOf(),
    description = "5-й уровень, способности чародея",
    next_level = null,
)

var mapOfSorcererAbilities = mutableMapOf(
    Pair(classFeaturesSorcerer.name, classFeaturesSorcerer),
    Pair(spellCastingSorcerer.name, spellCastingSorcerer),
    Pair(sorcerer1.name, sorcerer1),
    Pair(sorcerer2.name, sorcerer2),
    Pair(sorcerer3.name, sorcerer3),
    Pair(sorcerer4.name, sorcerer4),
    Pair(sorcerer5.name, sorcerer5)
)