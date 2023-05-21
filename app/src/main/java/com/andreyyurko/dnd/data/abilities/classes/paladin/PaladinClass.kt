package com.andreyyurko.dnd.data.abilities.classes.paladin

import com.andreyyurko.dnd.data.abilities.classes.AbilityNodeLevel
import com.andreyyurko.dnd.data.abilities.classes.extraAttack
import com.andreyyurko.dnd.data.abilities.other.abilityScoreImprovement
import com.andreyyurko.dnd.data.abilities.other.athletics
import com.andreyyurko.dnd.data.abilities.other.defense
import com.andreyyurko.dnd.data.abilities.other.dueling
import com.andreyyurko.dnd.data.abilities.other.greatWeaponFighting
import com.andreyyurko.dnd.data.abilities.other.insight
import com.andreyyurko.dnd.data.abilities.other.intimidation
import com.andreyyurko.dnd.data.abilities.other.medicine
import com.andreyyurko.dnd.data.abilities.other.persuasion
import com.andreyyurko.dnd.data.abilities.other.protection
import com.andreyyurko.dnd.data.abilities.other.religion
import com.andreyyurko.dnd.data.characterData.Ability
import com.andreyyurko.dnd.data.characterData.Action
import com.andreyyurko.dnd.data.characterData.ActionType
import com.andreyyurko.dnd.data.characterData.ArmorProf
import com.andreyyurko.dnd.data.characterData.character.CharacterInfo
import com.andreyyurko.dnd.data.characterData.ChargesCounter
import com.andreyyurko.dnd.data.characterData.Classes
import com.andreyyurko.dnd.data.characterData.Priority
import com.andreyyurko.dnd.data.characterData.addAllMartialWeapons
import com.andreyyurko.dnd.data.characterData.addAllSimpleWeapons
import com.andreyyurko.dnd.data.characterData.character.AbilityNode
import com.andreyyurko.dnd.data.characterData.character.abilityToModifier
import com.andreyyurko.dnd.data.spells.CharacterSpells
import kotlin.math.max

var classFeaturesPaladin: AbilityNode = AbilityNode(
    name = "Паладин: классовые умения",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.savingThrowProf.add(Ability.Wisdom)
        abilities.savingThrowProf.add(Ability.Charisma)
        addAllSimpleWeapons(abilities)
        addAllMartialWeapons(abilities)
        abilities.armorProficiency.add(ArmorProf.LightArmor)
        abilities.armorProficiency.add(ArmorProf.MediumArmor)
        abilities.armorProficiency.add(ArmorProf.HeavyArmor)
        abilities.armorProficiency.add(ArmorProf.Shield)
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair(
            "skill1",
            {
                listOf(
                    athletics.name,
                    intimidation.name,
                    medicine.name,
                    insight.name,
                    religion.name,
                    persuasion.name
                )
            }
        ),
        Pair(
            "skill2",
            {
                listOf(
                    athletics.name,
                    intimidation.name,
                    medicine.name,
                    insight.name,
                    religion.name,
                    persuasion.name
                )
            }
        ),
    ),
    requirements = { abilities: CharacterInfo ->
        abilities.characterClass == Classes.Paladin
    },
    description = "ХИТЫ\n" +
            "\n" +
            "Кость Хитов: 1к10 за каждый уровень паладина\n" +
            "\n" +
            "Хиты на 1 уровне: 10 + модификатор Телосложения\n" +
            "\n" +
            "Хиты на следующих уровнях: 1к10 (или 6) + модификатор Телосложения (суммарно минимум 1) за каждый уровень паладина после первого\n" +
            "\n" +
            "ВЛАДЕНИЕ\n" +
            "\n" +
            "Доспехи: Все виды доспехов, щиты\n" +
            "\n" +
            "Оружие: Простое оружие, воинское оружие\n" +
            "\n" +
            "Инструменты: нет\n" +
            "\n" +
            "Спасброски: Мудрость, Харизма\n" +
            "\n" +
            "Навыки: Выберите два навыка из следующих: Атлетика, Запугивание, Медицина, Проницательность, Религия, Убеждение\n",
    priority = Priority.DoFirst
)

