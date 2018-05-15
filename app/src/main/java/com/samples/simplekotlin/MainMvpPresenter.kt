package com.samples.simplekotlin

interface MainMvpPresenter {
    fun attachView(view: Any)
    fun loadMistakes()
    fun showMistake(mistakeId: String)
}