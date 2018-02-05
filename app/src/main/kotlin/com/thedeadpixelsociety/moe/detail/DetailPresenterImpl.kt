package com.thedeadpixelsociety.moe.detail

import com.thedeadpixelsociety.moe.browse.AnimeInfo
import com.thedeadpixelsociety.moe.mvp.SimplePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailPresenterImpl(
        private val detailUseCase: AnimeDetailUseCase,
        private val streamUseCase: StreamListUseCase
) : SimplePresenter<DetailView>(), DetailPresenter {

    override fun load(animeInfo: AnimeInfo) {
        detailUseCase.execute(animeInfo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { viewRef.get()?.setData(it) }
    }

    override fun getStreamList(episode: Episode) {
        streamUseCase.execute(episode.link)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    val streams = listOf(it.estream, it.gstream, it.mp4upload, it.openload, it.streamango)
                    val stream = streams.firstOrNull { it != "none" }
                    stream?.let { viewRef.get()?.showStream(it) }
                }
    }
}