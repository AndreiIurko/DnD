package com.andreyyurko.dnd.data.abilities.classes.monk

import com.andreyyurko.dnd.data.characterData.*
import com.andreyyurko.dnd.data.characterData.character.AbilityNode

var elementalAttunement = AbilityNode(
    name = "Родство со стихией",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Родство со стихией",
                description = "Вы можете действием на некоторое время взять под контроль расположенные в пределах 30 футов от вас стихийные силы. Это позволит выбрать один из следующих эффектов:\n" +
                        "\n" +
                        "    Создаётся безвредный мгновенный эффект, связанный с водой, воздухом, землёй или огнём, такой как сноп искр, дуновение ветра, поток лёгкого тумана или тихое гудение камня.\n" +
                        "    Мгновенно загорается или тушится свеча, факел или небольшой костёр.\n" +
                        "    Остужается или нагревается до 1 фунта неживой материи на время до 1 часа.\n" +
                        "    Вода, воздух, земля или огонь, способные втиснуться в куб с длиной ребра 1 фут, принимают на 1 минуту грубую форму, выбранную вами.\n",
                type = ActionType.Additional
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 3 && abilities.characterClass == Classes.Monk
    },
    description = "Вы можете действием на некоторое время взять под контроль расположенные в пределах 30 футов от вас стихийные силы. Это позволит выбрать один из следующих эффектов:\n" +
            "\n" +
            "    Создаётся безвредный мгновенный эффект, связанный с водой, воздухом, землёй или огнём, такой как сноп искр, дуновение ветра, поток лёгкого тумана или тихое гудение камня.\n" +
            "    Мгновенно загорается или тушится свеча, факел или небольшой костёр.\n" +
            "    Остужается или нагревается до 1 фунта неживой материи на время до 1 часа.\n" +
            "    Вода, воздух, земля или огонь, способные втиснуться в куб с длиной ребра 1 фут, принимают на 1 минуту грубую форму, выбранную вами.\n",
)

var fangsOfTheFireSnake = AbilityNode(
    name = "Зубы огненной змеи",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Зубы огненной змеи",
                description = "Когда вы в свой ход используете действие Атака, вы можете потратить 1 очко ци, и во время атаки из ваших рук и ног будут вырываться языки пламени. Досягаемость безоружных ударов для этой и последующих атак текущего хода увеличивается на 10 футов. Попадание таких атак причиняет урон огнём вместо дробящего урона, и вы можете при попадании потратить еще 1 очко ци, чтобы увеличить урон конкретной атаки на урон огнём 1к10.\n",
                type = ActionType.Additional,
                relatedCharges = "Ци"
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 3 && abilities.characterClass == Classes.Monk
    },
    description = "Когда вы в свой ход используете действие Атака, вы можете потратить 1 очко ци, и во время атаки из ваших рук и ног будут вырываться языки пламени. Досягаемость безоружных ударов для этой и последующих атак текущего хода увеличивается на 10 футов. Попадание таких атак причиняет урон огнём вместо дробящего урона, и вы можете при попадании потратить еще 1 очко ци, чтобы увеличить урон конкретной атаки на урон огнём 1к10.\n",
)

var fistOfTheFourThunders = AbilityNode(
    name = "Кулак четырёх громов",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Кулак четырёх громов",
                description = "Вы можете потратить 2 очка ци, чтобы наложить заклинание волна грома [thunderwave].\n",
                type = ActionType.Action,
                relatedCharges = "Ци"
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 3 && abilities.characterClass == Classes.Monk
    },
    description = "Вы можете потратить 2 очка ци, чтобы наложить заклинание волна грома [thunderwave].\n",
)

