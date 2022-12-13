package com.andreyyurko.dnd.data.characters

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

enum class WeaponProf(var profName: String) {
    Simple("Простое оружие"),
    Martial("Воинское оружие")
}

enum class ActionType(var actionName: String) {
    Action("Основное действие"),
    Bonus("Бонусное действие"),
    Reaction("Реакция")
}

enum class ItemRarity(var rarityName: String) {
    Common("Обычный"),
    Uncommon("Необычный"),
    Rare("Редкий"),
    VeryRare("Очень редкий"),
    Legendary("Легендарный"),
    Artifact("Артефакт")
}

enum class ItemType(var typeName: String) {
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
    //TODO: реализовать эту фигню, мне лениво их искать
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