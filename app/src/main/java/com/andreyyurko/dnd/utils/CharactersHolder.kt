package com.andreyyurko.dnd.utils

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.andreyyurko.dnd.data.abilities.mapOfAn
import com.andreyyurko.dnd.data.characterData.*
import com.andreyyurko.dnd.data.characterData.character.Character
import com.andreyyurko.dnd.data.characterData.character.CharacterAbilityNode
import com.andreyyurko.dnd.data.characterData.character.mergeAllAbilities
import com.andreyyurko.dnd.data.inventory.InventoryItemInfo
import com.andreyyurko.dnd.data.spells.CharacterSpells
import com.andreyyurko.dnd.db.DB
import com.andreyyurko.dnd.db.DBProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.FileInputStream
import java.io.FileOutputStream
import java.lang.reflect.Type
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.concurrent.timerTask


@Singleton
class CharactersHolder @Inject constructor(
    private val dbProvider: DBProvider,
    application: Application
) : AndroidViewModel(application) {
    private val db: DB by lazy(LazyThreadSafetyMode.NONE) {
        dbProvider.getDB(DB_TAG)
    }

    private var characters: MutableMap<Int, Character> = mutableMapOf()
    private var characterImages: MutableMap<Int, Bitmap?> = mutableMapOf()

    private var _initActionState = MutableStateFlow<InitializationState>(InitializationState.NotInitialized)
    val initActionState: Flow<InitializationState> get() = _initActionState.asStateFlow()

    private var _savingCharactersState = MutableStateFlow<SavingCharactersState>(SavingCharactersState.NotCompleted)
    var savingCharactersState: Flow<SavingCharactersState> = _savingCharactersState.asStateFlow()

    var isSavingNeeded = false

    fun initialize() {
        try {
            Timer().schedule(timerTask {
                if (_initActionState.asStateFlow().value != InitializationState.Initialized) {
                    viewModelScope.launch {
                        loadCharacters()
                    }
                }
            }, 20000)

            val worksQuery = WorkManager.getInstance(getApplication<Application>().applicationContext)
                .getWorkInfosByTagLiveData("saveCharacterInfo")

            val observer = Observer<List<WorkInfo>> { workList ->
                var isAllFinished = true
                for (work in workList) {
                    if (work.state != WorkInfo.State.SUCCEEDED) {
                        isAllFinished = false
                    }
                }
                if (isAllFinished) {
                    viewModelScope.launch {
                        loadCharacters()
                    }
                }
            }
            worksQuery.observeForever(observer)

            viewModelScope.launch {
                initActionState.collect {
                    if (it == InitializationState.Initialized) {
                        worksQuery.removeObserver(observer)
                    }
                }
            }
        }
        catch (e: Exception) {
            viewModelScope.launch {
                loadCharacters()
            }
        }
    }

    private suspend fun loadCharacters() {
        try {
            val listIdsType: Type = object : TypeToken<List<Int>>() {}.type
            val charactersListJson = db.getString(DB_CHARACTER_IDS)
            val charactersList: List<Int> = Gson().fromJson(charactersListJson, listIdsType) ?: emptyList()
            for (id in charactersList) {
                val character = loadCharacter(id)

                // add to character list
                character?.let {
                    characters[id] = character
                }

            }
        }
        catch (e: Exception) {
            characters = mutableMapOf()
        }
        _initActionState.emit(InitializationState.Initialized)
    }


    private fun loadCharacter(id: Int): Character? {
        var character: Character? = null
        try {
            // get name
            val name = db.getString(DB_CHARACTER_NAME + id.toString())
            val bitmap = loadCharacterBitmap(id)
            characterImages[id] = bitmap

            // init character
            character = Character(
                id = id,
                image = bitmap,
                name = name!!,
                characterInfo = CharacterInfo(),
            )

            // get custom info
            val characterCharacterInfoJson = db.getString(id.toString() + DB_CHARACTER_CUSTOM)
            val characterCharacterInfo = if (characterCharacterInfoJson != null) Gson().fromJson(
                characterCharacterInfoJson,
                CharacterInfo::class.java
            ) else CharacterInfo()

            // get current state
            val currentStateJson = db.getString(id.toString() + DB_CHARACTER_STATE)
            val currentState = if (currentStateJson != null) Gson().fromJson(
                currentStateJson,
                CurrentState::class.java
            ) else CurrentState()

            //get inventory
            val inventoryMapType: Type = object : TypeToken<MutableMap<String, InventoryItemInfo>>() {}.type
            val inventoryJson = db.getString(id.toString() + DB_INVENTORY)
            val inventory: MutableMap<String, InventoryItemInfo> =
                if (inventoryJson != null) Gson().fromJson(inventoryJson, inventoryMapType) else mutableMapOf()

            // get spells
            val spellsMapType: Type = object : TypeToken<MutableMap<String, CharacterSpells>>() {}.type
            val spellsJson = db.getString(id.toString() + DB_SPELLS)
            val spells: MutableMap<String, CharacterSpells> =
                if (spellsJson != null) Gson().fromJson(spellsJson, spellsMapType) else mutableMapOf()

            val notesListType: Type = object : TypeToken<MutableList<Note>>() {}.type
            val notesJson = db.getString(id.toString() + DB_NOTES)
            val notes: MutableList<Note> =
                if (notesJson != null) Gson().fromJson(notesJson, notesListType) else mutableListOf()

            // get base_an with all sub-nods
            val baseCharacterAbilityNode = loadCharacterNode("base_an", id, ".", character)

            // init essential props
            character.customAbilities = characterCharacterInfo
            character.baseCAN = baseCharacterAbilityNode

            // add all custom data
            character.characterInfo = mergeCharacterInfo(character.characterInfo, character.customAbilities)

            // add current state
            character.characterInfo.currentState = currentState

            // add inventory
            character.characterInfo.inventory = inventory

            // add chosen spells
            character.characterInfo.spellsInfo = spells

            // add notes
            character.notes = notes

            // merge all CAN
            mergeAllAbilities(character)
        }
        catch (e: Exception) {
            Log.d(LOG_TAG, e.message.toString())
            character = null
        }

        return character
    }

    private fun loadCharacterBitmap(id: Int): Bitmap? {
        val name = id.toString() + DB_IMAGE
        val fileInputStream: FileInputStream
        var bitmap: Bitmap? = null
        try {
            fileInputStream = getApplication<Application>().applicationContext.openFileInput(name)
            bitmap = BitmapFactory.decodeStream(fileInputStream)
            fileInputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

        return bitmap
    }

    private fun loadCharacterNode(name: String, id: Int, path: String, character: Character): CharacterAbilityNode {

        // init type to load from db
        val mapType: Type = object : TypeToken<Map<String, String>>() {}.type

        // get map option_name -> chosen_option_name
        val chosenAlternativesNamesJson = db.getString(id.toString() + DB_CHARACTER_ABILITY_NODE + path + name)
        val chosenAlternatives = Gson().fromJson<Map<String, String>>(chosenAlternativesNamesJson, mapType)

        // get map option_name -> chosen_option_for_data_action
        val chosenAlternativesForActionsJson = db.getString(id.toString() + DB_CHARACTER_ABILITY_NODE2 + path + name)
        val chosenAlternativesForActions = if (chosenAlternativesForActionsJson != null) Gson().fromJson<Map<String, String>>(chosenAlternativesForActionsJson, mapType) else mutableMapOf()

        // create CAN with reference to AN and empty chosen_alternatives
        val characterAbilityNode = CharacterAbilityNode(mapOfAn[name]!!, character)

        characterAbilityNode.chosenAlternativesForActions = chosenAlternativesForActions as MutableMap<String, String>

        // add to chosen_alternatives all references to sub-nodes
        for (key in chosenAlternatives.keys) {
            val chosenNode =
                loadCharacterNode(chosenAlternatives[key]!!, id, path + characterAbilityNode.data.name + '.', character)
            characterAbilityNode.chosen_alternatives[key] = chosenNode
        }

        return characterAbilityNode
    }

    // Everything beneath about methods ---------------------------------------------

    fun getCharacterById(id: Int): Character {
        characters[id]?.let {
            return it
        }
        throw IllegalArgumentException("No such id: ${id}!")
    }

    fun addCharacter(character: Character): Character {
        if (characters.isEmpty()) character.id = 0
        else {
            for (i in 0..characters.size) {
                if (characters.contains(i)) continue
                character.id = i
                break
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

    fun updateCharacterImage(character: Character, bitmap: Bitmap) {
        character.image = bitmap
        characterImages[character.id] = bitmap
        getApplication<Application>().applicationContext.deleteFile(character.id.toString() + DB_IMAGE)
        saveCharacterBitmap(character.id, bitmap)
    }

    fun removeCharacterById(id: Int) {
        if (characters.contains(id)) {
            val character = characters.remove(id)!!
            deleteCharacter(character)
            return
        }
        throw IllegalArgumentException("No such id: ${id}!")
    }

    fun getBriefInfo(): MutableList<CharacterBriefInfo> {
        val info: MutableList<CharacterBriefInfo> = mutableListOf()
        for (character in characters.values) {
            info.add(
                CharacterBriefInfo(
                    id = character.id,
                    name = character.name,
                    characterClass = character.characterInfo.characterClass.className,
                    level = character.characterInfo.level.toString(),
                    bitmap = characterImages[character.id]
                )
            )
        }
        return info
    }

    // Everything beneath about copying character -----------------------------------

    fun getCharacterCopy(id: Int): Character {
        val jsonMap: MutableMap<String, String> = mutableMapOf()
        getJsons(jsonMap, id)

        val character = Character(
            id = -1,
            image = characterImages[id],
            name = characters[id]!!.name,
            characterInfo = CharacterInfo(),
        )

        val characterCharacterInfoJson = jsonMap[id.toString() + DB_CHARACTER_CUSTOM]!!
        val characterCharacterInfo = Gson().fromJson(characterCharacterInfoJson, CharacterInfo::class.java)

        val currentStateJson = jsonMap[id.toString() + DB_CHARACTER_STATE]!!
        val currentState = Gson().fromJson(currentStateJson, CurrentState::class.java)

        val inventoryMapType: Type = object : TypeToken<MutableMap<String, InventoryItemInfo>>() {}.type
        val inventoryJson = jsonMap[id.toString() + DB_INVENTORY]!!
        val inventory: MutableMap<String, InventoryItemInfo> = Gson().fromJson(inventoryJson, inventoryMapType)

        val spellsMapType: Type = object : TypeToken<MutableMap<String, CharacterSpells>>() {}.type
        val spellsJson = jsonMap[id.toString() + DB_SPELLS]!!
        val spells: MutableMap<String, CharacterSpells> = Gson().fromJson(spellsJson, spellsMapType)

        val notesListType: Type = object : TypeToken<MutableList<Note>>() {}.type
        val notesJson = jsonMap[id.toString() + DB_NOTES]!!
        val notes: MutableList<Note> = Gson().fromJson(notesJson, notesListType)

        val baseCharacterAbilityNode = copyCharacterNode(jsonMap, "base_an", id, ".", character)

        character.customAbilities = characterCharacterInfo
        character.baseCAN = baseCharacterAbilityNode

        character.characterInfo = mergeCharacterInfo(character.characterInfo, character.customAbilities)

        character.characterInfo.currentState = currentState

        character.characterInfo.inventory = inventory

        character.characterInfo.spellsInfo = spells

        character.notes = notes

        mergeAllAbilities(character)

        return character
    }



    // see saving character for understanding (it works similarly)
    private fun getJsons(jsonMap: MutableMap<String, String>, id: Int) {

        val customInfoJson = Gson().toJson(characters[id]!!.customAbilities)
        jsonMap[id.toString() + DB_CHARACTER_CUSTOM] = customInfoJson

        val currentStateJson = Gson().toJson(characters[id]!!.characterInfo.currentState)
        jsonMap[id.toString() + DB_CHARACTER_STATE] = currentStateJson

        val inventoryJson = Gson().toJson(characters[id]!!.characterInfo.inventory)
        jsonMap[id.toString() + DB_INVENTORY] = inventoryJson

        val spellsJson = Gson().toJson(characters[id]!!.characterInfo.spellsInfo)
        jsonMap[id.toString() + DB_SPELLS] = spellsJson

        val notesJson = Gson().toJson(characters[id]!!.notes)
        jsonMap[id.toString() + DB_NOTES] = notesJson

        getCharacterNodesJsons(jsonMap, characters[id]!!.baseCAN, id, ".")


    }

    // see saving character nodes for understanding (it works similarly)
    private fun getCharacterNodesJsons(jsonMap: MutableMap<String, String>, characterAbilityNode: CharacterAbilityNode,
                                       characterId: Int, path: String) {

        for (characterNode in characterAbilityNode.chosen_alternatives.values) {
            getCharacterNodesJsons(jsonMap, characterNode, characterId, path + characterAbilityNode.data.name + '.')
        }

        val chosenAlternativesNames: MutableMap<String, String> = mutableMapOf()
        for (key in characterAbilityNode.chosen_alternatives.keys) {
            characterAbilityNode.chosen_alternatives[key]?.apply {
                chosenAlternativesNames[key] = this.data.name
            }
        }

        val chosenAlternativesJson = Gson().toJson(chosenAlternativesNames)
        jsonMap[characterId.toString() + DB_CHARACTER_ABILITY_NODE + path + characterAbilityNode.data.name] = chosenAlternativesJson

        // save map option_name -> chosen_option_for_data_action
        val chosenAlternativesForActionsJson = Gson().toJson(characterAbilityNode.chosenAlternativesForActions)
        jsonMap[characterId.toString() + DB_CHARACTER_ABILITY_NODE2 + path + characterAbilityNode.data.name] = chosenAlternativesForActionsJson
    }

    // see loading character nodes for understanding (it works similarly)
    private fun copyCharacterNode(jsonMap: MutableMap<String, String>, name: String, id: Int,
                                         path: String, character: Character): CharacterAbilityNode {

        val mapType: Type = object : TypeToken<Map<String, String>>() {}.type

        val chosenAlternativesNamesJson = jsonMap[id.toString() + DB_CHARACTER_ABILITY_NODE + path + name]!!
        val chosenAlternatives = Gson().fromJson<Map<String, String>>(chosenAlternativesNamesJson, mapType)


        val chosenAlternativesForActionsJson = jsonMap[id.toString() + DB_CHARACTER_ABILITY_NODE2 + path + name]!!
        val chosenAlternativesForActions = Gson().fromJson<Map<String, String>>(chosenAlternativesForActionsJson, mapType)

        val characterAbilityNode = CharacterAbilityNode(mapOfAn[name]!!, character)

        characterAbilityNode.chosenAlternativesForActions = chosenAlternativesForActions as MutableMap<String, String>

        for (key in chosenAlternatives.keys) {
            val chosenNode =
                copyCharacterNode(jsonMap, chosenAlternatives[key]!!, id, path + characterAbilityNode.data.name + '.', character)
            characterAbilityNode.chosen_alternatives[key] = chosenNode
        }

        return characterAbilityNode
    }

    // TODO: on start sync save with load (in case fast kill and restore)
    private fun saveCharacter(id: Int) {
        saveCharacterBitmap(id, characters[id]!!.image)
        // in case changes in ids
        db.putStringsAsync(
            listOf(Pair(DB_CHARACTER_IDS, Gson().toJson(characters.keys)))
        )
        // save character name
        db.putStringsAsync(
            listOf(Pair(DB_CHARACTER_NAME + id.toString(), characters[id]!!.name))
        )

        // save character custom info
        val customInfoJson = Gson().toJson(characters[id]!!.customAbilities)
        db.putStringsAsync(
            listOf(Pair(id.toString() + DB_CHARACTER_CUSTOM, customInfoJson))
        )

        // save character current state
        val currentStateJson = Gson().toJson(characters[id]!!.characterInfo.currentState)
        db.putStringsAsync(
            listOf(Pair(id.toString() + DB_CHARACTER_STATE, currentStateJson))
        )

        // save character inventory
        val inventoryJson = Gson().toJson(characters[id]!!.characterInfo.inventory)
        db.putStringsAsync(
            listOf(Pair(id.toString() + DB_INVENTORY, inventoryJson))
        )

        // save chosen spells
        val spellsJson = Gson().toJson(characters[id]!!.characterInfo.spellsInfo)
        db.putStringsAsync(
            listOf(Pair(id.toString() + DB_SPELLS, spellsJson))
        )

        // save notes
        val notesJson = Gson().toJson(characters[id]!!.notes)
        db.putStringsAsync(
            listOf(Pair(id.toString() + DB_NOTES, notesJson))
        )

        // save all graph of choices
        saveCharacterNode(characters[id]!!.baseCAN, id, ".")
    }

    private fun saveCharacterBitmap(id: Int, bitmap: Bitmap?) {
        if (bitmap == null) return
        val name = id.toString() + DB_IMAGE
        val fileOutputStream: FileOutputStream
        try {
            fileOutputStream =
                getApplication<Application>().applicationContext.openFileOutput(name, Context.MODE_PRIVATE)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream)
            fileOutputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    fun saveCharacters() {
        for (id in characters.keys) {
            saveCharacter(id)
        }
    }

    fun saveCharactersForService() {
        viewModelScope.launch {
            _savingCharactersState.emit(SavingCharactersState.NotCompleted)
            for (id in characters.keys) {
                saveCharacter(id)
            }
            val worksQuery = WorkManager.getInstance(getApplication<Application>().applicationContext).getWorkInfosByTagLiveData("saveCharacterInfo")

            val observer = Observer<List<WorkInfo>> { workList ->
                var isAllFinished = true
                for (work in workList) {
                    if (work.state != WorkInfo.State.SUCCEEDED) {
                        isAllFinished = false
                    }
                }
                if (isAllFinished) {
                    viewModelScope.launch {
                        _savingCharactersState.emit(SavingCharactersState.Completed)
                    }
                }
            }
            worksQuery.observeForever(observer)

            viewModelScope.launch {
                savingCharactersState.collect {
                    if (it == SavingCharactersState.Completed) {
                        worksQuery.removeObserver(observer)
                    }
                }
            }
        }
    }

    private fun saveCharacterNode(characterAbilityNode: CharacterAbilityNode, characterId: Int, path: String) {
        // save all sub-nodes
        for (characterNode in characterAbilityNode.chosen_alternatives.values) {
            saveCharacterNode(characterNode, characterId, path + characterAbilityNode.data.name + '.')
        }

        // get chosen AN names and save it using current AN option_name as key
        val chosenAlternativesNames: MutableMap<String, String> = mutableMapOf()
        for (key in characterAbilityNode.chosen_alternatives.keys) {
            characterAbilityNode.chosen_alternatives[key]?.apply {
                chosenAlternativesNames[key] = this.data.name
            }
        }

        // save map option_name -> chosen_option_name
        val chosenAlternativesJson = Gson().toJson(chosenAlternativesNames)
        db.putStringsAsync(
            listOf(
                Pair(
                    characterId.toString() + DB_CHARACTER_ABILITY_NODE + path + characterAbilityNode.data.name,
                    chosenAlternativesJson
                )
            )
        )

        // save map option_name -> chosen_option_for_data_action
        val chosenAlternativesForActionsJson = Gson().toJson(characterAbilityNode.chosenAlternativesForActions)
        db.putStringsAsync(
            listOf(
                Pair(
                    characterId.toString() + DB_CHARACTER_ABILITY_NODE2 + path + characterAbilityNode.data.name,
                    chosenAlternativesForActionsJson
                )
            )
        )
    }

    // everything beneath about deleting character

    private fun deleteCharacter(character: Character) {

        getApplication<Application>().applicationContext.deleteFile(character.id.toString() + DB_IMAGE)
        characterImages.remove(character.id)

        db.putStringsAsync(
            listOf(
                Pair(DB_CHARACTER_IDS, Gson().toJson(characters.keys))
            )
        )

        db.deleteDataAsync(
            listOf(
                DB_CHARACTER_NAME + character.id.toString(),
                character.id.toString() + DB_CHARACTER_CUSTOM,
                character.id.toString() + DB_CHARACTER_STATE,
                character.id.toString() + DB_INVENTORY,
                character.id.toString() + DB_SPELLS
            )
        )

        deleteCharacterNode(character.baseCAN, character.id, ".")
    }

    private fun deleteCharacterNode(characterAbilityNode: CharacterAbilityNode, characterId: Int, path: String) {
        for (characterNode in characterAbilityNode.chosen_alternatives.values) {
            deleteCharacterNode(characterNode, characterId, path + characterAbilityNode.data.name + '.')
        }

        db.deleteDataAsync(
            listOf(
                characterId.toString() + DB_CHARACTER_ABILITY_NODE + path + characterAbilityNode.data.name
            )
        )

        db.deleteDataAsync(
            listOf(
                characterId.toString() + DB_CHARACTER_ABILITY_NODE2 + path + characterAbilityNode.data.name
            )
        )
    }

    companion object {
        private const val DB_TAG = "charactersInfo"

        private const val DB_CHARACTER_IDS = "ChaIds"
        private const val DB_CHARACTER_NAME = "ChaName="
        private const val DB_CHARACTER_STATE = "ChaState"
        private const val DB_CHARACTER_ABILITY_NODE = "_ChAbNo_"
        private const val DB_CHARACTER_ABILITY_NODE2 = "_ChAbNo2_"
        private const val DB_CHARACTER_CUSTOM = "_ChaCustom"
        private const val DB_INVENTORY = "_Inv"
        private const val DB_SPELLS = "_Spells"
        private const val DB_NOTES = "_Notes"
        private const val DB_IMAGE = "_Image"

        private const val LOG_TAG = "CharacterHolder"
    }

    sealed class InitializationState {
        object NotInitialized : InitializationState()
        object Initialized : InitializationState()
    }

    sealed class SavingCharactersState {
        object NotCompleted : SavingCharactersState()
        object Completed : SavingCharactersState()

        object Waiting : SavingCharactersState()
    }
}