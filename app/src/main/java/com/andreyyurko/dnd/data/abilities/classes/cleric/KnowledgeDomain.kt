package com.andreyyurko.dnd.data.abilities.classes.cleric

import com.andreyyurko.dnd.data.abilities.other.*
import com.andreyyurko.dnd.data.characterData.Action
import com.andreyyurko.dnd.data.characterData.ActionType
import com.andreyyurko.dnd.data.characterData.CharacterInfo
import com.andreyyurko.dnd.data.characterData.Classes
import com.andreyyurko.dnd.data.characterData.character.AbilityNode
import com.andreyyurko.dnd.data.spells.CharacterSpells
import com.andreyyurko.dnd.data.spells.SpellLists

var blessingsOfKnowledge = AbilityNode(
    name = "Благословение знаний",
    changesInCharacterInfo = { abilities: CharacterInfo -> abilities },
    alternatives = mutableMapOf(
        Pair("language1", mapOfLanguages.keys.toList()),
        Pair("language2", mapOfLanguages.keys.toList()),
        Pair(
            "expertise1",
            listOf(
                historySkillAndExpertise.name,
                arcanaSkillAndExpertise.name,
                natureSkillAndExpertise.name,
                religionSkillAndExpertise.name,
            )
        ),
        Pair(
            "expertise2",
            listOf(
                historySkillAndExpertise.name,
                arcanaSkillAndExpertise.name,
                natureSkillAndExpertise.name,
                religionSkillAndExpertise.name,
            )
        )
    ),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 1 && abilities.characterClass == Classes.Cleric
    },
    description = "Вы можете выучить два языка на свой выбор. Вы также получаете владение двумя навыками из следующего списка: История, Магия, Природа, Религия.\n" +
            "\n" +
            "Ваш бонус мастерства удваивается для всех проверок характеристик при использовании этих навыков.\n"
)

var KnowledgeDomainSpells = AbilityNode(
    name = "Заклинания домена знаний",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        val additionalSpellList = mutableSetOf<String>()
        if (abilities.level >= 1) {
            additionalSpellList.add("Опознание")
            additionalSpellList.add("Приказ")
        }
        if (abilities.level >= 3) {
            additionalSpellList.add("Внушение")
            additionalSpellList.add("Гадание")
        }
        if (abilities.level >= 5) {
            additionalSpellList.add("Необнаружимость")
            additionalSpellList.add("Разговор с мёртвыми")
        }
        if (abilities.level >= 7) {
            additionalSpellList.add("Магический глаз")
            additionalSpellList.add("Смятение")
        }
        if (abilities.level >= 9) {
            additionalSpellList.add("Знание легенд")
            additionalSpellList.add("Наблюдение")
        }
        abilities.spellsInfo["Заклинания домена знаний"] = CharacterSpells(
            className = abilities.characterClass.className,
            maxKnownSpellsCount = 0,
            maxKnownCantripsCount = 0,
            spellLists = SpellLists(
                knownSpells = additionalSpellList
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 1 && abilities.characterClass == Classes.Cleric
    },
    description =
    "Уровень жреца | Заклинания\n" +
            "       1      | опознание, приказ\n" +
            "       3      | внушение, гадание\n" +
            "       5      | необнаружимость, разговор с мёртвыми\n" +
            "       7      | магический граз, смятение\n" +
            "       9      | знание легенд, наблюдение\n"
)

var channelDivinityKnowledgeOfTheAges: AbilityNode = AbilityNode(
    name = "Божественный канал: знания веков",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Божественный канал: Знания веков",
                description = "Вы можете использовать «Божественный канал», чтобы получить доступ к источнику знаний. Вы действием выбираете навык или инструмент. На 10 минут вы осваиваете владение выбранным навыком или инструментом.\n",
                type = ActionType.Action,
                relatedCharges = "Божественный канал"
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 2 && abilities.characterClass == Classes.Cleric
    },
    add_requirements = listOf(),
    description = "Вы можете использовать «Божественный канал», чтобы получить доступ к источнику знаний. Вы действием выбираете навык или инструмент. На 10 минут вы осваиваете владение выбранным навыком или инструментом.\n",
)

