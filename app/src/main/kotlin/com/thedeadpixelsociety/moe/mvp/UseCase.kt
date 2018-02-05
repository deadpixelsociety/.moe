package com.thedeadpixelsociety.moe.mvp

import io.reactivex.Observable

interface UseCase<Out> {
    fun execute(): Observable<Out>
}