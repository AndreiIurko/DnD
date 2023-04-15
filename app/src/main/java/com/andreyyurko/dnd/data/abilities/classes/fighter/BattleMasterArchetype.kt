package com.andreyyurko.dnd.data.abilities.classes.fighter

import com.andreyyurko.dnd.data.characterData.*
import com.andreyyurko.dnd.data.characterData.character.AbilityNode

var evasiveFootworkManeuver = AbilityNode(
    name = "Активное уклонение",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        val dice = if (abilities.level >= 18) "к12" else if (abilities.level >= 10) "к10" else "к8"
        abilities.actionsList.add(Action(
            name = "Прием: активное уклонение",
            description = "При перемещении вы можете потратить одну кость превосходства равную $dice, совершить её бросок и добавить выпавшее значение к КД, пока не прекратите перемещение.",
            type = ActionType.PartOfAction,
            "Боевое превосходство"
        ))
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        var isValid = true
        for (action in abilities.actionsList) {
            if (action.name == "Прием: активное уклонение") isValid = false
        }
        isValid
    },
    description = "При перемещении вы можете потратить одну кость превосходства, совершить её бросок и добавить выпавшее значение к КД, пока не прекратите перемещение."
)

var lungingAttackManeuver = AbilityNode(
    name = "Атака с выпадом",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        val dice = if (abilities.level >= 18) "к12" else if (abilities.level >= 10) "к10" else "к8"
        abilities.actionsList.add(Action(
            name = "Прием: атака с выпадом",
            description = "Если вы в свой ход совершаете рукопашную атаку оружием, вы можете потратить одну кость превосходства равную $dice, чтобы увеличить досягаемость этой атаки на 5 футов. В случае попадания вы добавляете кость превосходства к броску урона этой атаки.",
            type = ActionType.PartOfAction,
            relatedCharges = "Боевое превосходство"
        ))
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        var isValid = true
        for (action in abilities.actionsList) {
            if (action.name == "Прием: атака с выпадом") isValid = false
        }
        isValid
    },
    description = "Если вы в свой ход совершаете рукопашную атаку оружием, вы можете потратить одну кость превосходства, чтобы увеличить досягаемость этой атаки на 5 футов. В случае попадания вы добавляете кость превосходства к броску урона этой атаки."
)

var maneuveringAttackManeuver = AbilityNode(
    name = "Атака с манёвром",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        val dice = if (abilities.level >= 18) "к12" else if (abilities.level >= 10) "к10" else "к8"
        abilities.actionsList.add(Action(
            name = "Прием: атака с манёвром",
            description = "Если вы попадаете по существу атакой оружием, вы можете потратить одну кость превосходства равную $dice, чтобы один из ваших товарищей смог переместиться в более выгодное положение. Вы добавляете кость превосходства к броску урона этой атаки и выбираете дружественное существо, которое может видеть или слышать вас. Это существо может реакцией переместиться на расстояние до половины своей скорости, не провоцируя при этом атаки от цели вашей атаки.",
            type = ActionType.PartOfAction,
            relatedCharges = "Боевое превосходство"
        ))
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        var isValid = true
        for (action in abilities.actionsList) {
            if (action.name == "Прием: атака с манёвром") isValid = false
        }
        isValid
    },
    description = "Если вы попадаете по существу атакой оружием, вы можете потратить одну кость превосходства, чтобы один из ваших товарищей смог переместиться в более выгодное положение. Вы добавляете кость превосходства к броску урона этой атаки и выбираете дружественное существо, которое может видеть или слышать вас. Это существо может реакцией переместиться на расстояние до половины своей скорости, не провоцируя при этом атаки от цели вашей атаки."
)

