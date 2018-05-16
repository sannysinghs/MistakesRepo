package com.samples.simplekotlin.utils

import android.support.v4.widget.SwipeRefreshLayout

inline fun android.support.v4.widget.SwipeRefreshLayout.onRefresh(refreshListener: SwipeRefreshLayout.OnRefreshListener) {
    setOnRefreshListener(refreshListener)
}

fun android.support.v4.widget.SwipeRefreshLayout.onRefresh(l: () -> Unit) {
    setOnRefreshListener(l)
}
