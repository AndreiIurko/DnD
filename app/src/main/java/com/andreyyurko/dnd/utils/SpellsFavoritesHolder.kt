package com.andreyyurko.dnd.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreyyurko.dnd.data.SpellSpecificLanguage
import com.andreyyurko.dnd.db.DB
import com.andreyyurko.dnd.db.DBProvider
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SpellsFavoritesHolder @Inject constructor(
    private val dbProvider: DBProvider
) : ViewModel() {
    private val db: DB by lazy(LazyThreadSafetyMode.NONE) {
        dbProvider.getDB(DB_TAG)
    }

    private var favoriteSpells: MutableSet<SpellSpecificLanguage> = mutableSetOf()
    private var _initActionState = MutableStateFlow<InitializationState>(InitializationState.NotInitialized)
    val initActionState: Flow<InitializationState> get() = _initActionState.asStateFlow()

    fun initialize() {
        viewModelScope.launch {
            val size = db.getString(DB_FAVORITE_SPELLS_COUNT_TAG)?.toInt() ?: 0
            for (i in 0 until size) {
                val spellJson = db.getString(DB_FAVORITE_SPELLS_TAG + i.toString())
                val spell = Gson().fromJson(spellJson, SpellSpecificLanguage::class.java)
                if (spell != null) {
                    favoriteSpells.add(spell)
                }
            }
            _initActionState.emit(InitializationState.Initialized)
        }
    }

    fun getFavoriteSpells(): MutableSet<SpellSpecificLanguage> {
        return favoriteSpells
    }

    fun putFavoriteSpell(spell: SpellSpecificLanguage) {
        favoriteSpells.add(spell)
        saveFavoritesSpells()
    }

    fun removeFavoriteSpell(spell: SpellSpecificLanguage) {
        favoriteSpells.remove(spell)
        saveFavoritesSpells()
    }

    fun saveFavoritesSpells() {
        viewModelScope.launch {
            db.putStringsAsync(
                listOf(
                    Pair(DB_FAVORITE_SPELLS_COUNT_TAG, favoriteSpells.size.toString())
                )
            )
            val spellsList = favoriteSpells.toList()
            for (i in spellsList.indices) {
                val spellJson = Pair(DB_FAVORITE_SPELLS_TAG + i.toString(), Gson().toJson(spellsList[i]))
                db.putStringsAsync(listOf(spellJson))
            }

        }
    }

    companion object {
        private const val DB_TAG = "favoriteSpells"
        private const val DB_FAVORITE_SPELLS_TAG = "favorite_spells"
        private const val DB_FAVORITE_SPELLS_COUNT_TAG = "favorite_spells_count"
        private const val LOG_TAG = "SpellsFavorites"
    }

    sealed class InitializationState {
        object NotInitialized : InitializationState()
        object Initialized : InitializationState()
    }
}