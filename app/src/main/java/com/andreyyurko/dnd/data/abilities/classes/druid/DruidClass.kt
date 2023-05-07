package com.andreyyurko.dnd.data.abilities.classes.druid

import com.andreyyurko.dnd.data.abilities.classes.AbilityNodeLevel
import com.andreyyurko.dnd.data.abilities.classes.wizard.schoolOfConjuration
import com.andreyyurko.dnd.data.abilities.classes.wizard.schoolOfDivination
import com.andreyyurko.dnd.data.abilities.classes.wizard.spellCastingWizard
import com.andreyyurko.dnd.data.abilities.other.abilityScoreImprovement
import com.andreyyurko.dnd.data.abilities.other.animalHandling
import com.andreyyurko.dnd.data.abilities.other.arcana
import com.andreyyurko.dnd.data.abilities.other.herbalismTools
import com.andreyyurko.dnd.data.abilities.other.insight
import com.andreyyurko.dnd.data.abilities.other.medicine
import com.andreyyurko.dnd.data.abilities.other.nature
import com.andreyyurko.dnd.data.abilities.other.perception
import com.andreyyurko.dnd.data.abilities.other.survival
import com.andreyyurko.dnd.data.characterData.Ability
import com.andreyyurko.dnd.data.characterData.Action
import com.andreyyurko.dnd.data.characterData.ActionType
import com.andreyyurko.dnd.data.characterData.ArmorProf
import com.andreyyurko.dnd.data.characterData.CharacterInfo
import com.andreyyurko.dnd.data.characterData.ChargesCounter
import com.andreyyurko.dnd.data.characterData.Classes
import com.andreyyurko.dnd.data.characterData.Priority
import com.andreyyurko.dnd.data.characterData.Weapon
import com.andreyyurko.dnd.data.characterData.character.AbilityNode
import com.andreyyurko.dnd.data.characterData.character.abilityToModifier
import com.andreyyurko.dnd.data.spells.CharacterSpells
import kotlin.math.max

var classFeaturesDruid: AbilityNode = AbilityNode(
    name = "Друид: классовые умения",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.savingThrowProf.add(Ability.Wisdom)
        abilities.savingThrowProf.add(Ability.Intelligence)
        abilities.weaponProficiency.add(Weapon.Quarterstaff)
        abilities.weaponProficiency.add(Weapon.Mace)
        abilities.weaponProficiency.add(Weapon.Dart)
        abilities.weaponProficiency.add(Weapon.Club)
        abilities.weaponProficiency.add(Weapon.Dagger)
        abilities.weaponProficiency.add(Weapon.Spear)
        abilities.weaponProficiency.add(Weapon.Javelin)
        abilities.weaponProficiency.add(Weapon.Sling)
        abilities.weaponProficiency.add(Weapon.Sickle)
        abilities.weaponProficiency.add(Weapon.Scimitar)
        abilities.armorProficiency.add(ArmorProf.LightArmor)
        abilities.armorProficiency.add(ArmorProf.MediumArmor)
        abilities.armorProficiency.add(ArmorProf.Shield)
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair(
            "tool",
            { listOf(herbalismTools.name) }
        ),
        Pair(
            "skill1",
            { listOf(perception.name, survival.name, arcana.name, medicine.name, animalHandling.name, nature.name, insight.name) }
        ),
        Pair(
            "skill2",
            { listOf(perception.name, survival.name, arcana.name, medicine.name, animalHandling.name, nature.name, insight.name) }
        ),
    ),
    requirements = { abilities: CharacterInfo ->
        abilities.characterClass == Classes.Druid
    },
    description = "ХИТЫ\n" +
            "\n" +
            "Кость Хитов: 1к8 за каждый уровень друида\n" +
            "\n" +
            "Хиты на 1 уровне: 8 + ваш модификатор Телосложения\n" +
            "\n" +
            "Хиты на следующих уровнях: 1к8 (или 5) + модификатор Телосложения (суммарно минимум 1) за каждый уровень друида после первого\n" +
            "ВЛАДЕНИЕ\n" +
            "\n" +
            "Доспехи: Лёгкие доспехи, средние доспехи, щиты (друиды не носят доспехи и щиты из металла)\n" +
            "\n" +
            "Оружие: Боевые посохи, булавы, дротики, дубинки, кинжалы, копья, метательные копья, пращи, серпы, скимитары\n" +
            "\n" +
            "Инструменты: Набор травника\n" +
            "\n" +
            "Спасброски: Интеллект, Мудрость\n" +
            "\n" +
            "Навыки: Выберите два навыка из следующих: Восприятие, Выживание, Магия, Медицина, Уход за животными, Природа, Проницательность, Религия\n",
    priority = Priority.DoFirst
)

