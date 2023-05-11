package com.andreyyurko.dnd.data.abilities.classes.barbarian

import com.andreyyurko.dnd.data.abilities.classes.AbilityNodeLevel
import com.andreyyurko.dnd.data.abilities.classes.extraAttack
import com.andreyyurko.dnd.data.abilities.other.*
import com.andreyyurko.dnd.data.characterData.*
import com.andreyyurko.dnd.data.characterData.character.AbilityNode
import com.andreyyurko.dnd.data.characterData.character.abilityToModifier

var classFeaturesBarbarian: AbilityNode = AbilityNode(
    name = "Варвар: классовые умения",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.savingThrowProf.add(Ability.Strength)
        abilities.savingThrowProf.add(Ability.Constitution)
        addAllSimpleWeapons(abilities)
        addAllMartialWeapons(abilities)
        abilities.armorProficiency.add(ArmorProf.LightArmor)
        abilities.armorProficiency.add(ArmorProf.MediumArmor)
        abilities.armorProficiency.add(ArmorProf.Shield)
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair(
            "skill1",
            {
                listOf(
                    athletics.name,
                    perception.name,
                    survival.name,
                    intimidation.name,
                    nature.name,
                    animalHandling.name
                )
            }
        ),
        Pair(
            "skill2",
            {
                listOf(
                    athletics.name,
                    perception.name,
                    survival.name,
                    intimidation.name,
                    nature.name,
                    animalHandling.name
                )
            }
        )
    ),
    requirements = { abilities: CharacterInfo ->
        abilities.characterClass == Classes.Barbarian
    },
    description = "ХИТЫ\n" +
            "\n" +
            "Кость Хитов: 1к12 за каждый уровень варвара\n" +
            "\n" +
            "Хиты на 1 уровне: 12 + модификатор Телосложения\n" +
            "\n" +
            "Хиты на следующих уровнях: 1к12 (или 7) + модификатор Телосложения (суммарно минимум 1) за каждый уровень варвара после первого\n" +
            "\n" +
            "ВЛАДЕНИЕ\n" +
            "\n" +
            "Доспехи: Лёгкие доспехи, средние доспехи, щиты\n" +
            "\n" +
            "Оружие: Простое оружие, воинское оружие\n" +
            "\n" +
            "Инструменты: Нет\n" +
            "\n" +
            "Спасброски: Сила, Телосложение\n" +
            "\n" +
            "Навыки: Выберите два навыка из следующих: Атлетика, Восприятие, Выживание, Запугивание, Природа, Уход за животными\n",
    priority = Priority.DoFirst
)

var barbarianUnarmedDefence: AbilityNode = AbilityNode(
    name = "Варварская защита без доспехов",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        if (abilities.currentState.armor == Armor.NoArmor) {
            abilities.ac = Integer.max(
                abilities.ac,
                10 + abilityToModifier(abilities.constitution) + abilityToModifier(abilities.dexterity)
            )
        }
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    description = "Если вы не носите ни доспех, ни щит, ваш Класс Доспеха равен 10 + модификатор Ловкости + модификатор Телосложения.\n",
)

