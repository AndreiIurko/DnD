package com.andreyyurko.dnd.utils

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.data.characterData.ItemRarity
import com.andreyyurko.dnd.data.characterData.ItemType
import com.andreyyurko.dnd.data.characterData.Source
import com.andreyyurko.dnd.data.characterData.character.Character
import com.andreyyurko.dnd.data.inventory.InventoryItem
import com.andreyyurko.dnd.data.inventory.InventoryItemInfo
import com.andreyyurko.dnd.db.DBProvider
import com.google.gson.Gson
import com.squareup.moshi.Types
import java.io.IOException
import java.lang.reflect.Type
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InventoryHandler @Inject constructor(
    private val charactersHolder: CharactersHolder
) : ViewModel() {

    lateinit var allItems: MutableMap<String, InventoryItem>

    fun initialize(context: Context) {
        parseInventory(context)
    }

    private fun parseInventory(context: Context) {
        lateinit var jsonString: String
        try {
            jsonString = context.assets.open("magicItems_ru.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            Log.d("Inventory_parse", ioException.toString())
        }

        val type: Type = Types.newParameterizedType(MutableList::class.java, InventoryItem::class.java)
        val inventoryList: List<InventoryItem> = Gson().fromJson(jsonString, type)
        allItems = inventoryList.associateBy({ it.name }, { it }) as MutableMap<String, InventoryItem>
    }

    fun getItems(
        character: Character,
        filters: Filters = Filters()
    ): MutableList<InventoryItemInfo> {
        val resultList = mutableListOf<InventoryItemInfo>()
        Log.d("inventory", allItems.keys.toString())
        Log.d("inventory", allItems.values.size.toString())
        for (item in allItems.values) {
            if (checkFilterForItem(item, filters)) {
                resultList.add(getItemDescription(character, item.name))
            }
        }
        return resultList
    }

    fun getCharacterItems(
        character: Character,
        filters: Filters = Filters()
    ): MutableList<InventoryItemInfo> {
        val inventory = character.characterInfo.inventory
        val resultList = mutableListOf<InventoryItemInfo>()
        for (itemDescription in inventory.values) {
            val item = allItems[itemDescription.itemName]!!
            if (checkFilterForItem(item, filters)) {
                resultList.add(itemDescription)
            }
        }
        return resultList
    }

    private fun checkFilterForItem(item: InventoryItem, filters: Filters): Boolean {
        return checkRarityFilter(item.itemTypeAndRarity, filters.rarity.map { it.rarityName }) &&
                checkFilter(item.itemTypeAndRarity, filters.type.map { it.typeName }) &&
                checkFilter(item.source, filters.source.map { it.sourceName }) &&
                item.name.lowercase().contains(filters.substring.lowercase())
    }

    fun getItemDescription(character: Character, itemName: String): InventoryItemInfo {
        return character.characterInfo.inventory[itemName] ?: InventoryItemInfo(itemName = itemName)
    }

    fun changeItemDescription(character: Character, itemDescription: InventoryItemInfo) {
        val inventory = character.characterInfo.inventory
        inventory[itemDescription.itemName] = itemDescription
        if (itemDescription.count == 0) {
            inventory.remove(itemDescription.itemName)
        }
        Log.d("inventory", itemDescription.toString())
        charactersHolder.updateCharacter(character)
    }

    data class Filters(
        val rarity: MutableSet<ItemRarity> = mutableSetOf(),
        val type: MutableSet<ItemType> = mutableSetOf(),
        val source: MutableSet<Source> = mutableSetOf(),
        var substring: String = "",
    )

    private fun checkFilter(target: String, filter: List<String>): Boolean {
        if (filter.isEmpty()) return true
        var isCorrect = false
        for (filterItem in filter) {
            if (target.lowercase().contains(filterItem.lowercase())) isCorrect = true
        }
        return isCorrect
    }

    private fun checkRarityFilter(target: String, filter: List<String>): Boolean {
        if (filter.isEmpty()) return true
        var isCorrect = false
        for (filterItem in filter) {
            if (target.lowercase().contains(", " + filterItem.lowercase().substring(0..2))) isCorrect = true
        }
        return isCorrect
    }
}