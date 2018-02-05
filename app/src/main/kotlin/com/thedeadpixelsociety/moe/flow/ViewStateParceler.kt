package com.thedeadpixelsociety.moe.flow

import android.os.Parcelable
import flow.KeyParceler

class ViewStateParceler : KeyParceler {
    override fun toParcelable(key: Any): Parcelable {
        return key as Parcelable
    }

    override fun toKey(parcelable: Parcelable): Any {
        return parcelable
    }
}