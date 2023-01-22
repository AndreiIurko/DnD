package com.andreyyurko.dnd.ui.showcharacterfragments.skills

import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.data.characterData.Ability
import com.andreyyurko.dnd.data.characterData.Skill
import com.andreyyurko.dnd.data.characterData.character.abilityToModifier
import com.andreyyurko.dnd.utils.CharacterViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterSkillsViewModel @Inject constructor(
    private val characterViewModel: CharacterViewModel
) : ViewModel() {
    private val shownCharacter = characterViewModel.shownCharacter

    var skillsList = Skill.values()
    private var skillsProf = skillsList.associateWith { getProfBonusFromCharacter(it) }

    fun getBonus(skill: Skill): Int {
        var profBonus = shownCharacter.characterInfo.proficiencyBonus
        when (skillsProf[skill]) {
            0 -> profBonus = 0
            1 -> profBonus /= 2
            2 -> {}
            3 -> profBonus *= 2
        }
        return when (skill.ability) {
            Ability.Strength -> abilityToModifier(shownCharacter.characterInfo.strength)
            Ability.Dexterity -> abilityToModifier(shownCharacter.characterInfo.dexterity)
            Ability.Constitution -> abilityToModifier(shownCharacter.characterInfo.constitution)
            Ability.Intelligence -> abilityToModifier(shownCharacter.characterInfo.intelligence)
            Ability.Wisdom -> abilityToModifier(shownCharacter.characterInfo.wisdom)
            Ability.Charisma -> abilityToModifier(shownCharacter.characterInfo.charisma)
        } + profBonus
    }

    fun getProf(skill: Skill): Int? {
        //TODO("add half prof")
        /*if (skill in shownCharacter.characterInfo.expertize) return 3
        if (skill in shownCharacter.characterInfo.skillProficiency) return 2
        return 0*/
        return skillsProf[skill]
    }

    private fun getProfBonusFromCharacter(skill: Skill): Int {
        return if (shownCharacter.characterInfo.expertize.contains(skill)) {
            3
        } else if (shownCharacter.characterInfo.skillProficiency.contains(skill)) {
            2
        } else if (shownCharacter.characterInfo.isHasHalfProf) {
            1
        } else {
            0
        }
    }
}