package com.andreyyurko.dnd.ui.showcharacterfragments.inventory

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.data.inventory.InventoryItemInfo
import com.andreyyurko.dnd.utils.CharacterViewModel
import com.andreyyurko.dnd.utils.InventoryHandler
import com.andreyyurko.dnd.utils.onPressAnimation
import com.andreyyurko.dnd.utils.setupBasicPopUpMenu
import javax.inject.Inject

class InventoryAdapter @Inject constructor(
    private val inventoryHandler: InventoryHandler,
    private val characterViewModel: CharacterViewModel,
    private val popupBackground: View
) : RecyclerView.Adapter<InventoryAdapter.ViewHolder>() {

    var itemsList: MutableList<InventoryItemInfo> = mutableListOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView : TextView = itemView.findViewById(R.id.itemName)
        val increaseButton : ImageButton = itemView.findViewById(R.id.increaseButton)
        val countTextView : TextView = itemView.findViewById(R.id.count)
        val decreaseButton : ImageButton = itemView.findViewById(R.id.decreaseButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_inventory_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameTextView.text = itemsList[position].itemName
        holder.countTextView.text = itemsList[position].count.toString()

        if (itemsList[position].count == 0) holder.decreaseButton.alpha = 0.5F

        holder.increaseButton.setOnClickListener {
            itemsList[position].count += 1
            holder.decreaseButton.alpha = 1.0F
            inventoryHandler.changeItemDescription(characterViewModel.shownCharacter, itemsList[position])
            holder.countTextView.text = itemsList[position].count.toString()
        }

        holder.decreaseButton.setOnClickListener {
            itemsList[position].count -= 1
            if (itemsList[position].count == 0) holder.decreaseButton.alpha = 0.5F
            inventoryHandler.changeItemDescription(characterViewModel.shownCharacter, itemsList[position])
            holder.countTextView.text = itemsList[position].count.toString()
        }

        holder.nameTextView.isClickable = true
        holder.nameTextView.setOnClickListener { 
            showFullDescription(itemsList[position], it.context, position )
        }
    }
    
    private fun showFullDescription(itemDescription: InventoryItemInfo, context: Context, position: Int) {
        val item = inventoryHandler.allItems[itemDescription.itemName]!!
        
        val parent = LayoutInflater.from(context).inflate(R.layout.view_full_inventory_item, null)
        parent.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            400 * context.resources.displayMetrics.density.toInt()
        )

        
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

        increaseButton.setOnClickListener {
            itemDescription.count += 1
            decreaseButton.alpha = 1.0F
            //inventoryHandler.changeItemDescription(characterViewModel.shownCharacter, itemDescription)
            countTextView.text = itemDescription.count.toString()
        }

        decreaseButton.setOnClickListener {
            itemDescription.count -= 1
            if (itemDescription.count == 0) decreaseButton.alpha = 0.5F
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
        fullDescriptionPopUp.showAtLocation(popupBackground, Gravity.CENTER, 0, 0)
        popupBackground.alpha = 0.5F

        fullDescriptionPopUp.setOnDismissListener {
            popupBackground.alpha = 0.0F
            notifyItemChanged(position)
        }
    }
}