package com.andreyyurko.dnd.data.characters


data class Character(
    var id: Int,
    var characterInfo: CharacterAbilitySet,
    var abilities: List<CharacterAbility> = emptyList()
)