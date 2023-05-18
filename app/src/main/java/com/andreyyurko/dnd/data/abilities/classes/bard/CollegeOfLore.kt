package com.andreyyurko.dnd.data.abilities.classes.bard

import com.andreyyurko.dnd.data.abilities.other.acrobatics
import com.andreyyurko.dnd.data.abilities.other.animalHandling
import com.andreyyurko.dnd.data.abilities.other.arcana
import com.andreyyurko.dnd.data.abilities.other.athletics
import com.andreyyurko.dnd.data.abilities.other.deception
import com.andreyyurko.dnd.data.abilities.other.history
import com.andreyyurko.dnd.data.abilities.other.insight
import com.andreyyurko.dnd.data.abilities.other.intimidation
import com.andreyyurko.dnd.data.abilities.other.investigation
import com.andreyyurko.dnd.data.abilities.other.medicine
import com.andreyyurko.dnd.data.abilities.other.nature
import com.andreyyurko.dnd.data.abilities.other.perception
import com.andreyyurko.dnd.data.abilities.other.performance
import com.andreyyurko.dnd.data.abilities.other.persuasion
import com.andreyyurko.dnd.data.abilities.other.religion
import com.andreyyurko.dnd.data.abilities.other.sleightOfHand
import com.andreyyurko.dnd.data.abilities.other.stealth
import com.andreyyurko.dnd.data.abilities.other.survival
import com.andreyyurko.dnd.data.characterData.Action
import com.andreyyurko.dnd.data.characterData.ActionType
import com.andreyyurko.dnd.data.characterData.CharacterInfo
import com.andreyyurko.dnd.data.characterData.Classes
import com.andreyyurko.dnd.data.characterData.character.AbilityNode
import com.andreyyurko.dnd.data.spells.CharacterSpells
import com.andreyyurko.dnd.data.spells.SpellLists
import com.andreyyurko.dnd.data.spells.spellist

var collegeOfLoreBonusProficiencies = AbilityNode(
    name = "Дополнительные навыки коллегии знаний",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair(
            "skill1",
            {
                listOf(
                    acrobatics.name,
                    animalHandling.name,
                    arcana.name,
                    athletics.name,
                    deception.name,
                    history.name,
                    insight.name,
                    intimidation.name,
                    investigation.name,
                    medicine.name,
                    nature.name,
                    perception.name,
                    performance.name,
                    persuasion.name,
                    religion.name,
                    sleightOfHand.name,
                    stealth.name,
                    survival.name
                )
            }
        ),
        Pair(
            "skill2",
            {
                listOf(
                    acrobatics.name,
                    animalHandling.name,
                    arcana.name,
                    athletics.name,
                    deception.name,
                    history.name,
                    insight.name,
                    intimidation.name,
                    investigation.name,
                    medicine.name,
                    nature.name,
                    perception.name,
                    performance.name,
                    persuasion.name,
                    religion.name,
                    sleightOfHand.name,
                    stealth.name,
                    survival.name
                )
            }
        ),
        Pair(
            "skill3",
            {
                listOf(
                    acrobatics.name,
                    animalHandling.name,
                    arcana.name,
                    athletics.name,
                    deception.name,
                    history.name,
                    insight.name,
                    intimidation.name,
                    investigation.name,
                    medicine.name,
                    nature.name,
                    perception.name,
                    performance.name,
                    persuasion.name,
                    religion.name,
                    sleightOfHand.name,
                    stealth.name,
                    survival.name
                )
            }
        ),
    ),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 3 && abilities.characterClass == Classes.Bard
    },
    description = "Если вы присоединяетесь к коллегии знаний, вы овладеваете тремя навыками на ваш выбор.\n"
)

