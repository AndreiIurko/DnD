package com.andreyyurko.dnd.data.abilities.classes.barbarian

import com.andreyyurko.dnd.data.characterData.Action
import com.andreyyurko.dnd.data.characterData.ActionType
import com.andreyyurko.dnd.data.characterData.CharacterInfo
import com.andreyyurko.dnd.data.characterData.ChargesCounter
import com.andreyyurko.dnd.data.characterData.Classes
import com.andreyyurko.dnd.data.characterData.Priority
import com.andreyyurko.dnd.data.characterData.character.AbilityNode

var divineFuryNecrotic = AbilityNode(
    name = "Некротический урон божественного гнева",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        for (action in abilities.actionsList) {
            if (action.name == "Божественный гнев") {
                action.description = "Вы можете вкладывать божественный гнев в ваши удары оружием. Пока вы в ярости, первое существо, по которому вы попадаете атакой оружием в каждый свой ход, получает дополнительный урон некротической энергией, равный 1к6 + " + (abilities.level/2).toString() + "\n"
            }
        }
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 3 && abilities.characterClass == Classes.Barbarian
    },
    description = "Ваш божественный гнев наносит урон некротической энергией\n",
    priority = Priority.DoAfterBasic
)

var divineFuryLight = AbilityNode(
    name = "Урон излучением божественного гнева",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        for (action in abilities.actionsList) {
            if (action.name == "Божественный гнев") {
                action.description = "Вы можете вкладывать божественный гнев в ваши удары оружием. Пока вы в ярости, первое существо, по которому вы попадаете атакой оружием в каждый свой ход, получает дополнительный урон излучением, равный 1к6 + " + (abilities.level/2).toString() + "\n"
            }
        }
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 3 && abilities.characterClass == Classes.Barbarian
    },
    description = "Ваш божественный гнев наносит урон излучением\n",
    priority = Priority.DoAfterBasic
)

var divineFury = AbilityNode(
    name = "Божественный гнев",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Божественный гнев",
                description = "Вы можете вкладывать божественный гнев в ваши удары оружием. Пока вы в ярости, первое существо, по которому вы попадаете атакой оружием в каждый свой ход, получает дополнительный урон, равный 1к6 + " + (abilities.level/2).toString() + "\n" +
                        "\n" +
                        "Вы самостоятельно выбираете тип урона при получении этого умения из урона излучением или некротической энергией.\n",
                type = ActionType.PartOfAction
            )
        )
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(divineFuryNecrotic.name, divineFuryLight.name))
    ),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 3 && abilities.characterClass == Classes.Barbarian
    },
    description = "Вы можете вкладывать божественный гнев в ваши удары оружием. Пока вы в ярости, первое существо, по которому вы попадаете атакой оружием в каждый свой ход, получает дополнительный урон, равный 1к6 + половина вашего уровня варвара.\n" +
            "\n" +
            "Вы самостоятельно выбираете тип урона при получении этого умения из урона излучением или некротической энергией.\n"
)

var warriorOfTheGods = AbilityNode(
    name = "Воин богов",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Воин богов"] = "Ваша душа отмечена для вечной битвы. Если заклинание, такое как оживление [raise dead], имеет единственный эффект — вернуть вас к жизни (но не нежитью), заклинателю не требуются материальные компоненты, чтобы наложить его на вас.\n"
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 3 && abilities.characterClass == Classes.Barbarian
    },
    description = "Ваша душа отмечена для вечной битвы. Если заклинание, такое как оживление [raise dead], имеет единственный эффект — вернуть вас к жизни (но не нежитью), заклинателю не требуются материальные компоненты, чтобы наложить его на вас.\n"
)

var fanaticalFocus = AbilityNode(
    name = "Концентрация фанатика",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Концентрация фанатика",
                description = "Божественная сила, что питает вашу ярость, может защитить вас от вреда. Если вы провалили спасбросок, пребывая в ярости, вы можете перебросить его и будете обязаны использовать новое значение.\n" +
                        "\n" +
                        "Вы можете использовать эту способность только один раз в течение одной ярости.\n",
                type = ActionType.Additional
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 6 && abilities.characterClass == Classes.Barbarian
    },
    description = "Божественная сила, что питает вашу ярость, может защитить вас от вреда. Если вы провалили спасбросок, пребывая в ярости, вы можете перебросить его и будете обязаны использовать новое значение.\n" +
            "\n" +
            "Вы можете использовать эту способность только один раз в течение одной ярости.\n"
)