var druidic: AbilityNode = AbilityNode(
    name = "Друидический язык",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities[""] = "Вы знаете Друидический язык — тайный язык друидов. Вы можете на нём говорить и оставлять тайные послания. Вы и все, кто знают этот язык, автоматически замечаете эти послания. Другие замечают присутствие послания при успешной проверке Мудрости (Восприятие) Сл 15, но без помощи магии не могут расшифровать его.\n"
        abilities
    },
    getAlternatives = mutableMapOf(
    ),
    requirements = { true },
    description = "Вы знаете Друидический язык — тайный язык друидов. Вы можете на нём говорить и оставлять тайные послания. Вы и все, кто знают этот язык, автоматически замечаете эти послания. Другие замечают присутствие послания при успешной проверке Мудрости (Восприятие) Сл 15, но без помощи магии не могут расшифровать его.\n"
)

var spellCastingDruid: AbilityNode = AbilityNode(
    name = "Друид: использование заклинаний",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.spellsInfo.apply {
            if (!this.contains("Заклинания класса")) {
                this["Заклинания класса"] = CharacterSpells()
            }
            this["Заклинания класса"]?.className = Classes.Druid.className
            this["Заклинания класса"]?.maxPreparedSpellsCount =
                max((abilityToModifier(abilities.wisdom) + abilities.level), 1)
            this["Заклинания класса"]?.maxKnownCantripsCount = 2
            if ((abilities.level > 3) and (abilities.level < 10))
                this["Заклинания класса"]?.maxKnownCantripsCount = 3
            if (abilities.level > 9)
                this["Заклинания класса"]?.maxKnownCantripsCount = 4
        }
        abilities.additionalAbilities["Ритуальное колдовство"] =
            "Вы можете сотворить любое известное вам заклинание друида в качестве ритуала, если заклинание позволяет это.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { abilities: CharacterInfo ->
        abilities.characterClass == Classes.Druid
    },
    description = "Для накладывания заклинаний друиды пользуются сакральной эссенцией самой природы, облачая в неё свою волю. Вы найдёте список заклинаний, доступных друиду в этом разделе: заклинания друида.\n" +
            "Заговоры (заклинания 0-го уровня)\n" +
            "\n" +
            "На 1-м уровне вы знаете два заговора на ваш выбор из списка заклинаний друида. Вы узнаёте дополнительные заговоры друида на более высоких уровнях, как показано в колонке «Известные заговоры».\n" +
            "Подготовка и сотворение заклинаний\n" +
            "\n" +
            "Таблица «Друид» показывает, какое количество ячеек для накладывания заклинаний друида у вас есть на первом и более высоких уровнях. Для накладывания одного из этих заклинаний вы должны потратить ячейку заклинания того же уровня или выше, что и само заклинание. Вы восстановите все потраченные ячейки, когда завершите продолжительный отдых. Вы подготавливаете список заклинаний друида, доступных для накладывания. При этом вы выбираете число заклинаний друида из списка заклинаний друида, равное модификатору Мудрости + уровень друида (минимум одно заклинание). Уровень заклинаний не должен превышать уровень самой высокой имеющейся у вас ячейки заклинаний.\n" +
            "\n" +
            "Например, если вы друид 3-го уровня, то у вас есть четыре ячейки заклинаний 1-го уровня и две ячейки 2-го уровня. При Мудрости 16 ваш список подготовленных заклинаний может включать в себя шесть заклинаний 1-го или 2-го уровня, в любой комбинации. Если вы подготовили заклинание 1-го уровня лечение ран [cure wounds], вы можете наложить его, используя ячейку 1-го уровня или ячейку 2-го уровня. Накладывание заклинания не удаляет его из списка подготовленных заклинаний.\n" +
            "\n" +
            "Вы можете изменить список подготовленных заклинаний, когда завершаете продолжительный отдых. Подготовка нового списка заклинаний друида требует времени, проведённого в молитвах и медитации: не менее 1 минуты за уровень заклинания для каждого заклинания в вашем списке.\n" +
            "Базовая характеристика заклинаний\n" +
            "\n" +
            "Друидические заклинания накладываются с помощью Мудрости, поскольку требуют веры и близости с природой. Вы используете Мудрость в случаях, когда заклинание ссылается на базовую характеристику. Кроме того, вы используете модификатор Мудрости при определении Сл спасбросков от ваших заклинаний друида, и при броске атаки заклинаниями.\n" +
            "\n" +
            "Сл спасброска = 8 + ваш бонус мастерства + ваш модификатор Мудрости\n" +
            "\n" +
            "Модификатор броска атаки = ваш бонус мастерства + ваш модификатор Мудрости\n" +
            "Ритуальное колдовство\n" +
            "\n" +
            "Вы можете сотворить заклинание друида как ритуал, если у этого заклинания есть ключевое слово «ритуал», и оно подготовлено вами.\n" +
            "Фокусировка заклинания\n" +
            "\n" +
            "Вы можете использовать фокусировку друидов в качестве заклинательной фокусировки для заклинаний друида.\n"
)

