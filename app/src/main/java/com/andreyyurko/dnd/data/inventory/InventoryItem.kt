package com.andreyyurko.dnd.data.inventory

import com.andreyyurko.dnd.data.characterData.InventoryRelevantData

data class InventoryItem(
    val name: String,
    val engName: String,
    val itemTypeAndRarity: String,
    val cost: String,
    val source: String,
    val description: String,
    val inventoryRelevantData: InventoryRelevantData = InventoryRelevantData()
)