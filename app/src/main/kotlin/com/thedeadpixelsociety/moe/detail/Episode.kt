package com.thedeadpixelsociety.moe.detail

import com.google.gson.annotations.SerializedName

data class Episode(
        @SerializedName("title")
        val title: String,
        @SerializedName("href")
        val link: String
)