var zealousPresence = AbilityNode(
    name = "Фанатичное присутствие",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        if (!abilities.currentState.charges.contains("Фанатичное присутствие")) {
            abilities.currentState.charges["Фанатичное присутствие"] = ChargesCounter(
                current = 1,
                maximum = 1
            )
        }
        abilities.actionsList.add(
            Action(
                name = "Фанатичное присутствие",
                description = "До 10 существ по вашему выбору в пределах 60 футов, которые могут вас слышать, совершают броски атаки и спасброски с преимуществом до начала вашего следующего хода.\n" +
                        "\n" +
                        "Использовав это умение, вы не можете использовать его повторно до окончания продолжительного отдыха.\n",
                type = ActionType.Bonus,
                relatedCharges = "Фанатичное присутствие"
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 10 && abilities.characterClass == Classes.Barbarian
    },
    description = "Вы обучаетесь использовать божественную силу, чтобы вдохновлять на фанатизм других. Бонусным действием вы испускаете боевой клич, наполненный божественной энергией.\n" +
            "\n" +
            "До 10 существ по вашему выбору в пределах 60 футов, которые могут вас слышать, совершают броски атаки и спасброски с преимуществом до начала вашего следующего хода.\n" +
            "\n" +
            "Использовав это умение, вы не можете использовать его повторно до окончания продолжительного отдыха.\n"
)

var rageBeyondDeath = AbilityNode(
    name = "Ярость превыше смерти",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Ярость превыше смерти"] = "Божественная сила, что питает вашу ярость, позволяет вам игнорировать смертельные удары. Пока вы в ярости, опускание хитов до 0 не заставляет вас потерять сознание. Вы по-прежнему должны совершать спасброски от смерти, и наносимый вам урон имеет те же эффекты, что и урон, наносимый персонажу с 0 хитов. Но даже если вы должны умереть при провале спасбросков от смерти, вы не умрёте, пока не закончится ваша ярость, и в этом случае умрёте, только если у вас всё ещё будет 0 хитов.\n"
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 14 && abilities.characterClass == Classes.Barbarian
    },
    description = "Божественная сила, что питает вашу ярость, позволяет вам игнорировать смертельные удары. Пока вы в ярости, опускание хитов до 0 не заставляет вас потерять сознание. Вы по-прежнему должны совершать спасброски от смерти, и наносимый вам урон имеет те же эффекты, что и урон, наносимый персонажу с 0 хитов. Но даже если вы должны умереть при провале спасбросков от смерти, вы не умрёте, пока не закончится ваша ярость, и в этом случае умрёте, только если у вас всё ещё будет 0 хитов.\n"
)

var pathOfTheZealot = AbilityNode(
    name = "Путь фанатика",
    changesInCharacterInfo = { abilities: CharacterInfo -> abilities },
    alternatives = mutableMapOf(
        Pair("first", listOf(divineFury.name)),
        Pair("second", listOf(warriorOfTheGods.name)),
        Pair("third", listOf(fanaticalFocus.name)),
        Pair("fourth", listOf(zealousPresence.name)),
        Pair("fifth", listOf(rageBeyondDeath.name))
    ),
    requirements = { true },
    description = "",
    isNeedsToBeShown = false
)

var mapOfPathOfTheZealotAbilities: MutableMap<String, AbilityNode> = mutableMapOf(
    Pair(divineFuryNecrotic.name, divineFuryNecrotic),
    Pair(divineFuryLight.name, divineFuryLight),
    Pair(divineFury.name, divineFury),
    Pair(warriorOfTheGods.name, warriorOfTheGods),
    Pair(fanaticalFocus.name, fanaticalFocus),
    Pair(zealousPresence.name, zealousPresence),
    Pair(rageBeyondDeath.name, rageBeyondDeath),
    Pair(pathOfTheZealot.name, pathOfTheZealot),
)