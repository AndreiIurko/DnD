package com.andreyyurko.dnd.data.abilities.classes.warlock

import android.util.Log
import com.andreyyurko.dnd.data.abilities.classes.AbilityNodeLevel
import com.andreyyurko.dnd.data.abilities.other.*
import com.andreyyurko.dnd.data.characterData.*
import com.andreyyurko.dnd.data.characterData.character.AbilityNode
import com.andreyyurko.dnd.data.characterData.character.abilityToModifier
import com.andreyyurko.dnd.data.spells.CharacterSpells
import com.andreyyurko.dnd.data.spells.SpellLists
import com.andreyyurko.dnd.data.spells.spellist

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
        var spellNumber = abilities.level + 1
        if (abilities.level > 9) spellNumber = (abilities.level + 11) / 2
        var cantripNumber = 2
        if (abilities.level > 3) cantripNumber = 3
        if (abilities.level > 9) cantripNumber = 4
        abilities.spellsInfo.apply {
            if (!this.contains("Заклинания класса")) {
                this["Заклинания класса"] = CharacterSpells()
            }
            this["Заклинания класса"]?.className = Classes.Warlock.className
            this["Заклинания класса"]?.maxKnownSpellsCount = spellNumber
            this["Заклинания класса"]?.maxKnownCantripsCount = cantripNumber
        }
        abilities.additionalAbilities["Ритуальное колдовство"] =
            "Вы можете сотворить любое известное вам заклинание волшебника в качестве ритуала, если заклинание позволяет это.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.characterClass == Classes.Wizard
    },
    description = "Исследования тайн и магия, дарованная покровителем, дают вам возможность использовать заклинания. Вы найдёте список заклинаний, доступных колдуну в этом разделе: заклинания колдуна.\n" +
            "Заговоры (заклинания 0-го уровня)\n" +
            "\n" +
            "Вы знаете два заговора на свой выбор из списка заклинаний колдуна. Вы узнаёте дополнительные заговоры колдуна на более высоких уровнях, как показано в колонке «Известные заговоры».\n" +
            "Ячейки заклинаний\n" +
            "\n" +
            "Таблица «Колдун» показывает, какое количество ячеек для накладывания заклинаний колдуна с 1-го по 5-й уровень у вас есть, а также уровень этих ячеек — все ваши ячейки заклинаний одного уровня. Для накладывания одного из заклинаний колдуна 1-го уровня и выше вы должны потратить ячейку заклинаний. Вы восстановите все потраченные ячейки, когда завершите короткий или продолжительный отдых.\n" +
            "\n" +
            "Например, если ваш персонаж 5-го уровня, у вас есть две ячейки заклинаний 3-го уровня. Чтобы использовать заклинание 1-го уровня ведьмин снаряд [witch bolt] вы должны потратить одну из этих ячеек, и заклинание сработает как заклинание 3-го уровня.\n" +
            "Известные заклинания первого и более высоких уровней\n" +
            "\n" +
            "На 1-м уровне вы знаете два заклинания 1-го уровня по своему выбору из списка заклинаний колдуна.\n" +
            "\n" +
            "Колонка «Известные заклинания» показывает, когда вы сможете выучить новые заклинания 1-го уровня и выше. Уровень заклинания, которое вы выбрали, не должен превышать уровень ячеек, указанный в таблице для колдуна вашего уровня. Например, когда вы получите 6-й уровень, вы изучите новое заклинание колдуна, которое может быть 1-го, 2-го или 3-го уровней.\n" +
            "\n" +
            "Кроме того, когда вы получаете уровень в этом классе, вы можете выбрать одно из известных вам заклинаний колдуна и заменить его другим заклинанием из списка заклинаний колдуна, с уровнем, не превышающим уровень ячеек заклинаний.\n" +
            "Базовая характеристика заклинаний\n" +
            "\n" +
            "При накладывании заклинаний колдун использует Харизму. Вы используете Харизму в случаях, когда заклинание ссылается на базовую характеристику. Кроме того, вы используете модификатор Харизмы при определении Сл спасбросков от ваших заклинаний колдуна, и при броске атаки заклинаниями.\n" +
            "\n" +
            "Сл спасброска = 8 + ваш бонус мастерства + ваш модификатор Харизмы\n" +
            "\n" +
            "Модификатор броска атаки = ваш бонус мастерства + ваш модификатор Харизмы\n" +
            "Фокусировка заклинания\n" +
            "\n" +
            "Вы можете использовать магическую фокусировку в качестве заклинательной фокусировки для заклинаний колдуна.\n"
)

