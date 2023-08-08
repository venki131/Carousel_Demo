package com.example.carousel.util

import androidx.recyclerview.widget.RecyclerView
import com.mig35.carousellayoutmanager.CarouselLayoutManager

class ItemFadeOnScrollListener : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        val layoutManager = recyclerView.layoutManager as? CarouselLayoutManager ?: return
        val firstVisiblePosition = layoutManager.centerItemPosition
        val lastVisiblePosition = layoutManager.centerItemPosition + 1
        val itemCount = recyclerView.adapter?.itemCount ?: return

        for (position in 0 until itemCount) {
            val view = layoutManager.findViewByPosition(position) ?: continue
            val offset = view.left - recyclerView.paddingLeft
            val offsetPercentage = offset.toFloat() / recyclerView.width
            val alpha = 1 - kotlin.math.abs(offsetPercentage) * 2 // Fade factor change the number to set as per required

            view.alpha = alpha
        }
    }
}