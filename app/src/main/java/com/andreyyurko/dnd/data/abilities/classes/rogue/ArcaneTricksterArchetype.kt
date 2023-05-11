package com.andreyyurko.dnd.data.abilities.classes.rogue

import com.andreyyurko.dnd.data.characterData.*
import com.andreyyurko.dnd.data.characterData.character.AbilityNode
import com.andreyyurko.dnd.data.spells.CharacterSpells
import com.andreyyurko.dnd.data.spells.SpellLists

var spellCastingArcaneTrickster: AbilityNode = AbilityNode(
    name = "Мистический ловкач: использование заклинаний",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        var knownSpells = 3
        if (abilities.level >= 4) knownSpells = 4
        if (abilities.level >= 7) knownSpells = 5
        if (abilities.level >= 8) knownSpells = 6
        if (abilities.level >= 10) knownSpells = 7
        if (abilities.level >= 11) knownSpells = 8
        if (abilities.level >= 13) knownSpells = 9
        if (abilities.level >= 14) knownSpells = 10
        if (abilities.level >= 16) knownSpells = 11
        if (abilities.level >= 19) knownSpells = 12
        if (abilities.level >= 20) knownSpells = 13
        abilities.spellsInfo.apply {
            if (!this.contains("Заклинания подкласса")) {
                this["Заклинания подкласса"] = CharacterSpells()
            }
            this["Заклинания подкласса"]?.className = Classes.Wizard.className
            this["Заклинания подкласса"]?.maxKnownSpellsCount = knownSpells
            this["Заклинания подкласса"]?.maxKnownCantripsCount = 2 + (abilities.level + 1) / 11
        }
        abilities.spellsInfo["Волшебная рука мистического ловкача"] = CharacterSpells(
            className = Classes.Wizard.className,
            maxKnownSpellsCount = 0,
            maxKnownCantripsCount = 0,
            spellLists = SpellLists(
                knownSpells = mutableSetOf("Волшебная рука")
            )
        )
        abilities.spellCasterLevel += abilities.level/3
        abilities.spellsLevel = (abilities.level/3 + 1) / 2
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 3 && abilities.characterClass == Classes.Rogue
    },
    description = "Вы получаете способность накладывать заклинания.\n" +
            "\n" +
            "Заговоры. Вы знаете три заговора: волшебная рука [mage hand] и два других заговора по своему выбору из списка заклинаний волшебника. Вы узнаёте ещё один заговор волшебника на 10-м уровне.\n" +
            "\n" +
            "Ячейки заклинаний. В приведённой ниже таблице показано, сколько у вас есть ячеек заклинаний для сотворения заклинаний волшебника для накладывания заклинаний 1-го уровня и выше. Для накладывания одного из этих заклинаний вы должны потрать ячейку заклинаний с уровнем не ниже уровня заклинания. Вы восстанавливаете все потраченные ячейки заклинаний, когда заканчиваете продолжительный отдых.\n" +
            "\n" +
            "Например, если вы знаете заклинание 1-го уровня очарование личности [charm person] и у вас есть ячейки заклинания 1-го и 2-го уровня, вы можете наложить это заклинание, используя любую из этих ячеек.\n" +
            "\n" +
            "Известные заклинания первого и более высоких уровней. Вы знаете три заклинания волшебника 1-го уровня по своему выбору, два из которых должны быть выбраны из школ Иллюзий или Очарования.\n" +
            "\n" +
            "Колонка «известные заклинания» показывает, когда вы сможете выучить новые заклинания волшебника 1-го уровня и выше. Все эти заклинания должны принадлежать школе Иллюзии или Очарования, и должны быть того уровня, для которого у вас есть ячейки заклинаний. Например, когда вы получите 7-й уровень в этом классе, вы можете узнать одно новое заклинание 1-го или 2-го уровня. Заклинания, которые вы узнаёте на 8-м, 14-м и 20-м уровнях могут принадлежать любой школе магии. Каждый раз, когда вы получаете уровень в этом классе, вы можете заменить одно известное заклинание волшебника другим заклинанием по своему выбору из списка заклинаний волшебника. Уровень нового заклинания не должен превышать уровень самой большой ячейки заклинаний, и оно должно принадлежать школе Иллюзий или Очарования, если только вы не заменяете заклинание, полученное на 3, 8, 14 или 20 уровне из любой школы магии.\n" +
            "\n" +
            "Базовая характеристика заклинаний. При накладывании заклинаний волшебника вы используете Интеллект, так как вы узнаёте их с помощью специальных исследований и запоминания. Вы используете Интеллект в случаях, когда заклинание ссылается на базовую характеристику. Кроме того, вы используете модификатор Интеллекта при определении Сл спасбросков от ваших заклинаний волшебника, и при броске атаки заклинаниями.\n"
)

