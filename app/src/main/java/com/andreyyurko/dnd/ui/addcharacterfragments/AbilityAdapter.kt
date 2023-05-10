package com.andreyyurko.dnd.ui.addcharacterfragments

import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.data.characterData.character.CharacterAbilityNode
import com.andreyyurko.dnd.utils.setupBasicPopUpMenu
import kotlin.math.min

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
        if (abilityCAN.data.getAlternatives.isNotEmpty()) {
            // this line makes this ability view not reusable
            holder.parent.setHasTransientState(true)

            for (optionName in abilityCAN.data.getAlternatives.keys) {
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
                abilityCAN.chosenAlternativesForActions[optionName]?.let {
                    textView.text = it
                }

                // on press show options
                choiceButton.setOnClickListener {
                    createPopUpMenu(choiceButton, textView, optionsList, optionName, abilityCAN)
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

    // triggerView - button beneath which we create menu
    // textView - text inside button
    // listOfOptions - list of ability names
    // optionName - name of list of options in can
    // can - parent CharacterAbilityNode
    private fun createPopUpMenu(
        triggerView: View,
        textView: TextView,
        listOfOptions: List<String>,
        optionName: String,
        can: CharacterAbilityNode,
    ): PopupWindow {
        // parent - linear layout inside popup menu
        val maxHeight = min(24 * listOfOptions.size, 200)
        val (popupChoiceList, parent) = setupBasicPopUpMenu(triggerView.context, maxHeight)
        // for every ability we need to add it inside linear layout
        for (choice in listOfOptions) {
            // configure textView
            val choiceTextView = TextView(triggerView.context)
            choiceTextView.isClickable = true
            choiceTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textView.textSize)
            choiceTextView.text = choice
            // add textView in linear layout
            parent.addView(choiceTextView)
            // on press, we need to make choice, close menu and tell adapter that we need reload all abilities
            // because we are automatically making choice if option list size == 1 we can insert more when 1 ability
            // so, we need to call notifyDataSetChanged
            choiceTextView.setOnClickListener {
                can.makeChoice(optionName, choice)
                popupChoiceList.dismiss()
                notifyDataSetChanged()
            }
        }
        // configure location of popup menu and show it
        val location = IntArray(2)
        triggerView.getLocationOnScreen(location)
        parent.measure(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        popupChoiceList.showAtLocation(
            triggerView,
            Gravity.NO_GRAVITY,
            location[0] + triggerView.width / 2 - parent.measuredWidth / 2,
            location[1] + textView.height + (textView.context.resources.displayMetrics.density * 6).toInt())
        return popupChoiceList
    }
}

// parentName - имя способности в map
data class AbilityCard(
    var name: String = "",
    var parentDescription: String = "",
    var description: String = "",
    var can: CharacterAbilityNode
)