var otherwordlyPatron: AbilityNode = AbilityNode(
    name = "Потусторонний покровитель",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    getAlternatives = mutableMapOf(
        //Pair("first", { listOf(schoolOfDivination.name, schoolOfConjuration.name) })
    ),
    requirements = { true },
    description = "Вы заключаете сделку с потусторонним существом на ваш выбор. Подробности всех покровителей смотрите ниже. Ваш выбор определит умения, предоставляемые вам на 1-м, 6-м, 10-м и 14-м уровнях.\n"
)

var warlock1: AbilityNodeLevel = AbilityNodeLevel(
    name = "Колдун_1",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellsLevel += 1
        abilities.proficiencyBonus += 2
        abilities.hp += abilityToModifier(abilities.constitution) + 8
        abilities.characterClass = Classes.Warlock
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(classFeaturesWarlock.name) }),
        Pair("second", { listOf(spellCastingWarlock.name) }),
        Pair("third", { listOf(otherwordlyPatron.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "1-й уровень, способности колдуна",
    next_level = "Колдун_2",
)

var eldritchInvocations: AbilityNode = AbilityNode(
    name = "Таинственные воззвания",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { mapOfEldrichInvocations.keys.toList() }),
        Pair("second", { mapOfEldrichInvocations.keys.toList() })
    ),
    requirements = { true },
    description = "В процессе изучения оккультных знаний вы раскопали таинственные воззвания: фрагменты запрещенных знаний, которые даруют магические способности.\n" +
            "\n" +
            "Вы получаете два воззвания на свой выбор. Смотрите список воззваний. Получая новые уровни колдуна, вы получаете дополнительные воззвания на свой выбор, как показано в колонке «известные воззвания».\n" +
            "\n" +
            "Кроме того, когда вы получаете новый уровень этого класса, вы можете выбрать одно известное вам воззвание и заменить его другим, которое вы способны выучить на этом уровне.\n"
)

var newEldritchInvocation: AbilityNode = AbilityNode(
    name = "Дополнительное таинственное воззвание",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { mapOfEldrichInvocations.keys.toList() })
    ),
    requirements = { true },
    description = "Вы можете выбрать дополнительное таинственное воззвание\n"
)

