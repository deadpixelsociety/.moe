package com.thedeadpixelsociety.moe.browse

import com.thedeadpixelsociety.moe.mvp.SimplePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class BrowsePresenterImpl(private val animeList: AnimeListUseCase)
    : SimplePresenter<BrowseView>(), BrowsePresenter {
    private val disposables = CompositeDisposable()

    override fun attach(view: BrowseView) {
        super.attach(view)
        disposables.add(view.searchChanged().subscribe { getAnimeList(it) })
        getAnimeList(null)
    }

    override fun detach(view: BrowseView) {
        super.detach(view)
        disposables.clear()
    }

    override fun getAnimeList(filter: String?) {
        animeList.execute(filter)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { viewRef.get()?.setData(it) }
    }
}