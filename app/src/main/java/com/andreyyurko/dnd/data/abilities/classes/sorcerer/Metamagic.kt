package com.andreyyurko.dnd.data.abilities.classes.sorcerer

import com.andreyyurko.dnd.data.characterData.Action
import com.andreyyurko.dnd.data.characterData.ActionType
import com.andreyyurko.dnd.data.characterData.CharacterInfo
import com.andreyyurko.dnd.data.characterData.Classes
import com.andreyyurko.dnd.data.characterData.character.AbilityNode

var carefulSpell: AbilityNode = AbilityNode(
    name = "Аккуратное заклинание",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsMap["Аккуратное заклинание"] =
            Action(
                name = "Аккуратное заклинание",
                description = "Когда вы накладываете заклинание, которое вынуждает других существ совершить спасбросок, вы можете защитить некоторых из них от магического воздействия. Для этого вы тратите 1 единицу чародейства и выбираете существ в количестве, равном вашему модификатору Харизмы (минимум одно существо). Указанные существа автоматически преуспевают в спасброске от данного заклинания.",
                type = ActionType.PartOfAction,
                relatedCharges = "Единицы чародейства"
            )

        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        val isChosen = abilities.actionsMap.contains("Аккуратное заклинание")
        abilities.characterClass == Classes.Sorcerer && abilities.level >= 3 && !isChosen
    },
    description = "Когда вы накладываете заклинание, которое вынуждает других существ совершить спасбросок, вы можете защитить некоторых из них от магического воздействия. Для этого вы тратите 1 единицу чародейства и выбираете существ в количестве, равном вашему модификатору Харизмы (минимум одно существо). Указанные существа автоматически преуспевают в спасброске от данного заклинания."
)

var distantSpell: AbilityNode = AbilityNode(
    name = "Далёкое заклинание",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsMap["Далёкое заклинание"] =
            Action(
                name = "Далёкое заклинание",
                description = "При накладывании заклинания, дистанция которого 5 футов и более, вы можете потратить 1 единицу чародейства, чтобы удвоить это расстояние.\n" +
                        "\n" +
                        "При накладывании заклинания с дистанцией «касание», вы можете потратить 1 единицу чародейства, чтобы увеличить это расстояние до 30 футов.",
                type = ActionType.PartOfAction,
                relatedCharges = "Единицы чародейства"
            )

        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        val isChosen = abilities.actionsMap.contains("Далёкое заклинание")
        abilities.characterClass == Classes.Sorcerer && abilities.level >= 3 && !isChosen
    },
    description = "При накладывании заклинания, дистанция которого 5 футов и более, вы можете потратить 1 единицу чародейства, чтобы удвоить это расстояние.\n" +
            "\n" +
            "При накладывании заклинания с дистанцией «касание», вы можете потратить 1 единицу чародейства, чтобы увеличить это расстояние до 30 футов."
)

var empoweredSpell: AbilityNode = AbilityNode(
    name = "Усиленное заклинание",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsMap["Усиленное заклинание"] =
            Action(
                name = "Усиленное заклинание",
                description = "При совершении броска урона от заклинания вы можете потратить 1 единицу чародейства, чтобы перебросить несколько костей урона в количестве не больше вашего модификатора Харизмы (минимум одна). Вы должны использовать новое выпавшее значение.\n" +
                        "\n" +
                        "Вы можете воспользоваться вариантом «усиленное заклинание» даже если вы уже использовали другой вариант метамагии для этого заклинания.",
                type = ActionType.PartOfAction,
                relatedCharges = "Единицы чародейства"
            )

        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        val isChosen = abilities.actionsMap.contains("Усиленное заклинание")
        abilities.characterClass == Classes.Sorcerer && abilities.level >= 3 && !isChosen
    },
    description = "При совершении броска урона от заклинания вы можете потратить 1 единицу чародейства, чтобы перебросить несколько костей урона в количестве не больше вашего модификатора Харизмы (минимум одна). Вы должны использовать новое выпавшее значение.\n" +
            "\n" +
            "Вы можете воспользоваться вариантом «усиленное заклинание» даже если вы уже использовали другой вариант метамагии для этого заклинания."
)

var extendedSpell: AbilityNode = AbilityNode(
    name = "Продлённое заклинание",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsMap["Продлённое заклинание"] =
            Action(
                name = "Продлённое заклинание",
                description = "При накладывании заклинания с длительностью 1 минута или более, вы можете потратить 1 единицу чародейства, чтобы удвоить это время, вплоть до максимального в 24 часа.",
                type = ActionType.PartOfAction,
                relatedCharges = "Единицы чародейства"
            )

        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        val isChosen = abilities.actionsMap.contains("Продлённое заклинание")
        abilities.characterClass == Classes.Sorcerer && abilities.level >= 3 && !isChosen
    },
    description = "При накладывании заклинания с длительностью 1 минута или более, вы можете потратить 1 единицу чародейства, чтобы удвоить это время, вплоть до максимального в 24 часа."
)

