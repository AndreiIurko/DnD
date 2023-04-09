package com.andreyyurko.dnd.data.abilities.classes

import com.andreyyurko.dnd.data.abilities.classes.fighter.mapOfFighterAbilities
import com.andreyyurko.dnd.data.abilities.classes.sorcerer.mapOfSorcererAbilities
import com.andreyyurko.dnd.data.abilities.classes.cleric.mapOfClericAbilities
import com.andreyyurko.dnd.data.abilities.classes.monk.mapOfMonkAbilities
import com.andreyyurko.dnd.data.abilities.classes.wizard.mapOfWizardAbilities
import com.andreyyurko.dnd.data.abilities.classes.bard.mapOfBardAbilities
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
    alternatives: MutableMap<String, List<String>>,
    requirements: (abilities: CharacterInfo) -> Boolean,
    add_requirements: List<List<Triple<String, String, Int>>>,
    description: String,
    var next_level: String?,
) : AbilityNode(
    name,
    changesInCharacterInfo,
    alternatives,
    requirements,
    add_requirements,
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
        for (entries in data.alternatives.entries) {
            // check if ability is next level
            if (entries.value[0] == data.next_level) continue

            // choose all abilities
            (this as CharacterAbilityNode).makeChoice(entries.key, entries.value[0])
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

val mapOfClasses = (
        mapOfMonkAbilities
                + mapOfBarbarianAbilities
                + mapOfFighterAbilities
                + mapOfSorcererAbilities
                + mapOfClericAbilities
                + mapOfWizardAbilities
                + mapOfBardAbilities
        ).toMutableMap()