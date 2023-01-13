package com.andreyyurko.dnd.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.data.characters.character.Character
import com.andreyyurko.dnd.data.characters.character.mergeAllAbilities
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterViewModel @Inject constructor(
    private val charactersHolder: CharactersHolder
) : ViewModel() {
    var charactersBriefInfo = charactersHolder.getBriefInfo()

    lateinit var shownCharacter: Character

    val dataState: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun updateBriefInfo() {
        charactersBriefInfo = charactersHolder.getBriefInfo()
    }
    fun setShownCharacter(id: Int) {
        shownCharacter = charactersHolder.getCharacterById(id)
    }
    fun updateCharacterInfo() {
        dataState.value = DataState.Loading.stateName
        shownCharacter = mergeAllAbilities(shownCharacter)
        charactersHolder.updateCharacter(shownCharacter)
        dataState.value = DataState.Complete.stateName
    }

    fun deleteCharacter(id: Int) {
        charactersHolder.removeCharacterById(id)
    }

    enum class DataState(val stateName: String) {
        Loading("loading"),
        Complete("complete")
    }
}