package com.samples.simplekotlin.data.source.local

import android.content.SharedPreferences
import com.google.gson.Gson
import com.samples.simplekotlin.data.model.Mistake
import com.samples.simplekotlin.data.model.MistakesResponse
import com.samples.simplekotlin.data.source.MistakesDataSource
import java.util.*

class MistakesLocalDataSource (private val storage: SharedPreferences): MistakesDataSource {

    private val gson by lazy {
        Gson()
    }

    override fun getAll(callback: MistakesDataSource.LoadMistakesCallback) {
        val fromJson =
                gson.fromJson(storage.getString(PREF_TABLE_NAME, ""), MistakesResponse::class.java)

        callback.onTasksLoaded(fromJson?.mistakes ?: ArrayList())
    }

    override fun save(mistake: Mistake) {
        val data =
                gson.fromJson(storage.getString(PREF_TABLE_NAME, ""), MistakesResponse::class.java)

        val mistakes = ArrayList<Mistake>(data?.mistakes ?: listOf())
        mistakes.add(mistake)

        storage.edit()
                .apply {
                    putString(PREF_TABLE_NAME, Gson().toJson(MistakesResponse(mistakes), MistakesResponse::class.java))
                    apply()
                }
    }

    companion object {
        const val PREF_TABLE_NAME = "mistakes"
        const val PREF_FILE_NAME = "mistakes"
    }

}