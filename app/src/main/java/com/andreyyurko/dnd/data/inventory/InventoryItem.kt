package com.andreyyurko.dnd.data.inventory

data class InventoryItem(
    val name: String,
    val engName: String,
    val itemTypeAndRarity: String,
    val cost: String,
    val source: String,
    val description: String
)