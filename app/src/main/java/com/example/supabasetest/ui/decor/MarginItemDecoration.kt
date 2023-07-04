package com.example.supabasetest.ui.decor

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(private val spacing: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val manager = parent.layoutManager as? LinearLayoutManager
            ?: return

        val position = parent.getChildAdapterPosition(view)

        if (manager.orientation == RecyclerView.VERTICAL) {
            if (position > 0) {
                outRect.top = spacing
            }
        } else {
            if (position > 0) {
                outRect.left = spacing
            }
        }
    }
}