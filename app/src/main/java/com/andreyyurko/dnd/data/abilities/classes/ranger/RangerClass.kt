package com.andreyyurko.dnd.data.abilities.classes.ranger

import com.andreyyurko.dnd.data.abilities.classes.AbilityNodeLevel
import com.andreyyurko.dnd.data.abilities.classes.extraAttack
import com.andreyyurko.dnd.data.abilities.other.*
import com.andreyyurko.dnd.data.characterData.*
import com.andreyyurko.dnd.data.characterData.character.AbilityNode
import com.andreyyurko.dnd.data.characterData.character.abilityToModifier
import com.andreyyurko.dnd.data.spells.CharacterSpells
import kotlin.math.max


var classFeaturesRanger: AbilityNode = AbilityNode(
    name = "Следопыт: классовые умения",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.savingThrowProf.add(Ability.Strength)
        abilities.savingThrowProf.add(Ability.Dexterity)
        addAllSimpleWeapons(abilities)
        addAllMartialWeapons(abilities)
        abilities.armorProficiency.add(ArmorProf.LightArmor)
        abilities.armorProficiency.add(ArmorProf.MediumArmor)
        abilities.armorProficiency.add(ArmorProf.Shield)
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair(
            "skill1"
        ) {
            listOf(
                athletics.name,
                perception.name,
                survival.name,
                nature.name,
                insight.name,
                investigation.name,
                stealth.name,
                animalHandling.name
            )
        },
        Pair(
            "skill2"
        ) {
            listOf(
                athletics.name,
                perception.name,
                survival.name,
                nature.name,
                insight.name,
                investigation.name,
                stealth.name,
                animalHandling.name
            )
        },
        Pair(
            "skill3"
        ) {
            listOf(
                athletics.name,
                perception.name,
                survival.name,
                nature.name,
                insight.name,
                investigation.name,
                stealth.name,
                animalHandling.name
            )
        }
    ),
    requirements = { abilities: CharacterInfo ->
        abilities.characterClass == Classes.Ranger
    },
    description = "ХИТЫ\n" +
            "\n" +
            "Кость Хитов: 1к10 за каждый уровень следопыта\n" +
            "\n" +
            "Хиты на 1 уровне: 10 + модификатор Телосложения\n" +
            "\n" +
            "Хиты на следующих уровнях: 1к10 (или 6) + модификатор Телосложения (суммарно минимум 1) за каждый уровень следопыта после первого\n" +
            "\n" +
            "ВЛАДЕНИЕ\n" +
            "\n" +
            "Доспехи: Лёгкие доспехи, средние доспехи, щиты\n" +
            "\n" +
            "Оружие: Простое оружие, воинское оружие\n" +
            "\n" +
            "Инструменты: Нет\n" +
            "\n" +
            "Спасброски: Сила, Ловкость\n" +
            "\n" +
            "Навыки: Выберите три навыка из следующих: Атлетика, Восприятие, Выживание, Природа, Проницательность, Расследование, Скрытность, Уход за животными\n",
    priority = Priority.DoFirst
)

var fightingStyleRanger: AbilityNode = AbilityNode(
    name = "Боевой стиль",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("style") { listOf(dueling.name, defense.name, twoWeaponFighting.name, archery.name) }
    ),
    requirements = { abilities: CharacterInfo ->
        abilities.characterClass == Classes.Ranger
    },
    description = "Вы выбираете боевой стиль, соответствующий вашей специализации. Выберите один из следующих вариантов. Вы не можете выбирать один и тот же вариант боевого стиля, даже если позже у вас будет возможность выбрать еще один стиль."
)