var druid1: AbilityNode = AbilityNodeLevel(
    name = "Друид_1",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.characterClass = Classes.Druid
        abilities.level += 1
        abilities.proficiencyBonus += 2
        abilities.hp += abilityToModifier(abilities.constitution) + 8
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(classFeaturesDruid.name) }),
        Pair("second", { listOf(druidic.name) }),
        Pair("third", { listOf(spellCastingDruid.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "1-й уровень, способности друида",
    next_level = "Друид_2"
)

var wildShape: AbilityNode = AbilityNode(
    name = "Дикий облик",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        if (!abilities.currentState.charges.contains("Дикий облик")) {
            abilities.currentState.charges["Дикий облик"] = ChargesCounter(
                current = 2,
                maximum = 2
            )
        }
        abilities.actionsList.add(
            Action(
                name = "Дикий облик",
                description = "Действием вы можете магическим образом принять облик любого Зверя, которого вы видели. Вы можете использовать это умение два раза, восстанавливая использования после короткого или продолжительного отдыха.\n" +
                        "\n" +
                        "Уровень друида определяет, в каких Зверей можно превращаться. Например, на 2-м уровне можно превращаться в животное с показателем опасности не более 1/4 без скорости полёта и плавания.\n" +
                        "Облик ЗВЕРЯ\n" +
                        "Уровень | Макс. ПО | Ограничения | Пример\n" +
                        "2 | ¼ | Без скорости плавания и полёта | Волк [wolf]\n" +
                        "4 | ½ | Без скорости полёта | Крокодил [crocodile]\n" +
                        "8 | 1 | - | Гигантский орел [giant eagle]\n" +
                        "\n" +
                        "В облике зверя можно оставаться количество часов, равное половине уровня друида (округляя в меньшую сторону). Затем друид возвращается в нормальный облик, если только не потратит еще одно использование «Дикого облика». Бонусным действием можно вернуться в нормальный облик досрочно. Бессознательный, доведённый до 0 хитов или мёртвый друид сразу возвращается в нормальный облик.\n" +
                        "\n" +
                        "Когда вы превращены, действуют следующие правила:\n" +
                        "\n" +
                        "    Вся игровая статистика берётся из статистики Зверя, но сохраняются мировоззрение, личность и значения Интеллекта, Мудрости и Харизмы. Также у вас остаются владения навыками и спасбросками в дополнение к таковым нового облика. Если владение есть и у вас, и у Зверя, но у него бонус выше, используется бонус Зверя. Легендарные действия и действия в логове недоступны.\n" +
                        "    Когда вы превращены, вы принимаете хиты и Кость Хитов Зверя. Вернувшись в нормальный облик, ваши хиты будут такими же, как до превращения. Однако, если вы вернулись в свой облик из-за того, что ваши хиты опустились до 0, «лишний» урон переносится на нормальный облик. Например, если вы в облике Зверя получили 10 урона, имея при этом 1 хит, то вы возвращаетесь в нормальный облик и получаете 9 урона. Если этот урон не довёл хиты персонажа до 0, он не теряет сознание.\n" +
                        "    Вы не можете накладывать заклинания, а речь и действия, требующие рук, могут быть ограничены видом Зверя. Превращение не прерывает вашу концентрацию на уже наложенных заклинаниях и не мешает совершать действия, являющиеся частью заклинания, такие как в случае заклинания призыв молнии [call lightning].\n" +
                        "    Вы сохраняете преимущества от всех умений класса, расы и прочих источников и можете пользоваться ими, если этому не препятствует новый физический облик. Однако недоступны особые чувства, такие как тёмное зрение, если только их нет у Зверя.\n" +
                        "    Вы выбираете, упадет ли ваше снаряжение на землю в вашем пространстве, сольется с вашей новой формой или будет надето на нее. Носимое снаряжение функционирует как обычно, но Мастер решает, может ли новая форма, в зависимости от сложения и размера, носить тот или иной предмет снаряжения. Снаряжение не меняет форму и размер под новый облик, и если оно не подходит новому облику, оно должно остаться на земле или слиться с новым обликом. Слившееся с обликом снаряжение не работает, пока вы опять не примете свой облик.\n",
                type = ActionType.Action,
                relatedCharges = "Дикий облик"
            )
        )
        abilities
    },
    getAlternatives = mutableMapOf(
    ),
    requirements = { true },
    description = "Действием вы можете магическим образом принять облик любого Зверя, которого вы видели. Вы можете использовать это умение два раза, восстанавливая использования после короткого или продолжительного отдыха.\n" +
            "\n" +
            "Уровень друида определяет, в каких Зверей можно превращаться. Например, на 2-м уровне можно превращаться в животное с показателем опасности не более 1/4 без скорости полёта и плавания.\n" +
            "Облик ЗВЕРЯ\n" +
            "Уровень | Макс. ПО | Ограничения | Пример\n" +
            "2 | ¼ | Без скорости плавания и полёта | Волк [wolf]\n" +
            "4 | ½ | Без скорости полёта | Крокодил [crocodile]\n" +
            "8 | 1 | - | Гигантский орел [giant eagle]\n" +
            "\n" +
            "В облике зверя можно оставаться количество часов, равное половине уровня друида (округляя в меньшую сторону). Затем друид возвращается в нормальный облик, если только не потратит еще одно использование «Дикого облика». Бонусным действием можно вернуться в нормальный облик досрочно. Бессознательный, доведённый до 0 хитов или мёртвый друид сразу возвращается в нормальный облик.\n" +
            "\n" +
            "Когда вы превращены, действуют следующие правила:\n" +
            "\n" +
            "    Вся игровая статистика берётся из статистики Зверя, но сохраняются мировоззрение, личность и значения Интеллекта, Мудрости и Харизмы. Также у вас остаются владения навыками и спасбросками в дополнение к таковым нового облика. Если владение есть и у вас, и у Зверя, но у него бонус выше, используется бонус Зверя. Легендарные действия и действия в логове недоступны.\n" +
            "    Когда вы превращены, вы принимаете хиты и Кость Хитов Зверя. Вернувшись в нормальный облик, ваши хиты будут такими же, как до превращения. Однако, если вы вернулись в свой облик из-за того, что ваши хиты опустились до 0, «лишний» урон переносится на нормальный облик. Например, если вы в облике Зверя получили 10 урона, имея при этом 1 хит, то вы возвращаетесь в нормальный облик и получаете 9 урона. Если этот урон не довёл хиты персонажа до 0, он не теряет сознание.\n" +
            "    Вы не можете накладывать заклинания, а речь и действия, требующие рук, могут быть ограничены видом Зверя. Превращение не прерывает вашу концентрацию на уже наложенных заклинаниях и не мешает совершать действия, являющиеся частью заклинания, такие как в случае заклинания призыв молнии [call lightning].\n" +
            "    Вы сохраняете преимущества от всех умений класса, расы и прочих источников и можете пользоваться ими, если этому не препятствует новый физический облик. Однако недоступны особые чувства, такие как тёмное зрение, если только их нет у Зверя.\n" +
            "    Вы выбираете, упадет ли ваше снаряжение на землю в вашем пространстве, сольется с вашей новой формой или будет надето на нее. Носимое снаряжение функционирует как обычно, но Мастер решает, может ли новая форма, в зависимости от сложения и размера, носить тот или иной предмет снаряжения. Снаряжение не меняет форму и размер под новый облик, и если оно не подходит новому облику, оно должно остаться на земле или слиться с новым обликом. Слившееся с обликом снаряжение не работает, пока вы опять не примете свой облик.\n"
)

var druidCircle: AbilityNode = AbilityNode(
    name = "Круг друидов",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(circleOfTheMoon.name, circleOfTheShepherd.name) })
    ),
    requirements = { true },
    description = "Вам необходимо выбрать, в каком круге друидов состоит персонаж. Этот выбор даёт дополнительные умения на 2-м, 6-м, 10-м и 14-м уровнях.\n"
)

