package com.andreyyurko.dnd.data.abilities.characterclass

import android.util.Log
import com.andreyyurko.dnd.data.abilities.baseAN
import com.andreyyurko.dnd.data.characters.*

var monk_unarmed_defence: AbilityNode = AbilityNode(
    name = "Защита без доспехов",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        if (abilities.currentState.armor == Armor.NoArmor)
            abilities.ac = abilities.ac + (abilities.wisdomBonus - 10) / 2
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        abilities.characterClass == Classes.Monk && abilities.level >= 1
    },
    description = "Если вы не носите ни доспех, ни щит, ваш Класс Доспеха равен 10 + модификатор Ловкости + модификатор Мудрости.",
)

var monk_martial_arts: AbilityNode = AbilityNode(
    name = "Боевые искусства",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        if (abilities.currentState.armor == Armor.NoArmor && !abilities.currentState.hasShield) {
            checkProfs@ for (prof in abilities.weaponProficiency) {
                for (prop in prof.properties) {
                    if (prop.lowercase().contains("двуручное") or prop.lowercase().contains("тяжелое")) {
                        continue@checkProfs
                    }
                }
                prof.setOfSkills.plus(Ability.Dexterity)
            }
        }
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        abilities.characterClass == Classes.Monk && abilities.level >= 1
    },
    description = "Ваше знание боевых искусств позволяет вам эффективно использовать в бою безоружные удары и монашеское оружие — короткие мечи, а также любое простое рукопашное оружие, не имеющее свойств двуручное и тяжёлое.\n" +
            "\n" +
            "Если вы безоружны или используете только монашеское оружие, и не носите ни доспехов, ни щита, вы получаете следующие преимущества:\n" +
            "\n" +
            "\tВы можете использовать Ловкость вместо Силы для бросков атак и урона ваших безоружных ударов и атак монашеским оружием.\n" +
            "\tВы можете использовать к4 вместо обычной кости урона ваших безоружных ударов или атак монашеским оружием. Эта кость увеличивается с вашим уровнем, как показано в колонке «боевые искусства».\n" +
            "\tЕсли в свой ход вы используете действие Атака для безоружного удара или атаки монашеским оружием, вы можете бонусным действием совершить ещё один безоружный удар. Например, если вы совершили действие Атака и атаковали боевым посохом, вы можете совершить бонусным действием безоружный удар, при условии, что в этом ходу вы еще не совершали бонусное действие.\n" +
            "\n" +
            "Некоторые монастыри используют особые виды монашеского оружия. Например, вы можете использовать дубинку в виде двух деревянных брусков, соединённых короткой цепью (такое оружие называется нунчаками), или серп с более коротким и прямым лезвием (называется камой). Как бы ни называлось ваше монашеское оружие, вы используете характеристики, соответствующие этому оружию.",
    priority = Priority.DoLast
)

var monk1: AbilityNode = AbilityNodeLevel(
    "Монах_1",
    {abilities: CharacterInfo ->
        abilities.characterClass = Classes.Monk
        abilities.level += 1
        abilities.proficiencyBonus += 2
        addAllSimpleWeapons(abilities)
        abilities.weaponProficiency.plus(Weapon.ShortSword)
        abilities
    },
    /*CharacterInfo(
        characterClass = "Монах",
        level = 1,
        proficiencyBonus = 2
    ),*/
    mutableMapOf(
        Pair("first", listOf(monk_unarmed_defence.name)),
        Pair("second", listOf(monk_martial_arts.name))
    ),
    {abilities: CharacterInfo -> true},
    listOf(),
    "1-й уровень, способности монаха",
    "Монах_2"
)

var monk_unarmed_movement = AbilityNode(
    name = "Движение без доспехов",
    changesInCharacterInfo = {abilities: CharacterInfo ->
        if (abilities.currentState.armor == Armor.NoArmor) {
            abilities.speed = abilities.speed + 10
        }
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = {abilities: CharacterInfo ->
        abilities.characterClass == Classes.Monk && abilities.level >= 1
    },
    description = "Ваша скорость увеличивается на 10 футов, если вы не носите доспехов и щит. Этот бонус увеличивается с ростом вашего уровня, как показано в таблице.\n" +
            "\n" +
            "На 9-м уровне вы получаете возможность в свой ход перемещаться по вертикальным поверхностям и по поверхности жидкости, не падая во время движения.",
    priority = Priority.DoLast
)

var monk2: AbilityNode = AbilityNodeLevel(
    "Монах_2",
    {abilities: CharacterInfo -> abilities},
    mutableMapOf(
        Pair("first", listOf(monk_unarmed_movement.name))
    ),
    {abilities: CharacterInfo -> true},
    listOf(listOf()),
    "2-й уровень, способности монаха",
    null
)

var mapOfMonkAbilities: MutableMap<String, AbilityNode> = mutableMapOf(
    Pair(monk_unarmed_defence.name, monk_unarmed_defence),
    Pair(monk_martial_arts.name, monk_martial_arts),
    Pair(monk1.name, monk1),
    Pair(monk_unarmed_movement.name, monk_unarmed_movement),
    Pair(monk2.name, monk2),
)







