package com.andreyyurko.dnd.data.spells

import com.andreyyurko.dnd.data.characterData.Classes

class SpellInfo(
    val name: String,
    val level: Int,
    val classes: Set<Classes>
)

var spellist: List<SpellInfo> = listOf(
    SpellInfo("Адское возмездие", 1, setOf(Classes.Warlock)),
    SpellInfo("Антипатия/симпатия", 8, setOf(Classes.Wizard, Classes.Druid)),
    SpellInfo("Башня Галдера", 3, setOf(Classes.Wizard)),
    SpellInfo("Безмолвный образ", 1, setOf(Classes.Bard, Classes.Wizard, Classes.Sorcerer)),
    SpellInfo("Бесследное передвижение", 2, setOf(Classes.Druid, Classes.Ranger)),
    SpellInfo("Магические врата", 6, setOf(Classes.Wizard, Classes.Warlock, Classes.Sorcerer)),
    SpellInfo("Ментальная тюрьма", 6, setOf(Classes.Wizard, Classes.Warlock, Classes.Sorcerer)),
)