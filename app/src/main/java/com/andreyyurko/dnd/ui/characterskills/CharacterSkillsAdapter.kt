package com.andreyyurko.dnd.ui.characterskills

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.data.characters.Skill
import javax.inject.Inject

class CharacterSkillsAdapter @Inject constructor(
    private val viewModel: CharacterSkillsViewModel
) : RecyclerView.Adapter<CharacterSkillsAdapter.ViewHolder>() {

    var skillsList = Skill.values().toList()
    var skillsMap = skillsList.associateWith { viewModel.getSkill(it) }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val skillNameTextView: TextView = itemView.findViewById(R.id.skillNameTextView)
        val skillModifierTextView: TextView = itemView.findViewById(R.id.skillModifierTextView)
        val context: Context = itemView.context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.skill_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.skillNameTextView.text = skillsList[position].skillName
        holder.skillModifierTextView.text = skillsMap[skillsList[position]].toString()
    }

    override fun getItemCount(): Int {
        return skillsList.size
    }

    companion object {
        const val LOG_TAG = "CharacterSkillsAdapter"
    }
}