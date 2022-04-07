package io.github.aaaamirabbas.edge.ext

import android.widget.ImageView
import com.aaaamirabbas.next.R
import io.github.aaaamirabbas.edge.common.utils.glide.GlideApp

fun ImageView.clear() {
    this.setImageDrawable(null)
}

private fun ImageView.load(
    url: Any,
    type: ImageView.ScaleType? = null
) {
    GlideApp.with(this.context)
        .load(url)
        .error(R.mipmap.ic_icon)
        .into(this)

    adjustViewBounds = true
    type?.let { scaleType = type }
}
