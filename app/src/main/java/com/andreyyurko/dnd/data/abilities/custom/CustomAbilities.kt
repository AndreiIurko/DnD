package com.andreyyurko.dnd.data.abilities.custom

import com.andreyyurko.dnd.data.characters.CharacterAbility
import com.andreyyurko.dnd.data.characters.CharacterInfo

class CustomAbilities : CharacterAbility() {

    var customChangeAbilities = CustomChangeAbilities()

    override val  nextAbilities: MutableList<CharacterAbility>
        get() = mutableListOf(customChangeAbilities)
    override val id: Int
        get() = 1

    override var characterInfo: CharacterInfo = CharacterInfo()
}