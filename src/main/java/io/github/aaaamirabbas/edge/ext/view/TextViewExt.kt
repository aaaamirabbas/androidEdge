package io.github.aaaamirabbas.edge.ext.view

import android.widget.TextView

fun TextView.clear() {
    this.text = ""
}

fun TextView.toggleExpandText(minLine: Int) {
    maxLines = if (maxLines == minLine) {
        lineCount
    } else {
        minLine
    }
}