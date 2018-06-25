package com.samples.simplekotlin.data.source.local

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.samples.simplekotlin.data.model.Mistake
import com.samples.simplekotlin.data.model.MistakesResponse
import com.samples.simplekotlin.data.source.MistakesDataSource

class MistakesLocalDataSource (context: Context): MistakesDataSource {

    private val storage: SharedPreferences
    init {
        storage = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
    }

    override fun getAll(callback: MistakesDataSource.LoadMistakesCallback) {
        val fromJson =
                Gson().fromJson(storage.getString(PREF_TABLE_NAME, ""), MistakesResponse::class.java)
        callback.onTasksLoaded(fromJson.mistakes)
    }

    override fun save(mistake: Mistake) {
        val mistakeCollection =
                Gson().fromJson(storage.getString(PREF_TABLE_NAME, ""), MistakesResponse::class.java)
        //add to list
        storage.edit()
                .putString(PREF_TABLE_NAME, Gson().toJson(mistakeCollection, MistakesResponse::class.java))
                .apply()
    }

    companion object {
        const val PREF_TABLE_NAME = "mistakes"
        const val PREF_FILE_NAME = "mistakes"
    }

}