package com.andreyyurko.dnd.data.abilities

import com.andreyyurko.dnd.data.abilities.classes.barbarian1
import com.andreyyurko.dnd.data.abilities.classes.fighter.fighter1
import com.andreyyurko.dnd.data.abilities.classes.mapOfClasses
import com.andreyyurko.dnd.data.abilities.classes.monk.monk1
import com.andreyyurko.dnd.data.abilities.classes.sorcerer.sorcerer1
import com.andreyyurko.dnd.data.abilities.classes.cleric.cleric1
import com.andreyyurko.dnd.data.abilities.classes.wizard.wizard1
import com.andreyyurko.dnd.data.abilities.classes.bard.bard1
import com.andreyyurko.dnd.data.abilities.other.mapOfAbilityScoreImprovement
import com.andreyyurko.dnd.data.abilities.other.mapOfFightingStyles
import com.andreyyurko.dnd.data.abilities.other.mapOfLanguages
import com.andreyyurko.dnd.data.abilities.other.mapOfSkills
import com.andreyyurko.dnd.data.abilities.races.human
import com.andreyyurko.dnd.data.abilities.races.elf
import com.andreyyurko.dnd.data.abilities.races.mapOfRaces
import com.andreyyurko.dnd.data.characterData.*
import com.andreyyurko.dnd.data.characterData.character.AbilityNode

