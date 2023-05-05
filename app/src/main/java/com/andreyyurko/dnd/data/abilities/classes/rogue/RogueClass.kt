package com.andreyyurko.dnd.data.abilities.classes.rogue

import com.andreyyurko.dnd.data.abilities.classes.AbilityNodeLevel
import com.andreyyurko.dnd.data.abilities.other.*
import com.andreyyurko.dnd.data.characterData.*
import com.andreyyurko.dnd.data.characterData.character.AbilityNode
import com.andreyyurko.dnd.data.characterData.character.abilityToModifier

var classFeaturesRogue: AbilityNode = AbilityNode(
    name = "Плут: классовые умения",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.savingThrowProf.add(Ability.Intelligence)
        abilities.savingThrowProf.add(Ability.Dexterity)
        addAllSimpleWeapons(abilities)
        abilities.weaponProficiency.add(Weapon.CrossbowHand)
        abilities.weaponProficiency.add(Weapon.Longsword)
        abilities.weaponProficiency.add(Weapon.Rapier)
        abilities.weaponProficiency.add(Weapon.ShortSword)
        abilities.armorProficiency.add(ArmorProf.LightArmor)
        abilities.toolProficiency.add(Tools.ThievesTools)
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair(
            "skill1",
            {
                listOf(
                    acrobatics.name,
                    athletics.name,
                    perception.name,
                    performance.name,
                    intimidation.name,
                    sleightOfHand.name,
                    deception.name,
                    investigation.name,
                    insight.name,
                    stealth.name,
                    persuasion.name
                )
            }
        ),
        Pair(
            "skill2",
            {
                listOf(
                    acrobatics.name,
                    athletics.name,
                    perception.name,
                    performance.name,
                    intimidation.name,
                    sleightOfHand.name,
                    deception.name,
                    investigation.name,
                    insight.name,
                    stealth.name,
                    persuasion.name
                )
            }
        ),
        Pair(
            "skill3",
            {
                listOf(
                    acrobatics.name,
                    athletics.name,
                    perception.name,
                    performance.name,
                    intimidation.name,
                    sleightOfHand.name,
                    deception.name,
                    investigation.name,
                    insight.name,
                    stealth.name,
                    persuasion.name
                )
            }
        ),
        Pair(
            "skill4",
            {
                listOf(
                    acrobatics.name,
                    athletics.name,
                    perception.name,
                    performance.name,
                    intimidation.name,
                    sleightOfHand.name,
                    deception.name,
                    investigation.name,
                    insight.name,
                    stealth.name,
                    persuasion.name
                )
            }
        )
    ),
    requirements = { abilities: CharacterInfo ->
        abilities.characterClass == Classes.Rogue
    },
    description = "ХИТЫ\n" +
            "\n" +
            "Кость Хитов: 1к8 за каждый уровень плута\n" +
            "\n" +
            "Хиты на 1 уровне: 8 + модификатор Телосложения\n" +
            "\n" +
            "Хиты на следующих уровнях: 1к8 (или 5) + модификатор Телосложения (суммарно минимум 1) за каждый уровень плута после первого\n" +
            "\n" +
            "ВЛАДЕНИЕ\n" +
            "\n" +
            "Доспехи: Лёгкие доспехи\n" +
            "\n" +
            "Оружие: Простое оружие, ручные арбалеты, длинные мечи, рапиры, короткие мечи\n" +
            "\n" +
            "Инструменты: Воровские инструменты\n" +
            "\n" +
            "Спасброски: Ловкость, Интеллект\n" +
            "\n" +
            "Навыки: Выберите четыре навыка из следующих: Акробатика, Атлетика, Восприятие, Выступление, Запугивание, Ловкость рук, Обман, Проницательность, Расследование, Скрытность, Убеждение.\n",
    priority = Priority.DoFirst
)

