package com.andreyyurko.dnd.ui.spellslist

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreyyurko.dnd.data.SpellSpecificLanguage
import com.andreyyurko.dnd.utils.SpellsParser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpellsListViewModel @Inject constructor(
    private val spellsParser: SpellsParser
) : ViewModel() {

    companion object {
        const val LOG_TAG = "SpellsListViewModel"
    }

    sealed class LoadSpellsActionState {
        object Loading : LoadSpellsActionState()
        data class Data(val spells: List<SpellSpecificLanguage>) : LoadSpellsActionState()
        data class Error(val error: String) : LoadSpellsActionState()
    }

    private val _loadSpellsActionState = MutableStateFlow<LoadSpellsActionState>(LoadSpellsActionState.Loading)
    val loadSpellsActionState: Flow<LoadSpellsActionState> get() = _loadSpellsActionState.asStateFlow()

    fun parseSpells(context: Context?) {
        viewModelScope.launch {
            _loadSpellsActionState.emit(LoadSpellsActionState.Loading)
            val spells = mutableListOf<SpellSpecificLanguage>()
            try {
                context?.let {
                    val spellsAllLanguage = spellsParser.parseSpells(it)
                    for (spell in spellsAllLanguage) {
                        spells.add(spell.ru)
                    }
                    _loadSpellsActionState.emit(LoadSpellsActionState.Data(spells))
                } ?: _loadSpellsActionState.emit(LoadSpellsActionState.Error("Context is null"))

            } catch (error: Throwable) {
                Log.d(LOG_TAG, error.message.toString())
                _loadSpellsActionState.emit(LoadSpellsActionState.Error(error.message.toString()))
            }
        }
    }

}