var heightenedSpell: AbilityNode = AbilityNode(
    name = "Непреодолимое заклинание",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsMap["Непреодолимое заклинание"] =
            Action(
                name = "Непреодолимое заклинание",
                description = "Когда вы накладываете заклинание, которое вынуждает существо совершить спасбросок для защиты от его эффектов, вы можете потратить 3 единицы чародейства, чтобы одна из целей заклинания совершила первый спасбросок от этого заклинания с помехой.",
                type = ActionType.PartOfAction,
                relatedCharges = "Единицы чародейства"
            )

        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        val isChosen = abilities.actionsMap.contains("Непреодолимое заклинание")
        abilities.characterClass == Classes.Sorcerer && abilities.level >= 3 && !isChosen
    },
    description = "Когда вы накладываете заклинание, которое вынуждает существо совершить спасбросок для защиты от его эффектов, вы можете потратить 3 единицы чародейства, чтобы одна из целей заклинания совершила первый спасбросок от этого заклинания с помехой."
)

var quickenedSpell: AbilityNode = AbilityNode(
    name = "Ускоренное заклинание",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsMap["Ускоренное заклинание"] =
            Action(
                name = "Ускоренное заклинание",
                description = "Если вы накладываете заклинание со временем накладывания «1 действие», вы можете потратить 2 единицы чародейства, чтобы наложить это заклинание бонусным действием.",
                type = ActionType.PartOfAction,
                relatedCharges = "Единицы чародейства"
            )

        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        val isChosen = abilities.actionsMap.contains("Ускоренное заклинание")
        abilities.characterClass == Classes.Sorcerer && abilities.level >= 3 && !isChosen
    },
    description = "Если вы накладываете заклинание со временем накладывания «1 действие», вы можете потратить 2 единицы чародейства, чтобы наложить это заклинание бонусным действием."
)

var subtleSpell: AbilityNode = AbilityNode(
    name = "Неуловимое заклинание",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsMap["Неуловимое заклинание"] =
            Action(
                name = "Неуловимое заклинание",
                description = "Во время накладывания заклинания вы можете потратить 1 единицу чародейства, чтоб наложить его без вербальных и соматических компонентов.",
                type = ActionType.PartOfAction,
                relatedCharges = "Единицы чародейства"
            )

        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        val isChosen = abilities.actionsMap.contains("Неуловимое заклинание")
        abilities.characterClass == Classes.Sorcerer && abilities.level >= 3 && !isChosen
    },
    description = "Во время накладывания заклинания вы можете потратить 1 единицу чародейства, чтоб наложить его без вербальных и соматических компонентов."
)

var twinnedSpell: AbilityNode = AbilityNode(
    name = "Удвоенное заклинание",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsMap["Удвоенное заклинание"] =
            Action(
                name = "Удвоенное заклинание",
                description = "Если вы используете заклинание, нацеливаемое только на одно существо и не имеющее дистанцию «на себя», вы можете потратить количество единиц чародейства, равное уровню заклинания (1 для заговоров), чтобы нацелиться им на второе существо-цель в пределах дистанции этого заклинания.\n" +
                        "\n" +
                        "Чтобы применить этот вариант, заклинание не должно быть способно нацеливаться более чем на одну цель на текущем накладываемом уровне. ",
                type = ActionType.PartOfAction,
                relatedCharges = "Единицы чародейства"
            )

        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        val isChosen = abilities.actionsMap.contains("Удвоенное заклинание")
        abilities.characterClass == Classes.Sorcerer && abilities.level >= 3 && !isChosen
    },
    description = "Если вы используете заклинание, нацеливаемое только на одно существо и не имеющее дистанцию «на себя», вы можете потратить количество единиц чародейства, равное уровню заклинания (1 для заговоров), чтобы нацелиться им на второе существо-цель в пределах дистанции этого заклинания.\n" +
            "\n" +
            "Чтобы применить этот вариант, заклинание не должно быть способно нацеливаться более чем на одну цель на текущем накладываемом уровне. "
)

var mapOfMetamagicOptions = mutableMapOf(
    Pair(carefulSpell.name, carefulSpell),
    Pair(distantSpell.name, distantSpell),
    Pair(empoweredSpell.name, empoweredSpell),
    Pair(extendedSpell.name, extendedSpell),
    Pair(heightenedSpell.name, heightenedSpell),
    Pair(quickenedSpell.name, quickenedSpell),
    Pair(subtleSpell.name, subtleSpell),
    Pair(twinnedSpell.name, twinnedSpell)
)