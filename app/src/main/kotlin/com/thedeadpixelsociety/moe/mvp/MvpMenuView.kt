package com.thedeadpixelsociety.moe.mvp

import android.app.Activity
import android.support.annotation.IdRes
import android.view.Menu

interface MvpMenuView {
    val menuId: Int

    fun onCreateOptionsMenu(menu: Menu)

    fun menuClick(@IdRes itemId: Int)
}