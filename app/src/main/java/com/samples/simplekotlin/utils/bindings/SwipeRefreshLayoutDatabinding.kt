package com.samples.simplekotlin.utils.bindings

import android.databinding.BindingAdapter
import android.support.v4.widget.SwipeRefreshLayout
import com.samples.simplekotlin.ui.notes.MistakesViewModel

class SwipeRefreshLayoutDatabinding {

    @BindingAdapter("android:onRefresh")
    fun setOnRefreshLayouListener(view: SwipeRefreshLayout, vm: MistakesViewModel) {
        view.setOnRefreshListener {
            //load mistakes here...
            vm.loadAllMistakes(true)
        }
    }
}