var warlock2: AbilityNodeLevel = AbilityNodeLevel(
    name = "Колдун_2",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(eldritchInvocations.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "2-й уровень, способности колдуна",
    next_level = "Колдун_3",
)

var pactOfTheBlade: AbilityNode = AbilityNode(
    name = "Договор клинка",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Оружие договора",
                description = "Вы можете действием создать оружие договора в своей пустой руке. Вы сами выбираете форму этого рукопашного оружия каждый раз, когда создаёте. Вы получаете владение этим оружием, пока используете его. Оружие считается магическим при определении преодоления сопротивления и иммунитета от немагических атак и урона.\n" +
                        "\n" +
                        "Оружие договора исчезает, если оно в течении 1 минуты находится дальше 5 футов от вас. Оно также исчезает, если вы используете это умение еще раз, отзываете оружие (действие не требуется), или умираете. Вы можете трансформировать одно магическое оружие в своё оружие договора, проведя специальный ритуал, держа это оружие. Ритуал совершается 1 час, его можно провести во время короткого отдыха. Впоследствии вы можете отозвать оружие, помещая его между измерениями. Оно будет появляться в руке, когда вы будете в дальнейшем создавать оружие договора. Вы не можете сделать это с артефактом или разумным оружием. Оружие перестаёт быть оружием договора, когда вы умираете, выполняете часовой ритуал с другим оружием или когда вы исполните ритуал длиной в час для того, чтобы разорвать связь. Оружие материализуется у ваших ног, если в момент разрыва связи оно находилось между измерениями.\n",
                type = ActionType.Action
            )
        )
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    description = "Вы можете действием создать оружие договора в своей пустой руке. Вы сами выбираете форму этого рукопашного оружия каждый раз, когда создаёте. Вы получаете владение этим оружием, пока используете его. Оружие считается магическим при определении преодоления сопротивления и иммунитета от немагических атак и урона.\n" +
            "\n" +
            "Оружие договора исчезает, если оно в течении 1 минуты находится дальше 5 футов от вас. Оно также исчезает, если вы используете это умение еще раз, отзываете оружие (действие не требуется), или умираете. Вы можете трансформировать одно магическое оружие в своё оружие договора, проведя специальный ритуал, держа это оружие. Ритуал совершается 1 час, его можно провести во время короткого отдыха. Впоследствии вы можете отозвать оружие, помещая его между измерениями. Оно будет появляться в руке, когда вы будете в дальнейшем создавать оружие договора. Вы не можете сделать это с артефактом или разумным оружием. Оружие перестаёт быть оружием договора, когда вы умираете, выполняете часовой ритуал с другим оружием или когда вы исполните ритуал длиной в час для того, чтобы разорвать связь. Оружие материализуется у ваших ног, если в момент разрыва связи оно находилось между измерениями.\n"
)

var pactOfTheChain: AbilityNode = AbilityNode(
    name = "Договор цепи",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.spellsInfo.apply {
            if (!this.contains("Заклинания договора цепи")) {
                this["Заклинания договора цепи"] = CharacterSpells()
            }
            this["Заклинания договора цепи"]?.className = Classes.Warlock.className
            this["Заклинания договора цепи"]?.maxKnownSpellsCount = 0
            this["Заклинания договора цепи"]?.maxKnownCantripsCount = 0
            this["Заклинания договора цепи"]?.spellLists = SpellLists(
                knownSpells = mutableSetOf("Поиск фамильяра")
            )
        }
        abilities.additionalAbilities["Договор цепи"] = "Вы узнаёте заклинание поиск фамильяра [find familiar] и можете сотворять его как ритуал. Это заклинание не учитывается при подсчёте числа заклинаний, которые вы можете знать.\n" +
                "\n" +
                "Когда вы накладываете это заклинание, вы можете выбрать одну из обычных форм для вашего фамильяра, либо одну из особых форм: бес [imp], квазит [quasit], псевдодракон [pseudodragon] или спрайт [sprite].\n" +
                "\n" +
                "Кроме того, когда вы совершаете действие Атака, вы можете вместо одной своей атаки позволить атаковать один раз фамильяру. При этом он совершает свою атаку реакцией.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    description = "Вы узнаёте заклинание поиск фамильяра [find familiar] и можете сотворять его как ритуал. Это заклинание не учитывается при подсчёте числа заклинаний, которые вы можете знать.\n" +
            "\n" +
            "Когда вы накладываете это заклинание, вы можете выбрать одну из обычных форм для вашего фамильяра, либо одну из особых форм: бес [imp], квазит [quasit], псевдодракон [pseudodragon] или спрайт [sprite].\n" +
            "\n" +
            "Кроме того, когда вы совершаете действие Атака, вы можете вместо одной своей атаки позволить атаковать один раз фамильяру. При этом он совершает свою атаку реакцией.\n"
)

