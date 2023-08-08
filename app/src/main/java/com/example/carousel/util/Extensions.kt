package com.example.carousel.util

import android.os.Handler
import androidx.recyclerview.widget.RecyclerView
import com.mig35.carousellayoutmanager.CarouselLayoutManager
import java.lang.Math.abs

fun RecyclerView.startAutoScroll(interval: Long) {
    val autoScrollHandler = Handler()
    val autoScrollRunnable = object : Runnable {
        override fun run() {
            val layoutManager = layoutManager as? CarouselLayoutManager
            val currentPosition = layoutManager?.centerItemPosition

            if (currentPosition != null) {
                val itemCount = adapter?.itemCount ?: 0
                val nextPosition =
                    (currentPosition + 1) % itemCount // Calculate the next position (circular scrolling)
                smoothScrollToPosition(nextPosition)
            }

            // Schedule the next auto-scroll after the interval
            autoScrollHandler.postDelayed(this, interval)
        }
    }

    autoScrollHandler.postDelayed(autoScrollRunnable, interval)
}

fun RecyclerView.stopAutoScroll() {
    val autoScrollHandler = Handler()
    autoScrollHandler.removeCallbacksAndMessages(null)
}

fun RecyclerView.addOnItemFadeScrollListener(fadeFactor: Float, onScrollAction: (position: Int, alpha: Float) -> Unit) {
    val layoutManager = layoutManager as? CarouselLayoutManager ?: return

    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val firstVisiblePosition = layoutManager.centerItemPosition
            val lastVisiblePosition = layoutManager.centerItemPosition + 1
            val itemCount = adapter?.itemCount ?: return

            for (position in 0 until itemCount) {
                val view = layoutManager.findViewByPosition(position) ?: continue
                val offset = view.left - recyclerView.paddingLeft
                val offsetPercentage = offset.toFloat() / recyclerView.width
                val alpha = 1 - abs(offsetPercentage) * fadeFactor

                onScrollAction(position, alpha)
            }
        }
    })
}
