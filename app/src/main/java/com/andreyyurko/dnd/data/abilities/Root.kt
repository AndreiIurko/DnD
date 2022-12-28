package com.andreyyurko.dnd.data.abilities

import android.util.Log
import com.andreyyurko.dnd.data.abilities.characterclass.*
import com.andreyyurko.dnd.data.abilities.other.mapOfFightingStyles
import com.andreyyurko.dnd.data.characters.*
import java.lang.Integer.max

var baseAN: AbilityNode = AbilityNode(
    "base_an",
    {abilities: CharacterInfo ->
        abilities.weaponProficiency.plus(Weapon.Unarmed)
        abilities.ac = abilities.currentState.armor.ac + Integer.min(abilities.currentState.armor.dexRestriction, (abilities.dexterityBonus - 10) / 2)
        abilities.initiativeBonus = abilities.initiativeBonus + (abilities.dexterityBonus - 10) / 2
        //Log.d("Priority", "base:" + abilities.ac.toString())
        addAttackActions(abilities)
        abilities
    },
    mutableMapOf(
        Pair("class", listOf(monk1.name, barbarian1.name, fighter1.name)),
    ),
    {true},
    listOf(listOf()),
    description = "Base Ability Node, root of all AN",
    priority = Priority.DoFirst
)

var mapOfAn: MutableMap<String, AbilityNode> = (
        mutableMapOf(Pair(baseAN.name, baseAN))
                + mapOfMonkAbilities
                + mapOfBarbarianAbilities
                + mapOfFightingStyles
                + mapOfFighterAbilities
        ).toMutableMap()

fun addAttackActions(abilities: CharacterInfo) {
    // main action
    abilities.actionsList.add(
        Action(
            name = "Атака",
            description = "Совершить одну атаку рукопашным оружием",
            type = ActionType.Action
        )
    )

    // attack as bonus action
    if (abilities.currentState.weapons.size == 2) {
        if (abilities.currentState.weapons[0].properties.contains("Лёгкое") and abilities.currentState.weapons[1].properties.contains("Лёгкое")) {
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