var rage: AbilityNode = AbilityNode(
    name = "Ярость",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        if (abilities.level < 20) {
            var chargesNumber = 2
            if (abilities.level >= 3) chargesNumber = 3
            if (abilities.level >= 6) chargesNumber = 4
            if (abilities.level >= 12) chargesNumber = 5
            if (abilities.level >= 17) chargesNumber = 6
            if (!abilities.currentState.charges.contains("Ярость")) {
                abilities.currentState.charges["Ярость"] = ChargesCounter(
                    current = chargesNumber,
                    maximum = chargesNumber
                )
            }
            abilities.currentState.charges["Ярость"]?.let {
                if (it.maximum != chargesNumber) {
                    abilities.currentState.charges["Ярость"] = ChargesCounter(
                        current = chargesNumber,
                        maximum = chargesNumber
                    )
                }
            }
            var damageBonus = "+2"
            if (abilities.level >= 9) damageBonus = "+3"
            if (abilities.level >= 16) damageBonus = "+4"
            abilities.actionsList.add(
                Action(
                    name = "Ярость",
                    description = "В бою вы сражаетесь с первобытной свирепостью. В свой ход вы можете бонусным действием войти в состояние ярости.\n" +
                            "\n" +
                            "В состоянии ярости вы получаете следующие преимущества, если не носите тяжёлую броню:\n" +
                            "\n" +
                            "    Вы совершаете с преимуществом проверки и спасброски Силы.\n" +
                            "    Если вы совершаете рукопашную атаку оружием, используя Силу, вы получаете бонус " + damageBonus + " к броску урона.\n" +
                            "    Вы получаете сопротивление дробящему, колющему и рубящему урону.\n" +
                            "\n" +
                            "Если вы способны накладывать заклинания, то вы не можете накладывать или концентрироваться на заклинаниях, пока находитесь в состоянии ярости.\n" +
                            "\n" +
                            "Ваша ярость длится 1 минуту. Она прекращается раньше, если вы потеряли сознание или если вы закончили свой ход, не получив урон или не атаковав враждебное по отношению к вам существо с момента окончания вашего прошлого хода. Также вы можете прекратить свою ярость бонусным действием.\n" +
                            "\n" +
                            "Если вы впадали в состояние ярости " + chargesNumber.toString() + " раз (смотрите колонку «ярость»), то вы должны совершить продолжительный отдых, прежде чем сможете использовать ярость ещё раз.\n",
                    type = ActionType.Bonus,
                    relatedCharges = "Ярость"
                )
            )
        } else {
            abilities.actionsList.add(
                Action(
                    name = "Ярость",
                    description = "В бою вы сражаетесь с первобытной свирепостью. В свой ход вы можете бонусным действием войти в состояние ярости.\n" +
                            "\n" +
                            "В состоянии ярости вы получаете следующие преимущества, если не носите тяжёлую броню:\n" +
                            "\n" +
                            "    Вы совершаете с преимуществом проверки и спасброски Силы.\n" +
                            "    Если вы совершаете рукопашную атаку оружием, используя Силу, вы получаете бонус +4 к броску урона.\n" +
                            "    Вы получаете сопротивление дробящему, колющему и рубящему урону.\n" +
                            "\n" +
                            "Если вы способны накладывать заклинания, то вы не можете накладывать или концентрироваться на заклинаниях, пока находитесь в состоянии ярости.\n" +
                            "\n" +
                            "Ваша ярость длится 1 минуту. Она прекращается раньше, если вы потеряли сознание или если вы закончили свой ход, не получив урон или не атаковав враждебное по отношению к вам существо с момента окончания вашего прошлого хода. Также вы можете прекратить свою ярость бонусным действием.\n" +
                            "\n" +
                            "Вы можете впадать в ярость неограниченное число раз.\n",
                    type = ActionType.Bonus,
                )
            )
        }
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    description = "В бою вы сражаетесь с первобытной свирепостью. В свой ход вы можете бонусным действием войти в состояние ярости.\n" +
            "\n" +
            "В состоянии ярости вы получаете следующие преимущества, если не носите тяжёлую броню:\n" +
            "\n" +
            "    Вы совершаете с преимуществом проверки и спасброски Силы.\n" +
            "    Если вы совершаете рукопашную атаку оружием, используя Силу, вы получаете бонус к броску урона, соответствующий вашему уровню варвара, как показано в колонке «урон ярости» таблицы «Варвар».\n" +
            "    Вы получаете сопротивление дробящему, колющему и рубящему урону.\n" +
            "\n" +
            "Если вы способны накладывать заклинания, то вы не можете накладывать или концентрироваться на заклинаниях, пока находитесь в состоянии ярости.\n" +
            "\n" +
            "Ваша ярость длится 1 минуту. Она прекращается раньше, если вы потеряли сознание или если вы закончили свой ход, не получив урон или не атаковав враждебное по отношению к вам существо с момента окончания вашего прошлого хода. Также вы можете прекратить свою ярость бонусным действием.\n" +
            "\n" +
            "Если вы впадали в состояние ярости максимальное для вашего уровня количество раз (смотрите колонку «ярость»), то вы должны совершить продолжительный отдых, прежде чем сможете использовать ярость ещё раз.\n" +
            "На 20 уровне вы можете впадать в ярость неограниченное число раз\n",
)

