package com.samples.simplekotlin.data

import java.util.*

data class Mistake(
        val id: String = UUID.randomUUID().toString(),
        val title: String,
        val desc: String = "",
        val isFavourite: Boolean = false)