var druid2: AbilityNode = AbilityNodeLevel(
    name = "Друид_2",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(wildShape.name) }),
        Pair("second", { listOf(druidCircle.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "2-й уровень, способности друида",
    next_level = "Друид_3"
)

var druid3: AbilityNode = AbilityNodeLevel(
    name = "Друид_3",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "3-й уровень, способности друида",
    next_level = "Друид_4"
)

var druid4: AbilityNode = AbilityNodeLevel(
    name = "Друид_4",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(abilityScoreImprovement.name) }),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "4-й уровень, способности друида",
    next_level = "Друид_5"
)

var druid5: AbilityNode = AbilityNodeLevel(
    name = "Друид_5",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.proficiencyBonus += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "5-й уровень, способности друида",
    next_level = "Друид_6"
)

var druid6: AbilityNode = AbilityNodeLevel(
    name = "Друид_6",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "6-й уровень, способности друида",
    next_level = "Друид_7"
)

var druid7: AbilityNode = AbilityNodeLevel(
    name = "Друид_7",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "7-й уровень, способности друида",
    next_level = "Друид_8"
)

var druid8: AbilityNode = AbilityNodeLevel(
    name = "Друид_8",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(abilityScoreImprovement.name) }),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "8-й уровень, способности друида",
    next_level = "Друид_9"
)

