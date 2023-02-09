package com.andreyyurko.dnd.utils

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.data.SpellSpecificLanguage
import com.andreyyurko.dnd.data.spells.Spell
import com.google.gson.Gson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.IOException
import java.lang.reflect.Type
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SpellsParser @Inject constructor() : ViewModel() {
    companion object {
        const val LOG_TAG = "Spells parser"
    }

    var spells :  List<SpellSpecificLanguage>? = null

    fun parseRuSpells(context: Context): List<SpellSpecificLanguage> {

        spells?.let {
            return it
        }

        lateinit var jsonString: String
        try {
            jsonString = context.assets.open("spells_ru.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            Log.d(LOG_TAG, ioException.toString())
        }
        val type: Type = Types.newParameterizedType(MutableList::class.java, SpellSpecificLanguage::class.java)
        spells = Gson().fromJson(jsonString, type)
        return spells!!
    }
}