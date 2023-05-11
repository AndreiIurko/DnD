package com.andreyyurko.dnd.data.abilities.races

import com.andreyyurko.dnd.data.abilities.other.mapOfLanguages
import com.andreyyurko.dnd.data.characterData.*
import com.andreyyurko.dnd.data.characterData.character.AbilityNode
import com.andreyyurko.dnd.data.spells.CharacterSpells
import com.andreyyurko.dnd.data.spells.SpellLists

var woodElf = AbilityNode(
    name = "Лесной эльф",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.wisdom += 1
        abilities.weaponProficiency.add(Weapon.ShortSword)
        abilities.weaponProficiency.add(Weapon.Longsword)
        abilities.weaponProficiency.add(Weapon.ShortBow)
        abilities.weaponProficiency.add(Weapon.LongBow)
        abilities.speed += 5
        abilities.additionalAbilities["Маскировка в дикой местности"] =
            "Вы можете предпринять попытку спрятаться, даже если вы слабо заслонены листвой, сильным дождём, снегопадом, туманом или другими природными явлениями.\n"
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "Поскольку вы — лесной эльф, у вас обострённые чувства и интуиция, и ваши стремительные ноги несут вас быстро и незаметно через ваши родные леса. Эта категория включает диких эльфов Серого Ястреба и кагонести из Саги о Копье, а также расы, называемые лесными эльфами Серого Ястреба и Забытых Королевств. В Фаэруне лесные эльфы (также называемые дикими или зелёными) являются затворниками, не доверяющими не-эльфам.\n" +
            "\n" +
            "Кожа лесных эльфов, как правило, имеет медный оттенок, иногда со следами зелёного. У них часто коричневые и чёрные волосы, но иногда они бывают светлого или бронзового оттенков. У них зелёные, карие или орехового цвета глаза.\n" +
            "\n" +
            "Увеличение характеристик. Значение вашей Мудрости увеличивается на 1.\n" +
            "\n" +
            "Владение эльфийским оружием. Вы владеете длинным мечом, коротким мечом, коротким и длинным луками.\n" +
            "\n" +
            "Быстрые ноги. Ваша базовая скорость ходьбы увеличивается до 35 футов.\n" +
            "\n" +
            "Маскировка в дикой местности. Вы можете предпринять попытку спрятаться, даже если вы слабо заслонены листвой, сильным дождём, снегопадом, туманом или другими природными явлениями.\n"
)

var highElf = AbilityNode(
    name = "Высший эльф",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.intelligence += 1
        abilities.weaponProficiency.add(Weapon.ShortSword)
        abilities.weaponProficiency.add(Weapon.Longsword)
        abilities.weaponProficiency.add(Weapon.ShortBow)
        abilities.weaponProficiency.add(Weapon.LongBow)
        if (!abilities.spellsInfo.contains("Заклинания Высшего эльфа")) {
            abilities.spellsInfo["Заклинания Высшего эльфа"] = CharacterSpells(
                className = Classes.Wizard.className,
                maxKnownCantripsCount = 1,
                maxKnownSpellsCount = 0
            )
        }
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("language", { mapOfLanguages.keys.toList() })
    ),
    requirements = { true },
    addRequirements = listOf(),
    description = "Поскольку вы — высший эльф, у вас острый ум и вы знакомы, по крайней мере, с основами магии. Во многих мирах D&D существует два вида высших эльфов. Один вид (который включает серых эльфов и эльфов долин Серого Ястреба, сильванести Саги о Копье и солнечных эльфов Забытых Королевств) высокомерен и замкнут, считая себя выше не-эльфов и даже других эльфов. Другой вид (включающий высших эльфов Серого Ястреба, квалинести из Саги о Копье и лунных эльфов из Забытых Королевств) более распространён и дружелюбен, и часто встречается среди людей и других рас.\n" +
            "\n" +
            "У солнечных эльфов Фаэруна (также называемых золотыми эльфами или эльфами восхода) бронзовая кожа и волосы медного, чёрного или золотистого оттенка. У них золотые, серебристые или чёрные глаза. Лунные эльфы (также называемые серебряными или серыми эльфами) гораздо бледнее, с алебастровой кожей, имеющей иногда оттенок синего. У них часто серебристо-белые, чёрные или синие волосы, но и различные оттенки светлых, коричневых и рыжих тонов также не являются редкими. У них синие или зелёные глаза с золотыми вкраплениями.\n" +
            "\n" +
            "Увеличение характеристик. Значение вашего Интеллекта увеличивается на 1.\n" +
            "\n" +
            "Владение эльфийским оружием. Вы владеете длинным мечом, коротким мечом, коротким и длинным луками.\n" +
            "\n" +
            "Заговор. Вы знаете один заговор из списка заклинаний волшебника. Базовой характеристикой заклинаний для его использования является Интеллект.\n" +
            "\n" +
            "Дополнительный язык. Вы можете говорить, читать и писать на еще одном языке, на ваш выбор.\n"
)

