package com.thedeadpixelsociety.moe.detail

import com.nytimes.android.external.store3.base.impl.StoreBuilder
import com.thedeadpixelsociety.moe.browse.AnimeInfo
import com.thedeadpixelsociety.moe.mvp.UseCaseWithParameter
import io.reactivex.Observable

class AnimeDetailUseCase(private val api: DetailApi)
    : UseCaseWithParameter<AnimeInfo, AnimeDetail> {
    private val store by lazy {
        StoreBuilder.key<String, AnimeEpisodeInfo>()
                .fetcher { api.episodeInfo(it).singleOrError() }
                .open()
    }

    override fun execute(param: AnimeInfo): Observable<AnimeDetail> {
        return store.get(param.link)
                .map { AnimeDetail.create(param, it) }
                .toObservable()
    }
}