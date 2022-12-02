package com.andreyyurko.dnd.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

interface Properties {
    val properties: List<String>
}

data class SpellSpecificLanguage(
    val name: String = "",
    val engName: String = "",
    val level: String = "",
    val text: String = "",
    val school: String = "",
    val castingTime: String = "",
    val range: String = "",
    val materials: String = "",
    val components: String = "",
    val concentration: String = "",
    val duration: String = "",
    val source: String = ""
) : Properties {
    override val properties get() = listOf(name, engName, "$level уровень", text, school, castingTime, range, materials, components, concentration, duration, source)
}