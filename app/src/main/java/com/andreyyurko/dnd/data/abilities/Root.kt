package com.andreyyurko.dnd.data.abilities

import com.andreyyurko.dnd.data.abilities.classes.barbarian.barbarian1
import com.andreyyurko.dnd.data.abilities.classes.bard.bard1
import com.andreyyurko.dnd.data.abilities.classes.cleric.cleric1
import com.andreyyurko.dnd.data.abilities.classes.druid.druid1
import com.andreyyurko.dnd.data.abilities.classes.fighter.fighter1
import com.andreyyurko.dnd.data.abilities.classes.mapOfClasses
import com.andreyyurko.dnd.data.abilities.classes.monk.monk1
import com.andreyyurko.dnd.data.abilities.classes.paladin.paladin1
import com.andreyyurko.dnd.data.abilities.classes.ranger.ranger1
import com.andreyyurko.dnd.data.abilities.classes.rogue.rogue1
import com.andreyyurko.dnd.data.abilities.classes.sorcerer.sorcerer1
import com.andreyyurko.dnd.data.abilities.classes.warlock.warlock1
import com.andreyyurko.dnd.data.abilities.classes.wizard.wizard1
import com.andreyyurko.dnd.data.abilities.other.*
import com.andreyyurko.dnd.data.abilities.races.*
import com.andreyyurko.dnd.data.abilities.races.lineages.*
import com.andreyyurko.dnd.data.characterData.CharacterInfo
import com.andreyyurko.dnd.data.characterData.Priority
import com.andreyyurko.dnd.data.characterData.Weapon
import com.andreyyurko.dnd.data.characterData.character.AbilityNode
import com.andreyyurko.dnd.data.characterData.character.abilityToModifier
import java.lang.Integer.min

var baseAN: AbilityNode = AbilityNode(
    "base_an",
    { abilities: CharacterInfo ->
        abilities.weaponProficiency.add(Weapon.Unarmed)
        abilities.ac = abilities.currentState.armor.ac + min(
            abilities.currentState.armor.dexRestriction,
            abilityToModifier(abilities.dexterity)
        )
        if (abilities.currentState.hasShield) {
            abilities.ac += 2
        }
        abilities.initiativeBonus = abilities.initiativeBonus + (abilities.dexterity - 10) / 2
        abilities
    },
    mutableMapOf(
        Pair("commonAbilities") { listOf(commonRoot.name) },
        Pair(
            "class",
            {
                listOf(
                    monk1.name,
                    fighter1.name,
                    sorcerer1.name,
                    cleric1.name,
                    wizard1.name,
                    bard1.name,
                    rogue1.name,
                    barbarian1.name,
                    paladin1.name,
                    druid1.name,
                    ranger1.name,
                    warlock1.name
                )
            }
        ),
        Pair("race", { listOf(
            human.name,
            dwarf.name,
            elf.name,
            halfling.name,
            tabaxi.name,
            aasimar.name,
            commonLineage.name
        ) })
    ),
    { true },
    listOf(listOf()),
    description = "Base Ability Node, root of all AN",
    priority = Priority.DoAsSoonAsPossible
)

// helps to split code
var mapOfAn: MutableMap<String, AbilityNode> = (
        mutableMapOf(
            Pair(baseAN.name, baseAN),
            Pair(customBackstory.name, customBackstory)
        )
                + mapOfCommonAN
                + mapOfRaces
                + mapOfClasses
                + mapOfFightingStyles
                + mapOfAbilityScoreImprovement
                + mapOfSkills
                + mapOfExpertise
                + mapOfSkillAndExpertise
                + mapOfLanguages
                + mapOfTools
                + mapOfToolsExpertise
        ).toMutableMap()