var menacingAttackManeuver = AbilityNode(
    name = "Атака с угрозой",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        val dice = if (abilities.level >= 18) "к12" else if (abilities.level >= 10) "к10" else "к8"
        abilities.actionsList.add(Action(
            name = "Прием: атака с угрозой",
            description = "Если вы попадаете по существу атакой оружием, вы можете потратить одну кость превосходства равную $dice, чтобы попытаться напугать цель. Вы добавляете кость превосходства к броску урона этой атаки, а цель должна совершить спасбросок Мудрости. При провале цель испугана вами до конца вашего следующего хода.",
            type = ActionType.PartOfAction,
            relatedCharges = "Боевое превосходство"
        ))
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        var isValid = true
        for (action in abilities.actionsList) {
            if (action.name == "Прием: атака с угрозой") isValid = false
        }
        isValid
    },
    description = "Если вы попадаете по существу атакой оружием, вы можете потратить одну кость превосходства, чтобы попытаться напугать цель. Вы добавляете кость превосходства к броску урона этой атаки, а цель должна совершить спасбросок Мудрости. При провале цель испугана вами до конца вашего следующего хода."
)

var feintingAttackManeuver = AbilityNode(
    name = "Атака с финтом",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        val dice = if (abilities.level >= 18) "к12" else if (abilities.level >= 10) "к10" else "к8"
        abilities.actionsList.add(Action(
            name = "Прием: атака с финтом",
            description = "Вы можете в свой ход потратить одну кость превосходства равную $dice и бонусным действием совершить финт, выбрав в качестве цели одно существо в пределах 5 футов. Следующий бросок атаки по этому существу в этом ходу вы совершаете с преимуществом. Если атака попадает, добавьте кость превосходства к броску урона этой атаки. Оба преимущества пропадают, если вы не используете их в том же ходу, в котором получили их.",
            type = ActionType.Bonus,
            relatedCharges = "Боевое превосходство"
        ))
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        var isValid = true
        for (action in abilities.actionsList) {
            if (action.name == "Прием: атака с финтом") isValid = false
        }
        isValid
    },
    description = "Вы можете в свой ход потратить одну кость превосходства и бонусным действием совершить финт, выбрав в качестве цели одно существо в пределах 5 футов. Следующий бросок атаки по этому существу в этом ходу вы совершаете с преимуществом. Если атака попадает, добавьте кость превосходства к броску урона этой атаки. Оба преимущества пропадают, если вы не используете их в том же ходу, в котором получили их."
)

var disarmingAttackManeuver = AbilityNode(
    name = "Обезоруживающая атака",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        val dice = if (abilities.level >= 18) "к12" else if (abilities.level >= 10) "к10" else "к8"
        abilities.actionsList.add(Action(
            name = "Прием: обезоруживающая атака",
            description = "Если вы попадаете по существу атакой оружием, вы можете потратить одну кость превосходства равную $dice, чтобы попытаться обезоружить противника, заставляя его выронить один предмет по вашему выбору, который он держит. Вы добавляете кость превосходства к броску урона атаки, а цель должна совершить спасбросок Силы. В случае провала она роняет выбранный вами предмет. Предмет падает у её ног.",
            type = ActionType.PartOfAction,
            relatedCharges = "Боевое превосходство"
        ))
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        var isValid = true
        for (action in abilities.actionsList) {
            if (action.name == "Прием: обезоруживающая атака") isValid = false
        }
        isValid
    },
    description = "Если вы попадаете по существу атакой оружием, вы можете потратить одну кость превосходства, чтобы попытаться обезоружить противника, заставляя его выронить один предмет по вашему выбору, который он держит. Вы добавляете кость превосходства к броску урона атаки, а цель должна совершить спасбросок Силы. В случае провала она роняет выбранный вами предмет. Предмет падает у её ног."
)

