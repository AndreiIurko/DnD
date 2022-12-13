package com.andreyyurko.dnd.ui.addcharacterfragments.classfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andreyyurko.dnd.R

class ClassAdapter : RecyclerView.Adapter<ClassAdapter.ViewHolder>() {

    var abilitiesList = listOf<ClassAbility>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nameTextView : TextView = itemView.findViewById(R.id.abilityNameTextView)
        val classDescription : TextView = itemView.findViewById(R.id.classDescription)
        val description : TextView = itemView.findViewById(R.id.description)
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
    }
}

data class ClassAbility(
    var name: String = "",
    var classDescription: String = "",
    var description: String = ""
)