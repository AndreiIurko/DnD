package com.andreyyurko.dnd.data.abilities.classes.monk

import com.andreyyurko.dnd.data.abilities.classes.AbilityNodeLevel
import com.andreyyurko.dnd.data.abilities.classes.cleric.classFeaturesCleric
import com.andreyyurko.dnd.data.abilities.classes.fighter.extraAttack
import com.andreyyurko.dnd.data.abilities.other.*
import com.andreyyurko.dnd.data.characterData.Classes
import com.andreyyurko.dnd.data.characterData.*
import com.andreyyurko.dnd.data.characterData.character.AbilityNode
import com.andreyyurko.dnd.data.characterData.character.abilityToModifier

var classFeaturesMonk: AbilityNode = AbilityNode(
    name = "Монах: классовые умения",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.savingThrowProf.add(Ability.Dexterity)
        abilities.savingThrowProf.add(Ability.Strength)
        addAllSimpleWeapons(abilities)
        abilities.weaponProficiency.plus(Weapon.ShortSword)
        abilities
    },
    alternatives = mutableMapOf(
        Pair(
            "skill1",
            listOf(acrobatics.name, athletics.name, history.name, insight.name, religion.name, stealth.name)
        ),
        Pair(
            "skill2",
            listOf(acrobatics.name, athletics.name, history.name, insight.name, religion.name, stealth.name)
        ),
    ),
    requirements = { abilities: CharacterInfo ->
        abilities.characterClass == Classes.Monk
    },
    description = "ХИТЫ\n" +
            "\n" +
            "Кость Хитов: 1к8 за каждый уровень монаха\n" +
            "\n" +
            "Хиты на 1 уровне: 8 + модификатор Телосложения\n" +
            "\n" +
            "Хиты на следующих уровнях: 1к8 (или 5) + модификатор Телосложения (суммарно минимум 1) за каждый уровень монаха после первого\n" +
            "\n" +
            "ВЛАДЕНИЕ\n" +
            "\n" +
            "Доспехи: нет\n" +
            "\n" +
            "Оружие: Простое оружие, короткие мечи\n" +
            "\n" +
            "Инструменты: Выберите один вид инструмента ремесленников, либо музыкального инструмента.\n" +
            "\n" +
            "Спасброски: Сила, Ловкость\n" +
            "\n" +
            "Навыки: Выберите два навыка из следующих: Акробатика, Атлетика, История, Проницательность, Религия, Скрытность.\n",
    priority = Priority.DoFirst
)

var monkUnarmedDefence: AbilityNode = AbilityNode(
    name = "Монашеская защита без доспехов",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        if (abilities.currentState.armor == Armor.NoArmor)
            abilities.ac = abilities.ac + (abilities.wisdom - 10) / 2
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    description = "Если вы не носите ни доспех, ни щит, ваш Класс Доспеха равен 10 + модификатор Ловкости + модификатор Мудрости.\n",
)

var martialArts: AbilityNode = AbilityNode(
    name = "Боевые искусства",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        if (abilities.currentState.armor == Armor.NoArmor && !abilities.currentState.hasShield) {
            checkProfs@ for (prof in abilities.weaponProficiency) {
                for (prop in prof.properties) {
                    if (prop.lowercase().contains("двуручное") or prop.lowercase().contains("тяжелое")) {
                        continue@checkProfs
                    }
                }
                prof.setOfSkills.plus(Ability.Dexterity)
            }
            /// We need to change dice
        }
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    description = "Ваше знание боевых искусств позволяет вам эффективно использовать в бою безоружные удары и монашеское оружие — короткие мечи, а также любое простое рукопашное оружие, не имеющее свойств двуручное и тяжёлое.\n" +
            "\n" +
            "Если вы безоружны или используете только монашеское оружие, и не носите ни доспехов, ни щита, вы получаете следующие преимущества:\n" +
            "\n" +
            "\tВы можете использовать Ловкость вместо Силы для бросков атак и урона ваших безоружных ударов и атак монашеским оружием.\n" +
            "\tВы можете использовать к4 вместо обычной кости урона ваших безоружных ударов или атак монашеским оружием. Эта кость увеличивается с вашим уровнем, как показано в колонке «боевые искусства».\n" +
            "\tЕсли в свой ход вы используете действие Атака для безоружного удара или атаки монашеским оружием, вы можете бонусным действием совершить ещё один безоружный удар. Например, если вы совершили действие Атака и атаковали боевым посохом, вы можете совершить бонусным действием безоружный удар, при условии, что в этом ходу вы еще не совершали бонусное действие.\n" +
            "\n" +
            "Некоторые монастыри используют особые виды монашеского оружия. Например, вы можете использовать дубинку в виде двух деревянных брусков, соединённых короткой цепью (такое оружие называется нунчаками), или серп с более коротким и прямым лезвием (называется камой). Как бы ни называлось ваше монашеское оружие, вы используете характеристики, соответствующие этому оружию.\n",
    priority = Priority.DoLast
)

