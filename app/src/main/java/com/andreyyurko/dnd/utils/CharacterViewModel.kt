package com.andreyyurko.dnd.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.data.characterData.character.Character
import com.andreyyurko.dnd.data.characterData.character.mergeAllAbilities
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

    val backgroundIsShown: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    fun updateBriefInfo() {
        charactersBriefInfo = charactersHolder.getBriefInfo()
    }

    fun setShownCharacter(id: Int) {
        shownCharacter = charactersHolder.getCharacterById(id)
    }

    fun updateCharacterInfo() {
        dataState.value = DataState.Loading.stateName
        mergeAllAbilities(shownCharacter)
        charactersHolder.updateCharacter(shownCharacter)
        dataState.value = DataState.Complete.stateName
    }

    fun deleteCharacter(id: Int) {
        charactersHolder.removeCharacterById(id)
    }

    fun showPopUpBackground() {
        backgroundIsShown.value = true
    }

    fun closePopUpBackground() {
        backgroundIsShown.value = false
    }

    enum class DataState(val stateName: String) {
        Loading("loading"),
        Complete("complete")
    }
}