package com.andreyyurko.dnd.data.inventory

data class InventoryItemInfo(
    val itemName: String,
    var count: Int = 0,
    var notes: String = ""
)