var monk1: AbilityNode = AbilityNodeLevel(
    name = "Монах_1",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.characterClass = Classes.Monk
        abilities.level += 1
        abilities.proficiencyBonus += 2
        abilities.hp += abilityToModifier(abilities.constitution) + 8
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(classFeaturesMonk.name)),
        Pair("second", listOf(monkUnarmedDefence.name)),
        Pair("third", listOf(martialArts.name))
    ),
    requirements = { true },
    add_requirements = listOf(),
    description = "1-й уровень, способности монаха",
    next_level = "Монах_2"
)

var monkUnarmedMovement = AbilityNode(
    name = "Монашеское движение без доспехов",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        if (abilities.currentState.armor == Armor.NoArmor) {
            abilities.speed = abilities.speed + 5 * ((abilities.level + 6) / 4)
        }
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    description = "Ваша скорость увеличивается на 10 футов, если вы не носите доспехов и щит. Этот бонус увеличивается с ростом вашего уровня, как показано в таблице.\n" +
            "\n" +
            "На 9-м уровне вы получаете возможность в свой ход перемещаться по вертикальным поверхностям и по поверхности жидкости, не падая во время движения.\n",
    priority = Priority.DoLast
)

var kiUsing = AbilityNode(
    name = "Ци",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        if (!abilities.currentState.charges.contains("Ци")) {
            abilities.currentState.charges["Ци"] = ChargesCounter(
                current = abilities.level,
                maximum = abilities.level
            )
        }
        abilities.currentState.charges["Ци"]?.let {
            if (it.maximum < abilities.level) {
                abilities.currentState.charges["Ци"] = ChargesCounter(
                    current = abilities.level,
                    maximum = abilities.level
                )
            }
        }
        abilities.actionsList.add(
            Action(
                name = "Поступь ветра",
                description = "Вы можете потратить 1 очко ци в свой ход, чтобы совершить бонусным действием Отход или Рывок. В этот ход дальность ваших прыжков удваивается.\n",
                type = ActionType.Bonus,
                relatedCharges = "Ци"
            )
        )
        abilities.actionsList.add(
            Action(
                name = "Терпеливая оборона",
                description = "Вы тратите 1 очко ци в свой ход, чтобы совершить бонусным действием Уклонение.\n",
                type = ActionType.Bonus,
                relatedCharges = "Ци"
            )
        )
        abilities.actionsList.add(
            Action(
                name = "Шквал ударов",
                description = "Сразу же после того, как вы в свой ход совершили действие Атака, вы можете потратить 1 очко ци, чтобы бонусным действием совершить два безоружных удара.y\n",
                type = ActionType.Bonus,
                relatedCharges = "Ци"
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    description = "Ваши тренировки позволяют вам управлять мистической энергией ци. Ваш доступ к этой энергии выражается количеством очков ци. Ваш уровень монаха определяет это количество, согласно колонке «Очки ци». Вы можете использовать эти очки чтобы активировать различные умения ци. Вначале вам известны следующие три умения: «Поступь ветра», «Терпеливая оборона» и «Шквал ударов». С повышением уровня в этом классе вы выучите новые умения ци.\n" +
            "\n" +
            "Все потраченные очки ци восполняются по окончании короткого или продолжительного отдыха. Вы должны потратить как минимум 30 минут на медитацию для их восстановления. Некоторые из ваших умений ци требуют от цели спасброска, позволяющего избежать эффекта. Сл такого спасброска определяется следующим образом:\n" +
            "\n" +
            "Сл спасброска ци = 8 + ваш бонус мастерства + ваш модификатор Мудрости\n" +
            "\nПОСТУПЬ ВЕТРА\n" +
            "Вы можете потратить 1 очко ци в свой ход, чтобы совершить бонусным действием Отход или Рывок. В этот ход дальность ваших прыжков удваивается.\n" +
            "\nТЕРПЕЛИВАЯ ОБОРОНА\n" +
            "Вы тратите 1 очко ци в свой ход, чтобы совершить бонусным действием Уклонение.\n" +
            "\nШКВАЛ УДАРОВ\n" +
            "Сразу же после того, как вы в свой ход совершили действие Атака, вы можете потратить 1 очко ци, чтобы бонусным действием совершить два безоружных удара.\n",
    priority = Priority.DoLast
)

var monk2: AbilityNode = AbilityNodeLevel(
    name = "Монах_2",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(monkUnarmedMovement.name)),
        Pair("second", listOf(kiUsing.name))
    ),
    requirements = { true },
    add_requirements = listOf(listOf()),
    description = "2-й уровень, способности монаха",
    next_level = "Монах_3"
)

