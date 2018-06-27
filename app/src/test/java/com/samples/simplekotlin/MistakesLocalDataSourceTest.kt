package com.samples.simplekotlin

import android.content.SharedPreferences
import com.google.gson.Gson
import com.samples.simplekotlin.data.model.Mistake
import com.samples.simplekotlin.data.model.MistakesResponse
import com.samples.simplekotlin.data.source.MistakesDataSource
import com.samples.simplekotlin.data.source.local.MistakesLocalDataSource
import org.junit.Test
import org.mockito.Mockito.*

class MistakesLocalDataSourceTest {

    private val mistakes by lazy {
        listOf(
                Mistake(title = "Do not forgot passport", id = "1", desc = "Description")
        )
    }

    private val response by lazy {
        Gson().toJson(MistakesResponse(mistakes = mistakes), MistakesResponse::class.java)
    }

    @Test
    fun getAll_Test() {

        val mkSharePref = mock(SharedPreferences::class.java)
        `when`(mkSharePref.getString(MistakesLocalDataSource.PREF_TABLE_NAME, "")).thenReturn(response)

        val mkCallback =  mock(MistakesDataSource.LoadMistakesCallback::class.java)
        val localDataSource = MistakesLocalDataSource(mkSharePref)

        localDataSource.getAll(mkCallback)

        verify(mkSharePref).getString(MistakesLocalDataSource.PREF_TABLE_NAME, "")
        verify(mkCallback).onTasksLoaded(mistakes)
    }

    @Test
    fun save_Test() {
        val mkSharePref = mock(SharedPreferences::class.java)
        `when`(mkSharePref.getString(MistakesLocalDataSource.PREF_TABLE_NAME, "")).thenReturn(response)
        val mkSharePrefEditor = mock(SharedPreferences.Editor::class.java)
        `when`(mkSharePref.edit()).thenReturn(mkSharePrefEditor)
        //when
        val localDataSource = MistakesLocalDataSource(mkSharePref)
        val newMistake = Mistake("10", "Lover", "Chit Thu")
        localDataSource.save(newMistake)

        //then
        val finalMistakes = ArrayList<Mistake>(mistakes)
        finalMistakes.add(newMistake)

        verify(mkSharePrefEditor)
                .putString(MistakesLocalDataSource.PREF_TABLE_NAME, Gson().toJson(MistakesResponse(mistakes = finalMistakes), MistakesResponse::class.java))
        verify(mkSharePrefEditor)
                .apply()
    }

}