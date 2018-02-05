package com.thedeadpixelsociety.moe.detail

import com.google.gson.Gson
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface DetailApi {
    companion object {
        private const val API_URL = "http://animeonline.club"

        fun build(gson: Gson): DetailApi {
            return Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(DetailApi::class.java)
        }
    }

    @POST("/php/watchcartoononline.php")
    @FormUrlEncoded
    fun episodeInfo(@Field("Episodes") title: String): Observable<AnimeEpisodeInfo>

    @POST("/php/watchcartoononline.php")
    @FormUrlEncoded
    fun stream(@Field("LinkIos") link: String): Observable<StreamList>
}