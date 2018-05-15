package com.samples.simplekotlin.data

data class Mistake(val id: String, val title: String, val desc: String = "", val isFavourite: Boolean = false)