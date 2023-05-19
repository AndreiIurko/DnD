package com.andreyyurko.dnd.utils

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.andreyyurko.dnd.data.characterData.character.Character
import com.andreyyurko.dnd.data.characterData.character.mergeAllAbilities
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.InputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterViewModel @Inject constructor(
    private val charactersHolder: CharactersHolder,
    private val createCharacterViewModel: CreateCharacterViewModel,
    application: Application
) : AndroidViewModel(application) {
    var charactersBriefInfo = charactersHolder.getBriefInfo()

    var isDiceMenuShown = false
    var diceSet: MutableMap<String, Int> = mutableMapOf(
        Pair("d4", 0),
        Pair("d6", 0),
        Pair("d8", 0),
        Pair("d10", 0),
        Pair("d12", 0),
        Pair("d20", 0),
        Pair("d100", 0)
    )

    private lateinit var shownCharacter: Character

    val dataState: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val backgroundIsShown: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    fun getCharacter(): Character {
        return shownCharacter
    }

    fun updateBriefInfo() {
        charactersBriefInfo = charactersHolder.getBriefInfo()
    }

    fun setShownCharacter(id: Int) {
        shownCharacter = charactersHolder.getCharacterById(id)
    }

    fun updateCharacterInfo() {
        charactersHolder.isSavingNeeded = true
        dataState.value = DataState.Loading.stateName
        mergeAllAbilities(shownCharacter)
        dataState.value = DataState.Complete.stateName
    }

    fun changeShownCharacter() {
        createCharacterViewModel.character = charactersHolder.getCharacterCopy(shownCharacter.id)
        createCharacterViewModel.currentlyChangingCharacterId = shownCharacter.id
    }

    fun deleteCharacter(id: Int) {
        charactersHolder.removeCharacterById(id)
    }

    fun showPopUpBackground() {
        backgroundIsShown.value = true
    }

    fun closePopUpBackground() {
        backgroundIsShown.value = false
    }

    enum class DataState(val stateName: String) {
        Loading("loading"),
        Complete("Complete")
    }

    data class ImageState(
        val imageBitmap: ImageBitmap? = null,
    )

    private val _imageState = MutableStateFlow(ImageState())
    val imageState = _imageState.asStateFlow()

    fun setImageUri(uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            getContext().contentResolver.let { contentResolver: ContentResolver ->
                val readUriPermission: Int = Intent.FLAG_GRANT_READ_URI_PERMISSION
                contentResolver.takePersistableUriPermission(uri, readUriPermission)
                contentResolver.openInputStream(uri)?.use { inputStream: InputStream ->
                    _imageState.update { currentState: ImageState ->
                        currentState.copy(
                            imageBitmap = BitmapFactory.decodeStream(inputStream).asImageBitmap()
                        )
                    }
                }
            }
        }
    }

    fun saveBitmap(bitmap: Bitmap) {
        shownCharacter.image = bitmap
        charactersHolder.updateCharacterImage(shownCharacter, bitmap)
    }

    private fun getContext(): Context = getApplication<Application>().applicationContext
}