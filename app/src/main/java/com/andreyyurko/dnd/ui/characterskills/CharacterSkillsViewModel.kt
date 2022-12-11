package com.andreyyurko.dnd.ui.characterskills

import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.data.characters.Skill
import com.andreyyurko.dnd.utils.CharacterViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterSkillsViewModel @Inject constructor(
    private val characterViewModel: CharacterViewModel
) : ViewModel() {
    private val shownCharacter = characterViewModel.shownCharacter

    //private var skills: MutableList<Skill> = Skill.values().toMutableList()

    fun getSkill(skill: Skill): Int {
        //TODO("get skills from shownCharacter")
        return 0
    }
}