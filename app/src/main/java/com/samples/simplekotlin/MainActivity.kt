package com.samples.simplekotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity :
        AppCompatActivity() {

    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupMainPagerFragment()

    }

    private fun setupMainPagerFragment() {
        supportFragmentManager
                .beginTransaction()
                .add(R.id.main_container,  MainPagerFragment(), MainPagerFragment.TAG)
                .commit()
    }

}
