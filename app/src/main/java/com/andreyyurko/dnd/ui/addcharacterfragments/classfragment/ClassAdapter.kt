package com.andreyyurko.dnd.ui.addcharacterfragments.classfragment

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.data.abilities.characterclass.AbilityNodeLevel
import com.andreyyurko.dnd.data.abilities.characterclass.CharacterAbilityNodeLevel
import com.andreyyurko.dnd.utils.createPopUpMenu

class ClassAdapter : RecyclerView.Adapter<ClassAdapter.ViewHolder>() {

    var abilitiesList = listOf<ClassAbility>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nameTextView : TextView = itemView.findViewById(R.id.abilityNameTextView)
        val classDescription : TextView = itemView.findViewById(R.id.classDescription)
        val description : TextView = itemView.findViewById(R.id.description)
        val parent = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.class_ability_item, parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return abilitiesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameTextView.text = abilitiesList[position].name
        holder.classDescription.text = abilitiesList[position].classDescription
        holder.description.text = abilitiesList[position].description

        Log.d("test", (holder.parent as LinearLayout).childCount.toString())

        // выбор одной способности
        // сама CAN нашей ability
        val abilityCAN = abilitiesList[position].classCAN.chosen_alternatives[abilitiesList[position].parentName]
        abilityCAN?.let {
            // TODO: think about this code. I think it is too complicated
            // how it works:
            // search all available abilities and for each create button
            // extract list of choices and setup popup menu
            // TODO: showOptions
            // TODO: если у нас размер мапа хотя бы 2, то надо не показывать popup меню, а создавать карточку с выбором
            if (it.data.alternatives.isNotEmpty()) {
                // this line makes this ability view not reusable
                holder.parent.setHasTransientState(true)

                for ((optionName, optionsList) in it.data.alternatives.entries) {
                    val choiceButton = LayoutInflater.from(holder.parent.context).inflate(R.layout.choose_option_button, null)
                    // TODO: replace this shitty code with custom view or at least
                    val layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    layoutParams.setMargins(200, 40, 200, 40)
                    holder.parent.addView(choiceButton, layoutParams)

                    val textView = choiceButton.findViewById<TextView>(R.id.choiceText)

                    val can = it
                    choiceButton.setOnClickListener {
                        createPopUpMenu(choiceButton, textView, optionsList, optionName, can)
                    }
                }
            }
        }
    }
}

// parentName - имя способности в map
data class ClassAbility(
    var name: String = "",
    var classDescription: String = "",
    var description: String = "",
    var parentName: String = "",
    var classCAN: CharacterAbilityNodeLevel
)