var tripAttackManeuver = AbilityNode(
    name = "Опрокидывающая атака",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        val dice = if (abilities.level >= 18) "к12" else if (abilities.level >= 10) "к10" else "к8"
        abilities.actionsList.add(Action(
            name = "Прием: опрокидывающая атака",
            description = "Если вы попадаете по существу атакой оружием, вы можете потратить одну кость превосходства равную $dice, чтобы попытаться сбить цель с ног. Вы добавляете кость превосходства к броску урона атаки, и, если размер цели Большой или меньше, она должна совершить спасбросок Силы. В случае провала вы сбиваете цель с ног.",
            type = ActionType.PartOfAction,
            relatedCharges = "Боевое превосходство"
        ))
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        var isValid = true
        for (action in abilities.actionsList) {
            if (action.name == "Прием: опрокидывающая атака") isValid = false
        }
        isValid
    },
    description = "Если вы попадаете по существу атакой оружием, вы можете потратить одну кость превосходства, чтобы попытаться сбить цель с ног. Вы добавляете кость превосходства к броску урона атаки, и, если размер цели Большой или меньше, она должна совершить спасбросок Силы. В случае провала вы сбиваете цель с ног."
)

var riposteManeuver = AbilityNode(
    name = "Ответный удар",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        val dice = if (abilities.level >= 18) "к12" else if (abilities.level >= 10) "к10" else "к8"
        abilities.actionsList.add(Action(
            name = "Прием: ответный удар",
            description = "Если существо промахивается по вам рукопашной атакой, вы можете реакцией потратить одну кость превосходства равную $dice, чтобы совершить рукопашную атаку оружием по этому существу. Если вы попадаете, вы добавляете кость превосходства к броску урона этой атаки.",
            type = ActionType.Reaction,
            relatedCharges = "Боевое превосходство"
        ))
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        var isValid = true
        for (action in abilities.actionsList) {
            if (action.name == "Прием: ответный удар") isValid = false
        }
        isValid
    },
    description = "Если существо промахивается по вам рукопашной атакой, вы можете реакцией потратить одну кость превосходства, чтобы совершить рукопашную атаку оружием по этому существу. Если вы попадаете, вы добавляете кость превосходства к броску урона этой атаки."
)

var distractingStrikeManeuver = AbilityNode(
    name = "Отвлекающий удар",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        val dice = if (abilities.level >= 18) "к12" else if (abilities.level >= 10) "к10" else "к8"
        abilities.actionsList.add(Action(
            name = "Прием: отвлекающий удар",
            description = "Если вы попадаете по существу атакой оружием, вы можете потратить одну кость превосходства равную $dice, чтобы отвлечь существо, открывая его для ваших союзников. Вы добавляете кость превосходства к броску урона этой атаки. Следующий бросок атаки по этой цели любого существа кроме вас совершается с преимуществом, если атака совершается до начала вашего следующего хода.",
            type = ActionType.PartOfAction,
            relatedCharges = "Боевое превосходство"
        ))
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        var isValid = true
        for (action in abilities.actionsList) {
            if (action.name == "Прием: отвлекающий удар") isValid = false
        }
        isValid
    },
    description = "Если вы попадаете по существу атакой оружием, вы можете потратить одну кость превосходства, чтобы отвлечь существо, открывая его для ваших союзников. Вы добавляете кость превосходства к броску урона этой атаки. Следующий бросок атаки по этой цели любого существа кроме вас совершается с преимуществом, если атака совершается до начала вашего следующего хода."
)

var parryManeuver = AbilityNode(
    name = "Парирование",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        val dice = if (abilities.level >= 18) "к12" else if (abilities.level >= 10) "к10" else "к8"
        abilities.actionsList.add(Action(
            name = "Прием: парирование",
            description = "Если другое существо причиняет вам урон рукопашной атакой, вы можете реакцией потратить одну кость превосходства равную $dice, чтобы уменьшить урон на величину, равную броску вашей кости превосходства + ваш модификатор Ловкости.",
            type = ActionType.Reaction,
            relatedCharges = "Боевое превосходство"
        ))
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        var isValid = true
        for (action in abilities.actionsList) {
            if (action.name == "Прием: парирование") isValid = false
        }
        isValid
    },
    description = "Если другое существо причиняет вам урон рукопашной атакой, вы можете реакцией потратить одну кость превосходства, чтобы уменьшить урон на величину, равную броску вашей кости превосходства + ваш модификатор Ловкости."
)

