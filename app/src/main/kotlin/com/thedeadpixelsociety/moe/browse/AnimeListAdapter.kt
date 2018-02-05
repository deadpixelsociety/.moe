package com.thedeadpixelsociety.moe.browse

import android.animation.AnimatorInflater
import android.content.Context
import android.os.Build
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.jakewharton.rxbinding2.view.RxView
import com.thedeadpixelsociety.moe.R

class AnimeListAdapter(context: Context, private val onClick: (AnimeInfo) -> Unit)
    : RecyclerView.Adapter<AnimeListViewHolder>() {

    private val inflater by lazy { LayoutInflater.from(context) }
    private val data = arrayListOf<AnimeInfo>()

    fun setData(list: List<AnimeInfo>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AnimeListViewHolder {
        return AnimeListViewHolder(inflater.inflate(R.layout.row_browse, parent, false))
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: AnimeListViewHolder?, position: Int) {
        holder?.bind(data[position])
        holder?.itemView?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                it.stateListAnimator = AnimatorInflater.loadStateListAnimator(
                        it.context,
                        R.animator.lift_on_touch
                )
            }

            RxView.clicks(it).subscribe { onClick(data[position]) }
        }
    }
}