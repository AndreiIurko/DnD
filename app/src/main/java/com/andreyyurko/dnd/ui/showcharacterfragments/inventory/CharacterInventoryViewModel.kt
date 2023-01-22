package com.andreyyurko.dnd.ui.showcharacterfragments.inventory

import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.data.inventory.InventoryItem
import com.andreyyurko.dnd.data.inventory.InventoryItemInfo
import com.andreyyurko.dnd.utils.CharacterViewModel
import com.andreyyurko.dnd.utils.InventoryHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterInventoryViewModel @Inject constructor(
    val characterViewModel: CharacterViewModel,
    val inventoryHandler: InventoryHandler
) : ViewModel() {

    val filters = InventoryHandler.Filters()

    fun showItems(): MutableList<InventoryItemInfo> {
        return inventoryHandler.getItems(characterViewModel.shownCharacter, filters)
    }
}