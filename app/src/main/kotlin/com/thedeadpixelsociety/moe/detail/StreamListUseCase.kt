package com.thedeadpixelsociety.moe.detail

import com.nytimes.android.external.store3.base.impl.StoreBuilder
import com.thedeadpixelsociety.moe.mvp.UseCaseWithParameter
import io.reactivex.Observable

class StreamListUseCase(private val api: DetailApi)
    : UseCaseWithParameter<String, StreamList> {
    private val store by lazy {
        StoreBuilder.key<String, StreamList>()
                .fetcher { api.stream(it).singleOrError() }
                .open()
    }

    override fun execute(param: String): Observable<StreamList> {
        return store.get(param).toObservable()
    }
}