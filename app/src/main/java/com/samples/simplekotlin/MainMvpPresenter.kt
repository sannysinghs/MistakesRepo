package com.samples.simplekotlin

interface MainMvpPresenter {
    fun attachView(view: MainMvpView)
    fun loadMistakes()
    fun showMistake(mistakeId: String)
}