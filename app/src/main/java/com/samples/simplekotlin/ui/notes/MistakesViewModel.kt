package com.samples.simplekotlin.ui.notes

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import com.samples.simplekotlin.data.model.Mistake
import com.samples.simplekotlin.data.source.MistakesDataSource
import com.samples.simplekotlin.data.source.MistakesRepository

class MistakesViewModel: ViewModel() {

    lateinit var mistakeRepo: MistakesRepository
    val showLoading = ObservableBoolean(false)

    val liveItems = MutableLiveData<List<Mistake>>()

    fun loadAllMistakes(forceLoad: Boolean) {
        showLoading.set(true)
        if (forceLoad) {
            mistakeRepo.refreshData()
        }

        mistakeRepo.getAll(object: MistakesDataSource.LoadMistakesCallback {
            override fun onTasksLoaded(tasks: List<Mistake>) {
                showLoading.set(false)
                liveItems.value = tasks
            }

            override fun onDataNotAvailable() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }
}