var expertiseRogue: AbilityNode = AbilityNode(
    name = "Экспертиза плута",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair(
            "expertise1",
            {
                listOf(
                    acrobaticsExpertise.name,
                    animalHandlingExpertise.name,
                    arcanaExpertise.name,
                    athleticsExpertise.name,
                    deceptionExpertise.name,
                    historyExpertise.name,
                    insightExpertise.name,
                    intimidationExpertise.name,
                    investigationExpertise.name,
                    medicineExpertise.name,
                    natureExpertise.name,
                    perceptionExpertise.name,
                    performanceExpertise.name,
                    persuasionExpertise.name,
                    religionExpertise.name,
                    sleightOfHandExpertise.name,
                    stealthExpertise.name,
                    survivalExpertise.name,
                    thievesToolsExpertise.name
                )
            }
        ),
        Pair(
            "expertise2",
            {
                listOf(
                    acrobaticsExpertise.name,
                    animalHandlingExpertise.name,
                    arcanaExpertise.name,
                    athleticsExpertise.name,
                    deceptionExpertise.name,
                    historyExpertise.name,
                    insightExpertise.name,
                    intimidationExpertise.name,
                    investigationExpertise.name,
                    medicineExpertise.name,
                    natureExpertise.name,
                    perceptionExpertise.name,
                    performanceExpertise.name,
                    persuasionExpertise.name,
                    religionExpertise.name,
                    sleightOfHandExpertise.name,
                    stealthExpertise.name,
                    survivalExpertise.name,
                    thievesToolsExpertise.name
                )
            }
        )
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "Выберите два ваших владения в навыках или одно владение навыком и владение воровскими инструментами. Ваш бонус мастерства удваивается для всех проверок характеристик, которые вы совершаете, используя любое из выбранных владений.\n"
)

var sneakAttack: AbilityNode = AbilityNode(
    name = "Скрытая атака",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        var dice = ((abilities.level + 1) / 2).toString() + "к6"
        abilities.actionsList.add(
            Action(
                name = "Скрытая атака",
                description = "Вы знаете, как точно наносить удар и использовать отвлечение врага. Один раз в ход вы можете причинить дополнительный урон " + dice + " одному из существ, по которому вы попали атакой, совершённой с преимуществом к броску атаки. Атака должна использовать дальнобойное оружие или оружие со свойством «фехтовальное». Вам не нужно иметь преимущество при броске атаки, если другой враг цели находится в пределах 5 футов от неё. Этот враг не должен быть недееспособным, и у вас не должно быть помехи для броска атаки.\n",
                type = ActionType.PartOfAction,
            )
        )
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "Вы знаете, как точно наносить удар и использовать отвлечение врага. Один раз в ход вы можете причинить дополнительный урон 1к6 одному из существ, по которому вы попали атакой, совершённой с преимуществом к броску атаки. Атака должна использовать дальнобойное оружие или оружие со свойством «фехтовальное». Вам не нужно иметь преимущество при броске атаки, если другой враг цели находится в пределах 5 футов от неё. Этот враг не должен быть недееспособным, и у вас не должно быть помехи для броска атаки.\n" +
            "\n" +
            "Дополнительный урон увеличивается, когда вы получаете уровни в этом классе, как показано в колонке «скрытая атака».\n"
)

var thievesCant: AbilityNode = AbilityNode(
    name = "Воровской жаргон",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Воровской жаргон"] =
            "Во время плутовского обучения вы выучили воровской жаргон, тайную смесь диалекта, жаргона и шифра, который позволяет скрывать сообщения в, казалось бы, обычном разговоре. Только другое существо, знающее воровской жаргон, понимает такие сообщения. Это занимает в четыре раза больше времени, нежели передача тех же слов прямым текстом.\n" +
                    "\n" +
                    "Кроме того, вы понимаете набор секретных знаков и символов, используемый для передачи коротких и простых сообщений. Например, является ли область опасной или территорией гильдии воров, находится ли поблизости добыча, простодушны ли люди в округе, и предоставляют ли здесь безопасное убежище для воров в бегах.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "Во время плутовского обучения вы выучили воровской жаргон, тайную смесь диалекта, жаргона и шифра, который позволяет скрывать сообщения в, казалось бы, обычном разговоре. Только другое существо, знающее воровской жаргон, понимает такие сообщения. Это занимает в четыре раза больше времени, нежели передача тех же слов прямым текстом.\n" +
            "\n" +
            "Кроме того, вы понимаете набор секретных знаков и символов, используемый для передачи коротких и простых сообщений. Например, является ли область опасной или территорией гильдии воров, находится ли поблизости добыча, простодушны ли люди в округе, и предоставляют ли здесь безопасное убежище для воров в бегах.\n"
)

