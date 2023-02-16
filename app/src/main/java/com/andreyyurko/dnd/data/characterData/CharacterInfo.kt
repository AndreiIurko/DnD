package com.andreyyurko.dnd.data.characterData

import com.andreyyurko.dnd.data.inventory.InventoryItemInfo
import com.andreyyurko.dnd.data.spells.CharacterSpells

data class CharacterInfo(
    var level: Int = 0,
    var race: String = "",
    var characterClass: Classes = Classes.NotImplemented,
    var passiveInsightBonus: Int = 0,
    var passivePerceptionBonus: Int = 0,
    var strength: Int = 0,
    var dexterity: Int = 0,
    var constitution: Int = 0,
    var intelligence: Int = 0,
    var wisdom: Int = 0,
    var charisma: Int = 0,
    var proficiencyBonus: Int = 0,
    var skillProficiency: MutableSet<Skill> = mutableSetOf(),
    var expertize: MutableSet<Skill> = mutableSetOf(),
    var isHasHalfProf: Boolean = false,
    var savingThrowProf: MutableSet<Ability> = mutableSetOf(),
    var armorProficiency: MutableSet<ArmorProf> = mutableSetOf(),
    var weaponProficiency: MutableSet<Weapon> = mutableSetOf(),
    var armorAdditionalProficiency: MutableSet<Int> = mutableSetOf(), //every item has an id
    var toolProficiency: MutableSet<Int> = mutableSetOf(), //every tool has an id
    var languageProficiency: MutableSet<Languages> = mutableSetOf(),
    var ac: Int = 0,
    var initiativeBonus: Int = 0,
    var speed: Int = 0,
    var hp: Int = 0,
    var damageResistances: MutableSet<DamageType> = mutableSetOf(),
    var damageImmunities: MutableSet<DamageType> = mutableSetOf(),
    var conditionImmunities: MutableSet<Conditions> = mutableSetOf(),
    var actionsList: MutableList<Action> = mutableListOf(),
    var additionalAbilities: MutableList<String> = mutableListOf(), // like blind vision

    // String - name of inventory item
    var inventory: MutableMap<String, InventoryItemInfo> = mutableMapOf(),

    var spellsInfo: CharacterSpells = CharacterSpells(),

    var currentState: CurrentState = CurrentState()
)

fun mergeCharacterInfo(characterInfoFirst: CharacterInfo, characterInfoSecond: CharacterInfo): CharacterInfo {
    val resultCharacterInfo = CharacterInfo()
    resultCharacterInfo.inventory = characterInfoFirst.inventory
    resultCharacterInfo.currentState = characterInfoFirst.currentState
    resultCharacterInfo.spellsInfo.spellLists = characterInfoFirst.spellsInfo.spellLists

    resultCharacterInfo.characterClass.className =
        characterInfoFirst.characterClass.className + characterInfoSecond.characterClass.className
    resultCharacterInfo.level = characterInfoFirst.level + characterInfoSecond.level
    resultCharacterInfo.race = characterInfoFirst.race + characterInfoSecond.race
    resultCharacterInfo.strength = characterInfoFirst.strength + characterInfoSecond.strength
    resultCharacterInfo.dexterity = characterInfoFirst.dexterity + characterInfoSecond.dexterity
    resultCharacterInfo.constitution = characterInfoFirst.constitution + characterInfoSecond.constitution
    resultCharacterInfo.intelligence = characterInfoFirst.intelligence + characterInfoSecond.intelligence
    resultCharacterInfo.wisdom = characterInfoFirst.wisdom + characterInfoSecond.wisdom
    resultCharacterInfo.charisma = characterInfoFirst.charisma + characterInfoSecond.charisma
    resultCharacterInfo.proficiencyBonus = characterInfoFirst.proficiencyBonus + characterInfoSecond.proficiencyBonus
    resultCharacterInfo.speed = characterInfoFirst.speed + characterInfoSecond.speed
    resultCharacterInfo.initiativeBonus = characterInfoFirst.initiativeBonus + characterInfoSecond.initiativeBonus
    resultCharacterInfo.ac = characterInfoFirst.ac + characterInfoSecond.ac
    resultCharacterInfo.hp = characterInfoFirst.hp + characterInfoSecond.hp
    resultCharacterInfo.passiveInsightBonus =
        characterInfoFirst.passiveInsightBonus + characterInfoSecond.passiveInsightBonus
    resultCharacterInfo.passivePerceptionBonus =
        characterInfoFirst.passivePerceptionBonus + characterInfoSecond.passivePerceptionBonus
    resultCharacterInfo.skillProficiency =
        (characterInfoFirst.skillProficiency + characterInfoSecond.skillProficiency) as MutableSet<Skill>

    return resultCharacterInfo
}

data class CurrentState(
    var armor: Armor = Armor.NoArmor,
    var weapons: List<Weapon> = listOf(),
    var hasShield: Boolean = false,

    var equippedItems: MutableSet<String> = mutableSetOf(),
    // String - AN name
    var charges: MutableMap<String, ChargesCounter> = mutableMapOf()
)