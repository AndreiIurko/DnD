package com.andreyyurko.dnd.data.abilities.races

import com.andreyyurko.dnd.data.abilities.other.mapOfLanguages
import com.andreyyurko.dnd.data.characterData.CharacterInfo
import com.andreyyurko.dnd.data.characterData.Languages
import com.andreyyurko.dnd.data.characterData.Priority
import com.andreyyurko.dnd.data.characterData.character.AbilityNode

val humanChooseLanguage = AbilityNode(
    name = "Выбор языка: человек",
    changesInCharacterInfo = { abilities: CharacterInfo -> abilities },
    alternatives = mutableMapOf(
        Pair("language", mapOfLanguages.keys.toList())
    ),
    requirements = { true },
    description = "Выберите любой язык:"
)

val humanAbilities = AbilityNode(
    name = "Способности человека",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.strength += 1
        abilities.dexterity += 1
        abilities.constitution += 1
        abilities.intelligence += 1
        abilities.wisdom += 1
        abilities.charisma += 1
        abilities.languageProficiency.add(Languages.Common)
        abilities.speed += 30
        abilities
    },
    alternatives = mutableMapOf(
        Pair("languageFirst", listOf(humanChooseLanguage.name))
    ),
    requirements = { true },
    description = "Увеличение характеристик. Значение всех ваших характеристик увеличивается на 1.\n" +
            "\n" +
            "Возраст. Люди становятся взрослыми в районе 20 лет, и живут менее столетия.\n" +
            "\n" +
            "Мировоззрение. Любое.\n" +
            "\n" +
            "Размер. Люди сильно различаются по размерам. Некоторые с трудом достигают 5 футов (152 сантиметров) ростом, тогда как другие имеют рост, превосходящий 6 футов (183 сантиметра). Вне зависимости от роста, ваш размер — Средний.\n" +
            "\n" +
            "Скорость. Ваша базовая скорость ходьбы составляет 30 футов.\n" +
            "\n" +
            "Языки. Вы можете говорить, читать и писать на Общем и еще одном языке на ваш выбор. Люди обычно изучают языки народов, с которыми имеют дело, включая редкие диалекты. Они любят разбавлять собственную речь словами, позаимствованными из других языков: орочьими ругательствами, эльфийскими музыкальными терминами, дварфскими военными командами.",
    priority = Priority.DoFirst
)

val human = AbilityNode(
    name = "Человек",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.race = "Человек"
        abilities
    },
    alternatives = mutableMapOf(
        Pair("abilities", listOf(humanAbilities.name))
    ),
    requirements = { true },
    description = "Человек",
)

val mapOfHumanAbilities = mutableMapOf(
    Pair(humanChooseLanguage.name, humanChooseLanguage),
    Pair(humanAbilities.name, humanAbilities),
    Pair(human.name, human)
)