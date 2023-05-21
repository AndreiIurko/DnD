package com.andreyyurko.dnd.data.abilities.classes.ranger

import com.andreyyurko.dnd.data.abilities.classes.evasion
import com.andreyyurko.dnd.data.abilities.classes.rogue.uncannyDodge
import com.andreyyurko.dnd.data.characterData.Action
import com.andreyyurko.dnd.data.characterData.ActionType
import com.andreyyurko.dnd.data.characterData.character.CharacterInfo
import com.andreyyurko.dnd.data.characterData.Classes
import com.andreyyurko.dnd.data.characterData.character.AbilityNode

var colossusSlayer = AbilityNode(
    name = "Убийца колоссов",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsMap["Убийца колоссов"] =
            Action(
                name = "Убийца колоссов",
                description = "Ваше упорство может повергнуть самых мощных врагов. Если вы попадаете по существу атакой оружием, это существо получает дополнительный урон 1к8, если его хиты уже ниже максимума. Вы можете причинить этот дополнительный урон только один раз в ход.\n",
                type = ActionType.PartOfAction,
            )

        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    description = "Ваше упорство может повергнуть самых мощных врагов. Если вы попадаете по существу атакой оружием, это существо получает дополнительный урон 1к8, если его хиты уже ниже максимума. Вы можете причинить этот дополнительный урон только один раз в ход.\n"
)

var giantKiller = AbilityNode(
    name = "Убийца великанов",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsMap["Убийца великанов"] =
            Action(
                name = "Убийца великанов",
                description = "Если Большое или еще большее существо в пределах 5 футов попадает или промахивается по вам атакой, вы можете реакцией атаковать это существо сразу после его атаки, при условии, что вы можете видеть его.\n",
                type = ActionType.Reaction,
            )

        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    description = "Если Большое или еще большее существо в пределах 5 футов попадает или промахивается по вам атакой, вы можете реакцией атаковать это существо сразу после его атаки, при условии, что вы можете видеть его.\n"
)

var hordeBreaker = AbilityNode(
    name = "Сокрушитель орд",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsMap["Сокрушитель орд"] =
            Action(
                name = "Сокрушитель орд",
                description = "Один раз в каждый свой ход, когда вы совершаете атаку оружием, вы можете совершить еще одну атаку тем же оружием по другому существу, находящемуся в пределах 5 футов от первичной цели, и находящемуся в пределах досягаемости вашего оружия.\n",
                type = ActionType.PartOfAction,
            )

        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    description = "Один раз в каждый свой ход, когда вы совершаете атаку оружием, вы можете совершить еще одну атаку тем же оружием по другому существу, находящемуся в пределах 5 футов от первичной цели, и находящемуся в пределах досягаемости вашего оружия.\n"
)

var huntersPrey = AbilityNode(
    name = "Добыча охотника",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(colossusSlayer.name, giantKiller.name, hordeBreaker.name) })
    ),
    requirements = { abilities: CharacterInfo -> abilities.level >= 3 && abilities.characterClass == Classes.Ranger },
    description = "Вы получаете одно из следующих умений на свой выбор:\n"
)

var escapeTheHorde = AbilityNode(
    name = "Побег от орды",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Побег от орды"] =
            "Провоцированные атаки по вам совершаются с помехой.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    description = "Провоцированные атаки по вам совершаются с помехой.\n"
)

var multiattackDefence = AbilityNode(
    name = "Защита от мультиатаки",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Защита от мультиатаки"] =
            "Если существо попадает по вам атакой, вы получаете бонус +4 к КД против всех последующих атак этого существа до конца хода.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    description = "Если существо попадает по вам атакой, вы получаете бонус +4 к КД против всех последующих атак этого существа до конца хода.\n"
)

var steelWill = AbilityNode(
    name = "Стальная воля",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Стальная воля"] =
            "Вы совершаете с преимуществом спасброски от испуга.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    description = "Вы совершаете с преимуществом спасброски от испуга.\n"
)

var defenciveTactics = AbilityNode(
    name = "Оборонительная тактика",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(escapeTheHorde.name, multiattackDefence.name, steelWill.name) })
    ),
    requirements = { abilities: CharacterInfo -> abilities.level >= 7 && abilities.characterClass == Classes.Ranger },
    description = "Вы получаете одно из следующих умений на свой выбор:\n"
)