var favoredEnemySingleChoice: AbilityNode = AbilityNode(
    name = "Избранный враг: негуманоидиный выбор",
    changesInCharacterInfo = {abilities: CharacterInfo -> abilities },
    getAlternatives = mutableMapOf(
        Pair("first") { listOf("Аберрации", "Великаны", "Драконы", "Звери", "Исчадия", "Конструкты", "Монстры",
            "Небожители", "Нежить", "Растения", "Слизи", "Феи", "Элементали") },
        Pair("second") { mapOfLanguages.keys.toList() }
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "",
    actionForChoice = mutableMapOf(
        Pair("first") { choice: String, abilities: CharacterInfo ->
            var enemies = "$choice"
            abilities.additionalAbilities["Избранный враг"]?.let {
                val previousEnemies = it.split("\n")[1].split(", ").joinToString(" ")
                if (!previousEnemies.contains(enemies)) {
                    enemies += " $previousEnemies"
                }
            }
            abilities.additionalAbilities["Избранный враг"] = "Избранные враги:\n${enemies.split(" ").joinToString(", ")}" +
                    "\n" +
                    "Вы совершаете с преимуществом проверки Мудрости (Выживание) для выслеживания избранных врагов, а также проверки Интеллекта для вспоминания информации о них.\n"
            abilities
        }
    )
)

var favoredEnemyDoubleChoice: AbilityNode = AbilityNode(
    name = "Избранный враг: гуманоидиный выбор",
    changesInCharacterInfo = {abilities: CharacterInfo -> abilities },
    getAlternatives = mutableMapOf(
        Pair("first") { listOf("Аасимар", "Багбир", "Гит", "Гном", "Гоблин", "Голифа", "Дворф", "Драконорождённый", "Кенку", "Кобольд",
            "Людоящер", "Орк", "Полуорк", "Полурослик", "Полуэльф", "Табакси", "Тифлинг", "Тритон", "Фирболг", "Хобгоблин", "Человек", "Эльф", "Юань-ти") },
        Pair("second") { listOf("Аасимар", "Багбир", "Гит", "Гном", "Гоблин", "Голифа", "Дворф", "Драконорождённый", "Кенку", "Кобольд",
            "Людоящер", "Орк", "Полуорк", "Полурослик", "Полуэльф", "Табакси", "Тифлинг", "Тритон", "Фирболг", "Хобгоблин", "Человек", "Эльф", "Юань-ти") },
        Pair("third") { mapOfLanguages.keys.toList() },
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "",
    actionForChoice = mutableMapOf(
        Pair("first") { choice: String, abilities: CharacterInfo ->
            var enemies = "$choice"
            abilities.additionalAbilities["Избранный враг"]?.let {
                val previousEnemies = it.split("\n")[1].split(", ").joinToString(" ")
                if (!previousEnemies.contains(enemies)) {
                    enemies += " $previousEnemies"
                }
            }
            abilities.additionalAbilities["Избранный враг"] = "Избранные враги:\n${enemies.split(" ").joinToString(", ")}" +
                    "\n" +
                    "Вы совершаете с преимуществом проверки Мудрости (Выживание) для выслеживания избранных врагов, а также проверки Интеллекта для вспоминания информации о них.\n"
            abilities
        },
        Pair("second") { choice: String, abilities: CharacterInfo ->
            var enemies = "$choice"
            abilities.additionalAbilities["Избранный враг"]?.let {
                val previousEnemies = it.split("\n")[1].split(", ").joinToString(" ")
                if (!previousEnemies.contains(enemies)) {
                    enemies += " $previousEnemies"
                }
            }
            abilities.additionalAbilities["Избранный враг"] = "Избранные враги:\n${enemies.split(" ").joinToString(", ")}" +
                    "\n" +
                    "Вы совершаете с преимуществом проверки Мудрости (Выживание) для выслеживания избранных врагов, а также проверки Интеллекта для вспоминания информации о них.\n"
            abilities
        }
    )
)

var favoredEnemy: AbilityNode = AbilityNode(
    name = "Избранный враг",
    changesInCharacterInfo = {abilities: CharacterInfo -> abilities },
    getAlternatives = mutableMapOf(
        Pair("first") { listOf(favoredEnemySingleChoice.name, favoredEnemyDoubleChoice.name) }
    ),
    requirements = {abilities: CharacterInfo ->
        abilities.characterClass == Classes.Ranger
    },
    addRequirements = listOf(),
    description = "У вас есть значительный опыт изучения, отслеживания, охоты и даже общения с определённым видом врагов.\n" +
            "\n" +
            "Выберите вид избранного врага: Аберрации, Великаны, Драконы, Звери, Исчадия, Конструкты, Монстры, Небожители, Нежить, Растения, Слизи, Феи или Элементали. В качестве альтернативы вы можете выбрать в качестве избранных врагов две гуманоидные расы (например, гноллов и орков).\n" +
            "\n" +
            "Вы совершаете с преимуществом проверки Мудрости (Выживание) для выслеживания избранных врагов, а также проверки Интеллекта для вспоминания информации о них.\n" +
            "\n" +
            "Когда вы получаете это умение, вы также обучаетесь одному из языков, на котором говорит ваш избранный враг, если он вообще умеет говорить.\n" +
            "\n" +
            "Вы дополнительно выбираете по одному избранному врагу и языку, связанному с ним, на 6-м и 14-м уровнях. Получая уровни, ваш выбор должен отражать чудовищ, с которыми вы уже сталкивались во время приключений.",
)

var naturalExplorer: AbilityNode = AbilityNode(
    name = "Исследователь природы",
    changesInCharacterInfo = {abilities: CharacterInfo -> abilities },
    getAlternatives = mutableMapOf(
        Pair("first") { listOf("Арктика", "болота", "горы", "леса", "луга", "побережье", "Подземье", "пустыня") }
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "Вы очень хорошо знакомы с одним видом природной среды и имеете большой опыт путешествий и выживания в регионах с таким климатом. Выберите один вид известной местности: Арктика, болота, горы, леса, луга, побережье, Подземье или пустыня. Когда вы совершаете проверку Интеллекта или Мудрости, связанную с известной вам местностью, ваш бонус мастерства удваивается, если вы используете навык, которым владеете.\n" +
            "\n" +
            "Путешествуя по избранной вами местности в течении часа или более, вы получаете следующие преимущества:\n" +
            "\n" +
            "    Труднопроходимая местность не замедляет путешествие группы.\n" +
            "    Ваша группа не может заблудиться, кроме как по вине магии.\n" +
            "    Даже когда вы занимаетесь другой деятельностью во время путешествия (например, ищете пищу, ориентируетесь или занимаетесь выслеживанием), вы остаётесь готовы к опасности.\n" +
            "    Если вы путешествуете в одиночку, вы можете перемещаться скрытно в нормальном темпе.\n" +
            "    Когда вы ищете пищу, то находите в два раза больше, чем обычно.\n" +
            "    Когда вы выслеживаете других существ, вы также узнаёте их точное количество, их размеры, и как давно они прошли через область. \n" +
            "\n" +
            "Вы можете выбрать дополнительную известную местность на 6-м и 10-м уровнях.",
    actionForChoice = mutableMapOf(
        Pair("first") {choice: String, abilities: CharacterInfo ->
            var enviroments = "$choice"
            abilities.additionalAbilities["Исследователь природы"]?.let {
                val otherEnviroments = it.split("\n")[1].split(", ").joinToString(" ")
                if (!otherEnviroments.contains(enviroments)) {
                    enviroments += " $otherEnviroments"
                }
            }
            abilities.additionalAbilities["Исследователь природы"] = "Избранные местности:\n${enviroments.split(" ").joinToString(", ")}" +
                    "\n" +
                    "Путешествуя по избранной вами местности в течении часа или более, вы получаете следующие преимущества:\n" +
                    "\n" +
                    "    Труднопроходимая местность не замедляет путешествие группы.\n" +
                    "    Ваша группа не может заблудиться, кроме как по вине магии.\n" +
                    "    Даже когда вы занимаетесь другой деятельностью во время путешествия (например, ищете пищу, ориентируетесь или занимаетесь выслеживанием), вы остаётесь готовы к опасности.\n" +
                    "    Если вы путешествуете в одиночку, вы можете перемещаться скрытно в нормальном темпе.\n" +
                    "    Когда вы ищете пищу, то находите в два раза больше, чем обычно.\n" +
                    "    Когда вы выслеживаете других существ, вы также узнаёте их точное количество, их размеры, и как давно они прошли через область. \n"
            abilities
        }
    )
)

var ranger1: AbilityNodeLevel = AbilityNodeLevel(
    name = "Следопыт_1",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.characterClass = Classes.Ranger
        abilities.level += 1
        abilities.proficiencyBonus += 2
        abilities.hp += abilityToModifier(abilities.constitution) + 10
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first") { listOf(classFeaturesRanger.name) },
        Pair("second") { listOf(favoredEnemy.name) },
        Pair("third") { listOf(naturalExplorer.name) }
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "1-й уровень, способности следопыта",
    next_level = "Следопыт_2",
)

var spellCastingRanger: AbilityNode = AbilityNode(
    name = "Следопыт: использование заклинаний",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.spellsInfo.apply {
            if (!this.contains("Заклинания класса")) {
                this["Заклинания класса"] = CharacterSpells()
            }
            this["Заклинания класса"]?.className = Classes.Ranger.className
            this["Заклинания класса"]?.maxKnownSpellsCount = 2
            this["Заклинания класса"]?.maxKnownCantripsCount = 0
        }
        abilities.spellCasterLevel = 1f
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.characterClass == Classes.Wizard
    },
    description = "Вы обучаетесь использованию волшебной сущности природы для накладывания заклинаний подобно друиду.\n" +
            "Ячейки заклинаний\n" +
            "\n" +
            "Таблица «Следопыт» показывает, какое количество ячеек заклинаний для сотворения заклинаний следопыта у вас есть на втором и более высоких уровнях. Для накладывания одного из этих заклинаний вы должны потратить ячейку заклинаний того же уровня или выше, что и само заклинание. Вы восстановите все потраченные ячейки, когда завершите продолжительный отдых.\n" +
            "\n" +
            "Например, если вы знаете заклинание 1-го уровня дружба с животными [animal friendship], и у вас есть ячейки 1-го и 2-го уровней, вы можете наложить заклинание дружба с животными [animal friendship] с помощью любой из этих ячеек.\n" +
            "Известные заклинания первого и более высоких уровней\n" +
            "\n" +
            "Вы знаете два заклинания 1-го уровня на свой выбор из списка доступных следопыту.\n" +
            "\n" +
            "Колонка «известные заклинания» показывает, когда вы сможете выучить новые заклинания. Уровень заклинаний не должен превышать уровень самой высокой имеющейся у вас ячейки заклинаний. Например, когда вы достигнете 5-го уровня в этом классе, вы можете выучить одно новое заклинание 1-го или 2-го уровня.\n" +
            "\n" +
            "Кроме того, когда вы получаете новый уровень в этом классе, вы можете одно из известных вам заклинаний следопыта заменить на другое из списка следопыта, уровень которого тоже должен соответствовать имеющимся ячейкам заклинаний.\n" +
            "Базовая характеристика заклинаний\n" +
            "\n" +
            "При создании заклинаний следопыт использует Мудрость, так как магия отображает вашу взаимосвязь с природой. Вы используете Мудрость в случаях, когда заклинание ссылается на базовую характеристику. Кроме того, вы используете Мудрость при определении Сл спасбросков от ваших заклинаний, и при броске атаки заклинаниями.\n" +
            "\n" +
            "Сл спасброска = 8 + ваш бонус мастерства + ваш модификатор Мудрости\n" +
            "\n" +
            "Модификатор броска атаки = ваш бонус мастерства + ваш модификатор Мудрости",
    priority = Priority.DoFirst
)

var ranger2: AbilityNodeLevel = AbilityNodeLevel(
    name = "Следопыт_2",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first") { listOf(fightingStyleRanger.name) },
        Pair("second") { listOf(spellCastingRanger.name) }
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "2-й уровень, способности следопыта",
    next_level = "Следопыт_3",
)

var rangerConclave: AbilityNode = AbilityNode(
    name = "Архетип следопыта",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("archetype") { listOf() }
    ),
    requirements = { abilities: CharacterInfo ->
        abilities.characterClass == Classes.Ranger
    },
    description = "Вы выбираете архетип, к которому вы стремитесь. Подробности этих архетипов приведены в конце описания класса. Выбранный вами архетип предоставляет умения на 3-м, 7-м, 11-м и 15-м уровнях."
)

var primevalAwareness: AbilityNode = AbilityNode(
    name = "Первозданная осведомленность",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Первозданная осведомленность",
                description = "Вы можете действием потратить одну ячейку заклинаний следопыта, чтобы сосредоточиться на познании пространства вокруг себя. В течение 1 минуты за каждый уровень использованной ячейки заклинаний вы можете ощутить присутствие следующих видов существ в пределах 1 мили (или в пределах 6 миль, если вы находитесь в избранной местности): Аберрации, Драконы, Исчадия, Небожители, Нежить, Феи и Элементали. Это умение не раскрывает местоположение и количество существ.\n",
                type = ActionType.Action
            )
        )
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    description = "Вы можете действием потратить одну ячейку заклинаний следопыта, чтобы сосредоточиться на познании пространства вокруг себя. В течение 1 минуты за каждый уровень использованной ячейки заклинаний вы можете ощутить присутствие следующих видов существ в пределах 1 мили (или в пределах 6 миль, если вы находитесь в избранной местности): Аберрации, Драконы, Исчадия, Небожители, Нежить, Феи и Элементали. Это умение не раскрывает местоположение и количество существ.\n"
)

