package com.andreyyurko.dnd.data.abilities.classes.paladin

import com.andreyyurko.dnd.data.characterData.*
import com.andreyyurko.dnd.data.characterData.character.AbilityNode
import com.andreyyurko.dnd.data.spells.CharacterSpells
import com.andreyyurko.dnd.data.spells.SpellLists

var oathOfDevotionChanelDivinity = AbilityNode(
    name = "Божественный канал клятвы преданности",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Священное оружие",
                description = "Вы можете действием наполнить одно оружие, которое вы держите, позитивной энергией, используя «Божественный канал». На протяжении 1 минуты вы добавляете свой модификатор Харизмы к броскам атаки, сделанным этим оружием (минимальный бонус +1). Также оружие испускает яркий свет в радиусе 20 футов и тусклый свет в пределах еще 20 футов. Если оружие не является волшебным, то оно считается волшебным в течение этого времени. Вы можете закончить этот эффект в свой ход частью любого другого действия. Если вы не держите или не несёте это оружие, или теряете сознание, то этот эффект заканчивается.\n",
                type = ActionType.Action,
                relatedCharges = "Божественный канал"
            )
        )
        abilities.actionsList.add(
            Action(
                name = "Изгнать нечистого",
                description = "Вы действием показываете свой священный символ и произносите слова молитвы, осуждающей Исчадия и Нежить, используя «Божественный канал». Все Исчадия и Нежить, которые могут видеть или слышать вас, находящиеся в пределах 30 футов от вас, должны совершить спасбросок Мудрости. Если они проваливают спасбросок, то изгоняются на 1 минуту или до тех пор, пока не получат урон.\n" +
                        "\n" +
                        "Изгнанные существа должны весь свой ход пытаться убежать от вас так далеко, как только могут, и не могут добровольно переместиться в пространство, находящееся в пределах 30 футов от вас. Также они не могут совершать реакции. Из всех действий они могут совершать только Рывок и попытки избежать эффекта, не дающего им перемещаться. Если же двигаться некуда, существа могут использовать действие Уклонение.\n",
                type = ActionType.Action,
                relatedCharges = "Божественный канал"
            )
        )
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 3 && abilities.characterClass == Classes.Paladin
    },
    description = "Когда вы даёте эту клятву, вы получаете следующие два варианта использования «Божественного канала».\n" +
            "\n" +
            "Священное оружие. Вы можете действием наполнить одно оружие, которое вы держите, позитивной энергией, используя «Божественный канал». На протяжении 1 минуты вы добавляете свой модификатор Харизмы к броскам атаки, сделанным этим оружием (минимальный бонус +1). Также оружие испускает яркий свет в радиусе 20 футов и тусклый свет в пределах еще 20 футов. Если оружие не является волшебным, то оно считается волшебным в течение этого времени. Вы можете закончить этот эффект в свой ход частью любого другого действия. Если вы не держите или не несёте это оружие, или теряете сознание, то этот эффект заканчивается.\n" +
            "\n" +
            "Изгнать нечистого. Вы действием показываете свой священный символ и произносите слова молитвы, осуждающей Исчадия и Нежить, используя «Божественный канал». Все Исчадия и Нежить, которые могут видеть или слышать вас, находящиеся в пределах 30 футов от вас, должны совершить спасбросок Мудрости. Если они проваливают спасбросок, то изгоняются на 1 минуту или до тех пор, пока не получат урон.\n" +
            "\n" +
            "Изгнанные существа должны весь свой ход пытаться убежать от вас так далеко, как только могут, и не могут добровольно переместиться в пространство, находящееся в пределах 30 футов от вас. Также они не могут совершать реакции. Из всех действий они могут совершать только Рывок и попытки избежать эффекта, не дающего им перемещаться. Если же двигаться некуда, существа могут использовать действие Уклонение.\n",
)

var OathOfDevotionSpells = AbilityNode(
    name = "Заклинания клятвы преданности",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        val additionalSpellList = mutableSetOf<String>()
        if (abilities.level >= 3) {
            additionalSpellList.add("Защита от зла и добра")
            additionalSpellList.add("Убежище")
        }
        if (abilities.level >= 5) {
            additionalSpellList.add("Малое восстановление")
            additionalSpellList.add("Область истины")
        }
        if (abilities.level >= 9) {
            additionalSpellList.add("Маяк надежды")
            additionalSpellList.add("Рассеивание магии")
        }
        if (abilities.level >= 13) {
            additionalSpellList.add("Свобода перемещения")
            additionalSpellList.add("Страж веры")
        }
        if (abilities.level >= 17) {
            additionalSpellList.add("Небесный огонь")
            additionalSpellList.add("Общение")
        }
        abilities.spellsInfo["Заклинания клятвы преданности"] = CharacterSpells(
            className = abilities.characterClass.className,
            maxKnownSpellsCount = 0,
            maxKnownCantripsCount = 0,
            spellLists = SpellLists(
                knownSpells = additionalSpellList
            )
        )
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 3 && abilities.characterClass == Classes.Paladin
    },
    description =
    "Уровень паладина | Заклинания\n" +
            "       3      | защита от зла и добра, убежище\n" +
            "       5      | малое восстановление, область истины\n" +
            "       9      | маяк надежды, рассеивание магии\n" +
            "       13      | свобода перемещения, страж веры\n" +
            "       17      | небесный огонь, общение\n"
)

