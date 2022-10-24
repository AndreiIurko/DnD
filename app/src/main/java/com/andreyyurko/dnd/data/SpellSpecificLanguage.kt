package com.andreyyurko.dnd.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


data class SpellSpecificLanguage(
    val name: String = "",
    val level: String = "",
    val text: String = "",
    val school: String = "",
    val castingTime: String = "",
    val range: String = "",
    val materials: String = "",
    val components: String = "",
    val duration: String = "",
    val source: String = ""
)