var rogue1: AbilityNodeLevel = AbilityNodeLevel(
    name = "Плут_1",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.characterClass = Classes.Rogue
        abilities.level += 1
        abilities.proficiencyBonus += 2
        abilities.hp += abilityToModifier(abilities.constitution) + 10
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(classFeaturesRogue.name) }),
        Pair("second", { listOf(expertiseRogue.name) }),
        Pair("third", { listOf(sneakAttack.name) }),
        Pair("fourth", { listOf(thievesCant.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "1-й уровень, способности плута",
    next_level = "Плут_2",
)

var cunningAction: AbilityNode = AbilityNode(
    name = "Хитрое действие",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Отступление",
                description = "Если вы выполнили это действие, " +
                        "ваше перемещение не провоцирует внеочередных атак до конца этого хода\n",
                type = ActionType.Bonus
            )
        )
        abilities.actionsList.add(
            Action(
                name = "Рывок",
                description = "Если вы совершаете действие «Рывок», вы получаете дополнительное перемещение в текущем ходу, равное вашей скорости после применения всех модификаторов.\n",
                type = ActionType.Bonus
            )
        )
        abilities.actionsList.add(
            Action(
                name = "Засада",
                description = "Вы не можете прятаться от существа, которое видит вас, и если вы издадите шум (например, прокричите предупреждение или уроните вазу), вы выдаёте своё местоположение.\n" +
                        "\n" +
                        "Когда вы пытаетесь спрятаться, совершите проверку Ловкости (Скрытность). Пока вас не найдут или вы не прекратите прятаться, результат этой проверки будет противостоять проверкам Мудрости (Восприятие) существ, активно ищущих вас.\n" +
                        "\n" +
                        "Если вы прячетесь, есть шанс, что вас заметят даже без активных поисков. Для определения того, заметило ли вас существо, Мастер сравнивает результат вашей проверки Ловкости (Скрытность) с пассивным значением Мудрости (Восприятие), которое равно 10 + модификатор Мудрости существа, плюс все уместные бонусы и штрафы.\n",
                type = ActionType.Bonus
            )
        )
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "Ваше мышление и ловкость позволяют двигаться и действовать быстрее. Вы можете в каждом своем ходу боя совершать бонусное действие. Это действие может быть использовано только для Рывка, Отхода или Засады.\n"
)

var rogue2: AbilityNodeLevel = AbilityNodeLevel(
    name = "Плут_2",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(cunningAction.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "2-й уровень, способности плута",
    next_level = "Плут_3",
)

var roguishArchetype: AbilityNode = AbilityNode(
    name = "Архетип плута",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(assassinArchetype.name, arcaneTrickster.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "Вы выбираете архетип, который отображает ваши плутовские способности. Подробности всех архетипов находятся ниже. Выбранный вами архетип предоставляет умения на 3-м, 9-м, 13-м и 17-м уровнях.\n"
)

var rogue3: AbilityNodeLevel = AbilityNodeLevel(
    name = "Плут_3",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(roguishArchetype.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "3-й уровень, способности плута",
    next_level = "Плут_4",
)

var rogue4: AbilityNodeLevel = AbilityNodeLevel(
    name = "Плут_4",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(abilityScoreImprovement.name) }),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "4-й уровень, способности плута",
    next_level = "Плут_5",
)

var uncannyDodge: AbilityNode = AbilityNode(
    name = "Невероятное уклонение",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Невероятное уклонение"] =
            "Когда нападающий, которого вы можете видеть, попадает по вам атакой, вы можете реакцией уменьшить вдвое урон, причиняемый вам этой атакой.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "Когда нападающий, которого вы можете видеть, попадает по вам атакой, вы можете реакцией уменьшить вдвое урон, причиняемый вам этой атакой.\n"
)

var rogue5: AbilityNodeLevel = AbilityNodeLevel(
    name = "Плут_5",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities.proficiencyBonus += 1
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(uncannyDodge.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "5-й уровень, способности плута",
    next_level = "Плут_6",
)

var rogue6: AbilityNodeLevel = AbilityNodeLevel(
    name = "Плут_6",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("second", { listOf(expertiseRogue.name) }),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "6-й уровень, способности плута",
    next_level = "Плут_7",
)

var rogueEvasion: AbilityNode = AbilityNode(
    name = "Плутовская увёртливость",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Плутовская увёртливость"] =
            "Вы можете ловко увернуться от зональных эффектов, например, огненного дыхания красного дракона или заклинания град [ice storm]. Если вы попадаете под действие эффекта, который позволяет вам совершить спасбросок Ловкости, чтобы получить только половину урона, вместо этого вы не получаете вовсе никакого урона, если спасбросок был успешен, и получаете только половину урона, если он был провален.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(listOf()),
    description = "Вы можете ловко увернуться от зональных эффектов, например, огненного дыхания красного дракона или заклинания град [ice storm]. Если вы попадаете под действие эффекта, который позволяет вам совершить спасбросок Ловкости, чтобы получить только половину урона, вместо этого вы не получаете вовсе никакого урона, если спасбросок был успешен, и получаете только половину урона, если он был провален.\n"
)

var rogue7: AbilityNodeLevel = AbilityNodeLevel(
    name = "Плут_7",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(rogueEvasion.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "7-й уровень, способности плута",
    next_level = "Плут_8",
)

var rogue8: AbilityNodeLevel = AbilityNodeLevel(
    name = "Плут_8",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(abilityScoreImprovement.name) }),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "8-й уровень, способности плута",
    next_level = "Плут_9",
)

var rogue9: AbilityNodeLevel = AbilityNodeLevel(
    name = "Плут_9",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities.proficiencyBonus += 1
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "9-й уровень, способности плута",
    next_level = "Плут_10",
)

var rogue10: AbilityNodeLevel = AbilityNodeLevel(
    name = "Плут_10",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(abilityScoreImprovement.name) }),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "10-й уровень, способности плута",
    next_level = "Плут_11",
)