var fistOfUnbrokenAir = AbilityNode(
    name = "Несокрушимый воздушный кулак",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Несокрушимый воздушный кулак",
                description = "Вы создаёте область сжатого воздуха, бьющую как могучий кулак. Вы действием тратите 2 очка ци и выбираете существо в пределах 30 футов от себя. Это существо должно совершить спасбросок Силы. В случае провала существо получает дробящий урон 3к10, плюс дополнительно 1к10 дробящего урона за каждое дополнительно потраченное очко ци, и вы можете отбросить существо на 20 футов от себя и сбить его с ног. В случае успешного спасброска существо получает половину урона, не отбрасывается, не сбивается с ног.\n",
                type = ActionType.Action,
                relatedCharges = "Ци"
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 3 && abilities.characterClass == Classes.Monk
    },
    description = "Вы создаёте область сжатого воздуха, бьющую как могучий кулак. Вы действием тратите 2 очка ци и выбираете существо в пределах 30 футов от себя. Это существо должно совершить спасбросок Силы. В случае провала существо получает дробящий урон 3к10, плюс дополнительно 1к10 дробящего урона за каждое дополнительно потраченное очко ци, и вы можете отбросить существо на 20 футов от себя и сбить его с ног. В случае успешного спасброска существо получает половину урона, не отбрасывается, не сбивается с ног.\n",
)

var rushOfTheGaleSpirits = AbilityNode(
    name = "Натиск штормовых духов",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Натиск штормовых духов",
                description = "Вы можете потратить 2 очка ци, чтобы наложить заклинание порыв ветра [gust of wind].\n",
                type = ActionType.Action,
                relatedCharges = "Ци"
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 3 && abilities.characterClass == Classes.Monk
    },
    description = "Вы можете потратить 2 очка ци, чтобы наложить заклинание порыв ветра [gust of wind].\n",
)

var shapeTheFlowingRiver = AbilityNode(
    name = "Формирование текущей реки",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Формирование текущей реки",
                description = "Вы можете действием потратить 1 очко ци, чтобы выбрать область льда или воды в пространстве с длиной стороны не больше 30 футов, в пределах 120 футов от себя. Вы можете превратить в этой области воду в лёд и наоборот, и вы можете придавать льду в этой области любую форму. Вы можете повысить или понизить уровень льда, создать или заполнить углубление, возвести или сравнять стену, или создать ледяной столб. Размер любого изменения не может превышать половину наибольшей стороны области. Например, если вы изменяете пространство с длиной стороны в 30 футов, можно создать столб до 15 футов в высоту, поднять или опустить высоту области до 15 футов, вырыть углубление до 15 футов, и так далее. Вы не можете замуровать существо в лёд или причинить этим воздействием другому существу урон.\n",
                type = ActionType.Action,
                relatedCharges = "Ци"
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 3 && abilities.characterClass == Classes.Monk
    },
    description = "Вы можете действием потратить 1 очко ци, чтобы выбрать область льда или воды в пространстве с длиной стороны не больше 30 футов, в пределах 120 футов от себя. Вы можете превратить в этой области воду в лёд и наоборот, и вы можете придавать льду в этой области любую форму. Вы можете повысить или понизить уровень льда, создать или заполнить углубление, возвести или сравнять стену, или создать ледяной столб. Размер любого изменения не может превышать половину наибольшей стороны области. Например, если вы изменяете пространство с длиной стороны в 30 футов, можно создать столб до 15 футов в высоту, поднять или опустить высоту области до 15 футов, вырыть углубление до 15 футов, и так далее. Вы не можете замуровать существо в лёд или причинить этим воздействием другому существу урон.\n",
)

var sweepingCinderStrike = AbilityNode(
    name = "Испепеляющий удар",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Испепеляющий удар",
                description = "Вы можете потратить 2 очка ци, чтобы наложить заклинание огненные ладони [burning hands].\n",
                type = ActionType.Action,
                relatedCharges = "Ци"
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 3 && abilities.characterClass == Classes.Monk
    },
    description = "Вы можете потратить 2 очка ци, чтобы наложить заклинание огненные ладони [burning hands].\n",
)

var waterWhip = AbilityNode(
    name = "Водяной кнут",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Водяной кнут",
                description = "Вы можете действием потратить 2 очка ци и создать водяной кнут, сбивающий существ с ног. Существо, которое вы видите, и которое находится в пределах 30 футов от вас, должно совершить спасбросок Ловкости. В случае провала существо получает дробящий урон 3к10, плюс дополнительный дробящий урон 1к10 за каждое дополнительное потраченное очко ци, и вы можете либо сбить его с ног, либо подтянуть его на 25 футов ближе к себе. В случае успешного спасброска существо получает половину урона, не подтягивается и не сбивается с ног.\n",
                type = ActionType.Action,
                relatedCharges = "Ци"
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 3 && abilities.characterClass == Classes.Monk
    },
    description = "Вы можете действием потратить 2 очка ци и создать водяной кнут, сбивающий существ с ног. Существо, которое вы видите, и которое находится в пределах 30 футов от вас, должно совершить спасбросок Ловкости. В случае провала существо получает дробящий урон 3к10, плюс дополнительный дробящий урон 1к10 за каждое дополнительное потраченное очко ци, и вы можете либо сбить его с ног, либо подтянуть его на 25 футов ближе к себе. В случае успешного спасброска существо получает половину урона, не подтягивается и не сбивается с ног.\n",
)

