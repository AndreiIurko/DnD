package com.andreyyurko.dnd.data.abilities.classes.barbarian

import com.andreyyurko.dnd.data.characterData.Action
import com.andreyyurko.dnd.data.characterData.ActionType
import com.andreyyurko.dnd.data.characterData.CharacterInfo
import com.andreyyurko.dnd.data.characterData.Classes
import com.andreyyurko.dnd.data.characterData.character.AbilityNode

var frenzy = AbilityNode(
    name = "Бешенство",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Бешенство"] =
            "Находясь в состоянии ярости, вы можете впасть в бешенство. В этом случае, пока ваша ярость не прекратилась, вы можете в каждый свой ход после текущего совершать бонусным действием одну рукопашную атаку оружием. После окончания ярости вы получаете одну степень истощения.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 3 && abilities.characterClass == Classes.Barbarian
    },
    description = "Находясь в состоянии ярости, вы можете впасть в бешенство. В этом случае, пока ваша ярость не прекратилась, вы можете в каждый свой ход после текущего совершать бонусным действием одну рукопашную атаку оружием. После окончания ярости вы получаете одну степень истощения.\n"
)

var mindlessRage = AbilityNode(
    name = "Бездумная ярость",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Бездумная ярость"] =
            "Вы не можете быть испуганы или очарованы, пока находитесь в состоянии ярости. Если вы были испуганы или очарованы до того, как впали в состояние ярости, эти эффекты приостанавливаются до окончания вашей ярости.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 6 && abilities.characterClass == Classes.Barbarian
    },
    description = "Вы не можете быть испуганы или очарованы, пока находитесь в состоянии ярости. Если вы были испуганы или очарованы до того, как впали в состояние ярости, эти эффекты приостанавливаются до окончания вашей ярости.\n"
)

var intimidatingPresence = AbilityNode(
    name = "Пугающее присутствие",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Пугающее присутствие",
                description = "Вы можете действием пугать других своим ужасающим видом. Чтобы сделать это, выберите существо в пределах 30 футов от себя, которое вы можете видеть. Если оно может видеть или слышать вас, оно должно совершить успешный спасбросок Мудрости (Сл равна 8 + ваш бонус мастерства + ваш модификатор Харизмы), иначе станет испуганным вами до конца вашего следующего хода. В последующие ходы вы можете действием поддерживать этот эффект до конца своего следующего хода.\n" +
                        "\n" +
                        "Эффект оканчивается, если цель оказалась вне линии обзора, или далее чем в 60 футах от вас. Если существо преуспело в спасброске, вы не можете использовать на нём это умение следующие 24 часа.\n",
                type = ActionType.Action
            )
        )
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 10 && abilities.characterClass == Classes.Barbarian
    },
    description = "Вы можете действием пугать других своим ужасающим видом. Чтобы сделать это, выберите существо в пределах 30 футов от себя, которое вы можете видеть. Если оно может видеть или слышать вас, оно должно совершить успешный спасбросок Мудрости (Сл равна 8 + ваш бонус мастерства + ваш модификатор Харизмы), иначе станет испуганным вами до конца вашего следующего хода. В последующие ходы вы можете действием поддерживать этот эффект до конца своего следующего хода.\n" +
            "\n" +
            "Эффект оканчивается, если цель оказалась вне линии обзора, или далее чем в 60 футах от вас. Если существо преуспело в спасброске, вы не можете использовать на нём это умение следующие 24 часа.\n"
)

var retaliation = AbilityNode(
    name = "Ответный удар берсерка",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsList.add(
            Action(
                name = "Ответный удар берсерка",
                description = "При получении урона от существа, находящегося в пределах 5 футов от вас, вы можете реакцией совершить по нему рукопашную атаку оружием.\n",
                type = ActionType.Reaction
            )
        )
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 14 && abilities.characterClass == Classes.Barbarian
    },
    description = "При получении урона от существа, находящегося в пределах 5 футов от вас, вы можете реакцией совершить по нему рукопашную атаку оружием.\n"
)

var pathOfTheBerserker = AbilityNode(
    name = "Путь берсерка",
    changesInCharacterInfo = { abilities: CharacterInfo -> abilities },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(frenzy.name) }),
        Pair("second", { listOf(mindlessRage.name) }),
        Pair("third", { listOf(intimidatingPresence.name) }),
        Pair("fourth", { listOf(retaliation.name) })
    ),
    requirements = { true },
    description = "",
    isNeedsToBeShown = false
)

var mapOfPathOfTheBerserkerAbilities: MutableMap<String, AbilityNode> = mutableMapOf(
    Pair(frenzy.name, frenzy),
    Pair(mindlessRage.name, mindlessRage),
    Pair(intimidatingPresence.name, intimidatingPresence),
    Pair(retaliation.name, retaliation),
    Pair(pathOfTheBerserker.name, pathOfTheBerserker),
)