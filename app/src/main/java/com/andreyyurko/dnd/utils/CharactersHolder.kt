package com.andreyyurko.dnd.utils

import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.db.DB
import com.andreyyurko.dnd.db.DBProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharactersHolder @Inject constructor(
    private val dbProvider: DBProvider
) : ViewModel() {
    private val db: DB by lazy(LazyThreadSafetyMode.NONE) {
        dbProvider.getDB(DB_TAG)
    }

    fun initialize() {

    }

    companion object {
        private const val DB_TAG = "charactersInfo"

        private const val LOG_TAG = "CharacterHolder"
    }
}