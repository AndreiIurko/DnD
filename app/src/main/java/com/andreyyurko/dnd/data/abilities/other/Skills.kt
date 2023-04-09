package com.andreyyurko.dnd.data.abilities.other

import com.andreyyurko.dnd.data.characterData.CharacterInfo
import com.andreyyurko.dnd.data.characterData.Priority
import com.andreyyurko.dnd.data.characterData.Skill
import com.andreyyurko.dnd.data.characterData.character.AbilityNode

var acrobatics: AbilityNode = AbilityNode(
    name = "Акробатика",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.skillProficiency.add(Skill.Acrobatics)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.skillProficiency.contains(Skill.Acrobatics)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var animalHandling: AbilityNode = AbilityNode(
    name = "Уход за животными",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.skillProficiency.add(Skill.AnimalHandling)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.skillProficiency.contains(Skill.AnimalHandling)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var arcana: AbilityNode = AbilityNode(
    name = "Магия",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.skillProficiency.add(Skill.Arcana)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.skillProficiency.contains(Skill.Arcana)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var athletics: AbilityNode = AbilityNode(
    name = "Атлетика",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.skillProficiency.add(Skill.Athletics)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.skillProficiency.contains(Skill.Athletics)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var deception: AbilityNode = AbilityNode(
    name = "Обман",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.skillProficiency.add(Skill.Deception)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.skillProficiency.contains(Skill.Deception)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var history: AbilityNode = AbilityNode(
    name = "История",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.skillProficiency.add(Skill.History)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.skillProficiency.contains(Skill.History)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var insight: AbilityNode = AbilityNode(
    name = "Проницательность",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.skillProficiency.add(Skill.Insight)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.skillProficiency.contains(Skill.Insight)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var intimidation: AbilityNode = AbilityNode(
    name = "Запугивание",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.skillProficiency.add(Skill.Intimidation)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.skillProficiency.contains(Skill.Intimidation)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var investigation: AbilityNode = AbilityNode(
    name = "Анализ",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.skillProficiency.add(Skill.Investigation)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.skillProficiency.contains(Skill.Investigation)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var medicine: AbilityNode = AbilityNode(
    name = "Медицина",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.skillProficiency.add(Skill.Medicine)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.skillProficiency.contains(Skill.Medicine)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var nature: AbilityNode = AbilityNode(
    name = "Природа",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.skillProficiency.add(Skill.Nature)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.skillProficiency.contains(Skill.Nature)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var perception: AbilityNode = AbilityNode(
    name = "Внимательность",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.skillProficiency.add(Skill.Perception)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.skillProficiency.contains(Skill.Perception)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var performance: AbilityNode = AbilityNode(
    name = "Выступление",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.skillProficiency.add(Skill.Performance)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.skillProficiency.contains(Skill.Performance)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var persuasion: AbilityNode = AbilityNode(
    name = "Убеждение",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.skillProficiency.add(Skill.Persuasion)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.skillProficiency.contains(Skill.Persuasion)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var religion: AbilityNode = AbilityNode(
    name = "Религия",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.skillProficiency.add(Skill.Religion)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.skillProficiency.contains(Skill.Religion)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var sleightOfHand: AbilityNode = AbilityNode(
    name = "Ловкость рук",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.skillProficiency.add(Skill.SleightOfHand)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.skillProficiency.contains(Skill.SleightOfHand)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var stealth: AbilityNode = AbilityNode(
    name = "Скрытность",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.skillProficiency.add(Skill.Stealth)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.skillProficiency.contains(Skill.Stealth)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var survival: AbilityNode = AbilityNode(
    name = "Выживание",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.skillProficiency.add(Skill.Survival)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.skillProficiency.contains(Skill.Survival)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var acrobaticsExpertise: AbilityNode = AbilityNode(
    name = "Акробатика, экспертиза",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.expertize.add(Skill.Acrobatics)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.expertize.contains(Skill.Acrobatics) and
                abilities.skillProficiency.contains(Skill.Acrobatics)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var animalHandlingExpertise: AbilityNode = AbilityNode(
    name = "Уход за животными, экспертиза",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.expertize.add(Skill.AnimalHandling)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.expertize.contains(Skill.AnimalHandling) and
                abilities.skillProficiency.contains(Skill.AnimalHandling)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var arcanaExpertise: AbilityNode = AbilityNode(
    name = "Магия, экспертиза",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.expertize.add(Skill.Arcana)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.expertize.contains(Skill.Arcana) and
                abilities.skillProficiency.contains(Skill.Arcana)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var athleticsExpertise: AbilityNode = AbilityNode(
    name = "Атлетика, экспертиза",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.expertize.add(Skill.Athletics)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.expertize.contains(Skill.Athletics) and
                abilities.skillProficiency.contains(Skill.Athletics)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var deceptionExpertise: AbilityNode = AbilityNode(
    name = "Обман, экспертиза",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.expertize.add(Skill.Deception)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.expertize.contains(Skill.Deception) and
                abilities.skillProficiency.contains(Skill.Deception)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var historyExpertise: AbilityNode = AbilityNode(
    name = "История, экспертиза",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.expertize.add(Skill.History)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.expertize.contains(Skill.History) and
                abilities.skillProficiency.contains(Skill.History)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var insightExpertise: AbilityNode = AbilityNode(
    name = "Проницательность, экспертиза",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.expertize.add(Skill.Insight)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.expertize.contains(Skill.Insight) and
                abilities.skillProficiency.contains(Skill.Insight)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var intimidationExpertise: AbilityNode = AbilityNode(
    name = "Запугивание, экспертиза",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.expertize.add(Skill.Intimidation)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.expertize.contains(Skill.Intimidation) and
                abilities.skillProficiency.contains(Skill.Intimidation)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var investigationExpertise: AbilityNode = AbilityNode(
    name = "Анализ, экспертиза",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.expertize.add(Skill.Investigation)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.expertize.contains(Skill.Investigation) and
                abilities.skillProficiency.contains(Skill.Investigation)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var medicineExpertise: AbilityNode = AbilityNode(
    name = "Медицина, экспертиза",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.expertize.add(Skill.Medicine)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.expertize.contains(Skill.Medicine) and
                abilities.skillProficiency.contains(Skill.Medicine)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var natureExpertise: AbilityNode = AbilityNode(
    name = "Природа, экспертиза",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.expertize.add(Skill.Nature)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.expertize.contains(Skill.Nature) and
                abilities.skillProficiency.contains(Skill.Nature)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var perceptionExpertise: AbilityNode = AbilityNode(
    name = "Внимательность, экспертиза",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.expertize.add(Skill.Perception)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.expertize.contains(Skill.Perception) and
                abilities.skillProficiency.contains(Skill.Perception)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var performanceExpertise: AbilityNode = AbilityNode(
    name = "Выступление, экспертиза",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.expertize.add(Skill.Performance)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.expertize.contains(Skill.Performance) and
                abilities.skillProficiency.contains(Skill.Performance)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var persuasionExpertise: AbilityNode = AbilityNode(
    name = "Убеждение, экспертиза",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.expertize.add(Skill.Persuasion)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.expertize.contains(Skill.Persuasion) and
                abilities.skillProficiency.contains(Skill.Persuasion)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var religionExpertise: AbilityNode = AbilityNode(
    name = "Религия, экспертиза",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.expertize.add(Skill.Religion)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.expertize.contains(Skill.Religion) and
                abilities.skillProficiency.contains(Skill.Religion)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var sleightOfHandExpertise: AbilityNode = AbilityNode(
    name = "Ловкость рук, экспертиза",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.expertize.add(Skill.SleightOfHand)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.expertize.contains(Skill.SleightOfHand) and
                abilities.skillProficiency.contains(Skill.SleightOfHand)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var stealthExpertise: AbilityNode = AbilityNode(
    name = "Скрытность, экспертиза",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.expertize.add(Skill.Stealth)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.expertize.contains(Skill.Stealth) and
                abilities.skillProficiency.contains(Skill.Stealth)
    },
    description = "",
    isNeedsToBeShown = false,
    priority = Priority.DoFirst
)

var survivalExpertise: AbilityNode = AbilityNode(
    name = "Выживание, экспертиза",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.expertize.add(Skill.Survival)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        !abilities.expertize.contains(Skill.Survival) and
                abilities.skillProficiency.contains(Skill.Survival)
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
    Pair(survival.name, survival),
    Pair(acrobaticsExpertise.name, acrobaticsExpertise),
    Pair(animalHandlingExpertise.name, animalHandlingExpertise),
    Pair(arcanaExpertise.name, arcanaExpertise),
    Pair(athleticsExpertise.name, athleticsExpertise),
    Pair(deceptionExpertise.name, deceptionExpertise),
    Pair(historyExpertise.name, historyExpertise),
    Pair(insightExpertise.name, insightExpertise),
    Pair(intimidationExpertise.name, intimidationExpertise),
    Pair(investigationExpertise.name, investigationExpertise),
    Pair(medicineExpertise.name, medicineExpertise),
    Pair(natureExpertise.name, natureExpertise),
    Pair(perceptionExpertise.name, perceptionExpertise),
    Pair(performanceExpertise.name, performanceExpertise),
    Pair(persuasionExpertise.name, persuasionExpertise),
    Pair(religionExpertise.name, religionExpertise),
    Pair(sleightOfHandExpertise.name, sleightOfHandExpertise),
    Pair(stealthExpertise.name, stealthExpertise),
    Pair(survivalExpertise.name, survivalExpertise)
)