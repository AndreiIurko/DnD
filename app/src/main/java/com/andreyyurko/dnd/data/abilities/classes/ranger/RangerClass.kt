package com.andreyyurko.dnd.data.abilities.classes.ranger

import com.andreyyurko.dnd.data.abilities.classes.AbilityNodeLevel
import com.andreyyurko.dnd.data.abilities.classes.extraAttack
import com.andreyyurko.dnd.data.abilities.other.*
import com.andreyyurko.dnd.data.characterData.*
import com.andreyyurko.dnd.data.characterData.character.AbilityNode
import com.andreyyurko.dnd.data.characterData.character.abilityToModifier


var classFeaturesRanger: AbilityNode = AbilityNode(
    name = "Следопыт: классовые умения",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.savingThrowProf.add(Ability.Strength)
        abilities.savingThrowProf.add(Ability.Dexterity)
        addAllSimpleWeapons(abilities)
        addAllMartialWeapons(abilities)
        abilities.armorProficiency.add(ArmorProf.LightArmor)
        abilities.armorProficiency.add(ArmorProf.MediumArmor)
        abilities.armorProficiency.add(ArmorProf.Shield)
        abilities
    },
    alternatives = mutableMapOf(
        Pair(
            "skill1",
            listOf(
                athletics.name,
                perception.name,
                survival.name,
                nature.name,
                insight.name,
                investigation.name,
                stealth.name,
                animalHandling.name
            )
        ),
        Pair(
            "skill2",
            listOf(
                athletics.name,
                perception.name,
                survival.name,
                nature.name,
                insight.name,
                investigation.name,
                stealth.name,
                animalHandling.name
            )
        ),
        Pair(
            "skill3",
            listOf(
                athletics.name,
                perception.name,
                survival.name,
                nature.name,
                insight.name,
                investigation.name,
                stealth.name,
                animalHandling.name
            )
        )
    ),
    requirements = { abilities: CharacterInfo ->
        abilities.characterClass == Classes.Ranger
    },
    description = "ХИТЫ\n" +
            "\n" +
            "Кость Хитов: 1к10 за каждый уровень следопыта\n" +
            "\n" +
            "Хиты на 1 уровне: 10 + модификатор Телосложения\n" +
            "\n" +
            "Хиты на следующих уровнях: 1к10 (или 6) + модификатор Телосложения (суммарно минимум 1) за каждый уровень следопыта после первого\n" +
            "\n" +
            "ВЛАДЕНИЕ\n" +
            "\n" +
            "Доспехи: Лёгкие доспехи, средние доспехи, щиты\n" +
            "\n" +
            "Оружие: Простое оружие, воинское оружие\n" +
            "\n" +
            "Инструменты: Нет\n" +
            "\n" +
            "Спасброски: Сила, Ловкость\n" +
            "\n" +
            "Навыки: Выберите три навыка из следующих: Атлетика, Восприятие, Выживание, Природа, Проницательность, Расследование, Скрытность, Уход за животными\n",
    priority = Priority.DoFirst
)

var fightingStyleRanger: AbilityNode = AbilityNode(
    name = "Боевой стиль",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    alternatives = mutableMapOf(
        Pair("style", listOf(dueling.name, defense.name, twoWeaponFighting.name, archery.name))
    ),
    requirements = { abilities: CharacterInfo ->
        abilities.characterClass == Classes.Ranger
    },
    description = "Вы выбираете боевой стиль, соответствующий вашей специализации. Выберите один из следующих вариантов. Вы не можете выбирать один и тот же вариант боевого стиля, даже если позже у вас будет возможность выбрать еще один стиль."
)


var ranger1: AbilityNodeLevel = AbilityNodeLevel(
    name = "Следопыт_1",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.characterClass = Classes.Ranger
        abilities.level += 1
        abilities.proficiencyBonus += 2
        abilities.hp += abilityToModifier(abilities.constitution) + 10
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(classFeaturesRanger.name)),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "1-й уровень, способности следопыта",
    next_level = "Следопыт_2",
)

var ranger2: AbilityNodeLevel = AbilityNodeLevel(
    name = "Следопыт_2",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(fightingStyleRanger.name)),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "2-й уровень, способности следопыта",
    next_level = "Следопыт_3",
)

