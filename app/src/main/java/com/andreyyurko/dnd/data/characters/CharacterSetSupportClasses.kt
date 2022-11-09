package com.andreyyurko.dnd.data.characters

data class Action(
    var description: String
)

data class EquipmentItem(
    var id: Int,
    var description: String,
    var name: String,
    var rarity: ItemRarity,
    var type: ItemType
)
