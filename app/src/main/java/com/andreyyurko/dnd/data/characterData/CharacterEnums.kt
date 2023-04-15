package com.andreyyurko.dnd.data.characterData

import com.andreyyurko.dnd.data.characterData.character.Filter

enum class Skill(var skillName: String, var ability: Ability) {
    Acrobatics("Акробатика", Ability.Dexterity),
    AnimalHandling("Уход за животными", Ability.Wisdom),
    Arcana("Магия", Ability.Intelligence),
    Athletics("Атлетика", Ability.Strength),
    Deception("Обман", Ability.Charisma),
    History("История", Ability.Intelligence),
    Insight("Проницательность", Ability.Wisdom),
    Intimidation("Запугивание", Ability.Charisma),
    Investigation("Анализ", Ability.Intelligence),
    Medicine("Медицина", Ability.Wisdom),
    Nature("Природа", Ability.Intelligence),
    Perception("Восприятие", Ability.Wisdom),
    Performance("Выступление", Ability.Charisma),
    Persuasion("Убеждение", Ability.Charisma),
    Religion("Религия", Ability.Intelligence),
    SleightOfHand("Ловкость рук", Ability.Dexterity),
    Stealth("Скрытность", Ability.Dexterity),
    Survival("Выживание", Ability.Wisdom)
}

enum class Ability(var abilityName: String, var abilityShortName: String) {
    Strength("Сила", "Сил"),
    Dexterity("Ловкость", "Лвк"),
    Constitution("Телосложение", "Тел"),
    Intelligence("Интеллект", "Инт"),
    Wisdom("Мудрость", "Мдр"),
    Charisma("Харизма", "Хар")
}

enum class ArmorProf(var profName: String) {
    LightArmor("Лёгкий доспех"),
    MediumArmor("Средний доспех"),
    HeavyArmor("Тяжелый доспех"),
    Shield("Щит")
}

enum class Armor(
    var armorName: String,
    var cost: String,
    var ac: Int,
    var StrengthRequirement: Int,
    var StealthDisadvantage: Boolean,
    var weight: String,
    var dexRestriction: Int
) {
    StuddedLeather("Проклёпанный кожаный", "45 зм", 12, 0, false, "13 фнт.", 10),
    NoArmor("Без доспеха", "", 10, 0, false, "", 10)
}

enum class Weapon(
    var weaponName: String,
    var cost: String,
    var damage: String,
    var weight: String,
    var properties: List<String>,
    var setOfSkills: Set<Ability>,
    var isMelee: Boolean = true,
    var toHitBonus: Int = 0
) {
    Unarmed("Безоружный удар", "", "1", "", listOf(), setOf(Ability.Strength)),
    Club(
        "Дубинка", "1 см", "1к4 " + DamageType.Bludgeoning.typeName, "2 фнт.",
        listOf("Лёгкое"), setOf(Ability.Strength)
    ),
    Dagger(
        "Кинжал", "2 зм", "1к4 " + DamageType.Piercing.typeName, "1 фнт.",
        listOf("Лёгкое", "метательное (дис. 20/60)", "фехтовальное"), setOf(Ability.Strength, Ability.Dexterity)
    ),
    GreatClub(
        "Палица", "2 зм", "1к8 " + DamageType.Bludgeoning.typeName, "10 фнт.",
        listOf("Двуручное"), setOf(Ability.Strength)
    ),
    HandAxe(
        "Ручной топор", "5 зм", "1к6 " + DamageType.Slashing.typeName, "2 фнт.",
        listOf("Лёгкое", "метательное (дис. 20/60)"), setOf(Ability.Strength)
    ),
    Javelin(
        "Метательное копье", "5 см", "1к6 " + DamageType.Piercing.typeName, "2 фнт.",
        listOf("Метательное (дис. 30/120)"), setOf(Ability.Strength)
    ),
    LightHammer(
        "Лёгкий молот", "2 зм", "1к4 " + DamageType.Bludgeoning.typeName, "2 фнт.",
        listOf("Метательное (дис. 20/60)"), setOf(Ability.Strength)
    ),
    Mace(
        "Булава", "5 зм", "1к6 " + DamageType.Bludgeoning.typeName, "4 фнт.",
        listOf(), setOf(Ability.Strength)
    ),
    Quarterstaff(
        "Боевой посох", "2 см", "1к6 " + DamageType.Bludgeoning.typeName, "4 фнт.",
        listOf("Универсальное (1к8)"), setOf(Ability.Strength)
    ),
    Sickle(
        "Серп", "1 зм", "1к4 " + DamageType.Slashing.typeName, "2 фнт.",
        listOf("Лёгкое"), setOf(Ability.Strength)
    ),
    Spear(
        "Копье", "1 зм", "1к6 " + DamageType.Piercing.typeName, "3 фнт.",
        listOf("Метательное (дис. 20/60)", "универсальное (1к8)"), setOf(Ability.Strength)
    ),
    CrossbowLight(
        "Арбалет, легкий", "25 зм", "1к8 " + DamageType.Piercing.typeName, "5 фнт.",
        listOf("Боеприпас (дис. 80/320)", "двуручное", "перезарядка"), setOf(Ability.Dexterity), false
    ),
    Dart(
        "Дротик", "5 мм", "1к4 " + DamageType.Piercing.typeName, "1/4 фнт.",
        listOf("Метательное (дис. 20/60)", "фехтовальное"), setOf(Ability.Strength, Ability.Dexterity), false
    ),
    ShortBow(
        "Короткий лук", "25 зм", "1к6 " + DamageType.Piercing.typeName, "2 фнт.",
        listOf("Боеприпас (дис. 80/320)", "двуручное"), setOf(Ability.Dexterity), false
    ),
    Sling(
        "Праща", "1 см", "1к4 " + DamageType.Bludgeoning.typeName, "-",
        listOf("Боеприпас (дис. 30/120)"), setOf(Ability.Dexterity), false
    ),
    ShortSword(
        "Короткий меч", "10 зм", "1к6 " + DamageType.Slashing.typeName, "2 фнт",
        listOf("Лёгкое", "фехтовальное"), setOf(Ability.Strength, Ability.Dexterity)
    )
}

