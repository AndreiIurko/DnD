package com.andreyyurko.dnd.utils

import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.data.characters.CharacterBriefInfo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterViewModel @Inject constructor(
    private val charactersHolder: CharactersHolder
) : ViewModel() {
    var charactersBriefInfo = listOf<CharacterBriefInfo>(
        CharacterBriefInfo("Регбер", "Следопыт и жрец", "10"),
        CharacterBriefInfo(),
        CharacterBriefInfo("Дик Райдер", "Воин", "9")
    )
}