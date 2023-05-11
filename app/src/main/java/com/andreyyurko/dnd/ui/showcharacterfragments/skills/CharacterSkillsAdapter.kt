package com.andreyyurko.dnd.ui.showcharacterfragments.skills

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.andreyyurko.dnd.R
import javax.inject.Inject

class CharacterSkillsAdapter @Inject constructor(
    private val viewModel: CharacterSkillsViewModel
) : RecyclerView.Adapter<CharacterSkillsAdapter.ViewHolder>() {

    private var skillsList = viewModel.getListOfSkills().apply {
        val comparator =
            Comparator { skillFirst: CharacterSkillsViewModel.SkillInfo, skillSecond: CharacterSkillsViewModel.SkillInfo ->
                if (skillFirst.skillAbility != skillSecond.skillAbility) {
                    return@Comparator skillFirst.skillAbility.compareTo(skillSecond.skillAbility)
                }
                if (skillFirst.skillName.contains("Спасбросок"))
                    return@Comparator -1
                if (skillSecond.skillName.contains("Спасбросок"))
                    return@Comparator 1
                skillFirst.skillName.compareTo(skillSecond.skillName)
            }
        this.sortWith(comparator)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val skillNameTextView: TextView = itemView.findViewById(R.id.skillNameTextView)
        val skillBonusTextView: TextView = itemView.findViewById(R.id.skillBonusTextView)
        val skillProficiencyImageView: ImageView = itemView.findViewById(R.id.skillProficiencyImageView)
        val skillModifierTextView: TextView = itemView.findViewById(R.id.skillModifierTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.skill_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.skillNameTextView.text = skillsList[position].skillName
        holder.skillBonusTextView.text =
            if (skillsList[position].skillBonus >= 0)
                "+${skillsList[position].skillBonus}"
            else
                skillsList[position].skillBonus.toString()
        holder.skillModifierTextView.text = skillsList[position].skillAbility.abilityShortName
        when (skillsList[position].skillProf) {
            0 -> holder.skillProficiencyImageView.setImageDrawable(
                ContextCompat.getDrawable(
                    holder.skillProficiencyImageView.context,
                    R.drawable.prof_none_24
                )
            )

            1 -> holder.skillProficiencyImageView.setImageDrawable(
                ContextCompat.getDrawable(
                    holder.skillProficiencyImageView.context,
                    R.drawable.prof_half_24
                )
            )

            2 -> holder.skillProficiencyImageView.setImageDrawable(
                ContextCompat.getDrawable(
                    holder.skillProficiencyImageView.context,
                    R.drawable.prof_full_24
                )
            )

            3 -> holder.skillProficiencyImageView.setImageDrawable(
                ContextCompat.getDrawable(
                    holder.skillProficiencyImageView.context,
                    R.drawable.prof_exp_24
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return skillsList.size
    }

    companion object {
        const val LOG_TAG = "CharacterSkillsAdapter"
    }
}