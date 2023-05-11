package com.andreyyurko.dnd.ui.showcharacterfragments.equipment

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
import javax.inject.Inject

class EquipmentAdapter @Inject constructor(
    private val inventoryHandler: InventoryHandler,
    private val characterViewModel: CharacterViewModel
) : RecyclerView.Adapter<EquipmentAdapter.ViewHolder>() {

    var listOfItems: List<InventoryItemInfo> = listOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val typeAndRarityTextView: TextView = itemView.findViewById(R.id.typeAndRarityTextView)
        val chargesTextView: TextView = itemView.findViewById(R.id.chargesTextView)
        val increaseButton: ImageButton = itemView.findViewById(R.id.increaseButton)
        val decreaseButton: ImageButton = itemView.findViewById(R.id.decreaseButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_magic_item_equipment, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemProperties = inventoryHandler.getItemInfo(listOfItems[position].itemName)!!
        holder.nameTextView.text = listOfItems[position].itemName
        holder.typeAndRarityTextView.text = itemProperties.itemTypeAndRarity

        holder.nameTextView.setOnClickListener {
            showFullDescription(listOfItems[position], it.context, position)
        }

        holder.typeAndRarityTextView.setOnClickListener {
            showFullDescription(listOfItems[position], it.context, position)
        }

        if (listOfItems[position].currentCharges == null) {
            holder.chargesTextView.visibility = View.INVISIBLE
            holder.increaseButton.visibility = View.INVISIBLE
            holder.decreaseButton.visibility = View.INVISIBLE
        } else {
            listOfItems[position].currentCharges?.let {
                holder.chargesTextView.text = it.toString()
                if (it >= listOfItems[position].maximumCharges) {
                    holder.increaseButton.alpha = 0.5F
                    holder.increaseButton.isEnabled = false
                } else {
                    holder.increaseButton.alpha = 1F
                    holder.increaseButton.isEnabled = true
                }

                if (it <= 0) {
                    holder.decreaseButton.alpha = 0.5F
                    holder.decreaseButton.isEnabled = false
                } else {
                    holder.decreaseButton.alpha = 1F
                    holder.decreaseButton.isEnabled = true
                }

                holder.increaseButton.setOnClickListener { _ ->
                    listOfItems[position].currentCharges = it + 1
                    inventoryHandler.changeItemDescription(characterViewModel.shownCharacter, listOfItems[position])
                    notifyItemChanged(position)
                }

                holder.decreaseButton.setOnClickListener { _ ->
                    listOfItems[position].currentCharges = it - 1
                    inventoryHandler.changeItemDescription(characterViewModel.shownCharacter, listOfItems[position])
                    notifyItemChanged(position)
                }
            }
        }
    }

    private fun showFullDescription(itemDescription: InventoryItemInfo, context: Context, position: Int) {
        val item = inventoryHandler.getItemInfo(itemDescription.itemName)!!

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
        increaseButton.isEnabled = false
        decreaseButton.isEnabled = false
        increaseButton.visibility = View.INVISIBLE
        decreaseButton.visibility = View.INVISIBLE

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
            notifyItemChanged(position)
        }
    }
}