var cuttingWords = AbilityNode(
    name = "Острое словцо",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsMap["Острое словцо"] =
            Action(
                name = "Острое словцо",
                description = "Вы узнаёте, как использовать собственное остроумие, чтобы отвлечь, смутить или по-другому подорвать способности и уверенность противников. Если существо, которое вы можете видеть, в пределах 60 футов от вас совершает бросок атаки, урона или проверку характеристики, вы можете реакцией потратить одну из ваших костей бардовского вдохновения, и вычесть результат броска этой кости из броска этого существа. Вы можете принять решение об использовании этого умения после броска существа, но до того момента, когда Мастер объявит результат броска или проверки. Существо не подвержено этому умению, если не может слышать вас, или обладает иммунитетом к очарованию.\n",
                type = ActionType.Reaction,
                relatedCharges = "Бардовское вдохновение"
            )

        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 3 && abilities.characterClass == Classes.Bard
    },
    description = "Вы узнаёте, как использовать собственное остроумие, чтобы отвлечь, смутить или по-другому подорвать способности и уверенность противников. Если существо, которое вы можете видеть, в пределах 60 футов от вас совершает бросок атаки, урона или проверку характеристики, вы можете реакцией потратить одну из ваших костей бардовского вдохновения, и вычесть результат броска этой кости из броска этого существа. Вы можете принять решение об использовании этого умения после броска существа, но до того момента, когда Мастер объявит результат броска или проверки. Существо не подвержено этому умению, если не может слышать вас, или обладает иммунитетом к очарованию.\n"
)

var additionalMagicalSecrets = AbilityNode(
    name = "Дополнительные тайны магии",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { spellist.filter { it.level <= 3 }.map { it.name } }),
        Pair("second", { spellist.filter { it.level <= 3 }.map { it.name } })
    ),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 6 && abilities.characterClass == Classes.Bard
    },
    description = "Вы можете выучить 2 заклинания из доступных любому классу на свой выбор. Их уровень не должен превышать уровня заклинаний, которые вы можете использовать на этом уровне, как показано в таблице Барда. Они также могут быть заговорами. Выбранные заклинания теперь считаются для вас заклинаниями барда, но они не учитываются в общем количестве известных вам заклинаний барда.\n",
    actionForChoice = mutableMapOf(
        Pair("first") { choice: String, abilities: CharacterInfo ->
            abilities.spellsInfo["Тайны магии 6 уровня 1 заклинание"] = CharacterSpells(
                className = abilities.characterClass.className,
                maxKnownSpellsCount = 0,
                maxKnownCantripsCount = 0,
                spellLists = SpellLists(
                    knownSpells = mutableSetOf(choice)
                )
            )
            abilities
        },
        Pair("second") { choice: String, abilities: CharacterInfo ->
            abilities.spellsInfo["Тайны магии 6 уровня 2 заклинание"] = CharacterSpells(
                className = abilities.characterClass.className,
                maxKnownSpellsCount = 0,
                maxKnownCantripsCount = 0,
                spellLists = SpellLists(
                    knownSpells = mutableSetOf(choice)
                )
            )
            abilities
        }
    )
)

var peerlessSkill = AbilityNode(
    name = "Непревзойдённый навык",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.actionsMap["Непревзойдённый навык"] =
            Action(
                name = "Непревзойдённый навык",
                description = "Если вы совершаете проверку характеристики, вы можете бросить кость бардовского вдохновения и добавить результат к проверке. Вы можете принять решение об использовании этого умения после броска проверки, но до того, как Мастер объявит результат этой проверки.\n",
                type = ActionType.PartOfAction,
                relatedCharges = "Бардовское вдохновение"
            )
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.level >= 14 && abilities.characterClass == Classes.Bard
    },
    description = "Если вы совершаете проверку характеристики, вы можете бросить кость бардовского вдохновения и добавить результат к проверке. Вы можете принять решение об использовании этого умения после броска проверки, но до того, как Мастер объявит результат этой проверки.\n"
)

var collegeOfLore = AbilityNode(
    name = "Коллегия знаний",
    changesInCharacterInfo = { abilities: CharacterInfo -> abilities },
    getAlternatives = mutableMapOf(
        Pair("first") { listOf(collegeOfLoreBonusProficiencies.name) },
        Pair("second") { listOf(cuttingWords.name) },
        Pair("third") { listOf(additionalMagicalSecrets.name) },
        Pair("fourth") { listOf(peerlessSkill.name) }
    ),
    requirements = { true },
    description = "",
    isNeedsToBeShown = false
)

var mapOfCollegeOfLoreAbilities: MutableMap<String, AbilityNode> = mutableMapOf(
    Pair(collegeOfLoreBonusProficiencies.name, collegeOfLoreBonusProficiencies),
    Pair(cuttingWords.name, cuttingWords),
    Pair(additionalMagicalSecrets.name, additionalMagicalSecrets),
    Pair(peerlessSkill.name, peerlessSkill),
    Pair(collegeOfLore.name, collegeOfLore),
)