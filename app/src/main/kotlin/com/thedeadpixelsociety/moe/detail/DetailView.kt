package com.thedeadpixelsociety.moe.detail

import com.thedeadpixelsociety.moe.mvp.MvpView

interface DetailView : MvpView {
    fun setData(animeDetail: AnimeDetail)
    fun showStream(link: String)
}