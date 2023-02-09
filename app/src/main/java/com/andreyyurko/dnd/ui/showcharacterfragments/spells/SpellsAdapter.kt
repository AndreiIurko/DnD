package com.andreyyurko.dnd.ui.showcharacterfragments.spells

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.data.SpellSpecificLanguage
import com.andreyyurko.dnd.utils.CharacterViewModel
import com.andreyyurko.dnd.utils.SpellsHandler
import javax.inject.Inject

class SpellsAdapter @Inject constructor(
    private val spellsHandler: SpellsHandler,
    private val characterViewModel: CharacterViewModel,
    private val preparedSpellsCountTextView: TextView,
    private val preparedCantripsCountTextView: TextView
) : RecyclerView.Adapter<SpellsAdapter.ViewHolder>() {

    var spellsList : List<SpellSpecificLanguage> = listOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.spellName)
        val levelTextView: TextView = itemView.findViewById(R.id.spellLevel)
        val castTimeTextView: TextView = itemView.findViewById(R.id.spellCastTime)
        val isPreparedCheckbox: CheckBox = itemView.findViewById(R.id.isPrepared)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_spell_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return spellsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        spellsList[position].name.apply {
            if (this.length > 20) holder.nameTextView.text = this.substring(0..16) + "..."
            else holder.nameTextView.text = this
        }
        holder.levelTextView.text = spellsList[position].level
        spellsList[position].castingTime.apply {
            if (this.split(" ")[2][0] == 'м') holder.castTimeTextView.text = spellsList[position].castingTime.split(",")[0]
            else holder.castTimeTextView.text = this.split(" ")[2]
        }
        holder.isPreparedCheckbox.setOnCheckedChangeListener(null)
        holder.isPreparedCheckbox.isChecked = spellsHandler.getPreparedSpells(characterViewModel.shownCharacter).contains(spellsList[position])

        holder.isPreparedCheckbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                spellsHandler.addSpell(characterViewModel.shownCharacter, spellsList[position])
            }
            else {
                spellsHandler.removeSpell(characterViewModel.shownCharacter, spellsList[position])
            }
            preparedSpellsCountTextView.text = spellsHandler.getPreparedSpellsCount(characterViewModel.shownCharacter).toString()
            preparedCantripsCountTextView.text = spellsHandler.getPreparedCantripsCount(characterViewModel.shownCharacter).toString()
        }
    }
}