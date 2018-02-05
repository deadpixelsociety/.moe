package com.thedeadpixelsociety.moe.detail

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.thedeadpixelsociety.moe.R

class EpisodeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val nameView by lazy { itemView.findViewById<TextView>(R.id.episode_name) }

    fun bind(episode: Episode) {
        nameView.text = episode.title
    }
}