var ranger3: AbilityNodeLevel = AbilityNodeLevel(
    name = "Следопыт_3",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 0.5f
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities.spellsInfo["Заклинания класса"]?.let {
            it.maxKnownSpellsCount += 1
        }
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first") { listOf(rangerConclave.name) },
        Pair("second") { listOf(primevalAwareness.name) }
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "3-й уровень, способности следопыта",
    next_level = "Следопыт_4",
)

var ranger4: AbilityNodeLevel = AbilityNodeLevel(
    name = "Следопыт_4",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 0.5f
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first") { listOf(abilityScoreImprovement.name) },
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "4-й уровень, способности следопыта",
    next_level = "Следопыт_5",
)

var ranger5: AbilityNodeLevel = AbilityNodeLevel(
    name = "Следопыт_5",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 0.5f
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities.proficiencyBonus += 1
        abilities.spellsInfo["Заклинания класса"]?.let {
            it.maxKnownSpellsCount += 1
        }
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first") { listOf(extraAttack.name) },
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "5-й уровень, способности следопыта",
    next_level = "Следопыт_6",
)

var ranger6: AbilityNodeLevel = AbilityNodeLevel(
    name = "Следопыт_6",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 0.5f
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    getAlternatives = mutableMapOf(
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "6-й уровень, способности следопыта",
    next_level = "Следопыт_7",
)

var ranger7: AbilityNodeLevel = AbilityNodeLevel(
    name = "Следопыт_7",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 0.5f
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities.spellsInfo["Заклинания класса"]?.let {
            it.maxKnownSpellsCount += 1
        }
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "7-й уровень, способности следопыта",
    next_level = "Следопыт_8",
)

var landsStride: AbilityNode = AbilityNode(
    name = "Тропами земли",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.additionalAbilities["Тропами земли"] = "Перемещение по немагической труднопроходимой местности не стоит вам никакого дополнительного перемещения. Вы также можете проходить через немагические растения без замедления, и не получая от них урона, даже если у них есть шипы, колючки или аналогичная опасность.\n" +
                "\n" +
                "Кроме того, вы совершаете с преимуществом спасброски от растений, которые были магическим образом созданы или управляются магией, препятствуя движению, например, созданных заклинанием опутывание [entangle]."
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = {true},
    addRequirements = listOf(),
    description = "Перемещение по немагической труднопроходимой местности не стоит вам никакого дополнительного перемещения. Вы также можете проходить через немагические растения без замедления, и не получая от них урона, даже если у них есть шипы, колючки или аналогичная опасность.\n" +
            "\n" +
            "Кроме того, вы совершаете с преимуществом спасброски от растений, которые были магическим образом созданы или управляются магией, препятствуя движению, например, созданных заклинанием опутывание [entangle]."
)

var ranger8: AbilityNodeLevel = AbilityNodeLevel(
    name = "Следопыт_8",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 0.5f
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first") { listOf(abilityScoreImprovement.name) },
        Pair("second") { listOf(landsStride.name) }
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "8-й уровень, способности следопыта",
    next_level = "Следопыт_9",
)

var hideInPlainSight: AbilityNode = AbilityNode(
    name = "Маскировка на виду",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Маскировка на виду",
                description = "Вы можете потратить 1 минуту для создания персонального камуфляжа. У вас должен быть доступ к свежим илу, грязи, растениям, саже и другим природным материалам, с помощью которых будет создаваться камуфляж. После того как вы замаскировались подобным образом, вы можете попытаться спрятаться, прижавшись к твёрдой поверхности, такой как дерево или стена, не уступающей вам по высоте и ширине. Вы получаете бонус +10 к проверкам Ловкости (Скрытность), пока находитесь без движения и не предпринимаете действий.\n" +
                    "\n" +
                    "После того как вы переместились, совершили действие или реакцию, вы должны снова маскироваться с самого начала, чтобы получить это преимущество.",
                type = ActionType.Long
            )
        )
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "Вы можете потратить 1 минуту для создания персонального камуфляжа. У вас должен быть доступ к свежим илу, грязи, растениям, саже и другим природным материалам, с помощью которых будет создаваться камуфляж. После того как вы замаскировались подобным образом, вы можете попытаться спрятаться, прижавшись к твёрдой поверхности, такой как дерево или стена, не уступающей вам по высоте и ширине. Вы получаете бонус +10 к проверкам Ловкости (Скрытность), пока находитесь без движения и не предпринимаете действий.\n" +
            "\n" +
            "После того как вы переместились, совершили действие или реакцию, вы должны снова маскироваться с самого начала, чтобы получить это преимущество."
)

