package com.andreyyurko.dnd.data.characterData

data class Action(
    var name: String,
    var description: String,
    var type: ActionType
)

data class EquipmentItem(
    var id: Int,
    var abilityId: Int,
    var description: String,
    var name: String,
    var rarity: ItemRarity,
    var type: ItemType
)

data class ChargesCounter(
    var current: Int,
    var maximum: Int
)