var pactOfTheTome: AbilityNode = AbilityNode(
    name = "Договор гримуара",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.spellsInfo.apply {
            if (!this.contains("Заклинания договора гримуара")) {
                this["Заклинания договора гримуара"] = CharacterSpells()
            }
            this["Заклинания договора гримуара"]?.className = Classes.Warlock.className
            this["Заклинания договора гримуара"]?.maxKnownSpellsCount = 0
            this["Заклинания договора гримуара"]?.maxKnownCantripsCount = 3
        }
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    description = "Ваш покровитель дарует вам гримуар, который называется «Книга теней». Когда вы получаете это умение, выберите 3 заговора из списков любых классов. Пока книга с вами, вы можете применять эти заговоры неограниченно. Они не учитываются при подсчёте максимального числа заговоров, которые вы можете знать, и считаются для вас заклинаниями колдуна.\n" +
            "\n" +
            "Если вы теряете книгу, вам нужно провести ритуал длительностью в 1 час, чтобы получить замену от своего покровителя. Вы можете провести этот ритуал во время короткого или продолжительного отдыха. Предыдущая книга при этом уничтожается. Книга обращается в прах при вашей смерти.\n"
)

var pactBoon: AbilityNode = AbilityNode(
    name = "Предмет договора",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(pactOfTheBlade.name, pactOfTheChain.name, pactOfTheTome.name) })
    ),
    requirements = { true },
    description = "Потусторонний покровитель дарует вам подарок за верную службу. Вы получаете одно из следующих умений на выбор\n"
)

var warlock3: AbilityNodeLevel = AbilityNodeLevel(
    name = "Колдун_3",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellsLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(pactBoon.name) })
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
        abilities.spellsLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities.proficiencyBonus += 1
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(newEldritchInvocation.name) })
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
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "6-й уровень, способности колдуна",
    next_level = "Колдун_7",
)

var warlock7: AbilityNodeLevel = AbilityNodeLevel(
    name = "Колдун_7",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellsLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(newEldritchInvocation.name) })
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
        abilities.spellsLevel += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities.proficiencyBonus += 1
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(newEldritchInvocation.name) })
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
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "10-й уровень, способности колдуна",
    next_level = "Колдун_11",
)

var mysticArcanum_6 = AbilityNode(
    name = "Таинственный арканум 6 уровня",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        if (!abilities.currentState.charges.contains("Таинственный арканум 6 уровня")) {
            abilities.currentState.charges["Таинственный арканум 6 уровня"] = ChargesCounter(
                current = 1,
                maximum = 1
            )
        }
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first") { spellist.filter { (it.level == 6) && (it.classes.contains(Classes.Warlock)) }.map { it.name } }
    ),
    requirements = { true },
    description = "Ваш покровитель дарует вам магический секрет, называемый арканумом. Выберите одно заклинание 6-го уровня из списка заклинаний колдуна в качестве арканума.\n" +
            "\n" +
            "Вы можете наложить это заклинание, не используя ячейку заклинаний. Вы должны окончить продолжительный отдых, чтобы сделать это еще раз.\n" +
            "\n" +
            "На следующих уровнях вы получаете новые заклинания, которые можно применить таким образом — одно 7-го уровня на 13-м уровне, одно 8-го уровня на 15-м уровне и одно 9-го уровня на 17-м уровне. После окончания продолжительного отдыха вы восстанавливаете все потраченные использования арканумов.\n",
    actionForChoice = mutableMapOf(
        Pair("first") { choice: String, abilities: CharacterInfo ->
            Log.d("test", choice)
            abilities.actionsList.add(
                Action(
                    name = "Таинственный арканум 6 уровня",
                    description = "Вы можете наложить заклинание " + choice + ", не используя ячейку заклинаний. Вы должны окончить продолжительный отдых, чтобы сделать это еще раз.\n",
                    type = ActionType.Additional,
                    relatedCharges = "Таинственный арканум 6 уровня"
                )
            )
            abilities
        }
    )
)

