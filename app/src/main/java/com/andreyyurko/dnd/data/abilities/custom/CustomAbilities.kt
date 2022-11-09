package com.andreyyurko.dnd.data.abilities.custom

import com.andreyyurko.dnd.data.characters.CharacterAbility
import com.andreyyurko.dnd.data.characters.CharacterAbilitySet

class CustomAbilities : CharacterAbility() {

    var customSetAbilities = CustomSetAbilities()

    override fun invoke(abilitySet: CharacterAbilitySet): CharacterAbilitySet {
        var newAbilitySet = abilitySet
        for (ability in nextAbilities) {
            newAbilitySet = ability.invoke(newAbilitySet)
        }
        return newAbilitySet
    }

    override val nextAbilities: MutableList<CharacterAbility>
        get() = mutableListOf(customSetAbilities)
    override val id: Int
        get() = 1
}