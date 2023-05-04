package com.andreyyurko.dnd.data.abilities.other

import com.andreyyurko.dnd.data.characterData.CharacterInfo
import com.andreyyurko.dnd.data.characterData.Priority
import com.andreyyurko.dnd.data.characterData.Tools
import com.andreyyurko.dnd.data.characterData.character.AbilityNode

var alchemistsSupplies: AbilityNode = AbilityNode(
    name = "Инструменты алхимика",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.toolProficiency.add(Tools.AlchemistsSupplies)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.toolProficiency.contains(Tools.AlchemistsSupplies)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var brewersSupplies: AbilityNode = AbilityNode(
    name = "Инструменты пивовара",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.toolProficiency.add(Tools.BrewersSupplies)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.toolProficiency.contains(Tools.BrewersSupplies)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var calligraphersSupplies: AbilityNode = AbilityNode(
    name = "Инструменты каллиграфа",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.toolProficiency.add(Tools.CalligraphersSupplies)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.toolProficiency.contains(Tools.CalligraphersSupplies)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var carpentersTools: AbilityNode = AbilityNode(
        name = "Инструменты плотника",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.toolProficiency.add(Tools.CarpentersTools)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.toolProficiency.contains(Tools.CarpentersTools)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var cartographersTools: AbilityNode = AbilityNode(
    name = "Инструменты картографа",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.toolProficiency.add(Tools.CartographersTools)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.toolProficiency.contains(Tools.CartographersTools)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var cobblersTools: AbilityNode = AbilityNode(
    name = "Инструменты сапожника",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.toolProficiency.add(Tools.CobblersTools)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.toolProficiency.contains(Tools.CobblersTools)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var cooksUtensils: AbilityNode = AbilityNode(
    name = "Инструменты повара",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.toolProficiency.add(Tools.CooksUtensils)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.toolProficiency.contains(Tools.CooksUtensils)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var glassblowersTools: AbilityNode = AbilityNode(
    name = "Инструменты стеклодува",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.toolProficiency.add(Tools.GlassblowersTools)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.toolProficiency.contains(Tools.GlassblowersTools)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var jewelersTools: AbilityNode = AbilityNode(
    name = "Инструменты ювелира",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.toolProficiency.add(Tools.JewelersTools)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.toolProficiency.contains(Tools.JewelersTools)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var leatherworkersTools: AbilityNode = AbilityNode(
    name = "Инструменты кожевника",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.toolProficiency.add(Tools.LeatherworkersTools)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.toolProficiency.contains(Tools.LeatherworkersTools)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var masonsTools: AbilityNode = AbilityNode(
    name = "Инструменты каменщика",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.toolProficiency.add(Tools.MasonsTools)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.toolProficiency.contains(Tools.MasonsTools)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var paintersSupplies: AbilityNode = AbilityNode(
    name = "Инструменты художника",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.toolProficiency.add(Tools.PaintersSupplies)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.toolProficiency.contains(Tools.PaintersSupplies)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var pottersTools: AbilityNode = AbilityNode(
    name = "Инструменты гончара",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.toolProficiency.add(Tools.PottersTools)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.toolProficiency.contains(Tools.PottersTools)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var smithsTools: AbilityNode = AbilityNode(
    name = "Инструменты кузнеца",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.toolProficiency.add(Tools.SmithsTools)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.toolProficiency.contains(Tools.SmithsTools)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var tinkersTools: AbilityNode = AbilityNode(
    name = "Инструменты ремонтника",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.toolProficiency.add(Tools.TinkersTools)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.toolProficiency.contains(Tools.TinkersTools)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var weaversTools: AbilityNode = AbilityNode(
    name = "Инструменты ткача",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.toolProficiency.add(Tools.WeaversTools)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.toolProficiency.contains(Tools.WeaversTools)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var woodcarversTools: AbilityNode = AbilityNode(
    name = "Инструменты резчика по дереву",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.toolProficiency.add(Tools.WoodcarversTools)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.toolProficiency.contains(Tools.WoodcarversTools)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var diceSet: AbilityNode = AbilityNode(
    name = "Кости",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.toolProficiency.add(Tools.DiceSet)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.toolProficiency.contains(Tools.DiceSet)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var playingCards: AbilityNode = AbilityNode(
    name = "Игральные карты",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.toolProficiency.add(Tools.PlayingCards)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.toolProficiency.contains(Tools.PlayingCards)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var threeDragonAnte: AbilityNode = AbilityNode(
    name = "Ставка трёх драконов",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.toolProficiency.add(Tools.ThreeDragonAnte)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.toolProficiency.contains(Tools.ThreeDragonAnte)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var dragonChess: AbilityNode = AbilityNode(
    name = "Драконьи шахматы",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.toolProficiency.add(Tools.DragonChess)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.toolProficiency.contains(Tools.DragonChess)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var bagpipes: AbilityNode = AbilityNode(
    name = "Волынка",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.toolProficiency.add(Tools.Bagpipes)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.toolProficiency.contains(Tools.Bagpipes)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var drum: AbilityNode = AbilityNode(
    name = "Барабан",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.toolProficiency.add(Tools.Drum)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.toolProficiency.contains(Tools.Drum)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var dulcimer: AbilityNode = AbilityNode(
    name = "Цимбалы",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.toolProficiency.add(Tools.Dulcimer)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.toolProficiency.contains(Tools.Dulcimer)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var flute: AbilityNode = AbilityNode(
    name = "Флейта",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.toolProficiency.add(Tools.Flute)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.toolProficiency.contains(Tools.Flute)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)


var lute: AbilityNode = AbilityNode(
    name = "Лютня",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.toolProficiency.add(Tools.Lute)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.toolProficiency.contains(Tools.Lute)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var lyre: AbilityNode = AbilityNode(
    name = "Лира",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.toolProficiency.add(Tools.Lyre)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.toolProficiency.contains(Tools.Lyre)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var horn: AbilityNode = AbilityNode(
    name = "Рожок",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.toolProficiency.add(Tools.Horn)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.toolProficiency.contains(Tools.Horn)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var panFlute: AbilityNode = AbilityNode(
    name = "Флейта Пана",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.toolProficiency.add(Tools.PanFlute)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.toolProficiency.contains(Tools.PanFlute)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var shawm: AbilityNode = AbilityNode(
    name = "Шалмей",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.toolProficiency.add(Tools.Shawm)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.toolProficiency.contains(Tools.Shawm)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var viol: AbilityNode = AbilityNode(
    name = "Виола",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.toolProficiency.add(Tools.Viol)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.toolProficiency.contains(Tools.Viol)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var thievesTools: AbilityNode = AbilityNode(
    name = "Воровские инструменты",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.toolProficiency.add(Tools.ThievesTools)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.toolProficiency.contains(Tools.ThievesTools)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var disguiseTools: AbilityNode = AbilityNode(
    name = "Набор для грима",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.toolProficiency.add(Tools.DisguiseTools)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.toolProficiency.contains(Tools.DisguiseTools)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var forgery: AbilityNode = AbilityNode(
    name = "Набор для фальсификации",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.toolProficiency.add(Tools.Forgery)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.toolProficiency.contains(Tools.Forgery)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var poisonerTools: AbilityNode = AbilityNode(
    name = "Инструменты отравителя",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.toolProficiency.add(Tools.PoisonerTools)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.toolProficiency.contains(Tools.PoisonerTools)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var herbalismTools: AbilityNode = AbilityNode(
    name = "Инструменты травника",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.toolProficiency.add(Tools.HerbalismTools)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.toolProficiency.contains(Tools.HerbalismTools)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var navigatorTools: AbilityNode = AbilityNode(
    name = "Инструменты навигатора",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.toolProficiency.add(Tools.NavigatorTools)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.toolProficiency.contains(Tools.NavigatorTools)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var thievesToolsExpertise: AbilityNode = AbilityNode(
    name = "Воровские инструменты, экспертиза",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.toolExpertise.add(Tools.ThievesTools)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.toolExpertise.contains(Tools.ThievesTools) and
            abilities.toolProficiency.contains(Tools.ThievesTools)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var mapOfTools = mutableMapOf(
    Pair(alchemistsSupplies.name, alchemistsSupplies),
    Pair(brewersSupplies.name, brewersSupplies),
    Pair(calligraphersSupplies.name, calligraphersSupplies),
    Pair(carpentersTools.name, carpentersTools),
    Pair(cartographersTools.name, cartographersTools),
    Pair(cobblersTools.name, cobblersTools),
    Pair(cooksUtensils.name, cooksUtensils),
    Pair(glassblowersTools.name, glassblowersTools),
    Pair(jewelersTools.name, jewelersTools),
    Pair(leatherworkersTools.name, leatherworkersTools),
    Pair(masonsTools.name, masonsTools),
    Pair(paintersSupplies.name, paintersSupplies),
    Pair(pottersTools.name, pottersTools),
    Pair(smithsTools.name, smithsTools),
    Pair(tinkersTools.name, tinkersTools),
    Pair(weaversTools.name, weaversTools),
    Pair(woodcarversTools.name, woodcarversTools),
    Pair(diceSet.name, diceSet),
    Pair(playingCards.name, playingCards),
    Pair(threeDragonAnte.name, threeDragonAnte),
    Pair(dragonChess.name, dragonChess),
    Pair(bagpipes.name, bagpipes),
    Pair(drum.name, drum),
    Pair(dulcimer.name, dulcimer),
    Pair(flute.name, flute),
    Pair(lute.name, lute),
    Pair(lyre.name, lyre),
    Pair(horn.name, horn),
    Pair(panFlute.name, panFlute),
    Pair(shawm.name, shawm),
    Pair(viol.name, viol),
    Pair(thievesTools.name, thievesTools),
    Pair(disguiseTools.name, disguiseTools),
    Pair(forgery.name, forgery),
    Pair(poisonerTools.name, poisonerTools),
    Pair(herbalismTools.name, herbalismTools),
    Pair(navigatorTools.name, navigatorTools)
)

var mapOfToolsExpertise = mutableMapOf(
    Pair(thievesToolsExpertise.name, thievesToolsExpertise)
)