var clenchOfTheNorthWind = AbilityNode(
    name = "Объятья северного ветра",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Объятья северного ветра",
                description = "Вы можете потратить 3 очка ци, чтобы наложить заклинание удержание личности [hold person].\n",
                type = ActionType.Action,
                relatedCharges = "Ци"
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 6 && abilities.characterClass == Classes.Monk
    },
    description = "Вы можете потратить 3 очка ци, чтобы наложить заклинание удержание личности [hold person].\n",
)

var gongOfTheSummit = AbilityNode(
    name = "Гонг на вершине горы",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Гонг на вершине горы",
                description = "Вы можете потратить 3 очка ци, чтобы наложить заклинание дребезги [shatter].\n",
                type = ActionType.Action,
                relatedCharges = "Ци"
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 6 && abilities.characterClass == Classes.Monk
    },
    description = "Вы можете потратить 3 очка ци, чтобы наложить заклинание дребезги [shatter].\n",
)

var flamesOfThePhoenix = AbilityNode(
    name = "Пламя феникса",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Пламя феникса",
                description = "Вы можете потратить 4 очка ци, чтобы наложить заклинание огненный шар [fireball].\n",
                type = ActionType.Action,
                relatedCharges = "Ци"
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 11 && abilities.characterClass == Classes.Monk
    },
    description = "Вы можете потратить 4 очка ци, чтобы наложить заклинание огненный шар [fireball].\n",
)

var mistStance = AbilityNode(
    name = "Туманная стойка",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Туманная стойка",
                description = "Вы можете потратить 4 очка ци, чтобы наложить заклинание газообразная форма [gaseous form], нацеленное на себя.\n",
                type = ActionType.Action,
                relatedCharges = "Ци"
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 11 && abilities.characterClass == Classes.Monk
    },
    description = "Вы можете потратить 4 очка ци, чтобы наложить заклинание газообразная форма [gaseous form], нацеленное на себя.\n",
)

var rideTheWind = AbilityNode(
    name = "Осёдланный ветер",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Осёдланный ветер",
                description = "Вы можете потратить 4 очка ци, чтобы наложить заклинание полёт [fly], нацеленное на себя.\n",
                type = ActionType.Action,
                relatedCharges = "Ци"
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 11 && abilities.characterClass == Classes.Monk
    },
    description = "Вы можете потратить 4 очка ци, чтобы наложить заклинание полёт [fly], нацеленное на себя.\n",
)

var breathOfWinter = AbilityNode(
    name = "Дыхание зимы",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Дыхание зимы",
                description = "Вы можете потратить 6 очков ци, чтобы наложить заклинание конус холода [cone of cold].\n",
                type = ActionType.Action,
                relatedCharges = "Ци"
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 17 && abilities.characterClass == Classes.Monk
    },
    description = "Вы можете потратить 6 очков ци, чтобы наложить заклинание конус холода [cone of cold].\n",
)

var eternalMountainDefense = AbilityNode(
    name = "Прочность вечных гор",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "-Прочность вечных гор",
                description = "Вы можете потратить 5 очков ци, чтобы наложить заклинание каменная кожа [stoneskin], нацеленное на себя.\n",
                type = ActionType.Action,
                relatedCharges = "Ци"
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 17 && abilities.characterClass == Classes.Monk
    },
    description = "Вы можете потратить 5 очков ци, чтобы наложить заклинание каменная кожа [stoneskin], нацеленное на себя.\n",
)

var riverOfHungryFlame = AbilityNode(
    name = "Река голодного пламени",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Река голодного пламени",
                description = "Вы можете потратить 5 очков ци, чтобы наложить заклинание огненная стена [wall of fire].\n",
                type = ActionType.Action,
                relatedCharges = "Ци"
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 17 && abilities.characterClass == Classes.Monk
    },
    description = "Вы можете потратить 5 очков ци, чтобы наложить заклинание огненная стена [wall of fire].\n",
)

