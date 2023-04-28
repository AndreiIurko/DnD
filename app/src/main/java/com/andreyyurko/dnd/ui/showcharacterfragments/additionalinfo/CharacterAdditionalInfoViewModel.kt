package com.andreyyurko.dnd.ui.showcharacterfragments.additionalinfo

import android.util.Log
import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.data.characterData.DamageType
import com.andreyyurko.dnd.data.characterData.Languages
import com.andreyyurko.dnd.utils.CharacterViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterAdditionalInfoViewModel @Inject constructor(
    private val characterViewModel: CharacterViewModel
) : ViewModel() {

    fun getLanguages(): List<Languages> {
        return characterViewModel.shownCharacter.characterInfo.languageProficiency.toList()
    }

    fun getResistances(): List<DamageType> {
        return characterViewModel.shownCharacter.characterInfo.damageResistances.toList()
    }

    fun getAdditionalInfoItems(): List<Pair<String, String>> {
        val result: MutableList<Pair<String, String>> = mutableListOf()
        for ((name, description) in characterViewModel.shownCharacter.characterInfo.additionalAbilities.entries) {
            result.add(Pair(name, description))
        }
        Log.d("test", result.toString())
        return result
    }
}