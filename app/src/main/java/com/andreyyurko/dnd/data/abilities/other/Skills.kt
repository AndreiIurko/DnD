package com.andreyyurko.dnd.data.abilities.other

import android.util.Log
import com.andreyyurko.dnd.data.characterData.CharacterInfo
import com.andreyyurko.dnd.data.characterData.Priority
import com.andreyyurko.dnd.data.characterData.Skill
import com.andreyyurko.dnd.data.characterData.character.AbilityNode

var acrobatics: AbilityNode = AbilityNode(
    name = "Акробатика",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.skillProficiency.add(Skill.Acrobatics)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        !abilities.skillProficiency.contains(Skill.Acrobatics)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var animalHandling: AbilityNode = AbilityNode(
    name = "Уход за животными",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.skillProficiency.add(Skill.AnimalHandling)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        !abilities.skillProficiency.contains(Skill.AnimalHandling)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var arcana: AbilityNode = AbilityNode(
    name = "Магия",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.skillProficiency.add(Skill.Arcana)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        !abilities.skillProficiency.contains(Skill.Arcana)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var athletics: AbilityNode = AbilityNode(
    name = "Атлетика",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.skillProficiency.add(Skill.Athletics)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        !abilities.skillProficiency.contains(Skill.Athletics)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var deception: AbilityNode = AbilityNode(
    name = "Обман",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.skillProficiency.add(Skill.Deception)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        !abilities.skillProficiency.contains(Skill.Deception)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var history: AbilityNode = AbilityNode(
    name = "История",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.skillProficiency.add(Skill.History)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        !abilities.skillProficiency.contains(Skill.History)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var insight: AbilityNode = AbilityNode(
    name = "Проницательность",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.skillProficiency.add(Skill.Insight)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        !abilities.skillProficiency.contains(Skill.Insight)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var intimidation: AbilityNode = AbilityNode(
    name = "Запугивание",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.skillProficiency.add(Skill.Intimidation)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        !abilities.skillProficiency.contains(Skill.Intimidation)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var investigation: AbilityNode = AbilityNode(
    name = "Анализ",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.skillProficiency.add(Skill.Investigation)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        !abilities.skillProficiency.contains(Skill.Investigation)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var medicine: AbilityNode = AbilityNode(
    name = "Медицина",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.skillProficiency.add(Skill.Medicine)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        !abilities.skillProficiency.contains(Skill.Medicine)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var nature: AbilityNode = AbilityNode(
    name = "Природа",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.skillProficiency.add(Skill.Nature)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        !abilities.skillProficiency.contains(Skill.Nature)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var perception: AbilityNode = AbilityNode(
    name = "Внимательность",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.skillProficiency.add(Skill.Perception)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        !abilities.skillProficiency.contains(Skill.Perception)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var performance: AbilityNode = AbilityNode(
    name = "Выступление",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.skillProficiency.add(Skill.Performance)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        !abilities.skillProficiency.contains(Skill.Performance)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var persuasion: AbilityNode = AbilityNode(
    name = "Убеждение",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.skillProficiency.add(Skill.Persuasion)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        !abilities.skillProficiency.contains(Skill.Persuasion)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var religion: AbilityNode = AbilityNode(
    name = "Религия",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.skillProficiency.add(Skill.Religion)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        !abilities.skillProficiency.contains(Skill.Religion)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var sleightOfHand: AbilityNode = AbilityNode(
    name = "Ловкость рук",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.skillProficiency.add(Skill.SleightOfHand)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        !abilities.skillProficiency.contains(Skill.SleightOfHand)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var stealth: AbilityNode = AbilityNode(
    name = "Скрытность",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.skillProficiency.add(Skill.Stealth)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        !abilities.skillProficiency.contains(Skill.Stealth)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var survival: AbilityNode = AbilityNode(
    name = "Выживание",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        abilities.skillProficiency.add(Skill.Survival)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        !abilities.skillProficiency.contains(Skill.Survival)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var mapOfSkills = mutableMapOf(
    Pair(acrobatics.name, acrobatics),
    Pair(animalHandling.name, animalHandling),
    Pair(arcana.name, arcana),
    Pair(athletics.name, athletics),
    Pair(deception.name, deception),
    Pair(history.name, history),
    Pair(insight.name, insight),
    Pair(intimidation.name, intimidation),
    Pair(investigation.name, investigation),
    Pair(medicine.name, medicine),
    Pair(nature.name, nature),
    Pair(perception.name, perception),
    Pair(performance.name, performance),
    Pair(persuasion.name, persuasion),
    Pair(religion.name, religion),
    Pair(sleightOfHand.name, sleightOfHand),
    Pair(stealth.name, stealth),
    Pair(survival.name, survival)
)