var reliableTalent: AbilityNode = AbilityNode(
    name = "Надёжный талант",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Надёжный талант"] =
            "Вы улучшаете выбранные навыки, пока они не достигнут совершенства. Каждый раз, когда вы совершаете проверку характеристики, которая позволяет добавить бонус мастерства, вы можете при выпадении на к20 результата «1–9» считать, что выпало «10».\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(listOf()),
    description = "Вы улучшаете выбранные навыки, пока они не достигнут совершенства. Каждый раз, когда вы совершаете проверку характеристики, которая позволяет добавить бонус мастерства, вы можете при выпадении на к20 результата «1–9» считать, что выпало «10».\n"
)

var rogue11: AbilityNodeLevel = AbilityNodeLevel(
    name = "Плут_11",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(reliableTalent.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "11-й уровень, способности плута",
    next_level = "Плут_12",
)

var rogue12: AbilityNodeLevel = AbilityNodeLevel(
    name = "Плут_12",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(abilityScoreImprovement.name) }),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "12-й уровень, способности плута",
    next_level = "Плут_13",
)

var rogue13: AbilityNodeLevel = AbilityNodeLevel(
    name = "Плут_13",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.proficiencyBonus += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "13-й уровень, способности плута",
    next_level = "Плут_14",
)

var blindsense: AbilityNode = AbilityNode(
    name = "Слепое зрение",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Слепое зрение"] =
            "Если вы можете слышать, то знаете о местонахождении всех скрытых и невидимых существ в пределах 10 футов от себя.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(listOf()),
    description = "Если вы можете слышать, то знаете о местонахождении всех скрытых и невидимых существ в пределах 10 футов от себя.\n"
)

var rogue14: AbilityNodeLevel = AbilityNodeLevel(
    name = "Плут_14",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(blindsense.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "14-й уровень, способности плута",
    next_level = "Плут_15",
)

var slipperyMind: AbilityNode = AbilityNode(
    name = "Скользкий ум",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.savingThrowProf.add(Ability.Wisdom)
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(listOf()),
    description = "Вы увеличиваете силу мышления. Вы получаете владение спасбросками Мудрости.\n"
)

var rogue15: AbilityNodeLevel = AbilityNodeLevel(
    name = "Плут_15",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(slipperyMind.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "15-й уровень, способности плута",
    next_level = "Плут_16",
)

var rogue16: AbilityNodeLevel = AbilityNodeLevel(
    name = "Плут_16",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(abilityScoreImprovement.name) }),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "16-й уровень, способности плута",
    next_level = "Плут_17",
)

var rogue17: AbilityNodeLevel = AbilityNodeLevel(
    name = "Плут_17",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities.proficiencyBonus += 1
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "17-й уровень, способности плута",
    next_level = "Плут_18",
)