var monasticTradition: AbilityNode = AbilityNode(
    name = "Монашеская традиция",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(wayOfOpenHand.name, wayOfFourElements.name))
    ),
    requirements = { true },
    description = "Вы выбираете монастырскую традицию, которой следуете. Все они описаны в конце описания класса. Выбранная традиция обеспечивает вам дополнительные умения на 3-м, 6-м, 11-м и 17-м уровнях.\n"
)

var deflectMissiles: AbilityNode = AbilityNode(
    name = "Отражение снарядов",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Отражение снарядов",
                description = "Вы можете реакцией отразить или поймать снаряд, если по вам попали дальнобойной атакой оружием. Если вы делаете это, урон снижается на 1к10 + ваш модификатор Ловкости + ваш уровень монаха.\n" +
                        "\n" +
                        "Если вы снизили урон до 0, вы можете поймать снаряд в случае, если он достаточно мал, чтоб держать его одной рукой, и одна из ваших рук свободна. Если вы поймали снаряд с помощью этого умения, вы можете потратить одно очко ци, чтобы частью реакции совершить дальнобойную атаку пойманным оружием или боеприпасом с дистанцией 20/60 футов. Вы совершаете эту атаку с владением, вне зависимости от владения данным оружием, и этот снаряд считается для данной атаки монашеским оружием.\n",
                type = ActionType.Reaction,
                relatedCharges = "Ци"
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    description = "Вы можете реакцией отразить или поймать снаряд, если по вам попали дальнобойной атакой оружием. Если вы делаете это, урон снижается на 1к10 + ваш модификатор Ловкости + ваш уровень монаха.\n" +
            "\n" +
            "Если вы снизили урон до 0, вы можете поймать снаряд в случае, если он достаточно мал, чтоб держать его одной рукой, и одна из ваших рук свободна. Если вы поймали снаряд с помощью этого умения, вы можете потратить одно очко ци, чтобы частью реакции совершить дальнобойную атаку пойманным оружием или боеприпасом с дистанцией 20/60 футов. Вы совершаете эту атаку с владением, вне зависимости от владения данным оружием, и этот снаряд считается для данной атаки монашеским оружием.\n"
)

var monk3: AbilityNode = AbilityNodeLevel(
    name = "Монах_3",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(monasticTradition.name)),
        Pair("second", listOf(deflectMissiles.name))
    ),
    requirements = { true },
    add_requirements = listOf(listOf()),
    description = "3-й уровень, способности монаха",
    next_level = "Монах_4"
)

var slowFall: AbilityNode = AbilityNode(
    name = "Замедленное падение",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Замедленное падение",
                description = "Если вы упали, вы можете реакцией уменьшить урон от падения на значение, равное вашему уровню монаха, умноженному на пять.\n",
                type = ActionType.Reaction
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    add_requirements = listOf(listOf()),
    description = "Если вы упали, вы можете реакцией уменьшить урон от падения на значение, равное вашему уровню монаха, умноженному на пять.\n"
)

var monk4: AbilityNode = AbilityNodeLevel(
    name = "Монах_4",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(abilityScoreImprovement.name)),
        Pair("second", listOf(slowFall.name)),
    ),
    requirements = { true },
    add_requirements = listOf(listOf()),
    description = "4-й уровень, способности монаха",
    next_level = "Монах_5"
)

var stunningStrike: AbilityNode = AbilityNode(
    name = "Ошеломляющий удар",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Ошеломляющий удар",
                description = "Вы можете взаимодействовать с энергией ци, текущей в теле вашего противника. Если вы попали по другому существу рукопашной атакой оружием, вы можете потратить 1 очко ци, чтобы нанести ошеломляющий удар. Цель должна преуспеть в спасброске Телосложения, иначе она станет ошеломлённой до конца вашего следующего хода.\n",
                type = ActionType.Additional,
                relatedCharges = "Ци"
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    add_requirements = listOf(listOf()),
    description = "Вы можете взаимодействовать с энергией ци, текущей в теле вашего противника. Если вы попали по другому существу рукопашной атакой оружием, вы можете потратить 1 очко ци, чтобы нанести ошеломляющий удар. Цель должна преуспеть в спасброске Телосложения, иначе она станет ошеломлённой до конца вашего следующего хода.\n"
)