var ranger9: AbilityNodeLevel = AbilityNodeLevel(
    name = "Следопыт_9",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 0.5f
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities.proficiencyBonus += 1
        abilities.spellsInfo["Заклинания класса"]?.let {
            it.maxKnownSpellsCount += 1
        }
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "9-й уровень, способности следопыта",
    next_level = "Следопыт_10",
)

var ranger10: AbilityNodeLevel = AbilityNodeLevel(
    name = "Следопыт_10",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 0.5f
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first") { listOf(hideInPlainSight.name) }
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "10-й уровень, способности следопыта",
    next_level = "Следопыт_11",
)

var ranger11: AbilityNodeLevel = AbilityNodeLevel(
    name = "Следопыт_11",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 0.5f
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities.spellsInfo["Заклинания класса"]?.let {
            it.maxKnownSpellsCount += 1
        }
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "11-й уровень, способности следопыта",
    next_level = "Следопыт_12",
)

var ranger12: AbilityNodeLevel = AbilityNodeLevel(
    name = "Следопыт_12",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 0.5f
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first") { listOf(abilityScoreImprovement.name) },
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "12-й уровень, способности следопыта",
    next_level = "Следопыт_13",
)

var ranger13: AbilityNodeLevel = AbilityNodeLevel(
    name = "Следопыт_13",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 0.5f
        abilities.proficiencyBonus += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities.spellsInfo["Заклинания класса"]?.let {
            it.maxKnownSpellsCount += 1
        }
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "13-й уровень, способности следопыта",
    next_level = "Следопыт_14",
)