var channelDivinityReadThoughts: AbilityNode = AbilityNode(
    name = "Божественный канал: чтение мыслей",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Божественный канал: Чтение мыслей",
                description = "Вы можете использовать свой «Божественный канал», чтобы читать мысли существ. Затем вы можете использовать доступ к разуму существа, чтобы командовать им.\n" +
                        "\n" +
                        "Выберите действием одно существо, которое вы можете видеть, находящееся в пределах 60 футов от вас. Это существо должно совершить спасбросок Мудрости. Если существо преуспело, вы не можете использовать это умение на нём, пока не завершите продолжительный отдых. Если существо проваливает спасбросок, вы можете прочитать его поверхностные мысли (то, что у него на уме, его текущие эмоции и то, о чём оно активно думает), когда оно находится в пределах 60 футов от вас. Этот эффект длится в течение 1 минуты.\n" +
                        "\n" +
                        "В течение этого времени вы можете действием окончить этот эффект, накладывая на существо заклинание внушение [suggestion], не тратя ячейку заклинания. Цель автоматически проваливает спасбросок от этого заклинания.\n",
                type = ActionType.Action,
                relatedCharges = "Божественный канал"
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 6 && abilities.characterClass == Classes.Cleric
    },
    add_requirements = listOf(),
    description = "Вы можете использовать свой «Божественный канал», чтобы читать мысли существ. Затем вы можете использовать доступ к разуму существа, чтобы командовать им.\n" +
            "\n" +
            "Выберите действием одно существо, которое вы можете видеть, находящееся в пределах 60 футов от вас. Это существо должно совершить спасбросок Мудрости. Если существо преуспело, вы не можете использовать это умение на нём, пока не завершите продолжительный отдых. Если существо проваливает спасбросок, вы можете прочитать его поверхностные мысли (то, что у него на уме, его текущие эмоции и то, о чём оно активно думает), когда оно находится в пределах 60 футов от вас. Этот эффект длится в течение 1 минуты.\n" +
            "\n" +
            "В течение этого времени вы можете действием окончить этот эффект, накладывая на существо заклинание внушение [suggestion], не тратя ячейку заклинания. Цель автоматически проваливает спасбросок от этого заклинания.\n",
)

var potentSpellcasting = AbilityNode(
    name = "Могущественное колдовство",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Могущественное колдовство (жрец)"] =
            "Вы добавляете модификатор Мудрости к урону, который причиняете заговорами жреца.\n"
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 8 && abilities.characterClass == Classes.Cleric
    },
    description = "Вы добавляете модификатор Мудрости к урону, который причиняете заговорами жреца.\n"
)