var monk5: AbilityNode = AbilityNodeLevel(
    name = "Монах_5",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.proficiencyBonus += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(extraAttack.name)),
        Pair("second", listOf(stunningStrike.name)),
    ),
    requirements = { true },
    add_requirements = listOf(listOf()),
    description = "5-й уровень, способности монаха",
    next_level = "Монах_6"
)

var kiImprovedStrike: AbilityNode = AbilityNode(
    name = "Энергетические удары",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Энергетические удары",
                description = "Ваши безоружные удары считаются магическими при определении преодоления сопротивления и иммунитета к немагическим атакам и урону.\n",
                type = ActionType.Additional
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    add_requirements = listOf(listOf()),
    description = "Ваши безоружные удары считаются магическими при определении преодоления сопротивления и иммунитета к немагическим атакам и урону.\n"
)

var monk6: AbilityNode = AbilityNodeLevel(
    name = "Монах_6",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(kiImprovedStrike.name)),
    ),
    requirements = { true },
    add_requirements = listOf(listOf()),
    description = "6-й уровень, способности монаха",
    next_level = "Монах_7"
)

var monkEvasion: AbilityNode = AbilityNode(
    name = "Монашеская увёртливость",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Монашеская увёртливость",
                description = "Ваше инстинктивное проворство позволяет вам уклоняться от эффектов, направленных на определённую область, вроде дыхания синего дракона или заклинания огненный шар. Если вы попадаете под действие эффекта, позволяющего совершить спасбросок Ловкости, чтобы получить только половину урона, вы вместо этого не получаете урона при успешном спасброске и получаете только половину урона при проваленном.\n",
                type = ActionType.Additional
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    add_requirements = listOf(listOf()),
    description = "Ваше инстинктивное проворство позволяет вам уклоняться от эффектов, направленных на определённую область, вроде дыхания синего дракона или заклинания огненный шар. Если вы попадаете под действие эффекта, позволяющего совершить спасбросок Ловкости, чтобы получить только половину урона, вы вместо этого не получаете урона при успешном спасброске и получаете только половину урона при проваленном.\n"
)

var stillnessOfMind: AbilityNode = AbilityNode(
    name = "Спокойствие разума",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    add_requirements = listOf(listOf()),
    description = "Ваше инстинктивное проворство позволяет вам уклоняться от эффектов, направленных на определённую область, вроде дыхания синего дракона или заклинания огненный шар. Если вы попадаете под действие эффекта, позволяющего совершить спасбросок Ловкости, чтобы получить только половину урона, вы вместо этого не получаете урона при успешном спасброске и получаете только половину урона при проваленном.\n"
)

var monk7: AbilityNode = AbilityNodeLevel(
    name = "Монах_7",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(monkEvasion.name)),
        Pair("second", listOf(stillnessOfMind.name))
    ),
    requirements = { true },
    add_requirements = listOf(listOf()),
    description = "7-й уровень, способности монаха",
    next_level = "Монах_8"
)

var monk8: AbilityNode = AbilityNodeLevel(
    name = "Монах_8",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(abilityScoreImprovement.name)),
    ),
    requirements = { true },
    add_requirements = listOf(listOf()),
    description = "8-й уровень, способности монаха",
    next_level = "Монах_9"
)

var monk9: AbilityNode = AbilityNodeLevel(
    name = "Монах_9",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.proficiencyBonus += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    alternatives = mutableMapOf(
    ),
    requirements = { true },
    add_requirements = listOf(listOf()),
    description = "9-й уровень, способности монаха",
    next_level = "Монах_10"
)

var purityOfBody: AbilityNode = AbilityNode(
    name = "Чистота тела",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        if (!abilities.damageImmunities.contains(DamageType.Poison)) {
            abilities.damageImmunities.add(DamageType.Poison)
        }
        if (!abilities.conditionImmunities.contains(Conditions.Poisoned)) {
            abilities.conditionImmunities.add(Conditions.Poisoned)
        }
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    add_requirements = listOf(listOf()),
    description = "Ваше мастерство ци даёт вам иммунитет к болезням и яду.\n"
)

