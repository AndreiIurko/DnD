package com.andreyyurko.dnd.data.abilities.classes.monk

import com.andreyyurko.dnd.data.characterData.Action
import com.andreyyurko.dnd.data.characterData.ActionType
import com.andreyyurko.dnd.data.characterData.CharacterInfo
import com.andreyyurko.dnd.data.characterData.Classes
import com.andreyyurko.dnd.data.characterData.character.AbilityNode

var openHandTechnique = AbilityNode(
    name = "Техники открытой ладони",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Техники открытой ладони",
                description = "Вы получаете возможность манипулировать вражеской энергией ци, направляя свою собственную. Если вы попадаете по существу одной из атак, дарованных «Шквалом ударов», вы можете наложить на цель один из следующих эффектов:\n" +
                        "\n" +
                        "    Цель должна преуспеть в спасброске Ловкости, иначе она сбивается с ног.\n" +
                        "    Цель должна совершить спасбросок Силы. В случае провала вы можете толкнуть её от себя на расстояние до 15 футов.\n" +
                        "    Цель не может совершать реакции до конца вашего следующего хода.\n",
                type = ActionType.PartOfAction
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 3 && abilities.characterClass == Classes.Monk
    },
    description = "Вы получаете возможность манипулировать вражеской энергией ци, направляя свою собственную. Если вы попадаете по существу одной из атак, дарованных «Шквалом ударов», вы можете наложить на цель один из следующих эффектов:\n" +
            "\n" +
            "    Цель должна преуспеть в спасброске Ловкости, иначе она сбивается с ног.\n" +
            "    Цель должна совершить спасбросок Силы. В случае провала вы можете толкнуть её от себя на расстояние до 15 футов.\n" +
            "    Цель не может совершать реакции до конца вашего следующего хода.\n",
)

var wholenessOfBody = AbilityNode(
    name = "Исцеление тела",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Исцеление тела",
                description = "Вы получаете способность лечить себя. Вы можете действием восстановить количество хитов, равное вашему утроенному уровню монаха. Вы должны завершить продолжительный отдых, прежде чем сможете использовать это умение снова.\n",
                type = ActionType.Action
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 6 && abilities.characterClass == Classes.Monk
    },
    description = "Вы получаете способность лечить себя. Вы можете действием восстановить количество хитов, равное вашему утроенному уровню монаха. Вы должны завершить продолжительный отдых, прежде чем сможете использовать это умение снова.\n",
)

var tranquility = AbilityNode(
    name = "Умиротворение",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Умиротворение",
                description = "Вы можете совершить специальную медитацию, окружающую вас аурой мира. В конце продолжительного отдыха вы получаете эффект заклинания убежище [sanctuary], длящийся до начала вашего следующего продолжительного отдыха (эффект может окончиться ранее, согласно описанию заклинания). Сл спасброска от заклинания равна 8 + ваш модификатор Мудрости + ваш бонус мастерства.\n",
                type = ActionType.Long
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 11 && abilities.characterClass == Classes.Monk
    },
    description = "Вы можете совершить специальную медитацию, окружающую вас аурой мира. В конце продолжительного отдыха вы получаете эффект заклинания убежище [sanctuary], длящийся до начала вашего следующего продолжительного отдыха (эффект может окончиться ранее, согласно описанию заклинания). Сл спасброска от заклинания равна 8 + ваш модификатор Мудрости + ваш бонус мастерства.\n",
)

var quiveringPalm = AbilityNode(
    name = "Дрожащая ладонь",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Дрожащая ладонь",
                description = "Вы получаете возможность создавать смертельные колебания в чужом теле. Если вы попадёте по существу безоружным ударом, вы можете потратить 3 очка ци, чтобы создать неуловимые колебания в его теле, которые длятся в течение количества суток, равного вашему уровню монаха. Колебания безвредны, пока вы не остановите их действием. Для того чтобы остановить их, цель должна находится на одном плане существования с вами. При этом существо должно совершить спасбросок Телосложения. В случае успеха цель получает урон некротической энергией 10к10, а в случае провала её хиты опускаются до 0. Вы можете держать одновременно только одно существо под действием «Дрожащей ладони». Вы можете решить закончить колебания без вреда, не тратя действие.\n",
                type = ActionType.PartOfAction,
                relatedCharges = "Ци"
            )
        )
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 17 && abilities.characterClass == Classes.Monk
    },
    description = "Вы получаете возможность создавать смертельные колебания в чужом теле. Если вы попадёте по существу безоружным ударом, вы можете потратить 3 очка ци, чтобы создать неуловимые колебания в его теле, которые длятся в течение количества суток, равного вашему уровню монаха. Колебания безвредны, пока вы не остановите их действием. Для того чтобы остановить их, цель должна находится на одном плане существования с вами. При этом существо должно совершить спасбросок Телосложения. В случае успеха цель получает урон некротической энергией 10к10, а в случае провала её хиты опускаются до 0. Вы можете держать одновременно только одно существо под действием «Дрожащей ладони». Вы можете решить закончить колебания без вреда, не тратя действие.\n",
)

var wayOfOpenHand = AbilityNode(
    name = "Путь открытой ладони",
    changesInCharacterInfo = { abilities: CharacterInfo -> abilities },
    alternatives = mutableMapOf(
        Pair("first", listOf(openHandTechnique.name)),
        Pair("second", listOf(wholenessOfBody.name)),
        Pair("third", listOf(tranquility.name)),
        Pair("fourth", listOf(quiveringPalm.name))
    ),
    requirements = { true },
    description = "",
    isNeedsToBeShown = false
)

var mapOfWayOfOpenHandAbilities: MutableMap<String, AbilityNode> = mutableMapOf(
    Pair(openHandTechnique.name, openHandTechnique),
    Pair(wholenessOfBody.name, wholenessOfBody),
    Pair(tranquility.name, tranquility),
    Pair(quiveringPalm.name, quiveringPalm),
    Pair(wayOfOpenHand.name, wayOfOpenHand),
)