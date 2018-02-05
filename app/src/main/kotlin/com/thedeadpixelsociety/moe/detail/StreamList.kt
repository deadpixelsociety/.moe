package com.thedeadpixelsociety.moe.detail

import com.google.gson.annotations.SerializedName

data class StreamList(
        @SerializedName("mp4upload")
        val mp4upload: String,
        @SerializedName("gstream")
        val gstream: String,
        @SerializedName("openload")
        val openload: String,
        @SerializedName("streamango")
        val streamango: String,
        @SerializedName("estream")
        val estream: String
)