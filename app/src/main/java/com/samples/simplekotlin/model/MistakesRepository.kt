package com.samples.simplekotlin.model

import com.samples.simplekotlin.data.Mistake

class MistakesRepository: MistakesDataSource {

    val cacheValues: LinkedHashMap<String, Mistake> = LinkedHashMap()

    override fun getAll(): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun cacheAndPerform(m: Mistake, perform:(Mistake) -> Unit): Unit {
        val mistake = Mistake(id = m.id, title = m.title, desc = m.desc)
        cacheValues[mistake.id] = mistake
        perform(mistake)
    }

    override fun save(mistake: Mistake) {
        cacheAndPerform(mistake) {

        }
    }

}