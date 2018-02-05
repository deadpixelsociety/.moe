package com.thedeadpixelsociety.moe.browse

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton

val browseModule = Kodein.Module {
    bind<BrowsePresenter>() with singleton { BrowsePresenterImpl(instance()) }
    bind<BrowseApi>() with singleton { BrowseApi.build(instance()) }
    bind<AnimeListUseCase>() with singleton { AnimeListUseCase(instance()) }
}