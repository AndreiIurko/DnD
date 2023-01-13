package com.andreyyurko.dnd.data.characterData


data class CharacterBriefInfo(
    val id: Int,
    val name: String = "Марцелл",
    val characterClass: String = "Чародей",
    val level: String = "5",
)