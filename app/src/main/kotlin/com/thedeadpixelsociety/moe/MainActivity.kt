package com.thedeadpixelsociety.moe

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.thedeadpixelsociety.moe.flow.ViewStateChanger
import com.thedeadpixelsociety.moe.flow.ViewStateKey
import com.thedeadpixelsociety.moe.flow.ViewStateParceler
import com.thedeadpixelsociety.moe.mvp.MvpMenuView
import flow.Flow
import flow.KeyDispatcher

class MainActivity : AppCompatActivity() {
    private val bottomNavigation by lazy { findViewById<BottomNavigationView>(R.id.bottom_navigation) }
    private val contentView by lazy { findViewById<ViewGroup>(R.id.content) }

    override fun attachBaseContext(newBase: Context?) {

        super.attachBaseContext(newBase?.let {
            Flow.configure(it, this)
                    .dispatcher(KeyDispatcher.configure(this, ViewStateChanger(this)).build())
                    .keyParceler(ViewStateParceler())
                    .defaultKey(ViewStateKey.Browse.create())
                    .install()
        } ?: newBase)
    }

    private fun getCurrentView(): View? {
        if (contentView.childCount > 0) return contentView.getChildAt(0)
        return null
    }

    private fun getMenuView() = getCurrentView() as? MvpMenuView

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuView()?.let { view ->
            menuInflater.inflate(view.menuId, menu)
            menu?.let { view.onCreateOptionsMenu(it) }
            return true
        }

        return false
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        getMenuView()?.let {
            it.menuClick(item?.itemId ?: 0)
            return true
        }

        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_navigation_browse -> {
                    Flow.get(this).set(ViewStateKey.Browse.create())
                    true
                }
                else -> false
            }
        }
    }

    override fun onBackPressed() {
        if (Flow.get(this).goBack()) return
        super.onBackPressed()
    }
}