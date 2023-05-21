package com.andreyyurko.dnd.data.characterData

import com.andreyyurko.dnd.data.characterData.character.CharacterInfo
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

enum class ToolsType(var toolsType: String) {
    ArtisansTools("Инструменты ремесленника"),
    GamingSet("Игровой набор"),
    MusicalInstrument("Музыкальный инструмент"),
    Other("Особый инструмент"),
}

enum class Tools(var toolName: String, var toolsType: ToolsType) {
    AlchemistsSupplies("Инструменты алхимика", ToolsType.ArtisansTools),
    BrewersSupplies("Инструменты пивовара", ToolsType.ArtisansTools),
    CalligraphersSupplies("Инструменты каллиграфа", ToolsType.ArtisansTools),
    CarpentersTools("Инструменты плотника", ToolsType.ArtisansTools),
    CartographersTools("Инструменты картографа", ToolsType.ArtisansTools),
    CobblersTools("Инструменты сапожника", ToolsType.ArtisansTools),
    CooksUtensils("Инструменты повара", ToolsType.ArtisansTools),
    GlassblowersTools("Инструменты стеклодува", ToolsType.ArtisansTools),
    JewelersTools("Инструменты ювелира", ToolsType.ArtisansTools),
    LeatherworkersTools("Инструменты кожевника", ToolsType.ArtisansTools),
    MasonsTools("Инструменты каменщика", ToolsType.ArtisansTools),
    PaintersSupplies("Инструменты художника", ToolsType.ArtisansTools),
    PottersTools("Инструменты гончара", ToolsType.ArtisansTools),
    SmithsTools("Инструменты кузнеца", ToolsType.ArtisansTools),
    TinkersTools("Инструменты ремонтника", ToolsType.ArtisansTools),
    WeaversTools("Инструменты ткача", ToolsType.ArtisansTools),
    WoodcarversTools("Инструменты резчика по дереву", ToolsType.ArtisansTools),
    DiceSet("Кости", ToolsType.GamingSet),
    PlayingCards("Игральные карты", ToolsType.GamingSet),
    ThreeDragonAnte("Ставка трёх драконов", ToolsType.GamingSet),
    DragonChess("Драконьи шахматы", ToolsType.GamingSet),
    Bagpipes("Волынка", ToolsType.MusicalInstrument),
    Drum("Барабан", ToolsType.MusicalInstrument),
    Dulcimer("Цимбалы", ToolsType.MusicalInstrument),
    Flute("Флейта", ToolsType.MusicalInstrument),
    Lute("Лютня", ToolsType.MusicalInstrument),
    Lyre("Лира", ToolsType.MusicalInstrument),
    Horn("Рожок", ToolsType.MusicalInstrument),
    PanFlute("Флейта Пана", ToolsType.MusicalInstrument),
    Shawm("Шалмей", ToolsType.MusicalInstrument),
    Viol("Виола", ToolsType.MusicalInstrument),
    ThievesTools("Воровские инструменты", ToolsType.Other),
    DisguiseTools("Набор для грима", ToolsType.Other),
    Forgery("Набор для фальсификации", ToolsType.Other),
    PoisonerTools("Инструменты отравителя", ToolsType.Other),
    HerbalismTools("Инструменты травника", ToolsType.Other),
    NavigatorTools("Инструменты навигатора", ToolsType.Other),
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
    var ac: Int,
    var armorType: String,
    var StrengthRequirement: Int,
    var StealthDisadvantage: Boolean,
    var dexRestriction: Int
) {
    Padded("Стеганый", 11, "лёгкий", 0, true, 10),
    Leather("Кожаный", 11, "лёгкий", 0, false, 10),
    StuddedLeather("Проклёпанный кожаный", 12, "лёгкий", 0, false, 10),

    Hide("Шкурный", 12, "средний", 0, false, 2),
    ChainShirt("Кольчужная рубаха", 13, "средний", 0, false, 2),
    ScaleMail("Чешуйчатый", 14, "средний", 0, true, 2),
    Breastplate("Кираса", 14, "средний", 0, false, 2),
    HalfPlate("Полулаты", 15, "средний", 0, true, 2),

    RingMail("Колечный", 14, "тяжёлый", 0, true, 0),
    ChainMail("Кольчуга", 16, "тяжёлый", 13, true, 0),
    Splint("Наборный", 17, "тяжёлый", 15, true, 0),
    Plate("Латы", 18, "тяжёлый", 15, true, 0),

    Shield("Щит", 2, "щит", 0, false, 10),

    NoArmor("Без доспеха", 10, "без доспеха", 0, false, 10)
}

