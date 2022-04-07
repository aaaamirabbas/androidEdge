package io.github.aaaamirabbas.edge.ext

import android.util.Log

fun Any.logE(tag: String = "", throwable: Throwable? = null) {
    Log.e(tag, "log -> $this\n", throwable)
}