var divineSense: AbilityNode = AbilityNode(
    name = "Божественное чувство",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        if (!abilities.currentState.charges.contains("Божественное чувство")) {
            abilities.currentState.charges["Божественное чувство"] = ChargesCounter(
                current = 1 + abilityToModifier(abilities.charisma),
                maximum = 1 + abilityToModifier(abilities.charisma)
            )
        }
        abilities.currentState.charges["Божественное чувство"]?.let {
            if (it.maximum != 1 + abilityToModifier(abilities.charisma)) {
                abilities.currentState.charges["Божественное чувство"] = ChargesCounter(
                    current = 1 + abilityToModifier(abilities.charisma),
                    maximum = 1 + abilityToModifier(abilities.charisma)
                )
            }
        }
        abilities.actionsMap["Божественное чувство"] =
            Action(
                name = "Божественное чувство",
                description = "Присутствие сильного зла воспринимается вашими чувствами как неприятный запах, а могущественное добро звучит как небесная музыка в ваших ушах. Вы можете действием открыть своё сознание для обнаружения таких сил. Вы до конца своего следующего хода знаете местоположение всех Исчадий, Небожителей и Нежити в пределах 60 футов, не имеющих полного укрытия. Вы знаете вид (Исчадие, Небожитель, Нежить) любого существа, чьё присутствие вы чувствуете, но не можете определить, кто это конкретно (например, вампир граф Страд фон Зарович). В этом же радиусе вы также обнаруживаете присутствие мест и предметов, которые были освящены или осквернены, например, заклинанием святилище [hallow].\n" +
                        "\n" +
                        "Вы можете использовать это умение количество раз, равное 1 + модификатор Харизмы. Когда вы заканчиваете продолжительный отдых, вы восстанавливаете все потраченные использования.\n",
                type = ActionType.Action,
                relatedCharges = "Божественное чувство"
            )

        abilities.additionalAbilities["Божественное чувство"] =
            "Присутствие сильного зла воспринимается вашими чувствами как неприятный запах, а могущественное добро звучит как небесная музыка в ваших ушах.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(listOf()),
    description = "Присутствие сильного зла воспринимается вашими чувствами как неприятный запах, а могущественное добро звучит как небесная музыка в ваших ушах. Вы можете действием открыть своё сознание для обнаружения таких сил. Вы до конца своего следующего хода знаете местоположение всех Исчадий, Небожителей и Нежити в пределах 60 футов, не имеющих полного укрытия. Вы знаете вид (Исчадие, Небожитель, Нежить) любого существа, чьё присутствие вы чувствуете, но не можете определить, кто это конкретно (например, вампир граф Страд фон Зарович). В этом же радиусе вы также обнаруживаете присутствие мест и предметов, которые были освящены или осквернены, например, заклинанием святилище [hallow].\n" +
            "\n" +
            "Вы можете использовать это умение количество раз, равное 1 + модификатор Харизмы. Когда вы заканчиваете продолжительный отдых, вы восстанавливаете все потраченные использования.\n"
)

