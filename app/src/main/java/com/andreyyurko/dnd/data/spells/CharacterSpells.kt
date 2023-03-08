package com.andreyyurko.dnd.data.spells

data class CharacterSpells(
    var className: String = "",
    var spellLists: SpellLists = SpellLists(),
    // -1 means we know all spells from the list
    var maxKnownSpellsCount: Int = -1,
    var maxKnownCantripsCount: Int = -1,
    // -1 means all known spells are prepared
    var maxPreparedSpellsCount: Int = -1,
    var maxPreparedCantripsCount: Int = -1
)

data class SpellLists(
    var knownSpells: MutableSet<String> = mutableSetOf(),
    var knownCantrips: MutableSet<String> = mutableSetOf(),
    var preparedSpells: MutableSet<String> = mutableSetOf(),
    var preparedCantrips: MutableSet<String> = mutableSetOf(),
)