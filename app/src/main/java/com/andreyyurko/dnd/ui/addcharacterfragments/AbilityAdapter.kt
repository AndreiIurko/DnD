package com.andreyyurko.dnd.ui.addcharacterfragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.data.characterData.character.CharacterAbilityNode
import com.andreyyurko.dnd.utils.createPopUpMenu

class AbilityAdapter : RecyclerView.Adapter<AbilityAdapter.ViewHolder>() {

    var rootCan: CharacterAbilityNode? = null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.abilityNameTextView)
        val classDescription: TextView = itemView.findViewById(R.id.classDescription)
        val description: TextView = itemView.findViewById(R.id.description)
        val parent = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.class_ability_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        //makeAllSimpleChoice(firstLevelClassCAN)
        return getAbilitiesCount(rootCan)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // classCAN not null because size != 0
        // algorithm is correct, null value for first param needed in getAbilityAtPosition
        val classAbility = getAbilityAtPosition(rootCan!!, position).first!!

        // configure ability card
        holder.nameTextView.text = classAbility.name
        holder.classDescription.text = classAbility.parentDescription
        holder.description.text = classAbility.description
        val abilityCAN = classAbility.can

        // TODO: think about this code. I think it is too complicated
        // how it works:
        // search all available abilities and for each attach to card button
        // button allows to show popup menu on click with choices
        // so we need extract list of choices and setup popup menu
        if (abilityCAN.data.alternatives.isNotEmpty()) {
            // this line makes this ability view not reusable
            holder.parent.setHasTransientState(true)

            for (optionName in abilityCAN.data.alternatives.keys) {
                // extract all available options
                val optionsList = abilityCAN.showOptions(optionName)

                // if we have only one or zero options we don't need popup menu
                if (optionsList.size <= 1) {
                    continue
                }

                // TODO: replace this shitty code with custom view
                // creates a button
                val choiceButton =
                    LayoutInflater.from(holder.parent.context).inflate(R.layout.choose_option_button, null)
                val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
                )
                layoutParams.setMargins(200, 40, 200, 40)
                (holder.parent as LinearLayout).addView(choiceButton, layoutParams)

                // finds textView what shows chosen option
                val textView = choiceButton.findViewById<TextView>(R.id.choiceText)
                abilityCAN.chosen_alternatives[optionName]?.let {
                    textView.text = it.data.name
                }

                // on press show options
                choiceButton.setOnClickListener {
                    createPopUpMenu(choiceButton, textView, optionsList, optionName, abilityCAN, this)
                }
            }
        }
    }

    // run through tree of can and skip if ability is not supposed to be shown
    // this is DFS
    private fun getAbilitiesCount(can: CharacterAbilityNode?, initCount: Int = 0): Int {
        if (can == null) {
            return 0
        }
        var count = initCount
        for ((_, chosenAbility) in can.chosen_alternatives) {
            if (chosenAbility.data.isNeedsToBeShown) {
                count += 1
            }
            count = getAbilitiesCount(chosenAbility, count)
        }
        return count
    }

    // run through tree of can
    // returns can in DFS on pos == position
    private fun getAbilityAtPosition(can: CharacterAbilityNode, initPosition: Int): Pair<AbilityCard?, Int> {
        var position = initPosition
        for ((_, chosenAbility) in can.chosen_alternatives) {
            // skip if ability is not supposed to be shown
            if (chosenAbility.data.isNeedsToBeShown) {
                // if position is zero we find our can
                // if not position decreases by one
                if (position == 0) {
                    // we return the description (AbilityCard) of abilityCard with position = -1
                    return Pair(
                        AbilityCard(
                            name = chosenAbility.data.name,
                            description = chosenAbility.data.description,
                            parentDescription = if (isLevelClassCan(can)) can.data.description else can.data.name,
                            can = chosenAbility
                        ), -1
                    )
                }
                position -= 1
            }
            // position <= -1 means we must return this ability to top level and stop dfs
            getAbilityAtPosition(chosenAbility, position).apply {
                position = this.second
                if (position <= -1) {
                    return Pair(this.first, position)
                }
            }
        }
        return Pair(null, position)
    }

    private fun isLevelClassCan(can: CharacterAbilityNode): Boolean {
        // all level can has name like ClassName_x, there x is level
        return can.data.name.split('_')[0] == rootCan!!.data.name.split('_')[0]
    }
}

// parentName - имя способности в map
data class AbilityCard(
    var name: String = "",
    var parentDescription: String = "",
    var description: String = "",
    var can: CharacterAbilityNode
)