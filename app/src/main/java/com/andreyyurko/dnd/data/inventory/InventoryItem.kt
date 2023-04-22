package com.andreyyurko.dnd.data.inventory

import com.andreyyurko.dnd.data.characterData.CharacterInfo
import com.andreyyurko.dnd.data.characterData.InventoryBonus

data class InventoryItem(
    val name: String,
    val engName: String,
    val itemTypeAndRarity: String,
    val cost: String,
    val source: String,
    val description: String,
    val inventoryBonus: InventoryBonus = InventoryBonus()
)