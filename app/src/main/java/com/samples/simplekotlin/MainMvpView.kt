package com.samples.simplekotlin

import com.samples.simplekotlin.data.Mistake

interface MainMvpView {
    fun showMistakeList(mistakes: List<Mistake>)
    fun showMistakeDetail(mistake: Mistake)
}