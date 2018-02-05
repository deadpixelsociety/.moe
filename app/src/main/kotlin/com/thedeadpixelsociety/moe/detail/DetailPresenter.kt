package com.thedeadpixelsociety.moe.detail

import com.thedeadpixelsociety.moe.browse.AnimeInfo
import com.thedeadpixelsociety.moe.mvp.Presenter

interface DetailPresenter : Presenter<DetailView> {
    fun load(animeInfo: AnimeInfo)

    fun getStreamList(episode: Episode)
}