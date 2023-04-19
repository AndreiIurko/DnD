package com.andreyyurko.dnd.data.abilities.classes.fighter

import com.andreyyurko.dnd.data.abilities.other.mapOfFightingStyles
import com.andreyyurko.dnd.data.characterData.Ability
import com.andreyyurko.dnd.data.characterData.CharacterInfo
import com.andreyyurko.dnd.data.characterData.Classes
import com.andreyyurko.dnd.data.characterData.character.AbilityNode

var improvedCritical = AbilityNode(
    name = "Улучшенные критические попадания",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Улучшенные критические попадания"] = "Ваши атаки оружием совершают критическое попадание при выпадении «19» или «20» на кости атаки."
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        abilities.level >= 3 && abilities.characterClass == Classes.Fighter
    },
    description = "Ваши атаки оружием совершают критическое попадание при выпадении «19» или «20» на кости атаки.",
)

var remarkableAthlete = AbilityNode(
    name = "Выдающийся атлет",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.halfProfSet.add(Ability.Strength)
        abilities.halfProfSet.add(Ability.Dexterity)
        abilities.halfProfSet.add(Ability.Constitution)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        abilities.level >= 7 && abilities.characterClass == Classes.Fighter
    },
    description = "Вы можете добавлять половину бонуса мастерства, округлённую в большую сторону, ко всем проверкам Силы, Ловкости или Телосложения, куда этот бонус еще не включён.\n" +
            "\n" +
            "Кроме того, если вы совершаете прыжок в длину с разбега, дальность прыжка увеличивается на количество футов, равное модификатору Силы."
)

var additionalFightingStyle = AbilityNode(
    name = "Дополнительный боевой стиль",
    changesInCharacterInfo = {abilities: CharacterInfo -> abilities },
    alternatives = mutableMapOf(
        Pair("first", mapOfFightingStyles.keys.toList())
    ),
    requirements = {abilities: CharacterInfo -> abilities.level >= 10 && abilities.characterClass == Classes.Fighter },
    description = "На 10 уровне вы можете выбрать второй боевой стиль."
)

var superiorCritical = AbilityNode(
    name = "Превосходные критические попадания",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Улучшенные критические попадания"] = "Ваши атаки оружием совершают критическое попадание при выпадении «18–20» на кости атаки."
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo -> abilities.level >= 15 && abilities.characterClass == Classes.Fighter},
    description = "Ваши атаки оружием совершают критическое попадание при выпадении «18–20» на кости атаки."
)

var survivor = AbilityNode(
    name = "Уцелевший",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.additionalAbilities["Уцелевший"] = "Вы достигаете вершин стойкости в бою. В начале каждого своего хода вы восстанавливаете количество хитов, равное 5 + ваш модификатор Телосложения, если количество ваших хитов не превышает половины от максимума. Это умение не работает, если у вас 0 хитов."
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo -> abilities.level >= 18 && abilities.characterClass == Classes.Fighter },
    description = "Вы достигаете вершин стойкости в бою. В начале каждого своего хода вы восстанавливаете количество хитов, равное 5 + ваш модификатор Телосложения, если количество ваших хитов не превышает половины от максимума. Это умение не работает, если у вас 0 хитов."
)

var champion = AbilityNode(
    name = "Чемпион",
    changesInCharacterInfo = { abilities: CharacterInfo -> abilities },
    alternatives = mutableMapOf(
        Pair("first", listOf(improvedCritical.name)),
        Pair("second", listOf(remarkableAthlete.name)),
        Pair("third", listOf(additionalFightingStyle.name)),
        Pair("fourth", listOf(superiorCritical.name)),
        Pair("fifth", listOf(survivor.name))
    ),
    requirements = { true },
    description = "",
    isNeedsToBeShown = false
)

var mapOfChampionAbilities: MutableMap<String, AbilityNode> = mutableMapOf(
    Pair(champion.name, champion),
    Pair(improvedCritical.name, improvedCritical),
    Pair(remarkableAthlete.name, remarkableAthlete),
    Pair(additionalFightingStyle.name, additionalFightingStyle),
    Pair(superiorCritical.name, superiorCritical),
    Pair(survivor.name, survivor)
)