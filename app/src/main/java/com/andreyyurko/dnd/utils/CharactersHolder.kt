package com.andreyyurko.dnd.utils

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreyyurko.dnd.data.characters.Character
import com.andreyyurko.dnd.data.characters.CharacterBriefInfo
import com.andreyyurko.dnd.data.characters.mergeAllAbilities
import com.andreyyurko.dnd.db.DB
import com.andreyyurko.dnd.db.DBProvider
import com.google.gson.Gson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharactersHolder @Inject constructor(
    private val dbProvider: DBProvider
) : ViewModel() {
    private val db: DB by lazy(LazyThreadSafetyMode.NONE) {
        dbProvider.getDB(DB_TAG)
    }

    private var characters : MutableList<Character> = mutableListOf()

    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val adapter: JsonAdapter<Character> = moshi.adapter(Character::class.java)


    private var _initActionState = MutableStateFlow<InitializationState>(InitializationState.NotInitialized)
    val initActionState: Flow<InitializationState> get() = _initActionState.asStateFlow()
    fun initialize() {
        viewModelScope.launch {
            val charactersCount = db.getString(DB_CHARACTER_COUNT)?.toInt() ?: 0
            for (i in 0 until charactersCount) {
                val characterJson = db.getString(DB_CHARACTER_ITEM + i.toString())
                //var character = characterJson?.let { adapter.fromJson(it) }
                var character = Gson().fromJson(characterJson, Character::class.java)
                if (character != null) {
                    character.id = i
                    character = mergeAllAbilities(character)
                    characters.add(character)
                }
            }
            _initActionState.emit(InitializationState.Initialized)
        }
    }

    fun getCharacters(): List<Character> {
        return characters
    }

    fun isCharacterExists(id: Int): Boolean {
        for (character in characters) {
            if (character.id == id) {
                return true
            }
        }
        return false
    }
    fun getCharacterById(id: Int): Character {
        for (character in characters) {
            if (character.id == id) {
                return character
            }
        }
        throw IllegalArgumentException("No such id: ${id}!")
    }

    fun addCharacter(character: Character): Character {
        if (characters.size != 0) character.id = characters.last().id + 1
        else character.id = 0
        characters.add(character)
        for (character_i in characters) {
            Log.d(LOG_TAG, character_i.characterInfo.toString())
        }
        saveCharacters()
        return character
    }

    fun updateCharacter(character: Character) {
        for (i in characters.indices) {
            if (characters[i].id == character.id) {
                characters[i] = character
                saveCharacters()
                return
            }
        }
        throw IllegalArgumentException("Character with id ${character.id} not exist!")
    }

    fun removeCharacterById(id: Int) {
        for (i in characters.indices) {
            if (characters[i].id == id) {
                characters.removeAt(i)
                saveCharacters()
                return
            }
        }
        throw IllegalArgumentException("No such id: ${id}!")
    }

    private fun saveCharacters() {
        viewModelScope.launch {
            val listOfStrings = mutableListOf<Pair<String, String>>()
            listOfStrings.add(Pair(DB_CHARACTER_COUNT, characters.size.toString()))
            for (i in characters.indices) {
                //val characterJson = adapter.toJson(characters[i])
                val characterJson = Gson().toJson(characters[i], Character::class.java)
                listOfStrings.add(Pair(DB_CHARACTER_ITEM + i.toString(), characterJson))
            }
            db.putStringsAsync(
                listOfStrings
            )
        }
    }

    fun getBriefInfo(): MutableList<CharacterBriefInfo> {
        val info : MutableList<CharacterBriefInfo> = mutableListOf()
        for (character in characters) {
            info.add(CharacterBriefInfo(
                id = character.id,
                name = character.name,
                characterClass = character.characterInfo.characterClass,
                level = character.characterInfo.level.toString()
            ))
        }
        return info
    }

    companion object {
        private const val DB_TAG = "charactersInfo"
        private const val DB_CHARACTER_COUNT = "CharacterCount"
        private const val DB_CHARACTER_ITEM = "CharacterItem"
        private const val LOG_TAG = "CharacterHolder"
    }

    sealed class InitializationState {
        object NotInitialized: InitializationState()
        object Initialized: InitializationState()
    }
}