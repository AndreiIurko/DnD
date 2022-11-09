package com.andreyyurko.dnd.ui.characterlist

import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.data.characters.CharacterBriefInfo
import com.andreyyurko.dnd.utils.CharacterViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val characterViewModel: CharacterViewModel
) : ViewModel() {
    var charactersBriefInfo = characterViewModel.charactersBriefInfo
}