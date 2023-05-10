package com.andreyyurko.dnd.data.abilities.classes.warlock

import com.andreyyurko.dnd.data.abilities.classes.AbilityNodeLevel
import com.andreyyurko.dnd.data.abilities.other.abilityScoreImprovement
import com.andreyyurko.dnd.data.abilities.other.arcana
import com.andreyyurko.dnd.data.abilities.other.deception
import com.andreyyurko.dnd.data.abilities.other.history
import com.andreyyurko.dnd.data.abilities.other.intimidation
import com.andreyyurko.dnd.data.abilities.other.investigation
import com.andreyyurko.dnd.data.abilities.other.nature
import com.andreyyurko.dnd.data.abilities.other.religion
import com.andreyyurko.dnd.data.characterData.Ability
import com.andreyyurko.dnd.data.characterData.CharacterInfo
import com.andreyyurko.dnd.data.characterData.Classes
import com.andreyyurko.dnd.data.characterData.Priority
import com.andreyyurko.dnd.data.characterData.addAllSimpleWeapons
import com.andreyyurko.dnd.data.characterData.character.AbilityNode
import com.andreyyurko.dnd.data.characterData.character.abilityToModifier
import com.andreyyurko.dnd.data.spells.CharacterSpells
import kotlin.math.max

var classFeaturesWarlock: AbilityNode = AbilityNode(
    name = "Колдун: классовые умения",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.savingThrowProf.add(Ability.Wisdom)
        abilities.savingThrowProf.add(Ability.Charisma)
        addAllSimpleWeapons(abilities)
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair(
            "skill1",
            { listOf(intimidation.name, history.name, arcana.name, deception.name, nature.name, investigation.name, religion.name) }
        ),
        Pair(
            "skill2",
            { listOf(intimidation.name, history.name, arcana.name, deception.name, nature.name, investigation.name, religion.name) }
        ),
    ),
    requirements = { abilities: CharacterInfo ->
        abilities.characterClass == Classes.Warlock
    },
    description = "ХИТЫ\n" +
            "\n" +
            "Кость Хитов: 1к8 за каждый уровень колдуна\n" +
            "\n" +
            "Хиты на 1 уровне: 8 + модификатор Телосложения\n" +
            "\n" +
            "Хиты на следующих уровнях: 1к8 (или 5) + модификатор Телосложения (суммарно минимум 1) за каждый уровень колдуна после первого\n" +
            "\n" +
            "ВЛАДЕНИЕ\n" +
            "\n" +
            "Доспехи: Лёгкие доспехи\n" +
            "\n" +
            "Оружие: Простое оружие\n" +
            "\n" +
            "Инструменты: Нет\n" +
            "\n" +
            "Спасброски: Мудрость, Харизма\n" +
            "\n" +
            "Навыки: Выберите два навыка из следующих: Запугивание, История, Магия, Обман, Природа, Расследование, Религия\n",
    priority = Priority.DoFirst
)

var spellCastingWarlock: AbilityNode = AbilityNode(
    name = "Колдун: использование заклинаний",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.spellsInfo.apply {
            if (!this.contains("Заклинания класса")) {
                this["Заклинания класса"] = CharacterSpells()
            }
            this["Заклинания класса"]?.className = Classes.Wizard.className
            this["Заклинания класса"]?.maxKnownSpellsCount = 4 + 2 * (abilities.level)
            this["Заклинания класса"]?.maxPreparedSpellsCount =
                max((abilityToModifier(abilities.intelligence) + abilities.level), 1)
            this["Заклинания класса"]?.maxKnownCantripsCount = kotlin.math.min((abilities.level + 20) / 6, 5)
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

var warlock1: AbilityNodeLevel = AbilityNodeLevel(
    name = "Колдун_1",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.proficiencyBonus += 2
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(classFeaturesWarlock.name) }),
        //Pair("second", { listOf(spellCastingWarlock.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "1-й уровень, способности колдуна",
    next_level = "Колдун_2",
)

var warlock2: AbilityNodeLevel = AbilityNodeLevel(
    name = "Колдун_2",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        //Pair("first", { listOf(strokeOfLuck.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "2-й уровень, способности колдуна",
    next_level = "Колдун_3",
)

var warlock3: AbilityNodeLevel = AbilityNodeLevel(
    name = "Колдун_3",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        //Pair("first", { listOf(strokeOfLuck.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "3-й уровень, способности колдуна",
    next_level = "Колдун_4",
)

var warlock4: AbilityNodeLevel = AbilityNodeLevel(
    name = "Колдун_4",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(abilityScoreImprovement.name) }),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "4-й уровень, способности колдуна",
    next_level = "Колдун_5",
)

var warlock5: AbilityNodeLevel = AbilityNodeLevel(
    name = "Колдун_5",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities.proficiencyBonus += 1
        abilities
    },
    getAlternatives = mutableMapOf(
        //Pair("first", { listOf(strokeOfLuck.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "5-й уровень, способности колдуна",
    next_level = "Колдун_6",
)

