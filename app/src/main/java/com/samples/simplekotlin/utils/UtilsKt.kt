package com.samples.simplekotlin.utils

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun <T : View> Fragment.bind(rootView: View, resId: Int) = lazy { rootView.findViewById(resId) as T }
inline fun <reified T : View> View.find(@IdRes id: Int): T = findViewById(id)


fun ViewGroup.inflate(resLayoutId: Int) : View {
    return LayoutInflater.from(context)
            .inflate(resLayoutId, this, false)
}