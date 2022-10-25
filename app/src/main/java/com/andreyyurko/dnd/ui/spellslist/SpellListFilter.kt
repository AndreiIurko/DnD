package com.andreyyurko.dnd.ui.spellslist

import com.andreyyurko.dnd.data.SpellSpecificLanguage

fun filterBySearch(spells: List<SpellSpecificLanguage>, searchQueue: String): MutableList<SpellSpecificLanguage> {
    val modifiedSpellList = mutableListOf<SpellSpecificLanguage>()
    for (spell in spells) {
        for (property in spell.properties) {
            if (property.contains(searchQueue, ignoreCase = true)) {
                modifiedSpellList.add(spell)
                continue
            }
        }
    }
    return modifiedSpellList
}