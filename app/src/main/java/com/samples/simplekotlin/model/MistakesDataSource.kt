package com.samples.simplekotlin.model

import com.samples.simplekotlin.data.Mistake

interface MistakesDataSource {
    fun getAll(): Any
    fun save(mistake: Mistake)
}