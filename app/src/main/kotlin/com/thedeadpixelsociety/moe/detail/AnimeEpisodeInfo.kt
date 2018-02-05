package com.thedeadpixelsociety.moe.detail

import com.google.gson.annotations.SerializedName

data class AnimeEpisodeInfo(
        @SerializedName("episodes")
        val episodes: List<Episode>,
        @SerializedName("desc")
        val description: List<Content>,
        @SerializedName("status")
        val status: List<Content>,
        @SerializedName("released")
        val released: List<Content>
)