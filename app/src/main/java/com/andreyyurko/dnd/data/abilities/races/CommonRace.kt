package com.andreyyurko.dnd.data.abilities.races

import com.andreyyurko.dnd.data.abilities.races.lineages.mapOfLineageAbilities

val mapOfRaces =
    (
            mapOfHumanAbilities +
                    mapOfDwarfAbilities +
                    mapOfHalflingAbilities +
                    mapOfElfAbilities +
                    mapOfTabaxiAbilities +
                    mapOfAasimarAbilities +
                    mapOfLineageAbilities
            ).toMutableMap()