package com.andreyyurko.dnd.utils

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.andreyyurko.dnd.data.Spell
import com.andreyyurko.dnd.data.SpellSpecificLanguage
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.IOException
import java.lang.reflect.Type
import javax.inject.Inject


class SpellsParser @Inject constructor() : ViewModel() {
    companion object {
        const val LOG_TAG = "Spells parser"
    }

    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    fun parseSpells(context: Context): List<Spell> {
        lateinit var jsonString: String
        try {
            jsonString = context.assets.open("spells.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            Log.d(LOG_TAG, ioException.toString())
        }
        val type: Type = Types.newParameterizedType(MutableList::class.java, Spell::class.java)
        val adapter: JsonAdapter<List<Spell>> = moshi.adapter(type)
        return adapter.fromJson(jsonString)!!
    }

    fun parseRuSpells(context: Context): List<SpellSpecificLanguage> {
        lateinit var jsonString: String
        try {
            jsonString = context.assets.open("spells_ru.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            Log.d(LOG_TAG, ioException.toString())
        }
        val type: Type = Types.newParameterizedType(MutableList::class.java, SpellSpecificLanguage::class.java)
        val adapter: JsonAdapter<List<SpellSpecificLanguage>> = moshi.adapter(type)
        return adapter.fromJson(jsonString)!!
    }
}