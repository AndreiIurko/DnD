package com.andreyyurko.dnd.utils

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreyyurko.dnd.data.SpellSpecificLanguage
import com.andreyyurko.dnd.db.DB
import com.andreyyurko.dnd.db.DBProvider
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.reflect.Type
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SpellsFavoritesHolder @Inject constructor(
    private val dbProvider: DBProvider
) : ViewModel() {
    private val db: DB by lazy(LazyThreadSafetyMode.NONE) {
        dbProvider.getDB(DB_TAG)
    }

    private var favoriteSpells : MutableSet<SpellSpecificLanguage> = mutableSetOf()
    private var _initActionState = MutableStateFlow<InitializationState>(InitializationState.NotInitialized)
    val initActionState: Flow<InitializationState> get() = _initActionState.asStateFlow()

    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val type: Type = Types.newParameterizedType(MutableSet::class.java, SpellSpecificLanguage::class.java)
    private val adapter: JsonAdapter<MutableSet<SpellSpecificLanguage>> = moshi.adapter(type)

    fun initialize() {
        viewModelScope.launch {
            val spellsJson = db.getString(DB_FAVORITE_SPELLS_TAG)
            favoriteSpells = spellsJson?.let { adapter.fromJson(it) } ?: mutableSetOf()
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
            val spellsJson = adapter.toJson(favoriteSpells) ?: ""
            db.putStringsAsync(
                listOf(
                    Pair(DB_FAVORITE_SPELLS_TAG, spellsJson)
                )
            )
        }
    }

    companion object {
        private const val DB_TAG = "favoriteSpells"

        private const val DB_FAVORITE_SPELLS_TAG = "favorite_spells"

        private const val LOG_TAG = "SpellsFavorites"
    }

    sealed class InitializationState {
        object NotInitialized: InitializationState()
        object Initialized: InitializationState()
    }
}