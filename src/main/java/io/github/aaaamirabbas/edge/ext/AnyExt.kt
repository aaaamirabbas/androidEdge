package io.github.aaaamirabbas.edge.ext

import android.util.Log

fun Any.logV(tag: String = "", throwable: Throwable? = null) {
    Log.v("nExt -> $tag", "$this\n", throwable)
}

fun Any.logE(tag: String = "", throwable: Throwable? = null) {
    Log.e("nExt -> $tag", "$this\n", throwable)
}

fun Any.logI(tag: String = "", throwable: Throwable? = null) {
    Log.i("nExt -> $tag", "$this\n", throwable)
}

fun Any.logD(tag: String = "", throwable: Throwable? = null) {
    Log.d("nExt -> $tag", "$this\n", throwable)
}

fun Any.logW(tag: String = "", throwable: Throwable? = null) {
    Log.w("nExt -> $tag", "$this\n", throwable)
}

fun Any.logWTF(tag: String = "", throwable: Throwable? = null) {
    Log.wtf("nExt -> $tag", "$this\n", throwable)
}
