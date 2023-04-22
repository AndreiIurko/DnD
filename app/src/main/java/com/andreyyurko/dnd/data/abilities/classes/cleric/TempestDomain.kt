package com.andreyyurko.dnd.data.abilities.classes.cleric

import com.andreyyurko.dnd.data.abilities.classes.fighter.*
import com.andreyyurko.dnd.data.characterData.*
import com.andreyyurko.dnd.data.characterData.character.AbilityNode
import com.andreyyurko.dnd.data.spells.CharacterSpells
import com.andreyyurko.dnd.data.spells.SpellLists

var TempestDomainSpells = AbilityNode(
    name = "Заклинания домена бури",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        val additionalSpellList = mutableSetOf<String>()
        if (abilities.level >= 1) {
            additionalSpellList.add("Волна грома")
            additionalSpellList.add("Туманное облако")
        }
        if (abilities.level >= 3) {
            additionalSpellList.add("Дребезги")
            additionalSpellList.add("Порыв ветра")
        }
        if (abilities.level >= 5) {
            additionalSpellList.add("Метель")
            additionalSpellList.add("Призыв молнии")
        }
        if (abilities.level >= 7) {
            additionalSpellList.add("Власть над водами")
            additionalSpellList.add("Град")
        }
        if (abilities.level >= 9) {
            additionalSpellList.add("Нашествие насекомых")
            additionalSpellList.add("Разрушительная волна")
        }
        abilities.spellsInfo["Заклинания домена бури"] = CharacterSpells(
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
            "       1      | громовая волна, туманное облако\n" +
            "       3      | дребезги, порыв ветра\n" +
            "       5      | метель, призыв молнии\n" +
            "       7      | власть над водами, град\n" +
            "       9      | нашествие насекомых, разрушительная волна\n"
)

var bonusProficiencyTempestDomain = AbilityNode(
    name = "Дополнительные владения домена бури",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.armorProficiency.add(ArmorProf.HeavyArmor)
        //abilities.weaponProficiency.add()
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 1 && abilities.characterClass == Classes.Cleric
    },
    description = "Вы осваиваете владение воинским оружием и тяжёлыми доспехами."
)

var wrathOfTheStorm = AbilityNode(
    name = "Гнев бури",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Гнев бури",
                description = "Вы можете громогласно покарать атакующих. Если существо в пределах 5 футов от вас, которое вы можете видеть, успешно попадает по вам атакой, вы можете реакцией заставить существо совершить спасбросок Ловкости. Существо получает урона звуком или электричеством (по вашему выбору) 2к8, если провалит спасбросок, и половину этого урона если преуспеет. Вы можете использовать это умение количество раз, равное вашему модификатору Мудрости (минимум 1 раз). Вы восстанавливаете все потраченные применения, когда завершаете продолжительный отдых.\n",
                type = ActionType.Action,
                relatedCharges = "Божественный канал"
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 1 && abilities.characterClass == Classes.Cleric
    },
    description = "Вы можете громогласно покарать атакующих. Если существо в пределах 5 футов от вас, которое вы можете видеть, успешно попадает по вам атакой, вы можете реакцией заставить существо совершить спасбросок Ловкости. Существо получает урона звуком или электричеством (по вашему выбору) 2к8, если провалит спасбросок, и половину этого урона если преуспеет. Вы можете использовать это умение количество раз, равное вашему модификатору Мудрости (минимум 1 раз). Вы восстанавливаете все потраченные применения, когда завершаете продолжительный отдых.\n"
)

var channelDivinityDestructiveWrath: AbilityNode = AbilityNode(
    name = "Божественный канал: разрушительный гнев",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Разрушительный гнев",
                description = "Вы можете использовать «Божественный канал», чтобы овладеть могуществом бури с необузданной свирепостью.\n" +
                        "\n" +
                        "Когда вы совершаете бросок урона звуком или электричеством, вы можете использовать «Божественный канал», чтобы нанести максимальный урон вместо броска.\n",
                type = ActionType.PartOfAction,
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
    description = "Вы можете использовать «Божественный канал», чтобы овладеть могуществом бури с необузданной свирепостью.\n" +
            "\n" +
            "Когда вы совершаете бросок урона звуком или электричеством, вы можете использовать «Божественный канал», чтобы нанести максимальный урон вместо броска.\n",
)

var thunderboltStrike = AbilityNode(
    name = "Удар грома",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Удар грома",
                description = "Когда вы наносите урон электричеством существу с размером Большое или меньше, вы также можете оттолкнуть его на 10 футов от себя.\n",
                type = ActionType.PartOfAction,
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 6 && abilities.characterClass == Classes.Cleric
    },
    description = "Когда вы наносите урон электричеством существу с размером Большое или меньше, вы также можете оттолкнуть его на 10 футов от себя.\n"
)

var divineStrikeTempestDomain = AbilityNode(
    name = "Божественный удар домена бури",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Божественный удар домена бури",
                description = "Вы получаете способность наполнять удары своего оружия божественной энергией. Один раз в каждый свой ход, когда вы попадаете по существу атакой оружием, вы можете причинить цели дополнительный урон звуком 1к8. Когда вы достигаете 14-го уровня, дополнительный урон возрастает до 2к8.\n",
                type = ActionType.PartOfAction,
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 8 && abilities.characterClass == Classes.Cleric
    },
    description = "Вы получаете способность наполнять удары своего оружия божественной энергией. Один раз в каждый свой ход, когда вы попадаете по существу атакой оружием, вы можете причинить цели дополнительный урон звуком 1к8. Когда вы достигаете 14-го уровня, дополнительный урон возрастает до 2к8.\n"
)

var stormBorn = AbilityNode(
    name = "Бурерождённый",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Бурерождённый"] = "У вас появляется скорость полёта, равная вашей текущей наземной скорости ходьбы, когда вы не под землёй и не в помещении.\n"
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 17 && abilities.characterClass == Classes.Cleric
    },
    description = "У вас появляется скорость полёта, равная вашей текущей наземной скорости ходьбы, когда вы не под землёй и не в помещении.\n"
)

var tempestDomain = AbilityNode(
    name = "Домен бури",
    changesInCharacterInfo = { abilities: CharacterInfo -> abilities },
    alternatives = mutableMapOf(
        Pair("first", listOf(TempestDomainSpells.name)),
        Pair("second", listOf(bonusProficiencyTempestDomain.name)),
        Pair("third", listOf(wrathOfTheStorm.name)),
        Pair("fourth", listOf(channelDivinityDestructiveWrath.name)),
        Pair("fifth", listOf(thunderboltStrike.name)),
        Pair("sixth", listOf(divineStrikeTempestDomain.name)),
        Pair("seventh", listOf(stormBorn.name))
    ),
    requirements = { true },
    description = "",
    isNeedsToBeShown = false
)

var mapOfTempestDomainAbilities: MutableMap<String, AbilityNode> = mutableMapOf(
    Pair(TempestDomainSpells.name, TempestDomainSpells),
    Pair(bonusProficiencyTempestDomain.name, bonusProficiencyTempestDomain),
    Pair(wrathOfTheStorm.name, wrathOfTheStorm),
    Pair(channelDivinityDestructiveWrath.name, channelDivinityDestructiveWrath),
    Pair(thunderboltStrike.name, thunderboltStrike),
    Pair(divineStrikeTempestDomain.name, divineStrikeTempestDomain),
    Pair(stormBorn.name, stormBorn),
    Pair(tempestDomain.name, tempestDomain),
)