var volley = AbilityNode(
    name = "Залп",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsMap["Залп"] =
            Action(
                name = "Залп",
                description = "Вы можете действием совершить дальнобойные атаки по любому количеству видимых вами существ, находящихся в пределах 10 футов от одной точки, и находящихся в пределах дистанции вашего оружия. У вас должны быть боеприпасы для каждой атаки, как обычно, и вы должны совершить отдельный бросок атаки для каждой цели.\n",
                type = ActionType.Action,
            )

        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    description = "Вы можете действием совершить дальнобойные атаки по любому количеству видимых вами существ, находящихся в пределах 10 футов от одной точки, и находящихся в пределах дистанции вашего оружия. У вас должны быть боеприпасы для каждой атаки, как обычно, и вы должны совершить отдельный бросок атаки для каждой цели.\n"
)

var whirldwindAttack = AbilityNode(
    name = "Вихревая атака",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsMap["Вихревая атака"] =
            Action(
                name = "Вихревая атака",
                description = "Вы можете действием совершить рукопашные атаки по любому количеству существ в пределах 5 футов от себя, совершая отдельный бросок атаки по каждой цели.\n",
                type = ActionType.Action,
            )

        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    description = "Вы можете действием совершить рукопашные атаки по любому количеству существ в пределах 5 футов от себя, совершая отдельный бросок атаки по каждой цели.\n"
)

var huntersMultiattack = AbilityNode(
    name = "Мультиатака охотника",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(volley.name, whirldwindAttack.name) })
    ),
    requirements = { abilities: CharacterInfo -> abilities.level >= 11 && abilities.characterClass == Classes.Ranger },
    description = "Вы получаете одно из следующих умений на свой выбор:\n"
)

var standAgainstTheTide = AbilityNode(
    name = "Стоять против течения",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsMap["Стоять против течения"] =
            Action(
                name = "Стоять против течения",
                description = "Если враждебное существо промахивается по вам рукопашной атакой, вы можете реакцией заставить его повторить эту атаку по другому существу (кроме него самого) на ваш выбор.\n",
                type = ActionType.Reaction,
            )

        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    description = "Если враждебное существо промахивается по вам рукопашной атакой, вы можете реакцией заставить его повторить эту атаку по другому существу (кроме него самого) на ваш выбор.\n"
)

var superiorHuntersDefense = AbilityNode(
    name = "Улучшенная защита охотника",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(evasion.name, standAgainstTheTide.name, uncannyDodge.name) })
    ),
    requirements = { abilities: CharacterInfo -> abilities.level >= 11 && abilities.characterClass == Classes.Ranger },
    description = "Вы получаете одно из следующих умений на свой выбор:\n"
)

var hunter = AbilityNode(
    name = "Охотник",
    changesInCharacterInfo = { abilities: CharacterInfo -> abilities },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(huntersPrey.name) }),
        Pair("second", { listOf(defenciveTactics.name) }),
        Pair("third", { listOf(huntersMultiattack.name) }),
        Pair("fourth", { listOf(superiorHuntersDefense.name) })
    ),
    requirements = { true },
    description = "",
    isNeedsToBeShown = false
)

var mapOfHunterAbilities: MutableMap<String, AbilityNode> = mutableMapOf(
    Pair(colossusSlayer.name, colossusSlayer),
    Pair(giantKiller.name, giantKiller),
    Pair(hordeBreaker.name, hordeBreaker),
    Pair(huntersPrey.name, huntersPrey),
    Pair(escapeTheHorde.name, escapeTheHorde),
    Pair(multiattackDefence.name, multiattackDefence),
    Pair(steelWill.name, steelWill),
    Pair(defenciveTactics.name, defenciveTactics),
    Pair(volley.name, volley),
    Pair(whirldwindAttack.name, whirldwindAttack),
    Pair(huntersMultiattack.name, huntersMultiattack),
    Pair(standAgainstTheTide.name, standAgainstTheTide),
    Pair(superiorHuntersDefense.name, superiorHuntersDefense),
    Pair(hunter.name, hunter),
)