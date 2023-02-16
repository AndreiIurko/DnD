package com.andreyyurko.dnd.data.spells

data class CharacterSpells(
    var className: String = "",
    var spellLists: SpellLists = SpellLists(),
    // -1 means we know all spells from the list
    var maxKnownSpellsCount: Int = -1,
    var maxPreparedSpellsCount: Int = 0,
    var maxPreparedCantripsCount: Int = 0
)

data class SpellLists(
    var knownSpells: MutableSet<String> = mutableSetOf(),
    var preparedSpells: MutableSet<String> = mutableSetOf(),
    var preparedCantrips: MutableSet<String> = mutableSetOf(),
)