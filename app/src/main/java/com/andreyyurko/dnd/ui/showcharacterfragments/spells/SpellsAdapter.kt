package com.andreyyurko.dnd.ui.showcharacterfragments.spells

import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.data.SpellSpecificLanguage
import com.andreyyurko.dnd.data.spells.Spell
import com.google.android.material.color.MaterialColors
import javax.inject.Inject

class SpellsAdapter @Inject constructor(
    private val spellsCountTextView: TextView,
    private val cantripsCountTextView: TextView,
    private val root: ViewGroup,
    private val showPopUpBackground: () -> Unit,
    private val closePopUpBackground: () -> Unit,
    private val addSpell: (spell: Spell) -> Unit,
    private val removeSpell: (spell: Spell) -> Unit,
    private val getChosenSpells: () -> List<Spell>,
) : RecyclerView.Adapter<SpellsAdapter.ViewHolder>() {

    var shownSpellsList: List<Spell> = listOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.spellName)
        val levelTextView: TextView = itemView.findViewById(R.id.spellLevel)
        val castTimeTextView: TextView = itemView.findViewById(R.id.spellCastTime)
        val isAddedCheckbox: CheckBox = itemView.findViewById(R.id.isAdded)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.view_spell_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return shownSpellsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        shownSpellsList[position].data.name.apply {
            if (this.length > 20) holder.nameTextView.text = this.substring(0..16) + "..."
            else holder.nameTextView.text = this
        }
        holder.levelTextView.text = shownSpellsList[position].data.level
        shownSpellsList[position].data.castingTime.apply {
            if (this.split(" ")[1][0] == 'м') holder.castTimeTextView.text =
                shownSpellsList[position].data.castingTime.split(",")[0]
            else holder.castTimeTextView.text = this.split(" ")[1]
        }
        holder.isAddedCheckbox.setOnCheckedChangeListener(null)
        var isChecked = false
        var isAlwaysIncluded = false
        getChosenSpells().map { spell: Spell ->
            if (spell.data.name == shownSpellsList[position].data.name) {
                isChecked = true
                isAlwaysIncluded = spell.isAlwaysIncluded
            }
        }
        holder.isAddedCheckbox.isChecked = isChecked
        holder.isAddedCheckbox.isEnabled = !isAlwaysIncluded

        holder.isAddedCheckbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                addSpell(shownSpellsList[position])
                if (shownSpellsList[position].data.level.toInt() == 0)
                    cantripsCountTextView.text =
                        (cantripsCountTextView.text.toString().toInt() + 1).toString()
                else
                    spellsCountTextView.text =
                        (spellsCountTextView.text.toString().toInt() + 1).toString()
            } else {
                removeSpell(shownSpellsList[position])
                if (shownSpellsList[position].data.level.toInt() == 0)
                    cantripsCountTextView.text =
                        (cantripsCountTextView.text.toString().toInt() - 1).toString()
                else
                    spellsCountTextView.text =
                        (spellsCountTextView.text.toString().toInt() - 1).toString()
            }
        }

        holder.nameTextView.setOnClickListener {
            showFullDescription(shownSpellsList[position].data)
        }
    }

    private fun showFullDescription(spell: SpellSpecificLanguage) {
        val parent =
            LayoutInflater.from(root.context).inflate(R.layout.view_spell_description, null)
        parent.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            400 * root.context.resources.displayMetrics.density.toInt()
        )
        parent.setBackgroundColor(
            MaterialColors.getColor(
                root.context,
                R.attr.backgroundColor,
                Color.BLACK
            )
        )

        parent.findViewById<TextView>(R.id.nameTextView).text = spell.name
        parent.findViewById<TextView>(R.id.levelAndSchoolTextView).text =
            "уровень: " + spell.level + ", " + spell.school
        parent.findViewById<TextView>(R.id.castTimeAndRangeAndConcentrationTextView).text =
            spell.castingTime + ", дистанция: " + spell.range
        parent.findViewById<TextView>(R.id.durationTextView).text = "Длительность:" + spell.duration
        if (spell.materials != "")
            parent.findViewById<TextView>(R.id.componentsAndMaterialsTextView).text =
                "Компоненты: " + spell.components + ", " + spell.materials
        else
            parent.findViewById<TextView>(R.id.componentsAndMaterialsTextView).text =
                "Компоненты: " + spell.components
        parent.findViewById<TextView>(R.id.descriptionTextView).text = spell.text

        val focus = true
        val wid = LinearLayout.LayoutParams.WRAP_CONTENT
        val high = 400 * root.context.resources.displayMetrics.density.toInt()
        val fullDescriptionPopUp = PopupWindow(parent, wid, high, focus)

        fullDescriptionPopUp.animationStyle = androidx.appcompat.R.style.Animation_AppCompat_Dialog
        fullDescriptionPopUp.showAtLocation(parent, Gravity.CENTER, 0, 0)
        showPopUpBackground()

        fullDescriptionPopUp.setOnDismissListener {
            closePopUpBackground()
        }
    }
}