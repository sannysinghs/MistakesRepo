package com.samples.simplekotlin

import com.samples.simplekotlin.data.model.Mistake

interface MainMvpView {
    fun showMistakeList(mistakes: List<Mistake>)
    fun showMistakeDetail(mistake: Mistake)
    fun showLoading(b: Boolean)
}