var auraOfDevotion: AbilityNode = AbilityNode(
    name = "Аура преданности",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        var radius = "10"
        if (abilities.level >= 18) radius = "30"
        abilities.additionalAbilities["Аура защиты"] =
            "Вы и дружественные вам существа в пределах $radius футов от вас не могут быть очарованы, пока вы находитесь в сознании.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 7 && abilities.characterClass == Classes.Paladin
    },
    description = "Вы и дружественные вам существа в пределах 10 футов от вас не могут быть очарованы, пока вы находитесь в сознании. На 18-м уровне радиус ауры увеличивается до 30 футов.\n"
)

var purityOfSpirit: AbilityNode = AbilityNode(
    name = "Чистота духа",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Чистота духа"] =
            "Вы всегда находитесь под эффектом заклинания защита от зла и добра [protection from evil and good]:\n" +
                    "Вы защищены от существ следующих видов: Аберрации, Исчадия, Небожители, Нежить, Феи и Элементали.\n" +
                    "Защита предоставляет несколько преимуществ. Данные существа совершают с помехой броски атаки по вам. Вы также не можете быть очарованы, испуганы и одержимы ими.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 15 && abilities.characterClass == Classes.Paladin
    },
    description = "Вы всегда находитесь под эффектом заклинания защита от зла и добра [protection from evil and good]:\n" +
            "Вы защищены от существ следующих видов: Аберрации, Исчадия, Небожители, Нежить, Феи и Элементали.\n" +
            "Защита предоставляет несколько преимуществ. Данные существа совершают с помехой броски атаки по вам. Вы также не можете быть очарованы, испуганы и одержимы ими.\n"
)

var holyNimbus: AbilityNode = AbilityNode(
    name = "Святой нимб",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        if (!abilities.currentState.charges.contains("Святой нимб")) {
            abilities.currentState.charges["Святой нимб"] = ChargesCounter(
                current = 1,
                maximum = 1
            )
        }
        abilities.actionsList.add(
            Action(
                name = "Святой нимб",
                description = "Вы можете действием создать ауру солнечного света. В течение 1 минуты от вас исходит яркий свет в радиусе 30 футов, и тусклый свет в пределах еще 30 футов.\n" +
                        "\n" +
                        "Каждый раз, когда враждебное существо начинает ход в этом ярком свете, оно получает урон излучением 10.\n" +
                        "\n" +
                        "Кроме того, в течение этого времени вы совершаете с преимуществом спасброски от заклинаний, наложенных Исчадиями и Нежитью.\n" +
                        "\n" +
                        "Один раз использовав это умение, вы не можете использовать его повторно, пока не закончите продолжительный отдых.\n",
                type = ActionType.Action,
                relatedCharges = "Святой нимб"
            )
        )
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 20 && abilities.characterClass == Classes.Paladin
    },
    description = "Вы можете действием создать ауру солнечного света. В течение 1 минуты от вас исходит яркий свет в радиусе 30 футов, и тусклый свет в пределах еще 30 футов.\n" +
            "\n" +
            "Каждый раз, когда враждебное существо начинает ход в этом ярком свете, оно получает урон излучением 10.\n" +
            "\n" +
            "Кроме того, в течение этого времени вы совершаете с преимуществом спасброски от заклинаний, наложенных Исчадиями и Нежитью.\n" +
            "\n" +
            "Один раз использовав это умение, вы не можете использовать его повторно, пока не закончите продолжительный отдых.\n"
)

var oathOfDevotion = AbilityNode(
    name = "Клятва преданности",
    changesInCharacterInfo = { abilities: CharacterInfo -> abilities },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(oathOfDevotionChanelDivinity.name) }),
        Pair("second", { listOf(OathOfDevotionSpells.name) }),
        Pair("third", { listOf(auraOfDevotion.name) }),
        Pair("fourth", { listOf(purityOfSpirit.name) }),
        Pair("fifth", { listOf(holyNimbus.name) })
    ),
    requirements = { true },
    description = "",
    isNeedsToBeShown = false
)

var mapOfOathOfDevotionAbilities: MutableMap<String, AbilityNode> = mutableMapOf(
    Pair(oathOfDevotionChanelDivinity.name, oathOfDevotionChanelDivinity),
    Pair(OathOfDevotionSpells.name, OathOfDevotionSpells),
    Pair(auraOfDevotion.name, auraOfDevotion),
    Pair(purityOfSpirit.name, purityOfSpirit),
    Pair(holyNimbus.name, holyNimbus),
    Pair(oathOfDevotion.name, oathOfDevotion),
)