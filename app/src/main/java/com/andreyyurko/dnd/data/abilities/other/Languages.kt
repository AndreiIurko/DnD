package com.andreyyurko.dnd.data.abilities.other

import com.andreyyurko.dnd.data.characterData.CharacterInfo
import com.andreyyurko.dnd.data.characterData.Languages
import com.andreyyurko.dnd.data.characterData.character.AbilityNode

var commonLanguage: AbilityNode = AbilityNode(
    name = "Общий язык",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.languageProficiency.add(Languages.Common)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        !abilities.languageProficiency.contains(Languages.Common)
    },
    description = "",
    isNeedsToBeShown = false
)

var elvishLanguage: AbilityNode = AbilityNode(
    name = "Эльфийский язык",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.languageProficiency.add(Languages.Elvish)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        !abilities.languageProficiency.contains(Languages.Elvish)
    },
    description = "",
    isNeedsToBeShown = false
)

var dwarvishLanguage: AbilityNode = AbilityNode(
    name = "Дварфийский язык",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.languageProficiency.add(Languages.Dwarvish)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        !abilities.languageProficiency.contains(Languages.Dwarvish)
    },
    description = "",
    isNeedsToBeShown = false
)

var giantLanguage: AbilityNode = AbilityNode(
    name = "Великаний язык",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.languageProficiency.add(Languages.Giant)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        !abilities.languageProficiency.contains(Languages.Giant)
    },
    description = "",
    isNeedsToBeShown = false
)

var gnomishLanguage: AbilityNode = AbilityNode(
    name = "Гномий язык",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.languageProficiency.add(Languages.Gnomish)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        !abilities.languageProficiency.contains(Languages.Gnomish)
    },
    description = "",
    isNeedsToBeShown = false
)

var goblinLanguage: AbilityNode = AbilityNode(
    name = "Гоблинский язык",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.languageProficiency.add(Languages.Goblin)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        !abilities.languageProficiency.contains(Languages.Goblin)
    },
    description = "",
    isNeedsToBeShown = false
)

var halflingLanguage: AbilityNode = AbilityNode(
    name = "Язык полуросликов",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.languageProficiency.add(Languages.Halfling)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        !abilities.languageProficiency.contains(Languages.Halfling)
    },
    description = "",
    isNeedsToBeShown = false
)

var orcLanguage: AbilityNode = AbilityNode(
    name = "Орочий язык",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.languageProficiency.add(Languages.Orc)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        !abilities.languageProficiency.contains(Languages.Orc)
    },
    description = "",
    isNeedsToBeShown = false
)

var abyssalLanguage: AbilityNode = AbilityNode(
    name = "Язык бездны",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.languageProficiency.add(Languages.Abyssal)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        !abilities.languageProficiency.contains(Languages.Abyssal)
    },
    description = "",
    isNeedsToBeShown = false
)

var celestialLanguage: AbilityNode = AbilityNode(
    name = "Неьесный язык",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.languageProficiency.add(Languages.Celestial)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        !abilities.languageProficiency.contains(Languages.Celestial)
    },
    description = "",
    isNeedsToBeShown = false
)

var draconicLanguage: AbilityNode = AbilityNode(
    name = "Драконий язык",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.languageProficiency.add(Languages.Draconic)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        !abilities.languageProficiency.contains(Languages.Draconic)
    },
    description = "",
    isNeedsToBeShown = false
)

var deepSpeechLanguage: AbilityNode = AbilityNode(
    name = "Глубинная речь",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.languageProficiency.add(Languages.DeepSpeech)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        !abilities.languageProficiency.contains(Languages.DeepSpeech)
    },
    description = "",
    isNeedsToBeShown = false
)

var infernalLanguage: AbilityNode = AbilityNode(
    name = "Инфернальный язык",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.languageProficiency.add(Languages.Infernal)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        !abilities.languageProficiency.contains(Languages.Infernal)
    },
    description = "",
    isNeedsToBeShown = false
)

var primordialLanguage: AbilityNode = AbilityNode(
    name = "Первичный язык",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.languageProficiency.add(Languages.Primordial)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        !abilities.languageProficiency.contains(Languages.Primordial)
    },
    description = "",
    isNeedsToBeShown = false
)

var sylvanLanguage: AbilityNode = AbilityNode(
    name = "Сильван",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.languageProficiency.add(Languages.Sylvan)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        !abilities.languageProficiency.contains(Languages.Sylvan)
    },
    description = "",
    isNeedsToBeShown = false
)

var undercommonLanguage: AbilityNode = AbilityNode(
    name = "Подземный язык",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.languageProficiency.add(Languages.Undercommon)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        !abilities.languageProficiency.contains(Languages.Undercommon)
    },
    description = "",
    isNeedsToBeShown = false
)

var mapOfLanguages = mutableMapOf(
    Pair(commonLanguage.name, commonLanguage),
    Pair(elvishLanguage.name, elvishLanguage),
    Pair(dwarvishLanguage.name, dwarvishLanguage),
    Pair(giantLanguage.name, giantLanguage),
    Pair(gnomishLanguage.name, gnomishLanguage),
    Pair(goblinLanguage.name, goblinLanguage),
    Pair(halflingLanguage.name, halflingLanguage),
    Pair(orcLanguage.name, orcLanguage),
    Pair(abyssalLanguage.name, abyssalLanguage),
    Pair(celestialLanguage.name, celestialLanguage),
    Pair(draconicLanguage.name, draconicLanguage),
    Pair(deepSpeechLanguage.name, deepSpeechLanguage),
    Pair(infernalLanguage.name, infernalLanguage),
    Pair(primordialLanguage.name, primordialLanguage),
    Pair(sylvanLanguage.name, sylvanLanguage),
    Pair(undercommonLanguage.name, undercommonLanguage)
)