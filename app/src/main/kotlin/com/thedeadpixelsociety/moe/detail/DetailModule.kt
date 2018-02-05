package com.thedeadpixelsociety.moe.detail

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton

val detailModule = Kodein.Module {
    bind<DetailPresenter>() with singleton { DetailPresenterImpl(instance(), instance()) }
    bind<DetailApi>() with singleton { DetailApi.build(instance()) }
    bind<AnimeDetailUseCase>() with singleton { AnimeDetailUseCase(instance()) }
    bind<StreamListUseCase>() with singleton { StreamListUseCase(instance()) }
}