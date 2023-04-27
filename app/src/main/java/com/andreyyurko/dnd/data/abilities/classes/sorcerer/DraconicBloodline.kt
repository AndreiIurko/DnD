package com.andreyyurko.dnd.data.abilities.classes.sorcerer

import com.andreyyurko.dnd.data.characterData.*
import com.andreyyurko.dnd.data.characterData.character.AbilityNode
import com.andreyyurko.dnd.data.characterData.character.abilityToModifier
import java.lang.Integer.max

var typeToDamageMap = mapOf(
    Pair("Черный", DamageType.Acid),
    Pair("Белый", DamageType.Cold),
    Pair("Бронзовый", DamageType.Lightening),
    Pair("Зеленый", DamageType.Poison),
    Pair("Золотой", DamageType.Fire),
    Pair("Красный", DamageType.Fire),
    Pair("Латунный", DamageType.Fire),
    Pair("Медный", DamageType.Acid),
    Pair("Серебряный", DamageType.Cold),
    Pair("Синий", DamageType.Lightening)
)

var ancestorBlack: AbilityNode = AbilityNode(
    name = "Цвет дракона: черный",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Драконий предок"] =
            "Ваш драконий предок - Черный Дракон.\n" +
                    "\n" +
                    "Вы можете говорить, читать и писать на Драконьем языке. Кроме того, когда вы взаимодействуете с драконами и совершаете проверку Харизмы, ваш бонус мастерства удваивается для этой проверки."
        abilities.languageProficiency.add(Languages.Draconic)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 1 && abilities.characterClass == Classes.Sorcerer
    },
    description = "",
    isNeedsToBeShown = false
)

var ancestorBlue: AbilityNode = AbilityNode(
    name = "Цвет дракона: синий",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Драконий предок"] =
            "Ваш драконий предок - Синий Дракон.\n" +
                    "\n" +
                    "Вы можете говорить, читать и писать на Драконьем языке. Кроме того, когда вы взаимодействуете с драконами и совершаете проверку Харизмы, ваш бонус мастерства удваивается для этой проверки."
        abilities.languageProficiency.add(Languages.Draconic)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 1 && abilities.characterClass == Classes.Sorcerer
    },
    description = "",
    isNeedsToBeShown = false
)

var ancestorBrass: AbilityNode = AbilityNode(
    name = "Цвет дракона: латунный",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Драконий предок"] =
            "Ваш драконий предок - Латунный Дракон.\n" +
                    "\n" +
                    "Вы можете говорить, читать и писать на Драконьем языке. Кроме того, когда вы взаимодействуете с драконами и совершаете проверку Харизмы, ваш бонус мастерства удваивается для этой проверки."
        abilities.languageProficiency.add(Languages.Draconic)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 1 && abilities.characterClass == Classes.Sorcerer
    },
    description = "",
    isNeedsToBeShown = false
)

var ancestorBronze: AbilityNode = AbilityNode(
    name = "Цвет дракона: бронзовый",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Драконий предок"] =
            "Ваш драконий предок - Бронзовый Дракон.\n" +
                    "\n" +
                    "Вы можете говорить, читать и писать на Драконьем языке. Кроме того, когда вы взаимодействуете с драконами и совершаете проверку Харизмы, ваш бонус мастерства удваивается для этой проверки."
        abilities.languageProficiency.add(Languages.Draconic)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 1 && abilities.characterClass == Classes.Sorcerer
    },
    description = "",
    isNeedsToBeShown = false
)

var ancestorCopper: AbilityNode = AbilityNode(
    name = "Цвет дракона: медный",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Драконий предок"] =
            "Ваш драконий предок - Медный Дракон.\n" +
                    "\n" +
                    "Вы можете говорить, читать и писать на Драконьем языке. Кроме того, когда вы взаимодействуете с драконами и совершаете проверку Харизмы, ваш бонус мастерства удваивается для этой проверки."
        abilities.languageProficiency.add(Languages.Draconic)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 1 && abilities.characterClass == Classes.Sorcerer
    },
    description = "",
    isNeedsToBeShown = false
)

var ancestorGold: AbilityNode = AbilityNode(
    name = "Цвет дракона: золотой",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Драконий предок"] =
            "Ваш драконий предок - Золотой Дракон.\n" +
                    "\n" +
                    "Вы можете говорить, читать и писать на Драконьем языке. Кроме того, когда вы взаимодействуете с драконами и совершаете проверку Харизмы, ваш бонус мастерства удваивается для этой проверки."
        abilities.languageProficiency.add(Languages.Draconic)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 1 && abilities.characterClass == Classes.Sorcerer
    },
    description = "",
    isNeedsToBeShown = false
)

