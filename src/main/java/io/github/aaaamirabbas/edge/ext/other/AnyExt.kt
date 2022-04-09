package io.github.aaaamirabbas.edge.ext.other

import android.util.Log

fun Any.logE(tag: String = "", throwable: Throwable? = null) {
    Log.e(tag, "log -> $this\n", throwable)
}

inline fun <reified NEW> Any.cast(): NEW? {
    return if (this.isCastable<NEW>())
        this as NEW
    else null
}

inline fun <reified NEW> Any.isCastable(): Boolean {
    return this is NEW
}