package com.andreyyurko.dnd.data.inventory

data class InventoryItemInfo(
    val itemName: String,
    var count: Int = 0,
    var notes: String = "",
    var maximumCharges: Int = 0,
    var currentCharges: Int? = null
)