package com.andreyyurko.dnd.data.abilities.races

import com.andreyyurko.dnd.data.abilities.other.brewersSupplies
import com.andreyyurko.dnd.data.abilities.other.masonsTools
import com.andreyyurko.dnd.data.abilities.other.smithsTools
import com.andreyyurko.dnd.data.characterData.*
import com.andreyyurko.dnd.data.characterData.character.AbilityNode

var mountainDwarf = AbilityNode(
    name = "Горный дварф",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.strength += 2
        abilities.armorProficiency.add(ArmorProf.LightArmor)
        abilities.armorProficiency.add(ArmorProf.MediumArmor)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "Будучи горным дварфом, вы являетесь сильным и выносливым, приспособленным к жизни в суровой местности. Вы довольно высоки (по дварфской мерке), и скорее светлокожи. Щитовые дварфы из северного Фаэруна, а также правящий клан хиларов и благородный клан деваров из Саги о Копье, всё это горные дварфы.\n" +
            "\n" +
            "Увеличение характеристик. Значение вашей Силы увеличивается на 2.\n" +
            "\n" +
            "Владение доспехами дварфов. Вы владеете лёгкими и средними доспехами.\n"
)

var hillDwarf = AbilityNode(
    name = "Холмовой дварф",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.wisdom += 1
        abilities.hp += abilities.level
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "Будучи холмовым дварфом вы обладаете обострёнными чувствами, развитой интуицией и замечательной стойкостью. Золотые дварфы Фаэруна, в их могучем южном королевстве являются холмовыми дварфами, также как и изгнанные нейдары и свихнувшиеся клары из Кринна (мир Саги о Копье).\n" +
            "\n" +
            "Увеличение характеристик. Значение вашей Мудрости увеличивается на 1.\n" +
            "\n" +
            "Дварфская выдержка. Максимальное значение ваших хитов увеличивается на 1, и вы получаете 1 дополнительный хит с каждым новым уровнем.\n"
)

val dwarfAbilities = AbilityNode(
    name = "Способности дварфов",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.constitution += 2
        abilities.languageProficiency.add(Languages.Common)
        abilities.languageProficiency.add(Languages.Dwarvish)
        abilities.speed += 25
        if (abilities.additionalAbilities.contains("Тёмное зрение")) {
            var regex = Regex(" [^ ]* футов")
            abilities.additionalAbilities["Тёмное зрение"]?.let {
                regex.find(it)?.let { parsed ->
                    if (60 > parsed.value.split(" ")[1].toInt()) {
                        abilities.additionalAbilities["Тёмное зрение"] =
                            "Привыкнув к сумраку леса и ночному небу, вы обладаете превосходным зрением в темноте и при тусклом освещении. На расстоянии в 60 футов вы при тусклом освещении можете видеть так, как будто это яркое освещение, и в темноте так, как будто это тусклое освещение. В темноте вы не можете различать цвета, только оттенки серого.\n"
                    }
                }
            }
        } else {
            abilities.additionalAbilities["Тёмное зрение"] =
                "Привыкнув к сумраку леса и ночному небу, вы обладаете превосходным зрением в темноте и при тусклом освещении. На расстоянии в 60 футов вы при тусклом освещении можете видеть так, как будто это яркое освещение, и в темноте так, как будто это тусклое освещение. В темноте вы не можете различать цвета, только оттенки серого.\n"
        }
        abilities.additionalAbilities["Дварфийская устойчивость"] = "Вы совершаете с преимуществом спасброски от яда, и вы получаете сопротивление урону ядом.\n"
        abilities.damageResistances.add(DamageType.Poison)
        abilities.weaponProficiency.add(Weapon.Battleaxe)
        abilities.weaponProficiency.add(Weapon.HandAxe)
        abilities.weaponProficiency.add(Weapon.LightHammer)
        abilities.weaponProficiency.add(Weapon.Warhammer)
        // add tools
        abilities.additionalAbilities["Скорость"] = "Ношение тяжёлых доспехов не снижает вашу скорость.\n"
        abilities.additionalAbilities["Знание камня"] = "Если вы совершаете проверку Интеллекта (История), связанную с происхождением работы по камню, вы считаетесь владеющим навыком История, и добавляете к проверке удвоенный бонус мастерства вместо обычного.\n"
        abilities
    },
    alternatives = mutableMapOf(
        Pair("tool", listOf(brewersSupplies.name, masonsTools.name, smithsTools.name)),
        Pair("subraces", listOf(mountainDwarf.name, hillDwarf.name))
    ),
    requirements = { true },
    description = "Ваш персонаж дварф обладает рядом врождённых способностей, являющихся частью его природы.\n" +
            "\n" +
            "Увеличение характеристик. Значение вашего Телосложения увеличивается на 2.\n" +
            "\n" +
            "Возраст. Дварфы взрослеют с той же скоростью, что и люди, но считаются юными, пока не достигнут пятидесятилетнего возраста. В среднем, они живут свыше 350 лет.\n" +
            "\n" +
            "Мировоззрение. Любое.\n" +
            "\n" +
            "Размер. Рост дварфов находится между 4 и 5 футами (122 и 152 сантиметрами), и весят они около 150 фунтов (68 килограмм). Ваш размер — Средний.\n" +
            "\n" +
            "Скорость. Ваша базовая скорость ходьбы составляет 25 футов. Ношение тяжёлых доспехов не снижает вашу скорость.\n" +
            "\n" +
            "Тёмное зрение. Привыкнув к жизни под землёй, вы обладаете превосходным зрением в темноте и при тусклом освещении. На расстоянии в 60 футов вы при тусклом освещении можете видеть так, как будто это яркое освещение, и в темноте так, как будто это тусклое освещение. В темноте вы не можете различать цвета, только оттенки серого.\n" +
            "\n" +
            "Дварфская устойчивость. Вы совершаете с преимуществом спасброски от яда, и вы получаете сопротивление урону ядом.\n" +
            "\n" +
            "Дварфская боевая тренировка. Вы владеете боевым топором, ручным топором, лёгким и боевым молотами.\n" +
            "\n" +
            "Владение инструментами. Вы владеете ремесленными инструментами на ваш выбор: инструменты кузнеца, пивовара или каменщика.\n" +
            "\n" +
            "Знание камня. Если вы совершаете проверку Интеллекта (История), связанную с происхождением работы по камню, вы считаетесь владеющим навыком История, и добавляете к проверке удвоенный бонус мастерства вместо обычного.\n" +
            "\n" +
            "Языки. Вы разговариваете, читаете и пишете на Общем и Дварфийском языках. Дварфийский язык состоит из твёрдых согласных и гортанных звуков, и этот акцент будет присутствовать в любом языке, на котором дварф будет говорить.\n" +
            "\n" +
            "Разновидности. Два основных вида дварфов населяют миры D&D: холмовые дварфы и горные дварфы, но в мультивселенной D&D существует множество видов дварфов. С разрешения вашего Мастера вы можете выбрать один из них.\n",
    priority = Priority.DoFirst
)

val dwarf = AbilityNode(
    name = "Дварф",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.race = "Дварф"
        abilities
    },
    alternatives = mutableMapOf(
        Pair("abilities", listOf(dwarfAbilities.name))
    ),
    requirements = { true },
    description = "Дварф",
)

val mapOfDwarfAbilities = mutableMapOf(
    Pair(dwarfAbilities.name, dwarfAbilities),
    Pair(mountainDwarf.name, mountainDwarf),
    Pair(hillDwarf.name, hillDwarf),
    Pair(dwarf.name, dwarf)
)