var barbarian1: AbilityNode = AbilityNodeLevel(
    name = "Варвар_1",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.characterClass = Classes.Barbarian
        abilities.level += 1
        abilities.proficiencyBonus += 2
        abilities.hp += abilityToModifier(abilities.constitution) + 12
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(classFeaturesBarbarian.name) }),
        Pair("second", { listOf(barbarianUnarmedDefence.name) }),
        Pair("third", { listOf(rage.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "1-й уровень, способности варвара",
    next_level = "Варвар_2"
)

var recklessAttack: AbilityNode = AbilityNode(
    name = "Безрассудная атака",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Безрассудная атака",
                description = "Вы способны отбросить любую заботу о защите, чтобы атаковать ожесточённо и безрассудно. Когда вы совершаете первую атаку в свой ход, вы можете решить, что будете атаковать безрассудно. Решившись на это, вы в этом ходу совершаете рукопашные атаки оружием, использующие Силу, с преимуществом, но все броски атаки по вам до вашего следующего хода тоже совершаются с преимуществом.\n",
                type = ActionType.PartOfAction
            )
        )
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "Вы способны отбросить любую заботу о защите, чтобы атаковать ожесточённо и безрассудно. Когда вы совершаете первую атаку в свой ход, вы можете решить, что будете атаковать безрассудно. Решившись на это, вы в этом ходу совершаете рукопашные атаки оружием, использующие Силу, с преимуществом, но все броски атаки по вам до вашего следующего хода тоже совершаются с преимуществом.\n",
)

var dangerSense: AbilityNode = AbilityNode(
    name = "Чувство опасности",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Чувство опасности"] =
            "Вы получаете обострённое ощущение происходящего вокруг, помогающее вам избегать опасности.\n" +
                    "\n" +
                    "Вы совершаете с преимуществом спасброски Ловкости от эффектов, которые вы можете видеть, такие как заклинания и ловушки. Для использования этого преимущества вы не должны быть ослеплены, оглушены и не должны быть недееспособны.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "Вы получаете обострённое ощущение происходящего вокруг, помогающее вам избегать опасности.\n" +
            "\n" +
            "Вы совершаете с преимуществом спасброски Ловкости от эффектов, которые вы можете видеть, такие как заклинания и ловушки. Для использования этого преимущества вы не должны быть ослеплены, оглушены и не должны быть недееспособны.\n",
)

