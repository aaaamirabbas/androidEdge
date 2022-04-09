package io.github.aaaamirabbas.edge.ext.other

fun <T> Array<T>.logE(tag: String = "", throwable: Throwable? = null) {
    this.forEach {
        it?.logE(tag, throwable)
    }
}