var drow = AbilityNode(
    name = "Дроу",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.charisma += 1
        abilities.weaponProficiency.add(Weapon.ShortSword)
        abilities.weaponProficiency.add(Weapon.Rapier)
        abilities.weaponProficiency.add(Weapon.CrossbowHand)
        var regex = Regex(" [^ ]* футов")
        if (abilities.additionalAbilities.contains("Тёмное зрение")) {
            abilities.additionalAbilities["Тёмное зрение"]?.let {
                regex.find(it)?.let { parsed ->
                    if (120 > parsed.value.split(" ")[1].toInt()) {
                        abilities.additionalAbilities["Тёмное зрение"] =
                            "Привыкнув к сумраку леса и ночному небу, вы обладаете превосходным зрением в темноте и при тусклом освещении. На расстоянии в 120 футов вы при тусклом освещении можете видеть так, как будто это яркое освещение, и в темноте так, как будто это тусклое освещение. В темноте вы не можете различать цвета, только оттенки серого.\n"
                    }
                }
            }
        } else {
            abilities.additionalAbilities["Тёмное зрение"] =
                "Привыкнув к сумраку леса и ночному небу, вы обладаете превосходным зрением в темноте и при тусклом освещении. На расстоянии в 120 футов вы при тусклом освещении можете видеть так, как будто это яркое освещение, и в темноте так, как будто это тусклое освещение. В темноте вы не можете различать цвета, только оттенки серого.\n"
        }
        abilities.spellsInfo["Заклинания дроу"] = CharacterSpells(
            maxKnownSpellsCount = 0,
            maxKnownCantripsCount = 0,
            spellLists = SpellLists(
                knownCantrips = mutableSetOf<String>("Пляшущие огоньки")
            )
        )
        if (abilities.level >= 3) {
            if (!abilities.currentState.charges.contains("Магия дроу")) {
                abilities.currentState.charges["Магия дроу"] = ChargesCounter(
                    current = 1,
                    maximum = 1
                )
            }
            if (abilities.level < 5) {
                abilities.actionsList.add(
                    Action(
                        name = "Магия дроу",
                        description = "Начиная с 3-го уровня, вы можете накладывать заклинание огонь фей [faerie fire] с помощью магии дроу. После накладывания этого заклинания с помощью магии дроу вы должны закончить продолжительный отдых, прежде чем сможете вновь наложить его таким образом. \n",
                        type = ActionType.Action,
                        relatedCharges = "Магия дроу"
                    )
                )
            } else {
                abilities.actionsList.add(
                    Action(
                        name = "Магия дроу",
                        description = "Начиная с 3-го уровня, вы можете накладывать заклинание огонь фей [faerie fire] с помощью магии дроу. Начиная с 5-го уровня вы также можете накладывать заклинание тьма [darkness] с помощью этой особенности. После накладывания одного из этих заклинаний с помощью особенности вы должны закончить продолжительный отдых, прежде чем сможете вновь наложить его таким образом.\n",
                        type = ActionType.Action,
                        relatedCharges = "Магия дроу"
                    )
                )
            }
        }
        //weapon
        abilities
    },
    getAlternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "\n" +
            "\n" +
            "Как дроу, вы прониклись магией Подземья, подземного царства чудес и ужасов, которое редко можно увидеть на поверхности. Вы чувствуете себя в тени как дома и благодаря своей врожденной магии учитесь призывать и свет, и тьму. У ваших сородичей обычно ярко-белые волосы и разноцветная сероватая кожа.\n" +
            "\n" +
            "Культ богини Лолс, Королевы Пауков, развратил некоторые из старейших городов дроу, особенно в мирах Орта и Торила. Эберрон, Кринн и другие царства на данный момент избежали влияния культа. Везде, где таится культ, герои-дроу стоят на передовой в войне против него, стремясь разорвать паутину Лолс.\n" +
            "\n" +
            "Подробнее с лором дроу можно ознакомится в статье «Бестиарий: Дроу»\n" +
            "\n" +
            "Увеличение характеристик. Значение вашей Харизмы увеличивается на 1.\n" +
            "\n" +
            "Превосходное тёмное зрение. Ваше тёмное зрение имеет радиус 120 футов.\n" +
            "\n" +
            "Чувствительность к солнцу. Вы совершаете с помехой броски атаки и проверки Мудрости (Восприятие), основанные на зрении, если вы, цель вашей атаки или изучаемый предмет расположены на прямом солнечном свете.\n" +
            "\n" +
            "Магия дроу. Начиная с 3-го уровня, вы можете накладывать заклинание огонь фей [faerie fire] с помощью этой особенности. Начиная с 5-го уровня вы также можете накладывать заклинание тьма [darkness] с помощью этой особенности. После накладывания одного из этих заклинаний с помощью особенности вы должны закончить продолжительный отдых, прежде чем сможете вновь наложить его таким образом. \n" +
            "\n" +
            "Кроме того, вы знаете заговор пляшущие огоньки [dancing lights]. Базовой характеристикой для этих заклинаний является Харизма.\n" +
            "\n" +
            "Владение оружием дроу. Вы владеете рапирой, коротким мечом и ручным арбалетом.\n"
)

