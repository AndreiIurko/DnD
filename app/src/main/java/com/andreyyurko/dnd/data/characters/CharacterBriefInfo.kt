package com.andreyyurko.dnd.data.characters


data class CharacterBriefInfo(
    val id: Int,
    val name: String = "Марцелл",
    val characterClass: String = "Чародей",
    val level: String = "5",
)