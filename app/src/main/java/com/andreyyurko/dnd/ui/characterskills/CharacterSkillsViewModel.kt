package com.andreyyurko.dnd.ui.characterskills

import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.data.characters.Ability
import com.andreyyurko.dnd.data.characters.Skill
import com.andreyyurko.dnd.utils.CharacterViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class CharacterSkillsViewModel @Inject constructor(
    private val characterViewModel: CharacterViewModel
) : ViewModel() {
    private val shownCharacter = characterViewModel.shownCharacter

    var skillsList = Skill.values().toList()
    var skillsProf = skillsList.associateWith { Random.nextInt(0, 4) }

    fun getBonus(skill: Skill): Int {
        var profBonus = shownCharacter.characterInfo.proficiencyBonus
        when (skillsProf[skill]) {
            0 -> profBonus = 0
            1 -> profBonus /= 2
            2 -> {}
            3 -> profBonus *= 2
        }
        return (when (skill.ability) {
            Ability.Strength -> shownCharacter.characterInfo.strengthBonus
            Ability.Dexterity -> shownCharacter.characterInfo.dexterityBonus
            Ability.Constitution -> shownCharacter.characterInfo.constitutionBonus
            Ability.Intelligence -> shownCharacter.characterInfo.intelligenceBonus
            Ability.Wisdom -> shownCharacter.characterInfo.wisdomBonus
            Ability.Charisma -> shownCharacter.characterInfo.charismaBonus
        } - 10) / 2 + profBonus
    }

    fun getProf(skill: Skill): Int? {
        //TODO("add half prof")
        /*if (skill in shownCharacter.characterInfo.expertize) return 3
        if (skill in shownCharacter.characterInfo.skillProficiency) return 2
        return 0*/
        return skillsProf[skill]
    }
}