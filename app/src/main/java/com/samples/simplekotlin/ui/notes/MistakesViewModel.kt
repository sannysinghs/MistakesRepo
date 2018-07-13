package com.samples.simplekotlin.ui.notes

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.content.ContextWrapper
import android.databinding.*
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import com.samples.simplekotlin.R
import com.samples.simplekotlin.data.model.Mistake
import com.samples.simplekotlin.data.source.MistakesDataSource
import com.samples.simplekotlin.data.source.MistakesRepository

class MistakesViewModel(context: Application,
                        private val mistakeRepo: MistakesRepository) : AndroidViewModel(context) {


    val showLoading = ObservableBoolean(false)
    val noNotesLabel = ObservableField<String>()
    val noNotesIconRes = ObservableField<Drawable>()
    val empty = ObservableBoolean(false)

    val liveItems = MutableLiveData<List<Mistake>>()

    fun loadAllMistakes(forceLoad: Boolean) {
        showLoading.set(true)
        if (forceLoad) {
            mistakeRepo.refreshData()
        }

        mistakeRepo.getAll(object: MistakesDataSource.LoadMistakesCallback {
            override fun onTasksLoaded(tasks: List<Mistake>) {
                showLoading.set(false)
                empty.set(tasks.isEmpty())
                if (tasks.isEmpty()) {
                    noNotesLabel.set("Die learning quickly or else you will have not time!")
                    noNotesIconRes.set( ContextCompat.getDrawable(getApplication(), R.drawable.ic_list))
                }
                liveItems.value = tasks
            }

            override fun onDataNotAvailable() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

}
