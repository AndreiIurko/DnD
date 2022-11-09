package com.andreyyurko.dnd.data.abilities.custom

import com.andreyyurko.dnd.data.characters.CharacterAbility
import com.andreyyurko.dnd.data.characters.CharacterAbilitySet

class CustomSetAbilities : CharacterAbility() {

    var name : String = ""
    var maxHitPoints : Int = 0
    var profBonus : Int = 0
    var speed : Int = 30
    var initiativeBonus : Int = 0
    var ac : Int = 10
    var strength : Int = 10
    var dexterity : Int = 10
    var constitution : Int = 10
    var intelligence : Int = 10
    var wisdom : Int = 10
    var charisma : Int = 10
    override fun invoke(abilitySet: CharacterAbilitySet): CharacterAbilitySet {
        abilitySet.name = name
        abilitySet.hp = maxHitPoints
        abilitySet.proficiencyBonus = profBonus
        abilitySet.speed = speed
        abilitySet.initiativeBonus = initiativeBonus
        abilitySet.ac = ac
        abilitySet.strength = strength
        abilitySet.dexterity = dexterity
        abilitySet.constitution = constitution
        abilitySet.intelligence = intelligence
        abilitySet.wisdom = wisdom
        abilitySet.charisma = charisma
        return abilitySet
    }

    override val nextAbilities: MutableList<CharacterAbility>
        get() = mutableListOf()
    override val id: Int
        get() = 2
}