var rangerConclave: AbilityNode = AbilityNode(
    name = "Архетип следопыта",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    alternatives = mutableMapOf(
        Pair("archetype", listOf())
    ),
    requirements = { abilities: CharacterInfo ->
        abilities.characterClass == Classes.Ranger
    },
    description = "Вы выбираете архетип, к которому вы стремитесь. Подробности этих архетипов приведены в конце описания класса. Выбранный вами архетип предоставляет умения на 3-м, 7-м, 11-м и 15-м уровнях."
)

var ranger3: AbilityNodeLevel = AbilityNodeLevel(
    name = "Следопыт_3",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(rangerConclave.name)),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "3-й уровень, способности следопыта",
    next_level = "Следопыт_4",
)

var ranger4: AbilityNodeLevel = AbilityNodeLevel(
    name = "Следопыт_4",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(abilityScoreImprovement.name)),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "4-й уровень, способности следопыта",
    next_level = "Следопыт_5",
)

var ranger5: AbilityNodeLevel = AbilityNodeLevel(
    name = "Следопыт_5",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities.proficiencyBonus += 1
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(extraAttack.name)),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "5-й уровень, способности следопыта",
    next_level = "Следопыт_6",
)

var ranger6: AbilityNodeLevel = AbilityNodeLevel(
    name = "Следопыт_6",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    alternatives = mutableMapOf(
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "6-й уровень, способности следопыта",
    next_level = "Следопыт_7",
)

var ranger7: AbilityNodeLevel = AbilityNodeLevel(
    name = "Следопыт_7",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "7-й уровень, способности следопыта",
    next_level = "Следопыт_8",
)

var ranger8: AbilityNodeLevel = AbilityNodeLevel(
    name = "Следопыт_8",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(abilityScoreImprovement.name)),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "8-й уровень, способности следопыта",
    next_level = "Следопыт_9",
)

var ranger9: AbilityNodeLevel = AbilityNodeLevel(
    name = "Следопыт_9",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities.proficiencyBonus += 1
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf())
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "9-й уровень, способности следопыта",
    next_level = "Следопыт_10",
)

var ranger10: AbilityNodeLevel = AbilityNodeLevel(
    name = "Следопыт_10",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "10-й уровень, способности следопыта",
    next_level = "Следопыт_11",
)

var ranger11: AbilityNodeLevel = AbilityNodeLevel(
    name = "Следопыт_11",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "11-й уровень, способности следопыта",
    next_level = "Следопыт_12",
)

var ranger12: AbilityNodeLevel = AbilityNodeLevel(
    name = "Следопыт_12",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(abilityScoreImprovement.name)),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "12-й уровень, способности следопыта",
    next_level = "Следопыт_13",
)

var ranger13: AbilityNodeLevel = AbilityNodeLevel(
    name = "Следопыт_13",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.proficiencyBonus += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "13-й уровень, способности следопыта",
    next_level = "Следопыт_14",
)

var ranger14: AbilityNodeLevel = AbilityNodeLevel(
    name = "Следопыт_14",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    alternatives = mutableMapOf(
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "14-й уровень, способности следопыта",
    next_level = "Следопыт_15",
)

var ranger15: AbilityNodeLevel = AbilityNodeLevel(
    name = "Следопыт_15",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "15-й уровень, способности следопыта",
    next_level = "Следопыт_16",
)

var ranger16: AbilityNodeLevel = AbilityNodeLevel(
    name = "Следопыт_16",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(abilityScoreImprovement.name)),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "16-й уровень, способности следопыта",
    next_level = "Следопыт_17",
)

var ranger17: AbilityNodeLevel = AbilityNodeLevel(
    name = "Следопыт_17",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities.proficiencyBonus += 1
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "17-й уровень, способности следопыта",
    next_level = "Следопыт_18",
)

var ranger18: AbilityNodeLevel = AbilityNodeLevel(
    name = "Следопыт_18",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "18-й уровень, способности следопыта",
    next_level = "Следопыт_19",
)

var ranger19: AbilityNodeLevel = AbilityNodeLevel(
    name = "Следопыт_19",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(abilityScoreImprovement.name)),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "19-й уровень, способности следопыта",
    next_level = "Следопыт_20",
)

var ranger20: AbilityNodeLevel = AbilityNodeLevel(
    name = "Следопыт_20",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "20-й уровень, способности следопыта",
    next_level = null,
)

