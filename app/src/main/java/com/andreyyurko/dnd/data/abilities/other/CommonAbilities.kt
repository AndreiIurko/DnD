package com.andreyyurko.dnd.data.abilities.other


import com.andreyyurko.dnd.data.characterData.Ability
import com.andreyyurko.dnd.data.characterData.Action
import com.andreyyurko.dnd.data.characterData.ActionType
import com.andreyyurko.dnd.data.characterData.character.CharacterInfo
import com.andreyyurko.dnd.data.characterData.ChargesCounter
import com.andreyyurko.dnd.data.characterData.Priority
import com.andreyyurko.dnd.data.characterData.Skill
import com.andreyyurko.dnd.data.characterData.Weapon
import com.andreyyurko.dnd.data.characterData.character.AbilityNode
import com.andreyyurko.dnd.data.characterData.character.abilityToModifier
import kotlin.math.ceil

var baseActionsAN = AbilityNode(
    "base_actions_an",
    { abilities: CharacterInfo ->
        var (damage, toHitBonus) = calculateWeaponProp(
            abilities.currentState.firstWeapon,
            abilities
        )

        var damageBonus = -5
        if (abilities.currentState.firstWeapon.setOfSkills.contains(Ability.Strength)) {
            damageBonus = Integer.max(damageBonus, abilityToModifier(abilities.strength))
        }
        if (abilities.currentState.firstWeapon.setOfSkills.contains(Ability.Dexterity)) {
            damageBonus = Integer.max(damageBonus, abilityToModifier(abilities.dexterity))
        }
        damage = sumTwoRolls(damage, damageBonus.toString())


        abilities.currentState.firstWeapon.shownDamage = damage
        abilities.currentState.firstWeapon.shownToHit = toHitBonus

        abilities.actionsMap["Атака"] =
            Action(
                name = "Атака",
                description = "Совершить одну атаку оружием\n" +
                        "Бонус к попаданию: ${if (toHitBonus < 0) "" else "+"}${toHitBonus}\n" +
                        "Урон: $damage",
                type = ActionType.Action
            )


        abilities.currentState.secondWeapon?.let {
            val (secondDamage, secondToHitBonus) = calculateWeaponProp(it, abilities)

            it.shownSecondWeaponDamage = secondDamage
            it.shownToHit = secondToHitBonus
            abilities.actionsMap["Атака второй рукой"] =
                Action(
                    name = "Атака второй рукой",
                    description = "Если вы совершаете действие «Атака» и атакуете рукопашным оружием со свойством «лёгкое», удерживаемым в одной руке, вы можете бонусным действием атаковать другим рукопашным оружием со свойством «лёгкое», удерживаемым в другой руке.\n" +
                            "\n" +
                            "Вы не добавляете модификатор характеристики к урону от бонусной атаки, если только он не отрицательный.\n" +
                            "\n" +
                            "Если у любого из оружий есть свойство «метательное», вы можете не совершать им рукопашную атаку, а метнуть его.\n" +
                            "Бонус к попаданию: ${if (secondToHitBonus < 0) "" else "+"}${secondToHitBonus}\n" +
                            "Урон: $secondDamage",
                    type = ActionType.Bonus
                )

        }
        abilities.currentState.inventoryRelevantData.forEach { (_, v) ->
            abilities.ac += v.ac
        }
        abilities.actionsMap["Засада"] =
            Action(
                name = "Засада",
                description = "Вы не можете прятаться от существа, которое видит вас, и если вы издадите шум (например, прокричите предупреждение или уроните вазу), вы выдаёте своё местоположение.\n" +
                        "\n" +
                        "Когда вы пытаетесь спрятаться, совершите проверку Ловкости (Скрытность). Пока вас не найдут или вы не прекратите прятаться, результат этой проверки будет противостоять проверкам Мудрости (Восприятие) существ, активно ищущих вас.\n" +
                        "\n" +
                        "Если вы прячетесь, есть шанс, что вас заметят даже без активных поисков. Для определения того, заметило ли вас существо, Мастер сравнивает результат вашей проверки Ловкости (Скрытность) с пассивным значением Мудрости (Восприятие), которое равно 10 + модификатор Мудрости существа, плюс все уместные бонусы и штрафы.\n",
                type = ActionType.Action
            )

        abilities.actionsMap["Использование объекта"] =
            Action(
                name = "Использование объекта",
                description = "Как правило, вы взаимодействуете с объектами в ходе других действий, " +
                        "например когда вынимаете меч из ножен в ходе атаки. " +
                        "Но если предмет требует ваше действие для применения, " +
                        "совершите действие Использование объекта. " +
                        "Это действие также полезно, когда вам нужно взаимодействовать " +
                        "с более чем одним объектом в свой ход.\n",
                type = ActionType.Action
            )

        abilities.actionsMap["Отступление"] =
            Action(
                name = "Отступление",
                description = "Если вы выполнили это действие, " +
                        "ваше перемещение не провоцирует внеочередных атак до конца этого хода\n",
                type = ActionType.Action
            )

        abilities.actionsMap["Подготовка действия"] =
            Action(
                name = "Подготовка действия",
                description = "\n" +
                        "\n" +
                        "Во-первых, определите, какое воспринимаемое событие вызовет вашу реакцию.\n" +
                        "\n" +
                        "Затем выберите действие, либо перемещение, которое будет совершено.\n" +
                        "\n" +
                        "Примеры: «Если культист наступит на люк, я дёрну рычаг и открою его», «Если передо мной встанет гоблин, я отойду прочь».\n" +
                        "\n" +
                        "Когда срабатывает условие, вы можете либо совершить свою реакцию до окончания действия, вызвавшего срабатывание, либо игнорировать срабатывание условия. Подготовленное действие можно совершить только до начала вашего следующего хода. Помните, что вы можете совершить только одну реакцию в раунд.\n" +
                        "\n" +
                        "Если вы подготовили заклинание, вы накладываете его как обычно, но удерживаете энергию, пока не сработает условие. Для того чтобы заклинание можно было подготовить, у него должно быть время накладывания «1 действие», а удерживание магии требует концентрации. Если концентрация прервана, заклинание тратится без всякого эффекта.\n",
                type = ActionType.Action
            )

        abilities.actionsMap["Захват"] =
            Action(
                name = "Захват",
                description = "Если вы хотите схватить существо или побороться с ним, вы можете использовать действие «Атака» для совершения особой рукопашной атаки, захвата. Если вы можете совершать многочисленные атаки действием «Атака», эта атака заменяет одну из них.\n" +
                        "\n" +
                        "Цель вашего захвата должна быть не более чем на одну категорию больше вас, и она должна находиться в пределах вашей досягаемости.\n" +
                        "\n" +
                        "Используя как минимум одну свободную руку, вы пытаетесь схватить цель, совершая проверку захвата: проверку Силы (Атлетика), противопоставленную проверке Силы (Атлетика) или Ловкости (Акробатика) цели (цель сама выбирает, какую характеристику использовать). Проверка автоматически успешна, если цель недееспособна.\n" +
                        "\n" +
                        "Если вы преуспеете, цель становится схваченной. В описании состояния сказано, что его оканчивает, и вы в любой момент можете отпустить цель (действие не требуется).\n" +
                        "\n" +
                        "Высвобождение из захвата. Захваченное существо может пытаться высвободиться. Для этого оно действием совершает проверку Силы (Атлетика) или Ловкость (Акробатика), противопоставленную вашей проверке Силы (Атлетика).\n",
                type = ActionType.Action
            )

        abilities.actionsMap["Толчок"] =
            Action(
                name = "Толчок",
                description = "При помощи действия «Атака» совершить особую рукопашную атаку, чтобы или сбить цель с ног, или оттолкнуть от себя. Если вы можете совершать многочисленные атаки действием «Атака», эта атака может заменить одну из них.\n" +
                        "\n" +
                        "Цель вашего толкания должна быть не более чем на одну категорию больше вас, и она должна находиться в пределах вашей досягаемости.\n" +
                        "\n" +
                        "Вы совершаете проверку Силы (Атлетика), противопоставленную проверке Силы (Атлетика) или Ловкости (Акробатика) цели (цель сама выбирает, какую характеристику использовать). Проверка автоматически успешна, если цель недееспособна.\n" +
                        "\n" +
                        "Если вы преуспеете, вы либо сбиваете цель с ног, либо толкаете её на 5 футов от себя.\n",
                type = ActionType.Action
            )

        abilities.actionsMap["Рывок"] =
            Action(
                name = "Рывок",
                description = "Если вы совершаете действие «Рывок», вы получаете дополнительное перемещение в текущем ходу, равное вашей скорости после применения всех модификаторов.\n",
                type = ActionType.Action
            )

        abilities.actionsMap["Помощь"] =
            Action(
                name = "Помощь",
                description = "Существо, которому вы помогаете, совершит свою следующую проверку характеристики для выполнения задачи с преимуществом, если она будет совершена до начала вашего следующего хода.\n" +
                        "\n" +
                        "В качестве альтернативы, вы можете помочь дружественному существу атаковать другое существо, находящееся в пределах 5 футов от вас. Вы совершаете финт, отвлекаете цель или каким-то другим образом делаете атаку союзника более эффективной. Если ваш союзник атакует цель до начала вашего следующего хода, первый бросок атаки совершается с преимуществом.\n" +
                        "\n" +
                        "Преимущество длится до начала вашего следующего хода.\n",
                type = ActionType.Action
            )

        abilities.actionsMap["Стабилизация"] =
            Action(
                name = "Стабилизация",
                description = "Вы можете действием оказать первую помощь находящемуся без сознания существу и попытаться стабилизировать его, для чего требуется совершить проверку Мудрости (Медицина) Сл 10.\n" +
                        "\n" +
                        "Стабилизированное существо не делает проверки спасброска от смерти, хотя всё ещё имеет 0 хитов и остаётся без сознания. Существо перестаёт быть стабилизированным, если снова получает урон. Стабилизированное существо восстанавливает 1 хит раз в 1к4 часов.\n",
                type = ActionType.Action
            )

        abilities.actionsMap["Использование подготовленного действия"] =
            Action(
                name = "Использование подготовленного действия",
                description = "При достижении условий, выбранных при подготовке вы можете реакцией совершить подготовленное действие\n",
                type = ActionType.Reaction
            )

        abilities.actionsMap["Разговор"] =
            Action(
                name = "Разговор",
                description = "Вы можете общаться любыми доступными вам средствами, короткими фразами и жестами без затраты действия.\n",
                type = ActionType.Additional
            )

        abilities.actionsMap["Прерывание концентрации"] =
            Action(
                name = "Прерывание концентрации",
                description = "Вы можете перестать концентрироваться на заклинании в любое время без затраты действия.\n",
                type = ActionType.Additional
            )

        setupSpellSlots(abilities)
        abilities
    },
    mutableMapOf(),
    { true },
    listOf(listOf()),
    description = "Base actions",
    priority = Priority.DoLast
)