var druid9: AbilityNode = AbilityNodeLevel(
    name = "Друид_9",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.proficiencyBonus += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "9-й уровень, способности друида",
    next_level = "Друид_10"
)

var druid10: AbilityNode = AbilityNodeLevel(
    name = "Друид_10",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "10-й уровень, способности друида",
    next_level = "Друид_11"
)

var druid11: AbilityNode = AbilityNodeLevel(
    name = "Друид_11",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "11-й уровень, способности друида",
    next_level = "Друид_12"
)

var druid12: AbilityNode = AbilityNodeLevel(
    name = "Друид_12",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(abilityScoreImprovement.name) }),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "12-й уровень, способности друида",
    next_level = "Друид_13"
)

var druid13: AbilityNode = AbilityNodeLevel(
    name = "Друид_13",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.proficiencyBonus += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "13-й уровень, способности друида",
    next_level = "Друид_14"
)

var druid14: AbilityNode = AbilityNodeLevel(
    name = "Друид_14",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "14-й уровень, способности друида",
    next_level = "Друид_15"
)

var druid15: AbilityNode = AbilityNodeLevel(
    name = "Друид_15",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "15-й уровень, способности друида",
    next_level = "Друид_16"
)

var druid16: AbilityNode = AbilityNodeLevel(
    name = "Друид_16",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(abilityScoreImprovement.name) }),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "16-й уровень, способности друида",
    next_level = "Друид_17"
)

