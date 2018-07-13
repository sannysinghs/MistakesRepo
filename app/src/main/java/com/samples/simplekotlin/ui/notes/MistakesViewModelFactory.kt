package com.samples.simplekotlin.ui.notes

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.samples.simplekotlin.data.source.MistakesRepository

class MistakesViewModelFactory(
        private val context: Application,
        private val repository: MistakesRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = if (AndroidViewModel::class.java.isAssignableFrom(modelClass)) {
            MistakesViewModel(context = context, mistakeRepo = repository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }

}