var visionsOfThePast = AbilityNode(
    name = "Видения прошлого",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Видения прошлого",
                description = "Вы можете вызывать видения прошлого, связанные с предметом, который вы держите, или находящимся вокруг вас окружением. Вы проводите не менее 1 минуты медитируя и молясь, а затем получаете призрачные туманные видения последних событий. Вы можете медитировать таким образом количество минут, равное вашему значению Мудрости, и должны поддерживать концентрацию в течение этого времени, как если бы вы накладывали заклинание.\n" +
                        "\n" +
                        "После того как вы использовали это умение, вы не можете его использовать повторно, пока не окончите короткий или продолжительный отдых.\n" +
                        "\n" +
                        "Чтение предмета. Удерживая предмет во время медитации, вы получаете видение предыдущего владельца этого предмета. После медитации в течение 1 минуты вы узнаёте, как владелец приобрёл и потерял предмет, а также самое последнее значимое событие с участием предмета и его владельца. Если предмет принадлежал другому существу в недавнем прошлом (в пределах количества дней, равного значению вашей Мудрости), вы можете потратить 1 дополнительную минуту на каждого владельца, чтобы узнать ту же информацию о нём.\n" +
                        "\n" +
                        "Чтение окрестностей. Пока вы медитируете, вы наблюдаете видения последних событий, произошедших в непосредственной близости (комната, улица, туннель, поляна и тому подобное в пределах куба с длиной ребра 50 футов), на протяжении количества прошедших дней, равного значению вашей Мудрости. За каждую минуту медитации вы узнаёте об одном значимом событии, начиная с самого последнего. Значимые события обычно связаны с сильными эмоциями, например, битвами и предательствами, свадьбами и убийствами, родами и похоронами. Однако они могут также включать в себя более обыденные события, которые, тем не менее, важны в текущей ситуации.\n",
                type = ActionType.Long,
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 17 && abilities.characterClass == Classes.Cleric
    },
    description = "Вы можете вызывать видения прошлого, связанные с предметом, который вы держите, или находящимся вокруг вас окружением. Вы проводите не менее 1 минуты медитируя и молясь, а затем получаете призрачные туманные видения последних событий. Вы можете медитировать таким образом количество минут, равное вашему значению Мудрости, и должны поддерживать концентрацию в течение этого времени, как если бы вы накладывали заклинание.\n" +
            "\n" +
            "После того как вы использовали это умение, вы не можете его использовать повторно, пока не окончите короткий или продолжительный отдых.\n" +
            "\n" +
            "Чтение предмета. Удерживая предмет во время медитации, вы получаете видение предыдущего владельца этого предмета. После медитации в течение 1 минуты вы узнаёте, как владелец приобрёл и потерял предмет, а также самое последнее значимое событие с участием предмета и его владельца. Если предмет принадлежал другому существу в недавнем прошлом (в пределах количества дней, равного значению вашей Мудрости), вы можете потратить 1 дополнительную минуту на каждого владельца, чтобы узнать ту же информацию о нём.\n" +
            "\n" +
            "Чтение окрестностей. Пока вы медитируете, вы наблюдаете видения последних событий, произошедших в непосредственной близости (комната, улица, туннель, поляна и тому подобное в пределах куба с длиной ребра 50 футов), на протяжении количества прошедших дней, равного значению вашей Мудрости. За каждую минуту медитации вы узнаёте об одном значимом событии, начиная с самого последнего. Значимые события обычно связаны с сильными эмоциями, например, битвами и предательствами, свадьбами и убийствами, родами и похоронами. Однако они могут также включать в себя более обыденные события, которые, тем не менее, важны в текущей ситуации.\n"
)

var KnowledgeDomain = AbilityNode(
    name = "Домен знаний",
    changesInCharacterInfo = { abilities: CharacterInfo -> abilities },
    alternatives = mutableMapOf(
        Pair("first", listOf(blessingsOfKnowledge.name)),
        Pair("second", listOf(KnowledgeDomainSpells.name)),
        Pair("third", listOf(channelDivinityKnowledgeOfTheAges.name)),
        Pair("fourth", listOf(channelDivinityReadThoughts.name)),
        Pair("fifth", listOf(potentSpellcasting.name)),
        Pair("sizth", listOf(visionsOfThePast.name))
    ),
    requirements = { true },
    description = "",
    isNeedsToBeShown = false
)

var mapOfKnowledgeDomainAbilities: MutableMap<String, AbilityNode> = mutableMapOf(
    Pair(blessingsOfKnowledge.name, blessingsOfKnowledge),
    Pair(KnowledgeDomainSpells.name, KnowledgeDomainSpells),
    Pair(channelDivinityKnowledgeOfTheAges.name, channelDivinityKnowledgeOfTheAges),
    Pair(channelDivinityReadThoughts.name, channelDivinityReadThoughts),
    Pair(potentSpellcasting.name, potentSpellcasting),
    Pair(visionsOfThePast.name, visionsOfThePast),
    Pair(KnowledgeDomain.name, KnowledgeDomain),
)