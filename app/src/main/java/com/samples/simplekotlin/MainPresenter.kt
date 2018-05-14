package com.samples.simplekotlin
class MainPresenter : MainMvpPresenter {

    lateinit var view: MainMvpView

    override fun attachView(view: MainMvpView) {
        this.view = view
    }

    override fun loadMistakes() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMistake(mistakeId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}