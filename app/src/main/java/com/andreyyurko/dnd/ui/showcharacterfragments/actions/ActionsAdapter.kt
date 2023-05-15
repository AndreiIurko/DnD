package com.andreyyurko.dnd.ui.showcharacterfragments.actions

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.data.characterData.Action
import com.andreyyurko.dnd.utils.CharacterViewModel
import com.google.android.material.color.MaterialColors
import javax.inject.Inject

class ActionsAdapter @Inject constructor(
    private val characterViewModel: CharacterViewModel
) : RecyclerView.Adapter<ActionsAdapter.ViewHolder>() {

    var actionsList: List<Action> = listOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val actionNameTextView: TextView = itemView.findViewById(R.id.actionName)
        val increaseButton: ImageButton = itemView.findViewById(R.id.increaseButton)
        val countTextView: TextView = itemView.findViewById(R.id.chargesCountText)
        val decreaseButton: ImageButton = itemView.findViewById(R.id.decreaseButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_action_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return actionsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.actionNameTextView.text = actionsList[position].name
        holder.actionNameTextView.setOnClickListener {
            showDescription(actionsList[position], holder.actionNameTextView.context)
        }

        val charges =
            characterViewModel.shownCharacter.characterInfo.currentState.charges[actionsList[position].relatedCharges]
                ?: return

        holder.countTextView.visibility = View.VISIBLE
        holder.increaseButton.visibility = View.VISIBLE
        holder.decreaseButton.visibility = View.VISIBLE

        holder.countTextView.text = charges.current.toString()

        if (charges.current == 0) {
            holder.decreaseButton.alpha = 0.5F
            holder.decreaseButton.isEnabled = false
        } else {
            holder.decreaseButton.alpha = 1F
            holder.decreaseButton.isEnabled = true
        }

        if (charges.current == charges.maximum) {
            holder.increaseButton.alpha = 0.5F
            holder.increaseButton.isEnabled = false
        } else {
            holder.increaseButton.alpha = 1F
            holder.increaseButton.isEnabled = true
        }

        holder.increaseButton.setOnClickListener {
            charges.current += 1
            characterViewModel.updateCharacterInfo()
        }

        holder.decreaseButton.setOnClickListener {
            charges.current -= 1
            characterViewModel.updateCharacterInfo()
        }
    }

    private fun showDescription(action: Action, context: Context) {
        val parent = LayoutInflater.from(context).inflate(R.layout.view_action_description, null)
        parent.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            400 * context.resources.displayMetrics.density.toInt()
        )
        parent.setBackgroundColor(MaterialColors.getColor(context, R.attr.backgroundColor, Color.BLACK))

        parent.findViewById<TextView>(R.id.descriptionTextView).text = action.description

        val focus = true
        val wid = LinearLayout.LayoutParams.WRAP_CONTENT
        val high = 400 * context.resources.displayMetrics.density.toInt()
        val fullDescriptionPopUp = PopupWindow(parent, wid, high, focus)

        fullDescriptionPopUp.animationStyle = androidx.appcompat.R.style.Animation_AppCompat_Dialog
        fullDescriptionPopUp.showAtLocation(parent, Gravity.CENTER, 0, 0)
        characterViewModel.showPopUpBackground()

        fullDescriptionPopUp.setOnDismissListener {
            characterViewModel.closePopUpBackground()
        }
    }
}