package com.andreyyurko.dnd.data.characters

data class CharacterInfo(
    var level: Int = 0,
    var race: String = "",
    var characterClass: Classes = Classes.NotImplemented,
    var passiveInsightBonus: Int = 0,
    var passivePerceptionBonus: Int = 0,
    var strengthBonus: Int = 0,
    var dexterityBonus: Int = 0,
    var constitutionBonus: Int = 0,
    var intelligenceBonus: Int = 0,
    var wisdomBonus: Int = 0,
    var charismaBonus: Int = 0,
    var proficiencyBonus: Int = 0,
    var skillProficiency: MutableSet<Skill> = mutableSetOf(),
    var expertize: MutableSet<Skill> = mutableSetOf(),
    var savingThrowProf: MutableSet<Ability> = mutableSetOf(),
    var armorProficiency: MutableSet<ArmorProf> = mutableSetOf(),
    var weaponProficiency: MutableSet<Weapon> = mutableSetOf(),
    var armorAdditionalProficiency: MutableSet<Int> = mutableSetOf(), //every item has an id
    var toolProficiency: MutableSet<Int> = mutableSetOf(), //every tool has an id
    var languageProficiency: MutableSet<Languages> = mutableSetOf(), //every language has an id
    var ac: Int = 0,
    var initiativeBonus: Int = 0,
    var speed: Int = 0,
    var hp: Int = 0,
    var damageResistances: MutableSet<DamageType> = mutableSetOf(),
    var damageImmunities: MutableSet<DamageType> = mutableSetOf(),
    var conditionImmunities: MutableSet<Conditions> = mutableSetOf(),
    var actionsList: MutableList<Action> = mutableListOf(),
    var inventory: List<EquipmentItem> = emptyList(),
    var additionalAbilities: MutableList<String> = mutableListOf(), // like blind vision
    var currentState: CurrentState = CurrentState()
)

fun mergeCharacterInfo(characterInfoFirst: CharacterInfo, characterInfoSecond: CharacterInfo): CharacterInfo {
    val resultCharacterInfo = CharacterInfo()
    resultCharacterInfo.currentState = characterInfoFirst.currentState
    resultCharacterInfo.characterClass.className = characterInfoFirst.characterClass.className +characterInfoSecond.characterClass.className
    resultCharacterInfo.level = characterInfoFirst.level + characterInfoSecond.level
    resultCharacterInfo.race = characterInfoFirst.race + characterInfoSecond.race
    resultCharacterInfo.strengthBonus = characterInfoFirst.strengthBonus + characterInfoSecond.strengthBonus
    resultCharacterInfo.dexterityBonus = characterInfoFirst.dexterityBonus + characterInfoSecond.dexterityBonus
    resultCharacterInfo.constitutionBonus = characterInfoFirst.constitutionBonus + characterInfoSecond.constitutionBonus
    resultCharacterInfo.intelligenceBonus = characterInfoFirst.intelligenceBonus + characterInfoSecond.intelligenceBonus
    resultCharacterInfo.wisdomBonus = characterInfoFirst.wisdomBonus + characterInfoSecond.wisdomBonus
    resultCharacterInfo.charismaBonus = characterInfoFirst.charismaBonus + characterInfoSecond.charismaBonus
    resultCharacterInfo.proficiencyBonus = characterInfoFirst.proficiencyBonus + characterInfoSecond.proficiencyBonus
    resultCharacterInfo.speed = characterInfoFirst.speed + characterInfoSecond.speed
    resultCharacterInfo.initiativeBonus = characterInfoFirst.initiativeBonus + characterInfoSecond.initiativeBonus
    resultCharacterInfo.ac = characterInfoFirst.ac + characterInfoSecond.ac
    resultCharacterInfo.hp = characterInfoFirst.hp + characterInfoSecond.hp
    return resultCharacterInfo
}

data class CurrentState(
    var armor: Armor = Armor.NoArmor,
    var weapons: List<Weapon> = listOf(),
    var hasShield: Boolean = false,
    // String - AN name
    var charges: MutableMap<String, ChargesCounter> = mutableMapOf()
)