val elfAbilities = AbilityNode(
    name = "Способности эльфа",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.dexterity += 2
        abilities.languageProficiency.add(Languages.Common)
        abilities.languageProficiency.add(Languages.Elvish)
        abilities.speed += 30
        if (abilities.additionalAbilities.contains("Тёмное зрение")) {
            var regex = Regex(" [^ ]* футов")
            abilities.additionalAbilities["Тёмное зрение"]?.let {
                regex.find(it)?.let { parsed ->
                    if (60 > parsed.value.split(" ")[1].toInt()) {
                        abilities.additionalAbilities["Тёмное зрение"] =
                            "Привыкнув к сумраку леса и ночному небу, вы обладаете превосходным зрением в темноте и при тусклом освещении. На расстоянии в 60 футов вы при тусклом освещении можете видеть так, как будто это яркое освещение, и в темноте так, как будто это тусклое освещение. В темноте вы не можете различать цвета, только оттенки серого.\n"
                    }
                }
            }
        } else {
            abilities.additionalAbilities["Тёмное зрение"] =
                "Привыкнув к сумраку леса и ночному небу, вы обладаете превосходным зрением в темноте и при тусклом освещении. На расстоянии в 60 футов вы при тусклом освещении можете видеть так, как будто это яркое освещение, и в темноте так, как будто это тусклое освещение. В темноте вы не можете различать цвета, только оттенки серого.\n"
        }
        abilities.skillProficiency.add(Skill.Perception)
        abilities.additionalAbilities["Наследие фей"] =
            "Вы совершаете с преимуществом спасброски от состояния очарованный, и вас невозможно магически усыпить.\n"
        abilities.additionalAbilities["Транс"] =
            "Эльфы не спят. Вместо этого они погружаются в глубокую медитацию, находясь в полубессознательном состоянии до 4 часов в сутки (обычно такую медитацию называют трансом). Во время этой медитации вы можете грезить о разных вещах. Некоторые из этих грёз являются ментальными упражнениями, выработанными за годы тренировок. После такого отдыха вы получаете все преимущества, которые получает человек после 8 часов сна.\n"
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("subrace", { listOf(woodElf.name, highElf.name, drow.name) })
    ),
    requirements = { true },
    description = "Увеличение характеристик. Значение вашей Ловкости увеличивается на 2.\n" +
            "\n" +
            "Возраст. Несмотря на то, что физически эльфы взрослеют в том же возрасте, что и люди, их понимание о взрослении выходит за рамки физического развития, и располагается в сфере житейского опыта. Обычно эльф получает статус взрослого и взрослое имя в возрасте 100 лет, и может прожить до 750 лет.\n" +
            "\n" +
            "Мировоззрение. Любое.\n" +
            "\n" +
            "Размер. Рост эльфов колеблется между 5 и 6 футами (152 и 183 сантиметрами), у них стройное телосложение. Ваш размер — Средний.\n" +
            "\n" +
            "Скорость. Ваша базовая скорость ходьбы составляет 30 футов.\n" +
            "\n" +
            "Тёмное зрение. Привыкнув к сумраку леса и ночному небу, вы обладаете превосходным зрением в темноте и при тусклом освещении. На расстоянии в 60 футов вы при тусклом освещении можете видеть так, как будто это яркое освещение, и в темноте так, как будто это тусклое освещение. В темноте вы не можете различать цвета, только оттенки серого.\n" +
            "\n" +
            "Обострённые чувства. Вы владеете навыком Восприятие.\n" +
            "\n" +
            "Наследие фей. Вы совершаете с преимуществом спасброски от состояния очарованный, и вас невозможно магически усыпить.\n" +
            "\n" +
            "Транс. Эльфы не спят. Вместо этого они погружаются в глубокую медитацию, находясь в полубессознательном состоянии до 4 часов в сутки (обычно такую медитацию называют трансом). Во время этой медитации вы можете грезить о разных вещах. Некоторые из этих грёз являются ментальными упражнениями, выработанными за годы тренировок. После такого отдыха вы получаете все преимущества, которые получает человек после 8 часов сна.\n" +
            "\n" +
            "Языки. Вы можете говорить, читать и писать на Общем и Эльфийском языках. Эльфийский язык текучий, с утончёнными интонациями и сложной грамматикой. Эльфийская литература богата и разнообразна, а стихи и песни известны среди представителей других рас. Многие барды учат эльфийский язык, чтобы добавить песни в свой репертуар.\n" +
            "\n" +
            "Разновидности. Древний раскол среди эльфийских народов привёл к возникновению трёх видов: высших эльфов, лесных эльфов и тёмных эльфов, которых обычно называют дроу. Выберите один из этих видов. В некоторых мирах они делятся еще сильнее, (например на солнечных и лунных эльфов в мире Забытых Королевств) так что если хотите, можете выбрать более уточнённый вариант. Но помимо этого в мультивселенной D&D существует множество видов эльфов. С разрешения вашего Мастера вы можете выбрать один из них.\n",
    priority = Priority.DoFirst
)

val elf = AbilityNode(
    name = "Эльф",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.race = "Эльф"
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("abilities", { listOf(elfAbilities.name) })
    ),
    requirements = { true },
    description = "Эльф",
)

val mapOfElfAbilities = mutableMapOf(
    Pair(woodElf.name, woodElf),
    Pair(highElf.name, highElf),
    Pair(drow.name, drow),
    Pair(elfAbilities.name, elfAbilities),
    Pair(elf.name, elf)
)