var lastCommonCalculations: AbilityNode = AbilityNode(
    name = "Last common calculations",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.passiveInsightBonus = 10 + abilityToModifier(abilities.wisdom) +
                if (abilities.expertize.contains(Skill.Insight)) abilities.proficiencyBonus * 2
                else if (abilities.skillProficiency.contains(Skill.Insight)) abilities.proficiencyBonus
                else if (abilities.halfProfSet.contains(Ability.Wisdom)) abilities.proficiencyBonus / 2
                else 0
        abilities.passivePerceptionBonus = 10 + abilityToModifier(abilities.wisdom) +
                if (abilities.expertize.contains(Skill.Perception)) abilities.proficiencyBonus * 2
                else if (abilities.skillProficiency.contains(Skill.Perception)) abilities.proficiencyBonus
                else if (abilities.halfProfSet.contains(Ability.Wisdom)) abilities.proficiencyBonus / 2
                else 0
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    description = "",
    priority = Priority.DoLast,
    isNeedsToBeShown = false
)

var commonRoot: AbilityNode = AbilityNode(
    name = "Common_root",
    changesInCharacterInfo = { abilities: CharacterInfo -> abilities },
    getAlternatives = mutableMapOf(
        Pair("first") { listOf(baseActionsAN.name) },
        Pair("second") { listOf(lastCommonCalculations.name) }
    ),
    requirements = { true },
    description = "",
    isNeedsToBeShown = false
)

