package com.thedeadpixelsociety.moe.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.salomonbrys.kodein.KodeinInjector
import com.github.salomonbrys.kodein.instance
import com.thedeadpixelsociety.moe.MoeApp
import com.thedeadpixelsociety.moe.R
import com.thedeadpixelsociety.moe.flow.ViewStateKey
import flow.Flow
import timber.log.Timber

class DetailViewImpl(context: Context, attr: AttributeSet) : ConstraintLayout(context, attr), DetailView {
    private val bannerView by lazy { findViewById<ImageView>(R.id.detail_banner) }
    private val titleView by lazy { findViewById<TextView>(R.id.detail_title) }
    private val releasedView by lazy { findViewById<TextView>(R.id.detail_released) }
    private val statusView by lazy { findViewById<TextView>(R.id.detail_status) }
    private val descriptionView by lazy { findViewById<TextView>(R.id.detail_description) }
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.detail_list) }
    private val adapter by lazy {
        EpisodeAdapter(context) { presenter.getStreamList(it) }
    }

    private val injector = KodeinInjector()
    private val presenter by injector.instance<DetailPresenter>()

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (isInEditMode) return
        injector.inject(MoeApp.get(context).kodein)
        presenter.attach(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        Flow.getKey<ViewStateKey.Detail>(this)?.animeInfo?.apply { presenter.load(this) }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        if (isInEditMode) return
        presenter.detach(this)
    }

    override fun setData(animeDetail: AnimeDetail) {
        Glide.with(this)
                .load(animeDetail.thumbnail)
                .apply(RequestOptions.centerCropTransform())
                .into(bannerView)

        titleView.text = animeDetail.title
        releasedView.text = context.getString(R.string.released, animeDetail.released)
        statusView.text = context.getString(R.string.status, animeDetail.status)
        descriptionView.text = animeDetail.description

        adapter.setData(animeDetail.episodes)
    }

    override fun showStream(link: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        intent.setDataAndType(Uri.parse(link), "video/*")
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        } else {
            Timber.e("Unable to resolve video intent.")
        }
    }
}