var ancestorGreen: AbilityNode = AbilityNode(
    name = "Цвет дракона: зеленый",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Драконий предок"] =
            "Ваш драконий предок - Зеленый Дракон.\n" +
                    "\n" +
                    "Вы можете говорить, читать и писать на Драконьем языке. Кроме того, когда вы взаимодействуете с драконами и совершаете проверку Харизмы, ваш бонус мастерства удваивается для этой проверки."
        abilities.languageProficiency.add(Languages.Draconic)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 1 && abilities.characterClass == Classes.Sorcerer
    },
    description = "",
    isNeedsToBeShown = false
)

var ancestorRed: AbilityNode = AbilityNode(
    name = "Цвет дракона: красный",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Драконий предок"] =
            "Ваш драконий предок - Красный Дракон.\n" +
                    "\n" +
                    "Вы можете говорить, читать и писать на Драконьем языке. Кроме того, когда вы взаимодействуете с драконами и совершаете проверку Харизмы, ваш бонус мастерства удваивается для этой проверки."
        abilities.languageProficiency.add(Languages.Draconic)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 1 && abilities.characterClass == Classes.Sorcerer
    },
    description = "",
    isNeedsToBeShown = false
)

var ancestorSilver: AbilityNode = AbilityNode(
    name = "Цвет дракона: серебряный",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Драконий предок"] =
            "Ваш драконий предок - Серебряный Дракон.\n" +
                    "\n" +
                    "Вы можете говорить, читать и писать на Драконьем языке. Кроме того, когда вы взаимодействуете с драконами и совершаете проверку Харизмы, ваш бонус мастерства удваивается для этой проверки."
        abilities.languageProficiency.add(Languages.Draconic)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 1 && abilities.characterClass == Classes.Sorcerer
    },
    description = "",
    isNeedsToBeShown = false
)

var ancestorWhite: AbilityNode = AbilityNode(
    name = "Цвет дракона: белый",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Драконий предок"] =
            "Ваш драконий предок - Белый Дракон.\n" +
                    "\n" +
                    "Вы можете говорить, читать и писать на Драконьем языке. Кроме того, когда вы взаимодействуете с драконами и совершаете проверку Харизмы, ваш бонус мастерства удваивается для этой проверки."
        abilities.languageProficiency.add(Languages.Draconic)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 1 && abilities.characterClass == Classes.Sorcerer
    },
    description = "",
    isNeedsToBeShown = false
)

var dragonAncestor: AbilityNode = AbilityNode(
    name = "Драконий предок",
    changesInCharacterInfo = { abilities: CharacterInfo -> abilities },
    alternatives = mutableMapOf(
        Pair(
            "first", listOf(
                ancestorBlack.name, ancestorSilver.name, ancestorBlue.name, ancestorBrass.name, ancestorBronze.name,
                ancestorCopper.name, ancestorGold.name, ancestorGreen.name, ancestorRed.name, ancestorWhite.name
            )
        )
    ),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 1 && abilities.characterClass == Classes.Sorcerer
    },
    description = "Вы выбираете вид вашего дракона предка. Связанный с ним вид урона используется в ваших умениях.\n" +
            "\n" +
            "ДРАКОНИЙ ПРЕДОК\n" +
            "Дракон\tВид урона\n" +
            "Белый\tХолод\n" +
            "Бронзовый\tЭлектричество\n" +
            "Зеленый\tЯд\n" +
            "Золотой\tОгонь\n" +
            "Красный\tОгонь\n" +
            "Латунный\tОгонь\n" +
            "Медный\tКислота\n" +
            "Серебряный\tХолод\n" +
            "Синий\tЭлектричество\n" +
            "Чёрный\tКислота\n" +
            "\n" +
            "Вы можете говорить, читать и писать на Драконьем языке. Кроме того, когда вы взаимодействуете с драконами и совершаете проверку Харизмы, ваш бонус мастерства удваивается для этой проверки."
)

var draconicResilience: AbilityNode = AbilityNode(
    name = "Драконья устойчивость",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        if (abilities.currentState.armor == Armor.NoArmor) {
            abilities.ac = max(abilities.ac, 13 + abilityToModifier(abilities.dexterity))
        }

        abilities.hp += abilities.level

        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 1 && abilities.characterClass == Classes.Sorcerer
    },
    description = "Магия, струящаяся через ваше тело, проявляет физические черты ваших предков драконов. Максимум ваших хитов увеличивается на 1 на 1-м уровне и на 1 на каждом уровне, полученном в данном классе.\n" +
            "\n" +
            "Кроме того, некоторые участки вашей кожи покрыты тонкой драконьей чешуёй. Если вы не носите доспехов, ваш Класс Доспеха равен 13 + модификатор Ловкости."
)