var baseActionsAN = AbilityNode(
    "base_actions_an",
    { abilities: CharacterInfo ->
        addAttackActions(abilities)
        abilities.actionsList.add(
            Action(
                name = "Засада",
                description = "Вы не можете прятаться от существа, которое видит вас, и если вы издадите шум (например, прокричите предупреждение или уроните вазу), вы выдаёте своё местоположение.\n" +
                        "\n" +
                        "Когда вы пытаетесь спрятаться, совершите проверку Ловкости (Скрытность). Пока вас не найдут или вы не прекратите прятаться, результат этой проверки будет противостоять проверкам Мудрости (Восприятие) существ, активно ищущих вас.\n" +
                        "\n" +
                        "Если вы прячетесь, есть шанс, что вас заметят даже без активных поисков. Для определения того, заметило ли вас существо, Мастер сравнивает результат вашей проверки Ловкости (Скрытность) с пассивным значением Мудрости (Восприятие), которое равно 10 + модификатор Мудрости существа, плюс все уместные бонусы и штрафы.\n",
                type = ActionType.Action
            )
        )
        abilities.actionsList.add(
            Action(
                name = "Использование объекта",
                description = "Как правило, вы взаимодействуете с объектами в ходе других действий, " +
                        "например когда вынимаете меч из ножен в ходе атаки. " +
                        "Но если предмет требует ваше действие для применения, " +
                        "совершите действие Использование объекта. " +
                        "Это действие также полезно, когда вам нужно взаимодействовать " +
                        "с более чем одним объектом в свой ход.\n",
                type = ActionType.Action
            )
        )
        abilities.actionsList.add(
            Action(
                name = "Отступление",
                description = "Если вы выполнили это действие, " +
                        "ваше перемещение не провоцирует внеочередных атак до конца этого хода\n",
                type = ActionType.Action
            )
        )
        abilities.actionsList.add(
            Action(
                name = "Подготовка действия",
                description = "\n" +
                        "\n" +
                        "Во-первых, определите, какое воспринимаемое событие вызовет вашу реакцию.\n" +
                        "\n" +
                        "Затем выберите действие, либо перемещение, которое будет совершено.\n" +
                        "\n" +
                        "Примеры: «Если культист наступит на люк, я дёрну рычаг и открою его», «Если передо мной встанет гоблин, я отойду прочь».\n" +
                        "\n" +
                        "Когда срабатывает условие, вы можете либо совершить свою реакцию до окончания действия, вызвавшего срабатывание, либо игнорировать срабатывание условия. Подготовленное действие можно совершить только до начала вашего следующего хода. Помните, что вы можете совершить только одну реакцию в раунд.\n" +
                        "\n" +
                        "Если вы подготовили заклинание, вы накладываете его как обычно, но удерживаете энергию, пока не сработает условие. Для того чтобы заклинание можно было подготовить, у него должно быть время накладывания «1 действие», а удерживание магии требует концентрации. Если концентрация прервана, заклинание тратится без всякого эффекта.\n",
                type = ActionType.Action
            )
        )
        abilities.actionsList.add(
            Action(
                name = "Захват",
                description = "Если вы хотите схватить существо или побороться с ним, вы можете использовать действие «Атака» для совершения особой рукопашной атаки, захвата. Если вы можете совершать многочисленные атаки действием «Атака», эта атака заменяет одну из них.\n" +
                        "\n" +
                        "Цель вашего захвата должна быть не более чем на одну категорию больше вас, и она должна находиться в пределах вашей досягаемости.\n" +
                        "\n" +
                        "Используя как минимум одну свободную руку, вы пытаетесь схватить цель, совершая проверку захвата: проверку Силы (Атлетика), противопоставленную проверке Силы (Атлетика) или Ловкости (Акробатика) цели (цель сама выбирает, какую характеристику использовать). Проверка автоматически успешна, если цель недееспособна.\n" +
                        "\n" +
                        "Если вы преуспеете, цель становится схваченной. В описании состояния сказано, что его оканчивает, и вы в любой момент можете отпустить цель (действие не требуется).\n" +
                        "\n" +
                        "Высвобождение из захвата. Захваченное существо может пытаться высвободиться. Для этого оно действием совершает проверку Силы (Атлетика) или Ловкость (Акробатика), противопоставленную вашей проверке Силы (Атлетика).\n",
                type = ActionType.Action
            )
        )
        abilities.actionsList.add(
            Action(
                name = "Толчок",
                description = "При помощи действия «Атака» совершить особую рукопашную атаку, чтобы или сбить цель с ног, или оттолкнуть от себя. Если вы можете совершать многочисленные атаки действием «Атака», эта атака может заменить одну из них.\n" +
                        "\n" +
                        "Цель вашего толкания должна быть не более чем на одну категорию больше вас, и она должна находиться в пределах вашей досягаемости.\n" +
                        "\n" +
                        "Вы совершаете проверку Силы (Атлетика), противопоставленную проверке Силы (Атлетика) или Ловкости (Акробатика) цели (цель сама выбирает, какую характеристику использовать). Проверка автоматически успешна, если цель недееспособна.\n" +
                        "\n" +
                        "Если вы преуспеете, вы либо сбиваете цель с ног, либо толкаете её на 5 футов от себя.\n",
                type = ActionType.Action
            )
        )
        abilities.actionsList.add(
            Action(
                name = "Рывок",
                description = "Если вы совершаете действие «Рывок», вы получаете дополнительное перемещение в текущем ходу, равное вашей скорости после применения всех модификаторов.\n",
                type = ActionType.Action
            )
        )
        abilities.actionsList.add(
            Action(
                name = "Помощь",
                description = "Существо, которому вы помогаете, совершит свою следующую проверку характеристики для выполнения задачи с преимуществом, если она будет совершена до начала вашего следующего хода.\n" +
                        "\n" +
                        "В качестве альтернативы, вы можете помочь дружественному существу атаковать другое существо, находящееся в пределах 5 футов от вас. Вы совершаете финт, отвлекаете цель или каким-то другим образом делаете атаку союзника более эффективной. Если ваш союзник атакует цель до начала вашего следующего хода, первый бросок атаки совершается с преимуществом.\n" +
                        "\n" +
                        "Преимущество длится до начала вашего следующего хода.\n",
                type = ActionType.Action
            )
        )
        abilities.actionsList.add(
            Action(
                name = "Стабилизация",
                description = "Вы можете действием оказать первую помощь находящемуся без сознания существу и попытаться стабилизировать его, для чего требуется совершить проверку Мудрости (Медицина) Сл 10.\n" +
                        "\n" +
                        "Стабилизированное существо не делает проверки спасброска от смерти, хотя всё ещё имеет 0 хитов и остаётся без сознания. Существо перестаёт быть стабилизированным, если снова получает урон. Стабилизированное существо восстанавливает 1 хит раз в 1к4 часов.\n",
                type = ActionType.Action
            )
        )
        abilities.actionsList.add(
            Action(
                name = "Использование подготовленного действия",
                description = "При достижении условий, выбранных при подготовке вы можете реакцией совершить подготовленное действие\n",
                type = ActionType.Reaction
            )
        )
        abilities.actionsList.add(
            Action(
                name = "Разговор",
                description = "Вы можете общаться любыми доступными вам средствами, короткими фразами и жестами без затраты действия.\n",
                type = ActionType.Additional
            )
        )
        abilities.actionsList.add(
            Action(
                name = "Прерывание концентрации",
                description = "Вы можете перестать концентрироваться на заклинании в любое время без затраты действия.\n",
                type = ActionType.Additional
            )
        )
        abilities
    },
    mutableMapOf(),
    { true },
    listOf(listOf()),
    description = "Base actions",
    priority = Priority.DoLast
)

