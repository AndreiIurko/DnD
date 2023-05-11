package com.andreyyurko.dnd.ui.showcharacterfragments.actions

import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.data.characterData.Action
import com.andreyyurko.dnd.data.characterData.ActionType
import com.andreyyurko.dnd.utils.CharacterViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterActionsViewModel @Inject constructor(
    val characterViewModel: CharacterViewModel
) : ViewModel() {

    fun getActionList(actionType: ActionType): MutableList<Action> {
        val result: MutableList<Action> = mutableListOf()
        for (action in characterViewModel.shownCharacter.characterInfo.actionsList) {
            if (action.type == actionType) result.add(action)
        }

        return result
    }
}