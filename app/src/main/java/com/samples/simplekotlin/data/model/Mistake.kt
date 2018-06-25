package com.samples.simplekotlin.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Mistake(
        @SerializedName("id") val id: String = UUID.randomUUID().toString(),
        @SerializedName("title") val title: String,
        @SerializedName("desc") val desc: String = "",
        @SerializedName("favourite") val isFavourite: Boolean = false)