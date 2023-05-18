package com.andreyyurko.dnd.data.abilities.classes.wizard

import com.andreyyurko.dnd.data.characterData.Action
import com.andreyyurko.dnd.data.characterData.ActionType
import com.andreyyurko.dnd.data.characterData.CharacterInfo
import com.andreyyurko.dnd.data.characterData.Classes
import com.andreyyurko.dnd.data.characterData.character.AbilityNode

var conjurationSavant = AbilityNode(
    name = "Мастер вызова",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Мастер вызова"] =
            "Золото и время, которое вы тратите на копирование заклинания школы Вызова в свою книгу заклинаний, уменьшаются вдвое.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 2 && abilities.characterClass == Classes.Wizard
    },
    description = "Золото и время, которое вы тратите на копирование заклинания школы Вызова в свою книгу заклинаний, уменьшаются вдвое.\n",
)

var minorConjuration = AbilityNode(
    name = "Малый вызов",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsMap["Малый вызов"] =
            Action(
                name = "Малый вызов",
                description = "Вы можете действием создать неодушевлённый предмет в своей руке или на земле в свободном пространстве, которое вы можете видеть, и которое расположено в пределах 10 футов от вас. Этот предмет не должен превышать в длину 3 фута (90 сантиметров) и весить более 10 фунтов, и его форма должна быть как у немагического предмета, который вы уже видели. Видно, что предмет магический, и он излучает тусклый свет в пределах 5 футов.\n" +
                        "\n" +
                        "Предмет исчезает через 1 час, когда вы используете это умение вновь, или когда он получает или наносит любой урон.\n",
                type = ActionType.Action
            )

        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 2 && abilities.characterClass == Classes.Wizard
    },
    description = "Вы можете действием создать неодушевлённый предмет в своей руке или на земле в свободном пространстве, которое вы можете видеть, и которое расположено в пределах 10 футов от вас. Этот предмет не должен превышать в длину 3 фута (90 сантиметров) и весить более 10 фунтов, и его форма должна быть как у немагического предмета, который вы уже видели. Видно, что предмет магический, и он излучает тусклый свет в пределах 5 футов.\n" +
            "\n" +
            "Предмет исчезает через 1 час, когда вы используете это умение вновь, или когда он получает или наносит любой урон.\n",
)

var benignTransposition = AbilityNode(
    name = "Безопасное перемещение",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsMap["Безопасное перемещение"] =
            Action(
                name = "Безопасное перемещение",
                description = "Вы можете действием телепортироваться на 30 футов в свободное пространство, которое можете видеть. В качестве альтернативы, вы можете выбрать пространство в пределах этого диапазона, занятое существом Маленького или Среднего размера. Если это существо согласно, вы оба телепортируетесь, меняясь местами. Использовав это умение один раз, вы не можете использовать его вновь до окончания продолжительного отдыха или пока не наложите заклинание школы Вызова 1-го уровня или выше.\n",
                type = ActionType.Action
            )

        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 6 && abilities.characterClass == Classes.Wizard
    },
    description = "Вы можете действием телепортироваться на 30 футов в свободное пространство, которое можете видеть. В качестве альтернативы, вы можете выбрать пространство в пределах этого диапазона, занятое существом Маленького или Среднего размера. Если это существо согласно, вы оба телепортируетесь, меняясь местами. Использовав это умение один раз, вы не можете использовать его вновь до окончания продолжительного отдыха или пока не наложите заклинание школы Вызова 1-го уровня или выше.\n",
)

var focusedConjuration = AbilityNode(
    name = "Фокусированный вызов",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Фокусированный вызов"] =
            "Когда вы концентрируетесь на заклинании школы Вызова, ваша концентрация не может быть нарушена в результате получения урона.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 10 && abilities.characterClass == Classes.Wizard
    },
    description = "Когда вы концентрируетесь на заклинании школы Вызова, ваша концентрация не может быть нарушена в результате получения урона.\n",
)

var durableSummons = AbilityNode(
    name = "Надёжный призыв",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Надёжный призыв"] =
            "Все призванные или созданные вами заклинанием школы Вызова существа имеют 30 временных хитов.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 14 && abilities.characterClass == Classes.Wizard
    },
    description = "Все призванные или созданные вами заклинанием школы Вызова существа имеют 30 временных хитов.\n",
)

var schoolOfConjuration = AbilityNode(
    name = "Школа вызова",
    changesInCharacterInfo = { abilities: CharacterInfo -> abilities },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(conjurationSavant.name) }),
        Pair("second", { listOf(minorConjuration.name) }),
        Pair("third", { listOf(benignTransposition.name) }),
        Pair("fourth", { listOf(focusedConjuration.name) }),
        Pair("fifth", { listOf(durableSummons.name) })
    ),
    requirements = { true },
    description = "",
    isNeedsToBeShown = false
)

var mapOfSchoolOfConjurationAbilities: MutableMap<String, AbilityNode> = mutableMapOf(
    Pair(conjurationSavant.name, divineSavant),
    Pair(minorConjuration.name, minorConjuration),
    Pair(benignTransposition.name, benignTransposition),
    Pair(focusedConjuration.name, focusedConjuration),
    Pair(durableSummons.name, durableSummons),
    Pair(schoolOfConjuration.name, schoolOfConjuration),
)