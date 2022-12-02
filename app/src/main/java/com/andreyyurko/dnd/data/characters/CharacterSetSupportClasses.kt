package com.andreyyurko.dnd.data.characters

data class Action(
    var description: String,
    var abilityId: Int
)

data class EquipmentItem(
    var id: Int,
    var abilityId: Int,
    var description: String,
    var name: String,
    var rarity: ItemRarity,
    var type: ItemType
)
