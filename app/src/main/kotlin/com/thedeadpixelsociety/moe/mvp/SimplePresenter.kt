package com.thedeadpixelsociety.moe.mvp

import java.lang.ref.WeakReference

abstract class SimplePresenter<V : MvpView> : Presenter<V> {
    protected lateinit var viewRef: WeakReference<V>

    override fun attach(view: V) {
        viewRef = WeakReference(view)
    }

    override fun detach(view: V) {
        viewRef.clear()
    }
}