var layOnHands = AbilityNode(
    name = "Наложение рук",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        if (!abilities.currentState.charges.contains("Наложение рук")) {
            abilities.currentState.charges["Наложение рук"] = ChargesCounter(
                current = 5 * abilities.level,
                maximum = 5 * abilities.level
            )
        }
        abilities.currentState.charges["Наложение рук"]?.let {
            if (it.maximum != 5 * abilities.level) {
                abilities.currentState.charges["Наложение рук"] = ChargesCounter(
                    current = 5 * abilities.level,
                    maximum = 5 * abilities.level
                )
            }
        }
        abilities.actionsMap["Наложение рук"] =
            Action(
                name = "Наложение рук",
                description = "Ваше благословенное касание может лечить раны. У вас есть запас целительной силы, который восстанавливается после продолжительного отдыха. При помощи этого запаса вы можете восстанавливать количество хитов, равное уровню паладина, умноженному на 5.\n" +
                        "\n" +
                        "Вы можете действием коснуться существа и, зачерпнув силу из запаса, восстановить количество хитов этого существа на любое число, вплоть до максимума, оставшегося в вашем запасе.\n" +
                        "\n" +
                        "В качестве альтернативы, вы можете потратить 5 хитов из вашего запаса хитов для излечения цели от одной болезни или одного действующего на неё яда. Вы можете устранить несколько эффектов болезни и ядов одним использованием «Наложения рук», тратя хиты отдельно для каждого эффекта.\n" +
                        "\n" +
                        "Это умение не оказывает никакого эффекта на Нежить и Конструктов.\n",
                type = ActionType.Action
            )

        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    description = "Ваше благословенное касание может лечить раны. У вас есть запас целительной силы, который восстанавливается после продолжительного отдыха. При помощи этого запаса вы можете восстанавливать количество хитов, равное уровню паладина, умноженному на 5.\n" +
            "\n" +
            "Вы можете действием коснуться существа и, зачерпнув силу из запаса, восстановить количество хитов этого существа на любое число, вплоть до максимума, оставшегося в вашем запасе.\n" +
            "\n" +
            "В качестве альтернативы, вы можете потратить 5 хитов из вашего запаса хитов для излечения цели от одной болезни или одного действующего на неё яда. Вы можете устранить несколько эффектов болезни и ядов одним использованием «Наложения рук», тратя хиты отдельно для каждого эффекта.\n" +
            "\n" +
            "Это умение не оказывает никакого эффекта на Нежить и Конструктов.\n",
    priority = Priority.DoLast
)