var vanish: AbilityNode = AbilityNode(
    name = "Исчезновение",
    changesInCharacterInfo = {abilities: CharacterInfo ->
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
    requirements = {true},
    addRequirements = listOf(),
    description = "Вы можете в свой ход совершать действие Засада бонусным действием. Кроме того, вы не можете быть выслежены немагическими способом, если вы не оставите след намеренно."
)

var ranger14: AbilityNodeLevel = AbilityNodeLevel(
    name = "Следопыт_14",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 0.5f
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(vanish.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "14-й уровень, способности следопыта",
    next_level = "Следопыт_15",
)

var ranger15: AbilityNodeLevel = AbilityNodeLevel(
    name = "Следопыт_15",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 0.5f
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities.spellsInfo["Заклинания класса"]?.let {
            it.maxKnownSpellsCount += 1
        }
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "15-й уровень, способности следопыта",
    next_level = "Следопыт_16",
)

var ranger16: AbilityNodeLevel = AbilityNodeLevel(
    name = "Следопыт_16",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 0.5f
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first") { listOf(abilityScoreImprovement.name) },
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "16-й уровень, способности следопыта",
    next_level = "Следопыт_17",
)

var ranger17: AbilityNodeLevel = AbilityNodeLevel(
    name = "Следопыт_17",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 0.5f
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities.spellsInfo["Заклинания класса"]?.let {
            it.maxKnownSpellsCount += 1
        }
        abilities.proficiencyBonus += 1
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "17-й уровень, способности следопыта",
    next_level = "Следопыт_18",
)

var feralSenses: AbilityNode = AbilityNode(
    name = "Дикие чувства",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.additionalAbilities["Дикие чувства"] = "Вы получаете сверхъестественные чувства, которые помогут вам сражаться с существами, которых вы не можете увидеть. Когда вы атакуете существо, которое не видите, ваша неспособность видеть не накладывает помеху броскам атаки по нему.\n" +
                "\n" +
                "Вам также известно о местонахождении всех невидимых существ в пределах 30 футов от вас, при условии, что они не скрыты от вас, и вы не ослеплены и не оглушены."
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = {true},
    addRequirements = listOf(),
    description = "Вы получаете сверхъестественные чувства, которые помогут вам сражаться с существами, которых вы не можете увидеть. Когда вы атакуете существо, которое не видите, ваша неспособность видеть не накладывает помеху броскам атаки по нему.\n" +
            "\n" +
            "Вам также известно о местонахождении всех невидимых существ в пределах 30 футов от вас, при условии, что они не скрыты от вас, и вы не ослеплены и не оглушены."
)

var ranger18: AbilityNodeLevel = AbilityNodeLevel(
    name = "Следопыт_18",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 0.5f
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first") { listOf(feralSenses.name) }
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "18-й уровень, способности следопыта",
    next_level = "Следопыт_19",
)

var ranger19: AbilityNodeLevel = AbilityNodeLevel(
    name = "Следопыт_19",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.spellCasterLevel += 0.5f
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities.spellsInfo["Заклинания класса"]?.let {
            it.maxKnownSpellsCount += 1
        }
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first") { listOf(abilityScoreImprovement.name) },
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "19-й уровень, способности следопыта",
    next_level = "Следопыт_20",
)

var foeSlayer: AbilityNode = AbilityNode(
    name = "Убийца врагов",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Убийца врагов",
                description = "Вы становитесь беспрецедентным охотником на своих врагов. Один раз в каждом своём ходу вы можете добавить модификатор Мудрости к броску атаки или урона по существу из списка ваших избранных врагов. Вы можете использовать это умение до или после броска, но до того, как эффекты броска вступят в силу.",
                type = ActionType.PartOfAction
            )
        )
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = {true},
    description = "Вы становитесь беспрецедентным охотником на своих врагов. Один раз в каждом своём ходу вы можете добавить модификатор Мудрости к броску атаки или урона по существу из списка ваших избранных врагов. Вы можете использовать это умение до или после броска, но до того, как эффекты броска вступят в силу."
)

