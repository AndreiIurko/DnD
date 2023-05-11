package com.andreyyurko.dnd.data.abilities.classes.paladin

import com.andreyyurko.dnd.data.characterData.*
import com.andreyyurko.dnd.data.characterData.character.AbilityNode
import com.andreyyurko.dnd.data.spells.CharacterSpells
import com.andreyyurko.dnd.data.spells.SpellLists

var oathOfConquestChanelDivinity = AbilityNode(
    name = "Божественный канал клятвы покорения",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Покоряющее присутствие",
                description = "Вы можете использовать ваш «Божественный канал», чтобы источать ужасающее присутствие. Действием вы можете заставить каждое существо по вашему выбору в пределах 30 футов совершить спасбросок Мудрости. При провале существо становится испуганным на 1 минуту. Испуганное существо может повторять этот спасбросок в конце каждого своего хода оканчивая этот эффект на себе при успехе.\n",
                type = ActionType.Action,
                relatedCharges = "Божественный канал"
            )
        )
        abilities.actionsList.add(
            Action(
                name = "Направленный удар",
                description = "Вы можете использовать свой «Божественный канал», чтобы наносить удары со сверхъестественной точностью. Когда вы проводите атаку, вы можете использовать свой «Божественный канал», чтобы получить бонус +10 к этому броску. Вы можете использовать это свойство уже после того, как увидите результат броска, но обязаны сделать выбор до того, как Мастер объявит о попадании или промахе атаки.\n",
                type = ActionType.PartOfAction,
                relatedCharges = "Божественный канал"
            )
        )
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 3 && abilities.characterClass == Classes.Paladin
    },
    description = "Когда вы принимаете эту клятву, вы получаете два следующих варианта применения «Божественного канала».\n" +
            "\n" +
            "Покоряющее присутствие. Вы можете использовать ваш «Божественный канал», чтобы источать ужасающее присутствие. Действием вы можете заставить каждое существо по вашему выбору в пределах 30 футов совершить спасбросок Мудрости. При провале существо становится испуганным на 1 минуту. Испуганное существо может повторять этот спасбросок в конце каждого своего хода оканчивая этот эффект на себе при успехе.\n" +
            "\n" +
            "Направленный удар. Вы можете использовать свой «Божественный канал», чтобы наносить удары со сверхъестественной точностью. Когда вы проводите атаку, вы можете использовать свой «Божественный канал», чтобы получить бонус +10 к этому броску. Вы можете использовать это свойство уже после того, как увидите результат броска, но обязаны сделать выбор до того, как Мастер объявит о попадании или промахе атаки.\n",
)

var OathOfConquestSpells = AbilityNode(
    name = "Заклинания клятвы покорения",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        val additionalSpellList = mutableSetOf<String>()
        if (abilities.level >= 3) {
            additionalSpellList.add("Доспех Агатиса")
            additionalSpellList.add("Приказ")
        }
        if (abilities.level >= 5) {
            additionalSpellList.add("Удержание личности")
            additionalSpellList.add("Божественное оружие")
        }
        if (abilities.level >= 9) {
            additionalSpellList.add("Проклятие")
            additionalSpellList.add("Ужас")
        }
        if (abilities.level >= 13) {
            additionalSpellList.add("Подчинение зверя")
            additionalSpellList.add("Каменная кожа")
        }
        if (abilities.level >= 17) {
            additionalSpellList.add("Облако смерти")
            additionalSpellList.add("Подчинение личности")
        }
        abilities.spellsInfo["Заклинания клятвы покорения"] = CharacterSpells(
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
            "       3      | доспех Агатиса, приказ\n" +
            "       5      | удержание личности, божественное оружие\n" +
            "       9      | проклятие, ужас\n" +
            "       13      | подчинение зверя, каменная кожа\n" +
            "       17      | облако смерти, подчинение личности\n"
)

var auraOfConquest: AbilityNode = AbilityNode(
    name = "Аура покорения",
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

var scornfulRebuke: AbilityNode = AbilityNode(
    name = "Презрительное возмездие",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Презрительное возмездие"] =
            "Те, кто дерзнут вас ударить, психически караются за свою наглость.\n" +
                    "\n" +
                    "Когда существо попадает по вам атакой, это существо получает урон психической энергией, равный вашему модификатору Харизмы (минимум 1), если вы не недееспособны.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 15 && abilities.characterClass == Classes.Paladin
    },
    description = "Те, кто дерзнут вас ударить, психически караются за свою наглость.\n" +
            "\n" +
            "Когда существо попадает по вам атакой, это существо получает урон психической энергией, равный вашему модификатору Харизмы (минимум 1), если вы не недееспособны.\n"
)

var invincibleConqueror: AbilityNode = AbilityNode(
    name = "Непобедимый покоритель",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        if (!abilities.currentState.charges.contains("Непобедимый покоритель")) {
            abilities.currentState.charges["Непобедимый покоритель"] = ChargesCounter(
                current = 1,
                maximum = 1
            )
        }
        abilities.actionsList.add(
            Action(
                name = "Непобедимый покоритель",
                description = "Вы получаете способность овладевать необычайной воинской удалью. Действием вы можете магически превратиться в аватар покорения, получая следующие преимущества на 1 минуту:\n" +
                        "\n" +
                        "    Вы получаете сопротивление ко всему урону.\n" +
                        "    Когда вы совершаете действие Атака в ваш ход, вы можете совершить одну дополнительную атаку частью этого действия.\n" +
                        "    Ваши рукопашные атаки оружием наносят критический урон при результатах «19» и «20» броска к20.\n" +
                        "\n" +
                        "Использовав это умение, вы не можете использовать его повторно до окончания продолжительного отдыха.\n",
                type = ActionType.Action,
                relatedCharges = "Непобедимый покоритель"
            )
        )
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 20 && abilities.characterClass == Classes.Paladin
    },
    description = "Вы получаете способность овладевать необычайной воинской удалью. Действием вы можете магически превратиться в аватар покорения, получая следующие преимущества на 1 минуту:\n" +
            "\n" +
            "    Вы получаете сопротивление ко всему урону.\n" +
            "    Когда вы совершаете действие Атака в ваш ход, вы можете совершить одну дополнительную атаку частью этого действия.\n" +
            "    Ваши рукопашные атаки оружием наносят критический урон при результатах «19» и «20» броска к20.\n" +
            "\n" +
            "Использовав это умение, вы не можете использовать его повторно до окончания продолжительного отдыха.\n"
)

var oathOfConquest = AbilityNode(
    name = "Клятва покорения",
    changesInCharacterInfo = { abilities: CharacterInfo -> abilities },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(oathOfConquestChanelDivinity.name) }),
        Pair("second", { listOf(OathOfConquestSpells.name) }),
        Pair("third", { listOf(auraOfConquest.name) }),
        Pair("fourth", { listOf(scornfulRebuke.name) }),
        Pair("fifth", { listOf(invincibleConqueror.name) })
    ),
    requirements = { true },
    description = "",
    isNeedsToBeShown = false
)

var mapOfOathOfConquestAbilities: MutableMap<String, AbilityNode> = mutableMapOf(
    Pair(oathOfConquestChanelDivinity.name, oathOfConquestChanelDivinity),
    Pair(OathOfConquestSpells.name, OathOfConquestSpells),
    Pair(auraOfConquest.name, auraOfConquest),
    Pair(scornfulRebuke.name, scornfulRebuke),
    Pair(invincibleConqueror.name, invincibleConqueror),
    Pair(oathOfConquest.name, oathOfConquest),
)