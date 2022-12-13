package com.andreyyurko.dnd.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreyyurko.dnd.data.abilities.mapOfAn
import com.andreyyurko.dnd.data.characters.*
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

    private var characters : MutableList<Character> = mutableListOf()

    private var _initActionState = MutableStateFlow<InitializationState>(InitializationState.NotInitialized)
    val initActionState: Flow<InitializationState> get() = _initActionState.asStateFlow()
    fun initialize() {
        viewModelScope.launch {
            val charactersCount = db.getString(DB_CHARACTER_COUNT)?.toInt() ?: 0
            for (i in 0 until charactersCount) {
                // get name
                val name = db.getString(DB_CHARACTER_NAME + i.toString())

                // get custom info
                val characterCharacterInfoJson = db.getString(i.toString() + DB_CHARACTER_CUSTOM)
                val characterCharacterInfo = Gson().fromJson(characterCharacterInfoJson, CharacterInfo::class.java)

                // get current state
                val currentStateJson = db.getString(i.toString() + DB_CHARACTER_STATE)
                val currentState = Gson().fromJson(currentStateJson, CurrentState::class.java)

                // get base_an with all sub-nods
                val classCharacterAbilityNode = loadCharacterNode("base_an", i)

                // init character
                var character = Character(
                    id = i,
                    name = name!!,
                    characterInfo = CharacterInfo(),
                    customAbilities = characterCharacterInfo,
                    baseCAN = classCharacterAbilityNode
                )

                // add all custom data
                character.characterInfo = mergeCharacterInfo(character.characterInfo, character.customAbilities)

                // load current state
                character.characterInfo.currentState = currentState

                // merge all CAN
                character = mergeAllAbilities(character)

                // add to character list
                characters.add(character)
            }
            _initActionState.emit(InitializationState.Initialized)

            // TODO: how to clear memory that we are not using any more? Maybe Android are doing it already? Need to dig into it
            //db.clearMemory()
            //saveCharacters()
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
                // add to save list name
                listOfStrings.add(Pair(DB_CHARACTER_NAME + i.toString(), characters[i].name))

                // add to save list custom info
                val characterCharacterInfoJson = Gson().toJson(characters[i].customAbilities, CharacterInfo::class.java)
                listOfStrings.add(Pair(
                    i.toString() + DB_CHARACTER_CUSTOM,
                    characterCharacterInfoJson
                ))

                // add to save list current state
                val characterCurrentStateJson = Gson().toJson(characters[i].characterInfo.currentState)
                listOfStrings.add(Pair(
                    i.toString() + DB_CHARACTER_STATE,
                    characterCurrentStateJson
                ))

                // save all graph of choices
                saveCharacterNode(characters[i].baseCAN, i)
            }
            db.putStringsAsync(
                listOfStrings
            )
        }
    }

    private fun saveCharacterNode(characterAbilityNode: CharacterAbilityNode, characterId: Int) {
        // save all sub-nodes
        for (characterNode in characterAbilityNode.chosen_alternatives.values) {
            saveCharacterNode(characterNode, characterId)
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
                characterId.toString() + DB_CHARACTER_ABILITY_NODE + characterAbilityNode.data.name,
                chosenAlternativesJson
            )
        ))
    }

    private fun loadCharacterNode(name: String, id: Int) : CharacterAbilityNode{
        // init type to load from db
        val mapType: Type = object : TypeToken<Map<String, String>>() {}.type

        // get map option_name -> chosen_option_name
        val chosenAlternativesNamesJson = db.getString(id.toString() + DB_CHARACTER_ABILITY_NODE + name)
        val chosenAlternatives = Gson().fromJson<Map<String, String>>(chosenAlternativesNamesJson, mapType)

        // create CAN with reference to AN and empty chosen_alternatives
        val characterAbilityNode = CharacterAbilityNode(mapOfAn[name]!!)

        // add to chosen_alternatives all references to sub-nodes
        for (key in chosenAlternatives.keys) {
            val chosenNode = loadCharacterNode(chosenAlternatives[key]!!, id)
            characterAbilityNode.chosen_alternatives[chosenAlternatives[key]!!] = chosenNode
        }

        return characterAbilityNode
    }

    fun getBriefInfo(): MutableList<CharacterBriefInfo> {
        val info : MutableList<CharacterBriefInfo> = mutableListOf()
        for (character in characters) {
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

        private const val DB_CHARACTER_COUNT = "CharacterCount"
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