enum class Weapon(
    var weaponName: String,
    var cost: String,
    var damage: String,
    var damageType: DamageType,
    var properties: List<String>,
    var setOfSkills: Set<Ability>,
    var isMelee: Boolean = true,
    var toHitBonus: Int = 0
) {
    Unarmed("Безоружный удар", "", "1", DamageType.Bludgeoning, listOf(), setOf(Ability.Strength)),
    Club(
        "Дубинка", "1 см", "1к4", DamageType.Bludgeoning,
        listOf("лёгкое"), setOf(Ability.Strength)
    ),
    Dagger(
        "Кинжал",
        "2 зм",
        "1к4",
        DamageType.Piercing,
        listOf("лёгкое", "метательное (дис. 20/60)", "фехтовальное"),
        setOf(Ability.Strength, Ability.Dexterity)
    ),
    GreatClub(
        "Палица", "2 зм", "1к8", DamageType.Bludgeoning,
        listOf("двуручное"), setOf(Ability.Strength)
    ),
    HandAxe(
        "Ручной топор", "5 зм", "1к6", DamageType.Slashing,
        listOf("лёгкое", "метательное (дис. 20/60)"), setOf(Ability.Strength)
    ),
    Javelin(
        "Метательное копье", "5 см", "1к6", DamageType.Piercing,
        listOf("метательное (дис. 30/120)"), setOf(Ability.Strength)
    ),
    LightHammer(
        "Лёгкий молот", "2 зм", "1к4", DamageType.Bludgeoning,
        listOf("метательное (дис. 20/60)"), setOf(Ability.Strength)
    ),
    Mace(
        "Булава", "5 зм", "1к6", DamageType.Bludgeoning,
        listOf(), setOf(Ability.Strength)
    ),
    Quarterstaff(
        "Боевой посох", "2 см", "1к6", DamageType.Bludgeoning,
        listOf("универсальное (1к8)"), setOf(Ability.Strength)
    ),
    Sickle(
        "Серп", "1 зм", "1к4", DamageType.Slashing,
        listOf("лёгкое"), setOf(Ability.Strength)
    ),
    Spear(
        "Копье", "1 зм", "1к6", DamageType.Piercing,
        listOf("метательное (дис. 20/60)", "универсальное (1к8)"), setOf(Ability.Strength)
    ),
    CrossbowLight(
        "Арбалет, легкий",
        "25 зм",
        "1к8",
        DamageType.Piercing,
        listOf("боеприпас (дис. 80/320)", "двуручное", "перезарядка"),
        setOf(Ability.Dexterity),
        false
    ),
    Dart(
        "Дротик",
        "5 мм",
        "1к4",
        DamageType.Piercing,
        listOf("метательное (дис. 20/60)", "фехтовальное"),
        setOf(Ability.Strength, Ability.Dexterity),
        false
    ),
    ShortBow(
        "Короткий лук", "25 зм", "1к6", DamageType.Piercing,
        listOf("боеприпас (дис. 80/320)", "двуручное"), setOf(Ability.Dexterity), false
    ),
    Sling(
        "Праща", "1 см", "1к4", DamageType.Bludgeoning,
        listOf("боеприпас (дис. 30/120)"), setOf(Ability.Dexterity), false
    ),
    ShortSword(
        "Короткий меч", "10 зм", "1к6", DamageType.Slashing,
        listOf("лёгкое", "фехтовальное"), setOf(Ability.Strength, Ability.Dexterity)
    ),
    Battleaxe(
        "Боевой топор", "10 зм", "1к8", DamageType.Slashing,
        listOf("универсальное"), setOf(Ability.Strength)
    ),
    Flail(
        "Цеп", "10 зм", "1к8", DamageType.Bludgeoning,
        listOf(), setOf(Ability.Strength)
    ),
    Glaive(
        "Глефа", "20 зм", "1к10", DamageType.Slashing,
        listOf("двуручное", "досягаемость", "тяжёлое"), setOf(Ability.Strength)
    ),
    Greataxe(
        "Секира", "30 зм", "1к12", DamageType.Slashing,
        listOf("двуручное", "тяжёлое"), setOf(Ability.Strength)
    ),
    Greatsword(
        "Двуручный меч", "50 зм", "2к6", DamageType.Slashing,
        listOf("двуручное", "тяжёлое"), setOf(Ability.Strength)
    ),
    Halberd(
        "Алебарда", "20 зм", "1к10", DamageType.Slashing,
        listOf("двуручное", "досягаемость", "тяжёлое"), setOf(Ability.Strength)
    ),
    Lance(
        "Длинное копьё", "10 зм", "1к12", DamageType.Piercing,
        listOf("досягаемость", "особое"), setOf(Ability.Strength)
    ),
    Longsword(
        "Длинный меч", "15 зм", "1к8", DamageType.Slashing,
        listOf("универсальное (1к8)"), setOf(Ability.Strength)
    ),
    Maul(
        "Молот", "10 зм", "2к6", DamageType.Bludgeoning,
        listOf("двуручное", "тяжёлое"), setOf(Ability.Strength)
    ),
    Morningstart(
        "Моргенштерн", "15 зм", "1к8", DamageType.Piercing,
        listOf(), setOf(Ability.Strength)
    ),
    Pike(
        "Пика", "5 зм", "1к10", DamageType.Piercing,
        listOf("двуручное", "досягаемость", "тяжёлое"), setOf(Ability.Strength)
    ),
    Rapier(
        "Рапира", "25 зм", "1к8", DamageType.Piercing,
        listOf("фехтовальное"), setOf(Ability.Strength, Ability.Dexterity)
    ),
    Scimitar(
        "Скимитар", "25 зм", "1к6", DamageType.Slashing,
        listOf("лёгкое", "фехтовальное"), setOf(Ability.Strength, Ability.Dexterity)
    ),
    Trident(
        "Трезубец", "5 зм", "1к6", DamageType.Piercing,
        listOf("метательное (дис. 20/60)", "универсальное (1к8)"), setOf(Ability.Strength)
    ),
    WarPick(
        "Боевая кирка", "5 зм", "1к8", DamageType.Piercing,
        listOf(), setOf(Ability.Strength)
    ),
    Warhammer(
        "Боевой молот", "15 зм", "1к8", DamageType.Bludgeoning,
        listOf("универсальное (1к10)"), setOf(Ability.Strength)
    ),
    Whip(
        "Кнут", "2 зм", "1к4", DamageType.Slashing,
        listOf("досягаемость", "фехтовальное"), setOf(Ability.Strength, Ability.Dexterity)
    ),
    Blowgun(
        "Духовая трубка", "10 зм", "1", DamageType.Piercing,
        listOf("боеприпас (25/100)", "перезарядка"), setOf(Ability.Dexterity), false
    ),
    CrossbowHand(
        "Арбалет, ручной", "75 зм", "1к6", DamageType.Piercing,
        listOf("боеприпас (30/120)", "лёгкое", "перезарядка"), setOf(Ability.Dexterity), false
    ),
    CrossbowHeavy(
        "Арбалет, тяжёлый",
        "50 зм",
        "1к10",
        DamageType.Piercing,
        listOf("боеприпас (100/400)", "двуручное", "перезарядка", "тяжёлое"),
        setOf(Ability.Dexterity),
        false
    ),
    LongBow(
        "Длинный лук",
        "50 зм",
        "1к8",
        DamageType.Piercing,
        listOf("боеприпас (150/600)", "двуручное", "тяжёлое"),
        setOf(Ability.Strength, Ability.Dexterity),
        false
    ),
    Net(
        "Сеть", "1 зм", "0", DamageType.Slashing,
        listOf("Метательное (5/15)", "особое"), setOf(Ability.Dexterity)
    );

    var shownDamage: String = ""
    var shownSecondWeaponDamage: String = ""
    var shownToHit: Int = 0
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
    abilities.weaponProficiency =
        abilities.weaponProficiency.union(simpleWeapons) as MutableSet<Weapon>
    return abilities
}

fun addAllMartialWeapons(abilities: CharacterInfo): CharacterInfo {
    val simpleWeapons = setOf(
        Weapon.Battleaxe,
        Weapon.Flail,
        Weapon.Glaive,
        Weapon.Greataxe,
        Weapon.Greatsword,
        Weapon.Halberd,
        Weapon.Lance,
        Weapon.Longsword,
        Weapon.Maul,
        Weapon.Morningstart,
        Weapon.Pike,
        Weapon.Rapier,
        Weapon.Scimitar,
        Weapon.ShortSword,
        Weapon.Trident,
        Weapon.WarPick,
        Weapon.Warhammer,
        Weapon.Whip,
        Weapon.Blowgun,
        Weapon.CrossbowHand,
        Weapon.CrossbowHeavy,
        Weapon.LongBow,
        Weapon.Net
    )
    abilities.weaponProficiency =
        abilities.weaponProficiency.union(simpleWeapons) as MutableSet<Weapon>
    return abilities
}

enum class ActionType(override var shownName: String) : Filter {
    Action("Основное действие"),
    Bonus("Бонусное действие"),
    Reaction("Реакция"),
    PartOfAction("Часть действия"),
    Long("Длительные"),
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