var paladin1: AbilityNode = AbilityNodeLevel(
    name = "Паладин_1",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.characterClass = Classes.Paladin
        abilities.level += 1
        abilities.proficiencyBonus += 2
        abilities.hp += abilityToModifier(abilities.constitution) + 10
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(classFeaturesPaladin.name) }),
        Pair("second", { listOf(divineSense.name) }),
        Pair("third", { listOf(layOnHands.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "1-й уровень, способности паладина",
    next_level = "Паладин_2"
)

var fightingStylePaladin: AbilityNode = AbilityNode(
    name = "Боевой стиль паладина",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair(
            "style",
            { listOf(dueling.name, defense.name, protection.name, greatWeaponFighting.name) })
    ),
    requirements = { true },
    description = "Вы выбираете боевой стиль, соответствующий вашей специализации. Выберите один из следующих вариантов. Вы не можете выбирать один и тот же вариант боевого стиля, даже если позже у вас будет возможность выбрать еще один стиль."
)

var spellCastingPaladin: AbilityNode = AbilityNode(
    name = "Паладин: использование заклинаний",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.spellsInfo.apply {
            if (!this.contains("Заклинания класса")) {
                this["Заклинания класса"] = CharacterSpells()
            }
            this["Заклинания класса"]?.className = Classes.Paladin.className
            this["Заклинания класса"]?.maxPreparedSpellsCount =
                max((abilityToModifier(abilities.charisma) + abilities.level / 2), 1)
            this["Заклинания класса"]?.maxKnownCantripsCount = 0
        }
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    description = "Вы узнаёте, как черпать божественную магию посредством медитации и молитв, чтобы творить заклятья подобно жрецу.\n" +
            "Подготовка и сотворение заклинаний\n" +
            "\n" +
            "Таблица «Паладин» демонстрирует, какое количество ячеек для накладывания заклинаний паладина у вас есть. Для накладывания одного из этих заклинаний вы должны потратить ячейку заклинаний того же уровня или выше, что и само заклинание. Вы восстановите все потраченные ячейки, когда завершите продолжительный отдых.\n" +
            "\n" +
            "Вы подготавливаете список заклинаний паладина, доступных для накладывания. При этом вы выбираете число заклинаний паладина из списка заклинаний паладина, равное модификатору Харизмы + половина уровня паладина, округляя в меньшую сторону (минимум одно заклинание). Уровень заклинаний не должен превышать уровень самой высокой имеющейся у вас ячейки заклинаний. Например, если вы паладин 5-го уровня, то у вас есть четыре ячейки заклинаний 1-го уровня и две ячейки 2-го уровня. При Харизме 14 ваш список подготовленных заклинаний может включать в себя четыре заклинания 1-го или 2-го уровня, в любой комбинации. Если вы подготовили заклинание 1-го уровня лечение ран [cure wounds], вы можете наложить его, используя ячейку 1-го уровня или ячейку 2-го уровня. Накладывание заклинания не удаляет его из списка подготовленных заклинаний.\n" +
            "\n" +
            "Вы можете изменить список подготовленных заклинаний, когда завершаете продолжительный отдых. Подготовка нового списка заклинаний паладина требует времени, проведённого в молитвах и медитации: не менее 1 минуты за уровень заклинания для каждого заклинания в вашем списке.\n" +
            "Базовая характеристика заклинаний\n" +
            "\n" +
            "Заклинания паладина накладываются с помощью Харизмы, так как их мощь проистекает из силы ваших убеждений. Вы используете Харизму в случаях, когда заклинание ссылается на базовую характеристику. Кроме того, вы используете модификатор Харизмы при определении Сл спасбросков от ваших заклинаний паладина, и при броске атаки заклинаниями.\n" +
            "\n" +
            "Сл спасброска = 8 + ваш бонус мастерства + ваш модификатор Харизмы\n" +
            "\n" +
            "Модификатор броска атаки = ваш бонус мастерства + ваш модификатор Харизмы\n" +
            "Фокусировка заклинания\n" +
            "\n" +
            "Вы можете использовать священный символ в качестве заклинательной фокусировки для заклинаний паладина.\n"
)

var divineSmite: AbilityNode = AbilityNode(
    name = "Божественная кара",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsMap["Божественная кара"] =
            Action(
                name = "Божественная кара",
                description = "Если вы попадаете по существу рукопашной атакой оружием, вы можете потратить одну ячейку заклинания любого своего класса для причинения цели урона излучением, который добавится к урону от оружия. Дополнительный урон равен 2к8 за ячейку 1-го уровня, плюс 1к8 за каждый уровень ячейки выше первого, до максимума 5к8. Если цель — Нежить или Исчадие, урон увеличивается на 1к8 с максимумом 6к8.\n",
                type = ActionType.PartOfAction
            )

        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    description = "Если вы попадаете по существу рукопашной атакой оружием, вы можете потратить одну ячейку заклинания любого своего класса для причинения цели урона излучением, который добавится к урону от оружия. Дополнительный урон равен 2к8 за ячейку 1-го уровня, плюс 1к8 за каждый уровень ячейки выше первого, до максимума 5к8. Если цель — Нежить или Исчадие, урон увеличивается на 1к8 с максимумом 6к8.\n"
)

var paladin2: AbilityNode = AbilityNodeLevel(
    name = "Паладин_2",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities.spellCasterLevel += 1
        abilities.spellsLevel += 1
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(fightingStylePaladin.name) }),
        Pair("second", { listOf(spellCastingPaladin.name) }),
        Pair("third", { listOf(divineSmite.name) })
    ),
    requirements = { true },
    addRequirements = listOf(listOf()),
    description = "2-й уровень, способности паладина",
    next_level = "Паладин_3"
)

var divineHealth: AbilityNode = AbilityNode(
    name = "Божественное здоровье",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Божественное здоровье"] =
            "Божественная магия, текущая через вас, даёт вам иммунитет к болезням.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    description = "Божественная магия, текущая через вас, даёт вам иммунитет к болезням.\n"
)