var mapOfCommonAN: MutableMap<String, AbilityNode> =
    mutableMapOf(
        Pair(baseActionsAN.name, baseActionsAN),
        Pair(lastCommonCalculations.name, lastCommonCalculations),
        Pair(commonRoot.name, commonRoot)
    )

fun calculateWeaponProp(weapon: Weapon, abilities: CharacterInfo): Pair<String, Int> {
    var toHitBonus = -5
    if (weapon.setOfSkills.contains(Ability.Strength)) {
        toHitBonus = Integer.max(toHitBonus, abilityToModifier(abilities.strength))
    }
    if (weapon.setOfSkills.contains(Ability.Dexterity)) {
        toHitBonus = Integer.max(toHitBonus, abilityToModifier(abilities.dexterity))
    }

    if (abilities.weaponProficiency.contains(weapon)) {
        toHitBonus += abilities.proficiencyBonus
    }

    toHitBonus += weapon.toHitBonus

    var damage = weapon.damage

    abilities.currentState.inventoryRelevantData[abilities.currentState.firstWeaponName]?.let {
        toHitBonus += it.weaponToHit
        damage = sumTwoRolls(damage, it.weaponDamage)
    }

    return Pair(damage, toHitBonus)
}

fun sumTwoRolls(roll1: String, roll2: String): String {
    val mapOfDices: MutableMap<String, Int> = mutableMapOf(
        Pair("к4", 0),
        Pair("к6", 0),
        Pair("к8", 0),
        Pair("к10", 0),
        Pair("к12", 0),
        Pair("", 0)
    )

    for (dice in roll1.split('+')) {
        val count = if (dice.split('к')[0] == "") 1 else dice.split('к')[0].toInt()
        val diceType = if (dice.contains("к")) dice.substring(dice.indexOf('к')) else ""
        mapOfDices[diceType] = mapOfDices[diceType]!! + count
    }

    for (dice in roll2.split('+')) {
        val count = if (dice.split('к')[0] == "") 1 else dice.split('к')[0].toInt()
        val diceType = if (dice.contains("к")) dice.substring(dice.indexOf('к')) else ""
        mapOfDices[diceType] = mapOfDices[diceType]!! + count
    }

    var result = ""
    for ((key, value) in mapOfDices.entries) {
        if (value == 0) continue
        if (value < 0 && result != "")
            result = result.substring(0 until result.length - 1)
        result += value.toString() + key
        if (key.contains('к'))
            result += "+"
    }

    if (result == "") result = "0"
    return result
}

