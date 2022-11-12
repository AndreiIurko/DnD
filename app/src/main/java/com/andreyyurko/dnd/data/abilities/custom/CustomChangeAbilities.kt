package com.andreyyurko.dnd.data.abilities.custom

import com.andreyyurko.dnd.data.characters.CharacterAbility
import com.andreyyurko.dnd.data.characters.CharacterInfo

class CustomChangeAbilities : CharacterAbility() {

    override var characterInfo: CharacterInfo = CharacterInfo()

    override val nextAbilities: MutableList<CharacterAbility>
        get() = mutableListOf()
    override val id: Int
        get() = 2
}