var ranger20: AbilityNodeLevel = AbilityNodeLevel(
    name = "Следопыт_20",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 6
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first") { listOf(foeSlayer.name) }
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "20-й уровень, способности следопыта",
    next_level = null,
)

var mapOfRangerAbilities = mutableMapOf(
    Pair(classFeaturesRanger.name, classFeaturesRanger),
    Pair(fightingStyleRanger.name, fightingStyleRanger),
    Pair(favoredEnemy.name, favoredEnemy),
    Pair(favoredEnemySingleChoice.name, favoredEnemySingleChoice),
    Pair(favoredEnemyDoubleChoice.name, favoredEnemyDoubleChoice),
    Pair(naturalExplorer.name, naturalExplorer),
    Pair(ranger1.name, ranger1),
    Pair(spellCastingRanger.name, spellCastingRanger),
    Pair(ranger2.name, ranger2),
    Pair(rangerConclave.name, rangerConclave),
    Pair(primevalAwareness.name, primevalAwareness),
    Pair(ranger3.name, ranger3),
    Pair(ranger4.name, ranger4),
    Pair(ranger5.name, ranger5),
    Pair(ranger6.name, ranger6),
    Pair(ranger7.name, ranger7),
    Pair(landsStride.name, landsStride),
    Pair(ranger8.name, ranger8),
    Pair(hideInPlainSight.name, hideInPlainSight),
    Pair(ranger9.name, ranger9),
    Pair(ranger10.name, ranger10),
    Pair(ranger11.name, ranger11),
    Pair(ranger12.name, ranger12),
    Pair(ranger13.name, ranger13),
    Pair(vanish.name, vanish),
    Pair(ranger14.name, ranger14),
    Pair(ranger15.name, ranger15),
    Pair(ranger16.name, ranger16),
    Pair(ranger17.name, ranger17),
    Pair(ranger18.name, ranger18),
    Pair(ranger19.name, ranger19),
    Pair(ranger20.name, ranger20),
    Pair(feralSenses.name, feralSenses),
    Pair(foeSlayer.name, foeSlayer)
)
