package com.andreyyurko.dnd.data.abilities

import android.util.Log
import com.andreyyurko.dnd.data.abilities.characterclass.*
import com.andreyyurko.dnd.data.characters.AbilityNode
import com.andreyyurko.dnd.data.characters.CharacterInfo
import com.andreyyurko.dnd.data.characters.Priority

var baseAN: AbilityNode = AbilityNode(
    "base_an",
    {abilities: CharacterInfo ->
        abilities.ac = abilities.currentState.armor.ac + Integer.min(abilities.currentState.armor.dexRestriction, (abilities.dexterityBonus - 10) / 2)
        abilities.initiativeBonus = abilities.initiativeBonus + 5
        //Log.d("Priority", "base:" + abilities.ac.toString())
        abilities
    },
    mutableMapOf(
        Pair("class", listOf(monk1.name, barbarian1.name)),
    ),
    {true},
    listOf(listOf()),
    description = "Base Ability Node, root of all AN",
    priority = Priority.DoFirst
)

var mapOfAn: MutableMap<String, AbilityNode> = mutableMapOf(
    Pair(baseAN.name, baseAN),
    Pair(barbarian1.name, barbarian1),
    Pair(barbarian2.name, barbarian2),
    Pair(monk1.name, monk1),
    Pair(monk2.name, monk2),
    Pair(monk_unarmed_defence.name, monk_unarmed_defence),
    Pair(monk_martial_arts.name, monk_martial_arts),
    Pair(monk_unarmed_movement.name, monk_unarmed_movement)
)