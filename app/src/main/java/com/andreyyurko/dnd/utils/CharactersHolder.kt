package com.andreyyurko.dnd.utils

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreyyurko.dnd.data.abilities.mapOfAn
import com.andreyyurko.dnd.data.characterData.*
import com.andreyyurko.dnd.data.characterData.character.Character
import com.andreyyurko.dnd.data.characterData.character.CharacterAbilityNode
import com.andreyyurko.dnd.data.characterData.character.mergeAllAbilities
import com.andreyyurko.dnd.db.DB
import com.andreyyurko.dnd.db.DBProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.reflect.Type
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CharactersHolder @Inject constructor(
    private val dbProvider: DBProvider
) : ViewModel() {
    private val db: DB by lazy(LazyThreadSafetyMode.NONE) {
        dbProvider.getDB(DB_TAG)
    }

    private var characters : MutableMap<Int, Character> = mutableMapOf()

    private var _initActionState = MutableStateFlow<InitializationState>(InitializationState.NotInitialized)
    val initActionState: Flow<InitializationState> get() = _initActionState.asStateFlow()
    fun initialize() {
        viewModelScope.launch {
            val listIdsType: Type = object : TypeToken<List<Int>>() {}.type
            val charactersListJson = db.getString(DB_CHARACTER_IDS)
            val charactersList: List<Int> = Gson().fromJson(charactersListJson, listIdsType) ?: emptyList()
            for (id in charactersList) {
                // get name
                val name = db.getString(DB_CHARACTER_NAME + id.toString())

                // init character
                var character = Character(
                    id = id,
                    name = name!!,
                    characterInfo = CharacterInfo(),
                )

                // get custom info
                val characterCharacterInfoJson = db.getString(id.toString() + DB_CHARACTER_CUSTOM)
                val characterCharacterInfo = Gson().fromJson(characterCharacterInfoJson, CharacterInfo::class.java)

                // get current state
                val currentStateJson = db.getString(id.toString() + DB_CHARACTER_STATE)
                val currentState = Gson().fromJson(currentStateJson, CurrentState::class.java)

                // get base_an with all sub-nods
                val baseCharacterAbilityNode = loadCharacterNode("base_an", id, ".", character)

                // init essential props
                character.customAbilities = characterCharacterInfo
                character.baseCAN = baseCharacterAbilityNode

                // add all custom data
                character.characterInfo = mergeCharacterInfo(character.characterInfo, character.customAbilities)

                // load current state
                character.characterInfo.currentState = currentState

                // merge all CAN
                mergeAllAbilities(character)

                // add to character list
                characters[id] = character
            }
            _initActionState.emit(InitializationState.Initialized)

            // TODO: how to clear memory that we are not using any more? Maybe Android are doing it already? Need to dig into it
            //db.clearMemory()
            //saveCharacters()
        }
    }

    fun getCharacters(): List<Character> {
        return characters.values.toList()
    }

    fun isCharacterExists(id: Int): Boolean {
        return characters.contains(id)
    }
    fun getCharacterById(id: Int): Character {
        characters[id]?.let {
            return it
        }
        throw IllegalArgumentException("No such id: ${id}!")
    }

    fun addCharacter(character: Character): Character {
        if (characters.isEmpty()) character.id = 0
        else {
            for (i in 0 ..characters.size) {
                if (characters.contains(i)) continue
                character.id = i
            }
        }
        characters[character.id] = character
        saveCharacter(character.id)
        return character
    }

    fun updateCharacter(character: Character) {
        if (characters.contains(character.id)) {
            characters[character.id] = character
            saveCharacter(character.id)
            return
        }

        throw IllegalArgumentException("Character with id ${character.id} not exist!")
    }

    fun removeCharacterById(id: Int) {
        if (characters.contains(id)) {
            val character = characters.remove(id)!!
            deleteCharacter(character)
            return
        }
        throw IllegalArgumentException("No such id: ${id}!")
    }

    private fun saveCharacter(id: Int) {
        val listOfStrings = mutableListOf<Pair<String, String>>()
        listOfStrings.add(Pair(DB_CHARACTER_IDS, Gson().toJson(characters.keys)))
        // add to save list name
        listOfStrings.add(Pair(DB_CHARACTER_NAME + id.toString(), characters[id]!!.name))

        // add to save list custom info
        val characterCharacterInfoJson = Gson().toJson(characters[id]!!.customAbilities, CharacterInfo::class.java)
        listOfStrings.add(Pair(
            id.toString() + DB_CHARACTER_CUSTOM,
            characterCharacterInfoJson
        ))

        // add to save list current state
        val characterCurrentStateJson = Gson().toJson(characters[id]!!.characterInfo.currentState)
        listOfStrings.add(Pair(
            id.toString() + DB_CHARACTER_STATE,
            characterCurrentStateJson
        ))

        // save all graph of choices
        saveCharacterNode(characters[id]!!.baseCAN, id, ".")

        db.putStringsAsync(
            listOfStrings
        )
    }
    fun saveCharacters() {
        viewModelScope.launch {
            for (id in characters.keys) {
                saveCharacter(id)
            }
        }
    }

    private fun deleteCharacter(character: Character) {
        db.putStringsAsync(listOf(
            Pair(DB_CHARACTER_IDS, Gson().toJson(characters.keys))
        ))

        db.deleteDataAsync(listOf(
            DB_CHARACTER_NAME + character.id.toString(),
            character.id.toString() + DB_CHARACTER_CUSTOM,
            character.id.toString() + DB_CHARACTER_STATE
        ))

        deleteCharacterNode(character.baseCAN, character.id, ".")
    }

    private fun saveCharacterNode(characterAbilityNode: CharacterAbilityNode, characterId: Int, path: String) {
        // save all sub-nodes
        for (characterNode in characterAbilityNode.chosen_alternatives.values) {
            saveCharacterNode(characterNode, characterId, path + characterAbilityNode.data.name + '.')
        }

        // get chosen AN names and save it using current AN option_name as key
        val chosenAlternativesNames : MutableMap<String, String> = mutableMapOf()
        for (key in characterAbilityNode.chosen_alternatives.keys) {
            characterAbilityNode.chosen_alternatives[key]?.apply {
                chosenAlternativesNames[key] = this.data.name
            }
        }

        // save map option_name -> chosen_option_name
        val chosenAlternativesJson = Gson().toJson(chosenAlternativesNames)
        db.putStringsAsync(listOf(
            Pair(
                characterId.toString() + DB_CHARACTER_ABILITY_NODE + path + characterAbilityNode.data.name,
                chosenAlternativesJson
            )
        ))
    }

    private fun loadCharacterNode(name: String, id: Int, path: String, character: Character) : CharacterAbilityNode {
        // init type to load from db
        val mapType: Type = object : TypeToken<Map<String, String>>() {}.type

        // get map option_name -> chosen_option_name
        val chosenAlternativesNamesJson = db.getString(id.toString() + DB_CHARACTER_ABILITY_NODE + path + name)
        val chosenAlternatives = Gson().fromJson<Map<String, String>>(chosenAlternativesNamesJson, mapType)

        // create CAN with reference to AN and empty chosen_alternatives
        val characterAbilityNode = CharacterAbilityNode(mapOfAn[name]!!, character)

        // add to chosen_alternatives all references to sub-nodes
        for (key in chosenAlternatives.keys) {
            val chosenNode = loadCharacterNode(chosenAlternatives[key]!!, id, path + characterAbilityNode.data.name + '.', character)
            characterAbilityNode.chosen_alternatives[key] = chosenNode
        }

        return characterAbilityNode
    }

    private fun deleteCharacterNode(characterAbilityNode: CharacterAbilityNode, characterId: Int, path: String) {
        for (characterNode in characterAbilityNode.chosen_alternatives.values) {
            deleteCharacterNode(characterNode, characterId, path + characterAbilityNode.data.name + '.')
        }

        db.deleteDataAsync(listOf(
            characterId.toString() + DB_CHARACTER_ABILITY_NODE + path + characterAbilityNode.data.name
        ))
    }

    fun getBriefInfo(): MutableList<CharacterBriefInfo> {
        val info : MutableList<CharacterBriefInfo> = mutableListOf()
        for (character in characters.values) {
            info.add(CharacterBriefInfo(
                id = character.id,
                name = character.name,
                characterClass = character.characterInfo.characterClass.className,
                level = character.characterInfo.level.toString()
            ))
        }
        return info
    }

    companion object {
        private const val DB_TAG = "charactersInfo"

        private const val DB_CHARACTER_IDS = "CharacterIds"
        private const val DB_CHARACTER_NAME = "CharacterName="
        private const val DB_CHARACTER_STATE = "CharacterState"
        private const val DB_CHARACTER_ABILITY_NODE = "_CharacterAbilityNode_"
        private const val DB_CHARACTER_CUSTOM = "_CharacterCustom"

        private const val LOG_TAG = "CharacterHolder"
    }

    sealed class InitializationState {
        object NotInitialized: InitializationState()
        object Initialized: InitializationState()
    }
}