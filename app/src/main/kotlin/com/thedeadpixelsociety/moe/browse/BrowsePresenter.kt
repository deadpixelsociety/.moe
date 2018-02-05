package com.thedeadpixelsociety.moe.browse

import com.thedeadpixelsociety.moe.mvp.Presenter

interface BrowsePresenter : Presenter<BrowseView> {
    fun getAnimeList(filter: String?)
}