var warlock11: AbilityNodeLevel = AbilityNodeLevel(
    name = "Колдун_11",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(mysticArcanum_6.name) })
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
        Pair("second", { listOf(newEldritchInvocation.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "12-й уровень, способности колдуна",
    next_level = "Колдун_13",
)

var mysticArcanum_7 = AbilityNode(
    name = "Таинственный арканум 7 уровня",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        if (!abilities.currentState.charges.contains("Таинственный арканум 7 уровня")) {
            abilities.currentState.charges["Таинственный арканум 7 уровня"] = ChargesCounter(
                current = 1,
                maximum = 1
            )
        }
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first") { spellist.filter { (it.level == 7) && (it.classes.contains(Classes.Warlock)) }.map { it.name } }
    ),
    requirements = { true },
    description = "Ваш покровитель дарует вам магический секрет, называемый арканумом. Выберите одно заклинание 7-го уровня из списка заклинаний колдуна в качестве арканума.\n" +
            "\n" +
            "Вы можете наложить это заклинание, не используя ячейку заклинаний. Вы должны окончить продолжительный отдых, чтобы сделать это еще раз.\n",
    actionForChoice = mutableMapOf(
        Pair("first") { choice: String, abilities: CharacterInfo ->
            Log.d("test", choice)
            abilities.actionsList.add(
                Action(
                    name = "Таинственный арканум 7 уровня",
                    description = "Вы можете наложить заклинание " + choice + ", не используя ячейку заклинаний. Вы должны окончить продолжительный отдых, чтобы сделать это еще раз.\n",
                    type = ActionType.Additional,
                    relatedCharges = "Таинственный арканум 7 уровня"
                )
            )
            abilities
        }
    )
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
        Pair("first", { listOf(mysticArcanum_7.name) })
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
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "14-й уровень, способности колдуна",
    next_level = "Колдун_15",
)

var mysticArcanum_8 = AbilityNode(
    name = "Таинственный арканум 8 уровня",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        if (!abilities.currentState.charges.contains("Таинственный арканум 8 уровня")) {
            abilities.currentState.charges["Таинственный арканум 8 уровня"] = ChargesCounter(
                current = 1,
                maximum = 1
            )
        }
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first") { spellist.filter { (it.level == 8) && (it.classes.contains(Classes.Warlock)) }.map { it.name } }
    ),
    requirements = { true },
    description = "Ваш покровитель дарует вам магический секрет, называемый арканумом. Выберите одно заклинание 6-го уровня из списка заклинаний колдуна в качестве арканума.\n" +
            "\n" +
            "Вы можете наложить это заклинание, не используя ячейку заклинаний. Вы должны окончить продолжительный отдых, чтобы сделать это еще раз.\n",
    actionForChoice = mutableMapOf(
        Pair("first") { choice: String, abilities: CharacterInfo ->
            Log.d("test", choice)
            abilities.actionsList.add(
                Action(
                    name = "Таинственный арканум 8 уровня",
                    description = "Вы можете наложить заклинание " + choice + ", не используя ячейку заклинаний. Вы должны окончить продолжительный отдых, чтобы сделать это еще раз.\n",
                    type = ActionType.Additional,
                    relatedCharges = "Таинственный арканум 8 уровня"
                )
            )
            abilities
        }
    )
)

var warlock15: AbilityNodeLevel = AbilityNodeLevel(
    name = "Колдун_15",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(newEldritchInvocation.name) }),
        Pair("second", { listOf(mysticArcanum_8.name) })
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

var mysticArcanum_9 = AbilityNode(
    name = "Таинственный арканум 9 уровня",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        if (!abilities.currentState.charges.contains("Таинственный арканум 9 уровня")) {
            abilities.currentState.charges["Таинственный арканум 9 уровня"] = ChargesCounter(
                current = 1,
                maximum = 1
            )
        }
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first") { spellist.filter { (it.level == 9) && (it.classes.contains(Classes.Warlock)) }.map { it.name } }
    ),
    requirements = { true },
    description = "Ваш покровитель дарует вам магический секрет, называемый арканумом. Выберите одно заклинание 6-го уровня из списка заклинаний колдуна в качестве арканума.\n" +
            "\n" +
            "Вы можете наложить это заклинание, не используя ячейку заклинаний. Вы должны окончить продолжительный отдых, чтобы сделать это еще раз.\n",
    actionForChoice = mutableMapOf(
        Pair("first") { choice: String, abilities: CharacterInfo ->
            Log.d("test", choice)
            abilities.actionsList.add(
                Action(
                    name = "Таинственный арканум 9 уровня",
                    description = "Вы можете наложить заклинание " + choice + ", не используя ячейку заклинаний. Вы должны окончить продолжительный отдых, чтобы сделать это еще раз.\n",
                    type = ActionType.Additional,
                    relatedCharges = "Таинственный арканум 9 уровня"
                )
            )
            abilities
        }
    )
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
        Pair("first", { listOf(mysticArcanum_9.name) })
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
        Pair("first", { listOf(newEldritchInvocation.name) })
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

