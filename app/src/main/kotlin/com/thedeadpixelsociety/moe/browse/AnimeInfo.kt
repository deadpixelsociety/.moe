package com.thedeadpixelsociety.moe.browse

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AnimeInfo(
        @SerializedName("Title")
        val title: String,
        @SerializedName("Thumb")
        val thumbnail: String,
        @SerializedName("Link")
        val link: String,
        @SerializedName("Genres")
        val genres: String,
        @SerializedName("IsOn")
        val isOn: Boolean
) : Serializable {
    companion object {
        const val serialVersionUID = 1L
    }

    fun genreList(): List<String> = genres.split(',')
}