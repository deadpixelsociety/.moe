package com.thedeadpixelsociety.moe.browse

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.thedeadpixelsociety.moe.R

class AnimeListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val cardView by lazy { itemView.findViewById<CardView>(R.id.row_browse_card) }
    private val imageView by lazy { itemView.findViewById<ImageView>(R.id.row_browse_image) }
    private val titleView by lazy { itemView.findViewById<TextView>(R.id.row_browse_title) }

    fun bind(info: AnimeInfo) {
        titleView.text = info.title
        imageView.contentDescription = info.title
        Glide.with(imageView)
                .load(info.thumbnail)
                .apply(RequestOptions.centerCropTransform())
                .into(imageView)
    }
}