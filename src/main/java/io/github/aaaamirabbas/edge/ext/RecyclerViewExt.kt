package io.github.aaaamirabbas.edge.ext

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.configDynamicGridLayout(dpi: Float) {
    layoutManager = GridLayoutManager(
        this.context, when (dpi) {
            in 1.5f..3.0f -> 2
            in 3.0f..4.0f -> 3
            else -> 1
        }
    ).apply {
        spanSizeLookup = object :
            GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return 1
            }
        }
    }
}