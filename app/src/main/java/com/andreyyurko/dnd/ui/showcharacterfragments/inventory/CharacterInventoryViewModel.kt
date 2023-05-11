package com.andreyyurko.dnd.ui.showcharacterfragments.inventory

import androidx.lifecycle.ViewModel
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

    private val allItemsFilters = InventoryHandler.Filters()
    private val chosenItemsFilters = InventoryHandler.Filters()
    var isChosenListShown = false

    fun showItems(): MutableList<InventoryItemInfo> {
        return if (isChosenListShown)
            inventoryHandler.getCharacterItems(characterViewModel.shownCharacter, chosenItemsFilters)
        else
            inventoryHandler.getItems(characterViewModel.shownCharacter, allItemsFilters)
    }

    fun getFilters(): InventoryHandler.Filters {
        return if (isChosenListShown)
            chosenItemsFilters
        else
            allItemsFilters
    }
}