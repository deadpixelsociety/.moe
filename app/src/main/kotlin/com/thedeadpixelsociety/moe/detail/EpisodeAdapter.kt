package com.thedeadpixelsociety.moe.detail

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.jakewharton.rxbinding2.view.RxView
import com.thedeadpixelsociety.moe.R

class EpisodeAdapter(context: Context, private val onClick: (Episode) -> Unit)
    : RecyclerView.Adapter<EpisodeViewHolder>() {

    private val inflater by lazy { LayoutInflater.from(context) }
    private val data = arrayListOf<Episode>()

    fun setData(list: List<Episode>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder(inflater.inflate(R.layout.row_episode, parent, false))
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: EpisodeViewHolder?, position: Int) {
        holder?.bind(data[position])
        holder?.itemView?.let {
            RxView.clicks(it).subscribe { onClick(data[position]) }
        }
    }
}