fun getSpellSlotsCount(abilities: CharacterInfo): List<Int> {
    when (ceil(abilities.spellCasterLevel).toInt()) {
        1 -> return listOf(2)
        2 -> return listOf(3)
        3 -> return listOf(4, 2)
        4 -> return listOf(4, 3)
        5 -> return listOf(4, 3, 2)
        6 -> return listOf(4, 3, 3)
        7 -> return listOf(4, 3, 3, 1)
        8 -> return listOf(4, 3, 3, 2)
        9 -> return listOf(4, 3, 3, 3, 1)
        10 -> return listOf(4, 3, 3, 3, 2)
        in 11..12 -> return listOf(4, 3, 3, 3, 2, 1)
        in 13..14 -> return listOf(4, 3, 3, 3, 2, 1, 1)
        in 14..16 -> return listOf(4, 3, 3, 3, 2, 1, 1, 1)
        17 -> return listOf(4, 3, 3, 3, 2, 1, 1, 1, 1)
        18 -> return listOf(4, 3, 3, 3, 3, 1, 1, 1, 1)
        19 -> return listOf(4, 3, 3, 3, 3, 2, 1, 1, 1)
        20 -> return listOf(4, 3, 3, 3, 3, 2, 2, 1, 1)
        else -> return listOf()
    }
}

fun setupSpellSlots(abilities: CharacterInfo) {
    val spellSlots = getSpellSlotsCount(abilities)
    for (i in spellSlots.indices) {
        val spellLevel: String = (i + 1).toString()
        if (!abilities.currentState.charges.contains("Ячейки_$spellLevel")) {
            abilities.currentState.charges["Ячейки_$spellLevel"] = ChargesCounter(
                current = spellSlots[i],
                maximum = spellSlots[i]
            )
        }
        if (abilities.currentState.charges["Ячейки_$spellLevel"]!!.maximum < spellSlots[i]) {
            abilities.currentState.charges["Ячейки_$spellLevel"] = ChargesCounter(
                current = spellSlots[i],
                maximum = spellSlots[i]
            )
        }
    }
}