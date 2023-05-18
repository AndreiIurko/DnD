package com.andreyyurko.dnd.data.abilities.races.lineages

import com.andreyyurko.dnd.data.characterData.Ability
import com.andreyyurko.dnd.data.characterData.Action
import com.andreyyurko.dnd.data.characterData.ActionType
import com.andreyyurko.dnd.data.characterData.CharacterInfo
import com.andreyyurko.dnd.data.characterData.ChargesCounter
import com.andreyyurko.dnd.data.characterData.DamageType
import com.andreyyurko.dnd.data.characterData.Weapon
import com.andreyyurko.dnd.data.characterData.character.AbilityNode

val dhampir = AbilityNode(
    name = "Дампир",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.race = "Дампир"
        abilities.speed += 35
        if (abilities.additionalAbilities.contains("Тёмное зрение")) {
            var regex = Regex(" [^ ]* футов")
            abilities.additionalAbilities["Тёмное зрение"]?.let {
                regex.find(it)?.let { parsed ->
                    if (60 > parsed.value.split(" ")[1].toInt()) {
                        abilities.additionalAbilities["Тёмное зрение"] =
                            "На расстоянии 60 футов вы при тусклом освещении можете видеть так, как будто это яркое освещение, и в темноте так, как будто это тусклое освещение. В темноте вы не можете различать цвета, только оттенки серого.\n"
                    }
                }
            }
        } else {
            abilities.additionalAbilities["Тёмное зрение"] =
                "На расстоянии 60 футов вы при тусклом освещении можете видеть так, как будто это яркое освещение, и в темноте так, как будто это тусклое освещение. В темноте вы не можете различать цвета, только оттенки серого.\n"
        }
        abilities.additionalAbilities["Природа нежити"] = "Вам не нужно дышать\n"
        if (abilities.level < 3) abilities.additionalAbilities["Скорость лазания дампира"] = "Вы получаете скорость лазания, равную вашей скорости ходьбы\n"
        else abilities.additionalAbilities["Скорость лазания дампира"] = "Вы получаете скорость лазания, равную вашей скорости ходьбы. При этом вы можете перемещаться вверх, вниз и вдоль вертикальных поверхностей, а также по потолкам, оставляя руки свободными.\n"
        if (!abilities.currentState.charges.contains("Укус вампира")) {
            abilities.currentState.charges["Укус вампира"] = ChargesCounter(
                current = abilities.proficiencyBonus,
                maximum = abilities.proficiencyBonus
            )
        }
        abilities.actionsList.add(
            Action(
                name = "Укус вампира",
                description = "...\n",
                type = ActionType.Action,
                relatedCharges = "Ци"
            )
        )
        abilities
    },
    getAlternatives = mutableMapOf(
        Pair("first", { listOf(lineageAbilityScoreImprovement.name) }),
        Pair("second", { listOf(ancestralLegacy.name) }),
        Pair("third", { listOf(lineageLanguage.name) }),
    ),
    requirements = { true },
    description = "Вы обладаете следующими расовыми особенностями:\n" +
            "\n" +
            "Вид существа. Вы Гуманоид.\n" +
            "\n" +
            "Размер. Ваш размер — Маленький или Средний. Вы сами решаете, какого размера будете, когда получаете это происхождение.\n" +
            "\n" +
            "Скорость. Ваша базовая скорость ходьбы составляет 35 футов.\n" +
            "\n" +
            "Тёмное зрение. На расстоянии 60 футов вы при тусклом освещении можете видеть так, как будто это яркое освещение, и в темноте так, как будто это тусклое освещение. В темноте вы не можете различать цвета, только оттенки серого.\n" +
            "\n" +
            "Природа нежити. Вам не нужно дышать.\n" +
            "\n" +
            "Паучье лазание. Вы получаете скорость лазания, равную вашей скорости ходьбы. Кроме того, на 3-м уровне вы получаете возможность перемещаться вверх, вниз и вдоль вертикальных поверхностей, а также по потолкам, оставляя руки свободными.\n" +
            "\n" +
            "Укус вампира. Ваш укус клыками — это природное оружие, которое считается простым рукопашным оружием, которым вы владеете. Вы добавляете ваш модификатор Телосложения вместо модификатора Силы для бросков атаки и урона для атак, совершаемых этим укусом. Атака наносит 1к4 колющего урона при попадании. Если у вас не достаёт половины и более ваших хитов, вы совершаете с преимуществом броски атаки вашим укусом.\n" +
            "\n" +
            "Когда вы атакуете этим укусом и попадаете по существу, которое не является конструктом или нежитью, вы можете усилить себя одним из следующих способов по вашему выбору:\n" +
            "\n" +
            "    Вы восстанавливаете хиты, равные колющему урону, который вы нанесли укусом.\n" +
            "    Вы получаете бонус к следующей проверке характеристики или броску атаки, который вы совершаете. Бонус равен колющему урону, который вы нанесли укусом.\n" +
            "\n" +
            "Вы можете усилить себя данным укусом количество раз, равное вашему бонусу мастерства, и вы восстановите все израсходованные применения после окончания продолжительного отдыха.\n",
)

val mapOfDhampirAbilities = mutableMapOf(
    Pair(dhampir.name, dhampir)
)