var mageHandLegerdemain = AbilityNode(
    name = "Улучшенная магическая рука",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Улучшенная магическая рука"] =
            "Когда вы накладываете заклинание волшебная рука [mage hand], вы можете создать руку невидимой, и совершать ею следующие дополнительные действия:\n" +
                    "\n" +
                    "    Вы можете положить один предмет, удерживаемый рукой, в контейнер, носимый или несомый другим существом.\n" +
                    "    Вы можете достать предмет из контейнера, носимого или несомого другим существом.\n" +
                    "    Вы можете использовать воровские инструменты для вскрытия замков и обезвреживания ловушек в дистанции этого заклинания. \n" +
                    "\n" +
                    "Вы можете выполнить одно из этих действий, не будучи замеченным, если успешно совершите проверку Ловкости (Ловкость рук) против проверки Мудрости (Восприятие) существа.\n" +
                    "Кроме того, вы можете использовать бонусное действие, предоставленное умением «Хитрое действие», для управления рукой.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 3 && abilities.characterClass == Classes.Rogue
    },
    description = "Когда вы накладываете заклинание волшебная рука [mage hand], вы можете создать руку невидимой, и совершать ею следующие дополнительные действия:\n" +
            "\n" +
            "    Вы можете положить один предмет, удерживаемый рукой, в контейнер, носимый или несомый другим существом.\n" +
            "    Вы можете достать предмет из контейнера, носимого или несомого другим существом.\n" +
            "    Вы можете использовать воровские инструменты для вскрытия замков и обезвреживания ловушек в дистанции этого заклинания. \n" +
            "\n" +
            "Вы можете выполнить одно из этих действий, не будучи замеченным, если успешно совершите проверку Ловкости (Ловкость рук) против проверки Мудрости (Восприятие) существа.\n" +
            "Кроме того, вы можете использовать бонусное действие, предоставленное умением «Хитрое действие», для управления рукой.\n"
)

var magicalAmbush = AbilityNode(
    name = "Магическая засада",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Магическая засада"] =
            "Если вы накладываете заклинание на существо, от которого скрыты, существо в этом ходу совершает с помехой спасброски от этого заклинания.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 9 && abilities.characterClass == Classes.Rogue
    },
    description = "Если вы накладываете заклинание на существо, от которого скрыты, существо в этом ходу совершает с помехой спасброски от этого заклинания.\n"
)

var versatileTrickster = AbilityNode(
    name = "Многогранный ловкач",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Многогранный ловкач",
                description = "Вы получаете возможность отвлекать цель своей волшебной рукой. В свой ход вы бонусным действием можете обозначить существо в пределах 5 футов от руки, наложенной этим заклинанием. Если вы так делаете, то до конца этого хода совершаете броски атаки по этому существу с преимуществом.\n",
                type = ActionType.Bonus
            )
        )
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 13 && abilities.characterClass == Classes.Rogue
    },
    description = "Вы получаете возможность отвлекать цель своей волшебной рукой. В свой ход вы бонусным действием можете обозначить существо в пределах 5 футов от руки, наложенной этим заклинанием. Если вы так делаете, то до конца этого хода совершаете броски атаки по этому существу с преимуществом.\n"
)

var spellThief = AbilityNode(
    name = "Вор заклинаний",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        if (!abilities.currentState.charges.contains("Вор заклинаний")) {
            abilities.currentState.charges["Вор заклинаний"] = ChargesCounter(
                current = 1,
                maximum = 1
            )
        }
        abilities.actionsList.add(
            Action(
                name = "Вор заклинаний",
                description = "Вы получаете способность магически воровать у другого колдующего знания о том, как накладывать заклинание.\n" +
                        "\n" +
                        "Сразу после накладывания заклинания существом, целью которого являетесь вы, или же если вы попали в область действия этого заклинания, вы можете реакцией заставить существо совершить спасбросок его базовой характеристики. Сл равна вашей Сл спасброска заклинания. Если спасбросок провален, вы отрицаете эффект заклинания на себе, и крадёте знание этого заклинания, если оно как минимум 1 уровня, и вы можете накладывать заклинания этого уровня (оно не обязано быть заклинанием волшебника). Вы знаете это заклинание в течение следующих 8 часов и можете его накладывать, используя свои ячейки заклинаний. Существо, у которого вы украли заклинание, не может его накладывать на протяжении 8 часов.\n" +
                        "\n" +
                        "Использовав это умение, вы не можете использовать его повторно до окончания продолжительного отдыха.\n",
                type = ActionType.Bonus,
                relatedCharges = "Вор заклинаний"
            )
        )
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 17 && abilities.characterClass == Classes.Rogue
    },
    description = "Вы получаете способность магически воровать у другого колдующего знания о том, как накладывать заклинание.\n" +
            "\n" +
            "Сразу после накладывания заклинания существом, целью которого являетесь вы, или же если вы попали в область действия этого заклинания, вы можете реакцией заставить существо совершить спасбросок его базовой характеристики. Сл равна вашей Сл спасброска заклинания. Если спасбросок провален, вы отрицаете эффект заклинания на себе, и крадёте знание этого заклинания, если оно как минимум 1 уровня, и вы можете накладывать заклинания этого уровня (оно не обязано быть заклинанием волшебника). Вы знаете это заклинание в течение следующих 8 часов и можете его накладывать, используя свои ячейки заклинаний. Существо, у которого вы украли заклинание, не может его накладывать на протяжении 8 часов.\n" +
            "\n" +
            "Использовав это умение, вы не можете использовать его повторно до окончания продолжительного отдыха.\n"
)

var arcaneTrickster = AbilityNode(
    name = "Мистический ловкач",
    changesInCharacterInfo = { abilities: CharacterInfo -> abilities },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(spellCastingArcaneTrickster.name) }),
        Pair("second", { listOf(mageHandLegerdemain.name) }),
        Pair("third", { listOf(magicalAmbush.name) }),
        Pair("fourth", { listOf(versatileTrickster.name) }),
        Pair("fifth", { listOf(spellThief.name) })
    ),
    requirements = { true },
    description = "",
    isNeedsToBeShown = false
)

var mapOfArcaneTricksterArchetypeAbilities: MutableMap<String, AbilityNode> = mutableMapOf(
    Pair(spellCastingArcaneTrickster.name, spellCastingArcaneTrickster),
    Pair(mageHandLegerdemain.name, mageHandLegerdemain),
    Pair(magicalAmbush.name, magicalAmbush),
    Pair(versatileTrickster.name, versatileTrickster),
    Pair(spellThief.name, spellThief),
    Pair(arcaneTrickster.name, arcaneTrickster),
)