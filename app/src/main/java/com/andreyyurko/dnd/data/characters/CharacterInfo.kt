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
    var skillProficiency: Set<Skill> = emptySet(),
    var expertize: Set<Skill> = emptySet(),
    var savingThrowProf: Set<Ability> = emptySet(),
    var armorProficiency: Set<ArmorProf> = emptySet(),
    var weaponProficiency: Set<Weapon> = emptySet(),
    var armorAdditionalProficiency: Set<Int> = emptySet(), //every item has an id
    var toolProficiency: Set<Int> = emptySet(), //every tool has an id
    var languageProficiency: Set<Languages> = emptySet(), //every language has an id
    var ac: Int = 0,
    var initiativeBonus: Int = 0,
    var speed: Int = 0,
    var hp: Int = 0,
    var damageResistances: Set<Int> = emptySet(),
    var damageImmunities: Set<Int> = emptySet(),
    var actionsList: List<Action> = emptyList(),
    var inventory: List<EquipmentItem> = emptyList(),
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
    var hasShield: Boolean = false
)