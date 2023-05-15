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
    private val shownCharacter = characterViewModel.getCharacter()

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

    private fun getProfBonusFromCharacter(skill: Skill): Int {
        return if (shownCharacter.characterInfo.expertize.contains(skill)) {
            3
        } else if (shownCharacter.characterInfo.skillProficiency.contains(skill)) {
            2
        } else if (shownCharacter.characterInfo.halfProfSet.contains(skill.ability)) {
            1
        } else {
            0
        }
    }

    //TODO change this function...
    fun getListOfSkills(): MutableList<SkillInfo> {
        val result: MutableList<SkillInfo> = mutableListOf()

        for (skill in skillsList) {
            result.add(
                SkillInfo(
                    skillName = skill.skillName,
                    skillProf = skillsProf[skill]!!,
                    skillAbility = skill.ability,
                    skillBonus = getBonus(skill)
                )
            )
        }

        val savingThroughsNames = listOf(
            "Спасбросок силы", "Спасбросок ловкости", "Спасбросок телосложения",
            "Спасбросок интеллекта", "Спасбросок мудрости", "Спасбросок харизмы"
        )

        for (ability in Ability.values()) {
            when (ability) {
                Ability.Strength -> {
                    var bonus = abilityToModifier(shownCharacter.characterInfo.strength)
                    var skillProf = 0
                    if (shownCharacter.characterInfo.savingThrowProf.contains(ability)) {
                        bonus += shownCharacter.characterInfo.proficiencyBonus
                        skillProf = 2
                    }
                    result.add(
                        SkillInfo(
                            skillName = savingThroughsNames[0],
                            skillProf = skillProf,
                            skillAbility = ability,
                            skillBonus = bonus
                        )
                    )
                }

                Ability.Dexterity -> {
                    var bonus = abilityToModifier(shownCharacter.characterInfo.dexterity)
                    var skillProf = 0
                    if (shownCharacter.characterInfo.savingThrowProf.contains(ability)) {
                        bonus += shownCharacter.characterInfo.proficiencyBonus
                        skillProf = 2
                    }
                    result.add(
                        SkillInfo(
                            skillName = savingThroughsNames[1],
                            skillProf = skillProf,
                            skillAbility = ability,
                            skillBonus = bonus
                        )
                    )
                }

                Ability.Constitution -> {
                    var bonus = abilityToModifier(shownCharacter.characterInfo.constitution)
                    var skillProf = 0
                    if (shownCharacter.characterInfo.savingThrowProf.contains(ability)) {
                        bonus += shownCharacter.characterInfo.proficiencyBonus
                        skillProf = 2
                    }
                    result.add(
                        SkillInfo(
                            skillName = savingThroughsNames[2],
                            skillProf = skillProf,
                            skillAbility = ability,
                            skillBonus = bonus
                        )
                    )
                }

                Ability.Intelligence -> {
                    var bonus = abilityToModifier(shownCharacter.characterInfo.intelligence)
                    var skillProf = 0
                    if (shownCharacter.characterInfo.savingThrowProf.contains(ability)) {
                        bonus += shownCharacter.characterInfo.proficiencyBonus
                        skillProf = 2
                    }
                    result.add(
                        SkillInfo(
                            skillName = savingThroughsNames[3],
                            skillProf = skillProf,
                            skillAbility = ability,
                            skillBonus = bonus
                        )
                    )
                }

                Ability.Wisdom -> {
                    var bonus = abilityToModifier(shownCharacter.characterInfo.wisdom)
                    var skillProf = 0
                    if (shownCharacter.characterInfo.savingThrowProf.contains(ability)) {
                        bonus += shownCharacter.characterInfo.proficiencyBonus
                        skillProf = 2
                    }
                    result.add(
                        SkillInfo(
                            skillName = savingThroughsNames[4],
                            skillProf = skillProf,
                            skillAbility = ability,
                            skillBonus = bonus
                        )
                    )
                }

                Ability.Charisma -> {
                    var bonus = abilityToModifier(shownCharacter.characterInfo.charisma)
                    var skillProf = 0
                    if (shownCharacter.characterInfo.savingThrowProf.contains(ability)) {
                        bonus += shownCharacter.characterInfo.proficiencyBonus
                        skillProf = 2
                    }
                    result.add(
                        SkillInfo(
                            skillName = savingThroughsNames[5],
                            skillProf = skillProf,
                            skillAbility = ability,
                            skillBonus = bonus
                        )
                    )
                }
            }
        }

        return result
    }

    data class SkillInfo(
        val skillName: String,
        val skillProf: Int,
        val skillAbility: Ability,
        val skillBonus: Int
    )

}