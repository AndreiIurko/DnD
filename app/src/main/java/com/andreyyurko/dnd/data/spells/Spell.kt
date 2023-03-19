package com.andreyyurko.dnd.data.spells

import com.andreyyurko.dnd.data.SpellSpecificLanguage

data class Spell(
    val data: SpellSpecificLanguage,
    val listName: String,
    var isAlwaysIncluded: Boolean
)