package com.andreyyurko.dnd.ui.showcharacterfragments.notes

import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.data.characterData.Note
import com.andreyyurko.dnd.utils.CharacterViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotesViewModel @Inject constructor(
    private val characterViewModel: CharacterViewModel
) : ViewModel() {

    private var openedNotePosition: Int = 0
    private var isNewNote = false

    fun createNewNote() {
        val notesSize = characterViewModel.getCharacter().notes.size
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss")
        characterViewModel.getCharacter().notes.add(
            Note(
                createDate = "Last modified: " + LocalDateTime.now().format(formatter),
                title = "Записка номер ${notesSize + 1}",
                text = "",
                lastModifiedDate = LocalDateTime.now().format(formatter)
            )
        )
        openedNotePosition = notesSize
        isNewNote = true
        characterViewModel.updateCharacterInfo()
    }

    fun openNote(position: Int) {
        openedNotePosition = position
        isNewNote = false
    }

    fun getNotes(substring: String = ""): List<NoteDescription> {
        val notes = characterViewModel.getCharacter().notes

        val result: MutableList<NoteDescription> = mutableListOf()
        for (i in 0 until notes.size) {
            val note = notes[i]
            if ((note.title + note.text).contains(substring))
                result.add(
                    NoteDescription(
                        note = note,
                        position = i
                    )
                )
        }

        return sortNotesByCreateDate(result)
    }

    private fun sortNotesByCreateDate(notesDescription: List<NoteDescription>): List<NoteDescription> {
        val comparator = Comparator { first: NoteDescription, second: NoteDescription ->
            first.note.createDate.compareTo(second.note.createDate) * -1
        }
        return notesDescription.sortedWith(comparator)
    }

    private fun sortByModifiedDate(notesDescription: List<NoteDescription>) {
        val comparator = Comparator { first: NoteDescription, second: NoteDescription ->
            first.note.lastModifiedDate.compareTo(second.note.lastModifiedDate) * -1
        }
        notesDescription.sortedWith(comparator)
    }

    fun getOpenedNote(): Note {
        return characterViewModel.getCharacter().notes[openedNotePosition]
    }

    fun saveNote(note: Note) {
        characterViewModel.getCharacter().notes[openedNotePosition] = note
        characterViewModel.updateCharacterInfo()
    }

    fun cancelEditing() {
        if (isNewNote)
            characterViewModel.getCharacter().notes.removeAt(openedNotePosition)
    }

    fun removeNote(position: Int) {
        characterViewModel.getCharacter().notes.removeAt(position)
        characterViewModel.updateCharacterInfo()
    }

    data class NoteDescription(
        val note: Note,
        val position: Int
    )

}