var goadingAttackManeuver = AbilityNode(
    name = "Провоцирующая атака",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        val dice = if (abilities.level >= 18) "к12" else if (abilities.level >= 10) "к10" else "к8"
        abilities.actionsList.add(Action(
            name = "Прием: провоцирующая атака",
            description = "Если вы попадаете по существу атакой оружием, вы можете потратить одну кость превосходства равную $dice, чтобы попытаться спровоцировать противника атаковать вас. Вы добавляете кость превосходства к броску урона этой атаки, а цель должна совершить спасбросок Мудрости. При провале цель до конца вашего следующего хода совершает с помехой броски атаки по всем целям, кроме вас.",
            type = ActionType.PartOfAction,
            relatedCharges = "Боевое превосходство"
        ))
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        var isValid = true
        for (action in abilities.actionsList) {
            if (action.name == "Прием: провоцирующая атака") isValid = false
        }
        isValid
    },
    description = "Если вы попадаете по существу атакой оружием, вы можете потратить одну кость превосходства, чтобы попытаться спровоцировать противника атаковать вас. Вы добавляете кость превосходства к броску урона этой атаки, а цель должна совершить спасбросок Мудрости. При провале цель до конца вашего следующего хода совершает с помехой броски атаки по всем целям, кроме вас."
)

var rallyManeuver = AbilityNode(
    name = "Сплочение",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        val dice = if (abilities.level >= 18) "к12" else if (abilities.level >= 10) "к10" else "к8"
        abilities.actionsList.add(Action(
            name = "Прием: сплочение",
            description = "Вы можете в свой ход бонусным действием потратить одну кость превосходства равную $dice, чтобы поддержать решимость одного из ваших спутников. Если вы это сделаете, выберите дружественное существо, которое может видеть или слышать вас. Это существо получает временные хиты, равные броску кости превосходства + ваш модификатор Харизмы.",
            type = ActionType.Bonus,
            relatedCharges = "Боевое превосходство"
        ))
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        var isValid = true
        for (action in abilities.actionsList) {
            if (action.name == "Прием: сплочение") isValid = false
        }
        isValid
    },
    description = "Вы можете в свой ход бонусным действием потратить одну кость превосходства, чтобы поддержать решимость одного из ваших спутников. Если вы это сделаете, выберите дружественное существо, которое может видеть или слышать вас. Это существо получает временные хиты, равные броску кости превосходства + ваш модификатор Харизмы."
)

var pushingAttackManeuver = AbilityNode(
    name = "Толкающая атака",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        val dice = if (abilities.level >= 18) "к12" else if (abilities.level >= 10) "к10" else "к8"
        abilities.actionsList.add(Action(
            name = "Прием: толкающая атака",
            description = "Если вы попадаете по существу атакой оружием, вы можете потратить одну кость превосходства равную $dice, чтобы попытаться оттолкнуть цель. Вы добавляете кость превосходства к броску урона атаки, и, если размер цели Большой или меньше, она должна совершить спасбросок Силы. При провале вы толкаете цель на расстояние до 15 футов от себя.",
            type = ActionType.PartOfAction,
            relatedCharges = "Боевое превосходство"
        ))
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        var isValid = true
        for (action in abilities.actionsList) {
            if (action.name == "Прием: толкающая атака") isValid = false
        }
        isValid
    },
    description = "Если вы попадаете по существу атакой оружием, вы можете потратить одну кость превосходства, чтобы попытаться оттолкнуть цель. Вы добавляете кость превосходства к броску урона атаки, и, если размер цели Большой или меньше, она должна совершить спасбросок Силы. При провале вы толкаете цель на расстояние до 15 футов от себя."
)