var elementalAffinity: AbilityNode = AbilityNode(
    name = "Родство со стихией",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        if (abilities.additionalAbilities.contains("Драконий предок")) {
            var colorType = ""
            abilities.additionalAbilities["Драконий предок"]?.let { colorType = it.split(' ')[4] }
            typeToDamageMap[colorType]?.let {
                abilities.actionsList.add(
                    Action(
                        name = "Родство со стихией",
                        description = "Когда вы накладываете заклинание, причиняющее урон вида ${it.typeName}, вы добавляете модификатор Харизмы к одному броску урона этого заклинания. В это же самое время вы можете потратить 1 единицу чародейства, чтобы получить сопротивление этому виду урона на 1 час.",
                        type = ActionType.PartOfAction
                    )
                )
            }
        }
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 6 && abilities.characterClass == Classes.Sorcerer
    },
    description = "Когда вы накладываете заклинание, причиняющее урон вида, связанного с вашим драконьим предком, вы добавляете модификатор Харизмы к одному броску урона этого заклинания. В это же самое время вы можете потратить 1 единицу чародейства, чтобы получить сопротивление этому виду урона на 1 час."
)

var dragonWings: AbilityNode = AbilityNode(
    name = "Крылья дракона",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Крылья дракона",
                description = "Вы получаете способность расправить драконьи крылья у себя за спиной, получая при этом скорость полёта, равную вашей текущей скорости. Вы можете создать их бонусным действием в свой ход. Крылья существуют, пока вы не развеете их бонусным действием в свой ход.\n" +
                        "\n" +
                        "Вы не можете призвать свои крылья, нося броню, если, конечно, броня не изготовлена специально для этого. Одежда, также не приспособленная под крылья, может быть уничтожена, когда вы призываете их.",
                type = ActionType.Bonus
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 14 && abilities.characterClass == Classes.Sorcerer
    },
    description = "Вы получаете способность расправить драконьи крылья у себя за спиной, получая при этом скорость полёта, равную вашей текущей скорости. Вы можете создать их бонусным действием в свой ход. Крылья существуют, пока вы не развеете их бонусным действием в свой ход.\n" +
            "\n" +
            "Вы не можете призвать свои крылья, нося броню, если, конечно, броня не изготовлена специально для этого. Одежда, также не приспособленная под крылья, может быть уничтожена, когда вы призываете их."
)

var dragonPresence: AbilityNode = AbilityNode(
    name = "Образ дракона",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Образ дракона",
                description = "Вы можете вызвать ужасный образ своего предка дракона, повергая им в ужас врагов. Вы можете действием потратить 5 единиц чародейства, чтобы окружить себя аурой страха или трепета (на ваш выбор), радиусом 60 футов. В течение 1 минуты, или пока вы не утратите концентрацию (как если бы вы концентрировались на заклинании), все враждебные существа, начинающие ход в этой ауре, должны преуспеть в спасброске Мудрости, иначе они станут очарованными (если вы выбрали трепет) или испуганными (если вы выбрали страх) до окончания действия ауры. Существо, преуспевшее в спасброске, получает иммунитет к вашей ауре на 24 часа.",
                type = ActionType.Action
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 18 && abilities.characterClass == Classes.Sorcerer
    },
    description = "Вы можете вызвать ужасный образ своего предка дракона, повергая им в ужас врагов. Вы можете действием потратить 5 единиц чародейства, чтобы окружить себя аурой страха или трепета (на ваш выбор), радиусом 60 футов. В течение 1 минуты, или пока вы не утратите концентрацию (как если бы вы концентрировались на заклинании), все враждебные существа, начинающие ход в этой ауре, должны преуспеть в спасброске Мудрости, иначе они станут очарованными (если вы выбрали трепет) или испуганными (если вы выбрали страх) до окончания действия ауры. Существо, преуспевшее в спасброске, получает иммунитет к вашей ауре на 24 часа."
)

var draconicBloodline: AbilityNode = AbilityNode(
    name = "Наследие драконьей крови",
    changesInCharacterInfo = { abilities: CharacterInfo -> abilities },
    alternatives = mutableMapOf(
        Pair("first", listOf(dragonAncestor.name)),
        Pair("second", listOf(draconicResilience.name)),
        Pair("third", listOf(elementalAffinity.name)),
        Pair("forth", listOf(dragonWings.name)),
        Pair("fifth", listOf(dragonPresence.name))
    ),
    requirements = { true },
    description = "",
    isNeedsToBeShown = false
)

var draconicBloodlineMap = mutableMapOf(
    Pair(ancestorBlack.name, ancestorBlack),
    Pair(ancestorBlue.name, ancestorBlue),
    Pair(ancestorBrass.name, ancestorBrass),
    Pair(ancestorGold.name, ancestorGold),
    Pair(ancestorBronze.name, ancestorBronze),
    Pair(ancestorCopper.name, ancestorCopper),
    Pair(ancestorGreen.name, ancestorGreen),
    Pair(ancestorRed.name, ancestorRed),
    Pair(ancestorWhite.name, ancestorWhite),
    Pair(ancestorSilver.name, ancestorSilver),
    Pair(dragonAncestor.name, dragonAncestor),
    Pair(draconicResilience.name, draconicResilience),
    Pair(elementalAffinity.name, elementalAffinity),
    Pair(dragonWings.name, dragonWings),
    Pair(dragonPresence.name, dragonPresence),
    Pair(draconicBloodline.name, draconicBloodline)
)