var waveOfRollingEarth = AbilityNode(
    name = "Земляной вал",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Земляной вал",
                description = "Вы можете потратить 6 очков ци, чтобы наложить заклинание каменная стена [wall of stone].\n",
                type = ActionType.Action,
                relatedCharges = "Ци"
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 17 && abilities.characterClass == Classes.Monk
    },
    description = "Вы можете потратить 6 очков ци, чтобы наложить заклинание каменная стена [wall of stone].\n",
)

var elementalAdept = AbilityNode(
    name = "Адепт стихий",
    changesInCharacterInfo = { abilities: CharacterInfo -> abilities },
    alternatives = mutableMapOf(
        Pair("first", listOf(elementalAttunement.name)),
        Pair("second", listOf(
            fangsOfTheFireSnake.name,
            fistOfTheFourThunders.name,
            fistOfUnbrokenAir.name,
            rushOfTheGaleSpirits.name,
            shapeTheFlowingRiver.name,
            sweepingCinderStrike.name,
            waterWhip.name,
            clenchOfTheNorthWind.name,
            gongOfTheSummit.name,
            flamesOfThePhoenix.name,
            mistStance.name,
            rideTheWind.name,
            breathOfWinter.name,
            eternalMountainDefense.name,
            riverOfHungryFlame.name,
            waveOfRollingEarth.name
        ))
    ),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 3 && abilities.characterClass == Classes.Monk
    },
    description = "Вы изучаете практики, позволяющие управлять четырьмя стихиями. Эти практики требуют использования очков ци.\n" +
            "\n" +
            "Вы знаете практику «Родство со стихией» и ещё одну по вашему выбору. Вы изучаете одну дополнительную практику на 6-м, 11-м и 17-м уровнях.\n" +
            "\n" +
            "Каждый раз, когда вы изучаете новую практику, вы можете также заменить одну из ранее изученных практик другой, на свой выбор.\n" +
            "\n" +
            "Накладывание стихийных заклинаний. Некоторые стихийные практики позволяют вам накладывать заклинания. При накладывании этих заклинаний вы используете их обычное время накладывания и прочие правила, но вам не нужны материальные компоненты.\n" +
            "\n" +
            "При достижении 5-го уровня в этом классе вы можете тратить очки ци для усиления заклинаний, полученных благодаря стихийным практикам, если эти заклинания увеличивают свою эффективность с уровнем, как, например, огненные ладони [burning hands]. Уровень заклинания увеличивается на 1 за каждое потраченное очко ци. Например, если вы монах 5-го уровня и используете «Испепеляющий удар» для накладывания заклинания огненные ладони [burning hands], вы можете потратить 3 очка ци, чтобы наложить его как заклинание 2-го уровня (базовая стоимость использования этой практики 2 очка ци, плюс еще 1).\n" +
            "\n" +
            "Максимальное количество очков ци, которое может быть потрачено на одно заклинание, определяется вашим уровнем монаха и указано в таблице. Оно включает как базовую стоимость, так и дополнительно потраченные очки ци.\n" +
            "\n" +
            "Уровень монаха | Максимальное количество ци на заклинание\n" +
            "       5-8      | 3\n" +
            "       9-12     | 4\n" +
            "      13-16     | 5\n" +
            "      17-20     | 6\n",
)

var elementalDisciplines6Level = AbilityNode(
    name = "Стихийные техники 6 уровня монаха",
    changesInCharacterInfo = { abilities: CharacterInfo -> abilities },
    alternatives = mutableMapOf(
        Pair("first", listOf(
            fangsOfTheFireSnake.name,
            fistOfTheFourThunders.name,
            fistOfUnbrokenAir.name,
            rushOfTheGaleSpirits.name,
            shapeTheFlowingRiver.name,
            sweepingCinderStrike.name,
            waterWhip.name,
            clenchOfTheNorthWind.name,
            gongOfTheSummit.name,
            flamesOfThePhoenix.name,
            mistStance.name,
            rideTheWind.name,
            breathOfWinter.name,
            eternalMountainDefense.name,
            riverOfHungryFlame.name,
            waveOfRollingEarth.name
        ))
    ),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 6 && abilities.characterClass == Classes.Monk
    },
    description = "",
)

