package io.github.aaaamirabbas.edge.ext

import android.annotation.TargetApi
import android.os.Build
import android.widget.TextView

fun TextView.clear() {
    this.text = ""
}

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
fun TextView.setDrawable(
    start: Int = 0, end: Int = 0,
    top: Int = 0, bottom: Int = 0
) {
    this.setCompoundDrawablesRelativeWithIntrinsicBounds(
        start, top,
        end, bottom
    )
}

fun TextView.setDrawableStart(res: Int) {
    this.setCompoundDrawablesRelativeWithIntrinsicBounds(
        res, 0,
        0, 0
    )
}

fun TextView.setDrawableEnd(res: Int) {
    this.setCompoundDrawablesRelativeWithIntrinsicBounds(
        0, 0,
        res, 0
    )
}

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
fun TextView.toggleText(minLine: Int) {
    maxLines = if (maxLines == minLine) {
        lineCount
    } else {
        minLine
    }
}