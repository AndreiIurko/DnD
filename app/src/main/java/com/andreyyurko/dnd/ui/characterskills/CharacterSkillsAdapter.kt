package com.andreyyurko.dnd.ui.characterskills

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.data.characters.Skill
import javax.inject.Inject

class CharacterSkillsAdapter @Inject constructor(
    private val viewModel: CharacterSkillsViewModel
) : RecyclerView.Adapter<CharacterSkillsAdapter.ViewHolder>() {

    var skillsList = Skill.values().toList()
    var bonusMap = skillsList.associateWith { viewModel.getBonus(it) }
    var modifierMap = skillsList.associateWith { it.ability.abilityShortName }
    var profMap = skillsList.associateWith { viewModel.getProf(it) }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val skillNameTextView: TextView = itemView.findViewById(R.id.skillNameTextView)
        val skillBonusTextView: TextView = itemView.findViewById(R.id.skillBonusTextView)
        val skillProficiencyImageView: ImageView = itemView.findViewById(R.id.skillProficiencyImageView)
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
        holder.skillBonusTextView.text = bonusMap[skillsList[position]].toString()
        holder.skillModifierTextView.text = modifierMap[skillsList[position]]
        when (profMap[skillsList[position]]) {
            0 -> holder.skillProficiencyImageView.setImageDrawable(ContextCompat.getDrawable(holder.context, R.drawable.prof_none_24))
            1 -> holder.skillProficiencyImageView.setImageDrawable(ContextCompat.getDrawable(holder.context, R.drawable.prof_half_24))
            2 -> holder.skillProficiencyImageView.setImageDrawable(ContextCompat.getDrawable(holder.context, R.drawable.prof_full_24))
            3 -> holder.skillProficiencyImageView.setImageDrawable(ContextCompat.getDrawable(holder.context, R.drawable.prof_exp_24))
        }
    }

    override fun getItemCount(): Int {
        return skillsList.size
    }

    companion object {
        const val LOG_TAG = "CharacterSkillsAdapter"
    }
}