var elementalDisciplines11Level = AbilityNode(
    name = "Стихийные техники 11 уровня монаха",
    changesInCharacterInfo = { abilities: CharacterInfo -> abilities },
    alternatives = mutableMapOf(
        Pair("first", listOf(
            fangsOfTheFireSnake.name,
            fistOfTheFourThunders.name,
            fistOfUnbrokenAir.name,
            rushOfTheGaleSpirits.name,
            shapeTheFlowingRiver.name,
            sweepingCinderStrike.name,
            waterWhip.name,
            clenchOfTheNorthWind.name,
            gongOfTheSummit.name,
            flamesOfThePhoenix.name,
            mistStance.name,
            rideTheWind.name,
            breathOfWinter.name,
            eternalMountainDefense.name,
            riverOfHungryFlame.name,
            waveOfRollingEarth.name
        ))
    ),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 11 && abilities.characterClass == Classes.Monk
    },
    description = "",
)

var elementalDisciplines17Level = AbilityNode(
    name = "Стихийные техники 17 уровня монаха",
    changesInCharacterInfo = { abilities: CharacterInfo -> abilities },
    alternatives = mutableMapOf(
        Pair("first", listOf(
            fangsOfTheFireSnake.name,
            fistOfTheFourThunders.name,
            fistOfUnbrokenAir.name,
            rushOfTheGaleSpirits.name,
            shapeTheFlowingRiver.name,
            sweepingCinderStrike.name,
            waterWhip.name,
            clenchOfTheNorthWind.name,
            gongOfTheSummit.name,
            flamesOfThePhoenix.name,
            mistStance.name,
            rideTheWind.name,
            breathOfWinter.name,
            eternalMountainDefense.name,
            riverOfHungryFlame.name,
            waveOfRollingEarth.name
        ))
    ),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 17 && abilities.characterClass == Classes.Monk
    },
    description = "",
)

var wayOfFourElements = AbilityNode(
    name = "Путь четырёх стихий",
    changesInCharacterInfo = { abilities: CharacterInfo -> abilities },
    alternatives = mutableMapOf(
        Pair("first", listOf(elementalAdept.name)),
        Pair("second", listOf(elementalDisciplines6Level.name)),
        Pair("third", listOf(elementalDisciplines11Level.name)),
        Pair("fourth", listOf(elementalDisciplines17Level.name)),
    ),
    requirements = { true },
    description = "",
    isNeedsToBeShown = false
)

var mapOfWayOfFourElementsAbilities: MutableMap<String, AbilityNode> = mutableMapOf(
    Pair(elementalAttunement.name, elementalAttunement),
    Pair(fangsOfTheFireSnake.name, fangsOfTheFireSnake),
    Pair(fistOfTheFourThunders.name, fistOfTheFourThunders),
    Pair(fistOfUnbrokenAir.name, fistOfUnbrokenAir),
    Pair(rushOfTheGaleSpirits.name, rushOfTheGaleSpirits),
    Pair(shapeTheFlowingRiver.name, shapeTheFlowingRiver),
    Pair(sweepingCinderStrike.name, sweepingCinderStrike),
    Pair(waterWhip.name, waterWhip),
    Pair(clenchOfTheNorthWind.name, clenchOfTheNorthWind),
    Pair(gongOfTheSummit.name, gongOfTheSummit),
    Pair(flamesOfThePhoenix.name, flamesOfThePhoenix),
    Pair(mistStance.name, mistStance),
    Pair(rideTheWind.name, rideTheWind),
    Pair(breathOfWinter.name, breathOfWinter),
    Pair(eternalMountainDefense.name, eternalMountainDefense),
    Pair(riverOfHungryFlame.name, riverOfHungryFlame),
    Pair(waveOfRollingEarth.name, waveOfRollingEarth),
    Pair(elementalAdept.name, elementalAdept),
    Pair(elementalDisciplines6Level.name, elementalDisciplines6Level),
    Pair(elementalDisciplines11Level.name, elementalDisciplines11Level),
    Pair(elementalDisciplines17Level.name, elementalDisciplines17Level),
    Pair(wayOfFourElements.name, wayOfFourElements),
)