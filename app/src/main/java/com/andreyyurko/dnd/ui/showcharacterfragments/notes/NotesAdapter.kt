package com.andreyyurko.dnd.ui.showcharacterfragments.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.andreyyurko.dnd.R

class NotesAdapter(
    val notesViewModel: NotesViewModel,
    private val navController: NavController
) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    var notes: List<NotesViewModel.NoteDescription> = listOf()
    var searchString: String = ""

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        val noteTextView: TextView = itemView.findViewById(R.id.noteTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_notes_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.dateTextView.text = notes[position].note.lastModifiedDate
        holder.nameTextView.text = notes[position].note.title

        holder.noteTextView.text =
            if (notes[position].note.text.length > 100)
                notes[position].note.text.substring(0..100) + "..."
            else
                notes[position].note.text

        holder.nameTextView.setOnClickListener {
            editNote(notes[position].position, navController)
        }
        holder.noteTextView.setOnClickListener {
            editNote(notes[position].position, navController)
        }
        holder.dateTextView.setOnClickListener {
            editNote(notes[position].position, navController)
        }

        holder.deleteButton.setOnClickListener {
            notesViewModel.removeNote(notes[position].position)
            notes = notesViewModel.getNotes(searchString)
            notifyDataSetChanged()
        }
    }

    private fun editNote(position: Int, navController: NavController) {
        notesViewModel.openNote(position)
        navController.navigate(R.id.characterCreateNoteFragment)
    }
}