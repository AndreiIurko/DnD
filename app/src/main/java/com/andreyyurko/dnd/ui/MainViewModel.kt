package com.andreyyurko.dnd.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreyyurko.dnd.utils.CharactersHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val charactersHolder: CharactersHolder
) : ViewModel() {

    private val _initState: MutableStateFlow<InitState> = MutableStateFlow(InitState.NotInitialized)
    val initState: Flow<InitState> get() = _initState.asStateFlow()
    var isReady = false

    fun initCharacterHolder() {
        viewModelScope.launch {
            charactersHolder.initActionState.collect{
                when(it) {
                    CharactersHolder.InitializationState.NotInitialized ->
                        _initState.emit(InitState.NotInitialized)
                    CharactersHolder.InitializationState.Initialized -> {
                        _initState.emit(InitState.Initialized)
                        isReady = true
                        Log.d("test", "ready")
                    }
                }
            }
        }
    }

    sealed class InitState {
        object NotInitialized: InitState()
        object Initialized: InitState()
    }
}