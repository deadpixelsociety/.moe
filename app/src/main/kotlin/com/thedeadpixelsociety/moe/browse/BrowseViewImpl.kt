package com.thedeadpixelsociety.moe.browse

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.util.AttributeSet
import android.view.Menu
import com.github.salomonbrys.kodein.KodeinInjector
import com.github.salomonbrys.kodein.instance
import com.thedeadpixelsociety.moe.MoeApp
import com.thedeadpixelsociety.moe.R
import com.thedeadpixelsociety.moe.flow.ViewStateKey
import flow.Flow
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import java.util.concurrent.TimeUnit

class BrowseViewImpl(context: Context, attr: AttributeSet)
    : ConstraintLayout(context, attr), BrowseView {

    private val browseList by lazy { findViewById<RecyclerView>(R.id.browse_list) }
    private val searchSubject = PublishSubject.create<String?>()
    private val adapter by lazy {
        AnimeListAdapter(context) {
            Timber.i(it.toString())
            Flow.get(this).set(ViewStateKey.Detail.create(it))
        }
    }

    private val injector = KodeinInjector()
    private val presenter by injector.instance<BrowsePresenter>()

    override val menuId = R.menu.menu_browse

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (isInEditMode) return
        injector.inject(MoeApp.get(context).kodein)
        presenter.attach(this)
        browseList.layoutManager = GridLayoutManager(context, context.resources.getInteger(R.integer.browse_colspan))
        browseList.adapter = adapter
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        if (isInEditMode) return
        presenter.detach(this)
    }

    override fun setData(list: List<AnimeInfo>) {
        adapter.setData(list)
    }

    override fun searchChanged(): Observable<String?> =
            searchSubject.debounce(200L, TimeUnit.MILLISECONDS)

    override fun onCreateOptionsMenu(menu: Menu) {
        val searchView = menu.findItem(R.id.browse_search).actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchSubject.onNext(query ?: "")
                menu.findItem(R.id.browse_search).collapseActionView()
                return true
            }

            override fun onQueryTextChange(newText: String?) = true
        })
    }

    override fun menuClick(itemId: Int) {
    }
}