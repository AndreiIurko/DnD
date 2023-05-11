package com.andreyyurko.dnd.ui.showcharacterfragments.createnote

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.data.characterData.Note
import com.andreyyurko.dnd.databinding.FragmentCreateNoteBinding
import com.andreyyurko.dnd.ui.base.BaseFragment
import com.andreyyurko.dnd.ui.showcharacterfragments.notes.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@AndroidEntryPoint
class CreateNoteFragment : BaseFragment(R.layout.fragment_create_note) {

    private val viewBinding by viewBinding(FragmentCreateNoteBinding::bind)

    @Inject
    lateinit var notesViewModel: NotesViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val note = notesViewModel.getOpenedNote()

        viewBinding.titleEditText.setText(note.title)
        viewBinding.notesEditText.setText(note.text)

        viewBinding.deleteButton.setOnClickListener {
            notesViewModel.cancelEditing()
            findNavController().popBackStack()
        }

        viewBinding.saveButton.setOnClickListener {
            val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss")
            notesViewModel.saveNote(
                Note(
                    createDate = note.createDate,
                    title = viewBinding.titleEditText.text.toString(),
                    text = viewBinding.notesEditText.text.toString(),
                    lastModifiedDate = "Last modified: " + LocalDateTime.now().format(formatter)
                )
            )
            findNavController().popBackStack()
        }
    }

}