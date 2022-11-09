package com.andreyyurko.dnd.ui.spellslist

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreyyurko.dnd.data.SpellSpecificLanguage
import com.andreyyurko.dnd.utils.SpellsFavoritesHolder
import com.andreyyurko.dnd.utils.SpellsParser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class SpellsListViewModel @Inject constructor(
    private val spellsParser: SpellsParser,
) : ViewModel() {

    @Inject
    lateinit var spellsFavoritesHolder: SpellsFavoritesHolder

    companion object {
        const val LOG_TAG = "SpellsListViewModel"
    }

    sealed class LoadSpellsActionState {
        object Loading : LoadSpellsActionState()
        object Ok : LoadSpellsActionState()
        data class Error(val error: String) : LoadSpellsActionState()
    }

    private val _loadSpellsActionState = MutableStateFlow<LoadSpellsActionState>(LoadSpellsActionState.Loading)
    val loadSpellsActionState: Flow<LoadSpellsActionState> get() = _loadSpellsActionState.asStateFlow()

    enum class SpellListState {
        All, Favorites
    }

    var allSpells: List<SpellSpecificLanguage> = mutableListOf()
    var favoriteSpells: List<SpellSpecificLanguage> = mutableListOf()
    private var shownSpells: List<SpellSpecificLanguage> = mutableListOf()
    var shownList = SpellListState.All


    var searchQuery = ""

    fun parseSpells(context: Context?) {
        if (allSpells.isEmpty()) {
            viewModelScope.launch {
                _loadSpellsActionState.emit(LoadSpellsActionState.Loading)
                //val spells = mutableListOf<SpellSpecificLanguage>()
                try {
                    context?.let {
                        // TODO: check language, and load spells based on it
                        /*
                        val spellsAllLanguage = spellsParser.parseSpells(it)
                        for (spell in spellsAllLanguage) {
                            spells.add(spell.ru)
                        }*/
                        val spells = spellsParser.parseRuSpells(it)
                        allSpells = spells
                        _loadSpellsActionState.emit(LoadSpellsActionState.Ok)
                    } ?: _loadSpellsActionState.emit(LoadSpellsActionState.Error("Context is null"))

                } catch (error: Throwable) {
                    Log.d(LOG_TAG, error.message.toString())
                    _loadSpellsActionState.emit(LoadSpellsActionState.Error(error.message.toString()))
                }
            }
        }
    }
    fun getFavoriteSpells() {
        favoriteSpells = spellsFavoritesHolder.getFavoriteSpells().toList()
    }

    fun saveFavoriteSpells() {
        spellsFavoritesHolder.saveFavoritesSpells()
    }

    fun setShownSpellList(newSearchQuery: String, adapter: SpellsListAdapter) {
        shownSpells = when (shownList) {
            SpellListState.All -> {
                allSpells
            }

            SpellListState.Favorites -> {
                favoriteSpells
            }
        }

        if (newSearchQuery != searchQuery || searchQuery == "") {
            searchQuery = newSearchQuery
            if (searchQuery != "") shownSpells = filterBySearch(shownSpells, searchQuery)
            adapter.spellsList = shownSpells
            adapter.notifyDataSetChanged()
        }
    }

}