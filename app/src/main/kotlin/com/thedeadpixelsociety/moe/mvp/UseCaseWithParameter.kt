package com.thedeadpixelsociety.moe.mvp

import io.reactivex.Observable

interface UseCaseWithParameter<in Param, Out> {
    fun execute(param: Param): Observable<Out>
}