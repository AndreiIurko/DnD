package com.andreyyurko.dnd.data.abilities.races

import com.andreyyurko.dnd.data.characterData.*
import com.andreyyurko.dnd.data.characterData.character.AbilityNode

var LightfootHalfling = AbilityNode(
    name = "Легконогий полурослик",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.charisma += 1
        abilities.additionalAbilities["Естественная скрытность"] = "Вы можете предпринять попытку скрыться даже если заслонены только существом, превосходящими вас в размере как минимум на одну категорию.\n"
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "Коренастые полурослики выносливее других и обладают некоторой устойчивостью к ядам. Поговаривают, что в их жилах течёт толика дварфской крови. В мире Забытых Королевств таких полуросликов зовут сильными сердцем, и чаще всего они встречаются на юге.\n" +
            "\n" +
            "Увеличение характеристик. Значение вашего Телосложения увеличивается на 1.\n" +
            "\n" +
            "Устойчивость коренастых. Вы совершаете с преимуществом спасброски от яда, и вы получаете сопротивление урону ядом.\n"
)

var StoutHalfling = AbilityNode(
    name = "Коренастый полурослик",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.constitution += 1
        abilities.additionalAbilities["Дварфийская устойчивость"] = "Вы совершаете с преимуществом спасброски от яда, и вы получаете сопротивление урону ядом.\n"
        abilities.damageResistances.add(DamageType.Poison)
        abilities
    },
    alternatives = mutableMapOf(),
    requirements = { true },
    addRequirements = listOf(),
    description = "Легконогие полурослики умеют отлично скрываться, в том числе используя других существ как укрытие. Они приветливы и хорошо ладят с другими. В мире Забытых Королевств легконогие являются самой распространённой ветвью полуросликов. Легконогие более других склонны к перемене мест, и часто селятся по соседству с другими народами, или ведут кочевую жизнь. В мире Серого Ястреба таких полуросликов называют мохноногими или великанчиками.\n" +
            "\n" +
            "Увеличение характеристик. Значение вашей Харизмы увеличивается на 1.\n" +
            "\n" +
            "Естественная скрытность. Вы можете предпринять попытку скрыться даже если заслонены только существом, превосходящими вас в размере как минимум на одну категорию.\n"
)

val halflingAbilities = AbilityNode(
    name = "Способности полуросликов",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.dexterity += 2
        abilities.languageProficiency.add(Languages.Common)
        abilities.languageProficiency.add(Languages.Halfling)
        abilities.speed += 25
        abilities.additionalAbilities["Везучий"] = "Если при броске атаки, проверке характеристики или спасброске у вас выпало «1», вы можете перебросить кость, и должны использовать новый результат, даже если это «1».\n"
        abilities.additionalAbilities["Храбрый"] = "Вы совершаете с преимуществом спасброски от состояния испуганный.\n"
        abilities.additionalAbilities["Проворство полуросликов"] = "Вы можете проходить сквозь пространство, занятое существами, чей размер больше вашего.\n"
        // add tools
        abilities
    },
    alternatives = mutableMapOf(
        Pair("subraces", listOf(LightfootHalfling.name, StoutHalfling.name))
    ),
    requirements = { true },
    description = "Как и другие полурослики, ваш персонаж обладает определёнными качествами.\n" +
            "\n" +
            "Увеличение характеристик. Значение вашей Ловкости повышается на 2.\n" +
            "\n" +
            "Возраст. Полурослики достигают зрелости к 20 годам, и обычно живут до середины своего второго столетия.\n" +
            "\n" +
            "Мировоззрение. Любое.\n" +
            "\n" +
            "Размер. Полурослики в среднем примерно 3 фута (90 сантиметров) ростом и весят около 40 фунтов (18 килограмм). Ваш размер — Маленький.\n" +
            "\n" +
            "Скорость. Ваша базовая скорость ходьбы составляет 25 футов.\n" +
            "\n" +
            "Везучий. Если при броске атаки, проверке характеристики или спасброске у вас выпало «1», вы можете перебросить кость, и должны использовать новый результат, даже если это «1».\n" +
            "\n" +
            "Храбрый. Вы совершаете с преимуществом спасброски от состояния испуганный.\n" +
            "\n" +
            "Проворство полуросликов. Вы можете проходить сквозь пространство, занятое существами, чей размер больше вашего.\n" +
            "\n" +
            "Языки. Вы можете говорить, читать и писать на Общем и языке Полуросликов. Их язык не является секретным, но они не торопятся делиться им с остальными. Пишут они мало, и почти не создали собственной литературы, но устные предания у них очень распространены. Почти все полурослики знают Общий, чтобы общаться с людьми в землях, куда они направляются, или по которым странствуют.\n" +
            "\n" +
            "Разновидности. Существует два основных вида полуросликов. Они скорее являются двумя крупными родами, нежели разными видами. Но помимо этого в мультивселенной D&D существует множество видов полуросликов. С разрешения вашего Мастера вы можете выбрать один из них.\n",
    priority = Priority.DoFirst
)

val halfling = AbilityNode(
    name = "Полурослик",
    changesInCharacterInfo = { abilities: CharacterInfo ->
        abilities.race = "Полурослик"
        abilities
    },
    alternatives = mutableMapOf(
        Pair("abilities", listOf(halflingAbilities.name))
    ),
    requirements = { true },
    description = "Полурослик",
)

val mapOfHalflingAbilities = mutableMapOf(
    Pair(LightfootHalfling.name, LightfootHalfling),
    Pair(StoutHalfling.name, StoutHalfling),
    Pair(halflingAbilities.name, halflingAbilities),
    Pair(halfling.name, halfling)
)