package com.andreyyurko.dnd.data.characterData

import android.graphics.Bitmap


data class CharacterBriefInfo(
    val id: Int,
    val name: String = "Марцелл",
    val characterClass: String = "Чародей",
    val level: String = "5",
    val bitmap: Bitmap? = null
)