var monk10: AbilityNode = AbilityNodeLevel(
    name = "Монах_10",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(purityOfBody.name)),
    ),
    requirements = { true },
    add_requirements = listOf(listOf()),
    description = "10-й уровень, способности монаха",
    next_level = "Монах_11"
)

var monk11: AbilityNode = AbilityNodeLevel(
    name = "Монах_11",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    alternatives = mutableMapOf(
    ),
    requirements = { true },
    add_requirements = listOf(listOf()),
    description = "11-й уровень, способности монаха",
    next_level = "Монах_12"
)

var monk12: AbilityNode = AbilityNodeLevel(
    name = "Монах_12",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(abilityScoreImprovement.name)),
    ),
    requirements = { true },
    add_requirements = listOf(listOf()),
    description = "12-й уровень, способности монаха",
    next_level = "Монах_13"
)

var tongueOfTheSunAndMoon: AbilityNode = AbilityNode(
    name = "Язык солнца и луны",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Язык солнца и луны",
                description = "Вы понимаете, как взаимодействовать с энергией ци в чужом разуме, и теперь вы понимаете речь на любом языке. Кроме того, все существа, способные понимать хотя бы один язык, понимают то, что вы сказали.\n",
                type = ActionType.Additional,
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    add_requirements = listOf(listOf()),
    description = "Вы понимаете, как взаимодействовать с энергией ци в чужом разуме, и теперь вы понимаете речь на любом языке. Кроме того, все существа, способные понимать хотя бы один язык, понимают то, что вы сказали.\n"
)

var monk13: AbilityNode = AbilityNodeLevel(
    name = "Монах_13",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.proficiencyBonus += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(tongueOfTheSunAndMoon.name)),
    ),
    requirements = { true },
    add_requirements = listOf(listOf()),
    description = "13-й уровень, способности монаха",
    next_level = "Монах_14"
)

var diamondSoul: AbilityNode = AbilityNode(
    name = "Алмазная душа",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.savingThrowProf.add(Ability.Dexterity)
        abilities.savingThrowProf.add(Ability.Strength)
        abilities.savingThrowProf.add(Ability.Intelligence)
        abilities.savingThrowProf.add(Ability.Charisma)
        abilities.savingThrowProf.add(Ability.Constitution)
        abilities.savingThrowProf.add(Ability.Wisdom)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    add_requirements = listOf(listOf()),
    description = "Ваше мастерство ци предоставляет вам владение всеми спасбросками. Кроме того, если вы провалили спасбросок, вы можете повторить его, потратив 1 очко ци, и должны использовать второй результат.\n"
)

var monk14: AbilityNode = AbilityNodeLevel(
    name = "Монах_14",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(diamondSoul.name)),
    ),
    requirements = { true },
    add_requirements = listOf(listOf()),
    description = "14-й уровень, способности монаха",
    next_level = "Монах_15"
)

var timelessBody: AbilityNode = AbilityNode(
    name = "Безвременное тело",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Безвременное тело",
                description = "Ваша ци поддерживает вас, и ваше тело больше не подвержено признакам старения. Вы не можете быть состарены магически. Впрочем, вы всё еще можете умереть от старости. Кроме того, вам больше не требуется еда и вода.\n",
                type = ActionType.Additional,
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    add_requirements = listOf(listOf()),
    description = "Ваша ци поддерживает вас, и ваше тело больше не подвержено признакам старения. Вы не можете быть состарены магически. Впрочем, вы всё еще можете умереть от старости. Кроме того, вам больше не требуется еда и вода.\n"
)

var monk15: AbilityNode = AbilityNodeLevel(
    name = "Монах_15",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(timelessBody.name)),
    ),
    requirements = { true },
    add_requirements = listOf(listOf()),
    description = "15-й уровень, способности монаха",
    next_level = "Монах_16"
)

var monk16: AbilityNode = AbilityNodeLevel(
    name = "Монах_16",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(abilityScoreImprovement.name)),
    ),
    requirements = { true },
    add_requirements = listOf(listOf()),
    description = "16-й уровень, способности монаха",
    next_level = "Монах_17"
)

var monk17: AbilityNode = AbilityNodeLevel(
    name = "Монах_17",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.proficiencyBonus += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    alternatives = mutableMapOf(
    ),
    requirements = { true },
    add_requirements = listOf(listOf()),
    description = "17-й уровень, способности монаха",
    next_level = "Монах_18"
)

