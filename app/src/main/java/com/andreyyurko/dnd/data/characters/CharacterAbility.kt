package com.andreyyurko.dnd.data.characters

import com.squareup.moshi.JsonAdapter

abstract class CharacterAbility {
    abstract fun invoke(abilitySet: CharacterAbilitySet) : CharacterAbilitySet

    abstract val nextAbilities : MutableList<CharacterAbility>

    //TODO: разобраться с сохранением ability по отдельности
}