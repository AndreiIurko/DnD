package com.andreyyurko.dnd.data.characters


data class Character(
    var characterInfo: CharacterAbilitySet,
    var abilities: List<CharacterAbility> = emptyList()
)