var precisionAttackManeuver = AbilityNode(
    name = "Точная атака",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        val dice = if (abilities.level >= 18) "к12" else if (abilities.level >= 10) "к10" else "к8"
        abilities.actionsList.add(Action(
            name = "Прием: точная атака",
            description = "Если вы совершаете бросок атаки оружием по существу, вы можете потратить одну кость превосходства равную $dice, чтобы добавить её к броску. Вы можете использовать этот приём до или после совершения броска атаки, но до применения эффектов атаки.",
            type = ActionType.PartOfAction,
            relatedCharges = "Боевое превосходство"
        ))
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        var isValid = true
        for (action in abilities.actionsList) {
            if (action.name == "Прием: точная атака") isValid = false
        }
        isValid
    },
    description = "Если вы совершаете бросок атаки оружием по существу, вы можете потратить одну кость превосходства, чтобы добавить её к броску. Вы можете использовать этот приём до или после совершения броска атаки, но до применения эффектов атаки."
)

var commanderStrikeManeuver = AbilityNode(
    name = "Удар командующего",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        val dice = if (abilities.level >= 18) "к12" else if (abilities.level >= 10) "к10" else "к8"
        abilities.actionsList.add(Action(
            name = "Прием: удар командующего",
            description = "Если вы совершаете в свой ход действие Атака, вы можете отказаться от одной из ваших атак и бонусным действием направить удар одного из ваших соратников. Если вы это сделаете, выберите дружественное существо, которое может видеть или слышать вас и потратьте одну кость превосходства равную $dice. Это существо может немедленно совершить реакцией одну атаку оружием, добавив кость превосходства к броску урона этой атаки.",
            type = ActionType.PartOfAction,
            relatedCharges = "Боевое превосходство"
        ))
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        var isValid = true
        for (action in abilities.actionsList) {
            if (action.name == "Прием: удар командующего") isValid = false
        }
        isValid
    },
    description = "Если вы совершаете в свой ход действие Атака, вы можете отказаться от одной из ваших атак и бонусным действием направить удар одного из ваших соратников. Если вы это сделаете, выберите дружественное существо, которое может видеть или слышать вас и потратьте одну кость превосходства. Это существо может немедленно совершить реакцией одну атаку оружием, добавив кость превосходства к броску урона этой атаки."
)

var sweepingAttackManeuver = AbilityNode(
    name = "Широкая атака",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        val dice = if (abilities.level >= 18) "к12" else if (abilities.level >= 10) "к10" else "к8"
        abilities.actionsList.add(Action(
            name = "Прием: широкая атака",
            description = "Если вы попадаете по существу атакой рукопашным оружием, вы можете потратить одну кость превосходства равную $dice, чтобы попытаться причинить урон другому существу этой же атакой. Выберите другое существо в пределах 5 футов от первоначальной цели и в пределах вашей досягаемости. Если исходный бросок атаки попал бы по второму существу, оно получает урон, равный броску кости превосходства. Урон того же вида, что и для исходной атаки.",
            type = ActionType.PartOfAction,
            relatedCharges = "Боевое превосходство"
        ))
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        var isValid = true
        for (action in abilities.actionsList) {
            if (action.name == "Прием: широкая атака") isValid = false
        }
        isValid
    },
    description = "Если вы попадаете по существу атакой рукопашным оружием, вы можете потратить одну кость превосходства, чтобы попытаться причинить урон другому существу этой же атакой. Выберите другое существо в пределах 5 футов от первоначальной цели и в пределах вашей досягаемости. Если исходный бросок атаки попал бы по второму существу, оно получает урон, равный броску кости превосходства. Урон того же вида, что и для исходной атаки."
)

