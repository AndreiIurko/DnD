package com.andreyyurko.dnd.ui.addcharacterfragments.racefragment

import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.ui.addcharacterfragments.AbilityAdapter
import com.andreyyurko.dnd.utils.CreateCharacterViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RaceViewModel @Inject constructor(
    private val createCharacterViewModel: CreateCharacterViewModel
) : ViewModel() {
    val character = createCharacterViewModel.character
    val baseCAN = character.baseCAN

    lateinit var adapter: AbilityAdapter

    var chosenRace: String? = null

    fun makeChoice(raceChoice: String) {
        baseCAN.makeChoice("race", raceChoice)
        showAbilities()
    }

    fun showAbilities() {
        adapter.apply {
            rootCan = baseCAN.chosen_alternatives["race"]
            notifyDataSetChanged()
        }
    }

    fun updateCharacter() {
        createCharacterViewModel.updateCharacter()
    }

    fun deleteCharacter() {
        createCharacterViewModel.deleteCharacter()
    }
}