var sacredOath: AbilityNode = AbilityNode(
    name = "Священная клятва",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        if (!abilities.currentState.charges.contains("Божественный канал")) {
            abilities.currentState.charges["Божественный канал"] = ChargesCounter(
                current = 1,
                maximum = 1
            )
        }
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(oathOfDevotion.name, oathOfConquest.name) })
    ),
    requirements = { true },
    description = "Вы даёте клятву, навсегда связывающую вас как паладина. До этого момента вы были в подготовительной стадии, следуя по пути, но ещё не дав присягу. Все клятвы подробно рассмотрены ниже. Ваш выбор предоставляет вам умения на 3-м, 7-м, 15-м и 20-м уровнях. Эти умения включают в себя заклинания клятвы и умения «Божественного канала».\n" +
            "\n" +
            "ЗАКЛИНАНИЯ КЛЯТВЫ\n" +
            "\n" +
            "Каждая клятва имеет список связанных с ней заклинаний. Вы получаете доступ к этим заклинаниям на уровнях, указанных в описании клятвы. После того как вы получаете доступ к заклинанию клятвы, оно всегда у вас подготовлено. Заклинания клятвы не учитываются в количестве заклинаний, которые можно подготовить каждый день. Если вы получаете заклинание клятвы, которое не входит в список заклинаний паладина, это заклинание всё равно считается заклинанием паладина для вас.\n" +
            "\n" +
            "БОЖЕСТВЕННЫЙ КАНАЛ\n" +
            "\n" +
            "Ваша клятва позволяет направлять божественную энергию, чтобы подпитывать магические эффекты. Каждый вариант «Божественного канала», предоставленный клятвой, объясняет, как его использовать.\n" +
            "\n" +
            "Когда вы используете ваш «Божественный канал», вы выбираете, какой эффект создать. Затем вы должны окончить короткий или продолжительный отдых, чтобы использовать «Божественный канал» снова.\n" +
            "\n" +
            "Некоторые эффекты «Божественного канала» требуют совершить спасбросок. Когда вы используете эффекты такого рода, Сл равна Сл спасброска от ваших заклинаний паладина.\n"
)

var paladin3: AbilityNode = AbilityNodeLevel(
    name = "Паладин_3",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities.spellCasterLevel += 1
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(divineHealth.name) }),
        Pair("second", { listOf(sacredOath.name) })
    ),
    requirements = { true },
    addRequirements = listOf(listOf()),
    description = "3-й уровень, способности паладина",
    next_level = "Паладин_4"
)

var paladin4: AbilityNode = AbilityNodeLevel(
    name = "Паладин_4",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(abilityScoreImprovement.name) }),
    ),
    requirements = { true },
    addRequirements = listOf(listOf()),
    description = "4-й уровень, способности паладина",
    next_level = "Паладин_5"
)

var paladin5: AbilityNode = AbilityNodeLevel(
    name = "Паладин_5",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities.spellCasterLevel += 1
        abilities.spellsLevel += 1
        abilities.proficiencyBonus += 1
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(extraAttack.name) })
    ),
    requirements = { true },
    addRequirements = listOf(listOf()),
    description = "5-й уровень, способности паладина",
    next_level = "Паладин_6"
)

var auraOfProtection: AbilityNode = AbilityNode(
    name = "Аура защиты",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        var radius = "10"
        if (abilities.level >= 18) radius = "30"
        abilities.additionalAbilities["Аура защиты"] =
            "Если вы или дружественное существо в пределах $radius футов от вас должны совершить спасбросок, это существо получает бонус к спасброску, равный модификатору вашей Харизмы (минимальный бонус +1). Вы должны находиться в сознании, чтобы предоставлять этот бонус.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    description = "Если вы или дружественное существо в пределах 10 футов от вас должны совершить спасбросок, это существо получает бонус к спасброску, равный модификатору вашей Харизмы (минимальный бонус +1). Вы должны находиться в сознании, чтобы предоставлять этот бонус. На 18-м уровне дистанция этой ауры увеличивается до 30 футов.\n"
)

var paladin6: AbilityNode = AbilityNodeLevel(
    name = "Паладин_6",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(auraOfProtection.name) })
    ),
    requirements = { true },
    addRequirements = listOf(listOf()),
    description = "6-й уровень, способности паладина",
    next_level = "Паладин_7"
)

var paladin7: AbilityNode = AbilityNodeLevel(
    name = "Паладин_7",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities.spellCasterLevel += 1
        abilities
    },
    getAlternatives = mutableMapOf(
    ),
    requirements = { true },
    addRequirements = listOf(listOf()),
    description = "7-й уровень, способности паладина",
    next_level = "Паладин_8"
)

var paladin8: AbilityNode = AbilityNodeLevel(
    name = "Паладин_8",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(abilityScoreImprovement.name) }),
    ),
    requirements = { true },
    addRequirements = listOf(listOf()),
    description = "8-й уровень, способности паладина",
    next_level = "Паладин_9"
)

