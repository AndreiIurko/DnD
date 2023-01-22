package com.andreyyurko.dnd

import android.app.Application
import com.andreyyurko.dnd.utils.CharactersHolder
import com.andreyyurko.dnd.utils.InventoryHandler
import com.andreyyurko.dnd.utils.SpellsFavoritesHolder
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var spellsFavoritesHolder: SpellsFavoritesHolder

    @Inject
    lateinit var charactersHolder: CharactersHolder

    @Inject
    lateinit var inventoryHandler: InventoryHandler

    override fun onCreate() {
        super.onCreate()
        spellsFavoritesHolder.initialize()

        // order is relevant
        inventoryHandler.initialize(applicationContext)
        charactersHolder.initialize()
    }
}