var warlock6: AbilityNodeLevel = AbilityNodeLevel(
    name = "Колдун_6",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        //Pair("first", { listOf(strokeOfLuck.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "6-й уровень, способности колдуна",
    next_level = "Колдун_7",
)

var warlock7: AbilityNodeLevel = AbilityNodeLevel(
    name = "Колдун_7",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        //Pair("first", { listOf(strokeOfLuck.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "7-й уровень, способности колдуна",
    next_level = "Колдун_8",
)

var warlock8: AbilityNodeLevel = AbilityNodeLevel(
    name = "Колдун_8",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(abilityScoreImprovement.name) }),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "8-й уровень, способности колдуна",
    next_level = "Колдун_9",
)

var warlock9: AbilityNodeLevel = AbilityNodeLevel(
    name = "Колдун_9",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities.proficiencyBonus += 1
        abilities
    },
    getAlternatives = mutableMapOf(
        //Pair("first", { listOf(strokeOfLuck.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "9-й уровень, способности колдуна",
    next_level = "Колдун_10",
)

var warlock10: AbilityNodeLevel = AbilityNodeLevel(
    name = "Колдун_10",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        //Pair("first", { listOf(strokeOfLuck.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "10-й уровень, способности колдуна",
    next_level = "Колдун_11",
)

var warlock11: AbilityNodeLevel = AbilityNodeLevel(
    name = "Колдун_11",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        //Pair("first", { listOf(strokeOfLuck.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "11-й уровень, способности колдуна",
    next_level = "Колдун_12",
)

var warlock12: AbilityNodeLevel = AbilityNodeLevel(
    name = "Колдун_12",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(abilityScoreImprovement.name) }),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "12-й уровень, способности колдуна",
    next_level = "Колдун_13",
)

var warlock13: AbilityNodeLevel = AbilityNodeLevel(
    name = "Колдун_13",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities.proficiencyBonus += 1
        abilities
    },
    getAlternatives = mutableMapOf(
        //Pair("first", { listOf(strokeOfLuck.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "13-й уровень, способности колдуна",
    next_level = "Колдун_14",
)

var warlock14: AbilityNodeLevel = AbilityNodeLevel(
    name = "Колдун_14",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        //Pair("first", { listOf(strokeOfLuck.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "14-й уровень, способности колдуна",
    next_level = "Колдун_15",
)

var warlock15: AbilityNodeLevel = AbilityNodeLevel(
    name = "Колдун_15",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        //Pair("first", { listOf(strokeOfLuck.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "15-й уровень, способности колдуна",
    next_level = "Колдун_16",
)

var warlock16: AbilityNodeLevel = AbilityNodeLevel(
    name = "Колдун_16",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(abilityScoreImprovement.name) }),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "16-й уровень, способности колдуна",
    next_level = "Колдун_17",
)

var warlock17: AbilityNodeLevel = AbilityNodeLevel(
    name = "Колдун_17",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities.proficiencyBonus += 1
        abilities
    },
    getAlternatives = mutableMapOf(
        //Pair("first", { listOf(strokeOfLuck.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "17-й уровень, способности колдуна",
    next_level = "Колдун_18",
)

var warlock18: AbilityNodeLevel = AbilityNodeLevel(
    name = "Колдун_18",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        //Pair("first", { listOf(strokeOfLuck.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "18-й уровень, способности колдуна",
    next_level = "Колдун_19",
)

var warlock19: AbilityNodeLevel = AbilityNodeLevel(
    name = "Колдун_19",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(abilityScoreImprovement.name) }),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "19-й уровень, способности колдуна",
    next_level = "Колдун_20",
)

var warlock20: AbilityNodeLevel = AbilityNodeLevel(
    name = "Колдун_20",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        //Pair("first", { listOf(strokeOfLuck.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "20-й уровень, способности колдуна",
    next_level = null,
)

var mapOfWarlockAbilities: MutableMap<String, AbilityNode> = (mutableMapOf(
    Pair(classFeaturesWarlock.name, classFeaturesWarlock),
    Pair(spellCastingWarlock.name, spellCastingWarlock),
    Pair(warlock1.name, warlock1),
    Pair(warlock2.name, warlock2),
    Pair(warlock3.name, warlock3),
    Pair(warlock4.name, warlock4),
    Pair(warlock5.name, warlock5),
    Pair(warlock6.name, warlock6),
    Pair(warlock7.name, warlock7),
    Pair(warlock8.name, warlock8),
    Pair(warlock9.name, warlock9),
    Pair(warlock10.name, warlock10),
    Pair(warlock11.name, warlock11),
    Pair(warlock12.name, warlock12),
    Pair(warlock13.name, warlock13),
    Pair(warlock14.name, warlock14),
    Pair(warlock15.name, warlock15),
    Pair(warlock16.name, warlock16),
    Pair(warlock17.name, warlock17),
    Pair(warlock18.name, warlock18),
    Pair(warlock19.name, warlock19),
    Pair(warlock20.name, warlock20),
)
        //+ mapOfAssassinArchetypeAbilities
        //+ mapOfArcaneTricksterArchetypeAbilities
        ).toMutableMap()