var combatSuperiority = AbilityNode(
    name = "Боевое превосходство",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        if (!abilities.currentState.charges.contains("Боевое превосходство")) {
            abilities.currentState.charges["Боевое превосходство"] = ChargesCounter(
                current = 4,
                maximum = 4
            )
        }
        if (abilities.currentState.charges["Боевое превосходства"]?.maximum == 4 && abilities.level >= 7) {
            abilities.currentState.charges["Боевое превосходство"] = ChargesCounter(
                current = 5,
                maximum = 5
            )
        }
        if (abilities.currentState.charges["Боевое превосходство"]?.maximum == 5 && abilities.level >= 13) {
            abilities.currentState.charges["Боевое превосходство"] = ChargesCounter(
                current = 6,
                maximum = 6
            )
        }
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(evasiveFootworkManeuver.name, commanderStrikeManeuver.name, distractingStrikeManeuver.name, disarmingAttackManeuver.name,
            feintingAttackManeuver.name, goadingAttackManeuver.name, lungingAttackManeuver.name, maneuveringAttackManeuver.name, menacingAttackManeuver.name,
            parryManeuver.name, pushingAttackManeuver.name, precisionAttackManeuver.name, rallyManeuver.name, riposteManeuver.name, sweepingAttackManeuver.name,
            tripAttackManeuver.name)),
        Pair("second", listOf(evasiveFootworkManeuver.name, commanderStrikeManeuver.name, distractingStrikeManeuver.name, disarmingAttackManeuver.name,
            feintingAttackManeuver.name, goadingAttackManeuver.name, lungingAttackManeuver.name, maneuveringAttackManeuver.name, menacingAttackManeuver.name,
            parryManeuver.name, pushingAttackManeuver.name, precisionAttackManeuver.name, rallyManeuver.name, riposteManeuver.name, sweepingAttackManeuver.name,
            tripAttackManeuver.name)),
        Pair("third", listOf(evasiveFootworkManeuver.name, commanderStrikeManeuver.name, distractingStrikeManeuver.name, disarmingAttackManeuver.name,
            feintingAttackManeuver.name, goadingAttackManeuver.name, lungingAttackManeuver.name, maneuveringAttackManeuver.name, menacingAttackManeuver.name,
            parryManeuver.name, pushingAttackManeuver.name, precisionAttackManeuver.name, rallyManeuver.name, riposteManeuver.name, sweepingAttackManeuver.name,
            tripAttackManeuver.name)),
    ),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 3 && abilities.characterClass == Classes.Fighter
    },
    description = "Если вы выбираете этот архетип, вы изучаете приёмы, использующие специальные кости, называемые костями превосходства.\n" +
            "\n" +
            "Приёмы. Вы изучаете три приёма на ваш выбор. Большинство приёмов тем или иным образом усиливают атаку. Во время одной атаки вы можете использовать только один приём. Вы изучаете два дополнительных приёма при достижении 7-го, 10-го и 15-го уровней. Каждый раз, при изучении новых приёмов, вы можете также заменить один из известных вам приёмов на другой.\n" +
            "\n" +
            "Кости превосходства. У вас есть четыре кости превосходства. Это кости к8. Кости превосходства тратятся при использовании. Вы восполняете все потраченные кости в конце короткого или продолжительного отдыха.\n" +
            "\n" +
            "Вы получаете ещё по одной кости превосходства на 7-м и 15-м уровнях.\n" +
            "\n" +
            "Спасброски. Некоторые из ваших приёмов требуют от цели спасброска, чтобы избежать эффекта приёма. Сложность такого спасброска рассчитывается следующим образом:\n" +
            "\n" +
            "Сложность спасброска приёма = 8 + ваш бонус мастерства + ваш модификатор Силы или Ловкости (на ваш выбор)"
)

var studentOfWar = AbilityNode(
    name = "Ученик войны",
    changesInCharacterInfo = { abilities: CharacterInfo -> abilities },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 3 && abilities.characterClass == Classes.Fighter
    },
    description = "Вы осваиваете владением одним из ремесленных инструментов на ваш выбор."
)

