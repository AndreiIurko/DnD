package com.andreyyurko.dnd.data

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
    val duration: String = "",
    val concentration: String = "",
    val classes: String = "",
    val sources: String = "",
) : Properties {
    override val properties get() = listOf(name, engName, "$level уровень", text, school, castingTime, range, materials, components, concentration, duration, sources, classes)
}