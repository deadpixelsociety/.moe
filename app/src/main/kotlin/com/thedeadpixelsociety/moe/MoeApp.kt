package com.thedeadpixelsociety.moe

import android.app.Application
import android.content.Context
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.google.gson.GsonBuilder
import com.thedeadpixelsociety.moe.browse.browseModule
import com.thedeadpixelsociety.moe.detail.detailModule
import timber.log.Timber

class MoeApp : Application(), KodeinAware {
    companion object {
        fun get(context: Context) = context.applicationContext as MoeApp
    }

    override val kodein = Kodein {
        bind() from instance(GsonBuilder().create())
        import(browseModule)
        import(detailModule)
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}