var druid17: AbilityNode = AbilityNodeLevel(
    name = "Друид_17",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.proficiencyBonus += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "17-й уровень, способности друида",
    next_level = "Друид_18"
)

var timelessBody: AbilityNode = AbilityNode(
    name = "Безвременное тело",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.additionalAbilities["Безвременное тело"] = "Пронизывающая вас природная магия замедляет старение. За каждые 10 лет ваше тело стареет только на 1 год.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    description = "Пронизывающая вас природная магия замедляет старение. За каждые 10 лет ваше тело стареет только на 1 год.\n"
)

var beastSpells: AbilityNode = AbilityNode(
    name = "Заклинания зверя",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        for (action in abilities.actionsList) {
            if (action.name == "Дикий облик") {
                action.description = "Действием вы можете магическим образом принять облик любого Зверя, которого вы видели. Вы можете использовать это умение два раза, восстанавливая использования после короткого или продолжительного отдыха.\n" +
                        "\n" +
                        "Уровень друида определяет, в каких Зверей можно превращаться. Например, на 2-м уровне можно превращаться в животное с показателем опасности не более 1/4 без скорости полёта и плавания.\n" +
                        "Облик ЗВЕРЯ\n" +
                        "Уровень | Макс. ПО | Ограничения | Пример\n" +
                        "2 | ¼ | Без скорости плавания и полёта | Волк [wolf]\n" +
                        "4 | ½ | Без скорости полёта | Крокодил [crocodile]\n" +
                        "8 | 1 | - | Гигантский орел [giant eagle]\n" +
                        "\n" +
                        "В облике зверя можно оставаться количество часов, равное половине уровня друида (округляя в меньшую сторону). Затем друид возвращается в нормальный облик, если только не потратит еще одно использование «Дикого облика». Бонусным действием можно вернуться в нормальный облик досрочно. Бессознательный, доведённый до 0 хитов или мёртвый друид сразу возвращается в нормальный облик.\n" +
                        "\n" +
                        "Когда вы превращены, действуют следующие правила:\n" +
                        "\n" +
                        "    Вся игровая статистика берётся из статистики Зверя, но сохраняются мировоззрение, личность и значения Интеллекта, Мудрости и Харизмы. Также у вас остаются владения навыками и спасбросками в дополнение к таковым нового облика. Если владение есть и у вас, и у Зверя, но у него бонус выше, используется бонус Зверя. Легендарные действия и действия в логове недоступны.\n" +
                        "    Когда вы превращены, вы принимаете хиты и Кость Хитов Зверя. Вернувшись в нормальный облик, ваши хиты будут такими же, как до превращения. Однако, если вы вернулись в свой облик из-за того, что ваши хиты опустились до 0, «лишний» урон переносится на нормальный облик. Например, если вы в облике Зверя получили 10 урона, имея при этом 1 хит, то вы возвращаетесь в нормальный облик и получаете 9 урона. Если этот урон не довёл хиты персонажа до 0, он не теряет сознание.\n" +
                        "    Вы можете использовать соматический и вербальный компоненты заклинаний друида, но не можете обеспечить материальные компоненты.\n" +
                        "    Ваши речь и действия, требующие рук, могут быть ограничены видом Зверя. Превращение не прерывает вашу концентрацию на уже наложенных заклинаниях и не мешает совершать действия, являющиеся частью заклинания, такие как в случае заклинания призыв молнии [call lightning].\n" +
                        "    Вы сохраняете преимущества от всех умений класса, расы и прочих источников и можете пользоваться ими, если этому не препятствует новый физический облик. Однако недоступны особые чувства, такие как тёмное зрение, если только их нет у Зверя.\n" +
                        "    Вы выбираете, упадет ли ваше снаряжение на землю в вашем пространстве, сольется с вашей новой формой или будет надето на нее. Носимое снаряжение функционирует как обычно, но Мастер решает, может ли новая форма, в зависимости от сложения и размера, носить тот или иной предмет снаряжения. Снаряжение не меняет форму и размер под новый облик, и если оно не подходит новому облику, оно должно остаться на земле или слиться с новым обликом. Слившееся с обликом снаряжение не работает, пока вы опять не примете свой облик.\n"
            }
        }
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    description = "Персонаж может накладывать в диком облике заклинания друида. Будучи Зверем, вы можете использовать соматический и вербальный компоненты заклинаний друида, но не можете обеспечить материальные компоненты.\n"
)