var knowYourEnemy = AbilityNode(
    name = "Познай своего врага",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities.add("Если вы потратите как минимум 1 минуту, рассматривая, или по другому взаимодействуя с существом вне боя, вы можете узнать некоторую информацию о его способностях в сравнении с вашими. Мастер сообщит вам, равняется ли существо, превосходит или уступает вам в двух характеристиках на ваш выбор из перечисленных:\n" +
                "\n" +
                "    Значение Силы\n" +
                "    Значение Ловкости\n" +
                "    Значение Телосложения\n" +
                "    Класс Доспеха\n" +
                "    Текущие хиты\n" +
                "    Общее количество уровней (если есть)\n" +
                "    Количество уровней в классе Воин (если есть)\n")
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 7 && abilities.characterClass == Classes.Fighter
    },
    description = "Если вы потратите как минимум 1 минуту, рассматривая, или по другому взаимодействуя с существом вне боя, вы можете узнать некоторую информацию о его способностях в сравнении с вашими. Мастер сообщит вам, равняется ли существо, превосходит или уступает вам в двух характеристиках на ваш выбор из перечисленных:\n" +
            "\n" +
            "    Значение Силы\n" +
            "    Значение Ловкости\n" +
            "    Значение Телосложения\n" +
            "    Класс Доспеха\n" +
            "    Текущие хиты\n" +
            "    Общее количество уровней (если есть)\n" +
            "    Количество уровней в классе Воин (если есть)\n"
)

var improvedCombatSuperiority = AbilityNode(
    name = "Улучшенное боевое превосходство",
    changesInCharacterInfo = { abilities: CharacterInfo -> abilities },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 10 && abilities.characterClass == Classes.Fighter
    },
    description = "Ваша кость превосходства увеличивается до к10. На 18-м уровне — до к12."
)

var relentless = AbilityNode(
    name = "Неослабевающий",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities.add("Если вы совершаете бросок инициативы, не имея костей превосходства, вы получаете одну.")
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 15 && abilities.characterClass == Classes.Fighter
    },
    description = "Если вы совершаете бросок инициативы, не имея костей превосходства, вы получаете одну."
)

var battleMaster = AbilityNode(
    name = "Мастер боевых искусств",
    changesInCharacterInfo = { abilities: CharacterInfo -> abilities },
    alternatives = mutableMapOf(
        Pair("first", listOf(combatSuperiority.name)),
        Pair("second", listOf(studentOfWar.name)),
        Pair("third", listOf(knowYourEnemy.name)),
        Pair("fourth", listOf(improvedCombatSuperiority.name)),
        Pair("fifth", listOf(relentless.name))
    ),
    requirements = { true },
    description = "",
    isNeedsToBeShown = false
)

var mapOfBattleMasterAbilities: MutableMap<String, AbilityNode> = mutableMapOf(
    Pair(combatSuperiority.name, combatSuperiority),
    Pair(studentOfWar.name, studentOfWar),
    Pair(knowYourEnemy.name, knowYourEnemy),
    Pair(improvedCombatSuperiority.name, improvedCombatSuperiority),
    Pair(relentless.name, relentless),
    Pair(battleMaster.name, battleMaster),
    Pair(evasiveFootworkManeuver.name, evasiveFootworkManeuver),
    Pair(commanderStrikeManeuver.name, commanderStrikeManeuver),
    Pair(distractingStrikeManeuver.name, distractingStrikeManeuver),
    Pair(disarmingAttackManeuver.name, disarmingAttackManeuver),
    Pair(feintingAttackManeuver.name, feintingAttackManeuver),
    Pair(goadingAttackManeuver.name, goadingAttackManeuver),
    Pair(lungingAttackManeuver.name, lungingAttackManeuver),
    Pair(maneuveringAttackManeuver.name, maneuveringAttackManeuver),
    Pair(menacingAttackManeuver.name, menacingAttackManeuver),
    Pair(parryManeuver.name, parryManeuver),
    Pair(pushingAttackManeuver.name, pushingAttackManeuver),
    Pair(precisionAttackManeuver.name, precisionAttackManeuver),
    Pair(rallyManeuver.name, rallyManeuver),
    Pair(riposteManeuver.name, riposteManeuver),
    Pair(sweepingAttackManeuver.name, sweepingAttackManeuver),
    Pair(tripAttackManeuver.name, tripAttackManeuver)
)