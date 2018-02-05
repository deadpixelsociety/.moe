package com.thedeadpixelsociety.moe.browse

import com.thedeadpixelsociety.moe.mvp.MvpMenuView
import com.thedeadpixelsociety.moe.mvp.MvpView
import io.reactivex.Observable

interface BrowseView : MvpView, MvpMenuView {
    fun searchChanged(): Observable<String?>

    fun setData(list: List<AnimeInfo>)
}