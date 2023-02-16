package com.andreyyurko.dnd.data.abilities

import com.andreyyurko.dnd.data.abilities.classes.barbarian1
import com.andreyyurko.dnd.data.abilities.classes.fighter.fighter1
import com.andreyyurko.dnd.data.abilities.classes.mapOfClasses
import com.andreyyurko.dnd.data.abilities.classes.monk1
import com.andreyyurko.dnd.data.abilities.classes.sorcerer.sorcerer1
import com.andreyyurko.dnd.data.abilities.other.mapOfAbilityScoreImprovement
import com.andreyyurko.dnd.data.abilities.other.mapOfFightingStyles
import com.andreyyurko.dnd.data.abilities.other.mapOfLanguages
import com.andreyyurko.dnd.data.abilities.other.mapOfSkills
import com.andreyyurko.dnd.data.abilities.races.human
import com.andreyyurko.dnd.data.abilities.races.mapOfRaces
import com.andreyyurko.dnd.data.characterData.*
import com.andreyyurko.dnd.data.characterData.character.AbilityNode

var baseAN: AbilityNode = AbilityNode(
    "base_an",
    { abilities: CharacterInfo ->
        abilities.weaponProficiency.plus(Weapon.Unarmed)
        abilities.ac = abilities.currentState.armor.ac + Integer.min(
            abilities.currentState.armor.dexRestriction,
            (abilities.dexterity - 10) / 2
        )
        abilities.initiativeBonus = abilities.initiativeBonus + (abilities.dexterity - 10) / 2
        addAttackActions(abilities)
        abilities
    },
    mutableMapOf(
        Pair("class", listOf(monk1.name, barbarian1.name, fighter1.name, sorcerer1.name)),
        Pair("race", listOf(human.name))
    ),
    { true },
    listOf(listOf()),
    description = "Base Ability Node, root of all AN",
    priority = Priority.DoAsSoonAsPossible
)

// helps to split code
var mapOfAn: MutableMap<String, AbilityNode> = (
        mutableMapOf(Pair(baseAN.name, baseAN))
                + mapOfRaces
                + mapOfClasses
                + mapOfFightingStyles
                + mapOfAbilityScoreImprovement
                + mapOfSkills
                + mapOfLanguages
        ).toMutableMap()

fun addAttackActions(abilities: CharacterInfo) {
    // main action
    // TODO: consider to add damage as well
    abilities.actionsList.add(
        Action(
            name = "Атака",
            description = "Совершить одну атаку рукопашным оружием",
            type = ActionType.Action
        )
    )

    // attack as bonus action
    if (abilities.currentState.weapons.size == 2) {
        if (abilities.currentState.weapons[0].properties.contains("Лёгкое") and abilities.currentState.weapons[1].properties.contains(
                "Лёгкое"
            )
        ) {
            abilities.actionsList.add(
                Action(
                    name = "Атака второй рукой",
                    description = "Если вы совершаете действие «Атака» и атакуете рукопашным оружием со свойством «лёгкое», удерживаемым в одной руке, вы можете бонусным действием атаковать другим рукопашным оружием со свойством «лёгкое», удерживаемым в другой руке.\n" +
                            "\n" +
                            "Вы не добавляете модификатор характеристики к урону от бонусной атаки, если только он не отрицательный.\n" +
                            "\n" +
                            "Если у любого из оружий есть свойство «метательное», вы можете не совершать им рукопашную атаку, а метнуть его.",
                    type = ActionType.Bonus
                )
            )
        }
    }
}