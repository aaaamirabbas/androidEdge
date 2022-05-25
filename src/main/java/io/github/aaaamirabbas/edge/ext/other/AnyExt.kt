package io.github.aaaamirabbas.edge.ext.other

import android.util.Log

fun Any.logE(tag: String = "", throwable: Throwable? = null) {
    Log.e(tag, "ERROR -> $this\n", throwable)
}

fun Any.logI(tag: String = "", throwable: Throwable? = null) {
    Log.i(tag, "INFO -> $this\n", throwable)
}

inline fun <reified T> Any.cast(): T? {
    return if (this.isCastable<T>())
        this as T
    else null
}

inline fun <reified NEW> Any.isCastable(): Boolean {
    return this is NEW
}