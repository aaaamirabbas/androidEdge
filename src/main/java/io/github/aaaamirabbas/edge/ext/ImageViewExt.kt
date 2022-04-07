package io.github.aaaamirabbas.edge.ext

import android.widget.ImageView
import io.github.aaaamirabbas.edge.utils.glide.GlideApp

fun ImageView.clear() {
    this.setImageDrawable(null)
}

private fun ImageView.loadCompact(
    url: Any,
    type: ImageView.ScaleType? = null
) {
    GlideApp.with(this.context)
        .load(url)
        .into(this)

    adjustViewBounds = true
    type?.let { scaleType = type }
}
