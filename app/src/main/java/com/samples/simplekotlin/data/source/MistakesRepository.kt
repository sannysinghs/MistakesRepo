package com.samples.simplekotlin.data.source

import com.samples.simplekotlin.data.model.Mistake

class MistakesRepository: MistakesDataSource {

    val cacheValues: LinkedHashMap<String, Mistake> = LinkedHashMap()

    override fun getAll(callback: MistakesDataSource.LoadMistakesCallback) {

    }

    private fun cacheAndPerform(m: Mistake, perform:(Mistake) -> Unit): Unit {
        val mistake = Mistake(id = m.id, title = m.title, desc = m.desc)
        cacheValues[mistake.id] = mistake
        perform(mistake)
    }

    override fun save(mistake: Mistake) {
        cacheAndPerform(mistake) {
            //STORE TO PREFERENCE HERE
            //DO REMOTE STORAGE HERE
        }
    }

}