var baseAN: AbilityNode = AbilityNode(
    "base_an",
    { abilities: CharacterInfo ->
        abilities.weaponProficiency.plus(Weapon.Unarmed)
        abilities.ac = abilities.currentState.armor.ac + Integer.min(
            abilities.currentState.armor.dexRestriction,
            (abilities.dexterity - 10) / 2
        )
        abilities.initiativeBonus = abilities.initiativeBonus + (abilities.dexterity - 10) / 2
        abilities
    },
    mutableMapOf(
        Pair("actions", listOf(baseActionsAN.name)),
        Pair("class", listOf(monk1.name, barbarian1.name, fighter1.name, sorcerer1.name, cleric1.name, wizard1.name, bard1.name)),
        Pair("race", listOf(human.name, elf.name))
    ),
    { true },
    listOf(listOf()),
    description = "Base Ability Node, root of all AN",
    priority = Priority.DoAsSoonAsPossible
)

// helps to split code
var mapOfAn: MutableMap<String, AbilityNode> = (
        mutableMapOf(
            Pair(baseActionsAN.name, baseActionsAN),
            Pair(baseAN.name, baseAN)
        )
                + mapOfRaces
                + mapOfClasses
                + mapOfFightingStyles
                + mapOfAbilityScoreImprovement
                + mapOfSkills
                + mapOfLanguages
        ).toMutableMap()

fun addAttackActions(abilities: CharacterInfo) {
    // main action
    // TODO: consider to add damage as well
    abilities.actionsList.add(
        Action(
            name = "Атака",
            description = "Совершить одну атаку рукопашным оружием",
            type = ActionType.Action
        )
    )

    // attack as bonus action
    if (abilities.currentState.weapons.size == 2) {
        if (abilities.currentState.weapons[0].properties.contains("Лёгкое") and abilities.currentState.weapons[1].properties.contains(
                "Лёгкое"
            )
        ) {
            abilities.actionsList.add(
                Action(
                    name = "Атака второй рукой",
                    description = "Если вы совершаете действие «Атака» и атакуете рукопашным оружием со свойством «лёгкое», удерживаемым в одной руке, вы можете бонусным действием атаковать другим рукопашным оружием со свойством «лёгкое», удерживаемым в другой руке.\n" +
                            "\n" +
                            "Вы не добавляете модификатор характеристики к урону от бонусной атаки, если только он не отрицательный.\n" +
                            "\n" +
                            "Если у любого из оружий есть свойство «метательное», вы можете не совершать им рукопашную атаку, а метнуть его.",
                    type = ActionType.Bonus
                )
            )
        }
    }
}
