package com.andreyyurko.dnd.data.characters

enum class Skill(var skillName: String) {
    Acrobatics("Акробатика"),
    AnimalHandling("Уход за животными"),
    Arcana("Магия"),
    Athletics("Атлетика"),
    Deception("Обман"),
    History("История"),
    Insight("Проницательность"),
    Intimidation("Запугивание"),
    Investigation("Анализ"),
    Medicine("Медицина"),
    Nature("Природа"),
    Perception("Восприятие"),
    Performance("Выступление"),
    Persuasion("Убеждение"),
    Religion("Религия"),
    SleightOfHand("Ловкость рук"),
    Stealth("Скрытность"),
    Survival("Выживание")
}

enum class Ability(var abilityName: String) {
    Strength("Сила"),
    Dexterity("Ловкость"),
    Constitution("Телосложение"),
    Intelligence("Интеллект"),
    Wisdom("Мудрость"),
    Charisma("Харизма")
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