var elusive: AbilityNode = AbilityNode(
    name = "Неуловимость",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Неуловимость"] =
            "Вы можете уклоняться так хорошо, что противник крайне редко может взять над вами верх. Никакие броски атаки не получают преимущества над вами, пока вы не станете недееспособным.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(listOf()),
    description = "Вы можете уклоняться так хорошо, что противник крайне редко может взять над вами верх. Никакие броски атаки не получают преимущества над вами, пока вы не станете недееспособным.\n"
)

var rogue18: AbilityNodeLevel = AbilityNodeLevel(
    name = "Плут_18",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(elusive.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "18-й уровень, способности плута",
    next_level = "Плут_19",
)

var rogue19: AbilityNodeLevel = AbilityNodeLevel(
    name = "Плут_19",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(abilityScoreImprovement.name) }),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "19-й уровень, способности плута",
    next_level = "Плут_20",
)

var strokeOfLuck: AbilityNode = AbilityNode(
    name = "Удача",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        if (!abilities.currentState.charges.contains("Удача плута")) {
            abilities.currentState.charges["Удача плута"] = ChargesCounter(
                current = 1,
                maximum = 1
            )
        }
        abilities.actionsList.add(
            Action(
                name = "Удача плута",
                description = "Вы получаете сверхъестественный дар преуспевать, когда это нужнее всего. Если ваша атака промахивается по цели, находящейся в пределах досягаемости, вы можете изменить промах на попадание. В качестве альтернативы, если вы провалили проверку характеристики, вы можете заменить результат, выпавший на к20, на «20».\n" +
                        "\n" +
                        "Использовав это умение, вы не можете использовать его повторно, пока не завершите короткий или продолжительный отдых.\n",
                type = ActionType.Additional,
                relatedCharges = "Удача плута"
            )
        )
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(listOf()),
    description = "Вы получаете сверхъестественный дар преуспевать, когда это нужнее всего. Если ваша атака промахивается по цели, находящейся в пределах досягаемости, вы можете изменить промах на попадание. В качестве альтернативы, если вы провалили проверку характеристики, вы можете заменить результат, выпавший на к20, на «20».\n" +
            "\n" +
            "Использовав это умение, вы не можете использовать его повторно, пока не завершите короткий или продолжительный отдых.\n"
)

var rogue20: AbilityNodeLevel = AbilityNodeLevel(
    name = "Плут_20",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(strokeOfLuck.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "20-й уровень, способности плута",
    next_level = null,
)

var mapOfRogueAbilities: MutableMap<String, AbilityNode> = (mutableMapOf(
    Pair(classFeaturesRogue.name, classFeaturesRogue),
    Pair(expertiseRogue.name, expertiseRogue),
    Pair(sneakAttack.name, sneakAttack),
    Pair(thievesCant.name, thievesCant),
    Pair(rogue1.name, rogue1),
    Pair(cunningAction.name, cunningAction),
    Pair(rogue2.name, rogue2),
    Pair(roguishArchetype.name, roguishArchetype),
    Pair(rogue3.name, rogue3),
    Pair(rogue4.name, rogue4),
    Pair(uncannyDodge.name, uncannyDodge),
    Pair(rogue5.name, rogue5),
    Pair(rogue6.name, rogue6),
    Pair(rogueEvasion.name, rogueEvasion),
    Pair(rogue7.name, rogue7),
    Pair(rogue8.name, rogue8),
    Pair(rogue9.name, rogue9),
    Pair(rogue10.name, rogue10),
    Pair(reliableTalent.name, reliableTalent),
    Pair(rogue11.name, rogue11),
    Pair(rogue12.name, rogue12),
    Pair(rogue13.name, rogue13),
    Pair(blindsense.name, blindsense),
    Pair(rogue14.name, rogue14),
    Pair(slipperyMind.name, slipperyMind),
    Pair(rogue15.name, rogue15),
    Pair(rogue16.name, rogue16),
    Pair(rogue17.name, rogue17),
    Pair(elusive.name, elusive),
    Pair(rogue18.name, rogue18),
    Pair(rogue19.name, rogue19),
    Pair(strokeOfLuck.name, strokeOfLuck),
    Pair(rogue20.name, rogue20),
)
        + mapOfAssassinArchetypeAbilities
        + mapOfArcaneTricksterArchetypeAbilities
        ).toMutableMap()