package com.samples.simplekotlin.data.source

import com.samples.simplekotlin.data.model.Mistake
import com.samples.simplekotlin.data.source.local.MistakesLocalDataSource

class MistakesRepository (private val localDataSource: MistakesLocalDataSource): MistakesDataSource {

    val cacheValues: LinkedHashMap<String, Mistake> = LinkedHashMap()
    val refresh = true

    override fun getAll(callback: MistakesDataSource.LoadMistakesCallback) {
        if (refresh) {
            localDataSource.getAll(object : MistakesDataSource.LoadMistakesCallback {
                override fun onTasksLoaded(tasks: List<Mistake>) {
                    callback.onTasksLoaded(tasks)
                }

                override fun onDataNotAvailable() {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            })
        } else {
            callback.onTasksLoaded(ArrayList<Mistake>(cacheValues.values))
        }
    }

    private fun cacheAndPerform(m: Mistake, perform:(Mistake) -> Unit): Unit {
        val mistake = Mistake(id = m.id, title = m.title, desc = m.desc)
        cacheValues[mistake.id] = mistake
        perform(mistake)
    }

    override fun save(mistake: Mistake) = cacheAndPerform(mistake) {
        localDataSource.save(mistake)
    }

}