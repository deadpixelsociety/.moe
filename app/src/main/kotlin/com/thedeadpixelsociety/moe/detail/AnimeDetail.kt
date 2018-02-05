package com.thedeadpixelsociety.moe.detail

import com.thedeadpixelsociety.moe.browse.AnimeInfo

data class AnimeDetail(
        val title: String,
        val thumbnail: String,
        val link: String,
        val genres: String,
        val episodes: List<Episode>,
        val description: String?,
        val status: String?,
        val released: String?
) {
    companion object {
        fun create(animeInfo: AnimeInfo, episodeInfo: AnimeEpisodeInfo) =
                AnimeDetail(
                        animeInfo.title,
                        animeInfo.thumbnail,
                        animeInfo.link,
                        animeInfo.genres,
                        episodeInfo.episodes,
                        episodeInfo.description.firstOrNull()?.content?.trim(),
                        episodeInfo.status.firstOrNull()?.content?.trim(),
                        episodeInfo.released.firstOrNull()?.content?.trim()
                )
    }

    fun genreList(): List<String> = genres.split(',')
}