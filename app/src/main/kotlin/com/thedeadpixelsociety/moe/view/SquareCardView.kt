package com.thedeadpixelsociety.moe.view

import android.content.Context
import android.support.v7.widget.CardView
import android.util.AttributeSet

class SquareCardView(context: Context, attr: AttributeSet) : CardView(context, attr) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }
}