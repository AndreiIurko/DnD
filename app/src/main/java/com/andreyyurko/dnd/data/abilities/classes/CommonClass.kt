package com.andreyyurko.dnd.data.abilities.classes

import com.andreyyurko.dnd.data.abilities.classes.barbarian.mapOfBarbarianAbilities
import com.andreyyurko.dnd.data.abilities.classes.bard.mapOfBardAbilities
import com.andreyyurko.dnd.data.abilities.classes.cleric.mapOfClericAbilities
import com.andreyyurko.dnd.data.abilities.classes.druid.mapOfDruidAbilities
import com.andreyyurko.dnd.data.abilities.classes.fighter.mapOfFighterAbilities
import com.andreyyurko.dnd.data.abilities.classes.monk.mapOfMonkAbilities
import com.andreyyurko.dnd.data.abilities.classes.paladin.mapOfPaladinAbilities
import com.andreyyurko.dnd.data.abilities.classes.ranger.mapOfRangerAbilities
import com.andreyyurko.dnd.data.abilities.classes.rogue.mapOfRogueAbilities
import com.andreyyurko.dnd.data.abilities.classes.sorcerer.mapOfSorcererAbilities
import com.andreyyurko.dnd.data.abilities.classes.warlock.mapOfWarlockAbilities
import com.andreyyurko.dnd.data.abilities.classes.wizard.mapOfWizardAbilities
import com.andreyyurko.dnd.data.abilities.mapOfAn
import com.andreyyurko.dnd.data.characterData.CharacterInfo
import com.andreyyurko.dnd.data.characterData.Priority
import com.andreyyurko.dnd.data.characterData.character.AbilityNode
import com.andreyyurko.dnd.data.characterData.character.Character
import com.andreyyurko.dnd.data.characterData.character.CharacterAbilityNode

// For all levels alternatives: it is important that
class AbilityNodeLevel(
    name: String,
    changesInCharacterInfo: (abilities: CharacterInfo) -> CharacterInfo,
    getAlternatives: MutableMap<String, (abilities: CharacterInfo?) -> List<String>>,
    requirements: (abilities: CharacterInfo) -> Boolean,
    addRequirements: List<List<Triple<String, String, Int>>>,
    description: String,
    var next_level: String?,
) : AbilityNode(
    name,
    changesInCharacterInfo,
    getAlternatives,
    requirements,
    addRequirements,
    description,
    isNeedsToBeShown = false,
    priority = Priority.DoAsSoonAsPossible
)

class CharacterAbilityNodeLevel(
    override val data: AbilityNodeLevel,
    chosen_alternatives: MutableMap<String, CharacterAbilityNode>,
    character: Character? = null,
    var next_level: CharacterAbilityNodeLevel?
) : CharacterAbilityNode(data, chosen_alternatives, character) {
    constructor(_data: AbilityNodeLevel, character: Character?) : this(
        data = _data,
        chosen_alternatives = mutableMapOf(),
        character = character,
        next_level = null
    )

    fun makeChoice() {
        for (entries in data.getAlternatives.entries) {
            // check if ability is next level
            if (entries.value(character?.characterInfo)[0] == data.next_level) continue

            // choose all abilities
            (this as CharacterAbilityNode).makeChoice(
                entries.key,
                entries.value(character?.characterInfo)[0]
            )
        }
    }

    fun levelUp() {
        val nextAN = mapOfAn[data.next_level]
        nextAN?.let {
            next_level = CharacterAbilityNodeLevel(nextAN as AbilityNodeLevel, character)
            chosen_alternatives["nextLevel"] = next_level as CharacterAbilityNode
        }

    }
}

var extraAttack: AbilityNode = AbilityNode(
    name = "Дополнительная атака",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsMap["Атака"]?.let { action ->
            val actionSplit: MutableList<String> =
                action.description.split("\n") as MutableList<String>
            actionSplit[0] = "Совершить две атаки оружием"
            action.description = actionSplit.joinToString("\n")
        }
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "Если вы в свой ход совершаете действие Атака, вы можете совершить две атаки вместо одной.\n",
    priority = Priority.DoLast
)

var evasion: AbilityNode = AbilityNode(
    name = "Увёртливость",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Увёртливость"] =
            "Вы можете ловко увернуться от зональных эффектов, например, огненного дыхания красного дракона или заклинания град [ice storm]. Если вы попадаете под действие эффекта, который позволяет вам совершить спасбросок Ловкости, чтобы получить только половину урона, вместо этого вы не получаете вовсе никакого урона, если спасбросок был успешен, и получаете только половину урона, если он был провален.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(listOf()),
    description = "Вы можете ловко увернуться от зональных эффектов, например, огненного дыхания красного дракона или заклинания град [ice storm]. Если вы попадаете под действие эффекта, который позволяет вам совершить спасбросок Ловкости, чтобы получить только половину урона, вместо этого вы не получаете вовсе никакого урона, если спасбросок был успешен, и получаете только половину урона, если он был провален.\n"
)

val mapOfClasses = (
        mapOfMonkAbilities
                + mapOfBarbarianAbilities
                + mapOfFighterAbilities
                + mapOfSorcererAbilities
                + mapOfClericAbilities
                + mapOfWizardAbilities
                + mapOfBardAbilities
                + mapOfRogueAbilities
                + mapOfPaladinAbilities
                + mapOfDruidAbilities
                + mapOfRangerAbilities
                + mapOfWarlockAbilities
                + mapOf(Pair(extraAttack.name, extraAttack))
                + mapOf(Pair(evasion.name, evasion))
        ).toMutableMap()