var paladin9: AbilityNode = AbilityNodeLevel(
    name = "Паладин_9",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities.spellCasterLevel += 1
        abilities.spellsLevel += 1
        abilities.proficiencyBonus += 1
        abilities
    },
    getAlternatives = mutableMapOf(
    ),
    requirements = { true },
    addRequirements = listOf(listOf()),
    description = "9-й уровень, способности паладина",
    next_level = "Паладин_10"
)

var auraOfCourage: AbilityNode = AbilityNode(
    name = "Аура отваги",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        var radius = "10"
        if (abilities.level >= 18) radius = "30"
        abilities.additionalAbilities["Аура отваги"] =
            "Вы и дружественные существа в пределах $radius футов от вас не можете быть испуганы, пока вы находитесь в сознании.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    description = "Вы и дружественные существа в пределах 10 футов от вас не можете быть испуганы, пока вы находитесь в сознании. На 18-м уровне дистанция этой ауры увеличивается до 30 футов.\n"
)

var paladin10: AbilityNode = AbilityNodeLevel(
    name = "Паладин_10",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(auraOfCourage.name) })
    ),
    requirements = { true },
    addRequirements = listOf(listOf()),
    description = "10-й уровень, способности паладина",
    next_level = "Паладин_11"
)

var improvedDivineSmite: AbilityNode = AbilityNode(
    name = "Улучшенная божественная кара",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsMap["Улучшенная божественная кара"] =
            Action(
                name = "Улучшенная божественная кара",
                description = "Каждый раз, когда вы попадаете по существу рукопашным оружием, это существо получает дополнительные 1к8 урона излучением.\n",
                type = ActionType.PartOfAction
            )

        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    description = "Вы проникаетесь праведной мощью, что даёт всем вашим ударам рукопашным оружием божественную силу. Каждый раз, когда вы попадаете по существу рукопашным оружием, это существо получает дополнительные 1к8 урона излучением.\n"
)

var paladin11: AbilityNode = AbilityNodeLevel(
    name = "Паладин_11",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities.spellCasterLevel += 1
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(improvedDivineSmite.name) })
    ),
    requirements = { true },
    addRequirements = listOf(listOf()),
    description = "11-й уровень, способности паладина",
    next_level = "Паладин_12"
)

var paladin12: AbilityNode = AbilityNodeLevel(
    name = "Паладин_12",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(abilityScoreImprovement.name) }),
    ),
    requirements = { true },
    addRequirements = listOf(listOf()),
    description = "12-й уровень, способности паладина",
    next_level = "Паладин_13"
)

var paladin13: AbilityNode = AbilityNodeLevel(
    name = "Паладин_13",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities.spellCasterLevel += 1
        abilities.spellsLevel += 1
        abilities.proficiencyBonus += 1
        abilities
    },
    getAlternatives = mutableMapOf(
    ),
    requirements = { true },
    addRequirements = listOf(listOf()),
    description = "13-й уровень, способности паладина",
    next_level = "Паладин_14"
)

var cleansingTouch: AbilityNode = AbilityNode(
    name = "Очищающее касание",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        if (!abilities.currentState.charges.contains("Очищающее касание")) {
            abilities.currentState.charges["Очищающее касание"] = ChargesCounter(
                current = max(1, abilityToModifier(abilities.charisma)),
                maximum = max(1, abilityToModifier(abilities.charisma))
            )
        }
        abilities.actionsMap["Очищающее касание"] =
            Action(
                name = "Очищающее касание",
                description = "Вы можете действием окончить действие заклинания на себе или на одном согласном существе, которого вы касаетесь. Вы можете использовать это умение количество раз, равное вашему модификатору Харизмы (минимум 1). Вы восстанавливаете возможность использования после продолжительного отдыха.\n",
                type = ActionType.Action,
                relatedCharges = "Очищающее касание"
            )

        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    description = "Вы можете действием окончить действие заклинания на себе или на одном согласном существе, которого вы касаетесь. Вы можете использовать это умение количество раз, равное вашему модификатору Харизмы (минимум 1). Вы восстанавливаете возможность использования после продолжительного отдыха.\n"
)


