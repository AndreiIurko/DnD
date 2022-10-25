package com.andreyyurko.dnd.ui.spellslist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.data.SpellSpecificLanguage

class SpellsListAdapter : RecyclerView.Adapter<SpellsListAdapter.ViewHolder>() {

    var spellsList : List<SpellSpecificLanguage> = emptyList()
    var shownSpellList : List<SpellSpecificLanguage> = emptyList()

    var searchQuery = ""

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val spellNameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val spellLevelAndSchoolTextView: TextView = itemView.findViewById(R.id.levelAndSchoolTextView)
        val spellDescriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        val context: Context = itemView.context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.spell, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.spellNameTextView.text = holder.context.getString(
            R.string.spell_name,
            HtmlCompat.fromHtml(shownSpellList[position].name, HtmlCompat.FROM_HTML_MODE_LEGACY)
        )
        holder.spellLevelAndSchoolTextView.text =  holder.context.getString(
            R.string.spell_level_and_school,
            HtmlCompat.fromHtml(shownSpellList[position].level, HtmlCompat.FROM_HTML_MODE_LEGACY),
            HtmlCompat.fromHtml(shownSpellList[position].school, HtmlCompat.FROM_HTML_MODE_LEGACY)
        )
        holder.spellDescriptionTextView.text = HtmlCompat.fromHtml(shownSpellList[position].text, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

    override fun getItemCount(): Int {
        return shownSpellList.size
    }

    fun setShownSpellList(newSearchQuery: String = searchQuery) {
        shownSpellList = spellsList
        if (newSearchQuery != searchQuery) {
            searchQuery = newSearchQuery
            shownSpellList = filterBySearch(shownSpellList, searchQuery)
        }
    }

}