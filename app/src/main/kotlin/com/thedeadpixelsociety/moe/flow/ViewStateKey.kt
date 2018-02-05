package com.thedeadpixelsociety.moe.flow

import android.os.Parcelable
import android.support.annotation.LayoutRes
import com.google.auto.value.AutoValue
import com.thedeadpixelsociety.moe.R
import com.thedeadpixelsociety.moe.browse.AnimeInfo

sealed class ViewStateKey(@LayoutRes val layoutId: Int) : Parcelable {
    @AutoValue
    abstract class Browse : ViewStateKey(R.layout.view_browse) {
        companion object {
            fun create(): Browse = AutoValue_ViewStateKey_Browse()
        }
    }

    @AutoValue
    abstract class Detail : ViewStateKey(R.layout.view_detail) {
        abstract val animeInfo: AnimeInfo

        companion object {
            fun create(animeInfo: AnimeInfo): Detail = AutoValue_ViewStateKey_Detail(animeInfo)
        }
    }
}