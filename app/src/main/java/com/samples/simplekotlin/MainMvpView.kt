package com.samples.simplekotlin

interface MainMvpView {
    fun showMistakeList(mistakes: List<Any>)
    fun showMistakeDetail(mistake: Any)
}