var barbarian2: AbilityNode = AbilityNodeLevel(
    name = "Варвар_2",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 7
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(recklessAttack.name) }),
        Pair("second", { listOf(dangerSense.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "2-й уровень, способности варвара",
    next_level = "Варвар_3"
)

var primalPath: AbilityNode = AbilityNode(
    name = "Путь дикости",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(pathOfTheBerserker.name, pathOfTheZealot.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "Вы выбираете путь, определяющий природу вашей ярости. Все пути перечислены в конце описания класса. Ваш выбор обеспечит вам умения на 3-м, 6-м, 10-м и 14-м уровнях.\n"
)

var barbarian3: AbilityNode = AbilityNodeLevel(
    name = "Варвар_3",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 7
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(primalPath.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "3-й уровень, способности варвара",
    next_level = "Варвар_4"
)

var barbarian4: AbilityNode = AbilityNodeLevel(
    name = "Варвар_4",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 7
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(abilityScoreImprovement.name) }),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "4-й уровень, способности варвара",
    next_level = "Варвар_5"
)

var barbarianUnarmedMovement = AbilityNode(
    name = "Варварское движение без доспехов",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        if (abilities.currentState.armor == Armor.NoArmor) {
            abilities.speed = abilities.speed + 10
        }
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    description = "Ваша скорость увеличивается на 10 футов, если вы не носите тяжёлые доспехи.\n",
    priority = Priority.DoLast
)

var barbarian5: AbilityNode = AbilityNodeLevel(
    name = "Варвар_5",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.proficiencyBonus += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 7
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(barbarianUnarmedMovement.name) }),
        Pair("second", { listOf(extraAttack.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "5-й уровень, способности варвара",
    next_level = "Варвар_6"
)

var barbarian6: AbilityNode = AbilityNodeLevel(
    name = "Варвар_6",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 7
        abilities
    },
    getAlternatives = mutableMapOf(

    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "6-й уровень, способности варвара",
    next_level = "Варвар_7"
)

var feralInstinct: AbilityNode = AbilityNode(
    name = "Дикий инстинкт",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Дикий инстинкт"] =
            "Ваши инстинкты настолько обостряются, что вы совершаете с преимуществом броски инициативы.\n" +
                    "\n" +
                    "Кроме того, если вы были захвачены врасплох в начале боя, и вы не являетесь недееспособным, вы можете в первом ходу действовать нормальным образом, но только если вы впадёте в ярость раньше всех других действий в этом ходу.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "Ваши инстинкты настолько обостряются, что вы совершаете с преимуществом броски инициативы.\n" +
            "\n" +
            "Кроме того, если вы были захвачены врасплох в начале боя, и вы не являетесь недееспособным, вы можете в первом ходу действовать нормальным образом, но только если вы впадёте в ярость раньше всех других действий в этом ходу.\n"
)

var barbarian7: AbilityNode = AbilityNodeLevel(
    name = "Варвар_7",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 7
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(feralInstinct.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "7-й уровень, способности варвара",
    next_level = "Варвар_8"
)

var barbarian8: AbilityNode = AbilityNodeLevel(
    name = "Варвар_8",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 7
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(abilityScoreImprovement.name) }),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "8-й уровень, способности варвара",
    next_level = "Варвар_9"
)

var brutalCritical: AbilityNode = AbilityNode(
    name = "Сильный критический удар",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        if (abilities.level < 13) abilities.additionalAbilities["Сильный критический удар"] =
            "Вы можете бросать одну дополнительную кость урона оружия, когда определяете дополнительный урон от критического попадания рукопашной атакой.\n"
        if ((abilities.level >= 13) && (abilities.level < 17)) abilities.additionalAbilities["Сильный критический удар"] =
            "Вы можете бросать две дополнительных кости урона оружия, когда определяете дополнительный урон от критического попадания рукопашной атакой.\n"
        if (abilities.level >= 17) abilities.additionalAbilities["Сильный критический удар"] =
            "Вы можете бросать три дополнительных кости урона оружия, когда определяете дополнительный урон от критического попадания рукопашной атакой.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "Вы можете бросать одну дополнительную кость урона оружия, когда определяете дополнительный урон от критического попадания рукопашной атакой. Количество костей увеличивается до двух на 13-м уровне и трёх на 17-м уровне.\n"
)

var barbarian9: AbilityNode = AbilityNodeLevel(
    name = "Варвар_9",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.proficiencyBonus += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 7
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(brutalCritical.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "9-й уровень, способности варвара",
    next_level = "Варвар_10"
)

var barbarian10: AbilityNode = AbilityNodeLevel(
    name = "Варвар_10",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 7
        abilities
    },
    getAlternatives = mutableMapOf(

    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "10-й уровень, способности варвара",
    next_level = "Варвар_11"
)

var relentlessRage: AbilityNode = AbilityNode(
    name = "Непреклонная ярость",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Непреклонная ярость"] =
            "Ваша ярость позволяет вам сражаться, несмотря на тяжелейшие раны. Если ваши хиты опускаются до 0, когда вы в состоянии ярости, и вы не умерли сразу, вы можете совершить спасбросок Телосложения Сл 10. При успехе ваши хиты опускаются всего лишь до 1 хита.\n" +
                    "\n" +
                    "Каждый раз, когда вы используете это умение повторно, Сл спасброска повышается на 5. Когда вы закончите короткий либо продолжительный отдых, Сл снова равняется 10.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "Ваша ярость позволяет вам сражаться, несмотря на тяжелейшие раны. Если ваши хиты опускаются до 0, когда вы в состоянии ярости, и вы не умерли сразу, вы можете совершить спасбросок Телосложения Сл 10. При успехе ваши хиты опускаются всего лишь до 1 хита.\n" +
            "\n" +
            "Каждый раз, когда вы используете это умение повторно, Сл спасброска повышается на 5. Когда вы закончите короткий либо продолжительный отдых, Сл снова равняется 10.\n"
)


var barbarian11: AbilityNode = AbilityNodeLevel(
    name = "Варвар_11",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 7
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(relentlessRage.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "11-й уровень, способности варвара",
    next_level = "Варвар_12"
)

var barbarian12: AbilityNode = AbilityNodeLevel(
    name = "Варвар_12",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 7
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(abilityScoreImprovement.name) }),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "12-й уровень, способности варвара",
    next_level = "Варвар_13"
)

var barbarian13: AbilityNode = AbilityNodeLevel(
    name = "Варвар_13",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.proficiencyBonus += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 7
        abilities
    },
    getAlternatives = mutableMapOf(

    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "13-й уровень, способности варвара",
    next_level = "Варвар_14"
)

var barbarian14: AbilityNode = AbilityNodeLevel(
    name = "Варвар_14",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 7
        abilities
    },
    getAlternatives = mutableMapOf(

    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "14-й уровень, способности варвара",
    next_level = "Варвар_15"
)

var persistentRage: AbilityNode = AbilityNode(
    name = "Непрерывная ярость",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Непрерывная ярость"] =
            "Ваша ярость становится настолько сильной, что досрочно прекращается, только если вы теряете сознание или сами прекращаете её.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "Ваша ярость становится настолько сильной, что досрочно прекращается, только если вы теряете сознание или сами прекращаете её.\n"
)

var barbarian15: AbilityNode = AbilityNodeLevel(
    name = "Варвар_15",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 7
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(persistentRage.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "15-й уровень, способности варвара",
    next_level = "Варвар_16"
)

var barbarian16: AbilityNode = AbilityNodeLevel(
    name = "Варвар_16",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 7
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(abilityScoreImprovement.name) }),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "16-й уровень, способности варвара",
    next_level = "Варвар_17"
)

var barbarian17: AbilityNode = AbilityNodeLevel(
    name = "Варвар_17",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.proficiencyBonus += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 7
        abilities
    },
    getAlternatives = mutableMapOf(

    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "17-й уровень, способности варвара",
    next_level = "Варвар_18"
)

var indominatableMight: AbilityNode = AbilityNode(
    name = "Неукротимая мощь",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Неукротимая мощь"] =
            "Если результат вашей проверки Силы оказался меньше значения вашей Силы, то вы можете использовать значение характеристики вместо результата проверки.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "Если результат вашей проверки Силы оказался меньше значения вашей Силы, то вы можете использовать значение характеристики вместо результата проверки.\n"
)

var barbarian18: AbilityNode = AbilityNodeLevel(
    name = "Варвар_18",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 7
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(indominatableMight.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "18-й уровень, способности варвара",
    next_level = "Варвар_19"
)

var barbarian19: AbilityNode = AbilityNodeLevel(
    name = "Варвар_19",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 7
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(abilityScoreImprovement.name) }),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "19-й уровень, способности варвара",
    next_level = "Варвар_20"
)

