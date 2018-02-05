package com.thedeadpixelsociety.moe.browse

import com.nytimes.android.external.store3.base.impl.StoreBuilder
import com.thedeadpixelsociety.moe.mvp.UseCaseWithParameter
import io.reactivex.Observable

class AnimeListUseCase(private val api: BrowseApi) : UseCaseWithParameter<String?, List<AnimeInfo>> {
    private val store by lazy {
        StoreBuilder.key<Any, List<AnimeInfo>>()
                .fetcher {
                    println("network")
                    api.animeList().singleOrError()
                }
                .open()
    }

    override fun execute(param: String?): Observable<List<AnimeInfo>> {
        return if (param == null || param.isEmpty()) {
            store.get("AnimeList").toObservable()
        } else {
            store.get("AnimeList")
                    .map { it.filter { it.title.contains(param, true) } }
                    .toObservable()
        }
    }
}