var druid18: AbilityNode = AbilityNodeLevel(
    name = "Друид_18",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(timelessBody.name) }),
        Pair("second", { listOf(beastSpells.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "18-й уровень, способности друида",
    next_level = "Друид_19"
)

var druid19: AbilityNode = AbilityNodeLevel(
    name = "Друид_19",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(abilityScoreImprovement.name) }),
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "19-й уровень, способности друида",
    next_level = "Друид_20"
)

var archDruid: AbilityNode = AbilityNode(
    name = "Архидруид",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        for (action in abilities.actionsList) {
            if (action.name == "Дикий облик") {
                action.relatedCharges = ""
            }
        }
        abilities.additionalAbilities["Архидруид"] = "Вы можете игнорировать соматический и вербальный компоненты заклинаний друида, а также материальные компоненты без указанной стоимости и не расходуемые заклинанием. Это действует как в нормальном облике, так и в облике Зверя.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    description = "Количество применений умения «Дикий облик» не ограничено. Кроме того, вы можете игнорировать соматический и вербальный компоненты заклинаний друида, а также материальные компоненты без указанной стоимости и не расходуемые заклинанием. Это действует как в нормальном облике, так и в облике Зверя.\n"
)

var druid20: AbilityNode = AbilityNodeLevel(
    name = "Друид_20",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.level += 1
        abilities.hp += abilityToModifier(abilities.constitution) + 5
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(archDruid.name) })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "20-й уровень, способности друида",
    next_level = null
)

var mapOfDruidAbilities: MutableMap<String, AbilityNode> = (mutableMapOf(
    Pair(classFeaturesDruid.name, classFeaturesDruid),
    Pair(druidic.name, druidic),
    Pair(spellCastingDruid.name, spellCastingDruid),
    Pair(druid1.name, druid1),
    Pair(wildShape.name, wildShape),
    Pair(druidCircle.name, druidCircle),
    Pair(druid2.name, druid2),
    Pair(druid3.name, druid3),
    Pair(druid4.name, druid4),
    Pair(druid5.name, druid5),
    Pair(druid6.name, druid6),
    Pair(druid7.name, druid7),
    Pair(druid8.name, druid8),
    Pair(druid9.name, druid9),
    Pair(druid10.name, druid10),
    Pair(druid11.name, druid11),
    Pair(druid12.name, druid12),
    Pair(druid13.name, druid13),
    Pair(druid14.name, druid14),
    Pair(druid15.name, druid15),
    Pair(druid16.name, druid16),
    Pair(druid17.name, druid17),
    Pair(timelessBody.name, timelessBody),
    Pair(beastSpells.name, beastSpells),
    Pair(druid18.name, druid18),
    Pair(druid19.name, druid19),
    Pair(archDruid.name, archDruid),
    Pair(druid20.name, druid20),
)
        + mapOfСircleOfTheMoonAbilities
        + mapOfСircleOfTheShepHerdAbilities
        ).toMutableMap()