var primalChampion: AbilityNode = AbilityNode(
    name = "Дикий чемпион",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.strength += 4
        abilities.constitution += 4
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "Вы становитесь воплощением силы дикой природы. Значение ваших Силы и Телосложения увеличивается на 4. Максимальное значение для этих характеристик теперь 24.\n"
)


var barbarian20: AbilityNode = AbilityNodeLevel(
    name = "Варвар_20",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 7
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(primalChampion.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "20-й уровень, способности варвара",
    next_level = null
)

var mapOfBarbarianAbilities: MutableMap<String, AbilityNode> = (
        mutableMapOf(
            Pair(classFeaturesBarbarian.name, classFeaturesBarbarian),
            Pair(barbarianUnarmedDefence.name, barbarianUnarmedDefence),
            Pair(rage.name, rage),
            Pair(barbarian1.name, barbarian1),
            Pair(recklessAttack.name, recklessAttack),
            Pair(dangerSense.name, dangerSense),
            Pair(barbarian2.name, barbarian2),
            Pair(primalPath.name, primalPath),
            Pair(barbarian3.name, barbarian3),
            Pair(barbarian4.name, barbarian4),
            Pair(barbarianUnarmedMovement.name, barbarianUnarmedMovement),
            Pair(barbarian5.name, barbarian5),
            Pair(barbarian6.name, barbarian6),
            Pair(feralInstinct.name, feralInstinct),
            Pair(barbarian7.name, barbarian7),
            Pair(barbarian8.name, barbarian8),
            Pair(brutalCritical.name, brutalCritical),
            Pair(barbarian9.name, barbarian9),
            Pair(barbarian10.name, barbarian10),
            Pair(relentlessRage.name, relentlessRage),
            Pair(barbarian11.name, barbarian11),
            Pair(barbarian12.name, barbarian12),
            Pair(barbarian13.name, barbarian13),
            Pair(barbarian14.name, barbarian14),
            Pair(persistentRage.name, persistentRage),
            Pair(barbarian15.name, barbarian15),
            Pair(barbarian16.name, barbarian16),
            Pair(barbarian17.name, barbarian17),
            Pair(indominatableMight.name, indominatableMight),
            Pair(barbarian18.name, barbarian18),
            Pair(barbarian19.name, barbarian19),
            Pair(primalChampion.name, primalChampion),
            Pair(barbarian20.name, barbarian20),
        )
                + mapOfPathOfTheBerserkerAbilities
                + mapOfPathOfTheZealotAbilities
        ).toMutableMap()