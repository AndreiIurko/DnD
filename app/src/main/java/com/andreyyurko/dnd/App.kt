package com.andreyyurko.dnd

import android.app.Application
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.andreyyurko.dnd.ui.CrashActivity
import com.andreyyurko.dnd.utils.CharactersHolder
import com.andreyyurko.dnd.utils.InventoryHandler
import com.andreyyurko.dnd.utils.SpellsFavoritesHolder
import com.andreyyurko.dnd.utils.SpellsParser
import com.andreyyurko.dnd.utils.exceptionhandler.ExceptionListener
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


@HiltAndroidApp
class App : Application(), ExceptionListener {

    @Inject
    lateinit var spellsFavoritesHolder: SpellsFavoritesHolder

    @Inject
    lateinit var spellsParser: SpellsParser

    @Inject
    lateinit var charactersHolder: CharactersHolder

    @Inject
    lateinit var inventoryHandler: InventoryHandler

    var exceptions: MutableList<String> = mutableListOf()

    override fun onCreate() {
        super.onCreate()
        spellsFavoritesHolder.initialize()
        spellsParser.parseRuSpells(applicationContext)

        // order is relevant
        inventoryHandler.initialize(applicationContext)
        charactersHolder.initialize()
        setupExceptionHandler()
    }

    override fun uncaughtException(thread: Thread, throwable: Throwable) {
        // TODO Make sure you are logging this issue some where like Crashlytics.
        // Also indicate that something went wrong to the user like maybe a dialog or an activity.

        val stackTrace = Log.getStackTraceString(throwable)
        exceptions.add(stackTrace)

        val intent = Intent(applicationContext, CrashActivity::class.java)
            .addFlags(FLAG_ACTIVITY_NEW_TASK)
            .putExtra("exception_value", exceptions.toString())
        startActivity(intent)
    }

    private fun setupExceptionHandler() {
        Handler(Looper.getMainLooper()).post {
            while (true) {
                try {
                    Looper.loop()
                } catch (e: Throwable) {
                    uncaughtException(Looper.getMainLooper().thread, e)
                }
            }
        }
        Thread.setDefaultUncaughtExceptionHandler { t, e ->
            uncaughtException(t, e)
        }
    }
}