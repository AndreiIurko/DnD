package com.andreyyurko.dnd.data.abilities.classes.warlock

import com.andreyyurko.dnd.data.characterData.CharacterInfo
import com.andreyyurko.dnd.data.characterData.character.AbilityNode
import com.andreyyurko.dnd.data.spells.CharacterSpells
import com.andreyyurko.dnd.data.spells.SpellLists

var agonizingBlast = AbilityNode(
    name = "Мучительный взрыв",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Мучительный взрыв"] =
            "Когда вы накладываете мистический заряд, добавьте модификатор Харизмы к урону, причиняемому при попадании.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.additionalAbilities.contains("Мучительный взрыв")
    },
    description = "Когда вы накладываете мистический заряд, добавьте модификатор Харизмы к урону, причиняемому при попадании.\n",
)

var armorOfShadows = AbilityNode(
    name = "Доспех теней",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Доспех теней"] =
            "Вы можете неограниченно накладывать на себя заклинание доспехи мага, не тратя ячейки заклинаний и материальные компоненты.\n"
        abilities.spellsInfo["Заклинание доспеха теней"] = CharacterSpells(
            className = abilities.characterClass.className,
            maxKnownSpellsCount = 0,
            maxKnownCantripsCount = 0,
            spellLists = SpellLists(
                knownSpells = mutableSetOf("Доспехи мага")
            )
        )
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.additionalAbilities.contains("Доспех теней")
    },
    description = "Вы можете неограниченно накладывать на себя заклинание доспехи мага, не тратя ячейки заклинаний и материальные компоненты.\n",
)

var beastSpeech = AbilityNode(
    name = "Животная речь",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Животная речь"] =
            "Вы можете неограниченно накладывать заклинание разговор с животными, не тратя ячейки заклинаний.\n"
        abilities.spellsInfo["Заклинание животной речи"] = CharacterSpells(
            className = abilities.characterClass.className,
            maxKnownSpellsCount = 0,
            maxKnownCantripsCount = 0,
            spellLists = SpellLists(
                knownSpells = mutableSetOf("Разговор с животными")
            )
        )
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.additionalAbilities.contains("Животная речь")
    },
    description = "Вы можете неограниченно накладывать заклинание разговор с животными, не тратя ячейки заклинаний.\n",
)

var beguilingInfluence = AbilityNode(
    name = "Обманчивое влияние",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Обманчивое влияние"] =
            "Вы получаете владение навыками Обман и Убеждение.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.additionalAbilities.contains("Обманчивое влияние")
    },
    description = "Вы получаете владение навыками Обман и Убеждение.\n",
)

var devilsSight = AbilityNode(
    name = "Дьявольский взор",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Дьявольский взор"] =
            "Вы можете нормально видеть в темноте, как магической, так и немагической, на расстоянии 120 футов.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.additionalAbilities.contains("Дьявольский взор")
    },
    description = "Вы можете нормально видеть в темноте, как магической, так и немагической, на расстоянии 120 футов.\n",
)

var eldritchMind = AbilityNode(
    name = "Мистический разум",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Мистический разум"] =
            "Вы совершаете с преимуществом спасброски Телосложения для поддержания концентрации на вашем заклинании.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.additionalAbilities.contains("Мистический разум")
    },
    description = "Вы совершаете с преимуществом спасброски Телосложения для поддержания концентрации на вашем заклинании.\n",
)

var eldritchSight = AbilityNode(
    name = "Таинственный взгляд",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Таинственный взгляд"] =
            "Вы можете неограниченно накладывать заклинание обнаружение магии, не тратя ячейки заклинаний.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.additionalAbilities.contains("Таинственный взгляд")
    },
    description = "Вы можете неограниченно накладывать заклинание обнаружение магии, не тратя ячейки заклинаний.\n",
)

var eldritchSpear = AbilityNode(
    name = "Мистическое копьё",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Мистическое копьё"] =
            "Когда вы накладываете мистический заряд, его дистанция равна 300 футам.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.additionalAbilities.contains("Мистическое копьё")
    },
    description = "Когда вы накладываете мистический заряд, его дистанция равна 300 футам.\n",
)

var eyesOfTheRuneKeeper = AbilityNode(
    name = "Глаза хранителя рун",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Глаза хранителя рун"] = "Вы можете читать любые письмена.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.additionalAbilities.contains("Глаза хранителя рун")
    },
    description = "Вы можете читать любые письмена.\n",
)

var fiendishVigor = AbilityNode(
    name = "Мощь исчадия",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Мощь исчадия"] =
            "Вы можете неограниченно накладывать на себя заклинание псевдожизнь как заклинание 1-го уровня, не тратя ячейки заклинаний и материальные компоненты.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.additionalAbilities.contains("Мощь исчадия")
    },
    description = "Вы можете неограниченно накладывать на себя заклинание псевдожизнь как заклинание 1-го уровня, не тратя ячейки заклинаний и материальные компоненты.\n",
)

var gazeOfTwoMinds = AbilityNode(
    name = "Взор двух умов",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Взор двух умов"] =
            "Вы можете действием коснуться согласного гуманоида и до конца своего следующего хода воспринимать всё его чувствами. Пока существо находится на том же плане существования, что и вы, вы можете в последующие ходы действием продлевать эту связь до конца своего следующего хода. При восприятии чувствами другого существа, вы получаете все преимущества от особых чувств, которыми обладает это существо, а сами при этом слепы и глухи.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.additionalAbilities.contains("Взор двух умов")
    },
    description = "Вы можете действием коснуться согласного гуманоида и до конца своего следующего хода воспринимать всё его чувствами. Пока существо находится на том же плане существования, что и вы, вы можете в последующие ходы действием продлевать эту связь до конца своего следующего хода. При восприятии чувствами другого существа, вы получаете все преимущества от особых чувств, которыми обладает это существо, а сами при этом слепы и глухи.\n",
)

var graspOfHadar = AbilityNode(
    name = "Хватка Хадара",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Хватка Хадара"] =
            "Один раз на каждом из ваших ходов, когда вы попадаете по существу мистическим зарядом [eldritch blast], вы можете переместить это существо по прямой линии на 10 футов ближе к вам.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.additionalAbilities.contains("Хватка Хадара")
    },
    description = "Один раз на каждом из ваших ходов, когда вы попадаете по существу мистическим зарядом [eldritch blast], вы можете переместить это существо по прямой линии на 10 футов ближе к вам.\n",
)

var mapOfEldrichInvocations = mutableMapOf(
    Pair(agonizingBlast.name, agonizingBlast),
    Pair(armorOfShadows.name, armorOfShadows),
    Pair(beastSpeech.name, beastSpeech),
    Pair(beguilingInfluence.name, beguilingInfluence),
    Pair(devilsSight.name, devilsSight),
    Pair(eldritchMind.name, eldritchMind),
    Pair(eldritchSight.name, eldritchSight),
    Pair(eldritchSpear.name, eldritchSpear),
    Pair(eyesOfTheRuneKeeper.name, eyesOfTheRuneKeeper),
    Pair(fiendishVigor.name, fiendishVigor),
    Pair(gazeOfTwoMinds.name, gazeOfTwoMinds),
    Pair(graspOfHadar.name, graspOfHadar),
)