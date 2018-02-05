package com.thedeadpixelsociety.moe.browse

import com.google.gson.Gson
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface BrowseApi {
    companion object {
        private const val API_URL = "http://www.gomcineplex.com"

        fun build(gson: Gson): BrowseApi {
            return Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(BrowseApi::class.java)
        }
    }

    @GET("/data/anime/sd_android.json")
    fun animeList(): Observable<List<AnimeInfo>>
}