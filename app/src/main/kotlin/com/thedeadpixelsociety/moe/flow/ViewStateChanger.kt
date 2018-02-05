package com.thedeadpixelsociety.moe.flow

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.thedeadpixelsociety.moe.R
import flow.Direction
import flow.KeyChanger
import flow.State
import flow.TraversalCallback
import timber.log.Timber
import java.lang.ref.WeakReference

class ViewStateChanger(activity: Activity) : KeyChanger {
    private val activityRef = WeakReference(activity)

    override fun changeKey(
            outgoingState: State?,
            incomingState: State,
            direction: Direction,
            incomingContexts: MutableMap<Any, Context>,
            callback: TraversalCallback
    ) {
        val activity = activityRef.get()
        if (activity == null) {
            Timber.w("Lost activity reference.")
            callback.onTraversalCompleted()
            return
        }

        val key = incomingState.getKey<ViewStateKey>()
        val context = incomingContexts[key]

        val content = activity.findViewById<ViewGroup>(R.id.content)
        outgoingState?.apply { save(content.getChildAt(0)) }

        val view = LayoutInflater.from(context).inflate(key.layoutId, content, false)
        incomingState.restore(view)
        content.removeAllViews()
        content.addView(view)
        activity.invalidateOptionsMenu()
        callback.onTraversalCompleted()
    }
}