package com.andreyyurko.dnd.utils

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.data.characterData.*
import com.andreyyurko.dnd.data.characterData.character.Character
import com.andreyyurko.dnd.data.characterData.character.mergeAllAbilities
import com.andreyyurko.dnd.data.inventory.InventoryItem
import com.andreyyurko.dnd.data.inventory.InventoryItemInfo
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
            jsonString = context.assets.open("magicItems.json")
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
        return checkRarityFilter(item.itemTypeAndRarity, filters.rarity.map { it.shownName }) &&
                checkFilter(item.itemTypeAndRarity, filters.type.map { it.shownName }) &&
                checkFilter(item.source, filters.source.map { it.sourceName }) &&
                item.name.lowercase().contains(filters.substring.lowercase())
    }

    fun getItemDescription(character: Character, itemName: String): InventoryItemInfo {
        return character.characterInfo.inventory[itemName] ?: InventoryItemInfo(itemName = itemName)
    }

    fun changeItemDescription(character: Character, itemDescription: InventoryItemInfo) {
        val inventory = character.characterInfo.inventory
        inventory[itemDescription.itemName] = itemDescription
        if (itemDescription.count == 1 && character.characterInfo.currentState.secondWeapon != null) {
            if (itemDescription.itemName == character.characterInfo.currentState.secondWeaponName) {
                unequipItem(character, itemDescription.itemName)
            }
        }
        if (itemDescription.count == 0) {
            // nothing will happen if this item not equipped
            unequipItem(character, itemDescription.itemName)
            inventory.remove(itemDescription.itemName)
        }
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

    fun isItemEquipped(character: Character, itemName: String): Boolean {
        val listOfEquippedItemsNames: MutableList<String> = mutableListOf()
        val currentState = character.characterInfo.currentState
        listOfEquippedItemsNames.add(currentState.armorName)
        listOfEquippedItemsNames.add(currentState.firstWeaponName)
        if (currentState.secondWeapon != null) {
            listOfEquippedItemsNames.add(currentState.secondWeaponName)
        }
        if (currentState.hasShield) {
            listOfEquippedItemsNames.add(currentState.shieldItemName)
        }
        listOfEquippedItemsNames += currentState.equippedArtifacts
        listOfEquippedItemsNames += currentState.equippedMagicWeapons

        return listOfEquippedItemsNames.contains(itemName)
    }

    fun isItemEquitable(character: Character, itemName: String): Boolean {
        val item = allItems[itemName]!!
        val currentState = character.characterInfo.currentState
        // weapon
        if (item.itemTypeAndRarity.lowercase().contains(ItemType.Weapon.shownName.lowercase())) {
            if (currentState.firstWeapon == Weapon.Unarmed) {
                return true
            }
            else if (currentState.secondWeapon == null && currentState.firstWeapon.properties.contains("лёгкое") && !currentState.hasShield) {
                var weapon: Weapon = Weapon.Unarmed
                for (weaponType in Weapon.values()) {
                    if (item.itemTypeAndRarity.lowercase().contains(weaponType.weaponName.lowercase())) {
                        weapon = weaponType
                        break
                    }
                }
                if (weapon.properties.contains("лёгкое")) {
                    return true
                }
            }
        }

        //armor
        if (item.itemTypeAndRarity.lowercase().contains(ItemType.Armor.shownName.lowercase())) {
            if (item.itemTypeAndRarity.contains("щит") && !currentState.hasShield && currentState.secondWeapon == null &&
                !currentState.firstWeapon.properties.contains("двуручное")) {
                return true
            }
            else if (currentState.armor == Armor.NoArmor) {
                return true
            }
        }

        // wand/staff/rode
        if (item.itemTypeAndRarity.lowercase().contains(ItemType.Wand.shownName.lowercase()) ||
            item.itemTypeAndRarity.lowercase().contains(ItemType.Staff.shownName.lowercase()) ||
            item.itemTypeAndRarity.lowercase().contains(ItemType.Rode.shownName.lowercase())) {
            if (!currentState.equippedMagicWeapons.contains(itemName)) {
                return true
            }
        }

        // wondrous item/ring
        if (item.itemTypeAndRarity.lowercase().contains(ItemType.WondrousItem.shownName.lowercase()) ||
            item.itemTypeAndRarity.lowercase().contains(ItemType.Ring.shownName.lowercase())) {
            if (!currentState.equippedArtifacts.contains(itemName)) {
                return true
            }
        }

        return false
    }

    fun equipItem(character: Character, itemName: String): Boolean {
        val item = allItems[itemName]!!
        val currentState = character.characterInfo.currentState
        // adding weapon
        if (item.itemTypeAndRarity.lowercase().contains(ItemType.Weapon.shownName.lowercase())) {
            if (currentState.firstWeapon == Weapon.Unarmed) {
                var weapon: Weapon = Weapon.Unarmed
                for (weaponType in Weapon.values()) {
                    if (item.itemTypeAndRarity.lowercase().contains(weaponType.weaponName.lowercase())) {
                        weapon = weaponType
                        break
                    }
                }
                currentState.firstWeapon = weapon
                currentState.firstWeaponName = itemName
                currentState.inventoryBonuses[itemName] = item.inventoryBonus
                return true
            }
            else if (currentState.secondWeapon == null && currentState.firstWeapon.properties.contains("лёгкое") && !currentState.hasShield) {
                var weapon: Weapon = Weapon.Unarmed
                for (weaponType in Weapon.values()) {
                    if (item.itemTypeAndRarity.lowercase().contains(weaponType.weaponName.lowercase())) {
                        weapon = weaponType
                        break
                    }
                }
                if (weapon.properties.contains("лёгкое")) {
                    currentState.secondWeapon = weapon
                    currentState.secondWeaponName = itemName
                    currentState.inventoryBonuses[itemName] = item.inventoryBonus
                    return true
                }
            }
        }

        //adding armor
        if (item.itemTypeAndRarity.lowercase().contains(ItemType.Armor.shownName.lowercase())) {
            if (item.itemTypeAndRarity.contains("щит") && !currentState.hasShield && currentState.secondWeapon == null &&
                    !currentState.firstWeapon.properties.contains("двуручное")) {
                currentState.hasShield = true
                currentState.shieldItemName = itemName
            }
            else if (currentState.armor == Armor.NoArmor) {
                var armor: Armor = Armor.NoArmor
                for (armorType in Armor.values()) {
                    if (item.itemTypeAndRarity.lowercase().contains(armorType.armorName.lowercase())) {
                        armor = armorType
                        break
                    }
                }
                currentState.armor = armor
                currentState.armorName = itemName
                currentState.inventoryBonuses[itemName] = item.inventoryBonus
                return true
            }
        }

        // adding wand/staff/rode
        if (item.itemTypeAndRarity.lowercase().contains(ItemType.Wand.shownName.lowercase()) ||
                item.itemTypeAndRarity.lowercase().contains(ItemType.Staff.shownName.lowercase()) ||
                item.itemTypeAndRarity.lowercase().contains(ItemType.Rode.shownName.lowercase())) {
            if (!currentState.equippedMagicWeapons.contains(itemName)) {
                currentState.equippedMagicWeapons.add(itemName)
                currentState.inventoryBonuses[itemName] = item.inventoryBonus
                return true
            }
        }

        // adding wondrous item/ring
        if (item.itemTypeAndRarity.lowercase().contains(ItemType.WondrousItem.shownName.lowercase()) ||
                item.itemTypeAndRarity.lowercase().contains(ItemType.Ring.shownName.lowercase())) {
            if (!currentState.equippedArtifacts.contains(itemName)) {
                currentState.equippedArtifacts.add(itemName)
                currentState.inventoryBonuses[itemName] = item.inventoryBonus
                return true
            }
        }

        return false
    }

    fun unequipItem(character: Character, itemName: String) {
        val item = allItems[itemName]!!
        val currentState = character.characterInfo.currentState


        if (item.itemTypeAndRarity.lowercase().contains(ItemType.Weapon.shownName.lowercase())) {
            if (currentState.secondWeaponName == itemName) {
                currentState.secondWeapon = null
                currentState.secondWeaponName = ""
                currentState.inventoryBonuses.remove(itemName)
                // in case of similar weapons
                currentState.inventoryBonuses[currentState.firstWeapon.weaponName] = allItems[currentState.firstWeaponName]!!.inventoryBonus
            }
            else if (currentState.firstWeaponName == itemName) {
                if (currentState.secondWeapon != null) {
                    currentState.inventoryBonuses.remove(itemName)
                    currentState.firstWeapon = currentState.secondWeapon!!
                    currentState.firstWeaponName = currentState.secondWeaponName
                    currentState.secondWeapon = null
                    currentState.secondWeaponName = ""
                    // in case of similar weapons
                    currentState.inventoryBonuses[currentState.firstWeapon.weaponName] = allItems[currentState.firstWeaponName]!!.inventoryBonus
                }
                else {
                    currentState.firstWeapon = Weapon.Unarmed
                    currentState.firstWeaponName = Weapon.Unarmed.weaponName
                    currentState.inventoryBonuses.remove(itemName)
                }
            }
        }

        // removing armor
        if (item.itemTypeAndRarity.lowercase().contains(ItemType.Armor.shownName.lowercase())) {
            if (item.itemTypeAndRarity.contains("щит") && currentState.hasShield) {
                currentState.hasShield = false
                currentState.shieldItemName = ""
            }
            else if (currentState.armorName == itemName) {
                currentState.inventoryBonuses.remove(itemName)
                currentState.armor = Armor.NoArmor
                currentState.armorName = ""
            }
        }

        // removing wand/staff/rode
        if (item.itemTypeAndRarity.lowercase().contains(ItemType.Wand.shownName.lowercase()) ||
            item.itemTypeAndRarity.lowercase().contains(ItemType.Staff.shownName.lowercase()) ||
            item.itemTypeAndRarity.lowercase().contains(ItemType.Rode.shownName.lowercase())) {
            currentState.inventoryBonuses.remove(itemName)
            currentState.equippedMagicWeapons.remove(itemName)
        }

        // removing wondrous item/ring
        if (item.itemTypeAndRarity.lowercase().contains(ItemType.WondrousItem.shownName.lowercase()) ||
            item.itemTypeAndRarity.lowercase().contains(ItemType.Ring.shownName.lowercase())) {
            currentState.inventoryBonuses.remove(itemName)
            currentState.equippedArtifacts.remove(itemName)
        }
    }

}