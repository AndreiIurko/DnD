package com.andreyyurko.dnd.ui.charactermain

import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.utils.CharacterViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterMainViewModel @Inject constructor(
    private val characterViewModel: CharacterViewModel
) : ViewModel() {
    val shownCharacter = characterViewModel.shownCharacter
}