fun addAllSimpleWeapons(abilities: CharacterInfo): CharacterInfo {
    val simpleWeapons = setOf(
        Weapon.Club,
        Weapon.CrossbowLight,
        Weapon.Dagger,
        Weapon.Dart,
        Weapon.GreatClub,
        Weapon.HandAxe,
        Weapon.Javelin,
        Weapon.LightHammer,
        Weapon.Mace,
        Weapon.Quarterstaff,
        Weapon.Sickle,
        Weapon.Spear,
        Weapon.ShortBow,
        Weapon.Sling
    )
    abilities.weaponProficiency = abilities.weaponProficiency.union(simpleWeapons) as MutableSet<Weapon>
    return abilities
}

enum class ActionType(override var shownName: String) : Filter {
    Action("Основное действие"),
    Bonus("Бонусное действие"),
    Reaction("Реакция"),
    PartOfAction("Часть действия"),
    Additional("Дополнительные")
}

enum class ItemRarity(override var shownName: String) : Filter {
    Common("Обычный"),
    Uncommon("Необычный"),
    Rare("Редкий"),
    VeryRare("Очень редкий"),
    Legendary("Легендарный"),
    Artifact("Артефакт")
}

enum class ItemType(override var shownName: String) : Filter {
    Wand("Волшебная палочка"),
    Armor("Доспех"),
    Rode("Жезл"),
    Potion("Зелье"),
    Ring("Кольцо"),
    Weapon("Оружие"),
    Staff("Посох"),
    Scroll("Свиток"),
    WondrousItem("Чудесный предмет")
}

enum class DamageType(var typeName: String) {
    Bludgeoning("дробящий"),
    Piercing("колющий"),
    Slashing("режущий"),
    Poison("яд"),
    Acid("кислота"),
    Fire("огонь"),
    Cold("яд"),
    Necrotic("некротический"),
    Radiant("излучение"),
    Thunder("звук"),
    Lightening("молния"),
    Psychic("психический"),
    Force("сила"),
}

enum class Conditions(var typeName: String) {
    Blinded("ослеплённый"),
    Charmed("очарованный"),
    Deafened("оглушённый"),
    Frightened("испуганный"),
    Grappled("схваченный"),
    Incapacitated("недееспособный"),
    Invisible("невидимый"),
    Paralyzed("парализованный"),
    Petrified("окаменевший"),
    Poisoned("отравлен"),
    Prone("опрокинутый"),
    Restrained("обездвиженный"),
    Stunned("ошеломлённый"),
    Unconscious("бессознательный")
    //Maybe we need to add exhaustion
}

enum class Languages(var languageName: String) {
    Common("Общий"),
    Elvish("Эльфийский"),
    Dwarvish("Дварфийский"),
    Giant("Великаний"),
    Gnomish("Гномий"),
    Goblin("Гоблинский"),
    Halfling("Полуросликов"),
    Orc("Орочий"),
    Abyssal("Бездны"),
    Celestial("Небесный"),
    Draconic("Драконий"),
    DeepSpeech("Глубинная речь"),
    Infernal("Инфернальный"),
    Primordial("Первичный"),
    Sylvan("Сильван"),
    Undercommon("Подземный")
}

enum class Classes(var className: String = "") {
    Artificer("Изобретатель"),
    Barbarian("Варвар"),
    Bard("Бард"),
    Cleric("Жрец"),
    Druid("Друид"),
    Fighter("Воин"),
    Monk("Монах"),
    Paladin("Паладин"),
    Ranger("Следопыт"),
    Rogue("Плут"),
    Sorcerer("Чародей"),
    Warlock("Колдун"),
    Wizard("Волшебник"),
    NotImplemented()
}

enum class Source(val sourceName: String, override var shownName: String) : Filter {
    PlayerHandbook("«Player's handbook»", "PHB"),
    DungeonMastersGuide("«Dungeon master's guide»", "DMG"),
    XanatharsGuideToEverything("«Xanathar's Guide to Everything»", "XGE"),
    MordenkainenTomeOfFoes("«Mordenkainen's Tome of Foes»", "MTE"),
    VoloGuideToMonsters("«Volo's guide to monsters»", "VGM")
}

enum class School(override var shownName: String) : Filter {
    Abjuration("Ограждение"),
    Conjuration("Вызов"),
    Divination("Прорицание"),
    Enchantment("Очарование"),
    Evocation("Воплощение"),
    Illusion("Иллюзия"),
    Necromancy("Некромантия"),
    Transmutation("Преобразование")

}

enum class Priority {
    DoFirst,
    DoAsSoonAsPossible,
    Basic,
    DoAfterBasic,
    DoLast
}