var paladin14: AbilityNode = AbilityNodeLevel(
    name = "Паладин_14",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(cleansingTouch.name) })
    ),
    requirements = { true },
    addRequirements = listOf(listOf()),
    description = "14-й уровень, способности паладина",
    next_level = "Паладин_15"
)

var paladin15: AbilityNode = AbilityNodeLevel(
    name = "Паладин_15",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities.spellCasterLevel += 1
        abilities
    },
    getAlternatives = mutableMapOf(
    ),
    requirements = { true },
    addRequirements = listOf(listOf()),
    description = "15-й уровень, способности паладина",
    next_level = "Паладин_16"
)

var paladin16: AbilityNode = AbilityNodeLevel(
    name = "Паладин_16",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(abilityScoreImprovement.name) }),
    ),
    requirements = { true },
    addRequirements = listOf(listOf()),
    description = "16-й уровень, способности паладина",
    next_level = "Паладин_17"
)

var paladin17: AbilityNode = AbilityNodeLevel(
    name = "Паладин_17",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities.spellCasterLevel += 1
        abilities.spellsLevel += 1
        abilities.proficiencyBonus += 1
        abilities
    },
    getAlternatives = mutableMapOf(
    ),
    requirements = { true },
    addRequirements = listOf(listOf()),
    description = "17-й уровень, способности паладина",
    next_level = "Паладин_18"
)

var paladin18: AbilityNode = AbilityNodeLevel(
    name = "Паладин_18",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    getAlternatives = mutableMapOf(
    ),
    requirements = { true },
    addRequirements = listOf(listOf()),
    description = "18-й уровень, способности паладина",
    next_level = "Паладин_19"
)

var paladin19: AbilityNode = AbilityNodeLevel(
    name = "Паладин_19",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities.spellCasterLevel += 1
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(abilityScoreImprovement.name) }),
    ),
    requirements = { true },
    addRequirements = listOf(listOf()),
    description = "19-й уровень, способности паладина",
    next_level = "Паладин_20"
)

var paladin20: AbilityNode = AbilityNodeLevel(
    name = "Паладин_20",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    getAlternatives = mutableMapOf(
    ),
    requirements = { true },
    addRequirements = listOf(listOf()),
    description = "20-й уровень, способности паладина",
    next_level = null
)


var mapOfPaladinAbilities: MutableMap<String, AbilityNode> = (mutableMapOf(
    Pair(classFeaturesPaladin.name, classFeaturesPaladin),
    Pair(divineSense.name, divineSense),
    Pair(layOnHands.name, layOnHands),
    Pair(paladin1.name, paladin1),
    Pair(fightingStylePaladin.name, fightingStylePaladin),
    Pair(spellCastingPaladin.name, spellCastingPaladin),
    Pair(divineSmite.name, divineSmite),
    Pair(paladin2.name, paladin2),
    Pair(divineHealth.name, divineHealth),
    Pair(sacredOath.name, sacredOath),
    Pair(paladin3.name, paladin3),
    Pair(paladin4.name, paladin4),
    Pair(paladin5.name, paladin5),
    Pair(auraOfProtection.name, auraOfProtection),
    Pair(paladin6.name, paladin6),
    Pair(paladin7.name, paladin7),
    Pair(paladin8.name, paladin8),
    Pair(paladin9.name, paladin9),
    Pair(auraOfCourage.name, auraOfCourage),
    Pair(paladin10.name, paladin10),
    Pair(improvedDivineSmite.name, improvedDivineSmite),
    Pair(paladin11.name, paladin11),
    Pair(paladin12.name, paladin12),
    Pair(paladin13.name, paladin13),
    Pair(cleansingTouch.name, cleansingTouch),
    Pair(paladin14.name, paladin14),
    Pair(paladin15.name, paladin15),
    Pair(paladin16.name, paladin16),
    Pair(paladin17.name, paladin17),
    Pair(paladin18.name, paladin18),
    Pair(paladin19.name, paladin19),
    Pair(paladin20.name, paladin20),
)
        + mapOfOathOfDevotionAbilities
        + mapOfOathOfConquestAbilities
        ).toMutableMap()







