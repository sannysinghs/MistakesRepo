package com.samples.simplekotlin.data.source

import com.samples.simplekotlin.data.model.Mistake

interface MistakesDataSource {
    fun getAll(callback: LoadMistakesCallback)
    fun save(mistake: Mistake)

    interface LoadMistakesCallback {

        fun onTasksLoaded(tasks: List<Mistake>)

        fun onDataNotAvailable()
    }

    interface GetTaskCallback {

        fun onTaskLoaded(task: Mistake)

        fun onDataNotAvailable()
    }
}