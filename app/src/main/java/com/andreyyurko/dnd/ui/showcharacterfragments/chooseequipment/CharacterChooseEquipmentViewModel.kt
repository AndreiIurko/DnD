package com.andreyyurko.dnd.ui.showcharacterfragments.chooseequipment

import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.data.characterData.ItemType
import com.andreyyurko.dnd.data.inventory.InventoryItemInfo
import com.andreyyurko.dnd.utils.CharacterViewModel
import com.andreyyurko.dnd.utils.InventoryHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterChooseEquipmentViewModel @Inject constructor(
    val characterViewModel: CharacterViewModel,
    val inventoryHandler: InventoryHandler
) : ViewModel() {

    private fun getTypeItems(itemType: ItemType): MutableList<InventoryItemInfo> {
        val filters = InventoryHandler.Filters()
        filters.type.add(itemType)
        return inventoryHandler.getCharacterItems(characterViewModel.shownCharacter, filters)
    }

    fun getWeaponItems(): MutableList<InventoryItemInfo> {
        return getTypeItems(ItemType.Weapon)
    }

    fun getArmorItems(): MutableList<InventoryItemInfo> {
        return getTypeItems(ItemType.Armor)
    }

    fun getMagicWeapons(): List<InventoryItemInfo> {
        return getTypeItems(ItemType.Staff) + getTypeItems(ItemType.Rode) + getTypeItems(ItemType.Wand)
    }

    fun getArtifacts(): List<InventoryItemInfo> {
        return getTypeItems(ItemType.WondrousItem) + getTypeItems(ItemType.Ring)
    }

    fun getConsumables(): List<InventoryItemInfo> {
        return getTypeItems(ItemType.Scroll) + getTypeItems(ItemType.Potion)
    }

    fun isEquipped(itemName: String): Boolean {
        return true
    }
}