package com.andreyyurko.dnd.ui.showcharacterfragments.equipment

import android.content.Context
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.data.characterData.ItemType
import com.andreyyurko.dnd.data.inventory.InventoryItemInfo
import com.andreyyurko.dnd.ui.showcharacterfragments.inventory.InventoryAdapter
import com.andreyyurko.dnd.utils.CharacterViewModel
import com.andreyyurko.dnd.utils.InventoryHandler
import javax.inject.Inject

class EquipmentAdapter @Inject constructor(
    private val inventoryHandler: InventoryHandler,
    private val characterViewModel: CharacterViewModel
) : RecyclerView.Adapter<EquipmentAdapter.ViewHolder>() {

    data class EquipmentItem(
        val itemDescription: InventoryItemInfo,
        var isEquipped: Boolean,
        var isCanBeEquipped: Boolean
    )

    var itemsList: List<EquipmentItem> = listOf()
    var itemsListWithoutCopies: List<InventoryItemInfo> = listOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val equipButton: TextView = itemView.findViewById(R.id.equipButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_equipment_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("test", position.toString())
        holder.nameTextView.text = itemsList[position].itemDescription.itemName
        holder.nameTextView.setOnClickListener {
            showFullDescription(itemsList[position].itemDescription, it.context, position)
        }

        if (!itemsList[position].isEquipped && !itemsList[position].isCanBeEquipped) {
            holder.equipButton.alpha = 0.5F
            holder.equipButton.isEnabled = false
        }
        else {
            holder.equipButton.alpha = 1.0F
            holder.equipButton.isEnabled = true
        }

        if (itemsList[position].isEquipped) {
            holder.equipButton.text = "Снять"
        }
        else {
            holder.equipButton.text = "Надеть"
        }

        holder.equipButton.setOnClickListener {
            if (itemsList[position].isEquipped ) {
                inventoryHandler.unequipItem(characterViewModel.shownCharacter, itemsList[position].itemDescription.itemName)
            }
            else {
                inventoryHandler.equipItem(characterViewModel.shownCharacter, itemsList[position].itemDescription.itemName)
            }
            characterViewModel.updateCharacterInfo()
            itemsList = createListWithCopies()
            notifyDataSetChanged()
        }
    }

    fun createListWithCopies(): MutableList<EquipmentItem> {
        val resultList: MutableList<EquipmentItem> = mutableListOf()
        for (item in itemsListWithoutCopies) {
            if (item.count <= 0) continue

            val isEquipped = inventoryHandler.isItemEquipped(characterViewModel.shownCharacter, item.itemName)
            val isCanBeEquipped = inventoryHandler.isItemEquitable(characterViewModel.shownCharacter, item.itemName)
            Log.d("test", item.itemName)
            Log.d("test", isEquipped.toString())
            Log.d("test", isCanBeEquipped.toString())

            resultList.add(EquipmentItem(
                item,
                isEquipped,
                isCanBeEquipped
            ))

            if (isEquipped && isCanBeEquipped && item.count >= 2) {
                resultList.add(
                    EquipmentItem(
                        item,
                        isEquipped = false,
                        isCanBeEquipped = true
                    )
                )
            }
            if (isEquipped && !isCanBeEquipped && item.count >= 2) {
                if (characterViewModel.shownCharacter.characterInfo.currentState.firstWeaponName ==
                        characterViewModel.shownCharacter.characterInfo.currentState.secondWeaponName &&
                        characterViewModel.shownCharacter.characterInfo.currentState.firstWeaponName == item.itemName)
                    resultList.add(
                        EquipmentItem(
                            item,
                            isEquipped = true,
                            isCanBeEquipped = false
                        )
                    )
                else
                    resultList.add(
                        EquipmentItem(
                            item,
                            isEquipped = false,
                            isCanBeEquipped = false
                        )
                    )
            }
        }
        return resultList
    }

    private fun showFullDescription(itemDescription: InventoryItemInfo, context: Context, position: Int) {
        val item = inventoryHandler.allItems[itemDescription.itemName]!!

        val parent = LayoutInflater.from(context).inflate(R.layout.view_full_inventory_item, null)
        parent.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            400 * context.resources.displayMetrics.density.toInt()
        )
        parent.setBackgroundColor(context.getColor(R.color.background))

        parent.findViewById<TextView>(R.id.name).text = item.name
        parent.findViewById<TextView>(R.id.typeAndRarity).text = item.itemTypeAndRarity
        parent.findViewById<TextView>(R.id.source).text = "Источник: ${item.source}"
        parent.findViewById<TextView>(R.id.cost).text = item.cost
        parent.findViewById<TextView>(R.id.description).text = item.description

        parent.findViewById<EditText>(R.id.notesEditText).apply {
            this.setText(itemDescription.notes)
            // TODO: add buffer with auto save
            this.doOnTextChanged { text, _, _, _ ->
                text?.let {
                    itemDescription.notes = it.toString()
                }
            }
        }

        val countTextView = parent.findViewById<TextView>(R.id.count)
        countTextView.text = itemDescription.count.toString()

        val increaseButton: ImageButton = parent.findViewById(R.id.increaseButton)
        val decreaseButton: ImageButton = parent.findViewById(R.id.decreaseButton)
        if (itemDescription.count == 0) {
            decreaseButton.isEnabled = false
            decreaseButton.alpha = 0.5F
        }


        increaseButton.setOnClickListener {
            itemDescription.count += 1
            decreaseButton.alpha = 1.0F
            decreaseButton.isEnabled = true
            //inventoryHandler.changeItemDescription(characterViewModel.shownCharacter, itemDescription)
            countTextView.text = itemDescription.count.toString()
        }

        decreaseButton.setOnClickListener {
            itemDescription.count -= 1
            if (itemDescription.count == 0) {
                decreaseButton.alpha = 0.5F
                decreaseButton.isEnabled = false
            }
            //inventoryHandler.changeItemDescription(characterViewModel.shownCharacter, itemDescription)
            countTextView.text = itemDescription.count.toString()
        }

        val focus = true
        val wid = LinearLayout.LayoutParams.WRAP_CONTENT
        val high = 400 * context.resources.displayMetrics.density.toInt()
        val fullDescriptionPopUp = PopupWindow(parent, wid, high, focus)

        parent.findViewById<Button>(R.id.saveButton).setOnClickListener {
            inventoryHandler.changeItemDescription(characterViewModel.shownCharacter, itemDescription)
            fullDescriptionPopUp.dismiss()
        }

        fullDescriptionPopUp.animationStyle = androidx.appcompat.R.style.Animation_AppCompat_Dialog
        fullDescriptionPopUp.showAtLocation(parent, Gravity.CENTER, 0, 0)
        characterViewModel.showPopUpBackground()

        fullDescriptionPopUp.setOnDismissListener {
            characterViewModel.closePopUpBackground()
            itemsList = createListWithCopies()
            notifyDataSetChanged()
        }
    }
}