var edritchMaster: AbilityNode = AbilityNode(
    name = "Таинственный мастер",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        if (!abilities.currentState.charges.contains("Таинственный мастер")) {
            abilities.currentState.charges["Таинственный мастер"] = ChargesCounter(
                current = 1,
                maximum = 1
            )
        }
        abilities.actionsList.add(
            Action(
                name = "Отражение снарядов",
                description = "Вы можете обратиться к внутреннему резерву мистической силы, умоляя при этом покровителя восстановить потраченные ячейки заклинаний. Вам надо потратить 1 минуту, умоляя покровителя, чтобы восстановить все использованные ячейки заклинаний, дарованные умением «Магия договора». Вы должны закончить продолжительный отдых, чтобы применить это умение вновь.\n",
                type = ActionType.Long,
                relatedCharges = "Таинственный мастер"
            )
        )
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    description = "Вы можете обратиться к внутреннему резерву мистической силы, умоляя при этом покровителя восстановить потраченные ячейки заклинаний. Вам надо потратить 1 минуту, умоляя покровителя, чтобы восстановить все использованные ячейки заклинаний, дарованные умением «Магия договора». Вы должны закончить продолжительный отдых, чтобы применить это умение вновь.\n"
)

var warlock20: AbilityNodeLevel = AbilityNodeLevel(
    name = "Колдун_20",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(edritchMaster.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "20-й уровень, способности колдуна",
    next_level = null,
)

var mapOfWarlockAbilities: MutableMap<String, AbilityNode> = (mutableMapOf(
    Pair(classFeaturesWarlock.name, classFeaturesWarlock),
    Pair(spellCastingWarlock.name, spellCastingWarlock),
    Pair(otherwordlyPatron.name, otherwordlyPatron),
    Pair(warlock1.name, warlock1),
    Pair(eldritchInvocations.name, eldritchInvocations),
    Pair(newEldritchInvocation.name, newEldritchInvocation),
    Pair(warlock2.name, warlock2),
    Pair(pactOfTheBlade.name, pactOfTheBlade),
    Pair(pactOfTheChain.name, pactOfTheChain),
    Pair(pactOfTheTome.name, pactOfTheTome),
    Pair(pactBoon.name, pactBoon),
    Pair(warlock3.name, warlock3),
    Pair(warlock4.name, warlock4),
    Pair(warlock5.name, warlock5),
    Pair(warlock6.name, warlock6),
    Pair(warlock7.name, warlock7),
    Pair(warlock8.name, warlock8),
    Pair(warlock9.name, warlock9),
    Pair(warlock10.name, warlock10),
    Pair(mysticArcanum_6.name, mysticArcanum_6),
    Pair(warlock11.name, warlock11),
    Pair(warlock12.name, warlock12),
    Pair(mysticArcanum_7.name, mysticArcanum_7),
    Pair(warlock13.name, warlock13),
    Pair(warlock14.name, warlock14),
    Pair(mysticArcanum_8.name, mysticArcanum_8),
    Pair(warlock15.name, warlock15),
    Pair(warlock16.name, warlock16),
    Pair(mysticArcanum_9.name, mysticArcanum_9),
    Pair(warlock17.name, warlock17),
    Pair(warlock18.name, warlock18),
    Pair(warlock19.name, warlock19),
    Pair(edritchMaster.name, edritchMaster),
    Pair(warlock20.name, warlock20),
)
        + mapOfEldrichInvocations
        //+ mapOfArcaneTricksterArchetypeAbilities
        ).toMutableMap()