var emptyBody: AbilityNode = AbilityNode(
    name = "Пустое тело",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Пустое тело",
                description = "Вы можете действием потратить 4 очка ци, чтобы стать невидимым на 1 минуту. В течение этого времени вы получаете сопротивление всем видам урона, кроме урона силовым полем.\n",
                type = ActionType.Action,
                relatedCharges = "Ци"
            )
        )
        abilities.actionsList.add(
            Action(
                name = "Пустое тело. Проекция в астрал",
                description = "Вы можете потратить 8 очков ци, чтобы наложить заклинание проекция в астрал [astral projection] без применения материальных компонентов. Вы не можете перемещать кого-либо вместе с собой.\n",
                type = ActionType.Additional,
                relatedCharges = "Ци"
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    add_requirements = listOf(listOf()),
    description = "Вы можете действием потратить 4 очка ци, чтобы стать невидимым на 1 минуту. В течение этого времени вы получаете сопротивление всем видам урона, кроме урона силовым полем.\n" +
            "\n" +
            "Кроме того, вы можете потратить 8 очков ци, чтобы наложить заклинание проекция в астрал [astral projection] без применения материальных компонентов. Вы не можете перемещать кого-либо вместе с собой.\n"
)

var monk18: AbilityNode = AbilityNodeLevel(
    name = "Монах_18",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(emptyBody.name)),
    ),
    requirements = { true },
    add_requirements = listOf(listOf()),
    description = "18-й уровень, способности монаха",
    next_level = "Монах_19"
)

var monk19: AbilityNode = AbilityNodeLevel(
    name = "Монах_19",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(abilityScoreImprovement.name)),
    ),
    requirements = { true },
    add_requirements = listOf(listOf()),
    description = "19-й уровень, способности монаха",
    next_level = "Монах_20"
)

var perfectSelf: AbilityNode = AbilityNode(
    name = "Совершенство",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Совершенство",
                description = "Если при броске инициативы у вас нет очков ци, вы получаете 4 очка ци.\n",
                type = ActionType.Additional,
                relatedCharges = "Ци"
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    add_requirements = listOf(listOf()),
    description = "Если при броске инициативы у вас нет очков ци, вы получаете 4 очка ци.\n"
)

var monk20: AbilityNode = AbilityNodeLevel(
    name = "Монах_20",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    alternatives = mutableMapOf(
        Pair("first", listOf(perfectSelf.name)),
    ),
    requirements = { true },
    add_requirements = listOf(listOf()),
    description = "20-й уровень, способности монаха",
    next_level = null
)

var mapOfMonkAbilities: MutableMap<String, AbilityNode> = (mutableMapOf(
    Pair(classFeaturesMonk.name, classFeaturesMonk),
    Pair(monkUnarmedDefence.name, monkUnarmedDefence),
    Pair(martialArts.name, martialArts),
    Pair(monk1.name, monk1),
    Pair(monkUnarmedMovement.name, monkUnarmedMovement),
    Pair(kiUsing.name, kiUsing),
    Pair(monk2.name, monk2),
    Pair(monasticTradition.name, monasticTradition),
    Pair(deflectMissiles.name, deflectMissiles),
    Pair(monk3.name, monk3),
    Pair(slowFall.name, slowFall),
    Pair(monk4.name, monk4),
    Pair(stunningStrike.name, stunningStrike),
    Pair(monk5.name, monk5),
    Pair(kiImprovedStrike.name, kiImprovedStrike),
    Pair(monk6.name, monk6),
    Pair(monkEvasion.name, monkEvasion),
    Pair(stillnessOfMind.name, stillnessOfMind),
    Pair(monk7.name, monk7),
    Pair(monk8.name, monk8),
    Pair(monk9.name, monk9),
    Pair(purityOfBody.name, purityOfBody),
    Pair(monk10.name, monk10),
    Pair(monk11.name, monk11),
    Pair(monk12.name, monk12),
    Pair(tongueOfTheSunAndMoon.name, tongueOfTheSunAndMoon),
    Pair(monk13.name, monk13),
    Pair(diamondSoul.name, diamondSoul),
    Pair(monk14.name, monk14),
    Pair(timelessBody.name, timelessBody),
    Pair(monk15.name, monk15),
    Pair(monk16.name, monk16),
    Pair(monk17.name, monk17),
    Pair(emptyBody.name, emptyBody),
    Pair(monk18.name, monk18),
    Pair(monk19.name, monk19),
    Pair(perfectSelf.name, perfectSelf),
    Pair(monk20.name, monk20),
)
        + mapOfWayOfFourElementsAbilities
        + mapOfWayOfOpenHandAbilities).toMutableMap()







